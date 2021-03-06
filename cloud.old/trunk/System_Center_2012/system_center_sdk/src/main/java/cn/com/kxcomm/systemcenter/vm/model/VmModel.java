package cn.com.kxcomm.systemcenter.vm.model;

/**
 * 
* 功能描述:虚拟机实体
* 版权所有：康讯通讯
* 未经本公司许可，不得以任何方式复制或使用本程序任何部分
* @author chenliang 新增日期：2013-10-12
* @author chenliang 修改日期：2013-10-12
* @since system_center_sdk
 */
public class VmModel {
	
	/**
	 * 虚拟机名
	 */
	public String name;

	/**
	 * 拥有者
	 */
	public String owner;
	
	/**
	 * 描述
	 */
	public String description;
	
	/**
	 * 主机名
	 */
	public String hostName;
	
	/**
	 * 操作系统
	 */
	public String operatingSystem;
	
	/**
	 * cpu个数
	 */
	public String cpuCount;
	
	/**
	 * 内存大小，单位MB
	 */
	public String memory;
	
	/**
	 * 添加时间
	 */
	public String addedTime;
	
	/**
	 * 修改时间
	 */
	public String modifiedTime;
	
	/**
	 * 总大小
	 */
	public String totalSize;
	
	/**
	 * 状态
	 */
	public String status;
	
	/**
	 * 虚拟机存放目录
	 */
	public String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getCpuCount() {
		return cpuCount;
	}

	public void setCpuCount(String cpuCount) {
		this.cpuCount = cpuCount;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(String addedTime) {
		this.addedTime = addedTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "VmModel [name=" + name + ", owner=" + owner + ", description="
				+ description + ", hostName=" + hostName + ", operatingSystem="
				+ operatingSystem + ", cPUCount=" + cpuCount + ", memory="
				+ memory + ", addedTime=" + addedTime + ", modifiedTime="
				+ modifiedTime + ", totalSize=" + totalSize + ", status="
				+ status + ", location=" + location + "]";
	}
	
}
