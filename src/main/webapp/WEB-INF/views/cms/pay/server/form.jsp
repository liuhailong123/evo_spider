<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
		
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			
			<div class="form-group">
				<label class="col-lg-3 control-label">名称:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">编码:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="code" name="code" value="${entity.code}" />
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
				"name" : '请输入名称',
                "code" : '请输入编码'
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
		}
	);
});


</script>














