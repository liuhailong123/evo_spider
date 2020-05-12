<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${entity.id}" /> 
			<input type="hidden" id="level" name="level" value="${entity.level}" /> 
			<input type="hidden" id="parentId" name="parent.id" value="${entity.parent.id}" />
			<input type="hidden" id="areaId" name="area.id" value="${entity.area.id}" />
			<div class="form-group">
				<label class="col-lg-2 control-label">上级渠道:</label>
				<div class="col-lg-9">
					<span class="block input-icon input-icon-right"> 
						<input readonly type="text" class="form-control" id="parentName"
							name="parent.name" value="${empty entity.parent ? '所有渠道' : entity.parent.name}" /> 
						<i class="ace-icon fa fa-cogs"></i>
					</span>
					<div id="orgTree" class="selTree">
						<div id="selectOrgTree"></div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">所在区域:</label>
				<div class="col-lg-9">
					<span class="block input-icon input-icon-right"> 
						<input readonly type="text" class="form-control" id="areaName"
							name="area.name" value="${entity.area.name}" /> 
						<i class="ace-icon fa fa-cogs"></i>
					</span>
					<div id="areaTree" class="selTree">
						<div id="selectAreaTree"></div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">名称:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">渠道编码:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="code" name="code" value="${entity.code}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">优先级:</label> <span class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="number" min="1" class="form-control" id="priority" name="priority" value="${entity.priority}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">描述:</label>
				<div class="col-lg-9">
					<textarea class="form-control" id="description" name="description">${entity.description}</textarea>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	var ajaxTreeUrl = contextPath + '/productCharge/productChannel/tree';
	var ajaxAreaTreeUrl = contextPath + '/manage/area/tree';
	
	function hideOrgTree() {
		$("#orgTree").fadeOut("fast");
		$(".modal-dialog").unbind("mousedown", onBodyDown);
	}
	
	function hideAreaTree() {
		$("#areaTree").fadeOut("fast");
		$(".modal-dialog").unbind("mousedown", onBodyDown);
	}

	function onBodyDown(event) {
		if (!($(event.target).hasClass('selTree') || $(event.target).parents(".selTree").length > 0)) {
			hideOrgTree();
			hideAreaTree();
		}
	}
	
	jQuery(function($) {
		// 初始化树选中
		var id = $('#id').val();
		if (id == "") {
			var $JsTree = $.jstree.reference("treeview");
			var nodes = $JsTree.get_selected(true);
			console.log(nodes);
			if (nodes != null && nodes.length > 0) {
				$("#parentId").val(nodes[0].id == '0' ? null : nodes[0].id);
				$("#parentName").val(nodes[0].text);
				$("#level").val(parseInt(nodes[0].original.level) + 1);
			}
		}

		$('#parentName').on('click', function() {
			var selectObj = $("#parentName");
			$("#orgTree").css({ width : selectObj.outerWidth() + "px" }).slideDown("fast");
			$(".modal-dialog").bind("mousedown", onBodyDown);
		});
		
		$('#areaName').on('click', function() {
			var selectObj = $("#areaName");
			$("#areaTree").css({ width : selectObj.outerWidth() + "px" }).slideDown("fast");
			$(".modal-dialog").bind("mousedown", onBodyDown);
		});

		initTree("selectOrgTree", ajaxTreeUrl, false, {
			selNode : function(node, elem) {
				var parentIdVal = node.id == '0' ? null : node.id;
				if(id != "" && id == parentIdVal){
					CmMsg.warn("不可以选择自己做为上级机构", -1);
					return;
				}
				$("#parentId").val(parentIdVal);
				$("#parentName").val(node.text);
				$("#level").val(parseInt(node.original.level) + 1);
				hideOrgTree();
			}
		});
		
		initTree("selectAreaTree", ajaxAreaTreeUrl, false, {
			selNode : function(node, elem) {
				if(node.id == '0'){
					CmMsg.warn("请选择一个有效区域", -1);
					return;
				}
				$("#areaId").val(node.id);
				$("#areaName").val(node.text);
				hideAreaTree();
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
						"priority" : {
							required : true
						},
						"code" : {
							required : true
						}
					},
					messages : {
						"name" : '请输入名称',
						"priority" : '请输入优先级',
						"code" : '请输入渠道编码'
						
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