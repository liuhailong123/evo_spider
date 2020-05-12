<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="form-group">
                <label class="col-lg-3 control-label">频道号:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="number" name="number" value="${entity.number}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">频道名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">频道类型:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="LiveChannelType" var="liveChannelType">
                        <x:select defaultOption="请选择" hasDefault="true" var="liveChannelType" items="${liveChannelType}"
                                  id="type" name="type" className="form-control">
                            <x:option value="${liveChannelType.code }" text="${liveChannelType.name }"
                                      selected="${entity.type eq liveChannelType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">内容id:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="contentId" name="contentId"
                           value="${entity.contentId}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">内容名称:</label><span class="text-danger">*</span>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="contentName" name="contentName" readonly="readonly"
                           value="${contentName}"/>
                </div>
                <dev class="col-lg-2">
                    <input type="button" value="剧集" onclick="selectEpisode()"
                           class="btn btn-primary btn-sm form-control">
                    </input>
                </dev>
                <dev class="col-lg-2">
                    <input type="button" value="电影" onclick="selectMovie()"
                           class="btn btn-primary btn-sm form-control">
                    </input>
                </dev>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">子集集号:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="1" class="form-control" id="episodeNumber" name="episodeNumber"
                           value="${entity.episodeNumber}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">上线时间:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="uptime" name="uptime" placeholder="请选择"
                           value="${entity.uptime}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">下线时间:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="downtime" name="downtime" placeholder="请选择"
                           value="${entity.downtime}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">状态:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="LiveChannelStatus" var="liveChannelStatus">
                        <x:select defaultOption="请选择" hasDefault="true" var="liveChannelStatus"
                                  items="${liveChannelStatus}"
                                  id="status" name="status" className="form-control">
                            <x:option value="${liveChannelStatus.code }" text="${liveChannelStatus.name }"
                                      selected="${entity.status eq liveChannelStatus.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    jQuery(function ($) {

        /**
         * 日期选择
         */
        $("#uptime").jeDate({
            isTime: true,
            format: "YYYY-MM-DD hh:mm:ss",
            minDate: "2014-09-19 00:00:00"

        });

        /**
         * 日期选择
         */
        $("#downtime").jeDate({
            isTime: true,
            format: "YYYY-MM-DD hh:mm:ss",
            minDate: "2014-09-19 00:00:00"

        });

        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "number": {
                    required: true
                },
                "name": {
                    required: true
                },
                "type": {
                    required: true
                },
                "uptime": {
                    required: true
                },
                "downtime": {
                    required: true
                },
                "status": {
                    required: true
                },
                "contentName": {
                    required: true
                },
                "episodeNumber": {
                    required: true
                }
            },
            messages: {
                "number": '请输入频道号',
                "name": '请输频道名称',
                "type": '请选择频道类型',
                "uptime": '请选择上线时间',
                "downtime": '请选择下线时间',
                "status": '请选择状态',
                "contentName": '请选择相关内容',
                "episodeNumber": '请输入子集集数，电影输入1'
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

    function selectMovie() {
        var url = "/contentManage/catalogueRelation/select/1";
        showSelectDlg("选择", contextPath + url, ["Select_table-data"],
            selectCallBack, BootstrapDialog.SIZE_WIDE);
    }

    function selectEpisode() {
        var url = "/contentManage/catalogueRelation/select/2";
        showSelectDlg("选择", contextPath + url, ["Select_table-data"],
            selectCallBack, BootstrapDialog.SIZE_WIDE);
    }

    function selectCallBack(obj) {
        $("#contentId").val(obj[0].id);
        $("#contentName").val(obj[0].name);
    }
</script>