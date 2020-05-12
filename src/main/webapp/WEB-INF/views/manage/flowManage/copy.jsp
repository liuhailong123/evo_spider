<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-lg-2 control-label">省网编码:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="code" name="code"
                           value="${data.code}"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    jQuery(function ($) {
        $('#formDlg').validate(
            {
                errorElement: 'span',
                errorClass: 'help-block',
                focusCleanup: false,
                focusInvalid: false,
                onsubmit: false,
                rules: {
                    "code": {
                        required: true
                    }
                },
                messages: {
                    "code": '请输入省网编码',
                },
                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-info')
                        .addClass('has-error');
                },
                success: function (e) {
                    $(e).closest('.form-group').removeClass('has-error')
                        .addClass('has-success');
                    $(e).remove();
                },
            });
    });
</script>