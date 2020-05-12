<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="col-lg-6">
                <div class="form-group">
                    <label class="col-lg-3 control-label">类型:</label>
                    <div class="col-lg-8">
                        <x:dictData dictCode="Limit_Free_Type" var="limitFreeType">
                            <x:select defaultOption="请选择" hasDefault="false" var="limitFreeType"
                                      items="${limitFreeType}"
                                      id="type" name="type" className="form-control">
                                <x:option value="${limitFreeType.code }" text="${limitFreeType.name }"
                                          selected="${entity.type eq limitFreeType.code}"></x:option>
                            </x:select>
                        </x:dictData>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">开始时间:</label><span class="text-danger">*</span>
                    <div class="col-lg-8">
                        <input type="text" readonly="readonly" class="form-control" id="beginFreeTime"
                               name="beginFreeTime"
                               value="${entity.beginFreeTime}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">结束时间:</label><span class="text-danger">*</span>
                    <div class="col-lg-8">
                        <input type="text" readonly="readonly" class="form-control" id="endFreeTime" name="endFreeTime"
                               value="${entity.endFreeTime}"/>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="form-group">
                    <label class="col-lg-3 control-label">所属应用:</label><span class="text-danger">*</span>
                    <div class="col-lg-6">
                        <input type="hidden" id="appId" name="appId" value="${entity.appId}"/>
                        <input type="text" readonly="readonly" class="form-control" id="appName" name="appName"
                               value="${entity.appName}"/>
                    </div>
                    <div class="col-lg-2">
                        <input id="selectApp" type="button" value="选择" onclick="selectAppF();"
                               class="btn btn-primary btn-sm form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">所属栏目:</label>
                    <div class="col-lg-6">
                        <input type="hidden" id="columnId" name="columnId" value="${entity.columnId}"/>
                        <input type="text" readonly="readonly" class="form-control" id="columnName" name="columnName"
                               value="${entity.columnName}"/>
                    </div>
                    <div class="col-lg-2">
                        <input id="selectColumn" type="button" value="选择" onclick="selectColumnF();"
                               class="btn btn-primary btn-sm form-control" disabled="disabled"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    /**
     * 选择应用
     */
    function selectAppF() {
        showSelectDlg("选择", contextPath + "/columnManage/column/select", ["Select_table-data"],
            function (obj) {
                $("#appId").val(obj[0].id);
                $("#appName").val(obj[0].name);
            }, BootstrapDialog.SIZE_WIDE);
    }

    /**
     * 选择栏目
     */
    function selectColumnF() {
        showSelectDlg("选择", contextPath + "/columnManage/column/select", ["Select_table-data"],
            function (obj) {
                $("#columnId").val(obj[0].id);
                $("#columnName").val(obj[0].name);
            }, BootstrapDialog.SIZE_WIDE);
    }

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

        $("#type").on("change", function () {
            // 去除栏目、内容校验
            $("#columnName").rules("remove", "required");
            $("#selectColumn").attr("disabled", "disables");
            if ($(this).val() == 1) {
                // 无操作
            } else if ($(this).val() == 2) {
                // 增加栏目校验、
                $("#columnName").rules("add", "required");
                $("#selectColumn").removeAttr("disabled");
            } else if ($(this).val() == 3) {
                $("#type").val(1);
                // 增加内容校验
                CmMsg.warn("内容限免，请在站点维护菜单下添加!!!", -1);
            } else {
                CmMsg.warn("类型错误", -1);
            }
        })

        $('#formDlg').validate(
            {
                errorElement: 'span',
                errorClass: 'help-block',
                focusCleanup: false,
                focusInvalid: false,
                onsubmit: false,
                rules: {
                    "name": {
                        required: true
                    },
                    "code": {
                        required: true
                    },
                    "beginFreeTime": {
                        required: true
                    },
                    "endFreeTime": {
                        required: true
                    },
                    "appName": {
                        required: true
                    }
                },
                messages: {
                    "name": '请输入名称',
                    "code": '请输入编码',
                    "beginFreeTime": '请选择开始限免开始时间',
                    "coendFreeTimede": '请选择开始限免结束时间',
                    "appName": '请选择所属应用',
                    "columnName": '请选择所属栏目',
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