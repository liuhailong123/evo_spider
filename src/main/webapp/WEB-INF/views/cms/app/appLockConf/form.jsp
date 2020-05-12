<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="appId" name="appId" value="${entity.appId}" />
			<input type="hidden" id="contentId" name="contentId" value="${entity.contentId}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />

			<div class="form-group">
				<label class="col-lg-3 control-label">加锁内容类型:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<x:dictData dictCode="Lock_Content_Type" var="type">
						<x:select items="${type }" var="type" name="contentType" id="contentType" className="form-control">
							<x:option selected="${entity.contentType eq type.code}" value="${type.code }" text="${type.name }"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>

			<div class="form-group">
				<label class="col-lg-3 control-label">加锁内容:</label><span class="text-danger">*</span>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="contentName" name="contentName" value="${contentName }" readonly="readonly"/>
				</div>
				<div class="col-lg-2">
					<input id="Select_btnSearch" name="Select_btnSearch" type="button" value="选择"  onclick="selectContent()" class="btn btn-primary btn-sm form-control">
					</input>
				</div>
			</div>

			<div class="form-group">
				<label class="col-lg-3 control-label">是否默认加锁:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<x:dictData dictCode="OFF_ON" var="type">
						<x:select items="${type }" var="type" name="status" id="status" className="form-control">
							<x:option selected="${entity.status eq type.code}" value="${type.code }" text="${type.name }"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>

		</form>
	</div>
</div>
<script type="text/javascript">

    function selectContent() {
        var contentType=$("#contentType").val();
        if(contentType==1){//栏目
            showSelectDlg("选择栏目", contextPath + '/columnManage/column/select',[ "Select_table-data" ],
                selectContentCallBack,BootstrapDialog.SIZE_WIDE);
		}
    }

    function selectContentCallBack(obj) {
        $("#contentId").val(obj[0].id);
        $("#contentName").val(obj[0].name);
    }

jQuery(function($) {

	$('#formDlg').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusCleanup : false,
		focusInvalid : false,
		onsubmit : false,
		rules : {
			"contentType" : {
				required : true
			},
			"contentId" : {
				required : true
			},
			"status" : {
				required : true
			}
		},
		messages : {
			"contentType" : '请选择加锁内容类型',
			"contentId" : '请选择加锁内容',
			"status" : '请选择是否默认加锁'
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