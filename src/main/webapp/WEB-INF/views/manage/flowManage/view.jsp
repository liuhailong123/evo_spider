<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="widget-box transparent" id="divSearch">
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
        <div class="widget-body">
            <div class="widget-main no-padding">
                <table id="table-data" data-single-select="true" data-classes="table table-striped table-hover"
                       data-pagination="true"
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
            <li class="active"><a href="#flowManage" data-toggle="tab">流程配置</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="flowManage">
                <div class="widget-box transparent">
                    <div class="widget-header widget-header-large">
                        <h4 class="widget-title">搜索</h4>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form id="frmFlowManage" target="_self" class="form-inline searchCondition">
                                <input type="hidden" id="provinceId" name="search_EQ_province.id"/>
                                <label> 流程类型:
                                    <x:dictData dictCode="Flow_Type" var="flowType">
                                        <x:select var="flowType" items="${flowType}" id="type" name="search_EQ_type" className="form-control">
                                            <x:option value="${flowType.code }" text="${flowType.name }" selected=""></x:option>
                                        </x:select>
                                    </x:dictData>
                                </label>
                                <button id="flowManageBtnSearch" type="button" class="btn btn-primary btn-sm form-control">
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
                                <so:hasPermission name="Manage:PlayAuthFlow:add">
                                    <button id="addFlowManage" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Manage:PlayAuthFlow:modify">
                                    <button id="edtFlowManage" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Manage:PlayAuthFlow:modify">
                                    <button id="copyFlowManage" class="btn btn-xs btn-info" type="button">
                                        <span class="glyphicon glyphicon-tint"></span>&nbsp;复制
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="Manage:PlayAuthFlow:remove">
                                    <button id="delFlowManage" class="btn btn-xs btn-danger" type="button">
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
                            <table id="table-data-flowManage"
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

            var ajaxListFlowManageUrl = contextPath + '/manage/flowManage/list';
            var ajaxAddFlowManageUrl = contextPath + '/manage/flowManage/add';
            var ajaxEdtFlowManageUrl = contextPath + '/manage/flowManage/edit';
            var ajaxDelFlowManageUrl = contextPath + '/manage/flowManage/remove';
            var ajaxCopyFlowManageUrl = contextPath + '/manage/flowManage/copy';

            var flowManageColumns = [{
                field: 'state',
                checkbox: true,
                title: "选择"
            }, {
                field: 'name',
                sortable: false,
                title: "流程名称"
            }, {
                field: 'info',
                sortable: false,
                width: "30%",
                title: "流程说明"
            }, {
                field: 'classPath',
                sortable: false,
                title: "类路径"
            }, {
                field: 'funcName',
                sortable: false,
                title: "方法名称"
            }, {
                field: 'sort',
                sortable: false,
                title: "排序"
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
                        $('#frmFlowManage').find('#provinceId').val(data[0].id);
                        initTable('table-data-flowManage', flowManageColumns, ajaxListFlowManageUrl, 'frmFlowManage');
                    } else {
                        initTable('table-data-flowManage', flowManageColumns, ajaxListFlowManageUrl, 'frmFlowManage');
                    }
                });

                $('#table-data').on('check.bs.table', function (elem, row) {
                    $('#frmFlowManage').find('#provinceId').val(row.id);
                    $('#table-data-flowManage').bootstrapTable('refresh', {silent: true});//刷新表单
                });


                // 搜索域控制
                $('#frmFlowManage').find('#flowManageBtnSearch').on('click', function () {
                    $('#table-data-flowManage').bootstrapTable('selectPage', 1);
                });

                $('#addFlowManage').on('click', function (e) {
                    //新增
                    showAddDlg("新增", ajaxAddFlowManageUrl + "/" + $("#provinceId").val(), "formDlg", function () {
                        $('#table-data-flowManage').bootstrapTable('refresh', {silent: true});
                    }, false);
                });

                $('#edtFlowManage').on('click', function (e) {
                    showEdtDlg("编辑", ajaxEdtFlowManageUrl + "/" + $("#provinceId").val(), "table-data-flowManage", "formDlg", function () {
                        $('#table-data-flowManage').bootstrapTable('refresh', {silent: true});
                    }, false)
                });

                $('#copyFlowManage').on('click', function (e) {
                    showCopyDlg("复制", ajaxCopyFlowManageUrl, "table-data-flowManage", "formDlg", function () {
                        $('#table-data-flowManage').bootstrapTable('refresh', {silent: true});
                    })
                });

                $('#delFlowManage').on('click', function (e) {
                    doDelete(ajaxDelFlowManageUrl, "table-data-flowManage", function () {
                        $('#table-data-flowManage').bootstrapTable('selectPage', 1);
                    });
                });
            });
        });


    function showCopyDlg(title, ajaxUrl, tableId, formId, callback, hasFile, size) {
        var selections = $('#' + tableId).bootstrapTable("getSelections");//获取选中的行号
        //判断是否选中了一行
        if (selections !== undefined && selections !== null) {
            if (selections.length >= 1) {
                var ids = new Array();
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i].id);
                }
                var params = {'ids': ids};

                //把选中的行号拼装到该请求的路径的后面(用来区分不同的请求)
                var formUrl = ajaxUrl;
                //打开编辑的模态框
                BootstrapDialog.show({
                    type: BootstrapDialog.TYPE_PRIMARY,
                    size: size,
                    closable: true,
                    draggable: true,
                    closeByBackdrop: false,
                    title: title,
                    //加载远程页面,即想页面发送请求参数为当前选中行的id
                    message: $('<div></div>').load(formUrl),
                    //定义模态框中的按钮
                    buttons: [{
                        id: 'btn-Cancel',
                        icon: 'glyphicon glyphicon-remove',
                        label: '关闭',
                        cssClass: 'btn-default',
                        autospin: false,
                        action: function (dialogRef) {
                            dialogRef.close();
                        }
                    }, {
                        id: 'btn-OK',
                        icon: 'glyphicon glyphicon-check',
                        label: '确认',
                        cssClass: 'btn-primary',
                        autospin: false,
                        //点击确认按钮之后需要执行的函数
                        action: function (dialogRef) {
                            var $button = this;
                            $button.disable();
                            $button.toggleSpin(true);
                            if ($('#' + formId).valid()) {
                                $.post(ajaxUrl + "/" + $("#code").val(), params).done(function (data) {
                                    if (data.status == 'OK') {
                                        dialogRef.close();
                                        $('#' + tableId).bootstrapTable('selectPage', 1);
                                        CmMsg.success(data.message, -1);
                                        if (callback) {
                                            callback();
                                        }
                                    } else if (data.status == 'ERROR') {
                                        CmMsg.error(data.message, -1);
                                    } else if (data.status == 'TIMEOUT') {
                                        CmMsg.error(data.message, -1);
                                    } else {
                                        CmMsg.error("操作失败了", -1);
                                    }
                                });
                            } else {
                                $button.toggleSpin(false);
                                $button.enable();
                            }
                        }
                    }]
                });
            } else {
                CmMsg.warn("请至少选择一行数据记录进行操作.", -1);
            }
        } else {
            CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
        }
    }

</script>