<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
        <div class="col-lg-2">
            <input id="templateDownload1" type="button" value="模板1下载"/>
        </div>
        <div class="col-lg-2">
            <input id="templateDownload2" type="button" value="模板2下载"/>
        </div>
        <div class="col-lg-12">&nbsp;</div>
        <div class="col-lg-12">
            <div class="form-group">
                <div>
                    <label class="col-lg-3 control-label">模板类型:</label>
                    <div class="col-lg-8">
                        <select id="type" name="type" class="form-control">
                            <option value="1">模板1</option>
                            <option value="2">模板2</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <label class="col-lg-3 control-label">会员信息:</label>
                    <div class="col-lg-8" id="fileDiv">
                        <input type="file" id="files" name="files"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    //初始化附件上传控件
    var initData = [];
    initData['maxSize'] = "21474836480";//上传文件的最大容量
    initData['maxSizeMsg'] = "文件大小不能超过20Gb！";//最大容量提示
    initData['denyExt'] = ["exe", "mp3", "txt", "docx"];//禁止上传的文件类型
    initData['allowExt'] = ["xlsx"];//允许上传的文件类型
    initData['allowExtMsg'] = "文件格式不正确！只能上传xlsx格式的文件";//文件格式提示
    initFileUpLoad("formDlg", initData);

    jQuery(function ($) {
        $("#templateDownload1").on('click', function () {
            window.location.href = contextPath + "/static/excel/user_info_template1.xlsx";
        });
        $("#templateDownload2").on('click', function () {
            window.location.href = contextPath + "/static/excel/user_info_template2.xlsx";
        });
    });
</script>