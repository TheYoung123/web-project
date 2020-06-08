$(function () {
    $('.show_comment').click(function () {
        var maxLength = 300;
        var len = $(this).val().length;
        $('strong').text(maxLength - len);
        //判断输入是否超过300个数
        if (parseInt($('strong').text()) < 50) {
            $('strong').css('color', 'red');
        } else {
            $('strong').css('color', 'darkseagreen');
        }
        if (parseInt($('strong').text()) < 0) {
            $('strong').text(0);
            //截取前300个字符并重新塞到文本域中
            var content = $(this).val().substring(0, 300);
            $(this).val(content);
        }
    });

    //显示右上角菜单
    $('.down_opt').click(function () {
        $('.down_menu').removeAttr('class', 'hidden');
    });

});