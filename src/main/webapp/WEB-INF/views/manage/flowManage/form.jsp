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
                <label class="col-lg-2 control-label">流程类型:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <x:dictData dictCode="Flow_Type" var="flowType">
                        <x:select var="flowType" items="${flowType}" id="type" name="type" className="form-control">
                            <x:option value="${flowType.code }" text="${flowType.name }"
                                      selected="${data.type eq flowType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">流程名称:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="name" name="name"
                           value="${data.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">流程介绍:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <textarea class="form-control" id="info" name="info">${data.info}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">类路径:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="classPath" name="classPath"
                           value="${data.classPath}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">方法名:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="funcName" name="funcName"
                           value="${data.funcName}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">排序:</label><span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="number" min="1" class="form-control" id="sort" name="sort"
                           value="${data.sort}"/>
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
                    "type": {
                        required: true
                    },
                    "name": {
                        required: true
                    },
                    "info": {
                        required: true
                    },
                    "classPath": {
                        required: true
                    },
                    "funcName": {
                        required: true
                    },
                    "sort": {
                        required: true
                    }
                },
                messages: {
                    "type": '请选择流程类型',
                    "name": '请输入流程名称',
                    "info": '请输入流程介绍',
                    "classPath": '请输入类路径',
                    "funcName": '请输入方法名',
                    "sort": '请输入排序',
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