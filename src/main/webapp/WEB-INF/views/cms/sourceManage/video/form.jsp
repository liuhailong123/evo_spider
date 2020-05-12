<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form">
        <div class="col-lg-6">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="type" name="type" value="1"/>
            <input type="hidden" id="sourceId" name="source.id" value="${entity.source.id}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="form-group">
                <label class="col-lg-3 control-label">格式:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="ext" name="ext" value="${entity.ext}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">大小（MB）:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="size" name="size" value="${entity.size}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">码率（MB/s）:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="bitrate" name="bitrate" value="${entity.bitrate}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">时长（秒）:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="1" class="form-control" id="time" name="time" value="${entity.time}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">分辨率:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="resolution" name="resolution"
                           value="${entity.resolution}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">清晰度:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="videoDefinition" var="videodefinition">
                        <x:select var="videodefinition" items="${videodefinition}" id="definition" name="definition"
                                  className="form-control">
                            <x:option value="${videodefinition.code }" text="${videodefinition.name }"
                                      selected="${entity.definition eq videodefinition.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">地址/三方id:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="url" name="url">${entity.url}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">cdn1:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="cdn1Url" name="cdn1Url">${entity.cdn1Url}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">cdn2:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="cdn2Url" name="cdn2Url">${entity.cdn2Url}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">cdn2:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="cdn3Url" name="cdn3Url">${entity.cdn3Url}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">平台来源:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="PlatForm" var="platform">
                        <x:select defaultOption="请选择" hasDefault="true" var="platform"
                                  items="${platform}" id="platform" name="platform" className="form-control">
                            <x:option value="${platform.code }" text="${platform.name }"
                                      selected="${entity.platform eq platform.code}"></x:option>
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
                "ext": {
                    required: true
                },
                "size": {
                    required: true
                },
                "bitrate": {
                    required: true
                },
                "time": {
                    required: true
                },
                "resolution": {
                    required: true
                },
                "platform": {
                    required: true
                },
                "url": {
                    required: true
                }
            },
            messages: {
                "ext": '请输入格式',
                "size": '请输入大小',
                "size": '请输入码率',
                "time": '请输入时长',
                "resolution": '请输入分辨率',
                "platform": '请选择平台来源',
                "url": '请输入地址'
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