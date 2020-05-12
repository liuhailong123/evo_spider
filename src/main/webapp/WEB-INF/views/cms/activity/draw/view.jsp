<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#resource" data-toggle="tab">抽奖池管理</a></li>
        </ul>
        <div class="tab-content">
            <div id="resource">
                <div class="widget-box transparent" id="divSearch" hidden="true">
                    <div class="widget-header widget-header-large">
                        <h4 class="widget-title">搜索</h4>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
                                <input type="hidden" id="type" name="search_EQ_type" value="${type}"/>
                                <label> 名称:&nbsp;<input name="search_LIKE_name" class="form-control"
                                                        placeholder="请输入名称" type="text">
                                </label>
                                <button id="btnSearch" type="button"
                                        class="btn btn-primary btn-sm form-control">
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
                                <so:hasPermission name="Activity:Draw:add">
                                    <button id="add" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Activity:Draw:modify">
                                    <button id="edt" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Activity:Draw:remove">
                                    <button id="del" class="btn btn-xs btn-danger" type="button">
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Activity:Draw:search">
                                    <button id="search" class="btn btn-xs btn-primary" type="button">
                                        <span class="glyphicon glyphicon-search"></span>&nbsp;查询
                                    </button>
                                </so:hasPermission>
                            </div>
                        </div>
                        <div class="widget-toolbar">
                            <a data-action="reload" class="green2 bigger-120" href="#"><i
                                    class="ace-icon fa fa-refresh"></i></a> <a data-action="fullscreen"
                                                                               class="blue2 bigger-120" href="#"><i
                                class="ace-icon fa fa-arrows-alt"></i></a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main no-padding">
                            <table id="table-data"
                                   data-classes="table table-striped table-hover"
                                   data-pagination="true"
                                   data-single-select="false"
                                   data-id-field="id"
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
            <li class="active"><a href="#vip" data-toggle="tab">奖品设置</a></li>
        </ul>
        <form id="frmSearchChild">
            <input type="hidden" id="drawId" name="search_EQ_drawId" value="0"/>
        </form>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="vip">
                <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                    <div class="widget-header widget-header-large no-padding">
                        <div class="widget-title smaller">
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="Activity:Draw:add">
                                    <button id="addChild" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Activity:Draw:modify">
                                    <button id="edtChild" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Activity:Draw:remove">
                                    <button id="delChild" class="btn btn-xs btn-danger" type="button">
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                            </div>
                        </div>
                        <div class="widget-toolbar">
                            <a data-action="reload" class="green2 bigger-120" href="#"><i
                                    class="ace-icon fa fa-refresh"></i></a> <a data-action="fullscreen"
                                                                               class="blue2 bigger-120" href="#"><i
                                class="ace-icon fa fa-arrows-alt"></i></a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main no-padding">
                            <table id="table-data-child"
                                   data-classes="table table-striped table-hover"
                                   data-pagination="true" data-id-field="id" data-unique-id="id"
                                   data-sort-name="sort" data-sort-order="asc">
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
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/activity/draw/list';
        var ajaxAddUrl = contextPath + '/activity/draw/add';
        var ajaxEdtUrl = contextPath + '/activity/draw/edit';
        var ajaxDelUrl = contextPath + '/activity/draw/remove';
        //====================================================================================================
        //输出字典js变量
        <x:dictData dictCode="Menu_Status" var="dictStatus">
        <x:jsonArray varName="mStatus" keyName="code" valName="name" items="${dictStatus}" ></x:jsonArray>
        </x:dictData>
        <x:dictData dictCode="isNeedOrder" var="isNeedOrder">
        <x:jsonArray varName="isNeedOrder" keyName="code" valName="name" items="${isNeedOrder}" ></x:jsonArray>
        </x:dictData>

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: true,
            title: "名称"
        }, {
            field: 'beginTime',
            sortable: true,
            title: "开始时间"
        }, {
            field: 'endTime',
            sortable: true,
            title: "结束时间"
        }, {
            field: 'enable',
            sortable: true,
            title: "是否启用",
            formatter: function (value, row, index) {
                var showName = value;
                $.each(mStatus, function () {
                    if (this.code == value) {
                        showName = this.name;
                    }
                });
                return showName;
            }
        }
        ];

        //=====================================================================================================

        //输出字典js变量

        var childColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: false,
            title: "奖品名称"
        }, {
            field: 'count',
            sortable: false,
            title: "奖品数量"
        }, {
            field: 'nowCount',
            sortable: false,
            title: "剩余数量"
        }, {
            field: 'probability',
            sortable: false,
            title: "中奖概率",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return value + "%";
                }
            }
        }, {
            field: 'sort',
            sortable: true,
            title: "排序"
        }, {
            field: 'isEffective',
            sortable: true,
            title: "是否有效奖项",
            formatter: function (value, row, index) {
                var showName = value;
                $.each(isNeedOrder, function () {
                    if (this.code == value) {
                        showName = this.name;
                    }
                });
                return showName;
            }
        }, {
            field: 'enable',
            sortable: false,
            title: "是否启用",
            formatter: function (value, row, index) {
                var showName = value;
                $.each(mStatus, function () {
                    if (this.code == value) {
                        showName = this.name;
                    }
                });
                return showName;
            }
        },
        ];

        jQuery(function ($) {
            initTable('table-data', columns, ajaxListUrl, 'frmSearch');

            $('#search').on('click', function () {
                $('#divSearch').slideToggle("slow");
            });

            // 搜索域控制
            $('#frmSearch').find('#btnSearch').on('click', function () {
                $('#table-data').bootstrapTable('selectPage', 1);
            });

            $('#tbWidget').on('reload.ace.widget', function (ev) {
                $('#table-data').bootstrapTable('refresh', {silent: true});
            });

            $('#add').on('click', function (e) {
                showAddDlg("新增", ajaxAddUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                }, false);
            });

            $('#edt').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, false);
            });

            $('#del').on('click', function (e) {
                doDelete(ajaxDelUrl, "table-data", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });

            var ajaxListChildUrl = contextPath + '/activity/drawChild/list';
            var ajaxaddChildUrl = contextPath + '/activity/drawChild/add';
            var ajaxedtChildUrl = contextPath + '/activity/drawChild/edit';
            var ajaxdelChildUrl = contextPath + '/activity/drawChild/remove';

            $('#table-data').on('load-success.bs.table', function () {
                var $table = $(this);
                var data = $table.bootstrapTable('getData');
                if (data && data.length > 0) {
                    $table.bootstrapTable('check', 0);
                    var rowId = data[0].id;
                    if (rowId == null || rowId == '') {
                        $('#frmSearchChild').find('#drawId').val(0);
                    } else {
                        $('#frmSearchChild').find('#drawId').val(data[0].id);
                    }
// 	                $('#frmSearchChild').find('#sourceId').val(data[0].id);
                    initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
                } else {
                    initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
                }
            });

            $('#table-data').on('check.bs.table', function (elem, row) {
                $('#frmSearchChild').find('#sourceId').val(row.id);
                $('#table-data-child').bootstrapTable('refresh', {silent: true});//刷新表单
            });

            $('#addChild').on('click', function (e) {
                //新增
                showAddDlg("新增", ajaxaddChildUrl + "/" + $("#drawId").val(), "formDlg", function () {
                    $('#table-data-child').bootstrapTable('refresh', {silent: true});
                });
            });

            $('#edtChild').on('click', function (e) {
                showEdtDlg("编辑", ajaxedtChildUrl + "/" + $("#drawId").val(), "table-data-child", "formDlg", function () {
                    $('#table-data-child').bootstrapTable('refresh', {silent: true});
                })
            });

            $('#delChild').on('click', function (e) {
                doDelete(ajaxdelChildUrl, "table-data-child", function () {
                });
            });
        });
    });
</script>