<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#config" data-toggle="tab">支付配置</a></li>
        </ul>
    </div>
    <div class="tab-content">
        <div class="tab-pane fade in active" id="config">
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
                            <label> 省网编码:&nbsp;
                                <input name="search_LIKE_provinceCode" class="form-control" placeholder="请输入省网编码"
                                       type="text">
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
                            <so:hasPermission name="ProductCharge:PayConfig:add">
                                <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                        class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                </button>
                            </so:hasPermission>
                            <so:hasPermission name="ProductCharge:PayConfig:modify">
                                <button id="edt" class="btn btn-xs btn-success" type="button"><span
                                        class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                </button>
                            </so:hasPermission>
                            <so:hasPermission name="ProductCharge:PayConfig:remove">
                                <button id="del" class="btn btn-xs btn-danger" type="button"><span
                                        class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                </button>
                            </so:hasPermission>
                            <so:hasPermission name="ProductCharge:PayConfig:search">
                                <button id="search" class="btn btn-xs btn-primary" type="button"><span
                                        class="glyphicon glyphicon-search"></span>&nbsp;查询
                                </button>
                            </so:hasPermission>
                        </div>
                    </div>
                    <div class="widget-toolbar">
                        <a data-action="reload" class="green2 bigger-120" href="#"><i
                                class="ace-icon fa fa-refresh"></i></a>
                        <a data-action="fullscreen" class="blue2 bigger-120" href="#"><i
                                class="ace-icon fa fa-arrows-alt"></i></a>
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
    </div>
</div>
<div class="col-lg-12">&nbsp;</div>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#params" data-toggle="tab">接口参数</a></li>
        </ul>
        <form id="frmParams">
            <input type="hidden" id="configId" name="search_EQ_configId"/>
        </form>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="params">
                <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                    <div class="widget-header widget-header-large no-padding">
                        <div class="widget-title smaller">
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="ProductCharge:PayConfig:add">
                                    <button id="addParams" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ProductCharge:PayConfig:modify">
                                    <button id="edtParams" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ProductCharge:PayConfig:remove">
                                    <button id="delParams" class="btn btn-xs btn-danger" type="button">
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
                            <table id="table-data-params"
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
        var ajaxListUrl = contextPath + '/productCharge/payConfig/list';
        var ajaxAddUrl = contextPath + '/productCharge/payConfig/add';
        var ajaxEdtUrl = contextPath + '/productCharge/payConfig/edit';
        var ajaxDelUrl = contextPath + '/productCharge/payConfig/remove';

        <x:dictData dictCode="Pay_Type" var="payType">
        <x:jsonArray varName="payType" keyName="code" valName="name" items="${payType}" ></x:jsonArray>
        </x:dictData>
        <x:dictData dictCode="OFF_ON" var="offOn">
        <x:jsonArray varName="offOn" keyName="code" valName="name" items="${offOn}" ></x:jsonArray>
        </x:dictData>

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: false,
            title: "产品名称"
        }, {
            field: 'payType',
            sortable: false,
            title: "支付方式",
            formatter: function (value, row, index) {
                var show = value;
                $.each(payType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'remark',
            sortable: false,
            title: "备注"
        }, {
            field: 'provinceCode',
            sortable: false,
            title: "省网编码"
        }, {
            field: 'enable',
            sortable: false,
            title: "是否启用",
            formatter: function (value, row, index) {
                var show = value;
                $.each(offOn, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        },
        ];

        var ajaxListParamsUrl = contextPath + '/productCharge/payConfigParams/list';
        var ajaxAddParamsUrl = contextPath + '/productCharge/payConfigParams/add';
        var ajaxEdtParamsUrl = contextPath + '/productCharge/payConfigParams/edit';
        var ajaxDelParamsUrl = contextPath + '/productCharge/payConfigParams/remove';

        <x:dictData dictCode="Charge_Mode_Type" var="ChargeModeType">
        <x:jsonArray varName="ChargeModeType" keyName="code" valName="name" items="${ChargeModeType}" ></x:jsonArray>
        </x:dictData>


        var paramsColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'sort',
            sortable: false,
            title: "排序"
        }, {
            field: 'nameEn',
            sortable: false,
            title: "参数（英文）"
        }, {
            field: 'nameCn',
            sortable: false,
            title: "参数（中文）"
        }, {
            field: 'value',
            sortable: false,
            title: "默认值"
        },
        ];


        jQuery(function ($) {
            initTable('table-data', columns, ajaxListUrl, 'frmSearch');

            // 搜索域控制
            $('#search').on('click', function () {
                $('#divSearch').slideToggle("slow");
            });
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
                    $('#frmParams').find('#configId').val(data[0].id);
                    initTable('table-data-params', paramsColumns, ajaxListParamsUrl, 'frmParams');
                } else {
                    $('#frmParams').find('#configId').val("-1");
                    $('#table-data-params').bootstrapTable('refresh', {silent: true});
                }
            });

            $('#table-data').on('check.bs.table', function (elem, row) {
                $('#frmParams').find('#configId').val(row.id);
                $('#table-data-params').bootstrapTable('refresh', {silent: true});//刷新表单
            });

            $('#addParams').on('click', function (e) {
                var configId = $("#configId").val();
                if (configId == -1 || configId == '') {
                    CmMsg.warn("请选中支付配置信息.", -1);
                } else {
                    //新增
                    showAddDlg("新增", ajaxAddParamsUrl + "/" + $("#configId").val(), "formDlg", function () {
                        $('#table-data-params').bootstrapTable('refresh', {silent: true});
                    }, false);
                }
            });

            $('#edtParams').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtParamsUrl + "/" + $("#configId").val(), "table-data-params", "formDlg", function () {
                    $('#table-data-params').bootstrapTable('refresh', {silent: true});
                }, false)
            });

            $('#delParams').on('click', function (e) {
                doDelete(ajaxDelParamsUrl, "table-data-params", function () {
                });
            });

        });
    });
</script>