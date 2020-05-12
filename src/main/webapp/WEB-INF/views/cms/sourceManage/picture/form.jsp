<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="sourceId" name="source.id" value="${entity.source.id}"/>
            <input type="hidden" id="cloudPath" name="cloudPath" value="${entity.cloudPath}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="form-group" id="extDiv">
                <label class="col-lg-2 control-label">格式:</label><span class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="ext" name="ext" value="${entity.ext}"/>
                </div>
            </div>
            <div class="form-group" id="sizeDiv">
                <label class="col-lg-2 control-label">大小（KB）:</label><span class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="number" min="1" class="form-control" id="size" name="size" value="${entity.size}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">横/竖:</label>
                <div class="col-lg-9">
                    <x:dictData dictCode="Picture_Type" var="pictureType">
                        <x:select defaultOption="请选择" hasDefault="false" var="pictureType" items="${pictureType}"
                                  id="type" name="type" className="form-control">
                            <x:option value="${pictureType.code }" text="${pictureType.name }"
                                      selected="${entity.type eq pictureType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group" id="resolutionDiv">
                <label class="col-lg-2 control-label">分辨率:</label><span class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="resolution" name="resolution"
                           value="${entity.resolution}"/>
                </div>
            </div>
            <div class="form-group" id="urlDiv">
                <label class="col-lg-2 control-label">文件名:</label><span class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="fileName" name="fileName" value="${entity.fileName}"/>
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
                "ext": {
                    required: true
                },
                "size": {
                    required: true
                },
                "resolution": {
                    required: true
                },
                "fileName": {
                    required: true
                }
            },
            messages: {
                "ext": '请输入格式',
                "size": '请输入大小',
                "resolution": '请输入分辨率',
                "fileName": '请输入文件名'
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