<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;" title="配置单数据">
  <%--<t:datagrid name="tbConfigModelDataList" title="配置单数据" actionUrl="tbConfigModelDataController.do?datagrid&configId=${configId }" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="产品订货号" field="dataRecordId" ></t:dgCol>
   <t:dgCol title="产品名称" field="configId" ></t:dgCol>
   <t:dgCol title="产品描述" field="configId" ></t:dgCol>
   <t:dgCol title="数量" field="quantity" ></t:dgCol>
   <t:dgCol title="目录单价" field="" ></t:dgCol>
   <t:dgCol title="目录合价" field="catalogPrice" ></t:dgCol>
   <t:dgCol title="折扣率" field="discountrate" ></t:dgCol>   
   <t:dgCol title="折扣后价格" field="discountedPrice" ></t:dgCol>
   <t:dgCol title="运保及其他费率" field="discountedPrice" ></t:dgCol>
   <t:dgCol title="折扣后现场价" field="discountedAfterPrice" ></t:dgCol>
   <t:dgCol title="安装服务费" field="installservicecharge" ></t:dgCol>
   <t:dgCol title="第一年保修期费用" field="firstYear" ></t:dgCol>
   <t:dgCol title="第二年保修期费用" field="secondYear" ></t:dgCol>
   <t:dgCol title="第三年保修期费用" field="thirdYear" ></t:dgCol>
   <t:dgCol title="合计" field="totalPrice" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbConfigModelDataController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbConfigModelDataController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbConfigModelDataController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbConfigModelDataController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid> --%>
   
   <div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	 <button onClick="back();">返回</button><button onClick="save();">保存</button>
	</div>
  	<table id="ttt" toolbar="ads" width="1380px">
  		
  		<tr  class="datagrid-toolbar">
  			<td align="center" width="2%"></td>
  			<td align="center" style="display:none"></td>
  			<td align="center"  width="6%">产品订货号</td>
  			<td align="center" width="5%">产品名称</td>
  			<td align="center" width="15%">产品描述</td>
  			<td align="center" width="2%">数量</td>
  			<td align="center" width="5%">目录单价</td>
  			<td align="center" width="5%">目录合价</td>
  			<td align="center" width="3%">折扣率</td>
  			<td align="center" width="6%">折扣后价格</td>
  			<td align="center" width="6%">运保及其他费率</td>
  			<td align="center" width="6%">折扣后现场价</td>
  			<td align="center" width="6%">安装服务费</td>
  			<td align="center" width="5%">第一年保修期费用</td>
  			<td align="center" width="5%">第二年保修期费用</td>
  			<td align="center" width="5%">第三年保修期费用</td>
  			<td align="center" width="5%">合计</td>
  			<td align="center" style="display:none"></td>
  			<td align="center">操作</td>
  		</tr>
  		<c:if test="${fn:length(tbConfigModelDataPage)  > 0 }">
		<c:forEach items="${tbConfigModelDataPage}" var="poVal" varStatus="stuts">
			<tr align="center" >
				<td align="center" width="2%">${stuts.index+1}</td>
  				<td align="center" style="display:none">${poVal.tbDataRecord.id }</td>
  				<td><label >${poVal.tbDataRecord.productorderno }</label></td>
  				<td><label >${poVal.tbDataRecord.tbProductType.producttypename }</label></td>
	  			<td><label >${poVal.tbDataRecord.productdesc }</label></td>
	  			<td><input type="text" name="quantity" size="5" value="${poVal.quantity }" onblur="change1(this);"/></td>
	  			<td><label >${poVal.tbDataRecord.unitprice }</label></td>
	  			<td><label >${poVal.catalogPrice }</label></td>
	  			<td><input type="text" name="discountrate" size="5" value="${poVal.discountrate }" onblur="change2(this);"/></td>
	  			<td><label >${poVal.discountedPrice }</label></td>
	  			<td><label >${poVal.tbDataRecord.otherrates }</label></td>
	  			<td><label >${poVal.discountedAfterPrice }</label></td>
	  			<td><label >${poVal.tbDataRecord.installservicecharge }</label></td>
	  			<td><label >${poVal.firstYear }</label></td>
	  			<td><label >${poVal.secondYear }</label></td>
	  			<td><label >${poVal.thirdYear }</label></td>
	  			<td><label >${poVal.totalPrice }</label></td>
	  			<td align="center" style="display:none">${poVal.id }</td>
	  			<td><a href="javascript:void(0)" onClick="del(this);">删除</a></td>
	  		</tr>
	  	</c:forEach>
	  	</c:if>
  		<tr id="tt" bgcolor="#E6E6E6"  height="30px">
  			<td colspan="18"></td>
  			
  		</tr>
  		<tr height="30px" >
  			<td align="center" width="2%"></td>
  			<td align="center" style="display:none"></td>
  			<td align="center" width="6%"></td>
  			<td align="center" width="5%"></td>
  			<td align="center" width="15%">典配总价</td>
  			<td align="center" width="3%"></td>
  			<td align="center" width="5%"></td>
  			<td id="t1" align="center" width="5%">0</td>
  			<td align="center" width="5%"></td>
  			<td id="t2" align="center" width="6%">0</td>
  			<td align="center" width="6%"></td>
  			<td id="t3" align="center" width="6%">0</td>
  			<td align="center" width="6%"></td>
  			<td align="center" width="6%"></td>
  			<td align="center" width="6%"></td>
  			<td align="center" width="6%"></td>
  			<td id="t4" align="center" >0</td>
  			<td align="center" style="display:none"></td>
  			<td align="center"></td>
  		</tr>	
  	</table>
  </div>
  <div region="south"  style="height:290px;overflow: hidden;" split="true" border="false">
    <div id="myTabs" title="数据源详情" class="easyui-tabs" style="width:aotu;height:295px;"  >
		<div title="基本配置单元" data-options="closable:false,cache:false">
		<c:if test="${fn:length(volist)  > 0}">
		<c:forEach items="${volist}" var="poVal" varStatus="stuts">
		<c:if test="${poVal.param ==1}">
		  <div class="easyui-panel" data-options="collapsible:true" title="${poVal.name}" style="overflow:hidden;width:1390px;height:240px;">
			   <t:datagrid name="tbDataRecordEntityList${stuts.index }"   actionUrl="${poVal.url}" idField="id" fit="true" onClick="add">
			   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
			   <t:dgCol title="产品订货号" field="productorderno" ></t:dgCol>
			   <t:dgCol title="产品名称" field="productTypeName" ></t:dgCol>
			   <t:dgCol title="产品描述" field="productdesc" ></t:dgCol>
			   <t:dgCol title="数量" field="quantity" ></t:dgCol>
			   <t:dgCol title="目录单价" field="unitprice" ></t:dgCol>
			   <t:dgCol title="目录合价" field="heJia" ></t:dgCol>
			   <t:dgCol title="折扣率" field="discountrate" ></t:dgCol>
			   <t:dgCol title="折扣后价格" field="discountPrice" ></t:dgCol>
			   <t:dgCol title="运保及其他费率" field="otherrates" width="70"></t:dgCol>
			   <t:dgCol title="折扣后现场价" field="xianChangJia" ></t:dgCol>
			   <t:dgCol title="安装服务费" field="installservicecharge" ></t:dgCol>
			   <t:dgCol title="第一年保修期费用" field="firstyear" ></t:dgCol>
			   <t:dgCol title="第二年保修期费用" field="secondyear" ></t:dgCol>
			   <t:dgCol title="第三年保修期费用" field="thirdyear" ></t:dgCol>
			   <t:dgCol title="合计" field="totalPrice" width="70"></t:dgCol>
			   <t:dgCol title="备注" field="remark" ></t:dgCol>
			</t:datagrid>
		  </div>
		  </c:if>
		  </c:forEach>
		  </c:if>
		  </div>
		  <div title="同系列通用单元" data-options="closable:false,cache:false">
		<c:if test="${fn:length(volist)  > 0}">
		<c:forEach items="${volist}" var="poVal" varStatus="stuts">
		<c:if test="${poVal.param ==2}">
		  <div class="easyui-panel" data-options="collapsible:true" title="${poVal.name}" style="overflow:hidden;width:1390px;height:240px;">
			   <t:datagrid name="tbDataRecordEntityListTwo${stuts.index }"  actionUrl="${poVal.url}" idField="id" fit="true" onClick="add">
			   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
			   <t:dgCol title="产品订货号" field="productorderno" ></t:dgCol>
			   <t:dgCol title="产品名称" field="productTypeName" ></t:dgCol>
			   <t:dgCol title="产品描述" field="productdesc" ></t:dgCol>
			   <t:dgCol title="数量" field="quantity" ></t:dgCol>
			   <t:dgCol title="目录单价" field="unitprice" ></t:dgCol>
			   <t:dgCol title="目录合价" field="heJia" ></t:dgCol>
			   <t:dgCol title="折扣率" field="discountrate" ></t:dgCol>
			   <t:dgCol title="折扣后价格" field="discountPrice" ></t:dgCol>
			   <t:dgCol title="运保及其他费率" field="otherrates" width="70"></t:dgCol>
			   <t:dgCol title="折扣后现场价" field="xianChangJia" ></t:dgCol>
			   <t:dgCol title="安装服务费" field="installservicecharge" ></t:dgCol>
			   <t:dgCol title="第一年保修期费用" field="firstyear" ></t:dgCol>
			   <t:dgCol title="第二年保修期费用" field="secondyear" ></t:dgCol>
			   <t:dgCol title="第三年保修期费用" field="thirdyear" ></t:dgCol>
			   <t:dgCol title="合计" field="totalPrice" width="70"></t:dgCol>
			   <t:dgCol title="备注" field="remark" ></t:dgCol>
		  </t:datagrid>
		  </div>
		  </c:if>
		  </c:forEach>
		  </c:if>
		  </div>
		 <div title="全通用单元" data-options="closable:false,cache:false">
		<c:if test="${fn:length(volist)  > 0}">
		<c:forEach items="${volist}" var="poVal" varStatus="stuts">
		<c:if test="${poVal.param ==3}">
		  <div  class="easyui-panel" data-options="collapsible:true" title="${poVal.name}" style="overflow:hidden;width:1390px;height:240px;">
			   <t:datagrid name="tbDataRecordEntityListTwo${stuts.index }"  actionUrl="${poVal.url}" idField="id" fit="true" onClick="add">
			   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
			   <t:dgCol title="产品订货号" field="productorderno" ></t:dgCol>
			   <t:dgCol title="产品名称" field="productTypeName" ></t:dgCol>
			   <t:dgCol title="产品描述" field="productdesc" ></t:dgCol>
			   <t:dgCol title="数量" field="quantity" ></t:dgCol>
			   <t:dgCol title="目录单价" field="unitprice" ></t:dgCol>
			   <t:dgCol title="目录合价" field="heJia" ></t:dgCol>
			   <t:dgCol title="折扣率" field="discountrate" ></t:dgCol>
			   <t:dgCol title="折扣后价格" field="discountPrice" ></t:dgCol>
			   <t:dgCol title="运保及其他费率" field="otherrates" width="70"></t:dgCol>
			   <t:dgCol title="折扣后现场价" field="xianChangJia" ></t:dgCol>
			   <t:dgCol title="安装服务费" field="installservicecharge" ></t:dgCol>
			   <t:dgCol title="第一年保修期费用" field="firstyear" ></t:dgCol>
			   <t:dgCol title="第二年保修期费用" field="secondyear" ></t:dgCol>
			   <t:dgCol title="第三年保修期费用" field="thirdyear" ></t:dgCol>
			   <t:dgCol title="合计" field="totalPrice" width="70"></t:dgCol>
			   <t:dgCol title="备注" field="remark" ></t:dgCol>
		  </t:datagrid>
		  </div>
		  </c:if>
		  </c:forEach>
		  </c:if>
		  </div>
	  </div>
	  
  </div>
 </div>
 <script type="text/javascript">
 
 $(function(){
	
	if($("#ttt tr").length>3){
		var heJia=0;
		var discountPrice=0;
		var xianChangJia=0;
		var totalPrice=0;
		for(var i=1;i<$("#ttt tr").length-2;i++){
			$('#ttt tr').eq(i).each(function(){
				heJia += ($(this).children('td').eq(7).text()+"")*1;
				discountPrice += ($(this).children('td').eq(9).text()+"")*1;
				xianChangJia += ($(this).children('td').eq(11).text()+"")*1;
				totalPrice += ($(this).children('td').eq(16).text()+"")*1;
		 	});
		}
		$('#t1').html(heJia.toFixed(4));
		$('#t2').html(discountPrice.toFixed(4));
		$('#t3').html(xianChangJia.toFixed(4));
		$('#t4').html(totalPrice.toFixed(4));
	}
	 
 });
 
 	function add(rowIndex, rowData){
 		
 		var flag=true;
 		var _len = $("#ttt tr").length-2;
 		$('#ttt tr').each(function(){
 			if($(this).children('td').eq(1).html()==rowData.id){
 				flag=false;
 			}
 		 });
 		
 		if(flag){
 				$('#tt').before("<tr id="+_len+"  align='center'>"
 	 		 			+"<td>"+_len+"</td>"
 	 		 			+"<td style='display:none' id='"+_len+"'>"+rowData.id+"</td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.productorderno+"</label></td>"
 	 		 			+"<td>"+rowData.productTypeName+"</td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.productdesc+"</label></td>"
 	 		 			+"<td><input type='text' name='quantity' size='6' value='"+rowData.quantity+"' onblur='change1(this);'/></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.unitprice+"</label></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.heJia+"</label></td>"
 	 		 			+"<td><input type='text' name='discountrate' size='6' value='"+rowData.discountrate+"' onblur='change2(this);'/></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.discountPrice+"</label></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.otherrates+"</label></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.xianChangJia+"</label></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.installservicecharge+"</label></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.firstyear+"</label></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.secondyear+"</label></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.thirdyear+"</label></td>"
 	 		 			+"<td style='border-style: dotted;border-color: #ccc;border-width: 0 1px 1px 0;' ><label>"+rowData.totalPrice+"</label></td>"
 	 		 			+"<td style='display:none' ></td>"
 	 		 			+"<td><a href='javascript:void(0)' onClick='del(this);'>删除</a></td>"
 	 		 			+"</tr>"); 
 				sum();
 			}
 		
 	}
 	
 	function save(){
 		var a="";
 		var b="";
 		var c="";
 		var d="";
 		var e="";
 		var f="";
 		var g="";
 		var h="";
 		var j="";
 		var id="";
 		var oid="";
 		var vo="";
 		var configId='${configId}';
 		if($("#ttt tr").length<4){
 			$.dialog.tips("请先添加数据",2);
 			return;
 		}
 		for(var i=1;i<$("#ttt tr").length-2;i++){
 			$('#ttt tr').eq(i).each(function(){
 				a=$(this).children('td').eq(5).children('input').val()+",";
 				b=$(this).children('td').eq(7).text()+",";
 				c=$(this).children('td').eq(8).children('input').val()+",";
 				d=$(this).children('td').eq(9).text()+",";
 				e=$(this).children('td').eq(11).text()+",";
 				f=$(this).children('td').eq(13).text()+",";
 				g=$(this).children('td').eq(14).text()+",";
 				h=$(this).children('td').eq(15).text()+",";
 				j=$(this).children('td').eq(16).text()+",";
 				id=$(this).children('td').eq(1).text()+",";
 				oid=$(this).children('td').eq(17).text()+",";
 				vo+=a+b+c+d+e+f+g+h+j+id+oid+configId+",";
 				
 		 	});	
 		}
 		vo = vo.substring(0,vo.length-1);
 		$.ajax({
	    	url:'tbConfigModelDataController.do?save' , // 可以获取数据的接口
	    	dataType:"json",
	    	data:{'vo':vo},
	    	success:function(data) {
	    		$.dialog.tips(data.msg,2);
	    	}
	    });
 	}
 	function back(){
 		var configId='${configId}';
 		$('#quotations').panel("refresh", "tbConfigModelDataController.do?back&configId="+configId);
 	}
 	
 	function change1(obj){
 		var heJia=0;
 		var discountPrice=0;
 		var xianChangJia=0;
 		var totalPrice=0;
 		
 		var quantity = $(obj).val();
 		var tt = $(obj).parent().parent();
 		var discountrate = $(tt.find("input")[1]).val();
 		
 		heJia = ($(tt.find("td")[6]).text()*1)*quantity;
		discountPrice = heJia*(1-discountrate);
		xianChangJia = discountPrice*($(tt.find("td")[10]).text()*1+1);
		totalPrice = xianChangJia+($(tt.find("td")[12]).text()*1)+($(tt.find("td")[13]).text()*1)+($(tt.find("td")[14]).text()*1)+($(tt.find("td")[15]).text()*1);
		$(tt.find("td")[7]).text(heJia.toFixed(4));
		$(tt.find("td")[9]).text(discountPrice.toFixed(4));
		$(tt.find("td")[11]).text(xianChangJia.toFixed(4));
		$(tt.find("td")[16]).text(totalPrice.toFixed(4));
		
		sum();
 	}
 	function change2(obj){
 		var heJia=0;
 		var discountPrice=0;
 		var xianChangJia=0;
 		var totalPrice=0;
 		
 		var discountrate = $(obj).val();
 		var tt = $(obj).parent().parent();
 		var quantity = $(tt.find("input")[0]).val();
 		
 		heJia = ($(tt.find("td")[6]).text()*1)*quantity;
		discountPrice = heJia*(1-discountrate);
		xianChangJia = discountPrice*($(tt.find("td")[10]).text()*1+1);
		totalPrice = xianChangJia+($(tt.find("td")[12]).text()*1)+($(tt.find("td")[13]).text()*1)+($(tt.find("td")[14]).text()*1)+($(tt.find("td")[15]).text()*1);
		$(tt.find("td")[7]).text(heJia.toFixed(4));
		$(tt.find("td")[9]).text(discountPrice.toFixed(4));
		$(tt.find("td")[11]).text(xianChangJia.toFixed(4));
		$(tt.find("td")[16]).text(totalPrice.toFixed(4));
		
		sum();
 	}
 	
 	function sum(){
 		var heJia=0;
 		var discountPrice=0;
 		var xianChangJia=0;
 		var totalPrice=0;
 		for(var i=1;i<$("#ttt tr").length-2;i++){
 			$('#ttt tr').eq(i).each(function(){
 				heJia += ($(this).children('td').eq(7).text())*1;
 				discountPrice += ($(this).children('td').eq(9).text())*1;
 				xianChangJia += ($(this).children('td').eq(11).text())*1;
 				totalPrice += ($(this).children('td').eq(16).text())*1;
 		 	});
 		}
 		
 		$('#t1').html(heJia.toFixed(4));
 		$('#t2').html(discountPrice.toFixed(4));
 		$('#t3').html(xianChangJia.toFixed(4));
 		$('#t4').html(totalPrice.toFixed(4));
 	}
 	
 	function del(obj){
 		//alert("asd");
 		$(obj).parent().parent().remove();
 		if($("#ttt tr").length>3){
 			sum();
 		}
 		
 	}
 </script>