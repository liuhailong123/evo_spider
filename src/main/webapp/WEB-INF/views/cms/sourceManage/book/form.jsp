<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form">
        <input type="hidden" id="id" name="id" value="${entity.id}"/>
        <input type="hidden" id="clcClassify" name="clcClassify" value="${entity.clcClassify}"/>
        <input type="hidden" id="createDate" name="createDate"
               value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">书号:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="number" name="number"
                           value="${entity.number}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">书名:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">作者:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="author" name="author"
                           value="${entity.author}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">编辑:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="engineer" name="engineer"
                           value="${entity.engineer}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">分类:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="smallClassify" name="smallClassify"
                           value="${entity.smallClassify}"/>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">简介:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <textarea id="info" name="info" class="form-control" >${entity.info}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">适合年龄段:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="ageTag" name="ageTag" value="${entity.ageTag}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">适用群体:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="suitGroup" name="suitGroup" value="${entity.suitGroup}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">出版社:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="supplier" name="supplier" class="form-control" value="${entity.supplier}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">出版日期:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <input type="text" class="form-control" id="publishDate" name="publishDate"
                           value="${entity.publishDate}"/>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $('#smallClassify').tagsinput();//细分类别
    /**
     * 日期选择
     */
    $("#publishDate").jeDate({
        isTime:true,
        format: "YYYY-MM-DD"
        // ,
        // minDate:"2014-09-19"

    });
    jQuery(function ($) {

        $('#ageTag').tagsinput();//适合年龄段
        $('#suitGroup').tagsinput();//适用群体

        // 页面校验
        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "number": {required: true},
                "name": {required: true},
                "author": {required: true},
                "publishDate": {required: true},
                "smallClassify": {required: true}
            },
            messages: {
                "number": '请输入书号',
                "name": '请输入书名',
                "author": '请输入作者',
                "publishDate": '请选择出版日期',
                "smallClassify": '请输入分类'
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


    });
</script>