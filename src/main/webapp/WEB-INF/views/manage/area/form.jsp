<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${area.id}" /> 
			<input type="hidden" id="level" name="level" value="${empty area.level ? 0 : area.level}" /> 
			<input type="hidden" id="parentId" name="parent.id" value="${area.parent.id}" />
			<div class="form-group" id="areaTree">
				<label class="col-lg-2 control-label">上级区域:</label>
				<div class="col-lg-9">
					<span class="block input-icon input-icon-right"> 
						<input readonly type="text" class="form-control" id="parentName" name="parent.name"
						 value="${ empty area.parent ? '所有区域' : area.parent.name}" /> 
						<i class="ace-icon fa fa-cogs"></i>
					</span>
					<div class="selTree">
						<div id="selectTree"></div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">名称:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="name" name="name" value="${area.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">简称:</label>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="shortName" name="shortName" value="${area.shortName}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">经度:</label>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="jd" name="jd" value="${area.jd}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">纬度:</label>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="wd" name="wd" value="${area.wd}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">优先级:</label> <span class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="number" min="1" class="form-control" id="priority" name="priority" value="${area.priority}" />
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	var ajaxTreeUrl = contextPath + '/manage/area/tree';
	
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
		var id = $('#id').val();
		var $JsTree = $.jstree.reference("treeview");
		if (id == "") {
			var nodes = $JsTree.get_selected(true);
			if (nodes != null && nodes.length > 0) {
				$("#parentId").val(nodes[0].id == '0' ? null : nodes[0].id);
				$("#parentName").val(nodes[0].text);
				$("#level").val(parseInt(nodes[0].original.level) + 1);
			}
		}

		$('#parentName').on('click', function() {
			var selectObj = $("#parentName");
			$(".selTree").css({ width : selectObj.outerWidth() + "px" }).slideDown("fast");
			$(".modal-dialog").bind("mousedown", onBodyDown);
		});

		initTree("selectTree", ajaxTreeUrl, false, {
			selNode : function(node, elem) {
				var parentIdVal = node.id == '0' ? null : node.id;
				if(id != "" && id == parentIdVal){
					CmMsg.warn("上级区域不可以为自身", -1);
					return;
				}
				$("#parentId").val(parentIdVal);
				$("#parentName").val(node.text);
				$("#level").val(parseInt(node.original.level) + 1);
				hideTree();
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
						}
					},
					messages : {
						"name" : '请输入名称',
						"priority" : '请输入优先级',
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