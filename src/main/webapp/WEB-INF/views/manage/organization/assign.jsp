<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<form name="assignOrgRoles" id="assignOrgRoles" target="_self"  class="form-inline searchCondition">
	<input type="hidden" id="organizationId" name="organizationId" value="${organizationId }" />
</form>
<div class="row">
	<div class="col-lg-12">
		<div id="tbWidget" class="widget-box transparent ui-sortable-handle">
			<div class="widget-header widget-header-large no-padding">
				<div class="widget-title smaller">
					已授权的角色
				</div>
			</div>
			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="table-assigned"
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
<div class="row">
	<div class="col-lg-12">
		<div id="unAssignWidget" class="widget-box transparent ui-sortable-handle">
			<div class="widget-header widget-header-large no-padding">
				<div class="widget-title smaller">
					未授权的角色
				</div>
			</div>
			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="table-unAssign"
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
//撤销
function doUnAssign(organizationRoleId){
	var url = "${contextPath}/manage/organization/revoke";
	var params={
			'id': organizationRoleId
	};
	$.post(url, params).done(function(data){
		if(data.status == 'OK'){
			$('#table-assigned').bootstrapTable('refresh', {silent: true});
			$('#table-unAssign').bootstrapTable('refresh', {silent: true});
		} else if(data.status == 'ERROR'){
			CmMsg.error(data.message, -1);
		} else {
			CmMsg.error("未知的错误!", -1);
		}
	});
}
//赋权
function doAssign(roleId){
	var url = "${contextPath}/manage/organization/assign";
	var params={
			'organization.id': $('#organizationId').val(),
	        'role.id' : roleId    
	};
	$.post(url, params).done(function(data){
		if(data.status == 'OK'){
			$('#table-assigned').bootstrapTable('refresh', {silent: true});
			$('#table-unAssign').bootstrapTable('refresh', {silent: true});
		} else if(data.status == 'ERROR'){
			CmMsg.error(data.message, -1);
		} else {
			CmMsg.error("未知的错误!", -1);
		}
	});
}
jQuery(function($){
	var ajaxAssignedListUrl = contextPath + "/manage/organization/assigned";
	var assignedColumns = [
							{field:'role.name', title:'角色名称', width:'160'},
							{field:'id', title:'操作', formatter: function(value, row, index){
								return "<button class='btn btn-minier btn-danger btn-round' onclick='doUnAssign(" + value + ")'><span class='glyphicon glyphicon-remove'></span>&nbsp;撤销<"+"/"+"button>";
							}},
	                       ];
	var ajaxUnAssignListUrl = contextPath + "/manage/organization/unAssigned";
	var unassignColumns = [
							{field:'name', title:'角色名称', width:'160'},
							{field:'id', title:'操作', formatter: function(value, row, index){
								return "<button class='btn btn-minier btn-success btn-round' onclick='doAssign(" + value + ")'><span class='glyphicon glyphicon-check'></span>&nbsp;赋权<"+"/"+"button>";
							}},
	                       ];
	initTable('table-assigned', assignedColumns, ajaxAssignedListUrl, 'assignOrgRoles');
	initTable('table-unAssign', unassignColumns, ajaxUnAssignListUrl, 'assignOrgRoles');
})
</script>