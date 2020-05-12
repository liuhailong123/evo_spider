<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${module.id}" /> <input
				type="hidden" id="parentId" name="parent.id"
				value="${module.parent.id}" /> <input type="hidden" id="level"
				name="level" value="${module.level}" />
			<div class="form-group">
				<label class="col-lg-2 control-label">上级菜单:</label>
				<div class="col-lg-9">
					<span class="block input-icon input-icon-right"> <input
						readonly type="text" class="form-control" id="parentName"
						name="parent.name" value="${module.parent.name}" /> <i
						class="ace-icon fa fa-cogs"></i>
					</span>
					<div class="selTree">
						<div id="selectTree"></div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">菜单名称:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="name" name="name"
						value="${module.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">菜单编码:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="code" name="code"
						value="${module.code}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">菜单图标:</label>
				<div class="col-lg-9">
					<input type="hidden" id="icon" name="icon"
						value="${module.icon != null ? module.icon : 'menu-icon fa fa-caret-right'}" />
					<span id="selIcon" class="btn btn-white btn-lg"> <i
						id="menuIcon"
						class="${module.icon != null ? module.icon : 'fa fa-caret-right'}"></i>
					</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">菜单链接:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="url" name="url"
						value="${module.url}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">状态:</label>
				<div class="col-lg-9">
					<x:dictData dictCode="Menu_Status" var="dictStatus">
						<x:select var="status" items="${dictStatus}" id="status" name="status" className="form-control">
							<x:option value="${status.code }" text="${status.name }" selected="${module.status eq status.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">优先级:</label> <span
					class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="number" class="form-control" id="priority"
						name="priority" value="${module.priority}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">菜单描述:</label>
				<div class="col-lg-9">
					<textarea class="form-control" id="description" name="description">${module.description}</textarea>
				</div>
			</div>
			<fieldset>
				<legend>权限定义:</legend>
				<div class="form-group">
					<label class="col-lg-2 control-label">权限列表:</label>
					<div class="col-lg-10" id="permList">
						<c:forEach var="p" items="${module.permissions }" varStatus="s">
							<div class="checkbox inline" title="${p.description}" tabindex="${s.index }">
								<label> 
									<input name="permissions[${s.index}].id" value="${p.id}" type="hidden" />
									<input name="permissions[${s.index}].name" value="${p.name}" type="hidden" />
									<input name="permissions[${s.index}].code" value="${p.code}" type="checkbox" class="ace"
									<c:if test="${p.id != null }">checked="checked"</c:if> />
									<span class="lbl">${p.name}&nbsp;&nbsp;</span>
									<input name="permissions[${s.index}].description" value="${p.description}" type="hidden" />
									<input name="permissions[${s.index}].module.id" value="${p.module.id}" type="hidden" />
								</label>
							</div>
						</c:forEach>
					</div>
				</div>
			</fieldset>
			<fieldset id="createPermisson">
				<div class="form-group">
					<label class="col-lg-2 control-label">权限名称:</label>
					<div class="col-lg-9">
						<input type="text" class="form-control" id="p_name" name ="_name" value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label">权限编码:</label>
					<div class="col-lg-9">
						<input type="text" class="form-control" id="p_code" name ="_code" value=""/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label">权限描述:</label>
					<div class="col-lg-9">
						<textarea class="form-control" id="p_description" name="_description"></textarea>
					</div>
				</div>
				<div class="col-lg-offset-2 col-lg-10 btn-group btn-corner">
					<button id="addPerm" type="button" class="btn btn-success">添加权限</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript">
	var ajaxTreeUrl = contextPath + '/manage/module/tree/3';

	var iconDlg = null;

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

	function iconCall(icon) {
		$("#menuIcon").removeClass().addClass('menu-icon').addClass(
				$(icon).attr('src')).attr('src', $(icon).attr('src'));
		$("#icon").val('menu-icon ' + $(icon).attr('src'));
		if (iconDlg) {
			iconDlg.close();
		}
	}

	jQuery(function($) {
		// 初始化树选中
		var id = $('#id').val();

		if (id == "") {
			var $JsTree = $.jstree.reference("treeview");
			var nodes = $JsTree.get_selected(true);
			if (nodes != null) {
				$("#parentId").val(nodes[0].id);
				$("#parentName").val(nodes[0].text);
				$("#level").val(parseInt(nodes[0].original.level) + 1);
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
				$("#level").val(parseInt(node.original.level) + 1);
				hideTree();
			}
		});

		$('#selIcon').on('click', function(e) {
			iconDlg = BootstrapDialog.show({
				type : BootstrapDialog.TYPE_DEFAULT,
				closable : true,
				draggable : false,
				title : '选择图标',
				message : $("<div></div>").load(contextPath + "/manage/icon")
			});
		});
		
		$('#addPerm').on('click', function(event){
			var $fieldset = $('#createPermisson');
			var $name = $('#p_name', $fieldset),
			$code = $('#p_code', $fieldset),
			$desc = $('#p_description', $fieldset);
			var maxId = 0;
			var $permList = $('#permList');
			$permList.find('div.checkbox').each(function(){
				var idx = $(this).attr('tabindex');
				maxId = maxId > idx ? maxId : idx;
			});
			maxId = parseInt(maxId) + 1;
			var permHtml = new Array();
			permHtml.push('<div class="checkbox inline" title="'+ $desc.val() +'" tabindex="'+ maxId +'"><label>');
			permHtml.push('<input name="permissions[' + maxId + '].name" value="'+ $name.val() +'" type="hidden"/>');
			permHtml.push('<input name="permissions[' + maxId + '].code" value="'+ $code.val() +'" type="checkbox" class="ace" checked="checked"'+'"/>');
			permHtml.push('<span class="lbl">'+ $('#p_name').val() +'&nbsp;&nbsp;</span>');
			permHtml.push('<input name="permissions[' + maxId + '].description" value="'+ $desc.val() +'" type="hidden"/>');
			permHtml.push('</label></div>');
			$permList.append(permHtml.join(''));
			$name.val('');
			$code.val('');
			$desc.val('');
		});

		$('#formDlg').validate(
				{
					errorElement : 'span',
					errorClass : 'help-block',
					focusCleanup : false,
					focusInvalid : false,
					onsubmit : false,
					rules : {
						"parent.name" : {
							required : true
						},
						"name" : {
							required : true
						},
						"code" : {
							required : true
						},
						"url" : {
							required : true
						},
						"priority" : {
							required : true
						}
					},
					messages : {
						"parent.name" : '请选择一个上级菜单',
						"name" : '请输入菜单名称',
						"code" : '请输入菜单编码',
						"url" : '请输入菜单链接',
						"priority" : '请输入优先级',
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