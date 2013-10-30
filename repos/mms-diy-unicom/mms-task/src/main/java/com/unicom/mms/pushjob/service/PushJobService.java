package com.unicom.mms.pushjob.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicom.mms.common.contant.BusinessUtil;
import com.unicom.mms.constants.SharePublicContants;
import com.unicom.mms.entity.TbPolic;
import com.unicom.mms.entity.TbSendMmsQueue;
import com.unicom.mms.entity.TbSendSmsQueue;
import com.unicom.mms.gateway.BatchSendMMS;
import com.unicom.mms.gateway.BatchSendSMS;
import com.unicom.mms.job.PushJob;
import com.unicom.mms.pushjob.dao.PushJobDAO;
import com.unicom.mms.pushjob.multhread.RunThread;

/**
 * 
* 功能描述:群发短信业务处理类
* 版权所有：康讯通讯
* 未经本公司许可，不得以任何方式复制或使用本程序任何部分
* @author chenliang 新增日期：2013-9-12
* @author chenliang 修改日期：2013-9-12
* @since mms-task
 */
@Service("pushJobService")
public class PushJobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PushJobService.class);
	
	@Autowired( required = true )
	private PushJobDAO pushJobDAO;
	@Autowired( required = true )
	private SendSmsQueueService sendSmsQueueService;
	@Autowired( required = true )
	private SendMmsQueueService sendMmsQueueService;
	
	public static void main(String[] args) {
		new PushJobService().work();
	}
	
	/**
	 * 
	* 方法用途和描述:群发短信
	* @author chenliang 新增日期：2013-9-12
	* @since mms-task
	 */
	public void work(){
//		RunThread runThread = RunThread.getInstance();
		//扫描任务未启动的任务列表信息
		List<TbPolic> policList = selectPolic();
		LOGGER.debug("job size="+policList.size());
		for (int i = 0; i < policList.size(); i++) {
			
			TbPolic model = policList.get(i);
			//把任务的状态更改为执行中
			updatePolicRunning(model);
			
			//根据任务策略获取该策略设置的推送号码
			if( BusinessUtil.TYPE_SMS == model.getMsgType() ){
				LOGGER.info("Run push sendSms job.");
				pushSendSMS(model.getId());
			}else if( BusinessUtil.TYPE_MMS == model.getMsgType() ){
				LOGGER.info("Run push sendMms job.");
				pushSendMMS(model.getId());
			}
			
//			LOGGER.info("开始群发任务-->任务名("+model.getPolicName()+")");
//			runThread.run(model);
		}
		
	}
	
	/**
	 * 
	* 方法用途和描述: 修改任务的状态为执行中
	* @author chenliang 新增日期：2013-9-30
	* @since mms-task
	 */
	private void updatePolicRunning(TbPolic model) {
		TbPolic polic = pushJobDAO.findById(model.getId());
		polic.setRunStauts(SharePublicContants.JOB_RUNNING);
		pushJobDAO.merge(polic);
	}

	/**
	 * 
	* 方法用途和描述: 查询任务
	* @return
	* @author chenliang 新增日期：2013-9-27
	* @since mms-task
	 */
	private List<TbPolic> selectPolic(){
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" select t from TbPolic t ");
		selectSql.append(" where t.startSendTime<=sysdate ");
		selectSql.append(" and t.endSendTime>=sysdate ");
		selectSql.append(" and t.stauts= "+SharePublicContants.JOB_STATUES_START);
		selectSql.append(" and t.runStauts = "+SharePublicContants.JOB_NOTRUN);
		List<TbPolic> lists = pushJobDAO.selectPolic(selectSql.toString());
		return lists;
	}
	

	/**
	 * 
	* 方法用途和描述: 根据任务id查询推送号码保存到短信待发送表
	* @param mmsType
	* @return
	* @author chenliang 新增日期：2013-9-27
	* @since mms-task
	 */
	private void pushSendSMS(int jobId){
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" select a.mdn as reciverMdn,c.content as sendContents ");
		selectSql.append(" from tb_push_mdn a,tb_mdn_type b,tb_polic c ");
		selectSql.append(" where a.mdn_type_id = b.id ");
		selectSql.append(" and b.id = c.mdn_type_id ");
		selectSql.append(" and c.id = ? ");
		List lists = pushJobDAO.selectPushMdn(selectSql.toString(), jobId);
		List<TbSendSmsQueue> sendSmsList = new ArrayList<TbSendSmsQueue>();
		Object[] object = new Object[lists.size()];
		for (int i = 0; i < lists.size(); i++) {
			object = (Object[]) lists.get(i);
			TbSendSmsQueue smsQueue = new TbSendSmsQueue();
			smsQueue.setSendTime(new Date());
			smsQueue.setMsgBody(""+object[1]);
			smsQueue.setReceiveMobile(""+object[0]);
			smsQueue.setCreateTime(new Date());
			smsQueue.setSendLevel(SharePublicContants.SENDLEVEL_SECONDLY);
			smsQueue.setChannel(SharePublicContants.CHANNEL_CMS);
			smsQueue.setJobId(jobId);
			sendSmsList.add(smsQueue);
		}
		LOGGER.info("*****pushSendSMS num="+sendSmsList.size()+"***********");
		//把推送短信保存到待发表中
		sendSmsQueueService.saveBatchSendSms(sendSmsList);
		//调用发送短信的接口
		manyToSendSms(sendSmsList);
	}
	
	/**
	 * 
	* 方法用途和描述: 调用短信接口，群发
	* @param smslist
	* @author chenliang 新增日期：2013-9-27
	* @since mms-task
	 */
	private void manyToSendSms(List<TbSendSmsQueue> smslist){
		for (int i = 0; i < smslist.size(); i++) {
			TbSendSmsQueue smsQueue = smslist.get(i);
			BatchSendSMS batchSms = new BatchSendSMS();
			batchSms.setChannel(""+smsQueue.getChannel());
			batchSms.setContent(smsQueue.getMsgBody());
			batchSms.setEndSendTime(new Date());
			batchSms.setReceiver(smsQueue.getReceiveMobile());
			batchSms.setSender(smsQueue.getSponsor());
			batchSms.setStartSendTime(new Date());
			//TODO 调用发送接口 
		}
	}

	/**
	 * 
	* 方法用途和描述: 根据任务id查询推送号码保存到彩信待发送表
	* @param jobId
	* @author chenliang 新增日期：2013-9-27
	* @since mms-task
	 */
	private void pushSendMMS(int jobId){
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" select a.mdn as reciverMdn,c.mms_url as mmsUrl,c.content as content ");
		selectSql.append(" from tb_push_mdn a,tb_mdn_type b,tb_polic c ");
		selectSql.append(" where a.mdn_type_id = b.id ");
		selectSql.append(" and b.id = c.mdn_type_id ");
		selectSql.append(" and c.id = ? ");
		List lists = pushJobDAO.selectPushMdn(selectSql.toString(), jobId);
		List<TbSendMmsQueue> sendMmsList = new ArrayList<TbSendMmsQueue>();
		Object[] object = new Object[lists.size()];
		for (int i = 0; i < lists.size(); i++) {
			object = (Object[]) lists.get(i);
			TbSendMmsQueue smsQueue = new TbSendMmsQueue();
			smsQueue.setPicUrl(""+object[1]);  //图片地址
			smsQueue.setReciver(""+object[0]); //发送号码
			smsQueue.setChannel(SharePublicContants.CHANNEL_CMS); //渠道
			smsQueue.setMsgBody(""+object[2]); //消息内容
			smsQueue.setSendTime(new Date()); //发送时间
			smsQueue.setCreateTime(new Date()); //创建时间
			smsQueue.setSendLevel(SharePublicContants.SENDLEVEL_SECONDLY); //发送等级
			smsQueue.setJobId(jobId); //任务id
			sendMmsList.add(smsQueue);
		}
		LOGGER.info("****PushSendMMS Num = "+sendMmsList.size()+"****");
		//把推送彩信保存到待发送表中
		sendMmsQueueService.saveBatchSendSms(sendMmsList);
		//调用发送短信的接口
		manyToSendMms(sendMmsList);
	}
	
	
	/**
	 * 
	* 方法用途和描述: 调用彩信接口，群发
	* @param smslist
	* @author chenliang 新增日期：2013-9-27
	* @since mms-task
	 */
	private void manyToSendMms(List<TbSendMmsQueue> mmslist){
		for (int i = 0; i < mmslist.size(); i++) {
			TbSendMmsQueue mmsQueue = mmslist.get(i);
			BatchSendMMS batchMms = new BatchSendMMS();
			batchMms.setChannel(""+mmsQueue.getChannel());
			batchMms.setContent(mmsQueue.getMsgBody());
			batchMms.setEndSendTime(new Date());
			batchMms.setImagePath(mmsQueue.getPicUrl());
			batchMms.setImageType(""+SharePublicContants.IMAGES_GIF);
			batchMms.setReceiver(mmsQueue.getReciver());
			batchMms.setSender(mmsQueue.getSponsor());
			batchMms.setStartSendTime(new Date());
			batchMms.setSubject(mmsQueue.getTitle());
			//TODO 调用发送接口 
		}
	}
	
}
