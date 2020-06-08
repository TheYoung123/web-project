<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<nav class="navbar navbar-fixed-top navbar-default" role="navigation" id="navigator">
    <div class="container">
        <!-- 导航栏标志 -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${path}/index/index.do" id="nav-brand">
                <img src="${path}/Static/assets/images/logo.png" id="img-brand"> Share</a>
        </div>

        <!-- 导航栏功能元素 -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <!-- 搜索框 -->
            <form name="form-search" class="navbar-form navbar-left" role="search">
                <div class="input-group">
                    <input type="text" id="text-search" name="condition" value="${param.condition}" class="form-control"
                           placeholder="搜索分享、用户">
                    <span id="btn-search" class="input-group-addon search-btn-pointer"><span
                            class="glyphicon glyphicon-search"></span></span>
                </div>
            </form>
            <!-- 退出登录 -->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${path }/user/logout.do" class="force-white">
                    <span class="glyphicon glyphicon-log-out"></span> 退出 </a></li>
            </ul>


            <!-- 显示用户名 -->
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="force-white dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <span class="glyphicon glyphicon-user"></span> ${sessionScope.loginInfo.username}
                        <img src="${path}/${sessionScope.loginInfo.imgPath}" class="img-thumbnail">
                        <span class="caret"></span></a>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li>
                            <a href="${path}/index/likePage.do?userId=${sessionScope.loginInfo.id}">我的点赞 <span
                                    class="glyphicon glyphicon-thumbs-up"></span></a></li>
                        <li><a href="${path}/index/commentPage.do">
                            我的评论 <i class="icon iconfont icon-groupcopy5"></i></a></li>
                        <li><a href="${path}/index/collectPage.do"> 我的收藏 <i class="icon iconfont icon-shoucang"></i></a>
                        </li>
                    </ul>
                </li>
            </ul>

            <!-- 点赞榜 -->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${path}/index/likeRankingPage.do" class="force-white"><span
                        class="glyphicon glyphicon-thumbs-up"></span> 点赞榜</a></li>
            </ul>
            <!-- 首页 -->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${path}/index/index.do" class="force-white"><span class="glyphicon glyphicon-home"></span>
                    首页</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<script>
    //注册搜索框点击事件
    $("#btn-search").click(function () {
        //去除两边空格
        $("#text-search").val($.trim($("#text-search").val()));
        //搜索栏为空不提交表单
        if ($("#text-search").val() != "") {
            document.forms['form-search'].action = "${path}/index/searchPage.do";
            document.forms['form-search'].method = "post";
            document.forms['form-search'].submit();
        }
    });

    //注册‘按下回车键搜索’事件
    $("#text-search").keypress(function (e) {
        // 回车键事件
        if (e.keyCode == 13) {
            $("#btn-search").click();
            //取消按下回车提交表单
            return false;
        }
    });
</script>