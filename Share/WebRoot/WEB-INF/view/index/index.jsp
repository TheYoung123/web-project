<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>首页</title>
    <%@include file="/WEB-INF/view/layout/head_file.jsp"%><!-- 包含公共引用的 js, css 等 -->

    <script src="${path}/Static/assets/custom/js/share_control.js"></script> <!-- 控制文本输入数字 -->
    <link href="${path}/Static/assets/bootstrap/css/awesome-bootstrap-checkbox.css" rel="stylesheet">

    <!-- 图片上传 -->
    <link rel="stylesheet" href="${path }/Static/assets/ImgInput/css/ssi-uploader.css">
    <script src="${path }/Static/assets/ImgInput/js/ssi-uploader.js"></script>

    <!-- toastr消息提示 -->
    <link href="${path }/Static/assets/toastr/toastr.css" rel="stylesheet"/>
    <script src="${path }/Static/assets/toastr/toastr.js"></script>

    <!-- 用户相关函数 -->
    <script src="${path }/Static/assets/custom/js/user_function.js"></script>

</head>
<body>
<%@include file="/WEB-INF/view/layout/navbar.jsp"%><!-- 导航栏 -->

<div class="container">
    <%@include file="/WEB-INF/view/layout/main.jsp"%><!-- 中间部分 -->
    <div class="row">

        <div class="col-md-8 blog-main">

            <!-- 发布分享区域 -->
            <div class="send_container">
                <div class="form-group">

                    <label class="send_tip">有什么图片想分享给大家？</label>

                    <span style="color: #a3a3a3; display: block; float: right">
                            您还可以输入 <strong style="color:darkseagreen" class="words_number">300</strong> 个字
                        </span>
                    <textarea class="form-control" contenteditable="true" name="content"
                              id="share_content"></textarea>

                    <div class="send_footer"><!-- 选择表情/图片等按钮 -->
                        <div class="btn_face_image">
                            <a href="javascript:void(0)" class="sendarea_a emotion light_gray" id="btn_face">
                                <i class="icon iconfont icon-xiaolian" style="color: #ffbe16"></i> </a>

                            <!-- 上传图片 -->
                            <!--
                            <a href="javascript:void(0)" class="sendarea-a light_gray" id="btn-image">
                                <span class="glyphicon glyphicon-picture" style="color: darkseagreen"></span> 图片</a>
                             -->
                            <input type="file" multiple id="ssi-upload3" name="file"/>
                        </div>
                        <!-- 分享按钮 -->
                        <input id="uploadButton" type="button" value="分享" class="btn btn-send"
                               style="display: block; float: right"/>

                        <!-- 权限单选 -->
                        <div class="share_limits">
                            <div class="radio radio-info radio-inline">
                                <input type="radio" id="publicRadio" value="0" name="isPublic" checked="">
                                <label for="publicRadio"> 公开 </label>
                            </div>
                            <div class="radio radio-danger radio-inline">
                                <input type="radio" id="privateRadio" value="1" name="isPublic">
                                <label for="privateRadio"> 私密 </label>
                            </div>
                        </div>

                    </div>

                </div>
            </div><!-- 发布分享区域 -->

            <div id="show"></div><!-- 表情选择区域 -->

            <div id="recommend"></div><!-- 为您推荐标题 -->

            <div id="shareList"></div><!-- 展示分享区域 -->
        </div>

        <%@include file="/WEB-INF/view/layout/sidebar.jsp" %>
    </div>


    <hr/>
    <div id="pageNavigator"></div>
</div>

<!-- 警告弹出框 -->
<%@include file="/WEB-INF/view/layout/model_dialog.jsp"%>

<!-- 页脚 -->
<%@include file="/WEB-INF/view/layout/footer.jsp" %>
</body>
</html>
