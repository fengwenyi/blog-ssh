<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>冯文议的博客-管理系统</title>
	<meta charset="utf-8">

	<link rel="stylesheet" href="https://dn-ssl-dwcdn.qbox.me/fa/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/admin/index.css">
	<script type="text/javascript" src = "http://fengwenyi.com/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src = "<%=basePath %>resources/js/admin/index.js"></script>
</head>
<body>
	<!-- header -->
	<header class="header">
		<div class="title">冯文议个人博客管理系统</div>
		<div class="sign-out"><a href="/logout"><i class="fa fa-sign-out"></i></a></div>
	</header>

	<!-- nav -->
	<nav class="nav" id="nav">
		<ul>
			<li class="home in"><a href="javascript:;"><i class="fa fa-home"></i></a></li>
			<li class="book"><a href="javascript:;" id="nav-book"><i class="fa fa-book"></i></a></li>
			<li class="message"><a href="javascript:;" id="nav-message"><i class="fa fa-pencil-square-o"></i></a></li>
			<li class="comment"><a href="javascript:;" id="nav-comment"><i class="fa fa-comments"></i></a></li>
			<li class="user"><a href="javascript:;" id="nav-user"><i class="fa fa-user"></i></a></li>
		</ul>
	</nav>
	<div class="nav-list">
		<ul>
			<li class="book" id="nav-list-book">
				<div class="triangle"></div>
				<div class="list-value">
					<a href="/admin/essay-list">博文列表</a>
					<a href="/admin/essay-write">写博文</a>
				</div>
				<div class="clear"></div>
			</li>
			<li class="message" id="nav-list-message">
				<div class="triangle"></div>
				<div class="list-value">
					<a href="">留言列表</a>
				</div>
				<div class="clear"></div>
			</li>
			<li class="comment" id="nav-list-comment">
				<div class="triangle"></div>
				<div class="list-value">
					<a href="/admin/comment-list">评论列表</a>
				</div>
				<div class="clear"></div>
			</li>
			<li class="user" id="nav-list-user">
				<div class="triangle"></div>
				<div class="list-value">
					<a href="/admin/user-list">用户列表</a>
				</div>
				<div class="clear"></div>
			</li>
		</ul>
	</div>
	<div class="main" id="main">
        <ul>
        	<li>
        		<div class="num">10</div>
        		<div class="name">博文</div>
        	</li>
        	<li>
        		<div class="num">10</div>
        		<div class="name">留言</div>
        	</li>
        	<li>
        		<div class="num">10</div>
        		<div class="name">评论</div>
        	</li>
        	<li>
        		<div class="num">10</div>
        		<div class="name">用户</div>
        	</li>
        </ul>
    </div>
</body>
</html>