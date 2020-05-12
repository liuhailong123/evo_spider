<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="col-lg-4">
	<div class="widget-box transparent">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title lighter smaller">组织机构</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main padding-8">
				<div id="treeview" role="tree"></div>
			</div>
		</div>
	</div>
</div>
<div class="col-lg-8">
	<div class="widget-box transparent" id="divSearch" hidden="true">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title">搜索</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form name="frmSearch" id="frmSearch" target="_self"
					class="form-inline searchCondition">
					<input id="organizationId" name="search_EQ_organization.id" type="hidden">
					<label> 名称:&nbsp;<input name="search_LIKE_name"
						class="form-control" placeholder="请输入名称" type="text">
					</label>
					<button id="btnSearch" type="button"
						class="btn btn-primary btn-sm form-control">
						<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
					</button>
				</form>
			</div>
		</div>
	</div>
	<div id="tbWidget" class="widget-box transparent ui-sortable-handle">
		<div class="widget-header widget-header-large no-padding">
			<div class="widget-title smaller">
				<div id="toolbar" class="btn-group btn-corner">
					<so:hasPermission name="Manage:Role:assign">
					
<!-- 						<button id="assign" class="btn btn-info" type="button"> -->
<!-- 							<span class="glyphicon glyphicon-cog"></span>&nbsp;分配 -->
<!-- 						</button> -->
						
						<button type="button" data-toggle="dropdown" class="btn btn-info" aria-expanded="false">
							<span class="glyphicon glyphicon-cog"></span>&nbsp;分配
							<i class="ace-icon fa fa-angle-down icon-on-right"></i>
						</button>
						<ul class="dropdown-menu">
							<li>
								<a href="#" id="assign" >权限分配</a>
							</li>
							<li>
								<a href="#" id="site" >站点分配</a>
							</li>
						</ul>						
					</so:hasPermission>
					<so:hasPermission name="Manage:Role:add">
						<button id="add" class="btn btn-pink" type="button">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;新增
						</button>
					</so:hasPermission>
					<so:hasPermission name="Manage:Role:modify">
						<button id="edt" class="btn btn-success" type="button">
							<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
						</button>
					</so:hasPermission>
					<so:hasPermission name="Manage:Role:remove">
						<button id="del" class="btn btn-danger" type="button">
							<span class="glyphicon glyphicon-trash"></span>&nbsp;删除
						</button>
					</so:hasPermission>
					<so:hasPermission name="Manage:Role:search">
						<button id="search" class="btn btn-primary" type="button">
							<span class="glyphicon glyphicon-search"></span>&nbsp;查询
						</button>
					</so:hasPermission>
				</div>
			</div>
			<div class="widget-toolbar">
				<a data-action="reload" class="green2 bigger-120" href="#"><i
					class="ace-icon fa fa-refresh"></i></a> <a data-action="fullscreen"
					class="blue2 bigger-120" href="#"><i
					class="ace-icon fa fa-arrows-alt"></i></a>
			</div>
		</div>
		<div class="widget-body">
			<div class="widget-main no-padding">
				<table id="table-data"
					data-classes="table table-striped table-hover" 
					data-pagination="true"
					data-id-field="id"
					data-unique-id="id" 
					data-sort-name="id" 
					data-sort-order="asc">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var scripts = [];
	$('.page-content-area').ace_ajax(
			'loadScripts',
			scripts,
			function() {
				var ajaxListUrl = contextPath + '/manage/role/list';
				var ajaxAddUrl = contextPath + '/manage/role/add';
				var ajaxEdtUrl = contextPath + '/manage/role/edit';
				var ajaxDelUrl = contextPath + '/manage/role/remove';
				var ajaxAssignUrl = contextPath + '/manage/role/assign';
				var ajaxSiteUrl = contextPath + '/columnManage/column/site';
				var ajaxTreeUrl = contextPath + '/manage/organization/tree';

				var columns = [ {
					field : 'state',
					checkbox : true,
					title : "选择"
				}, {
					field : 'name',
					sortable : false,
					title : "名称"
				}, {
					field : 'code',
					sortable : false,
					title : "编码"
				}, {
					field : 'createDate',
					sortable : true,
					title : "创建时间"
				}, {
					field : 'modifyDate',
					sortable : true,
					title : "修改时间"
				}, ];
				jQuery(function($) {
					initTree("treeview", ajaxTreeUrl, false, {
						loaded: function(){
							var $JsTree = $.jstree.reference("treeview");
							var nodes = $JsTree.get_selected(true);
							if(nodes.length > 0){
								$('#frmSearch').find('#organizationId').val(nodes[0].id == '0' ? '' : nodes[0].id);
								initTable('table-data', columns, ajaxListUrl, 'frmSearch');
							} else {
								initTable('table-data', columns, ajaxListUrl, 'frmSearch');
							}
						},
						selNode : function(node, elem) {
							$('#frmSearch').find('#organizationId').val(node.id == '0' ? '' :node.id);
							$('#table-data').bootstrapTable('selectPage', 1);
						}
					});
					
					//initTable('table-data', columns, ajaxListUrl, 'frmSearch');

					$('#search').on('click', function() {
						$('#divSearch').slideToggle("slow");
					});

					// 搜索域控制
					$('#frmSearch').find('#btnSearch').on('click', function() {
						$('#table-data').bootstrapTable('selectPage', 1);
					});

					$('#tbWidget').on('reload.ace.widget', function(ev) {
						$('#table-data').bootstrapTable('refresh', {
							silent : true
						});
					});

					$('#add').on('click', function(e) {
						showAddDlg("新增", ajaxAddUrl, "formDlg", function() {
							var $JsTree = $.jstree.reference("treeview");
							$JsTree.refresh();
						}, false);
					});

					$('#edt').on('click', function(e) {
						showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function() {
							var $JsTree = $.jstree.reference("treeview");
							$JsTree.refresh();
						}, false);
					});

					$('#del').on('click', function(e) {
						doDelete(ajaxDelUrl, "table-data", function() {
							$('#table-data').bootstrapTable('selectPage', 1);
						});
					});
					
					$('#assign').on('click', function(e) {
						var buttons = [{
							id: 'btn-OK',   
						    icon: 'glyphicon glyphicon-remove',
						    label: '关闭',
						    cssClass: 'btn-default', 
						    autospin: false,
						    action: function(dialogRef){
						    	dialogRef.close();
						    }
						}];
						showComSelRowDlg("分配权限", ajaxAssignUrl, "table-data", buttons, BootstrapDialog.SIZE_WIDE);
					});
					
					$('#site').on('click', function(e) {
						var buttons = [{
							id: 'btn-OK',   
						    icon: 'glyphicon glyphicon-remove',
						    label: '关闭',
						    cssClass: 'btn-default', 
						    autospin: false,
						    action: function(dialogRef){
						    	dialogRef.close();
						    }
						}];
						showComSelRowDlg("分配站点", ajaxSiteUrl, "table-data", buttons, BootstrapDialog.SIZE_WIDE);
					});
				});
			});
</script>