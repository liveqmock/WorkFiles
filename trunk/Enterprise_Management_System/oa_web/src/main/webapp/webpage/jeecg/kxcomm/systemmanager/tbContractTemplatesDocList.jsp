<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbContractTemplatesDocList" title="合同模板文件管理" actionUrl="tbContractTemplatesDocController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="templatesdocId" field="templatesdocId" hidden="false"></t:dgCol>
   <t:dgCol title="文件名称" field="docname" ></t:dgCol>
    <t:dgCol title="文件类型" field="docType_typename" ></t:dgCol>
   <t:dgCol title="路径" field="path" hidden="false"></t:dgCol>
   <t:dgCol title="创建时间" field="createtime" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="是否设置变量" field="bvariable" replace="否_0,是_1"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
    <t:dgFunOpt funname="aa(path)" title="下载附件"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="tbContractTemplatesDocController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbContractTemplatesDocController.do?addorupdate" funname="openuploadwin"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbContractTemplatesDocController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbContractTemplatesDocController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
  <script type="text/javascript">
function aa(download){
	var url ='${basePath}'+'upload/'+download;
	window.open(url, "_blank");
}
</script>