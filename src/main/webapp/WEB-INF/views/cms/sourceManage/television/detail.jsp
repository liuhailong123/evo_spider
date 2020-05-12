<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="form-horizontal">
    <div class="row">
        <div class="col-lg-6">
            <img src="${entity.previewUrl}" style="width: 100%">
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">内容编码:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="code" name="code"
                           value="${entity.code}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">内容名称:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">评分:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="grade" name="grade"
                           value="${entity.grade}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">内容标题:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="title" name="title"
                           value="${entity.title}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">年份:</label>
                <div class="col-lg-8">
                    <input type="number" min="1900" max="3000" class="form-control" readonly="readonly" id="year" name="year"
                           value="${entity.year}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">时长:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="runTime" name="runTime"
                           value="${entity.runTime}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">分类标签:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="classifyTags" name="classifyTags"
                           value="${entity.classifyTags}" readonly="readonly" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">年代标签:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="yearTags" name="yearTags" value="${entity.yearTags}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">区域标签:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="areaTags" name="areaTags" value="${entity.areaTags}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">演员标签:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="actorTags" name="actorTags" value="${entity.actorTags}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">导演标签:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="directorTags" name="directorTags"
                           value="${entity.directorTags}" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否启用:</label>
                <div class="col-lg-8">
                    <x:dictData dictCode="Menu_Status" var="dictStatus">
                        <x:select defaultOption="请选择" hasDefault="true" var="status" items="${dictStatus}"
                                  id="enable" name="enable" className="form-control">
                            <x:option value="${status.code }" text="${status.name }"
                                      selected="${entity.enable eq status.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="form-group">
                <%--<label class="col-lg-1 control-label">简介:</label>--%>
                <div class="col-lg-12">
                    <textarea class="form-control" readonly="readonly" id="info" name="info">${entity.info}</textarea>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    jQuery(function ($) {

        $('#classifyTags').tagsinput();
        $('#yearTags').tagsinput();
        $('#areaTags').tagsinput();
        $('#actorTags').tagsinput();
        $('#directorTags').tagsinput();

        // 去除tag控件的多余样式
        $(".tt-hint").hide();
        $(".bootstrap-tagsinput").addClass("col-lg-12");

        $('#myTabs a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            if ($("#id").val() == "") {
                if (e.target.id != "contentTab") {
                    CmMsg.warn("请先保存内容基本信息", -1);
                    $('#contentTab').tab('show');
                }
            }
        });

        $("#enable").attr("disabled","disabled");
        $("#classifyTags").attr("disabled","disabled");
        $("#yearTags").attr("disabled","disabled");
        $("#areaTags").attr("disabled","disabled");
        $("#actorTags").attr("disabled","disabled");
        $("#directorTags").attr("disabled","disabled");
    });
</script>