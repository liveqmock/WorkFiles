package com.unicom.mms.entity;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * 功能描述:彩信待发送队列实体类
 * 
 * @author chenliang 新增日期：2013-1-31
 * @since mms-cms-unicom
 */
@Entity
@SequenceGenerator(name="SEQ_SEND_MMS_QUEUE_ID",sequenceName="SEQ_SEND_MMS_QUEUE_ID",allocationSize=1)
@Table(name = "tb_send_mms_queue")
public class TbSendMmsQueue implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_SEND_MMS_QUEUE_ID")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private Integer id;

	/**
	 * 地址
	 */
	@Column(name = "pic_url", updatable = true, nullable = false, insertable = true, length = 200)
	private String picUrl;
	
	/**
	 * 标题
	 */
	@Column(name = "title", updatable = true, nullable = true, insertable = true, length = 200)
	private String title;
	
	/**
	 * 声音文件的URL
	 */
	@Column(name = "music_url", updatable = true, nullable = true, insertable = true, length = 200)
	private String musicUrl;
	
	/**
	 * 接收人号码
	 */
	@Column(name = "reciver", updatable = true, nullable = false, insertable = true, length = 150)
	private String reciver;
	
	/**
	 * 渠道 1、web， 2、cms， 3、其它，  默认为3
	 */
	@Column(name = "channel", updatable = true, nullable = false, insertable = true, length = 32)
	private Integer channel;
	
	/**
	 * 发起人 (用户发个用户，发起人即是用户的手机号码，群发，发起人即是sp)
	 */
	@Column(name = "sponsor", updatable = true, nullable = true, insertable = true, length = 150)
	private String sponsor;
	
	/**
	 * 文本内容
	 */
	@Column(name = "msg_body", updatable = true, nullable = true, insertable = true, length = 150)
	private String msgBody; 
	
	/**
	 * 发送时间
	 */
	@Column(name = "send_time", updatable = true, nullable = true, insertable = true)
	private Date sendTime;
	
	/**
	 * 待发送时间 (用于实时发送的时间判断)
	 */
	@Column(name = "to_be_send_time", updatable = true, nullable = true, insertable = true)
	private Date toBeSendTime;
	
	
	/**
	 * 发送状态(发送给网关的，网关返回的状态，1、成功，2、失败)
	 */
	@Column(name = "msg_status", updatable = true, nullable = true, insertable = true, length = 150)
	private String msgStatus;
	
	/**
	 * 发送报告(网关发送给用户返回的状态)
	 */
	@Column(name = "msg_report", updatable = true, nullable = true, insertable = true, length = 150)
	private String msgReport;
	
	
	/***
	 * 创建时间
	 */
	@Column(name = "create_time", updatable = true, nullable = false, insertable = true)
	private Date createTime;
	
	/**
	 * 发送等级(1、优先，2、其次，3、普通)
	 */
	@Column(name = "send_level", updatable = true, nullable = false, insertable = true, length = 32)
	private Integer sendLevel;
	
	/**
	 * 消息id(发送给网关的消息id)
	 */
	@Column(name = "transation_id", updatable = true, nullable = true, insertable = true, length = 200)
	private String transationId;
	
	/**
	 * 任务id
	 */
	@Column(name = "job_id", updatable = true, nullable = true, insertable = true, length = 32)
	private Integer jobId;
	
	/**
	 * 消息类型,见<code>SharePublicContants</code>的GATEWAY_MSGTYPE_xxx
	 */
	@Column(name = "msg_type", updatable = true, nullable = false, insertable = true, length = 32)
	private Integer msgType;
	
	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public Date getToBeSendTime() {
		return toBeSendTime;
	}

	public void setToBeSendTime(Date toBeSendTime) {
		this.toBeSendTime = toBeSendTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}


	public Integer getSendLevel() {
		return sendLevel;
	}

	public void setSendLevel(Integer sendLevel) {
		this.sendLevel = sendLevel;
	}

	public String getTransationId() {
		return transationId;
	}

	public void setTransationId(String transationId) {
		this.transationId = transationId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
	public String getMsgReport() {
		return msgReport;
	}

	public void setMsgReport(String msgReport) {
		this.msgReport = msgReport;
	}

	@Override
	public String toString() {
		return "TbSendMmsQueue [id=" + id + ", picUrl=" + picUrl + ", title="
				+ title + ", musicUrl=" + musicUrl + ", reciver=" + reciver
				+ ", channel=" + channel + ", sponsor=" + sponsor
				+ ", msgBody=" + msgBody + ", sendTime=" + sendTime
				+ ", msgStatus=" + msgStatus + ", msg_report=" + msgReport
				+ ", createTime=" + createTime + ", sendLevel=" + sendLevel
				+ ", transationId=" + transationId + ", jobId=" + jobId + "]";
	}

	public TbSendMmsQueue(Integer id, String picUrl, String title,
			String musicUrl, String reciver, Integer channel, String sponsor,
			String msgBody, Date sendTime, String msgStatus, String msgReport,
			Date createTime, Integer sendLevel, String transationId,
			Integer jobId) {
		super();
		this.id = id;
		this.picUrl = picUrl;
		this.title = title;
		this.musicUrl = musicUrl;
		this.reciver = reciver;
		this.channel = channel;
		this.sponsor = sponsor;
		this.msgBody = msgBody;
		this.sendTime = sendTime;
		this.msgStatus = msgStatus;
		this.msgReport = msgReport;
		this.createTime = createTime;
		this.sendLevel = sendLevel;
		this.transationId = transationId;
		this.jobId = jobId;
	}

	public TbSendMmsQueue() {
		super();
	}

}