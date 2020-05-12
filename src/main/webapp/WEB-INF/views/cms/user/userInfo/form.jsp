<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
	<form id="formDlg" class="form-horizontal" role="form">
		<div class="col-lg-6">
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="password" name="password" value="${entity.password}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			
			<div class="form-group">
				<label class="col-lg-3 control-label">姓名:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">手机号:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="phone" name="phone" value="${entity.phone}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">身份证号:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="idCard" name="idCard" value="${entity.idCard}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">昵称:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="nick" name="nick" value="${entity.nick}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">微信号:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="wxNum" name="wxNum" value="${entity.wxNum}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">QQ号:</label>
				<div class="col-lg-8">
					<input type="number" class="form-control" id="qqNum" name="qqNum" value="${entity.qqNum}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">生日:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="brithday" name="brithday" placeholder="请选择" value="${entity.brithday}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">身高:</label>
				<div class="col-lg-8">
					<input type="number" class="form-control" id="height" name="height" placeholder="CM" value="${entity.height}" />
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="form-group">
				<label class="col-lg-3 control-label">微信OpenId:</label>
				<div class="col-lg-8">
					<input type="number" class="form-control" id="openId" name="openId" value="${entity.openId}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">体重:</label>
				<div class="col-lg-8">
					<input type="number" class="form-control" id="weight" name="weight" placeholder="KG" value="${entity.weight}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">民族:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="nation" name="nation" value="${entity.nation}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">微博号:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="wbNum" name="wbNum" value="${entity.wbNum}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">邮箱:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="email" name="email" value="${entity.email}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">性别:</label>
				<div class="col-lg-8">
                    <x:dictData dictCode="Sex_Type" var="sexType">
                        <x:select defaultOption="请选择" hasDefault="true" var="sexType" items="${sexType}"
                                  id="sex" name="sex" className="form-control">
                            <x:option value="${sexType.code }" text="${sexType.name }"
                                      selected="${entity.sex eq sexType.code}"></x:option>
                        </x:select>
                    </x:dictData>
				</div>
			</div>
            <div class="form-group">
                <label class="col-lg-3 control-label">状态:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="User_Status" var="userStatus">
                        <x:select defaultOption="请选择" hasDefault="true" var="userStatus" items="${userStatus}"
                                  id="status" name="status" className="form-control">
                            <x:option value="${userStatus.code }" text="${userStatus.name }"
                                      selected="${entity.status eq userStatus.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
		</div>
	</form>
</div>
<script type="text/javascript">
jQuery(function($) {
	
	/**
     * 日期选择
     */
    $("#brithday").jeDate({
        isTime:false,
        format: "YYYY-MM-DD",
        minDate:"1900-01-01"
    });
	
	
	$('#formDlg').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusCleanup : false,
		focusInvalid : false,
		onsubmit : false,
		rules : {
			"passwd" : {
				required : true
			},
// 			"phone" : {
// 				required : true
// 			},
			"status" : {
				required : true
			}
		},
		messages : {
			"passwd" : '请输入密码',
// 			"phone" : '请输入手机号',
			"status" : '请选择用户状态'
		},
		highlight : function(e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},
		success : function(e) {
			$(e).closest('.form-group').removeClass('has-error').addClass('has-success');
			$(e).remove();
		},
	});	
});
</script>