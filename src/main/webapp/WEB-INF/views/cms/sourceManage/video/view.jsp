<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#resource" data-toggle="tab">资源管理</a></li>
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
                                <label> 视频名称:&nbsp;<input name="search_LIKE_name" class="form-control"
                                                          placeholder="请输入视频名称" type="text">
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
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:add">
                                    <button id="add" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:modify">
                                    <button id="edt" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:remove">
                                    <button id="del" class="btn btn-xs btn-danger" type="button">
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:search">
                                    <button id="search" class="btn btn-xs btn-primary" type="button">
                                        <span class="glyphicon glyphicon-search"></span>&nbsp;查询
                                    </button>
                                </so:hasPermission>
                            </div>
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:add">
                                    <button id="videoExcel" class="btn btn-xs btn btn-inverse tooltip-info" type="button"
                                            data-rel="tooltip" data-placement="bottom" title="对于存在视频URL或视频三方ID的视频，可通过该功能批量导入系统。注：相同名称资源重复导入会导致存在多条资源附属信息。">
                                        <span class="fa fa-cloud-download"></span>&nbsp;信息导入
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:syn">
                                    <button type="button" id="videoSyn" data-toggle="dropdown"
                                            class="btn btn-xs btn-purple tooltip-info" aria-expanded="false"
                                            data-rel="tooltip" data-placement="bottom" title="某些省网可通过该功能，同步资源数据入系统">
                                        <span class="glyphicon glyphicon-refresh"></span>&nbsp;资源同步
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:add">
                                    <button type="button" id="videoUploads" data-toggle="dropdown"
                                            class="btn btn-xs btn-yellow tooltip-info" aria-expanded="false">
                                        <span class="fa fa-cloud-upload"></span>&nbsp;批量上传
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
            <li class="active"><a href="#vip" data-toggle="tab">资源附属信息</a></li>
        </ul>
        <form id="frmSearchChild">
            <input type="hidden" id="sourceId" name="search_EQ_source.id" value="0"/>
        </form>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="vip">
                <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                    <div class="widget-header widget-header-large no-padding">
                        <div class="widget-title smaller">
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:add">
                                    <button id="addChild" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:modify">
                                    <button id="edtChild" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:remove">
                                    <button id="delChild" class="btn btn-xs btn-danger" type="button">
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                            </div>
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:add">
                                    <button type="button" id="videoUpdate" data-toggle="dropdown"
                                            class="btn btn-xs btn-purple" aria-expanded="false">
                                        <span class="fa fa-cloud-upload"></span>&nbsp;单文件上传
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
                                   data-sort-name="id" data-sort-order="asc">
                            </table>
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
        var ajaxListUrl = contextPath + '/sourceManage/source/list';
        var ajaxAddUrl = contextPath + '/sourceManage/source/add';
        var ajaxEdtUrl = contextPath + '/sourceManage/source/edit';
        var ajaxDelUrl = contextPath + '/sourceManage/source/remove';
        var ajaxDataImportUrl = contextPath + '/sourceManage/video/dataImport';
        var ajaxVideoUploadsUrl = contextPath + '/sourceManage/video/videoUpload';
        var ajaxDataSynUrl = contextPath + '/sp/dataSyn';
        //====================================================================================================
        //输出字典js变量

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: true,
            title: "名称"
        }, {
            field: 'createDate',
            sortable: true,
            title: "创建时间"
        }, {
            field: 'modifyDate',
            sortable: true,
            title: "修改时间"
        }
        ];

        //=====================================================================================================

        //输出字典js变量
        <x:dictData dictCode="videoDefinition" var="definition">
        <x:jsonArray varName="definition" keyName="code" valName="name" items="${definition}" ></x:jsonArray>
        </x:dictData>
        <x:dictData dictCode="PlatForm" var="platform">
        <x:jsonArray varName="platform" keyName="code" valName="name" items="${platform}" ></x:jsonArray>
        </x:dictData>

        var childColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'platform',
            sortable: false,
            title: "平台来源",
            formatter: function (value, row, index) {
                var show = value;
                $.each(platform, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
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
            field: 'bitrate',
            sortable: false,
            title: "码率",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return value + "MB/s";
                }
            }
        }, {
            field: 'size',
            sortable: false,
            title: "大小",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return value + "MB";
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
            title: "地址",
            formatter: function (value, row, index) {
                return value.substr(0, 50) + "......";
            }
        }, {
            field: 'url',
            sortable: false,
            title: "操作",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return "<a style=\"cursor:pointer\" class='copyBtn' title='" + value + "' data-clipboard-text='" + value + "'>复制</a>";
                }
            }
        }
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
                showAddDlg("新增", ajaxAddUrl + "/" + $("#type").val(), "formDlg", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                }, false);
            });

            $('#edt').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtUrl + "/" + $("#type").val(), "table-data", "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, false);
            });

            $('#del').on('click', function (e) {
                doDelete(ajaxDelUrl, "table-data", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });

            $('#videoExcel').on('click', function (e) {
                showAddDlg("数据导入", ajaxDataImportUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, true);
            });

            $('#videoUploads').on('click', function (e) {
                showPreviewDlg("批量上传", ajaxVideoUploadsUrl, BootstrapDialog.SIZE_WIDE);
            });

            // 视频同步
            $('#videoSyn').on('click', function (e) {
                BootstrapDialog.confirm({
                    title: '确认提示',
                    type: BootstrapDialog.TYPE_WARNING,
                    btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                    btnOKLabel: '确认', // <-- Default value is 'OK',
                    message: "您确定要开始同步数据记录吗?",
                    callback: function (result) {
                        if (result) {
                            $.post(ajaxDataSynUrl, "").done(function (data) {
                                if (data.status == 'OK') {
                                    CmMsg.success(data.message, -1);
                                    $('#table-data').bootstrapTable('refresh', {silent: true});
                                } else if (data.status == 'ERROR') {
                                    CmMsg.error(data.message, -1);
                                } else if (data.status == 'TIMEOUT') {
                                    CmMsg.error(data.message, -1);
                                } else {
                                    CmMsg.error("保存失败或其他错误", -1);
                                }
                            });
                        }
                    }
                });
            });

            var buttons = [{
                id: 'btn-OK',
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
                    if ($('#formDlg').valid()) {
                        $.post(addBlackAjaxUrl, getParams("formDlg")).done(function (data) {
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
                    } else {
                        $button.toggleSpin(false);
                        $button.enable();
                    }
                }
            }];

            var ajaxListChildUrl = contextPath + '/sourceManage/video/list';
            var ajaxaddChildUrl = contextPath + '/sourceManage/video/add';
            var ajaxedtChildUrl = contextPath + '/sourceManage/video/edit';
            var ajaxdelChildUrl = contextPath + '/sourceManage/video/remove';
            var ajaxUploadUrl = contextPath + "/sourceManage/video/videoUpload";

            $('#table-data').on('load-success.bs.table', function () {
                var $table = $(this);
                var data = $table.bootstrapTable('getData');
                if (data && data.length > 0) {
                    $table.bootstrapTable('check', 0);
                    var rowId = data[0].id;
                    if (rowId == null || rowId == '') {
                        console.log("1");
                        $('#frmSearchChild').find('#sourceId').val(0);
                    } else {
                        $('#frmSearchChild').find('#sourceId').val(data[0].id);
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
                showAddDlg("新增", ajaxaddChildUrl + "/" + $("#sourceId").val(), "formDlg", function () {
                    $('#table-data-child').bootstrapTable('refresh', {silent: true});
                },false,BootstrapDialog.SIZE_WIDE);
            });

            $('#edtChild').on('click', function (e) {
                showEdtDlg("编辑", ajaxedtChildUrl, "table-data-child", "formDlg", function () {
                    $('#table-data-child').bootstrapTable('refresh', {silent: true});
                },false,BootstrapDialog.SIZE_WIDE)
            });

            $('#delChild').on('click', function (e) {
                doDelete(ajaxdelChildUrl, "table-data-child", function () {
                });
            });

            $('#videoUpdate').on('click', function (e) {
                showPreviewDlg("批量上传", ajaxUploadUrl + "/" + $("#sourceId").val(), BootstrapDialog.SIZE_WIDE);
            });
        });
    });
    //复制初始化
    if (clipboard != null) {
        clipboard.destroy();// 销毁方法
    }
    var clipboard = new Clipboard('.copyBtn');
    clipboard.on('success', function (e) {
        CmMsg.info("已复制至剪切板", -1);
    });
    clipboard.on('error', function (e) {
        CmMsg.error("复制失败", -1);
    });

</script>