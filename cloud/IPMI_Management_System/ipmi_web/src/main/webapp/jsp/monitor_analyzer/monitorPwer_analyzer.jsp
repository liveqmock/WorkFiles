<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cn.com.kxcomm.ipmi.entity.*" %>
<%@ include file="/jsp/common/jsp/commonStyle.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务器信息详情</title>

<script type="text/javascript" src="${ctx}/jsp/common/js/utilTool.js"></script>
<script type="text/javascript" src="view/monitorPwer_analyzer_view.js"></script>
<script type="text/javascript" src="store/monitorPwer_analyzer_store.js"></script>
<script type="text/javascript" src="model/monitorPwer_analyzer_model.js"></script>
<script type="text/javascript" src="controller/monitorPwer_analyzer_controller.js"></script>
</head>
<body style="text-align: center;width: 100%;overflow:scroll;">
<div id="firstForm"></div>
<div id="toptool"></div>
<div id="columChart"></div>
</body>
</html>