package cn.com.mmsweb.service;


import java.util.List;

import cn.com.mmsweb.vo.CardVo;
import cn.com.mmsweb.vo.PhotoFrameVo;

import com.unicom.mms.entity.TbPhotoFrame;

public interface PhotoFrameService extends CommonService<TbPhotoFrame> {

	/**
	 * 
	* 方法用途和描述:查询页面需要展示的图片
	* @return
	* @author lizl 新增日期：2013-4-25
	* @since mms-web
	 */
	public List<PhotoFrameVo> queryPhotoFramePage(int pageSize, int pageNow,int pictype,int whatpic);
	
	/**
	 * 
	* 方法用途和描述:获取最大页数
	* @return
	* @author lizl 新增日期：2013-4-25
	* @since mms-web
	 */
	 public int maxPage(int pageSize, int pageNow,int pictype,int whatpic);

	/**
	 * 获得所有相框类型.
	 *
	 * @param pageSize
	 * @param pageNow
	 * @param picType
	 * @return
	 */
	public List<PhotoFrameVo> queryFramePhotosType();
	
}
