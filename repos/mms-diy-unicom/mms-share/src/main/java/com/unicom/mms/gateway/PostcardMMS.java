package com.unicom.mms.gateway;

/**
 * 
 * 明信片彩信
 * 
 * @author zhangjh 新增日期：2013-9-25
 * @since mms-share
 */
public class PostcardMMS extends BaseMessage {
	private static final long serialVersionUID = 4328530466936348579L;

	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 图片路径,jpg
	 */
	private String imagePath;
	/**
	 * 音乐路径,amr
	 */
	private String musicPath;
	/**
	 * 产品代码
	 */
	private String productCode;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "PostcardMMS [subject=" + subject + ", imagePath=" + imagePath
				+ ", musicPath=" + musicPath + ", productCode=" + productCode
				+ "]";
	}
}
