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
					<input style="display: none"/>
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
				    <so:hasPermission name="User:NoticeConf:add">	
				    	<button id="add" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</button>
				    </so:hasPermission>
				    <so:hasPermission name="User:NoticeConf:modify">
				    	<button id="edt" class="btn btn-xs btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
				    </so:hasPermission>
				    <so:hasPermission name="User:NoticeConf:remove">
				    	<button id="del" class="btn btn-xs btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</button>
				    </so:hasPermission>
					<so:hasPermission name="User:NoticeConf:search">
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
					data-single-select="true"
					   data-pagination="true"
				       data-id-field="id"
				       data-unique-id="id"
				       data-sort-name="createDate"
				       data-sort-order="asc">
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var scripts = [];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	var ajaxListUrl = contextPath + '/user/noticeConf/list';
	var ajaxAddUrl = contextPath + '/user/noticeConf/add';
	var ajaxEdtUrl = contextPath + '/user/noticeConf/edit';
	var ajaxDelUrl = contextPath + '/user/noticeConf/remove';

    <x:dictData dictCode="Notice_Type" var="NoticeType">
    <x:jsonArray varName="NoticeType" keyName="code" valName="name" items="${NoticeType}" ></x:jsonArray>
    </x:dictData>

    <x:dictData dictCode="OFF_ON" var="OFFON">
    <x:jsonArray varName="OFFON" keyName="code" valName="name" items="${OFFON}" ></x:jsonArray>
    </x:dictData>
	
	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'type',
		sortable : true,
		title : "通知类型",
        formatter: function (value, row, index) {
            var show = value;
            $.each(NoticeType, function () {
                if (this.code == value) {
                    show = this.name;
                }
            });
            return show;
        }
	}, {
        field : 'info',
        sortable : true,
        title : "通知模版"
    }, {
        field : 'status',
        sortable : true,
        title : "是否启用",
        formatter: function (value, row, index) {
            var show = value;
            $.each(OFFON, function () {
                if (this.code == value) {
                    show = this.name;
                }
            });
            return show;
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
		//================================================
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



</script>