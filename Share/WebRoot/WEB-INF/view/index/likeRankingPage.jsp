<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>点赞榜</title>
    <%@include file="/WEB-INF/view/layout/head_file.jsp"%><!-- 包含公共引用的 js, css 等 -->

    <!-- toastr消息提示 -->
    <link href="${path }/Static/assets/toastr/toastr.css" rel="stylesheet"/>
    <script src="${path }/Static/assets/toastr/toastr.js"></script>

    <script>
        $(document).ready(function () {
            //初始化分享列表
            getShareList(null, null, null, null, 4, 1);
        });
    </script>
</head>
<body>

<%@include file="/WEB-INF/view/layout/navbar.jsp"%><!-- 导航栏 -->

<div class="container">
    <%-- 点赞榜样式--%>
    <%@include file="/WEB-INF/view/layout/like_ranking.jsp" %>
    <div id="pageNavigator">
    </div>
</div>
<!-- 警告弹出框 -->
<%@include file="/WEB-INF/view/layout/model_dialog.jsp"%>
<%@include file="/WEB-INF/view/layout/footer.jsp"%><!-- 页脚 -->
</body>
</html>
