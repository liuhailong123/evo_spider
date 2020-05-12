<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg2" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="bizId" name="bizId" value="${bizId}"/>
            <input type="hidden" id="type" name="type" value="${type}"/>
            <input type="hidden" id="appId" name="appId" value="${appId}"/>
            <input type="hidden" id="columnId" name="columnId" value="${columnId}"/>
            <input type="hidden" id="contentId" name="contentId" value="${contentId}"/>
            <input type="hidden" id="contentType" name="contentType" value="${contentType}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="col-lg-6">
                <div class="form-group">
                    <label class="col-lg-3 control-label">开始时间:</label><span class="text-danger">*</span>
                    <div class="col-lg-8">
                        <input type="text" readonly="readonly" class="form-control" id="beginFreeTime"
                               name="beginFreeTime"
                               value="${entity.beginFreeTime}"/>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="form-group">
                    <label class="col-lg-3 control-label">结束时间:</label><span class="text-danger">*</span>
                    <div class="col-lg-8">
                        <input type="text" readonly="readonly" class="form-control" id="endFreeTime" name="endFreeTime"
                               value="${entity.endFreeTime}"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    jQuery(function ($) {
        $("#beginFreeTime").jeDate({
            isTime: true,
            format: "YYYY-MM-DD hh:mm:ss",
            minDate: "2014-09-19 00:00:00",
        });
        $("#endFreeTime").jeDate({
            isTime: true,
            format: "YYYY-MM-DD hh:mm:ss",
            minDate: "2014-09-19 00:00:00",
        })


        $('#formDlg2').validate(
            {
                errorElement: 'span',
                errorClass: 'help-block',
                focusCleanup: false,
                focusInvalid: false,
                onsubmit: false,
                rules: {
                    "beginFreeTime": {
                        required: true
                    },
                    "endFreeTime": {
                        required: true
                    },
                },
                messages: {
                    "beginFreeTime": '请选择开始限免开始时间',
                    "coendFreeTimede": '请选择开始限免结束时间',
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