<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    pageContext.setAttribute("path", path);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>我的点赞</title>
    <%@include file="/WEB-INF/view/layout/head_file.jsp"%><!-- 包含公共引用的 js, css 等 -->
    
    <!-- toastr消息提示 -->
    <link href="${path }/Static/assets/toastr/toastr.css" rel="stylesheet"/>
    <script src="${path }/Static/assets/toastr/toastr.js"></script>
    
    <!-- 用户相关函数 -->
    <script src="${path }/Static/assets/custom/js/user_function.js"></script>

    <script>
        $(document).ready(function () { 
            //初始化分享列表
            getShareList(null, null, null, ${sessionScope.loginInfo.id}, 1);
            
          //加载个人简介
            getProfile(${sessionScope.loginInfo.id},"myProfile");
            
            //加载个人粉丝
            getFansList(${sessionScope.loginInfo.id},"myFansList",1,6);
            
            //加载关注列表
            getFollowingList(${sessionScope.loginInfo.id},"myFollowing",1,6);
            
        });
    </script>
</head>
<body>
    <%@include file="/WEB-INF/view/layout/navbar.jsp"%><!-- 导航栏 -->

    <div class="container">
		<div class="page_title">
			我点赞的分享
		</div>
    	<div class="row">
    		<div class="col-sm-8 blog-main">
    			<div id="shareList"></div>
    		</div>
    		<div class="blog-sidebar col-sm-3">
    		
			    <!-- 个人资料卡 -->
			    <div id="myProfile"></div>
			
			    <!-- 个人关注预览  -->
			    <div id="myFollowing">
			        
			    </div>
			
			    <!--个人粉丝预览-->
			    <div id="myFansList">
			       
			    </div>
		 	</div>
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
