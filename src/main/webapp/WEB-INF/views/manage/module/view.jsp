<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../include.inc.jsp" %>
<div class="col-lg-3">
    <div class="widget-box transparent">
        <div class="widget-header widget-header-large">
            <h4 class="widget-title lighter smaller">选择菜单</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main padding-8">
                <div id="treeview" role="tree"></div>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-9">
    <div class="widget-box transparent" id="divSearch" hidden="true">
        <div class="widget-header widget-header-large">
            <h4 class="widget-title">搜索</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <form name="frmSearch" id="frmSearch" target="_self"
                      class="form-inline searchCondition">
                    <input id="pid" name="search_EQ_parent.id" type="hidden" value="">
                    <label> 名称:&nbsp;<input name="search_LIKE_name"
                                            class="form-control" placeholder="请输入名称" type="text">
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
                    <so:hasPermission name="Manage:Module:add">
                        <button id="add" class="btn btn-xs btn-pink" type="button">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Manage:Module:modify">
                        <button id="edt" class="btn btn-xs btn-success" type="button">
                            <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Manage:Module:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button">
                            <span class="glyphicon glyphicon-trash"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Manage:Module:search">
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
                       data-id-field="id" data-unique-id="id" data-sort-name="priority"
                       data-sort-order="asc">
                </table>
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
            jQuery(function ($) {
                var ajaxListUrl = contextPath + '/manage/module/list';
                var ajaxTreeUrl = contextPath + '/manage/module/tree/4';
                var ajaxAddUrl = contextPath + '/manage/module/add';
                var ajaxEdtUrl = contextPath + '/manage/module/edit';
                var ajaxDelUrl = contextPath + '/manage/module/remove';

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
                    field: 'icon',
                    sortable: false,
                    align: 'center',
                    title: "图标",
                    formatter: function (value, row, index) {
                        return "<i class='" + value + "'></i>";
                    }
                }, {
                    field: 'url',
                    sortable: false,
                    title: "链接"
                }, {
                    field: 'status',
                    sortable: true,
                    title: "状态",
                    formatter: function (value, row, index) {
                        var showName = value;
                        $.each(mStatus, function () {
                            if (this.code == value) {
                                showName = this.name;
                            }
                        });
                        return showName;
                    }
                }, {
                    field: 'priority',
                    sortable: true,
                    title: "排序(优先级)"
                }, {
                    field: 'parent.name',
                    sortable: false,
                    title: "上级"
                }, {
                    field: 'createDate',
                    sortable: true,
                    title: "创建时间"
                }, {
                    field: 'modifyDate',
                    sortable: true,
                    title: "修改时间"
                },];

                initTree("treeview", ajaxTreeUrl, false, {
                    loadNode: function (elem, selection) {
                        if (selection.status && selection.node.parent == '#') {
                            $('#frmSearch').find('#pid').val(selection.node.id);
                            initTable('table-data', columns, ajaxListUrl, 'frmSearch');
                        }
                    },
                    selNode: function (node, elem) {
                        $('#frmSearch').find('#pid').val(node.id);
                        $('#table-data').bootstrapTable('selectPage', 1);
                    }
                });

                $('#search').on('click', function () {
                    $('#divSearch').slideToggle("slow");
                });

                // 搜索域控制
                $('#frmSearch').find('#btnSearch').on('click', function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });

                $('#tbWidget').on('reload.ace.widget', function (ev) {
                    $('#table-data').bootstrapTable('refresh', {
                        silent: true
                    });
                });

                $('#add').on('click', function (e) {
                    showAddDlg("新增", ajaxAddUrl, "formDlg", function () {
                        $('#table-data').bootstrapTable('selectPage', 1);
                        var $JsTree = $.jstree.reference("treeview");
                        $JsTree.refresh();
                    }, false);
                });

                $('#edt').on('click', function (e) {
                    showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function () {
                        $('#table-data').bootstrapTable('refresh', {silent: true});
                        var $JsTree = $.jstree.reference("treeview");
                        $JsTree.refresh();
                    }, false);
                });

                $('#del').on('click', function (e) {
                    doDelete(ajaxDelUrl, "table-data", function () {
                        $('#table-data').bootstrapTable('selectPage', 1);
                        var $JsTree = $.jstree.reference("treeview");
                        $JsTree.refresh();
                    });
                });
            });
        });
</script>