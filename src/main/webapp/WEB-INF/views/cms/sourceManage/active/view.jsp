<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="col-lg-12">
	<div class="widget-box transparent" id="divSearch" hidden="true">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title">搜索</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form name="frmSearch" id="frmSearch" target="_self"class="form-inline searchCondition">
					<label> 名称:&nbsp;
						<input name="search_LIKE_name"class="form-control" placeholder="请输入名称" type="text">
					</label>
					<button id="btnSearch" type="button"class="btn btn-primary btn-sm form-control">
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
				    <so:hasPermission name="ContentCenter:SourceConfig:Television:Active:add">	
				    	<button id="add" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</button>
				    </so:hasPermission>
				    <so:hasPermission name="ContentCenter:SourceConfig:Television:Active:modify">
				    	<button id="edt" class="btn btn-xs btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
				    </so:hasPermission>
				    <so:hasPermission name="ContentCenter:SourceConfig:Television:Active:remove">
				    	<button id="del" class="btn btn-xs btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</button>
				    </so:hasPermission>
					<so:hasPermission name="ContentCenter:SourceConfig:Television:Active:search">
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
	var ajaxListUrl = contextPath + '/sourceManage/active/list';
	var ajaxAddUrl = contextPath + '/sourceManage/active/add';
	var ajaxEdtUrl = contextPath + '/sourceManage/active/edit';
	var ajaxDelUrl = contextPath + '/sourceManage/active/remove';

	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'name',
		sortable : false,
		title : "活动名称"
	}, {
		field : 'author',
		sortable : false,
		title : "作者"
	}, {
		field : 'synopsis',
		sortable : false,
		title : "简介"
	}, {
		field : 'sponsor',
		sortable : false,
		title : "主办方"
	}, {
		field : 'validTime',
		sortable : false,
		title : "生效时间"
	}, {
		field : 'unValidTime',
		sortable : false,
		title : "失效时间"
	}, {
		field : 'info',
		sortable : false,
		title : "详情",
        formatter : function(value, row, index) {
            if(value != null){
                var id = row.id;
                if(value != null){
                    return "<a style=\"cursor:pointer\" onclick=\"_preview('"+id+"')\">查看</a>";
                }
            }
        }
	}, {
        field : 'imgUrl',
        sortable : false,
        title : "活动海报",
        formatter : function(value, row, index) {
            if(value != null && value != ""){
                return "<a style=\"cursor:pointer\"title='"+value+"' href='"+value+"' target=\"_blank\">查看</a>";
            }
        }
    }
	];

	jQuery(function($) {
		initTable('table-data', columns, ajaxListUrl, 'frmSearch');

		// 搜索域控制
		$('#search').on('click', function() {
			$('#divSearch').slideToggle("slow");
		});
		$('#frmSearch').find('#btnSearch').on('click', function() {
			$('#table-data').bootstrapTable('selectPage', 1);
		});

		$('#tbWidget').on('reload.ace.widget', function(ev) {
			$('#table-data').bootstrapTable('refresh', { silent : true });
		});		
		$('#add').on('click', function(e) {
			showAddDlg("新增", ajaxAddUrl, "formDlg", function() {
				$('#table-data').bootstrapTable('selectPage', 1);
			}, false,BootstrapDialog.SIZE_WIDE);
		});

		$('#edt').on('click', function(e) {
			showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function() {
				$('#table-data').bootstrapTable('refresh', { silent : true });
			}, false,BootstrapDialog.SIZE_WIDE);
		});

		$('#del').on('click', function(e) {
			doDelete(ajaxDelUrl, "table-data", function() {
				$('#table-data').bootstrapTable('selectPage', 1);
			});
		});
	});
});

function _preview(id){
    console.log(id);
    var ajaxPreviewUrl = contextPath + '/sourceManage/active/preview';
    var ajaxUrl = ajaxPreviewUrl + '/'+id;
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
 		size: BootstrapDialog.SIZE_WIDE,
        closable: true,
        draggable: true,
        closeByBackdrop: false,
        title: "预览",
        message: $('<div></div>').load(ajaxUrl),
        buttons: [{
            id: 'btn-Cancel',
            icon: 'glyphicon glyphicon-remove',
            label: '关闭',
            cssClass: 'btn-default',
            autospin: false,
            action: function(dialogRef){
                dialogRef.close();
            }
        }]
    });
}
</script>