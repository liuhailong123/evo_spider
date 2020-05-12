<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${classify.id}" />
			<div class="form-group">
				<label class="col-lg-2 control-label">分类名称:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="name" name="name"
						value="${classify.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">分类编码:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="code" name="code"
						value="${classify.code}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">分类描述:</label>
				<div class="col-lg-9">
					<textarea class="form-control" id="description" name="description">${classify.description}</textarea>
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
						"name" : {
							required : true
						},
						"code" : {
							required : true
						}
					},
					messages : {
						"name" : '请输入分类名称',
						"code" : '请输入分类编码',
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