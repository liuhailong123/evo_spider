<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="cardNo" name="cardNo" value="${entity.cardNo}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="form-group">
                <label class="col-lg-3 control-label">应用id:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="appId" name="appId" value="${entity.appId}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">用户id:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="userId" name="userId" value="${entity.userId}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">中奖商品id:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly"  id="drawChildId" name="drawChildId"
                           value="${entity.drawChildId}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否中奖:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="isNeedOrder" var="isOwn">
                        <x:select var="isOwn" items="${isOwn}" id="isOwn" name="isOwn"
                                  className="form-control">
                            <x:option value="${isOwn.code }" text="${isOwn.name }"
                                      selected="${entity.isOwn eq isOwn.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否发货:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="isNeedOrder" var="isSend">
                        <x:select var="isSend" items="${isSend}" id="isSend" name="isSend"
                                  className="form-control">
                            <x:option value="${isSend.code }" text="${isSend.name }"
                                      selected="${entity.isSend eq isSend.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">收货人手机号:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="phone" name="phone" rows="5">${entity.phone}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">收货地址:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="address" name="address" rows="5">${entity.address}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">物流单号:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <textarea class="form-control" id="orderNo" name="orderNo" rows="5">${entity.orderNo}</textarea>
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
                "appId": {
                    required: true
                },
                "phone": {
                    required: true
                },
                "address": {
                    required: true
                },
                "orderNo": {
                    required: true
                }
            },
            messages: {
                "appId": '请输入应用id',
                "phone": '请输入收货手机号',
                "address": '请输入收货地址',
                "orderNo": '请输入快递单号',
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