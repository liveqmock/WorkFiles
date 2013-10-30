<%@page import="cn.com.mmsweb.action.util.SessionUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/jsp/head.jsp" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>沃爱彩信</title>
<meta name="keywords" content="沃爱彩信" />
<meta name="description" content="沃爱彩信" />
<%-- <script type="text/javascript" src="controller/myContacts_controller.js"></script> --%>
<style >
body{text-align:center;width:100%;}  
</style>
<script type="text/javascript" src="<%= realPath%>sendTemplateCard.js"></script>

<script type="text/javascript">
$(function(){
	 //全选 
	   $("#checkAll").click(function(){
		   if ($(this).attr("checked")) { 
        $('[name=items]:checkbox').attr('checked', true);
		   }else //全不选 
			   {
			   $('[type=checkbox]:checkbox').attr('checked', false);
			   }
	   });
})

function deleteAll()
{
	var parm = "";
	flag = false;
	var de = document.getElementsByName("items");
	for(i = 0; i < de.length; i++){
	if(de[i].checked == true){
    parm +=de[i].id+",";
	flag = true;
	} 
	}
	if(flag == false){
		alert("至少选择一个待删除记录");
		return false;
		}
	deleteById(parm);
}

function deleteById(parm)
{
	if(confirm("确定删除?")) {
		jQuery.post(ctxPath+"/sendRecsdelete!deleteById.action",{//
			'id':parm,
		},
		function(data) {
		//	var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="ok"){
					alert("ok");
				}
			}
	},'json');
			}
}
</script>
</head>

<body>
	<div class="wrap">

	<!-- Header begin -->
	<jsp:include page="../top.jsp" />
	<!-- Header end -->
	
	<div class="menu">
		<ul>
			<li><a href="<%=path%>/weclome.jsp">模版明信片</a></li>
			<li><a href="<%= realPath%>diyPostcard/diyPostcard.jsp">DIY明信片</a></li>
			<li><a href="<%= realPath%>gifPostcard/gifPostcard.jsp">动态明信片</a></li>
		</ul>
		<i></i>
	</div>
	
	<!-- 登陆 begin -->
	<jsp:include page="../login.jsp" />
	<!-- 登陆 end -->
	
	<div class="mainbox">
		<div class="m-left">
			<div class="mblock-list">
            	<div class="mltitbar"><h3>我的发送记录</h3></div>
                <div class="tbl-list lfs">
                	<table cellspacing="0" cellpadding="0">
                    	<tr>
                        	<!-- <th class="w1"><input type="checkbox" name="" id="checkAll"/></th> -->
                          <!--   <th class="w2"><input type="button" onclick="deleteAll()" value="删除" /></th> -->
                            <th class="w2">图片</th>
                            <th class="w3">标题</th> 
                            <th class="w4">状态</th> 
                            <th class="w5">接收人</th>
                            <th class="w6">发送时间</th>
                           <!--  <th class="w7">操作</th> -->
                        </tr>
                          <s:iterator value="listSendRecs">
                        <tr>
                        	<%-- <td class="w1"><input type="checkbox" name="items" id="<s:property value="id"/>"/></td> --%>
                            <td class="w2"><img src="<%= realPath%><s:property value="picUrl"/>"/></td>
                            <td class="w3"><s:property value="title"/></td>
                             <td class="w4"><s:property value="struts"/></td>
                            <td class="w5"><s:property value="reciver"/></td>
                            <td class="w6"><s:property value="sendTime"/></td>
                            <%-- <td class="w7"><button type="button" title="发送" onClick="javascript:sendTemplate(<s:property value="id" />);">发 送</button><button onclick='deleteById(<s:property value="id"/>);' title="删除">删除</button></td> --%>
                        </tr>
                        </s:iterator>
                    </table>
                </div>
                  <div class="in-page">
                   <s:url id="url_home" value="sendrecs.action">
                     <s:param name="pageNow" value="1"></s:param>
                   </s:url>
                   <s:url id="url_pre" value="sendrecs.action">
                     <s:param name="pageNow" value="pageNow-1<=0?1:pageNow-1"></s:param>
                   </s:url>
                   <s:url id="url_next" value="sendrecs.action">
                     <s:param name="pageNow" value="pageNow+1>maxPage?maxPage:pageNow+1"></s:param>
                   </s:url> 
                    <s:url id="url_last" value="sendrecs.action">
                     <s:param name="pageNow" value="maxPage"></s:param>
                   </s:url> 
                   <s:a href="%{url_home}">首页</s:a>
                    <s:a href="%{url_pre}">上一页</s:a>
                  <s:a href="%{url_next}">下一页</s:a>
                   <s:a href="%{url_last}">末页</s:a>
                  </div>
        <!--         <div class="in-page"><a href="#" class="a1">上一页</a><a href="#">1</a><i>2</i><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#" class="a2" title="下一页">下一页</a></div> -->
            </div>
		</div>

		<!-- 最新推荐，最热推荐 begin -->
		<jsp:include page="../billboard.jsp" />
		<!-- 最新推荐，最热推荐 end -->
		
	</div>
</div>
<!-- FOOT begin-->
<jsp:include page="../foot.jsp" />
<!-- FOOT end-->

<!-- 发送模板明信片弹出框 begin -->
<jsp:include page="../sendBox.jsp" />
<!-- 发送模板明信片弹出框 end -->

<!--发送明信片弹窗 lightbox-->
<div id="sendBox1" style="display:none">
	<div class="lbox608">
		<i class="lbox-tbg png"></i>
		<div class="lbox-bg png">
			<form>
			<div class="lbox-wrap">
				<span class="btn-close png" title="关闭" onClick="javascript:sendBox1.Close();"></span>
				<span class="lbox-titbar">发送彩信</span>
				<span class="lbox-cons cons1">
					<span class="show-img1">
						<span class="yp1 png"></span>
						<img src="<%= realPath%>common/images/518x278.jpg" />
					</span>
					<span class="show-text1">
						<cite>留言：</cite>
						<textarea name="ly">也许岁月将往事褪色，或许空间将彼此隔离。但值得珍惜的依然是知心的友谊。想再次对你说声：2013，新年快乐！</textarea>
					</span>
					<span class="show-tips1">还可以输入60个字</span>
				</span>
				<span class="lbox-btns">
					<button class="btns-next" title="下一步"></button>
					<button class="btns-cancel" title="取消" onClick="javascript:sendBox1.Close();" type="button"></button>
				</span>
			</div>
			</form>
		</div>
		<i class="lbox-bbg png"></i>
	</div>
</div>
<!--发送明信片弹窗 lightbox end-->

<script type="text/javascript">
	var sendBox1 = new LightBox("sendBox1"); //发送明信片弹窗
	//sendBox1.Show();//默认显示出来
</script>
</body>
</html>
