<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/admin/essay/write.css">
    <style>
        .div-file .img .upload{
            display: none;
        }
        .div-file .img img{
            display: "";
        }
    </style>
	<script type="text/javascript" src = "http://fengwenyi.com/js/jquery-3.2.1.min.js"></script>

	<!-- editor start -->
    <link rel="stylesheet" href="<%=basePath %>resources/editor/editormd.css" />
    <!-- <link rel="stylesheet" href="../editor/style.css" /> -->
    <!-- <script src="../editor/jquery.min.js"></script> -->
    <script type="text/javascript" src = "<%=basePath %>resources/editor/editormd.min.js"></script>
    <!-- editor end -->
	<script type="text/javascript" src = "<%=basePath %>resources/js/admin/essay/write.js"></script>
	<script type="text/javascript">
		var testEditor;
        $(function() {
            testEditor = editormd("test-editormd", {
                width   : "90%",
                height  : 800,
                syncScrolling : "single",
                path    : "<%=basePath %>resources/editor/lib/",
				saveHTMLToTextarea : true
            });
        });
	</script>
</head>
<body>
	<div class="main" id="main">
        <form action="/admin/essay-update-impl" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${essay.id}">
            <div class="div-text"><input type="text" name="title" placeholder="写下文章标题" value="${essay.title}">
            </div>
            <div class="editormd" id="test-editormd">
                <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc">${essay.content}</textarea>
                <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
                <textarea class="editormd-html-textarea" name="content">${essay.content}</textarea>
            </div>
            <!-- summary -->
            <div class="div-summary"><textarea class="summary" name="summary" placeholder="摘要">${essay.summary}</textarea></div>
            <!-- file -->
            <div class="div-file">
                <div class="tip">上传标题图片</div>
                <div class="img">
                    <i class="fa fa-plus-square-o upload" id="upload"></i>
                    <input type="file" name="title-img" id="title-img" class="title-img" onchange="imgPreview(this)">
                    <img src="${essay.img}" height="80" id="img-preview">
                </div>
                <span class="reUpload" id="reUpload"><a href="javascript:;">重新上传</a></span>
            </div>
            <!-- tag & submit -->
            <div class="div-buttom"><input class="tag" type="text" name="tag" placeholder="标签，以逗号(,)隔开" value="<c:forEach var="l" items="${essay.tags}">${l.name},</c:forEach>">
                <input class="submit" type="submit" name="" value="修改"></div>
        </form>
    </div>
</body>
</html>