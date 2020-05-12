<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${account.id}" />
			<input type="hidden" id="organizationId" name="organization.id" value="${account.organization.id}" />
			<div class="form-group">
				<label class="col-lg-2 control-label">隶属部门:</label>
				<div class="col-lg-9">
					<span class="block input-icon input-icon-right"> <input
						readonly type="text" class="form-control" id="organizationName"
						name="organization.name" value="${account.organization.name}" /> <i
						class="ace-icon fa fa-cogs"></i>
					</span>
					<div class="selTree">
						<div id="selectTree"></div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">账号:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="username" name="username" value="${account.username}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">姓名:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="realname" name="realname" value="${account.realname}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">邮箱:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="email" name="email" value="${account.email}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">手机:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="mobile" name="mobile" value="${account.mobile}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">状态:</label> <span class="text-danger">*</span>
				<div class="col-lg-9">
					<x:dictData dictCode="Lock_Status" var="lockStatuses">
						<x:select var="status" items="${lockStatuses}" id="status" name="status" className="form-control">
							<x:option value="${status.code }" text="${status.name }" selected="${account.status eq status.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">类型:</label> <span class="text-danger">*</span>
				<div class="col-lg-9">
					<x:dictData dictCode="Account_Type" var="accountTypes">
						<x:select var="aTypes" items="${accountTypes}" id="superman" name="superman" className="form-control">
							<x:option value="${aTypes.code }" text="${aTypes.name }" selected="${account.superman eq aTypes.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	var ajaxTreeUrl = contextPath + '/manage/organization/tree';
	
	function hideTree() {
		$(".selTree").fadeOut("fast");
		$(".modal-dialog").unbind("mousedown", onBodyDown);
	}

	function onBodyDown(event) {
		if (!($(event.target).hasClass('selTree') || $(event.target).parents(".selTree").length > 0)) {
			hideTree();
		}
	}
	
	jQuery(function($) {
		// 初始化树选中
		var id = $('#formDlg').find('#id').val();
		if (id == "") {
			var $JsTree = $.jstree.reference("treeview");
			var nodes = $JsTree.get_selected(true);
			if (nodes != null && nodes.length > 0) {
				$('#formDlg').find("#organizationId").val(nodes[0].id);
				$('#formDlg').find("#organizationName").val(nodes[0].text);
			}
		}

		$('#formDlg').find('#organizationName').on('click', function() {
			var selectObj = $(this);
			$(".selTree").css({ width : selectObj.outerWidth() + "px" }).slideDown("fast");
			$(".modal-dialog").bind("mousedown", onBodyDown);
		});

		initTree("selectTree", ajaxTreeUrl, false, {
			selNode : function(node, elem) {
				$('#formDlg').find("#organizationId").val(node.id);
				$('#formDlg').find("#organizationName").val(node.text);
				hideTree();
			}
		});
		var accountSM = "${LOGINUSER.superman}";
		var superMan = "${SUPERMAN}";
		if(superMan !== accountSM){
			$("#superman option[value='0']").remove();
		}
		
		$('#formDlg').validate(
				{
					errorElement : 'span',
					errorClass : 'help-block',
					focusCleanup : false,
					focusInvalid : false,
					onsubmit : false,
					rules : {
						"organization.name" : {
							required : true
						},
						"username" : {
							required : true
						},
						"realname" : {
							required : true
						},
						"email" : {
							required : true
						},
						"mobile" : {
							required : true
						}
					},
					messages : {
						"organization.name" : '请选择所在部门',
						"username" : '请输账号',
						"realname" : '请输真实姓名',
						"email" : '请输入邮箱地址',
						"mobile" : '请输入手机号码',
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