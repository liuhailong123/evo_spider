<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../include.inc.jsp"%>
<div class="col-lg-12">
	<div class="widget-box transparent" id="divSearch" hidden="true">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title">搜索</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form name="frmSearch" id="frmSearch" target="_self"class="form-inline searchCondition">
					<label> appID:&nbsp;
						<input name="search_LIKE_appId"class="form-control" placeholder="请输入appID" type="text">
					</label>
					<label> 空间名称:&nbsp;
						<input name="search_LIKE_bucketName"class="form-control" placeholder="请输入存储空间名称" type="text">
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
				    <so:hasPermission name="ContentCenter:SourceConfig:CloudParam:add">	
				    	<button id="add" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</button>
				    </so:hasPermission>
				    <so:hasPermission name="ContentCenter:SourceConfig:CloudParam:modify">
				    	<button id="edt" class="btn btn-xs btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
				    </so:hasPermission>
				    <so:hasPermission name="ContentCenter:SourceConfig:CloudParam:remove">
				    	<button id="del" class="btn btn-xs btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</button>
				    </so:hasPermission>
					<so:hasPermission name="ContentCenter:SourceConfig:CloudParam:search">
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
				       data-sort-name="id"
				       data-sort-order="asc">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var scripts = [];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	var ajaxListUrl = contextPath + '/sourceManage/baseConfig/cloudParam/list';
	var ajaxAddUrl = contextPath + '/sourceManage/baseConfig/cloudParam/add';
	var ajaxEdtUrl = contextPath + '/sourceManage/baseConfig/cloudParam/edit';
	var ajaxDelUrl = contextPath + '/sourceManage/baseConfig/cloudParam/remove';

	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'appId',
		sortable : false,
		title : "腾讯云appID"
	}, {
		field : 'bucketName',
		sortable : false,
		title : "空间名称"
	}, {
		field : 'secretId',
		sortable : false,
		title : "秘钥ID"
	}, {
		field : 'secretKey',
		sortable : false,
		title : "秘钥KEY"
	}, {
		field : 'cosregion',
		sortable : false,
		title : "区域"
	}, {
		field : 'enable',
		sortable : false,
		title : "是否主要",
		formatter : function(value, row, index) {
			var check = "";
            if(value == 1){
                check = "checked";
            }
            var html = "<input onclick=\"changeMain('"+row.id+"');\" "+check+" type=\"checkbox\" class=\"ace ace-switch ace-switch-2\" />"
               +"<span class=\"lbl middle\"></span>";
            return html;
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
			}, false);
		});

		$('#edt').on('click', function(e) {
			showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function() {
				$('#table-data').bootstrapTable('refresh', { silent : true });
			}, false);
		});

		$('#del').on('click', function(e) {
			doDelete(ajaxDelUrl, "table-data", function() {
				$('#table-data').bootstrapTable('selectPage', 1);
			});
		});
	});
});
//是否主要状态更改
function changeMain(id) {
    var ajaxChangeMainUrl = contextPath + '/sourceManage/baseConfig/cloudParam/changeMain';
    $.ajax({
        type : "post",
        url : ajaxChangeMainUrl,
        data : {
            'id' : id
        },
        success : function(data) {
            if (data.status == "OK") {
                CmMsg.success(data.message, -1);
            } else if (data.status == "ERROR") {
                CmMsg.error(data.message, -1);
            } else {
                CmMsg.error(data.message, -1);
            }
		    $('#table-data').bootstrapTable('refresh', { silent : true });
        }
    });
}
</script>