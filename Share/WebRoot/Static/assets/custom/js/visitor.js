/*游客相关函数*/
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
        url: path + "/visitor/listShare.do",
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
            if(data.items.length==0) {
                html = '<div class="share_container"><div class="no_data">暂无相关分享</div></div>';
            }
            $.each(data.items, function (index, share) {
                //点赞榜，排名1~3的分别加上金银铜图标
                var rankHtml = '';
                if(shareType==4) {
                    if(index<3) {
                        if(index==0) {
                            rankHtml=
                                '    <div class="rank_gold">' +
                                '        <i class="icon iconfont icon-rank-2"></i>' +
                                '    </div>';
                        }else if(index==1) {
                            rankHtml=
                                '    <div class="rank_silver">' +
                                '        <i class="icon iconfont icon-rank-2"></i>' +
                                '    </div>';
                        }else if(index==2) {
                            rankHtml=
                                '    <div class="rank_coppery">' +
                                '        <i class="icon iconfont icon-rank-2"></i>' +
                                '    </div>';
                        }
                    }
                }
                
                share.content = replace_em(share.content);

                //搜索描红
                if(condition!='') {
                    share.content = share.content.replace(new RegExp(condition,"g"), "<font color='red'>" + condition + "</font>");
                }
                html +=
                    '<div class="share_container">' +
                        rankHtml +
                    '    <!-- 分享的上部分, 头像, 内容等 -->' +
                    '    <div class="share_main_container">';
                html+=
                    '        <div class="share_layout">' +
                    '            <div class="share_avatar"> <!-- 头像 -->' +
                    '                <a href="#"><img src="'+path+'/'+share.userImg+'" class="share_avatar"> </a>' +
                    '            </div>' +
                    '            <div class="share_main">' +
                    '                <div class="share_username">' +
                    '                    <a href="#" class="share_username_link">' +
                    '                        '+share.userName+'' +
                    '                    </a>' +
                    '                </div>' +
                    '                <div class="share_date">' +
                    '                    <div class="share_date_link">' +
                    '                        '+share.createTime+
                    '                    </div>' +
                    '                </div>' +
                    '                <div class="share_content">' +
                    '                    '+share.content+
                    '                </div>' +
                    '' +
                    '                <!-- 图片显示 -->' +
                    '                <div class="share_media">' +
                    '                    <div id="pic-'+share.id+'" style="clear: both">' +
                    '                        <p></p>' +
                    '                    </div>' +
                    '                </div>' +
                    '            </div>' +
                    '        </div>' +
                    '    </div>' +
                    '    <!-- 分享的下部分, 收藏/评论/点赞等 -->' +
                    '' +
                    '</div>';
            });
            $("#shareList").html(html);

            //渲染html完成后
            $.each(data.items, function (index, share) {
                //加载分享图片
                var imgs = new Array(share.shareImgs.length);
                $.each(share.shareImgs, function (index, obj) {
                    var img = {
                        id:index,
                        img:path+"/"+obj.smallPath,
                        middleimg:path+"/"+obj.bigPath,
                        bigimg:path+"/"+obj.bigPath
                    };
                    imgs[index]=img;
                });
                $("#pic-"+share.id).actizPicShow({
                    data: imgs
                });
                
            });

            //分页导航条
            var p=new Pagination({
            	containerId:'pageNavigator',
            	page:data.pageNo,
            	rows:data.pageSize,
            	count:data.totalCount,
            	toPage:function(page){
            		getShareList(page,data.rows,condition,userId,shareType,lastNDays);
            	}
            });

        }
    });
}


/**
 * 获取搜索的用户列表
 */
function getSearchUsers(condition, containerId) {
    $.ajax({
        url: path + "/visitor/getSearchUsers.do",
        type: 'post',
        dataType: 'json',
        data: {
            condition: condition
        },
        success: function (data) {
            if (!data.status) {
                alert(data.msg);
                return;
            }

            var $container = $("#" + containerId);
            var userList = data.data;
            if (userList.length == 0) {
                $container.html("搜索不到相关用户");
                return;
            }
            var html = '';
            $.each(userList, function (index, user) {
                //将包含condition的部分用户名描红
                var redUsername = user.username.replace(new RegExp(condition, "g"), '<font color="red">' + condition + '</font>');
                console.log(redUsername);
                html += '	<div class="result_user">' +
                    '        <div class="result_avatar">' +
                    '            <a href="#">' +
                    '				<img src="' + path + '/' + user.imgPath + '" class="thumbnail">' +
                    '			 </a>' +
                    '        </div>' +
                    '        <div class="result_username">' +
                    '            <a href="#"' +
                    '               title="' + user.username + '">' + redUsername + '</a>' +
                    '            <i class="icon iconfont ' + (user.gender == '男' ? 'icon-iconfontiocnnan' : 'icon-nv') + '"></i>' +
                    '        </div>' +
                    '    </div><!-- /用户信息 -->';
            });
            $container.html(html);

        }
    });
}

function replace_em(str) {

    str = str.replace(/\</g, '&lt;');
    str = str.replace(/\>/g, '&gt;');
    str = str.replace(/\n/g, '<br/>');
    str = str.replace(/\[em_([0-9]*)\]/g, '<img src= ' + path + '/Static/assets/qqFace/arclist/$1.gif border="0" />');
    return str;
}