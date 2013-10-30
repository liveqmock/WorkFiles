<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>合同模板文件</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //上传完关闭窗口
  function openrcon() {
	  frameElement.api.opener.tip('上传完毕');
	  frameElement.api.opener.reloadTable();
	  frameElement.api.close();
	 }
  //判断是新增还是修改
  function checkAddOrUpdate()
  {
	  var endid = '${tbContractTemplatesDocPage.id}';
		if(null != endid && "" != endid) {
			var isvariable = '${tbContractTemplatesDocPage.bvariable}';
			var docType = '${tbContractTemplatesDocPage.docType.id}';
			$("#bvariable").val(isvariable);
			$("#docType").val(docType);
		}
  }
  </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" >
			<input id="id" name="id" type="hidden" value="${tbContractTemplatesDocPage.id }">
			<table style="width: 600px;height: 200px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="docname" name="docname" datatype="s1-20"
							   value="${tbContractTemplatesDocPage.docname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件类型:
						</label>
					</td>
					<td class="value">
						<select id="docType"  name="docType" >
					       <c:forEach items="${docTyleList}" var="docType">
					        <option value="${docType.id}" >
					         ${docType.typename}
					        </option>
					       </c:forEach>
				      	</select>
				      	<span class="Validform_checktip">请选择文件类型</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否设置变量:
						</label>
					</td>
					<td class="value">
						<select id="bvariable"  name="bvariable"  datatype="*">
					        <option value="0">否</option>
					        <option value="1">是</option>
				      	</select>
				      	<span class="Validform_checktip">是否设置变量</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上传文件:
						</label>
					</td>
					<td class="value">
				 <div class="form" id="filediv">
	                </div>
					  <t:upload name="fiels"  view="true" auto="false" buttonText="上传文件" dialog="false" callback="openrcon" uploader="tbContractTemplatesDocController.do?uploadTemplatesDoc" extend="*.docx" id="files" formData="id,docname,docType,bvariable">
					  </t:upload>
                          <span class="Validform_checktip">请选择要上传的文件</span> 
                       <!--    <input name="filedata" class="easyui-validatebox"
                                   required="true" type="file" missingMessage="请选择上传文件" /> -->
                     </td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script type="text/javascript">
 checkAddOrUpdate();
 </script>