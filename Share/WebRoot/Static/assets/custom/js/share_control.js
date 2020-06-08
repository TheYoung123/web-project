//给文本域响应键盘按下事件
$(function () {
    $('#share_content').bind('input propertychange', function () {
        var maxLength = 300;
        var len = $(this).val().length;
        $('.words_number').text(maxLength - len);
        //判断输入是否超过300个数
        if (parseInt($('.words_number').text()) < 50) {
            $('.words_number').css('color', 'red');
        } else {
            $('.words_number').css('color', 'darkseagreen');
        }
        if (parseInt($('.words_number').text()) < 0) {
            $('.words_number').text(0);
            //截取前300个字符并重新塞到文本域中
            var content = $(this).val().substring(0, 300);
            $(this).val(content);
        }
    });
});

// QQ表情
$(function () {
    //表情
    $('.emotion').qqFace({
        assign: 'share_content', //给输入框赋值
        path: path + '/Static/assets/qqFace/arclist/'    //表情图片存放的路径
    });
});


// 多图上传控制
console
var imgCount = 0;//记录图片上传数量
$(document).ready(function () {

    //文件上传相关
    initFileUpload();

    //初始化分享列表
    getShareList();

    //加载个人简介
    getProfile(loginInfoId, "myProfile");

    //加载个人粉丝
    getFansList(loginInfoId, 'myFansList', 1, 6);

    //加载关注列表
    getFollowingList(loginInfoId, 'myFollowing', 1, 6);
});

//弹出提示框
function showMsg(title, msg) {
    $("#myModalLabel").html(title);
    $("#msgContent").html(msg);
    $("#myModal").modal('show');

}

function initFileUpload() {
    $('#ssi-upload3').ssi_uploader({
        url: path + '/share/upload.do',
        dropZone: false,
        allowed: ['jpg', 'jpeg', 'png', 'gif'],
        maxNumberOfFiles: 9,
        onUpload: function () {
            //上传成功后将清除选择的文件
            uploadObj.clear();

            releaseShare();
        }
    });
    $("#uploadButton").click(function () {
        $('#ssi-previewBox').css('visibility', 'hidden'); //点击上传按钮隐藏图片预览

        $shareContent = $("#share_content");
        imgCount = $(".ssi-imgToUpload").length;
        if ($shareContent.val() == "" || $shareContent.val().length > 300) {
            showMsg("错误提示", "内容不能为空，且长度不能超过300.");
        } else if (isBadwordExist($shareContent.val())) {
            showMsg("错误提示", "内容包含屏蔽关键词，不允许发布！");
        } else if ($(".ssi-imgToUpload").length == 0) {
            //没有选择图片，则执行另一个方法进行上传
            releaseShare();
        } else {
            //否则上传文件，同时带上数据
            uploadObj.uploadFiles();
        }
    });
}

function releaseShare() {
    $.ajax({
        url: path + '/share/releaseShare.do',
        type: 'post',
        data: {
            content: $("#share_content").val(),
            isPublic: $("input[name='isPublic']:checked").val(),
            imgCount: imgCount
        },
        dataType: 'json',
        success: function (data) {
            if (!data.status) {
                showMsg("错误提示", data.msg);
            } else {
                //界面回复原状态
                $("#publicRadio").attr("checked", true);
                $("#privateRadio").attr("checked", false);
                $("#share_content").val("");
                toastr.success('发布成功！');
                //发布完成
                $('.words_number').text('300');
                getShareList();
            }
        }
    });
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
