<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/8 0008
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>跳转</title>
    <style type="text/css">
        html{
            margin: 0;
            padding: 0;
            font-size: 14px;
            font-family: "Microsoft YaHei";
            background-color: #43BBEF;
            width: 100%;
            height: 100%;
        }
        body{
            width: 100%;
            height: 100%;
        }
        *{
            margin: 0;
            padding: 0;
        }
        a{
            text-decoration: none;
        }
        .clear{
            clear: both;
        }
        .tips{
            width: 300px;
            height: 80px;
            background-color: #333;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            margin: auto;
            z-index: 50;
            color: #fff;
            border-radius: 5px;
            border: 0;
            line-height: 80px;
            text-align: center;
        }
        .tips .tips-num{
            position: absolute;
            top: -28px;
            left: 285px;
            color: #eee;
        }
        .bg{
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 49;
        }
    </style>
    <script type="text/javascript" src = "http://fengwenyi.com/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        $(function() {
            //倒计时
            $(document).ready(function() {
                function jump(count) {
                    window.setTimeout(function(){
                        count--;
                        if(count >= 0) {
                            $('#tips-num').html(count);
                            jump(count);
                        } else {
                            ${intent};
                            $('#tips').hide();
                            $('#bg').hide(1000);
                        }
                    }, 1000);
                }
                jump(${number});
            });
        });
    </script>
</head>
<body>
<div class="tips">
    ${tip}
    <span class="tips-num" id="tips-num">${number}</span>
</div>
<div class="bg"></div>
</body>
</html>
