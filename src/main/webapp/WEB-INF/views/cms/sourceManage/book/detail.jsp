<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
    <div id="formDlg" class="form-horizontal">
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">书号:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="number" name="number"
                           value="${entity.number}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">书名:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="name" name="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">作者:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="author" name="author"
                           value="${entity.author}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-lg-3 control-label">中图法分类:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="clcClassify" name="clcClassify"
                           value="${entity.clcClassify}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">编辑:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="engineer" name="engineer"
                           value="${entity.engineer}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">细分类别:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="smallClassify" name="smallClassify"
                           value="${entity.smallClassify} "/>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="col-lg-3 control-label">简介:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <textarea id="info" name="info" class="form-control" readonly="readonly" value="${entity.info}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">适合年龄段:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="ageTag" name="ageTag" readonly="readonly" value="${entity.ageTag}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">适用群体:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="suitGroup" name="suitGroup" readonly="readonly" value="${entity.suitGroup}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">供应商:</label>
                <!-- <span class="text-danger">*</span> -->
                <div class="col-lg-8">
                    <input type="text" id="supplier" name="supplier" class="form-control" readonly="readonly" value="${entity.supplier}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">出版日期:</label>
                <div class="col-lg-8">
                    <input type="text" class="form-control" readonly="readonly" id="publishDate" name="publishDate"
                           value="${entity.publishDate}"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function ($) {

        /**
         * 日期选择
         */
        $("#publishDate").jeDate({
            isTime:true,
            format: "YYYY-MM-DD"
            // ,
            // minDate:"2014-09-19"

        });
        $('#clcClassify').tagsinput();//中图法分类
        $('#smallClassify').tagsinput();//细分类别
        $('#ageTag').tagsinput();//适合年龄段
        $('#suitGroup').tagsinput();//适用群体

        // 去除tag控件的多余样式
        $(".tt-hint").hide();
        $(".bootstrap-tagsinput").addClass("col-lg-12");

        $("#clcClassify").attr("disabled","disabled");
        $("#smallClassify").attr("disabled","disabled");
        $("#ageTag").attr("disabled","disabled");
        $("#suitGroup").attr("disabled","disabled");
    });
</script>