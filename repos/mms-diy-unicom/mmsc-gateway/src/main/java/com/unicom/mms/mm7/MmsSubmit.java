package com.unicom.mms.mm7;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;

import com.cmcc.mm7.vasp.common.MMConstants;
import com.cmcc.mm7.vasp.common.MMContent;
import com.cmcc.mm7.vasp.conf.MM7Config;
import com.cmcc.mm7.vasp.message.MM7RSRes;
import com.cmcc.mm7.vasp.message.MM7SubmitReq;
import com.cmcc.mm7.vasp.service.MM7Sender;
import com.unicom.mms.InitInstance;

public class MmsSubmit {
	private  CompositeConfiguration conf = InitInstance.getInstance().mm7;
	private static Logger logger = Logger.getLogger(MmsSubmit.class);
	/**
	 * 
	 * 消息格式组织
	 * 
	 * @param phone 手机号码
	 * @param content 彩信内容
	 * @param subject 彩信主题
	 * @param vasId 产品代码
	 * @return
	 * @author zhangjh 新增日期：2013-9-23
	 * @since mmsc-gateway
	 */
	public MM7SubmitReq fromat(String[] phone,String subject,MMContent content,String vasId){
		MM7SubmitReq submit = new MM7SubmitReq();
		for(int i=0;i<phone.length;i++){
			submit.addTo(phone[i]);  // 消息接收方，对于多个接收方可多次调用addTo()
		}
		submit.setTransactionID(conf.getString("TransactionID"));
		submit.setVASID(vasId);
		submit.setVASPID(conf.getString("VASPID"));
		submit.setServiceCode(conf.getString("ServiceCode"));
		submit.setSenderAddress(conf.getString("SenderAddress"));
//		submit.setSubject(subjectText.getBytes("UTF-8").toString()); //utf8编码
		submit.setSubject(subject); //标题
		submit.setChargedPartyID(conf.getString("ChargedPartyID"));
		submit.setChargedParty(conf.getByte("ChargedParty", (byte)4));
		submit.setDeliveryReport(conf.getBoolean("DeliveryReport")); // 设置是否需要递送报告
		submit.setPriority(conf.getByte("Priority", (byte)2)); //设置消息的优先级 （0=最低优先级，1=正常，2=紧急）
		
		submit.setReadReply(conf.getString("ReadReply").equals("true")?true:false); // 设置是否需要阅读报告
		submit.setMessageClass(MMConstants.MessageClass.PERSONAL);//设置消息类型
		//submit.setReplyCharging(true);
		//submit.setReplyChargingSize(100);
		//submit.setReplyDeadline(sdf.parse("2005-03-23T15:35:00"));
		//submit.setEarliestDeliveryTime(sdf.parse("2005-03-21T21:35:00"));
		//submit.setExpiryDate(sdf.parse("2004-03-11T17:00:00"));
		//submit.setReplyDeadline(sdf.parse("2004-03-03T23:59:59"));
		submit.setContent(content);//内容
		return submit;
	}
	
		
	/**
	 * 
	 * DIY明信片
	 * 
	 * @return
	 * @author zhangjh 新增日期：2013-9-23
	 * @since mmsc-gateway
	 */
	public MMContent postcards(String smilPath,String smilFileName,String imageType,String imagePath,String imageFileName,String musicType,String musicPath,String musicFileName){
		MMContent content = new MMContent();
		content.setContentType(MMConstants.ContentType.MULTIPART_RELATED);
		content.setContentID(smilFileName);
		
		MMContent sub0 = MMContent.createFromFile(smilPath);
		sub0.setContentType(MMConstants.ContentType.SMIL);
		sub0.setContentID(smilFileName);
		content.setPresentionContent(sub0);
		content.addSubContent(sub0);
		
		MMContent sub2 =MMContent.createFromFile(imagePath);
		if(imageType.equals("JPEG")){
			sub2.setContentType(MMConstants.ContentType.JPEG);
		}else if(imageType.equals("GIF")){
			sub2.setContentType(MMConstants.ContentType.GIF);
		}else if(imageType.equals("PNG")){
			sub2.setContentType(MMConstants.ContentType.PNG);
		}
		sub2.setContentID(imageFileName);
		sub2.setContentLocation(imageFileName);
		content.addSubContent(sub2);
		
		MMContent sub3 = MMContent.createFromFile(musicPath);
		sub3.setContentID(musicFileName);
		if(musicType.equals("MIDI")){
			sub3.setContentType(MMConstants.ContentType.MIDI);
		}else if(musicType.equals("AMR")){
			sub3.setContentType(MMConstants.ContentType.AMR);
		}
		sub3.setContentLocation(musicFileName);
		content.addSubContent(sub3);
		
		return content;
	}
	
	/**
	 * 
	 * 发送彩信文字
	 * 
	 * @param text
	 * @return
	 * @author zhangjh 新增日期：2013-9-23
	 * @since mmsc-gateway
	 */
	public MMContent Text(String text){
		try{
			MMContent content = new MMContent();
			content.setContentType(MMConstants.ContentType.MULTIPART_RELATED);
			content.setContentID("text-msg");
			InputStream input = new ByteArrayInputStream(text.getBytes("GBK"));
			MMContent sub1 = MMContent.createFromStream(input);
			sub1.setContentType(MMConstants.ContentType.TEXT);
			sub1.setContentID("text-msg");
			sub1.setCharset("GBK");
			content.addSubContent(sub1);
			return content;
		}catch(Exception e){
			e.printStackTrace();
		}		
		return null;
	}
	
	public boolean send(MM7SubmitReq submit){
		try{
			MM7Config mm7Config = new MM7Config(MmsSubmit.class.getResource("/mm7Config.xml").getFile());
			mm7Config.setConnConfigName(MmsSubmit.class.getResource("/ConnConfig.xml").getFile());
			MM7Sender mm7Sender = new MM7Sender(mm7Config);
			logger.info("发送消息:"+submit.toString());
			MM7RSRes rsRes = (MM7RSRes)mm7Sender.send(submit);
			if(rsRes!=null){
				logger.info("网关回复:"+rsRes.toString());
				logger.info("网关回复:"+rsRes.getTransactionID());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 
	 * 发送彩信文本
	 * 
	 * @param phone
	 * @param text
	 * @return
	 * @author zhangjh 新增日期：2013-9-23
	 * @since mmsc-gateway
	 */
	public boolean sendText(String[] phone,String subject,String text,String vasId){
		final MM7Config mm7Config = new MM7Config(MmsSubmit.class.getResource("/mm7Config.xml").getFile());
		mm7Config.setConnConfigName(MmsSubmit.class.getResource("/ConnConfig.xml").getFile());
		System.out.println("MMSCIP:"+mm7Config.getMMSCIP());
		try{
			MM7SubmitReq submit = new MM7SubmitReq();
			for(int i=0;i<phone.length;i++){
				submit.addTo(phone[i]);  // 消息接收方，对于多个接收方可多次调用addTo()
			}
			submit.setVASID(vasId);
			submit.setVASPID(conf.getString("VASPID"));
			submit.setServiceCode(conf.getString("ServiceCode"));
			submit.setSenderAddress(conf.getString("SenderAddress"));
//			submit.setSubject(subjectText.getBytes("UTF-8").toString()); //utf8编码
			submit.setSubject(subject); //标题
			submit.setChargedPartyID(conf.getString("ChargedPartyID"));
			submit.setChargedParty(conf.getByte("ChargedParty", (byte)4));
			submit.setDeliveryReport(conf.getBoolean("DeliveryReport")); // 设置是否需要递送报告
			submit.setPriority(conf.getByte("Priority", (byte)2)); //设置消息的优先级 （0=最低优先级，1=正常，2=紧急）
			
			submit.setReadReply(conf.getString("ReadReply").equals("true")?true:false); // 设置是否需要阅读报告
			submit.setMessageClass(MMConstants.MessageClass.PERSONAL);//设置消息类型
			//submit.setReplyCharging(true);
			//submit.setReplyChargingSize(100);
			//submit.setReplyDeadline(sdf.parse("2005-03-23T15:35:00"));
			//submit.setEarliestDeliveryTime(sdf.parse("2005-03-21T21:35:00"));
			//submit.setExpiryDate(sdf.parse("2004-03-11T17:00:00"));
			//submit.setReplyDeadline(sdf.parse("2004-03-03T23:59:59"));
			MMContent content = new MMContent();
			content.setContentType(MMConstants.ContentType.TEXT);
			content.setContentID("text-msg");
			InputStream input = new ByteArrayInputStream(text.getBytes("GBK"));
			MMContent sub1 = MMContent.createFromStream(input);
			sub1.setContentType(MMConstants.ContentType.TEXT);
			sub1.setContentID("text-msg");
			sub1.setCharset("GBK");
			content.addSubContent(sub1);
			submit.setContent(content);//内容
			
			MM7Sender mm7Sender = new MM7Sender(mm7Config);
			submit.setTransactionID("1111111111");
			logger.info(submit.toString());
			MM7RSRes rsRes = (MM7RSRes)mm7Sender.send(submit);
			if(rsRes!=null){
				logger.info("网关回复:"+rsRes.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
