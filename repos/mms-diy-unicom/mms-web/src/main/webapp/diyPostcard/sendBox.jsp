<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>沃爱彩信</title>
<meta name="keywords" content="沃爱彩信" />
<meta name="description" content="沃爱彩信" />

<script type="text/javascript">
function addShouCang()
{
	var cardId=$("#cardId").val();
	  $.post(ctxPath+'/myShouCang!addShouCang.action',{//
			'cardId':cardId
		},
		function(data) {
		//	var data = eval('(' + data + ')');
			if(null!=data && ""!=data){
				if(data.msg=="error"){
					//			$("#titleContant").html("请先登录！");
					//			sendBox5.Show();
								window.location.href = ctx+"timeout.jsp";
							}
				else if(data.msg=="ok"){
					alert("收藏成功！");
				}else if(data.msg=="no")
					{
					alert("收藏失败");
					}
			}
	});
}

//去到异常登录页面
function tpOtherJsp() {
	sendBox5.Close();
	window.location.href = ctx+"timeout.jsp";
} 

//提交后台，发送明信片
function sendCardDJ(){
	var addresseeIds = $("#shoujianren").val(); //收件人
	var bgMusic = imgHttp+$("#themuzics").val(); //背景音乐
	var year = $("select[name='nianer']").val(); //发送时间-年
	var month = $("select[name='yueer']").val(); //发送时间-月
	var day = $("select[name='rier']").val(); //发送时间-日
	var hour = $("select[name='shier']").val(); //发送时间-时
	var minute = $("select[name='fener']").val(); //发送时间-分
	var checkBoxChetime = $("select[name='chetime']").attr("checked");  //时间复选框
	var dateTime = year+"/"+month+"/"+day+" "+hour+":"+minute+":00";
	if(addresseeIds==null||""==addresseeIds){
		$("#letasdc").html("请填写收送人!");
		sendBox4.Show();
		return;
	}
	if(null==bgMusic || ""==bgMusic || 0==bgMusic){
		$("#letasdc").html("请选择背景音乐！");
		sendBox4.Show();
		return;
	}
	if(checkBoxChetime=="checked"){
		var nowTime = new Date();
		if(Date.parse(dateTime)<nowTime){
			$("#letasdc").html("发送时间不能小于当前时间!");
			sendBox4.Show();
			return;
		}
	}
	var picUrl = $("#newpic").attr("src");
	$.ajax({
		type: 'POST',
		dataType:'text',
		url:ctxPath+"/sendCardMessage!sendCardMessage.action", 
		data: {
			'cardVo.picUrl':picUrl,
			'cardVo.bgMusic':bgMusic,
			'cardVo.datetime':dateTime,
			'cardVo.addressId':addresseeIds,
			'cardVo.checkBoxChetime':checkBoxChetime,
			'cardVo.checkBoxMusic':"checked",
			'cardVo.msgType':2		//模板明信片
		}, 
		success: function(data, textStatus, jqXHR){
			if(null!=data && ""!=data){
				if(data.search("<head>")!=-1){ //包含有<head> 标签，即返回的是未登录页面
//					alert("请先登录！");
					$("#titleContant").html("请先登录！");
					sendBox5.Show();
					window.location.href = ctx+"timeout.jsp";
				}else{
					data = eval('(' + data + ')');
					if(data.msg=="ok"){
						sendBox3.Show();
						sendBox1.Close();
					}
				}
			}
		}
	});	
}
</script>
</head>
<!-- <body> -->
	<!--发送明信片弹窗 lightbox-->
	<!--发送明信片弹窗 lightbox-->
<div id="sendBox1" style="display:none;">
	<div class="lbox608">
		<i class="lbox-tbg png"></i>
		<div class="lbox-bg png">
			<form>
			<div class="lbox-wrap">
				<span class="btn-close png" title="关闭" onClick="javascript:sendBox1.Close();"></span>
				<span class="lbox-titbar">发送彩信</span>
				<span class="lbox-cons cons1">
					<span class="show-img1">
<!-- 						<span class="yp1 png"></span> -->
						<img id="newpic" src="" style="height: auto;no-repeat"/>
					</span>
				</span>
				<span class="lbox-btns">
					<button id="zuomieye" class="btns-next" title="下一步" onClick="sendCardDJ();" type="button"></button>
					<button class="btns-cancel" title="取消" onClick="javascript:sendBox1.Close();" type="button"></button>
				</span>
			</div>
			</form>
		</div>
		<i class="lbox-bbg png"></i>
	</div>
</div>
<!--验证 lightbox-->
	<div id="sendBox4" style="display:none">
		<div class="lbox466">
			<i class="lbox-tbg png"></i>
			<div class="lbox-bg png">
				<div class="lbox-wrap">
					<span class="btn-close png" title="关闭"  onclick="javascript:sendBox4.Close();"></span>
					<span class="lbox-titbar">温馨提示</span>
					<span class="lbox-cons">
						<span class="alert-tips t-no">
							<p id="letasdc"></p>
						</span>
					</span>
					<span class="lbox-btns">
						<button class="btns-close" title="关闭" type="button"  onclick="javascript:sendBox4.Close();"></button>
					</span>
				</div>
			</div>
			<i class="lbox-bbg png"></i>
		</div>
	</div>
	<!--验证 lightbox end-->
<!--验证 lightbox-->
	<div id="sendBox5" style="display:none">
		<div class="lbox466">
			<i class="lbox-tbg png"></i>
			<div class="lbox-bg png">
				<div class="lbox-wrap">
					<span class="btn-close png" title="关闭"  onclick="javascript:sendBox5.Close();"></span>
					<span class="lbox-titbar">温馨提示</span>
					<span class="lbox-cons">
						<span class="alert-tips t-no">
							<p id="titleContant"></p>
						</span>
					</span>
					<span class="lbox-btns">
						<button class="btns-close" title="关闭" type="button"  onclick="tpOtherJsp();"></button>
					</span>
				</div>
			</div>
			<i class="lbox-bbg png"></i>
		</div>
	</div>
	<!--验证 lightbox end-->
	<!--验证 lightbox end-->
	
	<script type="text/javascript">
		var sendBox1 = new LightBox("sendBox1"); //制定发送明信片弹窗
		var sendBox4 = new LightBox("sendBox4"); //制定发送明信片弹窗
		var sendBox5 = new LightBox("sendBox5");
// 		sendBox1.Show();//默认显示出来
	</script>
<!-- </body> -->
</html>
