<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <input type="hidden" id="id" name="id" value="${data.id}"/>
            <input type="hidden" id="provinceId" name="province.id" value="${provinceId}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${data.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="form-group">
                <label class="col-lg-2 control-label">常量说明:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <textarea class="form-control" id="info" name="info">${data.info}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">常量Key:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="constantKey" name="constantKey"
                           <%--<c:if test="${type == 2}">readonly="readonly"</c:if>--%>
                           value="${data.constantKey}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">常量value:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="constantValue" name="constantValue"
                           value="${data.constantValue}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">状态:</label>
                <div class="col-lg-9">
                    <x:dictData dictCode="Menu_Status" var="dictStatus">
                        <x:select var="status" items="${dictStatus}" id="enable" name="enable" className="form-control">
                            <x:option value="${status.code }" text="${status.name }"
                                      selected="${data.enable eq status.code}"></x:option>
                        </x:select>
                    </x:dictData>
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
                    "constantKey": {
                        required: true
                    },
                    "constantValue": {
                        required: true
                    },
                    "info": {
                        required: true
                    }
                },
                messages: {
                    "constantKey": '请输入常量Key',
                    "constantValue": '请输入常量值',
                    "info": '请输入常量说明',
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