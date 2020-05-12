<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form">
        <div class="col-lg-6">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="form-group">
                <label class="col-lg-3 control-label">应用名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">应用描述:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea id="info" class="form-control" name="info">${entity.info}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">应用编码:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="code" name="code" value="${entity.code}"/>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">版本名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="versionName" name="versionName"
                           value="${entity.versionName}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">版本标识:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="versionCode" name="versionCode"
                           value="${entity.versionCode}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">包名:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="packageName" name="packageName"
                           value="${entity.packageName}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">版本说明:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="versionInfo" name="versionInfo">${entity.versionInfo}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">发布日期:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="publishDate" name="publishDate"
                           value="${entity.publishDate}"/>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    jQuery(function ($) {

        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "name": {
                    required: true
                },
                "info": {
                    required: true
                },
                "code": {
                    required: true
                },
                "versionName": {
                    required: true
                },
                "versionCode": {
                    required: true
                },
                "packageName": {
                    required: true
                },
                "versionInfo": {
                    required: true
                },
                "publishDate": {
                    required: true
                },
            },
            messages: {
                "name": '请输入应用名称',
                "info": '请输入应用描述',
                "code": '请选择应用编码',
                "versionName": '请输入版本名称',
                "versionCode": '请输入版本标识',
                "packageName": '请输入包名',
                "versionInfo": '请输入版本描述',
                "publishDate": '请输入发布日期',
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