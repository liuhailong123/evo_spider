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
                    <input type="hidden" id="classify" name="search_EQ_classify" value="2"/>
                    <label> 分类名称:&nbsp;
                        <input name="search_LIKE_classifyTags" class="form-control" placeholder="请输入分类名称" type="text">
                    </label>
                    <label> 名称:&nbsp;
                        <input name="search_LIKE_name" class="form-control" placeholder="请输入内名称" type="text">
                    </label>
                    <label> 标题:&nbsp;
                        <input name="search_LIKE_title" class="form-control" placeholder="请输入标题" type="text">
                    </label>
                    <label> 编码:&nbsp;
                        <input name="search_LIKE_code" class="form-control" placeholder="请输入编码" type="text">
                    </label>
                    <label> 启用状态:&nbsp;
                        <x:dictData dictCode="Menu_Status" var="dictStatus">
                            <x:select defaultOption="请选择" hasDefault="true" var="status" items="${dictStatus}"
                                      id="enable" name="search_EQ_enable" className="form-control">
                                <x:option value="${status.code }" text="${status.name }"
                                          selected=""></x:option>
                            </x:select>
                        </x:dictData>
                    </label>
                    <label> 注入状态:&nbsp;
                        <x:dictData dictCode="Syn_Type" var="synType">
                            <x:select defaultOption="请选择" hasDefault="true" var="synType" items="${synType}"
                                      id="synType" name="search_EQ_synType" className="form-control">
                                <x:option value="${synType.code }" text="${synType.name }"
                                          selected=""></x:option>
                            </x:select>
                        </x:dictData>
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
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Episode:add">
                        <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Episode:modify">
                        <button id="edt" class="btn btn-xs btn-success" type="button"><span
                                class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Episode:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button"><span
                                class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:modify">
                        <button id="refresh" class="btn btn-xs btn btn-grey tooltip-info" type="button"
                                data-rel="tooltip" data-placement="bottom" title="前端搜索无结果时，使用该功能可刷新检索字段">
                            <span class="glyphicon glyphicon-refresh"></span>&nbsp;刷新检索
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Episode:search">
                        <button id="search" class="btn btn-xs btn-primary" type="button"><span
                                class="glyphicon glyphicon-search"></span>&nbsp;查询
                        </button>
                    </so:hasPermission>
                </div>
                <div id="toolbar" class="btn-group btn-corner">
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:modify">
                        <button id="import" class="btn btn-xs btn-success" type="button"><span
                                class="glyphicon glyphicon-download"></span>&nbsp;片单导入
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:modify">
                        <button id="xjdxImport" class="btn btn-xs btn-warning" type="button"><span
                                class="glyphicon glyphicon-download"></span>&nbsp;新疆电信片单注入
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:modify">
                        <button id="autoBind" class="btn btn-xs btn-primary" type="button"><span
                                class="glyphicon glyphicon-search"></span>&nbsp;匹配资源
                        </button>
                    </so:hasPermission>
                </div>
                <div id="toolbar" class="btn-group btn-corner">
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:syn">
                        <div class="btn-group">
                            <button data-toggle="dropdown" class="btn btn-xs btn-pink" aria-expanded="false">
                                <span class="fa fa-globe"></span>&nbsp;省网注入
                                <i class="ace-icon fa fa-angle-down icon-on-right"></i>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#" id="spAdd">首次注入</a>
                                </li>
                                <li>
                                    <a href="#" id="spEdit">修改信息</a>
                                </li>
                                <li>
                                    <a href="#" id="spDel">删除注入</a>
                                </li>
                            </ul>
                        </div>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:syn">
                        <div class="btn-group">
                            <button data-toggle="dropdown" class="btn btn-xs btn-primary" aria-expanded="false">
                                <span class="fa fa-globe"></span>&nbsp;媒资操作
                                <i class="ace-icon fa fa-angle-down icon-on-right"></i>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#" id="publish">发布</a>
                                </li>
                                <li>
                                    <a href="#" id="unPublish">取消发布</a>
                                </li>
                                <li>
                                    <a href="#" id="bindProduct">绑定产品</a>
                                </li>
                                <li>
                                    <a href="#" id="bindFreeProduct">绑定免费产品</a>
                                </li>
                                <li>
                                    <a href="#" id="delete">删除媒资</a>
                                </li>
                            </ul>
                        </div>
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
                       data-sort-name="modifyDate"
                       data-sort-order="desc">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12">&nbsp;</div>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#child" data-toggle="tab">子集</a></li>
        </ul>
        <div class="tab-content">
            <div id="child" class="tab-pane in active">
                <form id="frmSearchChild">
                    <input type="hidden" id="episodeId" name="search_EQ_pId" value="0"/>
                    <input type="hidden" id="classify" name="search_EQ_classify" value="3"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Episode:add">
                                        <button id="addChild" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Episode:modify">
                                        <button id="edtChild" class="btn btn-xs btn-success" type="button"><span
                                                class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Episode:remove">
                                        <button id="delChild" class="btn btn-xs btn-danger" type="button">
                                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                        </button>
                                    </so:hasPermission>
                                </div>
                                <div id="toolbar2" class="btn-group btn-corner">
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:syn">
                                        <div class="btn-group">
                                            <button data-toggle="dropdown" class="btn btn-xs btn-pink"
                                                    aria-expanded="false">
                                                <span class="fa fa-globe"></span>&nbsp;单集注入
                                                <i class="ace-icon fa fa-angle-down icon-on-right"></i>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li>
                                                    <a href="#" id="spSingleAdd">首次注入</a>
                                                </li>
                                                <li>
                                                    <a href="#" id="spSingleEdit">修改信息</a>
                                                </li>
                                                <li>
                                                    <a href="#" id="spSingleDel">删除注入</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </so:hasPermission>
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:syn">
                                        <div class="btn-group">
                                            <button data-toggle="dropdown" class="btn btn-xs btn-primary" aria-expanded="false">
                                                <span class="fa fa-globe"></span>&nbsp;媒资操作
                                                <i class="ace-icon fa fa-angle-down icon-on-right"></i>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li>
                                                    <a href="#" id="singlePublish">发布</a>
                                                </li>
                                                <li>
                                                    <a href="#" id="singleUnPublish">取消发布</a>
                                                </li>
                                                <li>
                                                    <a href="#" id="singleBindProduct">绑定产品</a>
                                                </li>
                                                <li>
                                                    <a href="#" id="singleBindFreeProduct">绑定免费产品</a>
                                                </li>
                                                <li>
                                                    <a href="#" id="singleDelete">删除媒资</a>
                                                </li>
                                            </ul>
                                        </div>
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
</div>
<script type="text/javascript">

    $('[data-rel=tooltip]').tooltip();

    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/sourceManage/episode/list';
        var ajaxAddUrl = contextPath + '/sourceManage/episode/add';
        var ajaxEdtUrl = contextPath + '/sourceManage/episode/edit';
        var ajaxDelUrl = contextPath + '/sourceManage/episode/remove';
        var ajaxAutoBindUrl = contextPath + '/sourceManage/television/autoRel';
        var ajaxImportUrl = contextPath + '/sourceManage/television/importFile';//Excel片单导入
        var ajaxXjdxImportUrl = contextPath + '/sourceManage/television/xjdxImportFile';//新疆电信Excel片单导入
        var ajaxSpSynUrl = contextPath + '/sp/spEpisodeSyn';
        var ajaxSpAssetSynUrl = contextPath + '/sp/spEpisodeAssetSyn';
        var ajaxSpChildSynUrl = contextPath + '/sp/spEpisodeChildSyn';
        var ajaxSpChildAssetSynUrl = contextPath + '/sp/spEpisodeChildAssetSyn';

        <x:dictData dictCode="Enable_Type" var="enable">//启用状态
        <x:jsonArray varName="enable" keyName="code" valName="name" items="${enable}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="Syn_Type" var="synType">//注入状态
        <x:jsonArray varName="synType" keyName="code" valName="name" items="${synType}" ></x:jsonArray>
        </x:dictData>
        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'id',
            sortable: false,
            title: "ID"
        }, {
            field: 'name',
            sortable: false,
            title: "名称"
        }, {
            field: 'code',
            sortable: false,
            title: "编码"
        }, {
            field: 'title',
            sortable: false,
            title: "标题"
        }, {
            field: 'classifyTags',
            sortable: false,
            title: "分类"
        }, {
            field: 'areaTags',
            sortable: false,
            title: "区域"
        }, {
            field: 'language',
            sortable: false,
            title: "语言"
        }, {
            field: 'yearTags',
            sortable: false,
            title: "年代"
        }, {
            field: 'sumNum',
            sortable: false,
            title: "总集数"
        }, {
            field: 'enable',
            sortable: false,
            title: "是否启用",
            formatter: function (value, row, index) {
                var show = value;
                $.each(enable, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'synType',
            sortable: false,
            title: "注入状态",
            formatter: function (value, row, index) {
                var show = value;
                $.each(synType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'isImageRel',
            sortable: false,
            title: "海报关系",
            formatter: function (value, row, index) {
                var show = value;
                if (value != 0) {
                    show = "<span style='color: green'>存在,数量:" + value + "</span>";
                } else {
                    show = "<span style='color: red'>不存在</span>";
                }
                return show;
            }
        }, {
            field: 'isVideoRel',
            sortable: false,
            title: "子集视频关系",
            formatter: function (value, row, index) {
                var show = value;
                if (value == 0) {
                    show = "<span style='color: red'>子集不存在</span>";
                } else if (value == 1) {
                    show = "<span style='color: green'>存在</span>";
                } else {
                    show = "<span style='color: red'>不存在或不完全存在</span>";
                }
                return show;
            }
        }
        ];

        var childColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'id',
            sortable: false,
            title: "内容id"
        }, {
            field: 'name',
            sortable: false,
            title: "名称"
        }, {
            field: 'code',
            sortable: false,
            title: "编码"
        }, {
            field: 'title',
            sortable: false,
            title: "标题"
        }, {
            field: 'info',
            sortable: false,
            title: "简介"
        }, {
            field: 'sort',
            sortable: false,
            title: "第X集"
        }, {
            field: 'titleSpellLong',
            sortable: false,
            title: "注入信息"
        }, {
            field: 'synType',
            sortable: false,
            title: "注入状态",
            formatter: function (value, row, index) {
                var show = value;
                $.each(synType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'enable',
            sortable: false,
            title: "是否启用",
            formatter: function (value, row, index) {
                var show = value;
                $.each(enable, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'isVideoRel',
            sortable: false,
            title: "视频关系",
            formatter: function (value, row, index) {
                var show = value;
                if (value != 0) {
                    show = "<span style='color: green'>存在,数量:" + value + "</span>";
                } else {
                    show = "<span style='color: red'>不存在</span>";
                }
                return show;
            }
        }
        ];


        jQuery(function ($) {
            //==============================================剧集==========================================================
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
                showAddOrEditDlg("新增", ajaxAddUrl, ajaxEdtUrl, "formDlg",
                    function (obj) {
                        $("#id").val(obj.id);
                        $("#code").val(obj.code);
                        $(".bootstrap-dialog-title").text("编辑");
                        $('#table-data').bootstrapTable('refresh', {silent: true});
                    }, function (obj) {
                        $('#table-data').bootstrapTable('refresh', {silent: true});
                    }, false, BootstrapDialog.SIZE_WIDE, 1);
            });
            $('#edt').on('click', function (e) {
                showEdtDlg2("编辑", ajaxEdtUrl, "table-data", "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, false, BootstrapDialog.SIZE_WIDE, 1);
            });
            $('#del').on('click', function (e) {
                doDelete(ajaxDelUrl, "table-data", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });

            $('#refresh').on('click', function (e) {
                var url = contextPath + '/sourceManage/television/refresh';
                BootstrapDialog.confirm({
                    title: '确认提示',
                    type: BootstrapDialog.TYPE_WARNING,
                    btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                    btnOKLabel: '确认', // <-- Default value is 'OK',
                    message: "您确定要刷新整个内容库的检索字段吗?",
                    callback: function (result) {
                        if (result) {
                            $.post(url).done(function (data) {
                                if (data.status == 'OK') {
                                    $('#table-data').bootstrapTable('selectPage', 1);
                                    CmMsg.success(data.message, -1);
                                } else if (data.status == 'ERROR') {
                                    CmMsg.error(data.message, -1);
                                } else if (data.status == 'TIMEOUT') {
                                    CmMsg.error(data.message, -1);
                                } else {
                                    CmMsg.error("删除失败了", -1);
                                }
                            });
                        }
                    }
                });
            });
            $('#import').on('click', function (e) {
                showAddDlg("片单导入", ajaxImportUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, true);
            });

            $('#xjdxImport').on('click', function (e) {
                showAddDlg("新疆电信片单注入", ajaxXjdxImportUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, true);
            });

            $('#autoBind').on('click', function (e) {
                doAction(ajaxAutoBindUrl, "table-data", "是否自动匹配资源关系？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });

            // 省网注入-新增
            $('#spAdd').on('click', function (e) {
                doAction(ajaxSpSynUrl + "/REGIST", "table-data", "是否确认要将选择的内容注入省网服务器中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 省网注入-修改
            $('#spEdit').on('click', function (e) {
                doAction(ajaxSpSynUrl + "/UPDATE", "table-data", "是否确认要将选择的内容注入省网服务器中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 省网注入-删除
            $('#spDel').on('click', function (e) {
                doAction(ajaxSpSynUrl + "/DELETE", "table-data", "是否确认要将选择的内容注入省网服务器中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 媒资操作-发布
            $('#publish').on('click', function (e) {
                doAction(ajaxSpAssetSynUrl + "/1", "table-data", "是否确认要将选择的内容发布中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 媒资操作-取消发布
            $('#unPublish').on('click', function (e) {
                doAction(ajaxSpAssetSynUrl + "/2", "table-data", "是否确认要将选择的内容取消发布中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 媒资操作-绑定产品
            $('#bindProduct').on('click', function (e) {
                doAction(ajaxSpAssetSynUrl + "/3", "table-data", "是否确认要将选择的内容绑定产品中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 媒资操作-删除媒资
            $('#delete').on('click', function (e) {
                doAction(ajaxSpAssetSynUrl + "/4", "table-data", "需要先取消发布后才能删除媒资，是否确认？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });

            //==============================================剧集子集==========================================================
            var ajaxListChildUrl = contextPath + '/sourceManage/episodeChild/list';
            var ajaxAddChildUrl = contextPath + '/sourceManage/episodeChild/add';
            var ajaxEdtChildUrl = contextPath + '/sourceManage/episodeChild/edit';
            var ajaxDelChildUrl = contextPath + '/sourceManage/episodeChild/remove';

            $('#table-data').on('load-success.bs.table', function () {
                var $table = $(this);
                var data = $table.bootstrapTable('getData');
                if (data && data.length > 0) {
                    $table.bootstrapTable('check', 0);
                    var rowId = data[0].id;
                    if (rowId == null || rowId == '') {
                        $('#frmSearchChild').find('#episodeId').val(0);
                    } else {
                        $('#frmSearchChild').find('#episodeId').val(data[0].id);
                    }
                    initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
                } else {
                    initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
                }
            });

            $('#table-data').on('check.bs.table', function (elem, row) {
                $('#frmSearchChild').find('#episodeId').val(row.id);
                $('#table-data-child').bootstrapTable('refresh', {silent: true});//刷新表单

            });
            $('#addChild').on('click', function (e) {
                showAddOrEditDlg("新增", ajaxAddChildUrl + "/" + $("#episodeId").val(), ajaxEdtChildUrl, "formDlg",
                    function (obj) {
                        $("#id").val(obj.id);
                        $("#code").val(obj.code);
                        $(".bootstrap-dialog-title").text("编辑");
                        $('#table-data-child').bootstrapTable('refresh', {silent: true});
                    }, function (obj) {
                        $('#table-data-child').bootstrapTable('refresh', {silent: true});
                    }, false, BootstrapDialog.SIZE_WIDE, 2);
            });
            $('#edtChild').on('click', function (e) {
                showEdtDlg2("编辑", ajaxEdtChildUrl, "table-data-child", "formDlg", function () {
                    $('#table-data-child').bootstrapTable('refresh', {silent: true});
                }, false, BootstrapDialog.SIZE_WIDE, 2);
            });

            $('#delChild').on('click', function (e) {
                doDelete(ajaxDelChildUrl, "table-data-child", function () {
                    $('#table-data-child').bootstrapTable('selectPage', 1);
                });
            });
            $('#spSingleAdd').on('click', function (e) {
                doAction(ajaxSpChildSynUrl + "/REGIST", "table-data-child", "是否确认要将选择的内容注入省网服务器中？", function () {
                    $('#table-data-child').bootstrapTable('refresh', {silent: true});
                });
            });
            $('#spSingleEdit').on('click', function (e) {
                doAction(ajaxSpChildSynUrl + "/UPDATE", "table-data-child", "是否确认要将选择的内容注入省网服务器中？", function () {
                    $('#table-data-child').bootstrapTable('refresh', {silent: true});
                });
            });
            $('#spSingleDel').on('click', function (e) {
                doAction(ajaxSpChildSynUrl + "/DELETE", "table-data-child", "是否确认要将选择的内容注入省网服务器中？", function () {
                    $('#table-data-child').bootstrapTable('refresh', {silent: true});
                });
            });

            // 媒资操作-发布
            $('#singlePublish').on('click', function (e) {
                doAction(ajaxSpChildAssetSynUrl + "/1", "table-data-child", "是否确认要将选择的内容发布中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 媒资操作-取消发布
            $('#singleUnPublish').on('click', function (e) {
                doAction(ajaxSpChildAssetSynUrl + "/2", "table-data-child", "是否确认要将选择的内容取消发布中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 媒资操作-绑定产品
            $('#singleBindProduct').on('click', function (e) {
                doAction(ajaxSpChildAssetSynUrl + "/3", "table-data-child", "是否确认要将选择的内容绑定产品中？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 媒资操作-绑定产品
            $('#singleBindFreeProduct').on('click', function (e) {
                doAction(ajaxSpChildAssetSynUrl + "/5", "table-data-child", "是否确认要将选择的内容做免费产品绑定？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
            // 媒资操作-删除媒资
            $('#singleDelete').on('click', function (e) {
                doAction(ajaxSpChildAssetSynUrl + "/4", "table-data-child", "需要先取消发布后才能删除媒资，是否确认？", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            });
        });
    });

    //==============================================js==========================================================
    /**
     * 打开内容编辑页
     */
    function showEdtDlg2(title, ajaxUrl, tableId, formId, callback, hasFile, size, type) {
        var selections = $('#' + tableId).bootstrapTable("getSelections");
        if (selections !== undefined && selections !== null) {
            if (selections.length === 1) {
                var id = selections[0].id;
                var formUrl = ajaxUrl + "/" + id;
                BootstrapDialog.show({
                    type: BootstrapDialog.TYPE_PRIMARY,
                    size: size,
                    closable: true,
                    draggable: true,
                    closeByBackdrop: false,
                    title: title,
                    message: $('<div></div>').load(formUrl),
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
                        action: function (dialogRef) {
                            var $button = this;
                            $button.disable();
                            $button.toggleSpin(true);
                            if ($('#' + formId).valid()) {
                                if (hasFile) {
                                    formFileProcess($('#' + formId), dialogRef, ajaxUrl, callback);
                                } else {
                                    if (type == 2) {
                                        // 设置内容与视频关系
                                        var jsonArrayVideo = [];
                                        $.each($(".contentVideoRow"), function (i, o) {
                                            var contentVideo = new Object();
                                            contentVideo.contentId = $("#id").val();
                                            contentVideo.videoId = $(o).attr("data-videoId");
                                            // 										contentVideo.videoSort = $("#sort"+$(o).attr("data-id")).val();
                                            jsonArrayVideo.push(contentVideo);
                                        })
                                        $("#contentVideoHidden").val(JSON.stringify(jsonArrayVideo));
                                    }
                                    if (type == 1) {
                                        // 设置内容与图片关系
                                        var jsonArrayImage = [];
                                        $.each($(".contentImageRow"), function (i, o) {
                                            var contentImage = new Object();
                                            contentImage.contentId = $("#id").val();
                                            var businessTypeTDId = $(o).attr("data-id");
                                            contentImage.businessType = $("#" + businessTypeTDId).val();
                                            contentImage.imageId = $(o).attr("data-imageId");
                                            contentImage.type = $(o).attr("data-type");
                                            jsonArrayImage.push(contentImage);
                                        })
                                        $("#contentImageHidden").val(JSON.stringify(jsonArrayImage));
                                    }

                                    $.post(ajaxUrl, getParams(formId)).done(function (data) {
                                        if (data.status == 'OK') {
                                            dialogRef.close();
                                            CmMsg.success(data.message, -1);
                                            if (callback) {
                                                callback();
                                            }
                                        } else if (data.status == 'ERROR') {
                                            CmMsg.error(data.message, -1);
                                        } else if (data.status == 'TIMEOUT') {
                                            CmMsg.error(data.message, -1);
                                        } else {
                                            CmMsg.error("编辑失败或其他错误", -1);
                                        }
                                        $button.toggleSpin(false);
                                        $button.enable();
                                    });
                                }
                            } else {
                                $button.toggleSpin(false);
                                $button.enable();
                            }
                        }
                    }]
                });
            } else {
                CmMsg.warn("请选择一行数据记录进行操作.", -1);
            }
        } else {
            CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
        }
    }

    /**
     * 打开新增或者编辑页面
     * @param title
     * @param ajaxUrl
     * @param formId
     * @param callback
     * @param hasFile
     * @param size
     * @param type 1 图片 2 视频
     */
    function showAddOrEditDlg(title, addAjaxUrl, editAjaxUrl, formId, addCallback, editCallback, hasFile, size, type) {
        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_PRIMARY,
            size: size,
            closable: true,
            draggable: true,
            closeByBackdrop: false,
            title: title,
            message: $('<div></div>').load(addAjaxUrl),
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
                action: function (dialogRef) {
                    saveOrUpdate(dialogRef, formId, addAjaxUrl, editAjaxUrl, addCallback, editCallback, hasFile, this, type);
                },
            }]
        });
    }

    /**
     * 打开内容添加页
     */
    function saveOrUpdate(dialogRef, formId, addAjaxUrl, editAjaxUrl, addCallback, editCallback, hasFile, obj, type) {
        var $button = obj;
        $button.disable();
        $button.toggleSpin(true);
        if ($('#' + formId).valid()) {
            if (hasFile) {
                formFileProcess($('#' + formId), dialogRef, addAjaxUrl, callback);
            } else {
                if ($("#id").val() == "") {//新增
                    $.post(addAjaxUrl, getParams(formId)).done(function (data) {
                        if (data.status == 'OK') {
//                        dialogRef.close();
                            CmMsg.success(data.message, -1);
                            if (addCallback) {
                                addCallback(data.rows);
                            }
                        } else if (data.status == 'ERROR') {
                            CmMsg.error(data.message, -1);
                        } else if (data.status == 'TIMEOUT') {
                            CmMsg.error(data.message, -1);
                        } else {
                            CmMsg.error("保存失败或其他错误", -1);
                        }
                        $button.toggleSpin(false);
                        $button.enable();
                    });
                } else {// 修改
                    if (type == 2) {
                        // 设置内容与视频关系
                        var jsonArrayVideo = [];
                        $.each($(".contentVideoRow"), function (i, o) {
                            var contentVideo = new Object();
                            contentVideo.contentId = $("#id").val();
                            contentVideo.videoId = $(o).attr("data-videoId");
                            //						contentVideo.videoSort = $("#sort"+$(o).attr("data-id")).val();
                            jsonArrayVideo.push(contentVideo);
                        })
                        $("#contentVideoHidden").val(JSON.stringify(jsonArrayVideo));
                    }
                    if (type == 1) {
                        // 设置内容与图片关系
                        var jsonArrayImage = [];
                        $.each($(".contentImageRow"), function (i, o) {
                            var contentImage = new Object();
                            contentImage.contentId = $("#id").val();
                            var businessTypeTDId = $(o).attr("data-id");
                            contentImage.businessType = $("#" + businessTypeTDId).val();
                            contentImage.imageId = $(o).attr("data-imageId");
                            contentImage.type = $(o).attr("data-type");
                            jsonArrayImage.push(contentImage);
                        })
                        $("#contentImageHidden").val(JSON.stringify(jsonArrayImage));
                    }

                    $.post(editAjaxUrl, getParams(formId)).done(function (data) {
                        if (data.status == 'OK') {
//                        dialogRef.close();
                            CmMsg.success(data.message, -1);
                            if (editCallback) {
                                editCallback();
                            }
                        } else if (data.status == 'ERROR') {
                            CmMsg.error(data.message, -1);
                        } else if (data.status == 'TIMEOUT') {
                            CmMsg.error(data.message, -1);
                        } else {
                            CmMsg.error("编辑失败或其他错误", -1);
                        }
                        $button.toggleSpin(false);
                        $button.enable();
                    });
                }
            }
        } else {
            $button.toggleSpin(false);
            $button.enable();
        }
    }

</script>