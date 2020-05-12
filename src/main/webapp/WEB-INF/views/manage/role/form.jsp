<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${role.id}" />
			<input type="hidden" id="organizationId" name="organization.id" value="${role.organization.id}" />
			<div class="form-group">
				<label class="col-lg-2 control-label">组织机构:</label>
				<div class="col-lg-9">
					<span class="block input-icon input-icon-right"> <input
						readonly type="text" class="form-control" id="organizationName"
						name="organization.name" value="${role.organization.name}" />
						<i class="ace-icon fa fa-cogs"></i>
					</span>
					<div id="orgTree" class="selTree">
						<div id="selectOrgTree"></div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">角色名称:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="name" name="name"
						value="${role.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">角色编码:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="code" name="code"
						value="${role.code}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">角色描述:</label>
				<div class="col-lg-9">
					<textarea class="form-control" id="description" name="description">${role.description}</textarea>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	var ajaxTreeUrl = contextPath + '/manage/organization/tree';

	function hideOrgTree() {
		$("#orgTree").fadeOut("fast");
		$(".modal-dialog").unbind("mousedown", onBodyDown);
	}
	
	function onBodyDown(event) {
		if (!($(event.target).hasClass('selTree') || $(event.target).parents(".selTree").length > 0)) {
			hideOrgTree();
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
			var selectObj = $("#organizationName");
			$("#orgTree").css({ width : selectObj.outerWidth() + "px" }).slideDown("fast");
			$(".modal-dialog").bind("mousedown", onBodyDown);
		});
		
		initTree("selectOrgTree", ajaxTreeUrl, false, {
			selNode : function(node, elem) {
				var orgIdVal = node.id == '0' ? null : node.id;
				if(orgIdVal == null){
					CmMsg.warn("请选择一个有效机构", -1);
					return;
				}
				$('#formDlg').find("#organizationId").val(orgIdVal);
				$('#formDlg').find("#organizationName").val(node.text);
				hideOrgTree();
			}
		});
		
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
						"name" : '请输入角色名称',
						"code" : '请输入角色编码',
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