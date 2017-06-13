<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>冯文议的博客-管理系统</title>
	<meta charset="utf-8">

	<link rel="stylesheet" href="https://dn-ssl-dwcdn.qbox.me/fa/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/admin/essay/list.css">
	<script type="text/javascript" src = "http://fengwenyi.com/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src = "<%=basePath %>resources/js/admin/essay/list.js"></script>
    <style type="text/css">
        /* table head */
        .main .table .tr-title{
            background-color: #3F48CC;
            color: #fff;
            border-bottom-width: 0;
            height: 35px;

        }
        .main .table .tr-title .id{
            width: 50px;
        }
        .main .table .tr-title .nickname{

        }
        .main .table .tr-title .ip{
            width: 100px;
        }
        .main .table .tr-title .position{
            width: 300px;
        }
        .main .table .tr-title .time{
            width: 100px;
        }
        .main .table .tr-title .delete{
            width: 50px;
        }
        /* table value */
        .main .table .tr-value a{
            color: #00A2E8;
        }
        .main .table .tr-value a:hover{
            font-size: 35px;
        }
    </style>
</head>
<body>
	<!-- header -->
	<header class="header">
		<div class="title">冯文议个人博客管理系统</div>
		<div class="sign-out"><a href=""><i class="fa fa-sign-out"></i></a></div>
	</header>

	<!-- nav -->
	<nav class="nav" id="nav">
		<ul>
			<li class="home"><a href="javascript:;"><i class="fa fa-home"></i></a></li>
			<li class="book"><a href="javascript:;" id="nav-book"><i class="fa fa-book"></i></a></li>
			<li class="message"><a href="javascript:;" id="nav-message"><i class="fa fa-pencil-square-o"></i></a></li>
			<li class="comment"><a href="javascript:;" id="nav-comment"><i class="fa fa-comments"></i></a></li>
			<li class="user in"><a href="javascript:;" id="nav-user"><i class="fa fa-user"></i></a></li>
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

	<!--
	#, nickname, ip, position, time
	-->
	<div class="main" id="main">
        <table class="table" id="table">
        	<tr class="tr-title">
        		<th class="id">#</th>
        		<th class="nickname">昵称</th>
        		<th class="ip">IP</th>
        		<th class="position">位置</th>
        		<th class="time">时间</th>
        		<th class="delete">删除</th>
        	</tr>
			<c:forEach var="l" items="${list}">
				<tr class="tr-value">
					<td align="center">${l.id}</td>
					<td align="center">${l.nickname}</td>
					<td align="center">${l.ip}</td>
					<td align="center">${l.position}</td>
					<td align="center">
                        <jsp:useBean id="myDate" class="java.util.Date"/>
                        <c:set target="${myDate}" property="time" value="${l.time}"/>
                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" type="both"/>
                    </td>
					<td align="center"><a href=""><i class="fa fa-trash-o"></i></a></td>
				</tr>
			</c:forEach>
        </table>

            ${page.totalRecords} | ${page.pageSize} | ${page.pageNo} | ${page.totalRecords}

		<%--<c:forEach var="p" items="${page}">
			&lt;%&ndash;首页${p.getTopPageNo()}，上一页${p.getPrevPageNo()}，下一页${p.getNextPageNo()}，尾页${p.getBtmPageNo()}|当前页：${p.getPageNo}，总记录数：${p.getTotalRecords()}&ndash;%&gt;
		</c:forEach>--%>
    </div>
</body>
</html>