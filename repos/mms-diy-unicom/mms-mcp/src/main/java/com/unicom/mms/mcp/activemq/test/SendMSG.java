package com.unicom.mms.mcp.activemq.test;

import com.unicom.mms.constants.SharePublicContants;
import com.unicom.mms.gateway.RealTimeSMS;
import com.unicom.mms.mcp.activemq.InitSpringBean;
import com.unicom.mms.mcp.activemq.MsgPublisher;

public class SendMSG {
	public static void main(String[] args){
		RealTimeSMS msg = new RealTimeSMS();
		msg.setChannel(""+SharePublicContants.CHANNEL_WEB);
		msg.setContent("张建华送个赵宝东的彩信祝福,嘻嘻");
		msg.setMsgSn("2013102810300215661");
		msg.setMsgType(SharePublicContants.GATEWAY_MSGTYPE_REALTIMESMS);
		msg.setReceiver("15580898198");
		msg.setSender("15580898198");
		
//		PostcardMMS cardmms = new PostcardMMS();
//		cardmms.setSender("15580898198"); //发起人
//		cardmms.setReceiver("15580898198"); //接收人
//		cardmms.setContent("张建华送个赵宝东的彩信祝福"); //内容
//		cardmms.setChannel(""+SharePublicContants.CHANNEL_WEB); //渠道
//		cardmms.setMsgType(SharePublicContants.GATEWAY_MSGTYPE_TEMPLATE_POSTCARDMMS); //消息类型
//		cardmms.setMsgSn("2013102810300215662");  //消息id
//		cardmms.setSubject("来自15580898198的祝福");
//		cardmms.setImagePath("http://211.91.224.244/resources/userData/2013-10/1382925839168.png"); //图片地址
//		cardmms.setMusicPath("http://211.91.224.244/resources/systemData/Music/cat.amr"); //音乐地址
//		cardmms.setProductCode("3174201001"); //产品id
		
		InitSpringBean initSpringBean = InitSpringBean.getSingleInstance();
		MsgPublisher msgPublisher = initSpringBean.getMsgPublisher();
		try {
			msgPublisher.send(msg);
			System.out.println("发送消息:"+msg.getSender());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
