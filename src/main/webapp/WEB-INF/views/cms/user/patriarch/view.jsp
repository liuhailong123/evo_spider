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
					<label> 账号:&nbsp;
						<input name="search_LIKE_userAccountNo"class="form-control" placeholder="请输入账号" type="text">
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
					<so:hasPermission name="User:UserLockRecord:search">
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
				       data-sort-order="asc">
				</table>
			</div>
		</div>
	</div>
</div>
<div class="col-lg-12">&nbsp;</div>
<div class="col-lg-12">
	<div class="tabbable">
		<ul id="myTabs" class="nav nav-tabs">
			<li class="active"><a href="#childs" data-toggle="tab">用户加锁记录</a></li>
		</ul>
		<form id="frmSearchChild">
			<input type="hidden" id="userId" name="search_EQ_userId" />
		</form>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="childs">
				<div id="tbWidget" class="widget-box transparent ui-sortable-handle">
					<div class="widget-header widget-header-large no-padding">
						<div class="widget-title smaller">
							<div id="toolbar" class="btn-group btn-corner">

							</div>
						</div>
						<div class="widget-toolbar">
							<a data-action="reload" class="green2 bigger-120" href="#">
								<i class="ace-icon fa fa-refresh"></i>
							</a>
							<a data-action="fullscreen" class="blue2 bigger-120" href="#">
								<i class="ace-icon fa fa-arrows-alt"></i>
							</a>
						</div>
					</div>
					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="table-data-child"
								   data-classes="table table-striped table-hover"
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
		</div>
	</div>
</div>
<script type="text/javascript">
var scripts = [];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	var ajaxListUrl = contextPath + '/user/patriarch/list';

    var columns = [ {
        field : 'state',
        checkbox : true,
        title : "选择"
    }, {
        field : 'userName',
        sortable : false,
        title : "用户名称"
    }, {
        field : 'userAccountNo',
        sortable : false,
        title : "用户账号"
    }, {
        field : 'createDate',
        sortable : true,
        title : "创建时间"
    }
    ];

    <x:dictData dictCode="Lock_Content_Type" var="lockContentType">
    	<x:jsonArray varName="lockContentType" keyName="code" valName="name" items="${lockContentType}" ></x:jsonArray>
    </x:dictData>

	var ajaxListChildUrl = contextPath + '/user/userLockRecord/list';

	var childColumns = [{
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'lockContentType',
		sortable : false,
		title : "加锁内容类型",
		formatter: function (value, row, index) {
			var show = value;
			$.each(lockContentType, function () {
				if (this.code == value) {
					show = this.name;
				}
			});
			return show;
		}
	}, {
		field : 'lockContentName',
		sortable : true,
		title : "加锁内容名称"
	}, {
		field : 'createDate',
		sortable : true,
		title : "创建时间"
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

		//--------------------------------------------------------------------

		$('#table-data').on('load-success.bs.table', function() {
            var $table = $(this);
            var data = $table.bootstrapTable('getData');
            if(data && data.length > 0){
                $table.bootstrapTable('check', 0);
                $('#frmSearchChild').find('#userId').val(data[0].userId);
                initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
            }else{
                initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
            }
        });

        $('#table-data').on('check.bs.table', function(elem, row) {
            $('#frmSearchChild').find('#appId').val(row.id);
            $('#table-data-child').bootstrapTable('refresh',{ silent : true });//刷新表单
        });


	});
});
</script>