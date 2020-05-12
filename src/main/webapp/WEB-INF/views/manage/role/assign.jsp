<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-5">
		<div class="widget-box transparent">
			<div class="widget-header widget-header-large">
				<h4 class="widget-title lighter smaller">权限目录</h4>
			</div>
			<div class="widget-body">
				<div class="widget-main padding-8">
					<div id="assignTree" role="tree"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-7">
		<div id="tbWidget" class="widget-box transparent ui-sortable-handle">
			<div class="widget-header widget-header-large no-padding">
				<div class="widget-title smaller">
					<div class="btn-group btn-corner">
						<so:hasPermission name="Manage:Role:assign">
							<button id="assignTo" class="btn btn-pink" type="button">
								<span class="glyphicon glyphicon-flag"></span>&nbsp;授权
							</button>
						</so:hasPermission>
					</div>
				</div>
			</div>
			<div class="widget-body">
				<form name="frmAssignSearch" id="frmAssignSearch" target="_self" class="form-inline searchCondition">
					<input id="roleId" name="roleId" type="hidden" value="${roleId}"/>
					<input id="moduleId" name="moduleId" type="hidden" value="">
				</form>
				<div class="widget-main no-padding">
					<table id="table-assign" 
						data-classes="table table-striped table-hover" 
						data-pagination="false"
						data-id-field="id" 
						data-unique-id="id" 
						data-sort-name="id"
						data-sort-order="asc">
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	jQuery(function($){
		var columns = [
						{	field: 'state', title: '全选',checkbox : true, 
							formatter:function(value, row, index){
								if( row.role ){
									return { checked:true };
								}
								return value;
							}
						},
						{	field: 'permission.name', title: '权限名称', clickToSelect: true },
						{	field: 'permission.code', title: '权限编码', clickToSelect: true },
						{	field: 'permission.description', title: '权限描述', clickToSelect: true }
					];
		initTree("assignTree", "${contextPath}/manage/module/tree/5", false, {
			loadNode : function(elem, selection) {
				if (selection.status && selection.node.parent == '#') {
					$('#frmAssignSearch').find('#moduleId').val(selection.node.id);
					initTable('table-assign', columns, "${contextPath}/manage/role/assigned", 'frmAssignSearch');
				}
			},
			selNode : function(node, elem) {
				$('#frmAssignSearch').find('#moduleId').val(node.id);
				$('#table-assign').bootstrapTable('refresh', {silent : true});
			}
		});
		
		$('#assignTo').on('click', function(e){
			var selections = $('#table-assign').bootstrapTable("getSelections");
			var ids = new Array();
			if(selections.length > 0){
				for (var i = 0; i < selections.length; i++) {
					ids.push(selections[i].permission.id);
				}
			}
			var params = {
					roleId: $('#roleId').val(),
					moduleId: $('#moduleId').val(),
					permIds: ids
			}
			$.post("${contextPath}/manage/role/assign", params).done(function(data){
				if(data.status == 'OK'){
	    			CmMsg.success(data.message, -1);
				} else if (data.status == 'ERROR'){
	    			CmMsg.error(data.message, -1);
				} else if (data.status == 'TIMEOUT'){
	    			CmMsg.error(data.message, -1);
				} else {
					CmMsg.error("分配权限失败!", -1);
				}
			});
		});
	});
</script>