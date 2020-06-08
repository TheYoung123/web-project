<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>搜索分享、用户</title>
    <%@include file="/WEB-INF/view/layout/head_file.jsp"%><!-- 包含公共引用的 js, css 等 -->

    <!-- 用户相关函数 -->
    <script src="${path }/Static/assets/custom/js/user_function.js"></script>

    <script>
        $(document).ready(function () {
            //初始化分享列表
            getShareList(null, null, '${param.condition}');

            //获取搜索到的用户
            getSearchUsers('${param.condition}', 'mySearchList');
        });
    </script>
</head>
<body>

<%@include file="/WEB-INF/view/layout/navbar.jsp"%><!-- 导航栏 -->

<div class="container">
    <div class="row">
        <div class="page_title">
            搜索 | 寻找分享、人...
        </div>
        <div id="shareList" class="col-md-8 blog-main">
        </div>

        <%-- 搜索结果: 相关的人 --%>
        <div class="blog-sidebar col-md-3">
            <div class="search_pos">
                <%-- 搜索栏标题 --%>
                <div class="search_result_header">
                    <div class="header_tip">
                        相关用户
                    </div>
                </div>

                <%-- 搜索结果 --%>
                <div class="search_result_list" id="mySearchList">

                </div>
            </div>
        </div>

    </div>
    <hr/>
    <div id="pageNavigator"></div>
</div>
<!-- 警告弹出框 -->
<%@include file="/WEB-INF/view/layout/model_dialog.jsp"%>
<%@include file="/WEB-INF/view/layout/footer.jsp"%><!-- 页脚 -->

</body>
</html>
