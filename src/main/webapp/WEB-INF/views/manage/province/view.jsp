<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="widget-box transparent" id="divSearch" hidden="true">
        <div class="widget-header widget-header-large">
            <h4 class="widget-title">搜索</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
                    <label> 名称:&nbsp;
                        <input name="search_LIKE_name" class="form-control" placeholder="请输入名称" type="text">
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
                    <so:hasPermission name="Manage:Province:add">
                        <button id="add" class="btn btn-xs btn-pink" type="button">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Manage:Province:modify">
                        <button id="edt" class="btn btn-xs btn-success" type="button">
                            <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Manage:Province:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button">
                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Manage:Province:search">
                        <button id="search" class="btn btn-xs btn-primary" type="button">
                            <span class="glyphicon glyphicon-search"></span>&nbsp;查询
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
                <table id="table-data" data-classes="table table-striped table-hover" data-pagination="true"
                       data-id-field="id" data-unique-id="id" data-sort-name="id" data-sort-order="asc">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12">&nbsp;</div>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#constant" data-toggle="tab">详细配置</a></li>
        </ul>
        <form id="frmConstant">
            <input type="hidden" id="provinceId" name="search_EQ_province.id"/>
        </form>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="constant">
                <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                    <div class="widget-header widget-header-large no-padding">
                        <div class="widget-title smaller">
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="Manage:Province:add">
                                    <button id="addConstant" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Manage:Province:modify">
                                    <button id="edtConstant" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Manage:Province:remove">
                                    <button id="delConstant" class="btn btn-xs btn-danger" type="button">
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                            </div>
                        </div>
                        <div class="widget-toolbar">
                            <a data-action="reload" class="green2 bigger-120" href="#"><i
                                    class="ace-icon fa fa-refresh"></i></a>
                            <a data-action="fullscreen" class="blue2 bigger-120" href="#">
                                <i class="ace-icon fa fa-arrows-alt"></i></a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main no-padding">
                            <table id="table-data-constant"
                                   data-classes="table table-striped table-hover"
                                   data-pagination="true" data-id-field="id" data-unique-id="id"
                                   data-sort-name="constantKey" data-sort-order="asc">
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
    $('.page-content-area').ace_ajax(
        'loadScripts',
        scripts,
        function () {
            var ajaxListUrl = contextPath + '/manage/province/list';
            var ajaxAddUrl = contextPath + '/manage/province/add';
            var ajaxEdtUrl = contextPath + '/manage/province/edit';
            var ajaxDelUrl = contextPath + '/manage/province/remove';

            //输出字典js变量
            <x:dictData dictCode="Menu_Status" var="dictStatus">
            <x:jsonArray varName="mStatus" keyName="code" valName="name" items="${dictStatus}" ></x:jsonArray>
            </x:dictData>

            var columns = [{
                field: 'state',
                checkbox: true,
                title: "选择"
            }, {
                field: 'name',
                sortable: false,
                title: "名称"
            }, {
                field: 'code',
                sortable: false,
                title: "编码"
            }, {
                field: 'ftpUrl',
                sortable: false,
                title: "FTP服务器地址"
            }, {
                field: 'ftpUser',
                sortable: false,
                title: "FTP用户名"
            }, {
                field: 'ftpPassword',
                sortable: false,
                title: "FTP密码"
            }, {
                field: 'ftpHost',
                sortable: false,
                title: "FTP访问域名"
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
            },];

            var ajaxListConstantUrl = contextPath + '/manage/provinceConstant/list';
            var ajaxAddConstantUrl = contextPath + '/manage/provinceConstant/add';
            var ajaxEdtConstantUrl = contextPath + '/manage/provinceConstant/edit';
            var ajaxDelConstantUrl = contextPath + '/manage/provinceConstant/remove';

            var constantColumns = [{
                field: 'state',
                checkbox: true,
                title: "选择"
            }, {
                field: 'info',
                sortable: false,
                title: "常量说明"
            }, {
                field: 'constantKey',
                sortable: false,
                title: "常量key"
            }, {
                field: 'constantValue',
                sortable: false,
                title: "常量值"
            },{
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


                $('#table-data').on('load-success.bs.table', function () {
                    var $table = $(this);
                    var data = $table.bootstrapTable('getData');
                    if (data && data.length > 0) {
                        $table.bootstrapTable('check', 0);
                        $('#frmConstant').find('#provinceId').val(data[0].id);
                        initTable('table-data-constant', constantColumns, ajaxListConstantUrl, 'frmConstant');
                    } else {
                        initTable('table-data-constant', constantColumns, ajaxListConstantUrl, 'frmConstant');
                    }
                });

                $('#table-data').on('check.bs.table', function (elem, row) {
                    $('#frmConstant').find('#provinceId').val(row.id);
                    $('#table-data-constant').bootstrapTable('refresh', {silent: true});//刷新表单
                });


                $('#addConstant').on('click', function (e) {
                    //新增
                    showAddDlg("新增", ajaxAddConstantUrl + "/" + $("#provinceId").val(), "formDlg", function () {
                        $('#table-data-constant').bootstrapTable('refresh', {silent: true});
                    });
                });

                $('#edtConstant').on('click', function (e) {
                    showEdtDlg("编辑", ajaxEdtConstantUrl + "/" + $("#provinceId").val(), "table-data-constant", "formDlg", function () {
                        $('#table-data-constant').bootstrapTable('refresh', {silent: true});
                    })
                });

                $('#delConstant').on('click', function (e) {
                    doDelete(ajaxDelConstantUrl, "table-data-constant", function () {
                        $('#table-data-constant').bootstrapTable('selectPage', 1);
                    });
                });
            });
        });
</script>