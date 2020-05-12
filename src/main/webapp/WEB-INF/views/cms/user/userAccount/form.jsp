<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
	<form id="formDlg" class="form-horizontal" role="form">
		<div class="col-lg-12">
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="userId" name="userId" value="${entity.userId}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			
			<div class="form-group">
				<label class="col-lg-3 control-label">账号类型:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
                    <x:dictData dictCode="User_Account_Type" var="AccountType">
                        <x:select defaultOption="请选择" hasDefault="true" var="AccountType" items="${AccountType}" id="accountType" name="accountType" className="form-control">
                            <x:option value="${AccountType.code }" text="${AccountType.name }" selected="${entity.accountType eq AccountType.code}"></x:option>
                        </x:select>
                    </x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">账号:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="accountNo" name="accountNo" value="${entity.accountNo}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">设备名称:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="equipmentName" name="equipmentName" value="${entity.equipmentName}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">设备标识:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="equipmentId" name="equipmentId" value="${entity.equipmentId}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">设备类型:</label>
				<div class="col-lg-8">
                    <x:dictData dictCode="Equipment_Type" var="EquipmentType">
                        <x:select defaultOption="请选择" hasDefault="true" var="EquipmentType" items="${EquipmentType}" id="equipmentType" name="equipmentType" className="form-control">
                            <x:option value="${EquipmentType.code }" text="${EquipmentType.name }" selected="${entity.equipmentType eq EquipmentType.code}"></x:option>
                        </x:select>
                    </x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">产品编码:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="productCode" name="productCode" value="${entity.productCode}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">渠道编码:</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="channelCode" name="channelCode"  value="${entity.channelCode}" />
				</div>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
jQuery(function($) {
	$('#formDlg').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusCleanup : false,
		focusInvalid : false,
		onsubmit : false,
		rules : {
			"accountType" : {
				required : true
			},
			"accountNo" : {
				required : true
			}
		},
		messages : {
			"accountType" : '请选择账号类型',
			"accountNo" : '请输入账号'
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