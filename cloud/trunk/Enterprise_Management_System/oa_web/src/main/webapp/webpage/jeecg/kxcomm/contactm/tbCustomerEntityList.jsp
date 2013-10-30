<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbCustomerEntityList" title="客户" actionUrl="tbCustomerEntityController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
    <t:dgCol title="公司名称" field="companyName" ></t:dgCol>
   <t:dgCol title="地址" field="address" ></t:dgCol>
    <t:dgCol title="联系人" field="contact" ></t:dgCol>
   <t:dgCol title="邮箱" field="email" ></t:dgCol>
   <t:dgCol title="电话" field="phone" ></t:dgCol>
    <t:dgCol title="省份" field="jobPlaceId_jobPlace" ></t:dgCol>
   <t:dgCol title="描述" field="description" ></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="tbCustomerEntityController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbCustomerEntityController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbCustomerEntityController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="删除" icon="icon-remove" url="tbCustomerEntityController.do?del" funname="delone"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <script type="text/javascript">
//删除
  function delone(title,url, id) {
  	var rowData = $('#'+id).datagrid('getSelected');
  	if (!rowData) {
  		tip('请选择要删除');
  		return;
  	}
  	$.dialog.confirm('确定删除吗', function(){
  		var i = rowData.id;
  		url += '&id='+rowData.id;
  		//直接操作
  		$.ajax({
  	    	url:'tbCustomerEntityController.do?del' , // 可以获取数据的接口
  	    	dataType:"json",
  	    	data:{'id':rowData.id},
  	    	success:function(data) {
  				$.dialog.tips(data.msg,2);
  				reloadTable();
  	    	}
  	    });
  	}, function(){
  	});
  }
  </script>