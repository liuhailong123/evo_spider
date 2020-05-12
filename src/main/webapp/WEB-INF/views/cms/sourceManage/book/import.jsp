<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
        <div class="col-lg-2"><input id="templateDownload1" type="button" value="图书信息模板"/></div>
        <div class="col-lg-2"><input id="templateDownload2" type="button" value="图片信息模板"/></div>
        <div class="col-lg-2"><input id="templateDownload3" type="button" value="关系信息模板"/></div>
        <div class="col-lg-12">&nbsp;</div>
        <div class="col-lg-12">
            <div class="form-group">
                <div>
                    <label class="col-lg-3 control-label">图书信息:</label>
                    <div class="col-lg-8" id="fileDiv1">
                        <input type="file" id="bookInfoFile" name="bookInfoFile"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="form-group">
                <div>
                    <label class="col-lg-3 control-label">图片信息:</label>
                    <div class="col-lg-8" id="fileDiv2">
                        <input type="file" id="pictureInfoFile" name="pictureInfoFile"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="form-group">
                <div>
                    <label class="col-lg-3 control-label">图书与图片关系:</label>
                    <div class="col-lg-8" id="fileDiv3">
                        <input type="file" id="relFile" name="relFile"/>
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
            window.location.href = contextPath + "/static/excel/bookInfo_template.xlsx";
        });
        $("#templateDownload2").on('click', function () {
            window.location.href = contextPath + "/static/excel/picture_template.xlsx";
        });
        $("#templateDownload3").on('click', function () {
            window.location.href = contextPath + "/static/excel/book_picture_rel_template.xlsx";
        });
    });
</script>
