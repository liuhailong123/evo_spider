<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp" %>
<form id="frmPwd" class="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-lg-3 control-label">旧密码:</label>
		<div class="col-lg-9">
			<input type="password" class="form-control" id="plainPassword" name ="plainPassword" value=""/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">新密码:</label>
		<div class="col-lg-9">
			<input type="password" class="form-control" id="newPassword" name ="newPassword" value=""/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">确认新密码:</label>
		<div class="col-lg-9">
			<input type="password" class="form-control" id="confirm_password" name = "confirm_password" value=""/>
		</div>
	</div>
</form>
<script type="text/javascript">
	jQuery(function($){
		$('#frmPwd').validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusCleanup: false,
			onsubmit: false,
			rules: {
				plainPassword: "required",
				newPassword: {
					required:true,
					minlength: 6
				},
				confirm_password: {
			        required: true,
			        minlength: 5,
			        equalTo: "#newPassword"
				}
			},
			messages:{
				plainPassword:'请输入旧密码',
				newPassword: {
					required : '请输入新密码',
					maxlength: '密码长度不能小于 6 位'
				},
				confirm_password: {
			        required: '请输入确认密码',
			        minlength: '密码长度不能小于 6 位',
			        equalTo: "两次密码输入不一致"
				}
			},
			highlight: function (e) {
				$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
			},
			success: function (e) {
				$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
				$(e).remove();
			},
		});
	});
</script>