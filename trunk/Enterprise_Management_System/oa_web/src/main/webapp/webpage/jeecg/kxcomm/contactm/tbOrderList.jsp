<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbOrderList"  fitColumns="true" title="销售订单" actionUrl="tbOrderController.do?datagrid" idField="id" fit="true" queryMode="group"  onClick="orderDetail"  >
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="康讯订单号" field="kxOrderNo" query="true"  ></t:dgCol>
   <t:dgCol title="项目名称" field="projectName" query="true"></t:dgCol>
   <t:dgCol title="合同编号" field="tbContract_contractNo" query="true"></t:dgCol>
   <t:dgCol title="客户名称" field="client" query="true"></t:dgCol>
   <t:dgCol title="最终客户" field="finalClient" ></t:dgCol>
   <t:dgCol title="付款方式" field="payment" ></t:dgCol>
   <t:dgCol title="项目负责人" field="principal" width="50" query="true"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd "></t:dgCol>
   <t:dgCol title="订单总价" field="totalPrice" width="50"></t:dgCol>
   <t:dgCol title="备注" field="remark" width="80"></t:dgCol>
    <t:dgCol title="采购状态" field="status" hidden="false" query="true"></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="tbOrderController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbOrderController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbOrderController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="删除" icon="icon-remove"  funname="delone"></t:dgToolBar>
  </t:datagrid>
 </div>
 
 	<div region="south"  style="height:200px;overflow: hidden;" split="true" border="false">
		<div class="easyui-panel" title="订单明细" style="padding:1px;" fit="true" border="false" id="orderDetailpanel">
  		</div>
	</div>
  

</div>
 
 
 <script type="text/javascript">
 function orderDetail(rowIndex, rowData)
 {
	 $('#orderDetailpanel').panel("refresh", "tbOrderController.do?orderDetail&id=" +rowData.id);
 }
 /**
 $(function() {
		$('#tbOrderList').datagrid({
			onClickRow:function(rowIndex, rowData){
				 $('#orderDetailpanel').panel("refresh", "tbOrderController.do?orderDetail&id=" +rowData.id);
			}
		});
		
		$('#tbOrderList').datagrid({
			onDblClickRow:function(rowIndex, rowData){
				openwindow('编辑','tbOrderController.do?addorupdate&id='+rowData.id +" onClick (rowIndex, rowData)",'tbOrderList');
			}
			});
	});**/
 
 function delone(title,url, id) {
		var rowData = $('#'+id).datagrid('getSelected');
		if (!rowData) {
			tip('请选择要删除');
			return;
		}
		var i = rowData.id;
		url += '&id='+rowData.id;
		//openwindow(title,'tbOrderController.do?del&id='+rowData.id);
		$.ajax({
	    	url:'tbOrderController.do?del' , // 可以获取数据的接口
	    	dataType:"json",
	    	data:{'id':rowData.id},
	    	success:function(data) {
				$.dialog.tips(data.msg,2);
	    	}
	    });
	}
</script>