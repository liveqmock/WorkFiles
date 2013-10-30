﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/jsp/taglibs.jsp" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>自服务平台</title>
<meta name="keywords" content="自服务平台" />
<meta name="description" content="自服务平台" />
<script type="text/javascript" src="${basePath}/jsp/common/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/jsp/common/js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="${basePath}/jsp/common/js/jquery.easing.js"></script>
<script type="text/javascript" src="${basePath}/jsp/common/js/jquery.dimensions.js"></script>
<script type="text/javascript" src="${basePath}/jsp/common/js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(${basePath}/jsp/common/images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(${basePath}/jsp/common/images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>


<body>
<div  style="height:100%;">
  <ul id="navigation">
  	<s:iterator value="%{menuList}" id="menu">
  		 <li> 
  		 	<a class="head"><s:property value="#menu.menuName"/></a>
		    <ul>
		    	<s:iterator value="#menu.list" id="rightList">
			       <li><a href="<s:property value="#rightList.url"/>" target="rightFrame"><s:property value="#rightList.rightName"/></a></li>
			       <!-- <li><a href="Articles.php" target="rightFrame">查看/修改日志</a></li> -->
		       </s:iterator>
		    </ul>
	    </li>
  	</s:iterator>
    <!-- <li> <a class="head">日志管理</a>
      <ul>
        <li><a href="AddArticle.php" target="rightFrame">添加日志</a></li>
        <li><a href="Articles.php" target="rightFrame">查看/修改日志</a></li>
      </ul>
    </li>
    <li> <a class="head">分类管理</a>
      <ul>
        <li><a href="AddKind.php" target="rightFrame">添加分类</a></li>
        <li><a href="Kinds.php" target="rightFrame">查看/删除分类</a></li>
      </ul>
    </li>
    <li> <a class="head">留言评论管理</a>
      <ul>
        <li><a href="messages.php" target="rightFrame">查看/删除留言</a></li>
        <li><a href="comments.php" target="rightFrame">查看/删除评论</a></li>
      </ul>
    </li>
    <li> <a class="head">友情链接管理</a>
      <ul>
        <li><a href="AddLink.php" target="rightFrame">添加友情链接</a></li>
        <li><a href="Links.php" target="rightFrame">查看/修改友情链接</a></li>
      </ul>
    </li>
    <li> <a class="head">版本信息</a>
      <ul>
        <li><a href="http://dnf.qq.com" target="_blank">ZhiLin-Li</a></li>
      </ul>
    </li> -->
  </ul>
</div>
</body>
</html>
