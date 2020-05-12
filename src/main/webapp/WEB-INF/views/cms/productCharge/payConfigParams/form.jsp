<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form">
        <div class="col-lg-12">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="configId" name="configId" value="${configId}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>

            <div class="form-group">
                <label class="col-lg-3 control-label">参数（英文）:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="nameEn" name="nameEn" value="${entity.nameEn}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">参数（中文）:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="nameCn" name="nameCn" value="${entity.nameCn}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">默认值:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="value" name="value" value="${entity.value}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">排序:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" class="form-control" id="sort" name="sort" value="${entity.sort}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">备注:</label>
                <div class="col-lg-8">
                    <textarea class="form-control" id="remark" name="remark">${entity.remark}</textarea>
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
                "nameEn": {
                    required: true
                },
                "nameCn": {
                    required: true
                },
                "sort": {
                    required: true
                },
            },
            messages: {
                "nameEn": '请输入参数（英文）',
                "nameCn": '请输入参数（中文）',
                "sort": '请输入排序',
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