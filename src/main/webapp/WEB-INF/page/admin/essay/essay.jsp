<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src = "http://fengwenyi.com/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="<%=basePath %>resources/editor/editormd.css" />
    <script type="text/javascript" src = "<%=basePath %>resources/editor/editormd.min.js"></script>
</head>
<body>
    <div>标题：${essay.title}</div>
    <div>内容：${essay.content}</div>
    <%--<c:forEach var="t" items="${essay.tags}">
        <div>标签：<c:out value="${t.name}" /></div>
    </c:forEach>--%>
    <div class="content editormd-preview-theme-dark" id="content"></div>

</body>
</html>
