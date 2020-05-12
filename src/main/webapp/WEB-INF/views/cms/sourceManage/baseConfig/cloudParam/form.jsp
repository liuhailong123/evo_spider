<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="form-group">
				<label class="col-lg-3 control-label">腾讯云appID:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="appId" name="appId" value="${entity.appId}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">空间名称:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="bucketName" name="bucketName" value="${entity.bucketName}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">秘钥ID:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="secretId" name="secretId" value="${entity.secretId}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">秘钥KEY:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<textarea type="text" class="form-control" id="secretKey" name="secretKey" >${entity.secretKey}</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">区域:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<textarea type="text" class="form-control" id="cosregion" name="cosregion" >${entity.cosregion}</textarea>
				</div>
			</div>
		</form>
	</div>
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
			"appId" : {
				required : true
			},
			"bucketName" : {
				required : true
			},
			"secretId" : {
				required : true
			},
			"secretKey" : {
				required : true
			},
			"cosregion" : {
				required : true
			}
		},
		messages : {
			"appId" : '请输入腾讯云appID',
			"bucketName" : '请输空间名称',
			"secretId" : '请输入秘钥ID',
			"secretKey" : '请输入秘钥KEY',
			"cosregion" : '请输入区域',
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