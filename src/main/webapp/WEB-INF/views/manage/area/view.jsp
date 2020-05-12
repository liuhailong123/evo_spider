<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="col-lg-3">
	<div class="widget-box transparent">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title lighter smaller">区域</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main padding-8">
				<div id="treeview" role="tree"></div>
			</div>
		</div>
	</div>
</div>
<div class="col-lg-9">
	<div class="widget-box transparent" id="divSearch" hidden="true" >
		<div class="widget-header widget-header-large">
			<h4 class="widget-title">搜索</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form  name="frmSearch" id="frmSearch" target="_self"  class="form-inline searchCondition">
					<input id="pid" name="search_EQ_parent.id" type="hidden" value="">
					<label>
						名称:&nbsp;<input name="search_LIKE_name" class="form-control" placeholder="请输入名称" type="text">
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
				    <so:hasPermission name="Manage:Area:add">
				    	<button id="add" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</button>
				    </so:hasPermission>
				    <so:hasPermission name="Manage:Area:modify">
				    	<button id="edt" class="btn btn-xs btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
				    </so:hasPermission>
				    <so:hasPermission name="Manage:Area:remove">
				    	<button id="del" class="btn btn-xs btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</button>
				    </so:hasPermission>
				    <so:hasPermission name="Manage:Area:search">
				    	<button id="search" class="btn btn-xs btn-primary" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
				    </so:hasPermission>
<!-- 				    <button id="init">初始化地区</button> -->
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
				       data-sort-name="priority"
				       data-sort-order="asc">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var scripts = [];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	var ajaxListUrl = contextPath + '/manage/area/list';
	var ajaxTreeUrl = contextPath + '/manage/area/tree';
	var ajaxAddUrl = contextPath + '/manage/area/add';
	var ajaxEdtUrl = contextPath + '/manage/area/edit';
	var ajaxDelUrl = contextPath + '/manage/area/remove';

	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'name',
		sortable : false,
		title : "地区"
	}, {
		field : 'shortName',
		sortable : false,
		title : "简称"
	}, {
		field : 'jd',
		sortable : false,
		title : "经度"
	}, {
		field : 'wd',
		sortable : false,
		title : "纬度"
	}, {
		field : 'level',
		sortable : false,
		title : "地区级别"
	}, {
		field : 'priority',
		sortable : true,
		title : "排序(优先级)"
	}, {
		field : 'parent.name',
		sortable : false,
		title : "上级区域"
	},
	];

	jQuery(function($) {
		initTree("treeview", ajaxTreeUrl, false, {
			loaded: function(){
				$('#frmSearch').find('#pid').val("1");//区域树默认选中中国
				initTable('table-data', columns, ajaxListUrl, 'frmSearch');
			},
			selNode : function(node, elem) {
				$('#frmSearch').find('#pid').val(node.id == '0' ? null :node.id);//node.id=0表示点击虚拟根：所有区域
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
				var $JsTree = $.jstree.reference("treeview");
				$JsTree.refresh();
			});
		});

		$('#edt').on('click', function(e) {
			showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function() {
				var $JsTree = $.jstree.reference("treeview");
				$JsTree.refresh();
			})
		});

		$('#del').on('click', function(e) {
			doDelete(ajaxDelUrl, "table-data", function() {
				var $JsTree = $.jstree.reference("treeview");
				$JsTree.refresh();
			});
		});
		$('#init').on('click',function(e){
			$.ajax({
				url:contextPath + '/manage/area/init',
				type:"post",
				success:function(data){
					if(data.status == 'OK'){
		    			CmMsg.success(data.message, -1);
		    		} else if(data.status == 'ERROR'){
		    			CmMsg.error(data.message, -1);
		    		}
				}
			})
		});
	});
});
</script>