<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../include.inc.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
			<input type="hidden" id="id" name="id" value="${entity.id}" /> 
			<input type="hidden" id="fallTemplateId" name="fallTemplate.id" value="${entity.fallTemplate.id}" /> 
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				
			<div class="form-group">
				<label class="col-lg-3 control-label">位置:</label>
				<span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="number" min="1" class="form-control" id="position" name="position" value="${entity.position}" placeholder="1"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">占行数:</label>
				<span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="number" min="1" max="999" class="form-control" id="positionX" name="positionX" value="${entity.positionX}" placeholder="1"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">占列数:</label>
				<span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="number" min="1" max="999" class="form-control" id="positionY" name="positionY" value="${entity.positionY}" placeholder="1"/>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">

	jQuery(function($) {
		$('#formDlg').validate(
				{
					errorElement : 'span',
					errorClass : 'help-block',
					focusCleanup : false,
					focusInvalid : false,
					onsubmit : false,
					rules : {
						"position" : {min : true},
						"positionX" : {min : true},
						"positionY" : {min : true}
					},
					messages : {
						"position" : '请输入正确位置',
						"positionX" : '请输入正确占行数',
						"positionY" : '请输入正确占列数'
					},
					highlight : function(e) {
						$(e).closest('.form-group').removeClass('has-info')
								.addClass('has-error');
					},
					success : function(e) {
						$(e).closest('.form-group').removeClass('has-error')
								.addClass('has-success');
						$(e).remove();
					},
				});
	});
</script>