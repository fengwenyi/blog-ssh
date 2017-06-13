<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/26 0026
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>${essay.title}</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<%=basePath %>resources/css/essay.css" />
    <script type="text/javascript" src = "http://fengwenyi.com/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="<%=basePath %>resources/editor/editormd.css" />
    <script type="text/javascript" src = "<%=basePath %>resources/editor/editormd.min.js"></script>
</head>
<body>
<!-- 博文 -->
<div class="main">
    <div class="title">${essay.title}</div>
    <div class="content" id="content">
        ${essay.content}
    </div>
</div>

<!-- 评论 -->
<!--
评论内容

 -->
<div class="comment">
    <form action="/comment" method="post">
        <!-- 隐藏部分 start -->
        <input type="hidden" name="essay_id" value="${essay.id}">
        <!-- 隐藏部分 end -->
        <div class="div-comment-title">
            评论
        </div>
        <div class="div-nickname">
            <input class="nickname" type="text" name="nickname" placeholder="请在此处填写您的昵称">
        </div>
        <div class="div-content">
            <textarea class="content" name="content" placeholder="扯淡、吐槽、表扬、鼓励……"></textarea>
        </div>
        <div class="div-submit">
            <input class="submit" type="submit" name="" value="提交">
        </div>
    </form>
</div>

<!-- 评论列表 -->
<div class="comment_list">
    <div class="comment_log">评论列表</div>
    <ul>
        <c:forEach var="c" items="${comment}">
            <li>
                <div class="user_info">
                    <div class="left">${c.user.nickname}</div>
                    <div class="right">
                        <jsp:useBean id="myDate" class="java.util.Date"/>
                        <c:set target="${myDate}" property="time" value="${c.time}"/>
                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${myDate}" type="both"/>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="comment_cnt">
                    ${c.content}
                </div>
                <div class="cmt_reply"><a href="javascript:;">回复</a></div>
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
