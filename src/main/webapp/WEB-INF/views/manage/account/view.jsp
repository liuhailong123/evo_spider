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
	<div class="widget-box transparent" id="divSearch" hidden="true" >
		<div class="widget-header widget-header-large">
			<h4 class="widget-title">搜索</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form  name="frmSearch" id="frmSearch" target="_self"  class="form-inline searchCondition">
					<input id="organizationId" name="search_EQ_organization.id" type="hidden" value="">
					<label>
						账号:&nbsp;<input name="search_EQ_username" class="form-control" placeholder="请输入账号" type="text">
					</label>
					<label>
						姓名:&nbsp;<input name="search_LIKE_realname" class="form-control" placeholder="请输入用户姓名" type="text">
					</label>
					<label>
						手机:&nbsp;<input name="search_EQ_mobile" class="form-control" placeholder="请输入用户手机" type="text">
					</label>
					<button id="btnSearch" type="button" class="btn btn-primary btn-sm form-control">
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
				    <so:hasPermission name="Manage:Account:add">
				    	<button id="add" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</button>
				    </so:hasPermission>
				    <so:hasPermission name="Manage:Account:modify">
				    	<button id="edt" class="btn btn-xs btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
				    </so:hasPermission>
				    <so:hasPermission name="Manage:Account:remove">
				    	<button id="del" class="btn btn-xs btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</button>
				    </so:hasPermission>
				    <so:hasPermission name="Manage:Account:assign">
						<button id="assign" class="btn btn-xs btn-info" type="button"><span class="glyphicon glyphicon-cog"></span>&nbsp;授权</button>
					</so:hasPermission>
				    <so:hasPermission name="Manage:Account:reset">
				    	<button id="reset" class="btn btn-xs btn-warning" type="button"><span class="glyphicon glyphicon-repeat"></span>&nbsp;重置密码</button>
				    </so:hasPermission>
				    <so:hasPermission name="Manage:Account:search">
				    	<button id="search" class="btn btn-xs btn-primary" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
				    </so:hasPermission>
				</div>
			</div>
			<div class="widget-toolbar">
			   <a data-action="reload" class="green2 bigger-120" href="#"><i class="ace-icon fa fa-refresh"></i></a>
			   <a data-action="fullscreen" class="blue2 bigger-120" href="#"><i class="ace-icon fa fa-arrows-alt"></i></a>
			 </div>
		</div>
		<div class="widget-body">
			<div class="widget-main no-padding">
				<table id="table-data"
					data-classes="table table-striped table-hover" 
					   data-pagination="true"
				       data-id-field="id"
				       data-unique-id="id"
				       data-sort-name="createDate"
				       data-sort-order="desc">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var scripts = [];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	var ajaxListUrl = contextPath + '/manage/account/list';
	var ajaxTreeUrl = contextPath + '/manage/organization/tree';
	var ajaxAddUrl = contextPath + '/manage/account/add';
	var ajaxEdtUrl = contextPath + '/manage/account/edit';
	var ajaxDelUrl = contextPath + '/manage/account/remove';
	var ajaxAssignUrl = contextPath + '/manage/account/assign';

	//输出字典js变量
	<x:dictData dictCode="Account_Type" var="accountTypes">
	<x:jsonArray varName="accTypes" keyName="code" valName="name" items="${accountTypes}" ></x:jsonArray>
	</x:dictData>
	
	//输出字典js变量
	<x:dictData dictCode="Lock_Status" var="lockStatuses">
	<x:jsonArray varName="lockStatus" keyName="code" valName="name" items="${lockStatuses}" ></x:jsonArray>
	</x:dictData>
	
	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'username',
		sortable : false,
		title : "账号"
	}, {
		field : 'realname',
		sortable : false,
		title : "姓名"
	}, {
		field : 'organization.name',
		sortable : false,
		title : "隶属部门"
	}, {
		field : 'email',
		sortable : true,
		title : "邮箱"
	}, {
		field : 'mobile',
		sortable : false,
		title : "手机"
	}, {
		field : 'status',
		sortable : false,
		title : "状态",
		formatter: function(value, row, index){
			var show = value;
			$.each(lockStatus, function() {
				if (this.code == value) {
					show = this.name;
				}
			});
			return show;
		}
	}, {
		field : 'superman',
		sortable : false,
		title : "超级用户",
		formatter: function(value, row, index){
			var show = value;
			$.each(accTypes, function() {
				if (this.code == value) {
					show = this.name;
				}
			});
			return show;
		}
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
				if(nodes != null){
					$('#frmSearch').find('#organizationId').val(nodes[0].id == '0' ? null : nodes[0].id);
					initTable('table-data', columns, ajaxListUrl, 'frmSearch');
				}
			},
			selNode : function(node, elem) {
				$('#frmSearch').find('#organizationId').val(node.id == '0' ? null : node.id);
				$('#table-data').bootstrapTable('selectPage', 1);
			}
		});

		$('#search').on('click', function() {
			$('#divSearch').slideToggle("slow");
		});

		// 搜索域控制
		$('#frmSearch').find('#btnSearch').on('click', function() {
			$('#table-data').bootstrapTable('selectPage', 1);
		});

		$('#tbWidget').on('reload.ace.widget', function(ev) {
			$('#table-data').bootstrapTable('refresh', { silent : true });
		});

		$('#add').on('click', function(e) {
			showAddDlg("新增", ajaxAddUrl, "formDlg", function() {
				$('#table-data').bootstrapTable('selectPage', 1);
			});
		});
//		$('#add').on('click',function(e){
//		    showAddDlg("新增",ajaxAddUrl,"formDlg",function(){
//		        $('#table-data').bootstrapTable('selectPage',2);
//			});
//		});

		$('#edt').on('click', function(e) {
			showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function() {
				$('#table-data').bootstrapTable('refresh', { silent : true });
			})
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
			showComSelRowDlg("分配", ajaxAssignUrl, "table-data", buttons, BootstrapDialog.SIZE_WIDE);
		});
		
		$('#reset').on('click', function(e) {
			var selections = $('#table-data').bootstrapTable("getSelections");
			if(selections.length == 1){
				var message = "<lable>输入新密码：</lable><input type='text' id='newpass' name='newPassword' value='' placeholder='不输入则默认重置为:123456' />";
				BootstrapDialog.confirm({
					title: '警告！您确定要重置该账号的密码吗?',
		            message: message,
		            type: BootstrapDialog.TYPE_WARNING, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
		            closable: true, // <-- Default value is false
		            draggable: true, // <-- Default value is false
		            btnCancelLabel: '取消', // <-- Default value is 'Cancel',
		            btnOKLabel: '确认', // <-- Default value is 'OK',
		            btnOKClass: 'btn-warning', // <-- If you didn't specify it, dialog type will be used,
		            callback: function(result) {
		                if(result) {
		                	var p = $('#newpass').val();
		                	var params = { id: selections[0].id, newPassword: p};
		                	var url = contextPath + '/manage/account/reset';
		                	$.post(url, params).done(function(data){
		                		if(data.status == 'OK'){
		                			CmMsg.success(data.message, -1);
		                		} else if(data.status == 'ERROR'){
		                			CmMsg.error(data.message, -1);
		                		} else {
		                			CmMsg.error("服务器未知错误", -1);
		                		}
		                	});
		                }
		            }
				});
			}else{
				CmMsg.warn("请选择一行数据记录进行操作.", -1);
			}
		});
	});
});
</script>