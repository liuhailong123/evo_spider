<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form" enctype="application/x-www-form-urlencoded" method="post">

            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>

            <div class="form-group">
                <label class="col-lg-3 control-label">价格优惠名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">价格优惠编码:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="code" name="code" value="${entity.code}" readonly/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">类型:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="PriceDiscountType" var="type">
                        <x:select items="${type }" var="type" name="type" id="type"
                                  className="form-control">
                            <x:option selected="${entity.type eq type.code}" value="${type.code }"
                                      text="${type.name }"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">目标值:</label><span class="text-danger">*</span>
                <div class="col-lg-5">
                    <input min="0" type="number" class="form-control" id="targetValue" name="targetValue"
                           value="${entity.targetValue}"/>
                </div>
                <div class="col-lg-3">
                    <span class="form-control">元</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">优惠值:</label><span class="text-danger">*</span>
                <div class="col-lg-5">
                    <input min="0" type="number" class="form-control" id="discountValue" name="discountValue"
                           value="${entity.discountValue}"/>
                </div>
                <div class="col-lg-3">
                    <span class="form-control" id="discountValueExt">元</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">累积触发:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="hidden" id="isMore" name="isMore" value="${entity.isMore}"/>
                    <x:dictData dictCode="IsMore" var="isMore">
                        <x:select items="${isMore }" var="isMore" name="isMoreSelect" id="isMoreSelect"
                                  className="form-control">
                            <x:option selected="${entity.isMore eq isMore.code}" value="${isMore.code }"
                                      text="${isMore.name }"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">优先级:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" class="form-control" id="priority" name="priority"
                           value="${entity.priority}" placeholder="该值越大，优先级越高"/>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    jQuery(function ($) {
        // 根据价格优惠类型设置累积触发
        if ($("#type").val() != 1) {
            $("#isMoreSelect").val("0");
            $("#isMoreSelect").attr("disabled", "disabled");
            if ($("#type").val() == 3) {
                $("#discountValueExt").text("元");
            } else if ($("#type").val() == 2) {
                $("#discountValueExt").text("%");
            }
        }


        $("#type").change(function () {
            var type = $(this).val()
            if (type == 1) {
                $("#isMoreSelect").removeAttr("disabled");
                $("#discountValueExt").text("元");
            } else {
                $("#isMore").val("0");
                $("#isMoreSelect").val("0");
                $("#isMoreSelect").attr("disabled", "disabled");
                if (type == 2) {
                    $("#discountValueExt").text("%");
                } else if (type == 3) {
                    $("#discountValueExt").text("元");
                }
            }
        });

        $("#isMoreSelect").change(function () {
            var val = $(this).val();
            $("#isMore").val(val);
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
                    "targetValue": {
                        required: true
                    },
                    "discountValue": {
                        required: true
                    },
                    "priority": {
                        required: true
                    },
                },
                messages: {
                    "name": '请输入名称',
                    "targetValue": '请输入目标值',
                    "discountValue": '请输入优惠值',
                    "priority": '请输入优先级',
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