package jeecg.kxcomm.entity.contactm;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

import jeecg.system.pojo.base.TSUser;

/**   
 * @Title: Entity
 * @Description: 报价表
 * @author zhangdaihao
 * @date 2013-10-23 09:56:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_quotations", schema = "")
@SuppressWarnings("serial")
public class TbQuotationsEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**createTime*/
	private java.util.Date createTime;
	/**downUrl*/
	private java.lang.String downUrl;
	/**quotationType*/
	private java.lang.String quotationType;
	/**status*/
	private java.lang.String status;
	/**title*/
	private java.lang.String title;
	/**projectId*/
	private java.lang.String projectId;
	/**creatorId*/
	private TSUser creatorId;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=255)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createTime
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createTime
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  downUrl
	 */
	@Column(name ="DOWN_URL",nullable=true,length=200)
	public java.lang.String getDownUrl(){
		return this.downUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  downUrl
	 */
	public void setDownUrl(java.lang.String downUrl){
		this.downUrl = downUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  quotationType
	 */
	@Column(name ="QUOTATION_TYPE",nullable=true,length=255)
	public java.lang.String getQuotationType(){
		return this.quotationType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  quotationType
	 */
	public void setQuotationType(java.lang.String quotationType){
		this.quotationType = quotationType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  status
	 */
	@Column(name ="STATUS",nullable=true,length=255)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  status
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  title
	 */
	@Column(name ="TITLE",nullable=true,length=100)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  title
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  projectId
	 */
	@Column(name ="PROJECT_ID",nullable=true,length=255)
	public java.lang.String getProjectId(){
		return this.projectId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  projectId
	 */
	public void setProjectId(java.lang.String projectId){
		this.projectId = projectId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  creatorId
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CREATOR_ID")
	public TSUser getCreatorId(){
		return this.creatorId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  creatorId
	 */
	public void setCreatorId(TSUser creatorId){
		this.creatorId = creatorId;
	}
	
}
