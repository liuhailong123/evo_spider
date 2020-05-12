<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="drawId" name="drawId" value="${drawId}"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <div class="form-group">
                <label class="col-lg-3 control-label">奖品名称:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
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
                <label class="col-lg-3 control-label">奖品数量:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="0" class="form-control" id="count" name="count" value="${entity.count}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">剩余数量:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="0" class="form-control" id="nowCount" name="nowCount" value="${entity.nowCount}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">中奖概率(%):</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="0" class="form-control" id="probability" name="probability"
                           value="${entity.probability}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">排序:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="number" min="1" class="form-control" id="sort" name="sort" value="${entity.sort}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否有效奖项:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="isNeedOrder" var="isEffective">
                        <x:select defaultOption="请选择" hasDefault="false" var="isEffective" items="${isEffective}"
                                  id="isEffective" name="isEffective" className="form-control">
                            <x:option value="${isEffective.code }" text="${isEffective.name }"
                                      selected="${entity.isEffective eq isEffective.code}"></x:option>
                        </x:select>
                    </x:dictData>
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
        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "name": {
                    required: true
                },
                "count": {
                    required: true
                },
                "probability": {
                    required: true
                },
                "sort": {
                    required: true
                },
                "pictureId": {
                    required: true
                },
            },
            messages: {
                "name": '请输入奖品名称',
                "count": '请输入奖品数量',
                "probability": '请输入中奖概率',
                "sort": '请输入奖品排序',
                "pictureId": '请选择背景图片',
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