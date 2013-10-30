package jeecg.kxcomm.vo.contactm;

public class TbQuotationsVo {
  private String id;
  private String nowPrice;  //折扣后现场价
  private String afterPrice; //折扣后价格
  private String catalogTotalPrice; //目录合价
  private String totalPrice;  //合计
  private String quotationName;//报价表名称
  private String createTime; //创建时间
  private String downUrl;
  private String quotationType;
  private String status;
  private String projectId;
  private String creatorId;
  
	public String getDownUrl() {
	return downUrl;
	}
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
	public String getQuotationType() {
		return quotationType;
	}
	public void setQuotationType(String quotationType) {
		this.quotationType = quotationType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getId() {
			return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getAfterPrice() {
		return afterPrice;
	}
	public void setAfterPrice(String afterPrice) {
		this.afterPrice = afterPrice;
	}
	
	public String getCatalogTotalPrice() {
		return catalogTotalPrice;
	}
	public void setCatalogTotalPrice(String catalogTotalPrice) {
		this.catalogTotalPrice = catalogTotalPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getQuotationName() {
		return quotationName;
	}
	public void setQuotationName(String quotationName) {
		this.quotationName = quotationName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	  
  
}
