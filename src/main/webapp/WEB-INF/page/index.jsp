<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>冯文议的博客-一个专注Java编程的博客网站</title>
    <meta charset="utf-8">

    <link rel="stylesheet" href="https://dn-ssl-dwcdn.qbox.me/fa/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/index.css">
    <script type="text/javascript" src = "http://fengwenyi.com/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src = "<%=basePath %>resources/js/index.js"></script>
</head>
<body>
<!-- 左侧 -->
<header class="header" id="header">
    <!-- 个人资料 -->
    <div class="my-data">
        <img src="http://imgages.fengwenyi.com/myHear.png" />
        <hgroup>
            <h1>冯文议的博客</h1>
            <h2>xfsyMrFeng's blog</h2>
        </hgroup>
        <ul class="nav">
            <li class="nav-left"><a href="" alt = "主页" title="主页"><i class="fa fa-home"></i></a></li>
            <li class="nav-left"><a href="" alt = "留言" title="留言"><i class="fa fa-comments"></i></a></li>
            <li><a href="" alt = "全屏" title="全屏"><i class="fa fa-expand"></i></a></li>
            <div class="clear"></div>
        </ul>
    </div>

    <!-- 个人介绍 -->
    <div class="introduct">
        <p>我是一个正在向IT行进的编程者，目前在成都一家软件开发公司实习。爱好：书法、乒乓球、旅游、电影、音乐、数码产品。</p>
        <br>
        <p>我是一个对理想有着执着追求的人，并坚信是金子总会发光。</p>
        <br>
        <p>我的理念是：在年轻的季节我甘愿吃苦受累，只愿通过自己富有激情、积极主动的努力实现自身价值并在工作中做出最大的贡献。</p>
    </div>

    <!-- 留言栏 -->
    <div class="message">
        <ul>
            <li>
                <div class="msg-name">无名</div>
                <div class="msg-cnt">海拉尔你去过了，很厉害啊，海拉尔（-47）冬天只比雅库茨克（-48）冷一度，动车将来会修到满洲里呢…</div>
            </li>
            <li>
                <div class="msg-name">无名</div>
                <div class="msg-cnt">海拉尔你去过了，很厉害啊，海拉尔（-47）冬天只比雅库茨克（-48）冷一度，动车将来会修到满洲里呢…</div>
            </li>
            <a href=""><li class="giveMeMsg"><i class="fa fa-pencil-square-o"></i>&emsp;给我留言</li></a>
        </ul>
    </div>
    <!-- footer -->
    <footer class="footer">
        <p>© xfsyMrFeng's Blog 2017</p>
        <br>
        <p>Designed and developed by xfsyMrFeng.</p>
    </footer>
</header>
<!-- 右侧正文 -->
<div id="main" class="main">
    <ul>
        <c:forEach var="l" items="${list}">
            <li>
                <div class="left">
                    <div class="title"><a target="_blank" href="/essay/${l.id}">${l.title}</a></div>
                    <div class="summary">${l.summary}</div>
                    <div class="info">
                        <jsp:useBean id="myDate" class="java.util.Date"/>
                        <c:set target="${myDate}" property="time" value="${l.time}"/>
                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" type="both"/>
                        &emsp;|&emsp;${fn:length(l.comments)} 人评论</div>
                </div>
                <div class="right">
                    <img src="${l.img}">
                </div>
                <div class="clear"></div>
            </li>
        </c:forEach>
        <!-- 分页 -->
        <li class="page">
            <c:if test="${page.pageNo > 1 }">
                <a href="/index?p=${page.getPrevPageNo()}" class="prePage">上一页</a>
            </c:if>
            <c:if test="${page.pageNo < page.getBtmPageNo()}">
                <a href="/index?p=${page.getNextPageNo()}" class="nextPage">下一页</a></li>
            </c:if>
    </ul>
</div>
</body>
</html>