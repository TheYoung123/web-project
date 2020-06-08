<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>主页</title>
    <%@include file="/WEB-INF/view/layout/head_file.jsp"%><!-- 包含公共引用的 js, css 等 -->
    <!-- 用户相关函数 -->
    <script src="${path }/Static/assets/custom/js/user_function.js"></script>

    <script>
        $(document).ready(function () {
            judgeUrl();

            //获取粉丝列表
            getFollowingList(${param.userId}, 'myList', 2, 12, 1, 'pageBar');
        });
    </script>
</head>
<body>
<div class="container" id="root-container">

    <%@include file="/WEB-INF/view/layout/navbar.jsp"%><!-- 导航栏 -->

    <div class="row follow_page">

        <div class="follow_container">

            <%@include file="/WEB-INF/view/layout/follow_head.jsp" %>

            <!-- 关注列表 -->
            <div id="myList">

            </div>
            <!-- /followList -->
        </div>

        <hr/>
        <div id="pageBar"></div>
    </div>
</div>

<!-- 页脚 -->
<%@include file="/WEB-INF/view/layout/footer.jsp" %>
</body>
</html>