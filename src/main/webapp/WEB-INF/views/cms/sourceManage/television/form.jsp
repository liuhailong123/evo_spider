<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form">
        <input type="hidden" id="id" name="id" value="${entity.id}"/>
        <input type="hidden" id="type" name="type" value="${type}"/>
        <input type="hidden" id="classify" name="classify" value="1"/>
        <input type="hidden" id="synType" name="synType" value="${entity.synType}"/>
        <input type="hidden" id="sort" name="sort" value="${entity.sort}"/>
        <input type="hidden" id="nameSpellLong" name="nameSpellLong" value="${entity.nameSpellLong}"/>
        <input type="hidden" id="nameSpellShort" name="nameSpellShort" value="${entity.nameSpellShort}"/>
        <input type="hidden" id="titleSpellLong" name="titleSpellLong" value="${entity.titleSpellLong}"/>
        <input type="hidden" id="titleSpellShort" name="titleSpellShort" value="${entity.titleSpellShort}"/>
        <input type="hidden" id="createDate" name="createDate"
               value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">内容编码:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="code" name="code"
                           value="${entity.code}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">内容名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">内容标题:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="title" name="title"
                           value="${entity.title}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">评分:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="grade" name="grade"
                           value="${entity.grade}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">年份:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="1900" max="3000" class="form-control" id="year" name="year"
                           value="${entity.year}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">时长:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="1" class="form-control" id="runTime" name="runTime"
                           value="${entity.runTime}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">内容简介:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="info" name="info">${entity.info}</textarea>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">分类标签:</label>
                <span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" id="classifyTags" name="classifyTags"
                           value="${entity.classifyTags}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">年代标签:</label>
                <div class="col-lg-8">
                    <input type="text" id="yearTags" name="yearTags" value="${entity.yearTags}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">区域标签:</label>
                <div class="col-lg-8">
                    <input type="text" id="areaTags" name="areaTags" value="${entity.areaTags}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">演员标签:</label>
                <span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" id="actorTags" name="actorTags" value="${entity.actorTags}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">导演标签:</label>
                <span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" id="directorTags" name="directorTags"
                           value="${entity.directorTags}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">语言:</label>
                <span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="language" name="language"
                           value="${entity.language}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否启用:</label><span class="text-danger">*</span>
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
    </form>
</div>
<script type="text/javascript">
    jQuery(function ($) {

        $('#classifyTags').tagsinput();
        $('#yearTags').tagsinput();
        $('#areaTags').tagsinput();
        $('#actorTags').tagsinput();
        $('#directorTags').tagsinput();

        // 页面校验
        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "name": {required: true},
                "year": {required: true},
                "runTime": {required: true},
                "contentClassify": {required: true},
                "sourceType": {required: true},
                "sLogoName": {required: true},
                "hLogoName": {required: true},
                "tags": {required: true},
                "grade": {required: true},
                "classifyTags": {required: true},
                "info": {required: true},
//			"yearTags" : { required : true },
//			"areaTags" : { required : true },
                "actorTags": {required: true},
                "directorTags": {required: true},
                "language": {required: true},
                "enable": {required: true},
                "isNew": {required: true},
            },
            messages: {
                "name": '请输入内容名称',
                "year": '请输入年份',
                "runTime": '请输入时长，格式为 HH:mm:ss',
                "contentClassify": '请选择内容分类',
                "sourceType": '请选择片源类型',
                "sLogoName": '请选择竖版海报',
                "hLogoName": '请选择横版海报',
                "tags": '请输入自定义标签',
                "grade": '请输入评分',
                "classifyTags": '请输入分类标签',
                "info": '请输入简介',
//			"yearTags" : '请输入年代标签',
//			"areaTags" : '请输入区域标签',
                "actorTags": '请输入演员标签',
                "directorTags": '请输入导演标签',
                "language": '请输入语言标签',
                "enable": '请选择启用状态',
                "isNew": '请选择是否最新',
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },

        });

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
        })
    });
</script>