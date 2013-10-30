package com.unicom.mms.mcp.activemq.work;

import org.apache.log4j.Logger;

import com.unicom.mms.constants.SharePublicContants;
import com.unicom.mms.gateway.MsgReport;
import com.unicom.mms.gateway.PostcardMMS;
import com.unicom.mms.mcp.activemq.InitSpringBean;
import com.unicom.mms.mcp.service.SendMmsRecsService;

/**
 * 
 * 处理发送明信片彩信的报告
 * 
 * @author zhangjh 新增日期：2013-9-27
 * @since mms-mcp
 */
public class PostcardMmsReport implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(PostcardMmsReport.class);	
	
	private MsgReport report;
	
	public MsgReport getReport() {
		return report;
	}
	public void setReport(MsgReport report) {
		this.report = report;
	}
	public PostcardMmsReport(MsgReport report ){
		this.report = report;
	}
	@Override
	public void run()  {
		LOGGER.info("SendMmsRecs begin.");
		try {
			SendMmsRecsService sendMmsRecsService = InitSpringBean.sendMmsRecsService;
			LOGGER.info(""+report.toString());
			if(SharePublicContants.MSGREPORT_USER == report.getReportType()){
				LOGGER.info("用户接收到状态报告,修改已发彩信表状态.");
				sendMmsRecsService.updateMmsRecsStates(report);
			}else if(SharePublicContants.MSGREPORT_GATEWAY == report.getReportType()){
				LOGGER.info("网关接收到状态报告,彩信已发送.");
				sendMmsRecsService.saveWork(report);
			}else{
				LOGGER.info("没有收到消息报告.");
			}
		} catch (Exception e) {
			LOGGER.error("实施收到彩信消息报告异常.",e);
		}
		LOGGER.info("SendMmsRecs Success.");
	}


}
