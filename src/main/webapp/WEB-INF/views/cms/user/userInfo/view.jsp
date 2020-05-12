<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="widget-box transparent" id="divSearch" hidden="true">
        <div class="widget-header widget-header-large">
            <h4 class="widget-title">搜索</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
                    <label> 用户id:&nbsp;
                        <input name="search_EQ_id" class="form-control" placeholder="请输入用户ID" type="text">
                    </label>
                    <label> 用户名称:&nbsp;
                        <input name="search_LIKE_name" class="form-control" placeholder="请输入用户名称" type="text">
                    </label>
                    <label> 用户手机号:&nbsp;
                        <input name="search_LIKE_phone" class="form-control" placeholder="请输入用户手机号" type="text">
                    </label>
                    <label> 用户身份证号:&nbsp;
                        <input name="search_LIKE_idCard" class="form-control" placeholder="请输入用户身份证号" type="text">
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
                    <so:hasPermission name="User:UserInfo:add">
                        <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="User:UserInfo:modify">
                        <button id="edt" class="btn btn-xs btn-success" type="button"><span
                                class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="User:UserInfo:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button"><span
                                class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="User:UserInfo:search">
                        <button id="search" class="btn btn-xs btn-primary" type="button"><span
                                class="glyphicon glyphicon-search"></span>&nbsp;查询
                        </button>
                    </so:hasPermission>
                </div>
                <div id="toolbar2" class="btn-group btn-corner">
                    <so:hasPermission name="User:UserInfo:modify">
                        <button id="serverExcel" class="btn btn-xs btn btn-inverse tooltip-info" type="button"
                                data-rel="tooltip" data-placement="bottom" title="按照导入模板，导入用户会员信息。(仅导入信息，不调用其他局方接口)">
                            <span class="fa fa-cloud-download"></span>&nbsp;会员导入
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="User:UserInfo:modify">
                        <button id="serverExcelOpen" class="btn btn-xs btn btn-pink tooltip-info" type="button"
                                data-rel="tooltip" data-placement="bottom"
                                title="按照开通模板，将真实执行开通流程(只支持boss支付，等同于用户点击开通功能，慎用！！！)">
                            <span class="fa fa-folder-open-o"></span>&nbsp;服务开通
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="User:UserInfo:modify">
                        <button id="serverOpenProgress" class="btn btn-xs btn btn-default tooltip-info" type="button"
                                data-rel="tooltip" data-placement="bottom">
                            <span class="fa fa-folder-open-o"></span>&nbsp;导入进度
                        </button>
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
            <li class="active"><a href="#userAccount" data-toggle="tab">账号设备管理</a></li>
        </ul>
        <form id="frmUserAccount">
            <input type="hidden" id="userId" name="search_EQ_userId"/>
        </form>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="accountUser">
                <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                    <div class="widget-header widget-header-large no-padding">
                        <div class="widget-title smaller">
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="User:UserInfo:add">
                                    <button id="addAccountUser" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="User:UserInfo:modify">
                                    <button id="edtAccountUser" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="User:UserInfo:Group:remove">
                                    <button id="delAccountUser" class="btn btn-xs btn-danger" type="button">
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
                            <table id="table-data-accountUser"
                                   data-classes="table table-striped table-hover"
                                   data-pagination="true" data-id-field="id" data-unique-id="id"
                                   data-sort-name="createDate" data-sort-order="asc">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="my-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="smaller lighter blue no-margin">导入进度</h3>
            </div>
            <div class="modal-body">
                <h1>总数：<span id="totalCount">0</span></h1>
                <h1>成功：<span id="successCount">0</span></h1>
                <h1>失败：<span id="failCount">0</span></h1>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('[data-rel=tooltip]').tooltip();
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/user/userInfo/list';
        var ajaxAddUrl = contextPath + '/user/userInfo/add';
        var ajaxEdtUrl = contextPath + '/user/userInfo/edit';
        var ajaxDelUrl = contextPath + '/user/userInfo/remove';
        var ajaxDataImportUrl = contextPath + '/user/userInfo/dataImport';
        var ajaxServerOpenUrl = contextPath + '/user/userInfo/serverOpen';

        <x:dictData dictCode="Sex_Type" var="sexType">
        <x:jsonArray varName="sexType" keyName="code" valName="name" items="${sexType}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="User_Status" var="userStatus">
        <x:jsonArray varName="userStatus" keyName="code" valName="name" items="${userStatus}" ></x:jsonArray>
        </x:dictData>

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'id',
            sortable: false,
            title: "userId"
        }, {
            field: 'name',
            sortable: false,
            title: "姓名"
        }, {
            field: 'phone',
            sortable: false,
            title: "手机号"
        }, {
            field: 'openId',
            sortable: false,
            title: "微信OpenId"
        }, {
            field: 'idCard',
            sortable: false,
            title: "身份证号"
        }, {
            field: 'sex',
            sortable: false,
            title: "性别",
            formatter: function (value, row, index) {
                var show = value;
                $.each(sexType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'brithday',
            sortable: false,
            title: "生日"
        }, {
            field: 'email',
            sortable: false,
            title: "电子邮箱"
        }, {
            field: 'status',
            sortable: false,
            title: "用户状态",
            formatter: function (value, row, index) {
                var show = value;
                $.each(userStatus, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }
        ];

        var ajaxListAccountUserUrl = contextPath + '/user/userAccount/list';
        var ajaxAddAccountUserUrl = contextPath + '/user/userAccount/add';
        var ajaxEdtAccountUserUrl = contextPath + '/user/userAccount/edit';
        var ajaxDelAccountUserUrl = contextPath + '/user/userAccount/remove';

        <x:dictData dictCode="User_Account_Type" var="AccountType">
        <x:jsonArray varName="AccountType" keyName="code" valName="name" items="${AccountType}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="Equipment_Type" var="EquipmentType">
        <x:jsonArray varName="EquipmentType" keyName="code" valName="name" items="${EquipmentType}" ></x:jsonArray>
        </x:dictData>


        var accountUserColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'accountType',
            sortable: false,
            title: "账号类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(AccountType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'accountNo',
            sortable: false,
            title: "账号"
        }, {
            field: 'equipmentName',
            sortable: false,
            title: "设备名称"
        }, {
            field: 'equipmentId',
            sortable: false,
            title: "设备标识"
        }, {
            field: 'equipmentType',
            sortable: false,
            title: "设备类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(EquipmentType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'productCode',
            sortable: false,
            title: "产品编码"
        }, {
            field: 'channelCode',
            sortable: false,
            title: "渠道编码"
        }
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
                    $('#table-data-accountUser').bootstrapTable('refresh', {silent: true});
                });
            });

            $('#serverExcel').on('click', function (e) {
                showAddDlg("会员导入", ajaxDataImportUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});

                    $('#my-modal').modal('show');

                    // 2秒调用一次进度查询
                    window.setInterval(keepSession, 5 * 1000);
                }, true);
            });

            $('#serverExcelOpen').on('click', function (e) {
                showAddDlg("服务开通", ajaxServerOpenUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});

                    $('#my-modal').modal('show');

                    // 2秒调用一次进度查询
                    window.setInterval(keepSession, 5 * 1000);
                }, true);
            });
            $('#serverOpenProgress').on('click', function (e) {
                $('#my-modal').modal('show');
            });

            $('#table-data').on('load-success.bs.table', function () {
                var $table = $(this);
                var data = $table.bootstrapTable('getData');
                if (data && data.length > 0) {
                    $table.bootstrapTable('check', 0);
                    $('#frmUserAccount').find('#userId').val(data[0].id);
                    initTable('table-data-accountUser', accountUserColumns, ajaxListAccountUserUrl, 'frmUserAccount');
                } else {
                    initTable('table-data-accountUser', accountUserColumns, ajaxListAccountUserUrl, 'frmUserAccount');
                }
            });

            $('#table-data').on('check.bs.table', function (elem, row) {
                $('#frmUserAccount').find('#userId').val(row.id);
                $('#table-data-accountUser').bootstrapTable('refresh', {silent: true});//刷新表单
            });

            $('#addAccountUser').on('click', function (e) {
                //新增
                showAddDlg("新增", ajaxAddAccountUserUrl + "/" + $("#userId").val(), "formDlg", function () {
                    $('#table-data-accountUser').bootstrapTable('refresh', {silent: true});
                }, false);
            });

            $('#edtAccountUser').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtAccountUserUrl, "table-data-accountUser", "formDlg", function () {
                    $('#table-data-accountUser').bootstrapTable('refresh', {silent: true});
                }, false)
            });

            $('#delAccountUser').on('click', function (e) {
                doDelete(ajaxDelAccountUserUrl, "table-data-accountUser", function () {
                });
            });

        });
    });

    function keepSession() {
        $.ajax({
            type: "post",
            url: contextPath + '/user/userInfo/serverOpenProgress',
            success: function (data) {
                $("#totalCount").text(data.rows.total);
                $("#successCount").text(data.rows.success);
                $("#failCount").text(data.rows.fail);
            }
        });
    }
</script>