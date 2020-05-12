<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@include file="../../../include.inc.jsp" %>--%>
<%--<div class="tabbable">--%>
<%--    <ul id="myTabs" class="nav nav-tabs">--%>
<%--        <li class="active">--%>
<%--            <a id="spiderContentChildTab" href="#spiderContentChild" data-toggle="tab"><i--%>
<%--                    class="green ace-icon fa fa-pencil-square-o bigger-120"></i>文章段落信息</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--    <form id="formDlg" class="form-horizontal" role="form">--%>
<%--        <input type="hidden" id="id" name="id" value="${entity.id}"/>--%>
<%--        <!-- 内容视频隐藏域 -->--%>
<%--&lt;%&ndash;        <input type="hidden" id="contentVideoHidden" name="contentVideoHidden"/>&ndash;%&gt;--%>
<%--        <div class="tab-content">--%>
<%--            <!-- 基本信息 -->--%>
<%--            <div id="spiderContentChild" class="tab-pane fade in active">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-lg-12">--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="col-lg-2 control-label">文章ID:</label>--%>
<%--                            <div class="col-lg-9">--%>
<%--                                <input type="text" class="form-control" readonly="readonly" id="fId" name="fId"--%>
<%--                                       value="${entity.fId}"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="col-lg-2 control-label">段落排序:</label><span class="text-danger">*</span>--%>
<%--                            <div class="col-lg-9">--%>
<%--                                <input type="text" class="form-control" id="textSort" name="textSort" value="${entity.textSort}"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="col-lg-2 control-label">段落内容:</label><span class="text-danger">*</span>--%>
<%--                            <div class="col-lg-9">--%>
<%--                                <textarea class="form-control" id="contentText" name="contentText">${entity.contentText}</textarea>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <!-- 视频资源 -->--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>
<%--<script type="text/javascript">--%>
<%--    jQuery(function ($) {--%>
<%--        // 页面校验--%>
<%--        $('#formDlg').validate({--%>
<%--            errorElement: 'span',--%>
<%--            errorClass: 'help-block',--%>
<%--            focusCleanup: false,--%>
<%--            focusInvalid: false,--%>
<%--            onsubmit: false,--%>
<%--            rules: {--%>
<%--                "contentText": {required: true},--%>
<%--                "textSort": {required: true},--%>
<%--            },--%>
<%--            messages: {--%>
<%--                "contentText": '请输入内容',--%>
<%--                "textSort": '请给当前段落排序',--%>
<%--            },--%>
<%--            highlight: function (e) {--%>
<%--                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');--%>
<%--            },--%>
<%--            success: function (e) {--%>
<%--                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');--%>
<%--                $(e).remove();--%>
<%--            },--%>

<%--        });--%>
<%--        // 去除tag控件的多余样式--%>
<%--//         $(".tt-hint").hide();--%>
<%--//         $(".bootstrap-tagsinput").addClass("col-lg-12");--%>

<%--        $('#myTabs a[data-toggle="tab"]').on('shown.bs.tab', function (e) {--%>
<%--            if ($("#id").val() == "") {--%>
<%--                if (e.target.id != "spiderContentChildTab") {--%>
<%--                    CmMsg.warn("请保存段落信息", -1);--%>
<%--                    $('#spiderContentChildTab').tab('show');--%>
<%--                }--%>
<%--            }--%>
<%--        })--%>

<%--    });--%>


<%--    /**--%>
<%--     * 打开视频选择页--%>
<%--     */--%>
<%--    function addVideoAsset() {--%>
<%--        showSelectDlg("选择视频", contextPath + '/sourceManage/video/videoSelect/1/' + encodeURIComponent($("#name").val()), ["Select_table-data"],--%>
<%--            selectVideoCallBack, BootstrapDialog.SIZE_WIDE);--%>
<%--    }--%>

<%--    function selectVideoCallBack(obj) {--%>
<%--        // 隐藏无数据提醒区域--%>
<%--        $("#noRowsTr_video").hide();--%>
<%--        var html = "";--%>
<%--        $.each(obj, function (i, o) {// 循环添加视频信息进入列表中--%>
<%--            var id = parseInt(10000000000000000000 * Math.random());--%>
<%--            html += '<tr class=\'contentVideoRow\' data-id="' + id + '" data-videoId="' + o.id + '">' +--%>
<%--                '<td>' + o.name + '</td>' +--%>
<%--                '<td>' + o.typeName + '</td>' +--%>
<%--                '<td>' + o.definitionName + '</td>' +--%>
<%--                '<td>' + o.ext + '</td>' +--%>
<%--                '<td>' + o.size / 1024 + 'MB</td>' +--%>
<%--                '<td>' + o.time / (1000 * 1000) + '秒</td>' +--%>
<%--                '<td>' + o.resolution + '</td>' +--%>
<%--                '<td>' +--%>
<%--                '<button type=\'button\' class=\'btn btn-white btn-purple btn-sm\' onclick=\'deleteContentVideo(this,0);\'>删除</button>&nbsp;' +--%>
<%--                '</td>' +--%>
<%--                '</tr>';--%>
<%--        });--%>
<%--        $("#noRowsTr_video").before(html);--%>
<%--    }--%>

<%--    /**--%>
<%--     * 删除内容视频关系--%>
<%--     */--%>
<%--    function deleteContentVideo(obj, type, id) {--%>
<%--        $(obj).parent().parent().remove();// 删除dom--%>
<%--        if ($("#resultBox_video").children().length == 2) {--%>
<%--            $("#noRowsTr_video").show();--%>
<%--        }--%>
<%--        if (type == 1) {//1:删除数据库数据；0：无需删除数据库数据--%>
<%--            //调用ajax方法删除内容视频关系--%>
<%--            $.ajax({--%>
<%--                url: contextPath + "/sourceManage/sourceRel/remove/" + id,--%>
<%--                type: "post",--%>
<%--                data: {},--%>
<%--                success: function (data) {--%>
<%--                    if (data.status == 'OK') {--%>
<%--                        CmMsg.success(data.message, -1);--%>
<%--                    } else if (data.status == 'ERROR') {--%>
<%--                        CmMsg.error(data.message, -1);--%>
<%--                    } else if (data.status == 'TIMEOUT') {--%>
<%--                        CmMsg.error(data.message, -1);--%>
<%--                    } else {--%>
<%--                        CmMsg.error("删除失败了", -1);--%>
<%--                    }--%>
<%--                }--%>
<%--            });--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>