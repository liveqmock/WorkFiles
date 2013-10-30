package cn.com.kxcomm.selfservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.kxcomm.base.dao.CommonDAO;
import cn.com.kxcomm.common.BlankUtil;
import cn.com.kxcomm.common.Page;
import cn.com.kxcomm.selfservice.dao.RightDAO;
import cn.com.kxcomm.selfservice.entity.TbRight;
import cn.com.kxcomm.selfservice.service.RightService;
import cn.com.kxcomm.selfservice.vo.MenuEntity;
import cn.com.kxcomm.selfservice.vo.RightEntity;

/**
 * 
* 功能描述:
 * 权限业务逻辑处理类
* @author chenliang 新增日期：2013-1-14
* @since mms-cms-unicom
 */
@Service("rightService")
public class RightServiceImpl extends CommonServiceImpl<TbRight> implements RightService {
	
	public static Logger log = Logger.getLogger(RightServiceImpl.class);
	
	@Autowired(required = true)
	private RightDAO rightDAO;
	
	@Override
	public CommonDAO<TbRight> getBindDao() {
		return rightDAO;
	}
	
	/**
	 * 查找菜单
	 */
	@Override
	public List<MenuEntity> queryMenu() {
		List<MenuEntity> menuList = new ArrayList<MenuEntity>();
		List<TbRight> rightList = rightDAO.findByProperty(TbRight.class, "rightLevel", 1);
		log.debug("第一级菜单："+rightList.size());
		for (TbRight tbRight : rightList) {
			MenuEntity menuEntity = new MenuEntity();
			menuEntity.setMenuId(Integer.parseInt(tbRight.getId().toString()));
			menuEntity.setLevel(Short.parseShort(tbRight.getRightLevel().toString()));
			menuEntity.setMenuName(tbRight.getRightName());
			menuEntity.setUrl(tbRight.getUrl());
			ArrayList<RightEntity> rightEntityList = new ArrayList<RightEntity>();
			String hql = "from TbRight tr where tr.rightLevel=2 and tr.parid=?";
			List<TbRight> list = rightDAO.find(hql, menuEntity.getMenuId());
			log.debug("第二级菜单："+list.size());
			for (int i = 0; i < list.size(); i++) {
				TbRight right = list.get(i);
				RightEntity entity = new RightEntity();
				entity.setRightId(Integer.parseInt(right.getId().toString()));
				entity.setLevel(Short.parseShort(right.getRightLevel().toString()));
				entity.setParentId(right.getParid());
				entity.setRightName(right.getRightName());
				entity.setUrl(right.getUrl());
				rightEntityList.add(entity);
			}
			menuEntity.setList(rightEntityList);
			menuList.add(menuEntity);
		}
		return menuList;
	}

	/**
	 * 重写分页方法
	 */
	@Override
	public Page<TbRight> findByPage(TbRight entity, Page<TbRight> pageInfo) {
		StringBuffer hql = new StringBuffer(" from TbRight tr where 1=1 ");
		if(!BlankUtil.isBlank(entity)){
			if(!BlankUtil.isBlank(entity.getRightName())){
				hql.append(" tr.rightName =? ");
			}
			return rightDAO.findByPage(pageInfo, hql.toString(), entity.getRightName());
		}else{
			return rightDAO.findByPage(pageInfo, hql.toString());
		}
	}


}
