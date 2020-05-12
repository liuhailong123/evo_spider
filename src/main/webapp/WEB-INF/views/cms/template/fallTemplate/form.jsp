<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
			<input type="hidden" id="id" name="id" value="${entity.id}" /> 
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				
			<div class="form-group">
				<label class="col-lg-3 control-label">名称:</label>
				<span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">简介:</label>
				<div class="col-lg-8">
					<textarea type="text" class="form-control" id="info" name="info">${entity.info}</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">位置总数:</label>
				<span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="number" min="1" max="999" class="form-control" id="count" name="count" value="${entity.count}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">预览图:</label>
				<div class="col-lg-6">
					<textarea class="form-control"  id="imgUrl" name="imgUrl">${entity.imgUrl}</textarea>
				</div>
				<div class="col-lg-2">
					<input id="Select_btnSearch" name="Select_btnSearch" type="button" value="选择"  onclick="selectImage()" class="btn btn-primary btn-sm form-control">
					</input>
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
    $("#imgUrl").val(obj[0].url);
}

	jQuery(function($) {
		$('#formDlg').validate(
				{
					errorElement : 'span',
					errorClass : 'help-block',
					focusCleanup : false,
					focusInvalid : false,
					onsubmit : false,
					rules : {
						"name" : {required : true},
						"count" : {required : true}
					},
					messages : {
						"name" : '请输入模版名称',
						"count" : '请输入模版位总数'
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