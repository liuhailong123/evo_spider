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
					<label> 应用名称:&nbsp;
						<input name="search_LIKE_name"class="form-control" placeholder="请输入应用名称" type="text">
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
				    <so:hasPermission name="App:AppInfo:add">	
				    	<button id="add" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</button>
				    </so:hasPermission>
				    <so:hasPermission name="App:AppInfo:modify">
				    	<button id="edt" class="btn btn-xs btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
				    </so:hasPermission>
				    <so:hasPermission name="App:AppInfo:remove">
				    	<button id="del" class="btn btn-xs btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</button>
				    </so:hasPermission>
					<so:hasPermission name="App:AppInfo:search">
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
			<li class="active"><a href="#childs" data-toggle="tab">应用加锁配置</a></li>
		</ul>
		<form id="frmSearchChild">
			<input type="hidden" id="appId" name="search_EQ_appId" />
		</form>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="childs">
				<div id="tbWidget" class="widget-box transparent ui-sortable-handle">
					<div class="widget-header widget-header-large no-padding">
						<div class="widget-title smaller">
							<div id="toolbar" class="btn-group btn-corner">
								<so:hasPermission name="ContentCenter:Template:FallTemplate:add">
									<button id="addChild" class="btn btn-xs btn-pink" type="button">
										<span class="glyphicon glyphicon-plus"></span>&nbsp;新增
									</button>
								</so:hasPermission>
								<so:hasPermission name="ContentCenter:Template:FallTemplate:modify">
									<button id="edtChild" class="btn btn-xs btn-success" type="button">
										<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
									</button>
								</so:hasPermission>
								<so:hasPermission name="ContentCenter:Template:FallTemplate:remove">
									<button id="delChild" class="btn btn-xs btn-danger" type="button">
										<span class="glyphicon glyphicon-remove"></span>&nbsp;删除
									</button>
								</so:hasPermission>
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
	var ajaxListUrl = contextPath + '/app/appInfo/list';
	var ajaxAddUrl = contextPath + '/app/appInfo/add';
	var ajaxEdtUrl = contextPath + '/app/appInfo/edit';
	var ajaxDelUrl = contextPath + '/app/appInfo/remove';

    var columns = [ {
        field : 'state',
        checkbox : true,
        title : "选择"
    }, {
        field : 'name',
        sortable : false,
        title : "应用名称"
    }, {
        field : 'info',
        sortable : false,
        title : "应用描述"
    }, {
        field : 'createDate',
        sortable : true,
        title : "创建时间"
    }
    ];

    <x:dictData dictCode="Lock_Content_Type" var="lockContentType">
    <x:jsonArray varName="lockContentType" keyName="code" valName="name" items="${lockContentType}" ></x:jsonArray>
    </x:dictData>

    <x:dictData dictCode="OFF_ON" var="offOn">
    <x:jsonArray varName="offOn" keyName="code" valName="name" items="${offOn}" ></x:jsonArray>
    </x:dictData>

    var ajaxListChildUrl = contextPath + '/app/appLockConf/list';
    var ajaxAddChildUrl = contextPath + '/app/appLockConf/add';
    var ajaxEdtChildUrl = contextPath + '/app/appLockConf/edit';
    var ajaxDelChildUrl = contextPath + '/app/appLockConf/remove';
    
    var childColumns = [{
        field : 'state',
        checkbox : true,
        title : "选择"
    }, {
        field : 'contentType',
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
        field : 'contentName',
        sortable : true,
        title : "加锁内容名称"
    }, {
        field : 'status',
        sortable : false,
        title : "是否默认加锁",
        formatter: function (value, row, index) {
            var show = value;
            $.each(offOn, function () {
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
			}, false, BootstrapDialog.SIZE_WIDE);
		});

		$('#edt').on('click', function(e) {
			showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function() {
				$('#table-data').bootstrapTable('refresh', { silent : true });
			}, false, BootstrapDialog.SIZE_WIDE);
		});

		$('#del').on('click', function(e) {
			doDelete(ajaxDelUrl, "table-data", function() {
				$('#table-data').bootstrapTable('selectPage', 1);
			});
		});

		//--------------------------------------------------------------------
        $('#table-data').on('load-success.bs.table', function() {
            var $table = $(this);
            var data = $table.bootstrapTable('getData');
            if(data && data.length > 0){
                $table.bootstrapTable('check', 0);
                $('#frmSearchChild').find('#appId').val(data[0].id);
                initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
            }else{
                initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
            }
        });

        $('#table-data').on('check.bs.table', function(elem, row) {
            $('#frmSearchChild').find('#appId').val(row.id);
            $('#table-data-child').bootstrapTable('refresh',{ silent : true });//刷新表单
        });

        $('#addChild').on('click', function(e) {
            var selections = $('#table-data').bootstrapTable("getSelections");
            if(selections.length != 0){
                //新增
                showAddDlg("新增", ajaxAddChildUrl+"/"+$("#appId").val(), "formDlg", function() {
                    $('#table-data-child').bootstrapTable('refresh', { silent : true });
                },false);
            }else{
                CmMsg.warn("请先选择应用！！！", -1);
            }
        });

        $('#edtChild').on('click', function(e) {
            showEdtDlg("编辑", ajaxEdtChildUrl, "table-data-child", "formDlg", function() {
                $('#table-data-child').bootstrapTable('refresh', { silent : true });
            },false)
        });

        $('#delChild').on('click', function(e) {
            doDelete(ajaxDelChildUrl, "table-data-child", function() {
            });
        });
		
	});
});
</script>