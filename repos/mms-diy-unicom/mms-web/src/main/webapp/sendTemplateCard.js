var canToSecondWin = true;
$(function() {  
	$("#liuyanTitle").keyup(function() {  
		//定义最多输入数字  
		var $max =80;  
		//取得用户输入的字符的长度  
		var $length = $("#liuyanTitle").val().length;  
		if ($length >= $max) {  
			//判断是否超过最长数字  
			$("#msgcount").html("<font color=red>内容不能超过80字</font>");
			canToSecondWin = false;
		} else {  
			//没有超过就修改剩余字数  
			$("#msgcount").html("还可以输入<font color=red>"+($max - $length)+"</font>字符");
			canToSecondWin = true;
		}
	});
});

//关闭第一个发送内容输入框
function closeFirstWindow() {
	if(canToSecondWin) {
		//加载背景音乐下拉框
		var music = $("#bgMusic");
		music.empty();//清除select中的 option
		var opt = $("<option>").text("请选择背景音乐").val(0);  
		music.append(opt); 
		jQuery.post(ctxPath+"/findAllBgMusic!findAll.action",
		function(data) {
			if(null!=data && ""!=data){
				for(var i = 0;i < data.list.length; i++) {
					var opt = $("<option>").text(data.list[i].name).val(data.list[i].fileUrl);  
					music.append(opt);  
				}
			}
		},'json');
		//生成图片
		var topy=$("#liuyanTitle").css("margin-top"); 
		var leftx=$("#liuyanTitle").css("margin-left");
		var width=$("#liuyanTitle").css("width");
		var height=$("#liuyanTitle").css("height");
		var wishword = $("#liuyanTitle").val();
		var cardPic = $("#sendTemplateImg").attr("src");
		cardPic = cardPic.replace(imgHttp,'');
		topy = topy.replace('px','');
		leftx = leftx.replace('px','');
		width = width.replace('px','');
		height = height.replace('px','') +18;//systemData/templateCardPath/1380505039120003.jpg
		var imgPic = serverImgPic+"?json={'userId':"+usersId+",'postcard':'"+cardPic+"','text':{'text':'"+wishword+"','xPos':"+leftx+",'yPos':"+topy+",'width':"+width+",'height':"+height+",'fontSize':20,'fontsTTF':'systemData/fonts/COLOUR.TTF','fontsColor':'black'}}";
		var reg=new RegExp("'","g"); //创建正则RegExp对象
		 var newstr=imgPic;//.replace(reg,"\"");
//		 alert(newstr);
		 $.ajax({
	         type: "get",
	         url: newstr,
	         jsonp:'callback',
	         dataType:"jsonp",
	         success: function (result) {
	        	 $('#alreadyImg').attr("src",result["urls"]);
//	        	 $('#sendBox1').css("z-Index",9999);
//	        	 sendBox1.Show();//默认显示出来
	         },error: function (XMLHttpRequest, textStatus, errorThrown) {
//	        	 alert(XMLHttpRequest.responseText);
	        	 alert("系统出错了，请稍后在操作!");
	         }
	     });
		//留言
		 $("#content").val($("#liuyanTitle").val());
		//加载联系人
		loadGroupContract();
		//添加时间下拉框中的值
		loadTime();
		//清空联系人中的内容
		$("#shoujianren").val("");
		$("#addressee").val("");
		
//		$("#iscontext").html($("#liuyanTitle").val());
		sendBox1.Close();
		sendBox2.Show();
	}
}

//加载联系人
function loadGroupContract(){
	var groupContract = $("#groupContract");
	groupContract.html("");//清除select中的 option
	jQuery.post(ctxPath+"/findGroups!findGroups.action",
	function(data) {
		if(null!=data && ""!=data){
			for(var i = 0;i < data.groupList.length; i++) {
				var opt = $("<dt>").text(data.groupList[i].name).val(data.groupList[i].id);
				opt.mouseover(function(){
					$(this).addClass("over");
				});
				opt.mouseout(function(){
					$(this).removeClass("over");
				});
				opt.click(function(){
					$(this).parent().toggleClass("show-dd");
				});
//				var xxd = $("<div>").css("border-bottom","1px dashed gray");
				groupContract.append(opt);
//				groupContract.append(xxd);
				var contractsList = data.groupList[i].contactslist;
				for(var j = 0 ; j<contractsList.length; j++ ){
					var dd = $("<dd>").text(contractsList[j].name).val(contractsList[j].mdn);
					dd.mouseover(function(){
						$(this).addClass("over");
					});
					dd.mouseout(function(){
						$(this).removeClass("over");
					});
					dd.click(function(){
						wantContactsForCheck(this);
					});
					var xx = $("<div>").css("border-bottom","1px dashed gray");
					groupContract.append(dd);
					groupContract.append(xx);
				}
			}
		}
	},'json');
}

//加载时间
function loadTime(){
	//加载年
	var year = $("#year");
	year.empty();
	for(var i = 0;i<10; i++){
		var opt = $("<option>").text(2010+i).val(2010+i);
		year.append(opt);
	}
	var month = $("#month");
	month.empty();
	for(var i = 1;i<= 12; i++){
		var opt;
		if(i<10){
			opt = $("<option>").text("0"+i).val("0"+i);
		}else{
			opt = $("<option>").text(i).val(i);
		}
		month.append(opt);
	}
	
	var day = $("#day");
	day.empty();
	for(var i = 1;i<= 31; i++){
		var opt;
		if(i<10){
			opt = $("<option>").text("0"+i).val("0"+i);
		}else{
			opt = $("<option>").text(i).val(i);
		}
		day.append(opt);
	}
	
	var hour = $("#hour");
	hour.empty();
	for(var i = 1;i<= 24; i++){
		var opt;
		if(i<10){
			opt = $("<option>").text("0"+i).val("0"+i);
		}else{
			opt = $("<option>").text(i).val(i);
		}
		hour.append(opt);
	}
	
	var minute = $("#minute");
	minute.empty();
	for(var i = 1;i<= 60; i++){
		var opt;
		if(i<10){
			opt = $("<option>").text("0"+i).val("0"+i);
		}else{
			opt = $("<option>").text(i).val(i);
		}
		minute.append(opt);
	}
}

//获取点击选择的联系人
//params联系人的value
//&quot;忘忧草&quot;;&quot;提拉米苏&quot;;&quot;风吹草低&quot;;
function wantContactsForCheck(params) {
	//联系人名字,用于显示
	var contactsed = $("#shoujianren").val();//已经选择的联系人
	//联系人id,用于传到后台
	var addressee = $("#addressee").val(); //已经选择的联系人id
	
	var flat = true;
	var cids="";  //id
	var ccontent=""; //联系人
	if(addressee!=null){
		var ids = addressee.split(";");
		var cont = contactsed.split(";");
		for(var i=0; i<ids.length; i++){
			if(($(params).val())*1==ids[i]*1){ 
				flat = false;
			}else{
				cids += ids[i] +";";
				ccontent += cont[i]+";";
			}
		}
	}
	if(flat){
		var thenewcontact = params.innerHTML+"("+$(params).val()+")"+";";
		contactsed = contactsed + thenewcontact;
		$("#shoujianren").val(contactsed);
		
		var addresseeId = $(params).val() + ";";
		addressee = addressee + addresseeId;
		$("#addressee").val(addressee);
	}else{ //如果该联系人已经被选中，则删除该联系人
		$("#shoujianren").val(ccontent.substring(0,ccontent.length-1));
		$("#addressee").val(cids.substring(0,cids.length-1));
	}
}

var isCanClear = true;
//当第一次点击弹出发送窗口中的文本编辑框时，清楚所有的内容
function onceClear(paramsid) {
	if(isCanClear) {
		$("#"+paramsid+"").val("");
	}
}

//发送明信片
function sendTemplate(id){
	$("#cardId").val(id);
	$.ajax({
		type: 'POST',
		dataType:'text',
		url:ctxPath+"/sendTemplate!findById.action", 
		data: {
			'model.id':id
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
						$("#sendTemplateImg").attr("src",imgHttp+data.cardVo.picUrl);
						$("#liuyanTitle").val(data.cardVo.wishWord);
						$("#liuyanTitle").css("margin-top",data.cardVo.yPosText-18);  //设置输入框的相对顶部的位置
						$("#liuyanTitle").css("margin-left",data.cardVo.xPosText);  //设置输入框相对左部的位置
						$("#liuyanTitle").css("width",data.cardVo.widthText); //设置输入框的宽度
						$("#liuyanTitle").css("height",data.cardVo.heightText); //设置输入框的高度
						//定义最多输入数字  
						var $max =80;  
						//取得用户输入的字符的长度  
						if($("#liuyanTitle").val()!=null){
							var $length = $("#liuyanTitle").val().length;  
							$("#msgcount").html("还可以输入<font color=red>"+($max - $length)+"</font>字符");
						}else{
							$("#msgcount").html("还可以输入<font color=red>"+$max+"</font>字符");
						}
						sendBox1.Show();
					}
				}
			}
		}
	});	
}

//提交后台，发送明信片
function sendCard(){
	var content=$("#content").val();
	var picUrl=$("#alreadyImg").attr("src");
	var addresseeIds = $("#addressee").val(); //发件人
	var bgMusic = $("#bgMusic").val(); //背景音乐
	var year = $("#year").val(); //发送时间-年
	var month = $("#month").val(); //发送时间-月
	var day = $("#day").val(); //发送时间-日
	var hour = $("#hour").val(); //发送时间-时
	var minute = $("#minute").val(); //发送时间-分
	var checkBoxChetime = $("#checkBoxChetime").attr("checked");  //时间复选框
	var checkBoxMusic = $("#checkBoxMusic").attr("checked"); //背景音乐复选框
	var dateTime = year+"/"+month+"/"+day+" "+hour+":"+minute+":00";
	if(addresseeIds==null||""==addresseeIds){
//		alert("请选择发送人!");
		$("#titleContant").html("请选择发送人!");
		sendBox5.Show();
		return;
	}
	if(checkBoxMusic=="checked"){
		if(null==bgMusic || ""==bgMusic || 0==bgMusic){
//			alert("请选择背景音乐！");
			$("#titleContant").html("请选择背景音乐！");
			sendBox5.Show();
			return;
		}
	}
	if(checkBoxChetime=="checked"){
		var nowTime = new Date();
		if(Date.parse(dateTime)<nowTime){
//			alert("发送时间不能小于当前时间!");
			$("#titleContant").html("发送时间不能小于当前时间!");
			sendBox5.Show();
			return;
		}
	}
	$.ajax({
		type: 'POST',
		dataType:'text',
		url:ctxPath+"/sendCardMessage!sendCardMessage.action", 
		data: {
			'cardVo.picUrl':picUrl,
			'cardVo.wishWord':content,
			'cardVo.bgMusic':imgHttp+bgMusic,
			'cardVo.datetime':dateTime,
			'cardVo.msgType':1,		//模板
			'cardVo.addressId':addresseeIds,
			'cardVo.checkBoxChetime':checkBoxChetime,
			'cardVo.checkBoxMusic':checkBoxMusic
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
						sendBox2.Close();
					}
				}
			}
		}
	});	
	
}

function closed(){
	sendBox1.Close();
	$("#liuyanTitle").val("");
}

/**
 * 输入联系人的时候，自动追加分号“;”
 */
function pressKeyup(id,event){
	var value = $("#"+id).val();
	var evt = event ? event : (window.event ? window.event : null);
	var addressee = $("#addressee").val(); //已经选择的联系人id
	if(null!=value && ""!=value){
		var lastIndex = value.lastIndexOf(";");
		var restr = value.substring(lastIndex+1,value.length);
		//判断删除的时候
		if(evt.keyCode==8){
			var newValue = value.substring(0,value.lastIndexOf(restr));
			$('#shoujianren').val(newValue);
		}else{
			var reg=/^1[3|4|5|8][0-9]\d{8}$/;  //匹配号码正则表达式
			//判断自动输入手机号的时候
			if(reg.test(restr)) {
				var ids = addressee.split(";");
				var flat = true; //判断输入的号码是否重复
				for(var i=0; i<ids.length; i++){
					if(restr==ids[i]){ 
						flat = false;
						break;
					}
				}
				if(flat){ //输入11为手机号之后自动在后面追加分号“;”
					value+=";";
					$('#shoujianren').val(value);
					
					//给影藏表单域追加号码
					var addresseeId = restr + ";";
					addressee = addressee + addresseeId;
					$("#addressee").val(addressee);
				}else{
					var newValue = value.substring(0,value.lastIndexOf(restr));
					$('#shoujianren').val(newValue);
				}
			}else{ //输入不对的时候为红色底部
				//$('#shoujianren').css("background-color","red");
			}
		}
	}
}

window.onload = function(){
	var ecks = 1;
	$('.block-titbar').each(function(){
		$(this).css("background-image","url("+ctxPath+"/common/images/titbar"+ecks+".jpg)");
		ecks = ecks + 1;
	});
}
