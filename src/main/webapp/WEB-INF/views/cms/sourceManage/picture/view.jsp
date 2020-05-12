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
                                <label> 图片名称:&nbsp;<input name="search_LIKE_name" class="form-control"
                                                          placeholder="请输入图片名称" type="text">
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
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:add">
                                    <button id="add" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:modify">
                                    <button id="edt" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:remove">
                                    <button id="del" class="btn btn-xs btn-danger" type="button">
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:search">
                                    <button id="search" class="btn btn-xs btn-primary" type="button">
                                        <span class="glyphicon glyphicon-search"></span>&nbsp;查询
                                    </button>
                                </so:hasPermission>
                            </div>
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:add">
                                    <button id="imageExcel" class="btn btn-xs btn btn-inverse tooltip-info" type="button"
                                            data-rel="tooltip" data-placement="bottom" title="对于存在图片URL的图片，可通过该功能批量导入系统。注：相同名称资源重复导入会导致存在多条资源附属信息。">
                                        <span class="fa fa-cloud-download"></span>&nbsp;信息导入
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:add">
                                    <button type="button" id="pictureUploads" data-toggle="dropdown"
                                            class="btn btn-xs btn-yellow" aria-expanded="false">
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
            <li class="active"><a href="#child" data-toggle="tab">资源附属信息</a></li>
        </ul>
        <form id="frmSearchChild">
            <input type="hidden" id="sourceId" name="search_EQ_source.id" value="0"/>
        </form>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="child">
                <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                    <div class="widget-header widget-header-large no-padding">
                        <div class="widget-title smaller">
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:add">
                                    <button id="addChild" class="btn btn-xs btn-pink" type="button">
                                        <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:modify">
                                    <button id="edtChild" class="btn btn-xs btn-success" type="button">
                                        <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Picture:remove">
                                    <button id="delChild" class="btn btn-xs btn-danger" type="button">
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                            </div>
                            <div id="toolbar" class="btn-group btn-corner">
                                <so:hasPermission name="ContentCenter:SourceConfig:Video:add">
                                    <button type="button" id="imageUpdate" data-toggle="dropdown"
                                            class="btn btn-xs btn-purple" aria-expanded="false">
                                        <span class="fa fa-cloud-upload"></span>&nbsp;文件上传
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
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/sourceManage/source/list';
        var ajaxAddUrl = contextPath + '/sourceManage/source/add';
        var ajaxEdtUrl = contextPath + '/sourceManage/source/edit';
        var ajaxDelUrl = contextPath + '/sourceManage/source/remove';
        var ajaxDataImportUrl = contextPath + '/sourceManage/picture/dataImport';
        var ajaxUploadUrl = contextPath + "/sourceManage/picture/pictureUpload";
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
        <x:dictData dictCode="Picture_Type" var="pictureType">
        <x:jsonArray varName="pictureType" keyName="code" valName="name" items="${pictureType}" ></x:jsonArray>
        </x:dictData>

        var childColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
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
                return value + "KB";
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
            field: 'url',
            sortable: false,
            title: "访问地址",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return "<a style=\"cursor:pointer\" href=\"" + value + "\" target=\"_Blank\">查看</a>";
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

            $('#imageExcel').on('click', function (e) {
                showAddDlg("数据导入", ajaxDataImportUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, true);
            });

            $('#pictureUploads').on('click', function (e) {
                showPreviewDlg("批量上传", ajaxUploadUrl, BootstrapDialog.SIZE_WIDE);
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

            var ajaxListChildUrl = contextPath + '/sourceManage/picture/list';
            var ajaxaddChildUrl = contextPath + '/sourceManage/picture/add';
             var ajaxedtChildUrl = contextPath + '/sourceManage/picture/edit';
            var ajaxdelChildUrl = contextPath + '/sourceManage/picture/remove';

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
                }, false);
            });

             $('#edtChild').on('click', function (e) {
                 showEdtDlg("编辑", ajaxedtChildUrl, "table-data-child", "formDlg", function () {
                     $('#table-data-child').bootstrapTable('refresh', {silent: true});
                 }, false)
             });

            $('#delChild').on('click', function (e) {
                doDelete(ajaxdelChildUrl, "table-data-child", function () {
                });
            });

            $('#imageUpdate').on('click', function (e) {
                showPreviewDlg("批量上传", ajaxUploadUrl + "/" + $("#sourceId").val(), BootstrapDialog.SIZE_WIDE);
            });
        });
    });
</script>