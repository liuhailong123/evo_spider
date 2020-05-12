<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="type" name="type" value="${type}"/>
            <input type="hidden" id="remark" name="remark" value="${entity.remark}"/>
            <input type="hidden" id="info" name="info" value="${entity.info}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="form-group">
                <label class="col-lg-2 control-label">名称:</label><span class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
        </form>
    </div>
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
                }
            },
            messages: {
                "name": '请输入名称'
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