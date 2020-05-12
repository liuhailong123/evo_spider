<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form">
        <div class="col-lg-12">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>

            <div class="form-group">
                <label class="col-lg-3 control-label">名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">省网编码:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="provinceCode" name="provinceCode"
                           value="${entity.provinceCode}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">支付方式:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="Pay_Type" var="payType">
                        <x:select defaultOption="请选择" hasDefault="true" var="payType" items="${payType}" id="payType"
                                  name="payType" className="form-control">
                            <x:option value="${payType.code }" text="${payType.name }"
                                      selected="${entity.payType eq payType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">备注:</label>
                <div class="col-lg-8">
                    <textarea class="form-control" id="remark" name="remark">${entity.remark}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否启用:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="OFF_ON" var="enable">
                        <x:select defaultOption="请选择" hasDefault="true" var="enable" items="${enable}" id="enable"
                                  name="enable" className="form-control">
                            <x:option value="${enable.code }" text="${enable.name }"
                                      selected="${entity.enable eq enable.code}"></x:option>
                        </x:select>
                    </x:dictData>
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
                "code": {
                    required: true
                },
                "appCode": {
                    required: true
                }
            },
            messages: {
                "name": '请输入产品名称',
                "code": '请输入产品编码',
                "appCode": '请选择所属应用'
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