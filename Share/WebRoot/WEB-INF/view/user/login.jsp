<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${path}/Static/assets/images/logo.png">
    <title>登录</title>
    <!-- Bootstrap 核心 CSS -->
    <link href="${path}/Static/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- 自定义样式 -->
    <link href="${path}/Static/assets/custom/css/default.css" rel="stylesheet"> <!-- 在这里修改字体 -->
    <link href="${path}/Static/assets/custom/css/signin.css" rel="stylesheet">

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <form class="form-signin" role="form" method="post" action="${path }/user/login.do">
        <h1 class="form-signin-logo"></h1>
        <div class="form-signin-title">
            <h1 class="form-signin-heading">Share</h1>
            <h3 class="form-signin-subtitle">图片分享社区</h3>
        </div>
        
        <div style="clear:both;"></div>
        <div class="error_msg">${msg }</div>

        <input sign="username" name="username" type="text" class="form-control" placeholder="用户名" required=""
               autofocus="">
        <input name="password" type="password" class="form-control" placeholder="密码" required="">
        <div class="input-group">
            <input sign="captcha" name="code" type="text" class="form-control" placeholder="验证码" required="">
            <span type="captchaholder" class="input-group-addon">
            	<img height="30px" src="${path }/user/getCode.do" style="cursor:pointer;" id="codeImg"
                     title="点击切换验证码">
            </span>
        </div>
        <div class="checkbox">
            <label class="checkbox-remember">
                <input type="checkbox" name="remember" value="1"> 记住一周
            </label>
            <label class="checkbox-signup">
                <a class="a-signup" href="${path }/user/registerUI.do">注册</a>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>

</div> <!-- /container -->

<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="${path}/Static/assets/jquery/jquery-3.1.1.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="${path}/Static/assets/bootstrap/js/bootstrap.min.js"></script>

<script>
    $(function () {
        //点击切换验证码
        $("#codeImg").click(function () {
            var baseSrc = "${path}/user/getCode.do";
            $(this).attr("src", baseSrc + "?r=" + Math.random());
        });
    });
</script>
</body>
</html>