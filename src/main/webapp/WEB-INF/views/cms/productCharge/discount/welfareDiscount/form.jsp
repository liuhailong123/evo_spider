<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">

            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>

            <div class="form-group">
                <label class="col-lg-3 control-label">福利优惠名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">福利优惠编码:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="code" name="code" value="${entity.code}" readonly/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">类型:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="WelfareDiscountType" var="type">
                        <x:select items="${type }" var="type" name="type" id="type"
                                  className="form-control">
                            <x:option selected="${entity.type eq type.code}" value="${type.code }"
                                      text="${type.name }"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">商品id:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="goodsId" name="goodsId"
                           value="${entity.goodsId}"/>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    jQuery(function ($) {
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
                    "goodsId": {
                        required: true
                    },
                },
                messages: {
                    "name": '请输入名称',
                    "goodsId": '请输入商品id',
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