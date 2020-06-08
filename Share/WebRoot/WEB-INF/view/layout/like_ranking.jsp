<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page_title">
    点赞榜 | 分享精彩从这里开始
</div>
<div class="col-md-12 rank_main">
    <div class="rank_container">

        <div class="rank_list_container">
            <span class="arrow_today"></span>
            <span class="arrow_week hidden"></span>
            <span class="arrow_total hidden"></span>
            <div class="rank_list_layout">
                <div class="rank_list_title">
                    今天
                </div>

                <div id="shareList"></div>
            </div>
            <div style="clear: both"></div>
        </div>


        <div class=" rank_side_container
            ">
            <ul>
                <li>
                    <a href="javascript:getShareList(null,null,null,null,4,1);">
                        <div class="rank_today click_on">
                            今天
                        </div>
                    </a>
                </li>
                <li>
                    <a href="javascript:getShareList(null,null,null,null,4,7);">
                        <div class="rank_week">
                            最近一周
                        </div>
                    </a>
                </li>
                <li>
                    <a href="javascript:getShareList(null,null,null,null,4);">
                        <div class="rank_total">
                            所有分享
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        <div style="clear: both"></div>
    </div>
    <div style="clear: both"></div>
    <hr/>
</div>