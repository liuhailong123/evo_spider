<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <input type="hidden" id="id" name="id" value="${id}"/>
            <div class="form-group">
                <label class="col-lg-3 control-label">类型:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <select id="type" name="type" class="form-control">
                        <option value="1">置顶</option>
                        <option value="2">插入</option>
                    </select>
                </div>
            </div>
            <div class="form-group" hidden="hidden" id="indexDiv">
                <label class="col-lg-3 control-label">目标序号:</label>
                <div class="col-lg-8">
                    <input type="number" min="1" class="form-control" id="index" name="index"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    jQuery(function ($) {
        $("#type").on('change', function (e) {
            if ($(this).val() == 2) {
                $("#indexDiv").show();
                // 增加校验规则
                $("#index").rules("add", "required");
            } else {
                $("#indexDiv").hide();
                // 删除校验规则
                $("#index").rules("remove", "required");
            }
        })

        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {},
            messages: {
                "index": '请输入目标序号'
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
        });
    })
</script>