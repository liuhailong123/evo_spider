<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#parent" data-toggle="tab">首页配置</a></li>
        </ul>
        <div class="tab-content">
            <div id="fallTemplate">
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
							    <so:hasPermission name="ContentCenter:ContentManage:IndexConf:add">
							    	<button id="add" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</button>
							    </so:hasPermission>
							    <so:hasPermission name="ContentCenter:ContentManage:IndexConf:modify">
							    	<button id="edt" class="btn btn-xs btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
							    </so:hasPermission>
							    <so:hasPermission name="ContentCenter:ContentManage:IndexConf:remove">
							    	<button id="del" class="btn btn-xs btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</button>
							    </so:hasPermission>
								<so:hasPermission name="ContentCenter:ContentManage:IndexConf:search">
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
							       data-single-select="true"
							       data-unique-id="id"
							       data-sort-name="modifyDate"
							       data-sort-order="desc">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="col-lg-12">&nbsp;</div>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#childs" data-toggle="tab">子配置</a></li>
        </ul>
        <form id="frmSearchChild">
            <input type="hidden" id="indexConfId" name="search_EQ_indexConfId" />
        </form>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="childs">
                <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                    <div class="widget-header widget-header-large no-padding">
                        <div class="widget-title smaller">
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="ContentCenter:ContentManage:IndexConf:add">
                                    <button id="addChild" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:ContentManage:IndexConf:modify">
                                    <button id="edtChild" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:ContentManage:IndexConf:remove">
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
                                data-sort-name="position"
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
	var ajaxListUrl = contextPath + '/contentManage/indexConf/list';
	var ajaxAddUrl = contextPath + '/contentManage/indexConf/add';
	var ajaxEdtUrl = contextPath + '/contentManage/indexConf/edit';
	var ajaxDelUrl = contextPath + '/contentManage/indexConf/remove';

	
	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'name',
		sortable : false,
		title : "名称"
	}, {
		field : 'info',
		sortable : false,
		title : "简介"
	}
	, {
		field : 'count',
		sortable : false,
		title : "位置总数"
	}, {
		field : 'imgUrl',
		sortable : false,
		title : "预览图",
		formatter : function(value, row, index) {
			if(value != null && value != ""){
				return "<a style=\"cursor:pointer\" href=\""+value+"\" target=\"_Blank\">查看</a>";
			}
		}
	}, {
		field : 'createDate',
		sortable : true,
		title : "创建时间"
	}, {
		field : 'modifyDate',
		sortable : true,
		title : "修改时间"
	}
	];
	
	//资源绑定colums
	var childColumns = [{
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
        field : 'position',
        sortable : false,
        title : "位置"
    }, {
		field : 'name',
		sortable : false,
		title : "内容名称"
	}, {
		field : 'bgImg',
		sortable : false,
		title : "背景图",
        formatter : function(value, row, index) {
            if(value != null && value != ""){
                return "<a style=\"cursor:pointer\" href=\""+value+"\" target=\"_Blank\">查看</a>";
            }
        }
	}, {
        field : 'focusImg',
        sortable : true,
        title : "焦点图",
        formatter : function(value, row, index) {
            if(value != null && value != ""){
                return "<a style=\"cursor:pointer\" href=\""+value+"\" target=\"_Blank\">查看</a>";
            }
        }
    }, {
        field : 'blankUrl',
        sortable : false,
        title : "跳转地址"
    }, {
        field : 'upPx',
        sortable : true,
        title : "上"
    }, {
        field : 'downPx',
        sortable : false,
        title : "下"
    }, {
        field : 'leftPx',
        sortable : false,
        title : "左"
    }, {
        field : 'rightPx',
        sortable : false,
        title : "右"
    }, {
        field : 'contentId',
        sortable : true,
        title : "内容Id"
    }
	];
	
	jQuery(function($) {
		initTable('table-data', columns, ajaxListUrl, 'frmSearch');
		// 搜索域控制========================================
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
			},false);
		});

		$('#edt').on('click', function(e) {
			showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function() {
				$('#table-data').bootstrapTable('refresh', { silent : true });
			},false);
		});

		$('#del').on('click', function(e) {
			doDelete(ajaxDelUrl, "table-data", function() {
				$('#table-data').bootstrapTable('selectPage', 1);
			});
		});

		//资源绑定相关js
        var ajaxListChildUrl = contextPath + '/contentManage/indexConfChild/list';
        var ajaxAddChildUrl = contextPath + '/contentManage/indexConfChild/add';
        var ajaxEdtChildUrl = contextPath + '/contentManage/indexConfChild/edit';
        var ajaxDelChildUrl = contextPath + '/contentManage/indexConfChild/remove';
        
        $('#table-data').on('load-success.bs.table', function() {
            var $table = $(this);
            var data = $table.bootstrapTable('getData');
            if(data && data.length > 0){
                $table.bootstrapTable('check', 0);
                $('#frmSearchChild').find('#indexConfId').val(data[0].id);
                initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
            }else{
                initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
            }
        });

        $('#table-data').on('check.bs.table', function(elem, row) {
            $('#frmSearchChild').find('#indexConfId').val(row.id);
            $('#table-data-child').bootstrapTable('refresh',{ silent : true });//刷新表单
        });

        $('#addChild').on('click', function(e) {
            var selections = $('#table-data').bootstrapTable("getSelections");
           	if(selections.length != 0){
	            //新增
	            showAddDlg("新增", ajaxAddChildUrl+"/"+$("#indexConfId").val(), "formDlg", function() {
	                $('#table-data-child').bootstrapTable('refresh', { silent : true });
	            },false);
           	}else{
               CmMsg.warn("请先选择首页配置！！！", -1);
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