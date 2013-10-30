package com.unicom.mms.activemq;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;

import com.cmcc.mm7.vasp.message.MM7RSRes;
import com.cmcc.mm7.vasp.message.MM7SubmitRes;
import com.unicom.mms.InitInstance;
import com.unicom.mms.constants.SharePublicContants;
import com.unicom.mms.gateway.MsgReport;
import com.unicom.mms.gateway.PostcardMMS;
import com.unicom.mms.mm7.SmilPostcardMMS;

public class PostcardMMSListener implements MessageListener{
	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Topic postcardmmsTopic;
	private Topic msgreportTopic;
	private MessageProducer msgReportProducer;
	private static final Logger log = Logger.getLogger(PostcardMMSListener.class);
	
	public ActiveMQConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public Topic getPostcardmmsTopic() {
		return postcardmmsTopic;
	}

	public void setPostcardmmsTopic(Topic postcardmmsTopic) {
		this.postcardmmsTopic = postcardmmsTopic;
	}

	public Topic getMsgreportTopic() {
		return msgreportTopic;
	}

	public void setMsgreportTopic(Topic msgreportTopic) {
		this.msgreportTopic = msgreportTopic;
	}

	@Override
	public void onMessage(Message message) {
		ObjectMessage obj = (ObjectMessage)message;
		try{
			InitInstance init = InitInstance.getInstance();
			CompositeConfiguration mm7 = init.mm7;
			PostcardMMS u = (PostcardMMS) obj.getObject();
			log.info("发送明信片:"+ u.toString());
			log.info("MsgType:"+u.getMsgType()+",Receiver:"+u.getReceiver()+",Content"+u.getContent());
			log.info("remoteurl:"+ mm7.getString("remoteurl"));
			log.info("localurl:"+ mm7.getString("localurl"));
	    	SmilPostcardMMS mms = new SmilPostcardMMS(u,mm7.getString("remoteurl"),mm7.getString("localurl"));
	    	MM7RSRes rsRes = mms.send();
	    	MsgReport report = new MsgReport();
	    	report.setMsgType(u.getMsgType());
	    	report.setReq(u);
	    	report.setReportType(SharePublicContants.MSGREPORT_GATEWAY);
	    	if (rsRes instanceof MM7SubmitRes) {
				MM7SubmitRes submitRes = (MM7SubmitRes) rsRes;
				report.setMessageID(submitRes.getMessageID());
		        report.setReciveTime(new Date());
		        report.setResult(submitRes.getStatusText());
		      //保存请求对象到mencached
		    	init.cacheClientImpl.set(submitRes.getMessageID(), u);
//				_log.info("**RightMessage:"+submitRes.toString());
		        log.info("***submitResp=version:" + submitRes.getMM7Version()
						+ ",seq:" + submitRes.getTransactionID() + ",msgID:"
						+ submitRes.getMessageID() + ",statusDetail:"
						+ submitRes.getStatusDetail() + ",statuscode:"
						+ submitRes.getStatusCode() + ",statusText="
						+ submitRes.getStatusText());
			} else {
				report.setReason(rsRes.getStatusText());
				report.setResult(""+rsRes.getStatusCode());
				log.info("WrongMessage!statuscode=" + rsRes.getStatusCode()
						+ "statusText=" + rsRes.getStatusText());
			}
	        sendReport(report);
	        log.info("返回网关报告:"+ u.toString());
	        
		}catch(Exception e){
			e.printStackTrace();
			MsgReport report = new MsgReport();
	        report.setReq(obj);
	        report.setReciveTime(new Date());
	        report.setReportType(SharePublicContants.MSGREPORT_GATEWAY);
	        report.setResult("Error");
	        report.setReason(e.getMessage());
	        sendReport(report);
		}
	}

	public void run() throws JMSException {
		if(connectionFactory!=null){
			log.info("connectionFactory is ok");
			
			connection = connectionFactory.createConnection();
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        MessageConsumer consumer = session.createConsumer(postcardmmsTopic);
	        consumer.setMessageListener(this);
	        connection.start();
	        
	        msgReportProducer = session.createProducer(msgreportTopic);
	        log.info("Waiting for messages...");
		}
	}
	
	public void sendReport(MsgReport report){
		try {
			msgReportProducer.send(session.createObjectMessage(report));
			log.info("发送报告:"+report.toString());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
