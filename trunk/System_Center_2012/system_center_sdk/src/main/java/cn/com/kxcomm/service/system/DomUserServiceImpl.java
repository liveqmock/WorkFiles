package cn.com.kxcomm.systemcenter.domUser.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import cn.com.kxcomm.entity.DomUserEntity;
import cn.com.kxcomm.interfaces.ExecuteService;
import cn.com.kxcomm.interfaces.system.DomUserService;

/**
 * 
* 功能描述:域用户业务处理实现类
* @author chenliang 新增日期：2013-6-24
* @since system_center_sdk
 */
public class DomUserServiceImpl implements DomUserService {

	private static final Logger LOGGER = Logger.getLogger(DomUserServiceImpl.class);
	
	private static DomUserServiceImpl domUserServiceImpl;
	private ExecuteService execute = CommonServiceImpl.getInstance();
	
	/**
	 * 
	* 方法用途和描述: 单例
	* @return
	* @author chenliang 新增日期：2013-6-24
	* @since system_center_sdk
	 */
	public static DomUserServiceImpl getInstance(){
		if(null == domUserServiceImpl){
			domUserServiceImpl = new DomUserServiceImpl();
		}
		return domUserServiceImpl;
	}

	@Override
	public List<DomUserEntity> listDomUser() {
		//获取查询所有的域用户的脚本
//		execute.execute(powerShell);
		return null;
	}

	@Override
	public boolean addDomUser(DomUserEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDomUser(DomUserEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDomUser(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
