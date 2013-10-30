package cn.com.mmsweb.action.web;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.common.Response;
import cn.com.mmsweb.action.util.SessionUtils;
import cn.com.mmsweb.service.CommonService;
import cn.com.mmsweb.service.HotBillBoardService;
import cn.com.mmsweb.service.NewBillBoardService;
import cn.com.mmsweb.service.SendRecsService;
import cn.com.mmsweb.vo.SendRecsVo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unicom.mms.entity.TbCollect;
import com.unicom.mms.entity.TbHotBillboard;
import com.unicom.mms.entity.TbNewBillboard;
import com.unicom.mms.entity.TbSendedMmsRecs;
import com.unicom.mms.entity.TbTemplateCard;
import com.unicom.mms.entity.TbUsers;
import com.unicom.mms.mcp.webservice.Mcp;
import com.unicom.mms.mcp.webservice.McpServiceLocator;

public class SendRecsAction extends BaseAction<TbSendedMmsRecs, String>{
	private static Logger log = Logger.getLogger(SendRecsAction.class);
	@Autowired(required = true)
	private SendRecsService sendRecsService;
	@Autowired(required=true)
	private HotBillBoardService hotBillBoardService;
	@Autowired(required=true)
	private NewBillBoardService newBillBoardService;
	
	private int pageNow=1;
	private int pageSize=10;
	private String id;
	
	private List<SendRecsVo> listSendRecs;
	private List<TbHotBillboard> hotBillBoardList;
	private List<TbNewBillboard> newBillBoardList;
	
	private int maxPage;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<SendRecsVo> getListSendRecs() {
		return listSendRecs;
	}

	public void setListSendRecs(List<SendRecsVo> listSendRecs) {
		this.listSendRecs = listSendRecs;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}


	public SendRecsService getSendRecsService() {
		return sendRecsService;
	}

	public void setSendRecsService(SendRecsService sendRecsService) {
		this.sendRecsService = sendRecsService;
	}

    /*
     * 分页查询我的发送记录
     */
	 public String list()
	 {
		 HttpSession session = getHttpSession();
		 TbUsers users = (TbUsers) session.getAttribute(SessionUtils.USER);
		 listSendRecs=sendRecsService.queryByPage(pageSize, pageNow,users.getMdn());
		 maxPage=sendRecsService.maxPage(pageSize, pageNow,users.getMdn());
		 hotBillBoardList = hotBillBoardService.findAll();
		 newBillBoardList = newBillBoardService.findAll();
		 return SUCCESS;
	 }
	 /*
	     * 删除
	     */
	/* 
		public String deleteById()
		{
			try {
				List<TbSendedMmsRecs> list=new ArrayList<TbSendedMmsRecs>();
				TbSendedMmsRecs sendRecs=new TbSendedMmsRecs();
				Mcp mcp = new McpServiceLocator().getMcpPort();
				Gson gson = new Gson();
				String[] ids = id.split(",");
				for (String strId : ids) {
					if(null!=strId)
					{
						sendRecs=sendRecsService.getByPk(Integer.parseInt(strId));
						TbSendedMmsRecs sendRecs2=new TbSendedMmsRecs();
					sendRecs2.setId(sendRecs.getId());
					sendRecs2.setCreateTime(sendRecs.getCreateTime());
					sendRecs2.setMusicUrl(sendRecs.getMusicUrl());
					sendRecs2.setPicUrl(sendRecs.getPicUrl());
					sendRecs2.setReciver(sendRecs.getReciver());
					sendRecs2.setSendTime(sendRecs.getSendTime());
					sendRecs2.setSendType(sendRecs.getSendType());
					TbUsers users=new TbUsers();
					users.setId(sendRecs.getUsers().getId());
					sendRecs2.setUsers(users);
						list.add(sendRecs2);
					}
				}
				String jsonStr = gson.toJson(list);	
			try {
				//TODO 未实现功能
				  String res="";// mcp.send
				  Response response =  gson.fromJson(res,   
			                new TypeToken<Response>() {   
			            }.getType());  
				  if(response.getResultCode()==0)
				  {
					  return successInfo("ok");
				  }else {
					return successInfo("error");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
	*/
	public List<TbNewBillboard> getNewBillBoardList() {
		return newBillBoardList;
	}

	public void setNewBillBoardList(List<TbNewBillboard> newBillBoardList) {
		this.newBillBoardList = newBillBoardList;
	}

	public List<TbHotBillboard> getHotBillBoardList() {
		return hotBillBoardList;
	}

	public void setHotBillBoardList(List<TbHotBillboard> hotBillBoardList) {
		this.hotBillBoardList = hotBillBoardList;
	}
		
	@Override
	public CommonService getCommonService() {
		return sendRecsService;
	}

	@Override
	public TbSendedMmsRecs getModel() {
		if(null==this.model){
			this.model = new TbSendedMmsRecs();
		}
		return this.model;
	}

	@Override
	public void setModel(TbSendedMmsRecs model) {
		this.model = model;
	}

	@Override
	public String[] getIds() {
		return this.ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
