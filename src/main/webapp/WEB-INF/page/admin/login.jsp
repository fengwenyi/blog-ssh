<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>冯文议的博客-登录</title>
	<meta charset="utf-8">

	<link rel="stylesheet" href="https://dn-ssl-dwcdn.qbox.me/fa/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/admin/login.css">
	<script type="text/javascript" src = "http://fengwenyi.com/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src = "<%=basePath %>resources/js/admin/login.js"></script>
</head>
<body>
	<div class="login">
		<form action="javascript:;" method="post">
			<input class="text" id="token" type="password" name="token" placeholder="TOKEN"><input class="submit" id="submit" type="submit" name="submit" value="验证">
		</form>
	</div>
    <div class="tips" id="tips">
        <span id="tip">正在验证，请稍后···</span>
        <span class="tips-num" id="tips-num">3</span>
    </div>
    <div class="bg" id="bg"></div>
</body>
</html>