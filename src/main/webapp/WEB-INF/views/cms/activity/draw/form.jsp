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
                <label class="col-lg-3 control-label">应用id:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="appId" name="appId" value="${entity.appId}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">开始时间:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="beginTime" name="beginTime"
                           value="${entity.beginTime}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">结束时间:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="endTime" name="endTime" value="${entity.endTime}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">背景图:</label><span class="text-danger">*</span>
                <div class="col-lg-6">
                    <input type="text" class="form-control" id="pictureId" name="pictureId"
                           value="${entity.pictureId}"/>
                </div>
                <div class="col-lg-2">
                    <input id="Select_btnSearch" name="Select_btnSearch" type="button" value="选择"  onclick="selectImage()" class="btn btn-primary btn-sm form-control">
                    </input>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否需要订购:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="isNeedOrder" var="isNeedOrder">
                        <x:select var="isNeedOrder" items="${isNeedOrder}" id="isNeedOrder" name="isNeedOrder"
                                  className="form-control">
                            <x:option value="${isNeedOrder.code }" text="${isNeedOrder.name }"
                                      selected="${entity.isNeedOrder eq isNeedOrder.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">最大次数:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="0" class="form-control" id="maxCount" name="maxCount"
                           value="${entity.maxCount}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">限制单位:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="limitUnit" var="unit">
                        <x:select var="unit" items="${unit}" id="unit" name="unit"
                                  className="form-control">
                            <x:option value="${unit.code }" text="${unit.name }"
                                      selected="${entity.unit eq unit.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">活动介绍:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="info" name="info" rows="10">${entity.info}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否启用:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="Menu_Status" var="dictStatus">
                        <x:select defaultOption="请选择" hasDefault="false" var="status" items="${dictStatus}"
                                  id="enable" name="enable" className="form-control">
                            <x:option value="${status.code }" text="${status.name }"
                                      selected="${entity.enable eq status.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function selectImage(){
        showSelectDlg("选择图片", contextPath + '/sourceManage/picture/singlePictureSelect/2/?name=' + $("#name").val(), ["Select_table-data"],
            selectImageCallBack,BootstrapDialog.SIZE_WIDE);
    }

    function selectImageCallBack(obj){
        $("#pictureId").val(obj[0].id);
    }

    jQuery(function ($) {
        $("#beginTime").jeDate({
            isTime: true,
            format: "YYYY-MM-DD hh:mm:ss",
            minDate: "2014-09-19 00:00:00",
        });
        $("#endTime").jeDate({
            isTime: true,
            format: "YYYY-MM-DD hh:mm:ss",
            minDate: "2014-09-19 00:00:00",
        })

        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "appId": {
                    required: true
                },
                "name": {
                    required: true
                },
                "beginTime": {
                    required: true
                },
                "endTime": {
                    required: true
                },
                "pictureId": {
                    required: true
                },
                "maxCount": {
                    required: true
                },
                "info": {
                    required: true
                }
            },
            messages: {
                "appId": '请输入应用id',
                "name": '请输入抽奖池名称',
                "beginTime": '请选择活动开始时间',
                "endTime": '请选择活动结束时间',
                "pictureId": '请选择背景图片',
                "maxCount": '请输入最大次数',
                "info": '请输入活动介绍'
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