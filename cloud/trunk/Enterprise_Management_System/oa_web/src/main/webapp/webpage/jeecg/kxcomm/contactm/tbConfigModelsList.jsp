<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  
  <t:datagrid name="tbConfigModelsList" title="机型配置" actionUrl="tbConfigModelsController.do?datagrid&quotation=${quotation }" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
    <t:dgCol title="配置单名称" field="name" ></t:dgCol>
    <t:dgCol title="分类" field="tbDataSource_tbDataSourceType_sourcetypename" ></t:dgCol>
   <t:dgCol title="目录合价" field="catalogTotalPrice" ></t:dgCol>
   <t:dgCol title="折扣后价格" field="afterDiscountPrice" ></t:dgCol>
   <t:dgCol title="折扣后现场价" field="afterDiscountNowPrice" ></t:dgCol>
   <t:dgCol title="合计" field="totalPrice" ></t:dgCol>
   
   
   
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbConfigModelsController.do?del&id={id}" /> --%>
   <t:dgToolBar title="新增配置" icon="icon-add" url="tbConfigModelsController.do?addorupdate&qid=${quotation}" funname="add1"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbConfigModelsController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看配置单数据" icon="icon-search" url="tbConfigModelsController.do?check" funname="check"></t:dgToolBar>
   <t:dgToolBar title="返回" icon="icon-back" url="tbQuotationsController.do?tbQuotations" funname="back"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 function add1(title,addurl,gname) {
		gridname=gname;
		createwindow1(title, addurl);
}

 function createwindow1(title, addurl) {
		if(typeof(windowapi) == 'undefined'){
			$.dialog({
				content: 'url:'+addurl,
				lock : true,
				title:title,
				opacity : 0.3,
				cache:false,
				
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
					saveObj();
					var timer_alert = setTimeout(function() {	
						$('#quotations').panel("refresh", "tbConfigModelDataController.do?changePageById");
					}, 2000);
					
					return false;
			    },
			    cancelVal: '关闭',
			    cancel: true
			   
			});
		}else{
			W.$.dialog({
				content: 'url:'+addurl,
				lock : true,
				parent:windowapi,
				title:title,
				opacity : 0.3,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
					saveObj();
					var timer_alert = setTimeout(function() {	
						$('#quotations').panel("refresh", "tbConfigModelDataController.do?changePageById");
					}, 2000);
					return false;
			    },
			    cancelVal: '关闭',
			    cancel: true 
			});
		}
		
	}
 function check(title,url, id){
	 var rowData = $('#'+id).datagrid('getSelected');
		if (!rowData) {
			tip('请选择查看项目');
			return;
		}
	url += '&id='+rowData.id;
	$('#quotations').panel("refresh", url);
 }
 function back(title,url, id){
	 
	$('#quotations').panel("refresh", url);
 }
</script>