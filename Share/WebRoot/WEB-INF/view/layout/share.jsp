<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="share_container">
    <!-- 分享的上部分, 头像, 内容等 -->

    <div class="share_main_container">
'        <%--箭头按钮--%>'+
'        <div class="down_opt">'+
'            <a href="javascript:void(0);">'+
'                <i class="icon iconfont icon-down"></i></a>'+
'        </div>'+
'        <%-- 显示菜单的代码在 share_control.js 最后面 --%>'+
'        <div class="down_menu hidden">'+
'            <div class="menu_opt">'+
'                <div class="opt_trans">'+
'                    <a href="javascript:void(0);">'+
'                        转为私密'+
'                    </a>'+
'                </div>'+
'                <div class="opt_trans">'+
'                    <a href="javascript:void(0);">'+
'                        删除'+
'                    </a>'+
'                </div>'+
'            </div>'+
'        </div>'+
'        <%-- 右上角菜单到此结束--%>'+


        <div class="share_layout">
            <div class="share_avatar"> <!-- 头像 -->
                <a href="#"><img src="${path}/${sessionScope.loginInfo.imgPath}" class="share_avatar"></a>
            </div>
            <div class="share_main">
                <div class="share_username">
                    <a href="#" class="share_username_link">
                        ${sessionScope.loginInfo.username}
                    </a>
                </div>
                <div class="share_date">
                    <a href="#" class="share_date_link">
                        ${sessionScope.loginInfo.createTime}
                    </a>
                </div>


                <!-- 图片显示 -->
                <div class="share_media">
                    <div id="pic" style="clear: both">
                        <p></p>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- 分享的下部分, 收藏/评论/点赞等 -->
    <div class="share_opt_container">
        <ul class="opt_line">
            <li><a href="#"><span class="opt_pos"><i class="icon iconfont icon-shoucang"></i> 收藏</span></a>
            </li>
            <li><a href="javascript:void(0);" class="show_comment"><span class="opt_pos"><i
                    class="icon iconfont icon-groupcopy5"></i> 评论 2</span></a>
                <span class="arrow"></span>　<!-- 三角箭头, 点击评论时出现, 收起时隐藏. -->
            </li>
            <li><a href="#"><span class="opt_pos"><span class="glyphicon glyphicon-thumbs-up"></span> 点赞</span></a></li>
        </ul>
    </div>

    <!-- 评论展开加载状态 -->
    <div class="share_loader hidden"> <!-- 添加 hidden 到 class 中时隐藏加载状态, 即在评论加载完成时添加 hidden -->
        <div class="loader_container">
            <i class="loading_img"></i><span class="loading_words"> 正在加载，请稍候...</span>
        </div>
    </div>

    <!-- 评论展示 -->

    <div class="comment_container">
        <%-- 评论展示区的输入 --%>
        <div class="comment_layout">
            <%-- 头像 --%>
            <div class="comment_my_avatar">
                <img src="${path}/${sessionScope.loginInfo.imgPath}" class="comment_avatar">
            </div>

            <%-- 输入框操作区 --%>
            <div class="comment_opt_container">
                <div><textarea rows="3" id=""></textarea></div>
                <!-- 选择表情按钮, 这里需要为 textarea 注册事件, 将 id 作为参数传给
                    $('.emotion').qqFace({
                        assign: 'share_content', //给输入框赋值
                        path: path + '/Static/assets/qqFace/arclist/'    //表情图片存放的路径
                    });
                    中的 assign
                    相关代码在 share_control.js
                -->
                <div class="comment_footer">
                    <div class="btn_comment_face">
                        <a href="javascript:void(0)" class="sendarea_a emotion light_gray" id="btn_face">
                            <i class="icon iconfont icon-xiaolian" style="color: #ffbe16"></i></a>
                    </div>
                    <!-- 分享按钮 -->
                    <input id="uploadButton" type="button" value="评论" class="btn btn-send btn-sm"
                           style="display: block; float: right">
                </div>
            </div>

            <%-- 评论列表 --%>
            <div class="comment_list_container">
                <div class="comment">
                    <%-- 一级评论者头像 --%>
                    <div class="comment_user_avatar"><a href="#">
                        <img src="/blog/upload/3b4895ca-7113-4fe4-8f4d-12c3bb9c88ec.jpg"></a>
                    </div>
                    <div class="main_comment">
                        <%-- 一级评论内容 --%>
                        <a href="#">admin</a> 
                        <div class="comment_time">
                             <a href="#">回复</a>
                        </div>
                        <%-- 一级评论结束 --%>
                        <%-- 二级评论 --%>
                        <div class="sub_comment"> <!-- 每一条评论的二级评论列表为一个 ul, 二级评论的每一条回复为一个 li -->
                            <ul>
                                <li> <!-- 一条二级评论 -->
                                    <div class="sub_comment_item">
                                        <div class="sub_item_avatar"> <!-- 子评论回复者的头像, 不显示被回复者的头像 -->
                                            <a href="#"><img
                                                    src="/blog/upload/3968b5bb-f9ad-4522-b0be-a49f5744af3b.jpg"></a>
                                        </div>

                                        <div class="sub_comment_content">
                                            <a href="#">test</a> <!-- 回复者的用户名 -->
                                            回复 <a href="#">admin</a> 
                                            <div class="sub_content_time"> <!-- 每一条回复都有时间以及后面的[回复]操作链接 -->
                                                <a href="#">回复</a>
                                            </div>
                                        </div>
                                    </div>
                                </li><!-- 一条二级回复结束 -->

                                <li>
                                    <div class="sub_comment_item">
                                        <div class="sub_item_avatar">
                                            <a href="#"><img
                                                    src="/blog/upload/3b4895ca-7113-4fe4-8f4d-12c3bb9c88ec.jpg"></a>
                                        </div>
                                        <div class="sub_comment_content">
                                            <a href="#">admin</a>
                                            回复 <a href="#">test</a> 
                                            
                                            <div class="sub_content_time">
                                                 <a href="#">回复</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <%----%>
                    </div>
                </div>


                <div class="comment">
                    <%-- 一级评论者头像 --%>
                    <div class="comment_user_avatar"><a href="#">
                        <img src="/blog/upload/3b4895ca-7113-4fe4-8f4d-12c3bb9c88ec.jpg"></a>
                    </div>
                    <%-- 一级评论内容 --%>
                    <div class="main_comment">
                        <a href="#">admin</a> : 随便说两句随便说两句随便说两句随便说两句随便说两句随便说两句随便说两句随便说两句随便说两句随便说两句随便说两句随便说两句随便说两句
                        <div class="comment_time">
                             <a href="#">回复</a>
                        </div>
                        <%-- 子评论 --%>
                        <div class="sub_comment">
                            <ul>
                                <li>
                                    <div class="sub_comment_item">
                                        <div class="sub_item_avatar">
                                            <a href="#"><img
                                                    src="/blog/upload/3968b5bb-f9ad-4522-b0be-a49f5744af3b.jpg"></a>
                                        </div>
                                        <div class="sub_comment_content">
                                            <a href="#">test</a>
                                            回复 <a href="#">admin</a> :
                                            二级评论内容唉唉唉啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
                                            <div class="sub_content_time">
                                                 <a href="#">回复</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="sub_comment_item">
                                        <div class="sub_item_avatar">
                                            <a href="#"><img
                                                    src="/blog/upload/3b4895ca-7113-4fe4-8f4d-12c3bb9c88ec.jpg"></a>
                                        </div>
                                        <div class="sub_comment_content">
                                            <a href="#">admin</a>
                                            回复 <a href="#">test</a> :
                                            回复二级评论内容唉唉唉啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
                                            <div class="sub_content_time">
                                                 <a href="#">回复</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <%----%>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>
<%----%>


<%-- 下面是图片预览的数据输入函数, #pic 为自己定义的 id, 放在 class 为 share_media 的 div 内部--%>
<script>
    $("#pic").actizPicShow({
        data: [
            {
                id: "1",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://tva2.sinaimg.cn/crop.0.0.1080.1080.50/63ee9e7cjw8ezcajkp1gbj20u00u0jt6.jpg",
                bigimg: "http://ww2.sinaimg.cn/bmiddle/62f1ea53gw1e892gvspghj20ue0na7b0.jpg"
            },
            {
                id: "2",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://ww3.sinaimg.cn/large/838966c5gw1f9ts66g9w7j20ci0m8q4f.jpg",
                bigimg: "http://ww1.sinaimg.cn/bmiddle/9a6bab8cgw1e895pzr5w9j20c80g7js6.jpg"
            },
            {
                id: "1",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://ww2.sinaimg.cn/bmiddle/62f1ea53gw1e892gvspghj20ue0na7b0.jpg",
                bigimg: "http://ww2.sinaimg.cn/bmiddle/62f1ea53gw1e892gvspghj20ue0na7b0.jpg"
            },
            {
                id: "2",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://ww2.sinaimg.cn/bmiddle/9a6bab8cgw1e895pzr5w9j20c80g7js6.jpg",
                bigimg: "http://ww1.sinaimg.cn/bmiddle/9a6bab8cgw1e895pzr5w9j20c80g7js6.jpg"
            },
            {
                id: "1",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://ww3.sinaimg.cn/large/8a728a2bjw1f9ubcav2vgj20qo1beq6b.jpg",
                bigimg: "http://ww3.sinaimg.cn/large/8a728a2bjw1f9ubcav2vgj20qo1beq6b.jpg"
            },
            {
                id: "2",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://ww2.sinaimg.cn/bmiddle/9a6bab8cgw1e895pzr5w9j20c80g7js6.jpg",
                bigimg: "http://ww1.sinaimg.cn/bmiddle/9a6bab8cgw1e895pzr5w9j20c80g7js6.jpg"
            },
            {
                id: "1",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://ww2.sinaimg.cn/bmiddle/62f1ea53gw1e892gvspghj20ue0na7b0.jpg",
                bigimg: "http://ww2.sinaimg.cn/bmiddle/62f1ea53gw1e892gvspghj20ue0na7b0.jpg"
            },
            {
                id: "2",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://ww2.sinaimg.cn/bmiddle/9a6bab8cgw1e895pzr5w9j20c80g7js6.jpg",
                bigimg: "http://ww1.sinaimg.cn/bmiddle/9a6bab8cgw1e895pzr5w9j20c80g7js6.jpg"
            },
            {
                id: "1",
                img: "http://ww3.sinaimg.cn/thumb180/4a62bb09gw1f9tx04woeqj20zk0qo44p.jpg",
                middleimg: "http://ww2.sinaimg.cn/bmiddle/62f1ea53gw1e892gvspghj20ue0na7b0.jpg",
                bigimg: "http://ww2.sinaimg.cn/bmiddle/62f1ea53gw1e892gvspghj20ue0na7b0.jpg"
            }
        ]
    });
</script>