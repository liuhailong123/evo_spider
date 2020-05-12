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
                    <input type="hidden" id="classify" name="search_EQ_classify" value="1"/>
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
                    <input type="hidden" id="uploadClassify" name="uploadClassify">
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:add">
                        <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:modify">
                        <button id="edt" class="btn btn-xs btn-success" type="button"><span
                                class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:remove">
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
                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:search">
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
                       data-sort-name="createDate"
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
            <li class="active"><a href="#video" data-toggle="tab">视频</a></li>
            <li><a href="#picture" data-toggle="tab">图片</a></li>
        </ul>
        <div class="tab-content">
            <div id="video" class="tab-pane in active">
                <form id="frmSearchChild">
                    <input type="hidden" id="contentId" name="search_EQ_fId" value="0"/>
                    <input type="hidden" id="sourcetype" name="search_EQ_sourcetype" value="1"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:add">
                                        <button id="addChild" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:remove">
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
                                       data-sort-name="createDate" data-sort-order="asc">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="picture" class="tab-pane">
                <form id="frmSearchChildPicture">
                    <input type="hidden" id="contentId" name="search_EQ_fId" value="0"/>
                    <input type="hidden" id="sourcetype" name="search_EQ_sourcetype" value="2"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:add">
                                        <button id="addChildPicture" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:modify">
                                        <button id="edtChildPicture" class="btn btn-xs btn-success" type="button">
                                            <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Movie:remove">
                                        <button id="delChildPicture" class="btn btn-xs btn-danger" type="button">
                                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
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
                                <table id="table-data-childPicture"
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
</div>

<script type="text/javascript">

    $('[data-rel=tooltip]').tooltip();

    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/sourceManage/television/list';
        var ajaxAddUrl = contextPath + '/sourceManage/television/add';
        var ajaxEdtUrl = contextPath + '/sourceManage/television/edit';
        var ajaxSpSynUrl = contextPath + '/sp/spMovieSyn';
        var ajaxSpAssetSynUrl = contextPath + '/sp/spMovieAssetSyn';
        var ajaxXjdxImportUrl = contextPath + '/sourceManage/television/xjdxImportFile';//新疆电信Excel片单导入
        var ajaxDelRelationUrl = contextPath + '/sourceManage/television/removeRelation';
        var ajaxAutoBindUrl = contextPath + '/sourceManage/television/autoRel';
        var ajaxImportUrl = contextPath + '/sourceManage/television/importFile';//Excel片单导入

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
            field: 'runTime',
            sortable: false,
            title: "时长"
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
        }
        ];

        //输出字典js变量
        <x:dictData dictCode="videoDefinition" var="definition">
        <x:jsonArray varName="definition" keyName="code" valName="name" items="${definition}" ></x:jsonArray>
        </x:dictData>
        var videoColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: false,
            title: "名称"
        }, {
            field: 'definition',
            sortable: true,
            title: "清晰度",
            formatter: function (value, row, index) {
                var show = value;
                $.each(definition, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'ext',
            sortable: false,
            title: "格式"
        }, {
            field: 'size',
            sortable: false,
            title: "大小",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return (value / 1024).toFixed(2) + "MB";
                }
            }
        }, {
            field: 'time',
            sortable: false,
            title: "时长",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return value + "秒";
                }
            }
        }, {
            field: 'resolution',
            sortable: false,
            title: "分辨率"
        }, {
            field: 'url',
            sortable: false,
            title: "地址"
        }, {
            field: 'url',
            sortable: false,
            title: "操作",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return "<a style=\"cursor:pointer\" href='" + value + "'target=\"_blank\">在线预览</a>";
                }
            }
        }
        ];

        //输出字典js变量
        <x:dictData dictCode="Picture_Type" var="pictureType">
        <x:jsonArray varName="pictureType" keyName="code" valName="name" items="${pictureType}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="Picture_Business_Type" var="pictureBusinessType">
        <x:jsonArray varName="pictureBusinessType" keyName="code" valName="name" items="${pictureBusinessType}" ></x:jsonArray>
        </x:dictData>


        var pictureColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: false,
            title: "名称"
        }, {
            field: 'ext',
            sortable: false,
            title: "格式"
        }, {
            field: 'resolution',
            sortable: false,
            title: "分辨率"
        }, {
            field: 'size',
            sortable: false,
            title: "大小",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    value = (value / 1024).toFixed(2) + "KB";
                }
                return value;
            }
        }, {
            field: 'type',
            sortable: false,
            title: "横/竖",
            formatter: function (value, row, index) {
                var show = value;
                $.each(pictureType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'businessType',
            sortable: false,
            title: "业务类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(pictureBusinessType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'url',
            sortable: false,
            title: "访问地址",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return "<a style=\"cursor:pointer\" href=\"" + value + "\" target=\"_Blank\">在线预览</a>";
                }
            }
        }
        ];


        jQuery(function ($) {
            //==============================================内容==========================================================
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
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, false, BootstrapDialog.SIZE_WIDE);
            });

            $('#edt').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, false, BootstrapDialog.SIZE_WIDE);
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
            $('#del').on('click', function (e) {
                doDelete(ajaxDelRelationUrl, "table-data", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
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
            //==========================================================================================================
            var ajaxListChildUrl = contextPath + '/sourceManage/sourceRel/videoList';
            var ajaxaddChildUrl = contextPath + '/sourceManage/sourceRel/add';
            var ajaxEdtChildUrl = contextPath + '/sourceManage/sourceRel/edit';
            var ajaxdelChildUrl = contextPath + '/sourceManage/sourceRel/remove';


            var ajaxListChildPictureUrl = contextPath + '/sourceManage/sourceRel/pictureList';

            $('#table-data').on('load-success.bs.table', function () {
                var $table = $(this);
                var data = $table.bootstrapTable('getData');
                if (data && data.length > 0) {
                    $table.bootstrapTable('check', 0);
                    var rowId = data[0].id;
                    if (rowId == null || rowId == '') {
                        $('#frmSearchChild').find('#contentId').val(0);
                        $('#frmSearchChildPicture').find('#contentId').val(0);
                    } else {
                        $('#frmSearchChild').find('#contentId').val(data[0].id);
                        $('#frmSearchChildPicture').find('#contentId').val(data[0].id);
                    }
                    initTable('table-data-child', videoColumns, ajaxListChildUrl, 'frmSearchChild');
                    initTable('table-data-childPicture', pictureColumns, ajaxListChildPictureUrl, 'frmSearchChildPicture');
                } else {
                    initTable('table-data-child', videoColumns, ajaxListChildUrl, 'frmSearchChild');
                    initTable('table-data-childPicture', pictureColumns, ajaxListChildPictureUrl, 'frmSearchChildPicture');
                }
            });

            $('#table-data').on('check.bs.table', function (elem, row) {
                $('#frmSearchChild').find('#contentId').val(row.id);
                $('#table-data-child').bootstrapTable('refresh', {silent: true});//刷新表单

                $('#frmSearchChildPicture').find('#contentId').val(row.id);
                $('#table-data-childPicture').bootstrapTable('refresh', {silent: true});//刷新表单
            });
            //==============================================视频==========================================================
            $('#addChild').on('click', function (e) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                //判断是否选中了一行
                if (selections !== undefined && selections !== null) {
                    if (selections.length === 1) {
                        var rowName = selections[0].name;
                        if (rowName == null || rowName == '' || rowName == undefined) {
                            showSelectDlg("选择视频", contextPath + '/sourceManage/video/videoSelect/1/' + "name", ["Select_table-data"],
                                selectVideoCallBack, BootstrapDialog.SIZE_WIDE);
                        } else {
                            showSelectDlg("选择视频", contextPath + '/sourceManage/video/videoSelect/1/' + encodeURIComponent(rowName), ["Select_table-data"],
                                selectVideoCallBack, BootstrapDialog.SIZE_WIDE);
                        }
                    } else {
                        CmMsg.error("请选择一条数据", -1);
                    }
                } else {
                    CmMsg.error("没有选择电影", -1);
                }
                $('#table-data-child').bootstrapTable('refresh', {silent: true});//刷新表单
            });

            function selectVideoCallBack(obj) {
                var objStr = JSON.stringify(obj);
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                $.post(
                    ajaxaddChildUrl,
                    {objStr: objStr, type: 1, contentId: selections[0].id},
                    function (result) {
                        if (result.status == 'OK') {
                            CmMsg.success(result.message, -1);
                            $('#table-data-child').bootstrapTable('refresh', {silent: true});//刷新表单
                            $('#table-data').bootstrapTable('refresh', {silent: true});
                        } else {
                            CmMsg.error(result.message, -1);
                        }
                    }, "json");
            }

            $('#delChild').on('click', function (e) {
                doDelete(ajaxdelChildUrl, "table-data-child", function () {
                    $('#table-data-child').bootstrapTable('selectPage', 1);
                });
            });


            //==============================================图片==========================================================
            $('#edtChildPicture').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtChildUrl, "table-data-childPicture", "formDlg", function () {
                    $('#table-data-childPicture').bootstrapTable('selectPage', 1);
                }, false);
            });

            $('#addChildPicture').on('click', function (e) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                //判断是否选中了一行
                if (selections !== undefined && selections !== null) {
                    if (selections.length === 1) {
                        var rowName = selections[0].name;
                        console.log(rowName);
                        if (rowName == null || rowName == '' || rowName == undefined) {
                            showSelectDlg("选择图片", contextPath + '/sourceManage/picture/pictureSelect/2?name=' + "name", ["Select_table-data"],
                                selectImageCallBack, BootstrapDialog.SIZE_WIDE);
                        } else {
                            showSelectDlg("选择图片", contextPath + '/sourceManage/picture/pictureSelect/2?name=' + encodeURIComponent(rowName), ["Select_table-data"],
                                selectImageCallBack, BootstrapDialog.SIZE_WIDE);
                        }
                    } else {
                        CmMsg.error("请选择一条数据", -1);
                    }
                } else {
                    CmMsg.error("没有选择电影", -1);
                }
                $('#table-data-childPicture').bootstrapTable('refresh', {silent: true});//刷新表单
            });

            function selectImageCallBack(obj) {
                var objStr = JSON.stringify(obj);
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                $.post(
                    ajaxaddChildUrl,
                    {objStr: objStr, type: 2, contentId: selections[0].id},
                    function (result) {
                        if (result.status == 'OK') {
                            CmMsg.success(result.message, -1);
                            $('#table-data-childPicture').bootstrapTable('refresh', {silent: true});
                            $('#table-data').bootstrapTable('refresh', {silent: true});
                        } else {
                            CmMsg.error(result.message, -1);
                        }
                    }, "json");
            }

            $('#delChildPicture').on('click', function (e) {
                doDelete(ajaxdelChildUrl, "table-data-childPicture", function () {
                    $('#table-data-childPicture').bootstrapTable('selectPage', 1);
                });
            });

        });
    });
</script>