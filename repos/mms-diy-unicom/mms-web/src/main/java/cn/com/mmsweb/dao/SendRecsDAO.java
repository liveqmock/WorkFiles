package cn.com.mmsweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.com.common.Page;

import com.unicom.mms.entity.TbSendMmsQueue;
import com.unicom.mms.entity.TbSendedMmsRecs;
import com.unicom.mms.entity.TbSendedSmsRecs;

/**
*
* 功能描述:我的发送记录类
* @author lujia 新增日期：2013-4-17
* @since mms-web-unicom
*/
public class SendRecsDAO extends CommonDAO<TbSendMmsQueue>{
	private static Logger log = Logger.getLogger(SendRecsDAO.class);
	
	  public List<TbSendMmsQueue> queryByPage (int pageSize, int pageNow,String sponsor) {
		   List<TbSendMmsQueue> list = new ArrayList<TbSendMmsQueue>();
		   try {
			   //最大页数
			   int maxPage=this.maxPage(pageSize, pageNow,sponsor);
			   //判断当前页，最小一页不能小于1
			   if(pageNow<=0){
				   pageNow = 1;
			   }
			   //判断当前页，最大一页不能大于总数/页大小
			   if(pageNow>maxPage){
				   pageNow = maxPage;
			   }
			   String hql=" from TbSendMmsQueue c where c.channel=1 and c.sponsor="+sponsor+" order by c.id ";//limit "+(pageNow*pageSize-pageSize)+","+pageSize;
			   Page<TbSendMmsQueue> page = new Page<TbSendMmsQueue>();
			   page.setPageSize(pageSize);
			   page.setPageNo(pageNow);
		       Page<TbSendMmsQueue> pagelist=this.findByPage(page, hql);
		       list = pagelist.getResult();
	/*	       for(TbSendedMmsRecs sendRecs:list)
		       {
		    	   SendRecsVo sendRecsVo=new SendRecsVo();
		    	   sendRecsVo.setId(""+sendRecs.getId());
		    	   sendRecsVo.setMusicUrl(sendRecs.getMusicUrl());
		    	   sendRecsVo.setPicUrl(sendRecs.getPicUrl());
		    	   sendRecsVo.setReciver(sendRecs.getReciver());
		    	   sendRecsVo.setTitle(sendRecs.getTitle());
		    	   sendRecsVo.setUserName(sendRecs.getSponsor());
		    	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");  
		    	   sendRecsVo.setSendTime(sdf.format(sendRecs.getSendTime()));
		    	   list2.add(sendRecsVo);
		       }*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return list;
		  }
	  
	  //总页数
	  public int maxPage(int pageSize, int pageNow,String sponsor)
	  {
		  int totalount1 = 0;
		   String countHql = "select count(*) from TbSendMmsQueue c where c.channel=1 and  c.sponsor="+sponsor;
		   totalount1 = this.findTotalCount(countHql);
		   int totalount2 = 0;
		   String countHql1 = "select count(*) from TbSendedMmsRecs c where c.channel=1 and  c.sponsor="+sponsor;
		   totalount2 = findTotalCount(countHql1);
		   int totalount=totalount1+totalount2;
		   int maxPage = 1;
		   maxPage = totalount%pageSize>0?totalount/pageSize+1:totalount/pageSize;
			return maxPage; 
	  }
}
