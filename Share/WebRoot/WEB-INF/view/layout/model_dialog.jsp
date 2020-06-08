<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 警告弹出框 --%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">错误提示</h4>
            </div>
            <div class="modal-body">
                <p id="msgContent">
                <p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>