package com.unicom.mms.mcp.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unicom.mms.mcp.service.SecurityAddressService;
import com.unicom.mms.mcp.service.SendMmsRecsService;
import com.unicom.mms.mcp.service.SendSmsQueueService;
import com.unicom.mms.mcp.service.SendSmsRecsService;

/**
 * 
* 初始化Spring Bean
* @author zhangjh 新增日期：2013-9-27
* @since mms-mcp
 */
public class InitSpringBean {
	private static InitSpringBean instance = null;
	private MsgPublisher msgPublisher;
	private SecurityAddressService securityAddressService=null;
	public static SendSmsRecsService sendSmsRecsService = null;
	public static SendMmsRecsService sendMmsRecsService = null;
	public MsgPublisher getMsgPublisher() {
		return msgPublisher;
	}

	public void setMsgPublisher(MsgPublisher msgPublisher) {
		this.msgPublisher = msgPublisher;
	}

	private InitSpringBean(){	
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		if(context!=null){
			System.out.println("success load spring");
		}
		msgPublisher= (MsgPublisher) context.getBean("msgPublisher");
		try {
			msgPublisher.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		securityAddressService= (SecurityAddressService) context.getBean("securityAddressService");
		sendSmsRecsService = (SendSmsRecsService) context.getBean("sendSmsRecsService");
		sendMmsRecsService = (SendMmsRecsService) context.getBean("sendMmsRecsService");
	}
	
	public void run(){
		//监听发送消息的报告
				try {
					msgPublisher.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	public static InitSpringBean getSingleInstance(){
		if(instance==null)
			instance = new InitSpringBean();
		return instance;
	}

	public SecurityAddressService getSecurityAddressService() {
		return securityAddressService;
	}
}
