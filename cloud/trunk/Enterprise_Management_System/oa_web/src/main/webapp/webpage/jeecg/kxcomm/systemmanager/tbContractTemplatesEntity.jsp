<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>合同模板</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbContractTemplatesEntityController.do?save">
			<input id="id" name="id" type="hidden" value="${tbContractTemplatesEntityPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
<!-- 				<tr> -->
<!-- 					<td align="right"> -->
<!-- 						<label class="Validform_label"> -->
<!-- 							创建时间: -->
<!-- 						</label> -->
<!-- 					</td> -->
<!-- 					<td class="value"> -->
<!-- 						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createTime" name="createTime"  -->
<%-- 							     value="<fmt:formatDate value='${tbContractTemplatesEntityPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*"> --%>
<!-- 						<span class="Validform_checktip"></span> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td align="right">
						<label class="Validform_label">
							模板名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${tbContractTemplatesEntityPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr style="display: none;">
					<td align="right">
						<label class="Validform_label">
							状   态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="status" name="status" 
							   value="${tbContractTemplatesEntityPage.status}" >
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>