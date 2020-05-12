<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
            <div class="form-group">
                <label class="col-lg-2 control-label">图片文件:</label>
                <span class="text-danger">*</span>
                <div class="col-lg-9" id="fileDiv">
                    <input type="file" id="file" name="file" multiple="multiple"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">

    //初始化附件上传控件
    var initData = [];
    initData['maxSize'] = "104857600";//上传文件的最大容量
    initData['maxSizeMsg'] = "文件大小不能超过100M！";//最大容量提示
    initData['denyExt'] = ["exe", "mp4", "txt", "docx", "xlsx"];//禁止上传的文件类型
    initData['allowExt'] = []; //允许上传的文件类型
    //initData['allowExt'] = [ "jpg","png"]; //允许上传的文件类型
    initData['allowExtMsg'] = "文件格式不正确！只能上传jpg、png格式的文件";//文件格式提示
    initFileUpLoad("formDlg", initData);

    jQuery(function ($) {
        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
// 				"ext" : {
// 					required : true
// 				},
// 				"size" : {
// 					required : true
// 				},
// 				"time" : {
// 					required : true
// 				},
// 				"resolution" : {
// 					required : true
// 				},
// 				"url" : {
// 					required : true
// 				}
            },
            messages: {
// 				"ext" : '请输入格式',
// 				"size" : '请输入大小',
// 				"resolution" : '请输入分辨率',
// 				"url" : '请输入地址'
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
        });
    });

</script>