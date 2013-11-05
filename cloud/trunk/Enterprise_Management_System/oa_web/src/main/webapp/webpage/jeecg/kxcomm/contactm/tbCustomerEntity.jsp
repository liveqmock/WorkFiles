<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
   <script type="text/javascript">
 function checkAddOrUpdate()
 {
	  var endid = '${tbCustomerEntityPage.id}';
		if(null != endid && "" != endid) {
			var jobPlac = '${tbCustomerEntityPage.jobPlaceId.id}';
			$("#jobPlaceId").val(jobPlac);
		}
 }
 </script>
 </head>

 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbCustomerEntityController.do?save&type=1">
			<input id="id" name="id" type="hidden" value="${tbCustomerEntityPage.id }">
			<table style="width: 700px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							公司名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="companyName" name="companyName" datatype="s1-50"
							   value="${tbCustomerEntityPage.companyName}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="address" name="address" datatype="s0-100"
							   value="${tbCustomerEntityPage.address}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="contact" name="contact" datatype="s0-20"
							   value="${tbCustomerEntityPage.contact}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							邮箱:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="email" name="email" datatype="e"
							   value="${tbCustomerEntityPage.email}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="phone" name="phone" datatype="n0-20"
							   value="${tbCustomerEntityPage.phone}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							省份:
						</label>
					</td>
					<td class="value">
					<select id="jobPlaceId"  name="jobPlaceId.id" >
					       <c:forEach items="${tbJobPlaceList}" var="jobPlace">
					        <option value="${jobPlace.id}" >
					         ${jobPlace.jobPlace}
					        </option>
					       </c:forEach>
				      	</select>
				      	<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							纳税人识别号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="taxpayerRegistrationNo" name="taxpayerRegistrationNo" datatype="*"
							   value="${tbCustomerEntityPage.taxpayerRegistrationNo}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							开户银行:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="depositBank" name="depositBank" datatype="*"
							   value="${tbCustomerEntityPage.depositBank}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							账户名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="accountName" name="accountName" datatype="*"
							   value="${tbCustomerEntityPage.accountName}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							账户号码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="accountNumber" name="accountNumber" datatype="*"
							   value="${tbCustomerEntityPage.accountNumber}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							营业证:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="shopCard" name="shopCard" 
							   value="${tbCustomerEntityPage.shopCard}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							开票信息:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="billingInformation" name="billingInformation" 
							   value="${tbCustomerEntityPage.billingInformation}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							三证:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="threeCards" name="threeCards" 
							   value="${tbCustomerEntityPage.threeCards}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							营业执照号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="businessLicenseNo" name="businessLicenseNo" 
							   value="${tbCustomerEntityPage.businessLicenseNo}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							企业法定代表人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" style="width: 200px" id="legalRepresentative" name="legalRepresentative" 
							   value="${tbCustomerEntityPage.legalRepresentative}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						
					</td>
					<td class="value">
						
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value" colspan="3">
					<textarea style="width:70%;height:80px;" id="description" name="description"  datatype="s0-50">${tbCustomerEntityPage.description}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
 <script type="text/javascript">
 checkAddOrUpdate();
 </script>