<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="alert alert-info">
                <button type="button" class="close" data-dismiss="alert">
                    <i class="ace-icon fa fa-times"></i>
                </button>
                <strong></strong>
                人工处理提示:如需人工取消订购，请先在计费营业厅或当地运营商处取消该产品，再进行下面操作！
                <br>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">人工处理类型:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="Manual_Process_Type" var="manualProcessType">
                        <x:select defaultOption="" hasDefault="false" var="manualProcessType" items="${manualProcessType}"
                                  id="manualProcessType" name="manualProcessType" className="form-control">
                            <x:option value="${manualProcessType.code }" text="${manualProcessType.name }"
                                      selected="${entity.manualProcessType eq manualProcessType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">人工处理意见:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
					<textarea class="form-control" id="manualHandlingInfo"
                              name="manualHandlingInfo" rows="10">${entity.manualHandlingInfo}</textarea>
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
                    "manualHandlingInfo": {
                        required: true
                    },
                },
                messages: {
                    "manualHandlingInfo": '请输入人工处理意见',
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
            }
        );
    });


</script>














