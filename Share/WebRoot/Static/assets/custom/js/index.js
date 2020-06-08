function replace_em(str) {

    str = str.replace(/\</g, '&lt;');
    str = str.replace(/\>/g, '&gt;');
    str = str.replace(/\n/g, '<br/>');
    str = str.replace(/\[em_([0-9]*)\]/g, '<img src= ' + path + '/Static/assets/qqFace/arclist/$1.gif border="0" />');
    return str;
}

/**
 * 获取分享列表
 * @param pageNo 页号
 * @param pageSize 页大小
 * @param condition 搜索条件
 * @param userId 用户id
 * @param shareType 分享类型（点赞/评论/收藏等的分享，见常量定义）
 * @param lastNDays 最近N天的分享
 */
function getShareList(pageNo, pageSize, condition, userId, shareType, lastNDays) {
    if (condition == undefined) {
        condition = "";
    }
    $.ajax({
        url: path + "/share/listShare.do",
        type: "post",
        data: {
            "pageNo": pageNo,
            "pageSize": pageSize,
            "condition": condition,
            "userId": userId,
            "shareType": shareType,
            "lastNDays": lastNDays
        },
        dataType: "json",
        success: function (data) {
            var html = '';
            if(shareType==null||shareType==0) {
                $("#recommend").empty();
            }
            if (data.items.length == 0) {
                if((shareType==null||shareType==0)&&condition==''&&userId==null) {
                    $("#recommend").html('<div class="share_container" style="min-height: 34px;text-align: center; padding: 8px; color: #bababa">— 为您推荐 —</div>');
                    getShareList(null, null, null, null, 4);
                    return;
                } else {
                    html = '<div class="share_container"><div class="no_data">暂无相关分享</div></div>';
                }
            }
            $.each(data.items, function (index, share) {
                //点赞榜，排名1~3的分别加上金银铜图标
                var rankHtml = '';
                if (shareType == 4) {
                    if (index < 3 && (pageNo==null||pageNo==1)) {
                        if (index == 0) {
                            rankHtml =
                                '    <div class="rank_gold">' +
                                '        <i class="iconfont icon-rank-2"></i>' +
                                '    </div>';
                        } else if (index == 1) {
                            rankHtml =
                                '    <div class="rank_silver">' +
                                '        <i class="iconfont icon-rank-2"></i>' +
                                '    </div>';
                        } else if (index == 2) {
                            rankHtml =
                                '    <div class="rank_coppery">' +
                                '        <i class="iconfont icon-rank-2"></i>' +
                                '    </div>';
                        }
                    }
                }

                share.content = replace_em(share.content);
                //搜索描红
                if (condition != '') {
                    share.content = share.content.replace(new RegExp(condition, "g"), "<font color='red'>" + condition + "</font>");
                }
                html +=
                    '<div class="share_container">' +
                    rankHtml +
                    '    <!-- 分享的上部分, 头像, 内容等 -->' +
                    '    <div class="share_main_container">';
                //自己的分享
                if (share.userId == loginInfoId) {
                    html +=
                        '        <!--箭头按钮-->' +
                        '        <div class="down_opt" id="down_opt-' + share.id + '">' +
                        '            <a href="javascript:void(0);">' +
                        '                <i class="icon iconfont icon-down"></i></a>' +
                        '        </div>' +
                        '        <!-- 显示菜单的代码在 share_control.js 最后面 -->' +
                        '        <div class="down_menu hidden" id="down_menu-' + share.id + '">' +
                        '            <div class="menu_opt">' +
                        '                <div class="opt_trans">' +
                        '                    <a href="javascript:void(0);" id="toggleStatus-' + share.id + '">' +
                        '                        设为' + (share.status ? '公开' : '私密') +
                        '                    </a>' +
                        '                </div>' +
                        '                <div class="opt_trans" id="deleteShare-' + share.id + '">' +
                        '                    <a href="javascript:void(0);">' +
                        '                        删除' +
                        '                    </a>' +
                        '                </div>' +
                        '            </div>' +
                        '        </div>' +
                        '        <!-- 右上角菜单到此结束-->';
                }
                html +=
                    '        <div class="share_layout">' +
                    '            <div class="share_avatar"> <!-- 头像 -->' +
                    '                <a href="' + path + '/index/personalPage.do?userId=' + share.userId + '"><img src="' + path + '/' + share.userImg + '" class="share_avatar"> </a>' +
                    '            </div>' +
                    '            <div class="share_main">' +
                    '                <div class="share_username">' +
                    '                    <a href="' + path + '/index/personalPage.do?userId=' + share.userId + '" class="share_username_link">' +
                    '                        ' + share.userName + '' +
                    '                    </a>' +
                    '                </div>' +
                    '                <div class="share_date">' +
                    '                    <div class="share_date_link">' +
                    '                        ' + share.createTime + ' ' + (share.userId == loginInfoId ? (share.status ? '私密' : '公开') : '') +
                    '                    </div>' +
                    '                </div>' +
                    '                <div class="share_content">' +
                    '                    ' + share.content +
                    '                </div>' +
                    '' +
                    '                <!-- 图片显示 -->' +
                    '                <div class="share_media">' +
                    '                    <div id="pic-' + share.id + '" style="clear: both">' +
                    '                        <p></p>' +
                    '                    </div>' +
                    '                </div>' +
                    '            </div>' +
                    '        </div>' +
                    '    </div>' +
                    '    <!-- 分享的下部分, 收藏/评论/点赞等 -->' +
                    '    <div class="share_opt_container">' +
                    '        <ul class="opt_line">' +
                    '            <li><a href="javascript:void(0);" id="collectBtn-' + share.id + '"><span class="opt_pos"><i class="icon iconfont icon-shoucang ' + (share.isCollect ? 'orange' : '') + '"></i> <span id="collectText-' + share.id + '">' + (share.isCollect ? '取消收藏' : '收藏') + '</span> <span id="collectCount-' + share.id + '">' + share.collectCount + '</span></span></a>' +
                    '            </li>' +
                    '            <li><a href="javascript:void(0);" id="commentBtn-' + share.id + '" class="show_comment"><span class="opt_pos"><i' +
                    '                    class="icon iconfont icon-groupcopy5"></i> 评论 <span id="commentCount-' + share.id + '">' + share.commentCount + '</span></span></a>' +
                    '                <span id="arrow-' + share.id + '" class="arrow hidden"></span>　<!-- 三角箭头, 点击评论时出现, 收起时隐藏. -->' +
                    '            </li>' +
                    '            <li><a href="javascript:void(0);" id="likeBtn-' + share.id + '"><span class="opt_pos"><span class="glyphicon glyphicon-thumbs-up ' + (share.isLike ? 'red' : '') + '"></span> <span id="likeText-' + share.id + '">' + (share.isLike ? '取消点赞' : '点赞') + '</span> <span id="likeCount-' + share.id + '">' + share.likeCount + '</span></span></a></li>' +
                    '        </ul>' +
                    '    </div>' +
                    '' +
                    '    <!-- 评论展开加载状态 -->' +
                    '    <div id="share_loader-' + share.id + '" class="share_loader hidden"> <!-- 添加 hidden 到 class 中时隐藏加载状态, 即在评论加载完成时添加 hidden -->' +
                    '        <div class="loader_container">' +
                    '            <i class="loading_img"></i><span class="loading_words"> 正在加载，请稍候...</span>' +
                    '        </div>' +
                    '    </div>' +
                    '' +
                    '    <!-- 评论展示 -->' +
                    '    <div id="commentArea-' + share.id + '"></div>' +
                    '</div>';
            });
            $("#shareList").html(html);

            //渲染html完成后
            $.each(data.items, function (index, share) {
                //加载分享图片
                var imgs = new Array(share.shareImgs.length);
                $.each(share.shareImgs, function (index, obj) {
                    var img = {
                        id: index,
                        img: path + "/" + obj.smallPath,
                        middleimg: path + "/" + obj.bigPath,
                        bigimg: path + "/" + obj.bigPath
                    };
                    imgs[index] = img;
                });
                $("#pic-" + share.id).actizPicShow({
                    data: imgs
                });
                //注册点赞按钮
                registerLikeButton(share.isLike, share.id);
                //注册收藏按钮
                registerCollectButton(share.isCollect, share.id);
                //注册评论按钮
                registerCommentButton(share.id);

                //显示右上角菜单
                $('#down_opt-' + share.id).click(function () {
                    $('.down_menu').addClass('hidden');//防止同时显示多个右上角菜单
                    event.stopImmediatePropagation();//取消事件冒泡；
                    $('#down_menu-' + share.id).removeClass('hidden');
                });
                $(document).bind("click", function () {
                    $('.down_menu').addClass('hidden');
                });

                //设为公开/隐私按钮
                $("#toggleStatus-" + share.id).click(function () {
                    var url;
                    if (share.status) {
                        url = path + '/share/showShare.do';
                    } else {
                        url = path + '/share/hideShare.do';
                    }
                    $.ajax({
                        url: url,
                        type: "post",
                        data: {
                            "id": share.id
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.status) {
                                toastr.success('设为' + (share.status ? '公开' : '私密') + '成功！');
                                //重新加载分享列表
                                getShareList(pageNo, pageSize, condition, userId, shareType, lastNDays);
                            }
                        }
                    });
                });

                //删除分享按钮
                $("#deleteShare-" + share.id).click(function () {
                    $.ajax({
                        url: path + '/share/deleteShare.do',
                        type: "post",
                        data: {
                            "id": share.id
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.status) {
                                //重新加载分享列表
                                toastr.success('删除成功！');
                                getShareList(pageNo, pageSize, condition, userId, shareType, lastNDays);
                            }
                        }
                    });
                });

            });

            //分页导航条
            var p = new Pagination({
                containerId: 'pageNavigator',
                page: data.pageNo,
                rows: data.pageSize,
                count: data.totalCount,
                toPage: function (page) {
                    getShareList(page, data.rows, condition, userId, shareType, lastNDays);
                }
            });

        }
    });
}

//获取评论列表
function getCommentList(shareId) {
    $.ajax({
        url: path + "/comment/getCommentList.do",
        type: "post",
        data: {
            "shareId": shareId
        },
        dataType: "json",
        async: false,    //展开与小三角同步
        complete: function () {
            $("#share_loader-" + shareId).addClass("hidden");
        },
        success: function (data) {
            var html = '';
            html += '' +
                '    <div class="comment_container">' +
                '        <!-- 评论展示区的输入 -->' +
                '        <div class="comment_layout">' +
                '            <!-- 头像 -->' +
                '            <div class="comment_my_avatar">' +
                '                <img src="' + path + '/' + loginInfoImg + '" class="comment_avatar">' +
                '            </div>' +
                '' +
                '            <!-- 输入框操作区 -->' +
                '            <div class="comment_opt_container">' +
                '                <div><textarea rows="3" id="comment_content-' + shareId + '"></textarea></div>' +
                '                <div class="comment_footer">' +
                '                    <div class="btn_comment_face">' +
                '                        <a href="javascript:void(0)" class="sendarea_a emotion light_gray" id="btn_face">' +
                '                            <i class="icon iconfont icon-xiaolian" style="color: #ffbe16"></i></a>' +
                '                    </div>' +
                '                    <!-- 分享按钮 -->' +
                '                    <input id="uploadCommentBtn-' + shareId + '" type="button" value="评论" class="btn btn-send btn-sm"' +
                '                           style="display: block; float: right">' +
                '                </div>' +
                '            </div>' +
                '' +
                '            <!-- 评论列表 -->' +
                '            <div class="comment_list_container">';
            $.each(data.data, function (index, comment) {
                html +=
                    '                <div class="comment">' +
                    '                    <!-- 一级评论者头像 -->' +
                    '                    <div class="comment_user_avatar"><a href="' + path + '/index/personalPage.do?userId=' + comment.userId + '">' +
                    '                        <img src="' + path + '/' + comment.userImg + '"></a>' +
                    '                    </div>' +
                    '                    <div class="main_comment">' +
                    '                        <!-- 一级评论内容 -->' +
                    '                        <a href="' + path + '/index/personalPage.do?userId=' + comment.userId + '">' + comment.username + '</a> : ' + replace_em(comment.content) +
                    '                        <div class="comment_time">' +
                    '                            ' + comment.createTime + ' <a href="javascript:void(0);" id="replyBtn-' + comment.id + '">回复</a>' +
                    '                        </div>' +
                    '                        <div id="replyInput-' + comment.id + '"></div>' +
                    '                        <!-- 一级评论结束 -->' +
                    '                        <!-- 二级评论 -->' +
                    '                        <div class="sub_comment"> <!-- 每一条评论的二级评论列表为一个 ul, 二级评论的每一条回复为一个 li -->' +
                    '                            <ul>';
                $.each(comment.childs, function (ind, child) {
                    html +=
                        '                                <li> <!-- 一条二级评论 -->' +
                        '                                    <div class="sub_comment_item">' +
                        '                                        <div class="sub_item_avatar"> <!-- 子评论回复者的头像, 不显示被回复者的头像 -->' +
                        '                                            <a href="' + path + '/index/personalPage.do?userId=' + child.userId + '"><img' +
                        '                                                    src="' + path + '/' + child.userImg + '"></a>' +
                        '                                        </div>' +
                        '' +
                        '                                        <div class="sub_comment_content">' +
                        '                                            <a href="' + path + '/index/personalPage.do?userId=' + child.userId + '">' + child.username + '</a> <!-- 回复者的用户名 -->' +
                        '                                            回复 <a href="' + path + '/index/personalPage.do?userId=' + child.pUserId + '">' + child.pUsername + '</a> : ' + replace_em(child.content) +
                        '                                            <div class="sub_content_time"> <!-- 每一条回复都有时间以及后面的[回复]操作链接 -->' +
                        '                                                ' + child.createTime + ' <a href="javascript:void(0);" id="replyBtn-' + child.id + '">回复</a>' +
                        '                                            </div>' +
                        '                                            <div id="replyInput-' + child.id + '"></div>' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                </li><!-- 一条二级回复结束 -->';
                });
                html +=
                    '                            </ul>' +
                    '                        </div>' +
                    '                        <!---->' +
                    '                    </div>' +
                    '                </div>';
            });
            html +=
                '            </div>' +
                '        </div>' +
                '    </div>' +
                '</div>';

            $("#commentArea-" + shareId).html(html);

            //表情
            $('.emotion').qqFace({
                assign: 'comment_content-' + shareId, //给输入框赋值
                path: path + '/Static/assets/qqFace/arclist/'    //表情图片存放的路径
            });

            //注册发表评论按钮事件
            $("#uploadCommentBtn-" + shareId).click(function () {
                var $comment_content = $("#comment_content-" + shareId);
                if ($comment_content.val() == "" || $comment_content.val().length > 100) {
                    showMsg("错误提示", "内容不能为空，且长度不能超过100.");
                    return;
                } else if (isBadwordExist($comment_content.val())) {
                    showMsg("错误提示", "内容包含屏蔽关键词，不允许发布！");
                    return;
                }
                var url = path + "/comment/save.do";
                $.ajax({
                    url: url,
                    type: "post",
                    data: {
                        "shareId": shareId,
                        "content": $comment_content.val()
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.status == true) {
                            //清空textfield
                            $("#comment_content-" + shareId).val('');
                            //评论数+1
                            $commentCount = $("#commentCount-" + shareId);
                            $commentCount.text(parseInt($commentCount.text()) + 1);
                            //刷新评论列表
                            getCommentList(shareId);
                        }
                    },
                });
            });

            //准备回复组件
            $.each(data.data, function (index, comment) {
                //第一层
                prepareReply(shareId, comment);
                $.each(comment.childs, function (ind, child) {
                    //第二层
                    prepareReply(shareId, child);
                });
            });
        }
    });
}

//准备回复的组件
function prepareReply(shareId, comment) {
//点击回复链接（出现回复输入框）
    $("#replyBtn-" + comment.id).click(function () {
        //再次点击清除输入框
        if ($("#replyInput-" + comment.id).html()) {
            //不为空（已有输入框）
            $("#replyInput-" + comment.id).empty();
            return;
        }
        var html =
            '            <!-- 输入框操作区 -->' +
            '            <div class="comment_opt_container">' +
            '                <div><textarea rows="2" id="reply_content-' + comment.id + '"></textarea></div>' +
            '                <div class="comment_footer">' +
            '                    <div class="btn_comment_face">' +
            '                        <a href="javascript:void(0)" class="sendarea_a emotion light_gray" id="btn_face">' +
            '                            <i class="icon iconfont icon-xiaolian" style="color: #ffbe16"></i></a>' +
            '                    </div>' +
            '                    <!-- 分享按钮 -->' +
            '                    <input id="uploadReplyBtn-' + comment.id + '" type="button" value="回复" class="btn btn-send btn-sm"' +
            '                           style="display: block; float: right">' +
            '                </div>' +
            '            </div>' +
            '';
        $("#replyInput-" + comment.id).html(html);
        //表情
        $('.emotion').qqFace({
            assign: 'reply_content-' + comment.id, //给输入框赋值
            path: path + '/Static/assets/qqFace/arclist/'    //表情图片存放的路径
        });
        //点击回复按钮
        $("#uploadReplyBtn-" + comment.id).click(function () {
            var $reply_content = $("#reply_content-" + comment.id);
            if ($reply_content.val() == "" || $reply_content.val().length > 100) {
                showMsg("错误提示", "内容不能为空，且长度不能超过100.");
                return;
            } else if (isBadwordExist($reply_content.val())) {
                showMsg("错误提示", "内容包含屏蔽关键词，不允许发布！");
                return;
            }
            var url = path + "/comment/save.do";
            $.ajax({
                url: url,
                type: "post",
                data: {
                    "shareId": shareId,
                    "content": $reply_content.val(),
                    "parentId": comment.id
                },
                dataType: "json",
                success: function (data) {
                    if (data.status == true) {
                        //清除textfield
                        $("#replyInput-" + comment.id).empty();
                        //评论数+1
                        $commentCount = $("#commentCount-" + shareId);
                        $commentCount.text(parseInt($commentCount.text()) + 1);
                        //刷新评论列表
                        getCommentList(shareId);
                    }
                },
            });
        });
    });
}

//点赞/取消点赞
function doLike(isLike, shareId) {
    var url = "";
    if (isLike) {
        url = path + "/like/addLike.do";
    } else {
        url = path + "/like/deleteLike.do";
    }
    $.ajax({
        url: url,
        type: "post",
        data: {"id": shareId},
        dataType: "json",
        async: false,    //防止点赞数在一次点击出现多次修改
        success: function (data) {
            if (data.status == true) {
                //更新显示（点赞数、文字）
                var likeCount = $("#likeCount-" + shareId);
                var likeText = $("#likeText-" + shareId);
                if (isLike) {
                    likeCount.text(parseInt(likeCount.text()) + 1);
                    likeText.text("取消点赞");
                    $('#likeBtn-' + shareId + ' .glyphicon-thumbs-up').addClass('red');
                } else {
                    likeCount.text(parseInt(likeCount.text()) - 1);
                    likeText.text("点赞");
                    $('#likeBtn-' + shareId + ' .glyphicon-thumbs-up').removeClass('red');
                }
                registerLikeButton(isLike, shareId);
            }
        },
    });
}

//收藏/取消收藏
function doCollect(isCollect, shareId) {
    var url = "";
    if (isCollect) {
        url = path + "/collect/addCollect.do";
    } else {
        url = path + "/collect/deleteCollect.do";
    }
    $.ajax({
        url: url,
        type: "post",
        data: {"id": shareId},
        dataType: "json",
        async: false,    //防止收藏数在一次点击出现多次修改
        success: function (data) {
            if (data.status == true) {
                //更新显示（收藏数、文字）
                var collectCount = $("#collectCount-" + shareId);
                var collectText = $("#collectText-" + shareId);
                if (isCollect) {
                    collectCount.text(parseInt(collectCount.text()) + 1);
                    collectText.text("取消收藏");
                    $('#collectBtn-' + shareId + ' .icon-shoucang').addClass('orange');
                } else {
                    collectCount.text(parseInt(collectCount.text()) - 1);
                    collectText.text("收藏");
                    $('#collectBtn-' + shareId + ' .icon-shoucang').removeClass('orange');
                }
                registerCollectButton(isCollect, shareId);
            }
        },
    });
}

//注册点赞按钮
function registerLikeButton(isLike, shareId) {
    //注册前失效，防止出现意外错误
    $("#likeBtn-" + shareId).off();
    $("#likeBtn-" + shareId).click(function () {
        doLike(!isLike, shareId);
    });
}
//注册收藏按钮
function registerCollectButton(isCollect, shareId) {
    //注册前失效，防止出现意外错误
    $("#collectBtn-" + shareId).off();
    $("#collectBtn-" + shareId).click(function () {
        doCollect(!isCollect, shareId);
    });
}

//点击查看评论列表
function registerCommentButton(shareId) {
    $("#commentBtn-" + shareId).click(function () {
        if ($("#arrow-" + shareId).hasClass("hidden")) {
            //显示三角形
            $("#arrow-" + shareId).removeClass("hidden");
            //显示加载
            $("#share_loader-" + shareId).removeClass("hidden");
            //模拟加载
            setTimeout(function () {
                //展开
                getCommentList(shareId);
            }, 400);
        } else {
            //闭合
            $("#arrow-" + shareId).addClass("hidden");
            $("#commentArea-" + shareId).empty();
        }
    });
}

//弹出提示框
function showMsg(title, msg) {
    $("#myModalLabel").html(title);
    $("#msgContent").html(msg);
    $("#myModal").modal('show');

}

function isBadwordExist(str) {
    var result = false;
    $.ajax({
        url: path + "/badword/isBadwordExist.do",
        type: 'post',
        dataType: 'json',
        async: false,
        data: {
            text: str
        },
        success: function (data) {
            if (data.data) {
                result = true;
            } else {
                result = false;
            }
        }

    });
    return result;
}