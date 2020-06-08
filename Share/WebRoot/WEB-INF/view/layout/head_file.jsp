<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 所有页面统一内容 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${path}/Static/assets/images/logo.png">
<!-- 分割 -->
<!-- Bootstrap 核心 CSS -->
<link href="${path}/Static/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 共用的默认样式如背景颜色, 字体等 -->
<link href="${path}/Static/assets/custom/css/default.css" rel="stylesheet">
<!-- 导航栏样式 -->
<link href="${path}/Static/assets/custom/css/navbar.css" rel="stylesheet">
<!-- 本页面自定义样式 -->
<link href="${path}/Static/assets/custom/css/index.css" rel="stylesheet">
<!-- 单条分享的样式 -->
<link href="${path}/Static/assets/custom/css/share.css" rel="stylesheet">
<!-- 页脚样式 -->
<link href="${path}/Static/assets/custom/css/footer.css" rel="stylesheet">
<!-- 图标 -->
<link href="${path}/Static/assets/bootstrap/css/iconfont.css" rel="stylesheet">
<!-- 右边栏 -->
<link href="${path}/Static/assets/custom/css/sidebar.css" rel="stylesheet">
<!-- 关注/粉丝列表样式 -->
<link href="${path}/Static/assets/custom/css/follow.css" rel="stylesheet">
<!-- 搜索页样式 -->
<link href="${path}/Static/assets/custom/css/searchPage.css" rel="stylesheet">
<!-- 点赞榜样式 -->
<link href="${path}/Static/assets/custom/css/like_ranking.css" rel="stylesheet">

<script>
    //全局变量，传值到.js文件
    var path = "${path}";
    var loginInfoId = "${sessionScope.loginInfo.id}";
    var loginInfoImg = "${sessionScope.loginInfo.imgPath}";
</script>

<script type="text/javascript" src="${path}/Static/assets/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${path}/Static/assets/picShow/jquery.rotate.js"></script>
<script type="text/javascript" src="${path}/Static/assets/picShow/picShow.js"></script>

<script type="text/javascript" src="${path}/Static/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/Static/assets/custom/js/index.js"></script>

<!-- 分页工具类 -->
<script src="${path }/Static/assets/pagination/js/Pagination.js"></script>
<link rel="stylesheet" href="${path }/Static/assets/pagination/css/Pagination.css"/>

<script src="${path}/Static/assets/qqFace/js/jquery.qqFace.js"></script>
<!-- 表情 -->

<script src="${path}/Static/assets/custom/js/ranking.js"></script>
<!-- 点赞榜 -->