<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${data.id}" />
			<input type="hidden" id="parentId" name="dictClassify.id" value="${data.dictClassify.id}" />
			<div class="form-group">
				<label class="col-lg-2 control-label">所属分类:</label>
				<div class="col-lg-9">
					<span class="block input-icon input-icon-right">
						<input readonly type="text" class="form-control" id="parentName" name="dictClassify.name" value="${data.dictClassify.name}" />
						<i class="ace-icon fa fa-cogs"></i>
					</span>
					<div class="selTree">
						<div id="selectTree"></div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">字典名称:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="name" name="name"
						value="${data.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">字典编码:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="code" name="code"
						value="${data.code}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">优先级:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="number" class="form-control" id="priority"
						name="priority" value="${data.priority}" />
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	var ajaxTreeUrl = contextPath + '/manage/dictionary/classify/tree';
	
	function hideTree() {
		$(".selTree").fadeOut("fast");
		$(".modal-dialog").unbind("mousedown", onBodyDown);
	}

	function onBodyDown(event) {
		if (!($(event.target).hasClass('selTree') || $(event.target).parents(
				".selTree").length > 0)) {
			hideTree();
		}
	}
	jQuery(function($) {
		// 初始化树选中
		var id = $('#id').val();
		if (id == "") {
			console.log(111);
			var $JsTree = $.jstree.reference("treeview");
			var nodes = $JsTree.get_selected(true);
			if (nodes != null) {
				$("#parentId").val(nodes[0].id);
				$("#parentName").val(nodes[0].text);
			}
		}

		$('#parentName').on('click', function() {
			var selectObj = $("#parentName");
			$(".selTree").css({
				width : selectObj.outerWidth() + "px"
			}).slideDown("fast");
			$(".modal-dialog").bind("mousedown", onBodyDown);
		});

		initTree("selectTree", ajaxTreeUrl, false, {
			selNode : function(node, elem) {
				$("#parentId").val(node.id);
				$("#parentName").val(node.text);
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
						"dictClassify.name" : {
							required : true
						},
						"name" : {
							required : true
						},
						"code" : {
							required : true
						},
						"priority" : {
							required : true
						}
					},
					messages : {
						"dictClassify.name" : '请选择一个字典分类',
						"name" : '请输入字典名称',
						"code" : '请输入字典编码',
						"priority" : '请输入字典优先级',
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