package cn.com.kxcomm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
 * 功能描述:订单表
 * 版权所有：康讯通讯 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author chenliang 新增日期：2013-9-11
 * @author chenliang 修改日期：2013-9-11
 * @since system_center_share
 */
@Entity
@SequenceGenerator(name="SEQ_ORDERS_ID",sequenceName="SEQ_ORDERS_ID",allocationSize=1)
@Table(name = "tb_orders")
public class OrdersEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_ORDERS_ID")
	@Column(name = "id", updatable = false, nullable = false, insertable = false, length = 32)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tenants_id", nullable = false)
	private TenantsEntity tbTenantsId;  //租户
	
	@Column(name = "order_num", updatable = true, nullable = false, insertable = true, length = 150)
	private String orderNum; //订单号
	
	@Column(name = "create_time", updatable = true, nullable = false, insertable = true)
	private Date createTime; //创建时间
	
	@Column(name = "state", updatable = true, nullable = false, insertable = true, length = 32)
    private Integer state; //订单状态
    
	@Column(name = "type", updatable = true, nullable = false, insertable = true, length = 32)
	private Integer type; //类型
	
	@Column(name = "product_name", updatable = true, nullable = false, insertable = true, length = 150)
    private String productName; //产品名
	
	@OneToMany(mappedBy = "ordersId")
	@Cascade({ CascadeType.ALL })
	private Set<OrderDetailEntity> orderDetailEntity;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TenantsEntity getTbTenantsId() {
		return tbTenantsId;
	}

	public void setTbTenantsId(TenantsEntity tbTenantsId) {
		this.tbTenantsId = tbTenantsId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "OrdersEntity [id=" + id + ", tbTenantsId=" + tbTenantsId
				+ ", orderNum=" + orderNum + ", createTime=" + createTime
				+ ", state=" + state + ", type=" + type + ", productName="
				+ productName + "]";
	}
    
    
}
