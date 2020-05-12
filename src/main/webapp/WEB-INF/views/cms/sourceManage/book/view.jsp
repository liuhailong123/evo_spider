<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#adInfo" data-toggle="tab">书单管理</a></li>
        </ul>
        <div class="tab-content">
            <div id="bookInfo">
                <div class="widget-box transparent" id="divSearch" hidden="true">
                    <div class="widget-header widget-header-large">
                        <h4 class="widget-title">搜索</h4>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
                                <label> 书名:&nbsp;
                                    <input name="search_LIKE_name" class="form-control" placeholder="请输入书名" type="text">
                                </label>
                                <label> 书号:&nbsp;
                                    <input name="search_LIKE_number" class="form-control" placeholder="请输入书号"
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
                                <so:hasPermission name="ContentCenter:SourceConfig:Television:Book:add">
                                    <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                            class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Television:Book:modify">
                                    <button id="edt" class="btn btn-xs btn-success" type="button"><span
                                            class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Television:Book:remove">
                                    <button id="del" class="btn btn-xs btn-danger" type="button"><span
                                            class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Television:Book:search">
                                    <button id="search" class="btn btn-xs btn-primary" type="button"><span
                                            class="glyphicon glyphicon-search"></span>&nbsp;查询
                                    </button>
                                </so:hasPermission>
                            </div>
                            <div id="toolbar1" class="btn-group btn-corner">
                                <so:hasPermission name="ContentCenter:SourceConfig:Television:Book:modify">
                                    <button id="import" class="btn btn-xs btn-success" type="button"><span
                                            class="glyphicon glyphicon-download"></span>&nbsp;片单导入
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
            <li class="active"><a href="#picture" data-toggle="tab">图片</a></li>
        </ul>
        <div class="tab-content">
            <div id="picture" class="tab-pane in active">
                <form id="frmSearchChildPicture">
                    <input type="hidden" id="contentId" name="search_EQ_fId" value="0"/>
                    <input type="hidden" id="sourcetype" name="search_EQ_sourcetype" value="2"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <%--<so:hasPermission name="ContentCenter:SourceConfig:Television:Book:add">
                                        <button id="addChildPicture" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>--%>
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Book:modify">
                                        <button id="edtChildPicture" class="btn btn-xs btn-success" type="button">
                                            <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Book:remove">
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
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/sourceManage/book/list';
        var ajaxAddUrl = contextPath + '/sourceManage/book/add';
        var ajaxEdtUrl = contextPath + '/sourceManage/book/edit';
        var ajaxDelUrl = contextPath + '/sourceManage/book/remove';
        var ajaxImportUrl = contextPath + '/sourceManage/book/importFile';//Excel片单导入

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'number',
            sortable: false,
            title: "书号(标示类别)"
        }, {
            field: 'name',
            sortable: false,
            title: "书名"
        }, {
            field: 'author',
            sortable: false,
            title: "作者"
        }, {
            field: 'publishDate',
            sortable: false,
            title: "出版日期"
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
        }
            , {
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
            initTable('table-data', columns, ajaxListUrl, 'frmSearch');
            // 搜索域控制========================================
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

            $('#del').on('click', function (e) {
                doDelete(ajaxDelUrl, "table-data", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });
            $('#import').on('click', function (e) {
                showAddDlg("片单导入", ajaxImportUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, true);
            });

            //==========================================================================================================
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
                        $('#frmSearchChildPicture').find('#contentId').val(0);
                    } else {
                        $('#frmSearchChildPicture').find('#contentId').val(data[0].id);
                    }
                    initTable('table-data-childPicture', pictureColumns, ajaxListChildPictureUrl, 'frmSearchChildPicture');
                } else {
                    initTable('table-data-childPicture', pictureColumns, ajaxListChildPictureUrl, 'frmSearchChildPicture');
                }
            });

            $('#table-data').on('check.bs.table', function (elem, row) {
                $('#frmSearchChildPicture').find('#contentId').val(row.id);
                $('#table-data-childPicture').bootstrapTable('refresh', {silent: true});//刷新表单
            });

            $('#edtChildPicture').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtChildUrl, "table-data-childPicture", "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
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
                            $('#table-data-childPicture').bootstrapTable('refresh', {silent: true});//刷新表单
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
