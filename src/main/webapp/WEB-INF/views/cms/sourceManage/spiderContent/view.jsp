<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<script type="text/javascript" charset="utf-8" src="${contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${contextPath}/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#adInfo" data-toggle="tab">文章列表</a></li>
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
                                <label> 标题:&nbsp;
                                    <input name="search_LIKE_name" class="form-control" placeholder="请输入标题" type="text">
                                </label>
                                <label> 副标题:&nbsp;
                                    <input name="search_LIKE_number" class="form-control" placeholder="请输入副标题"
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
                                <so:hasPermission name="ContentCenter:SourceConfig:Television:SpiderContent:add">
                                    <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                            class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Television:SpiderContent:modify">
                                    <button id="edt" class="btn btn-xs btn-success" type="button"><span
                                            class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                                    </button>
                                </so:hasPermission>
                                <so:hasPermission name="ContentCenter:SourceConfig:Television:SpiderContent:remove">
                                    <button id="del" class="btn btn-xs btn-danger" type="button"><span
                                            class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                    </button>
                                </so:hasPermission>
                            </div>
<%--                            <div id="toolbar1" class="btn-group btn-corner">--%>
<%--                                <so:hasPermission name="ContentCenter:SourceConfig:Television:Book:modify">--%>
<%--                                    <button id="import" class="btn btn-xs btn-success" type="button"><span--%>
<%--                                            class="glyphicon glyphicon-download"></span>&nbsp;片单导入--%>
<%--                                    </button>--%>
<%--                                </so:hasPermission>--%>
<%--                            </div>--%>
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
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/sourceManage/spiderContent/list';
        var ajaxAddUrl = contextPath + '/sourceManage/spiderContent/add';
        var ajaxEdtUrl = contextPath + '/sourceManage/spiderContent/edit';
        var ajaxDelUrl = contextPath + '/sourceManage/spiderContent/remove';

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'title',
            sortable: false,
            title: "标题"
        }, {
            field: 'subtitle',
            sortable: false,
            title: "副标题"
        }, {
            field: 'author',
            sortable: false,
            title: "作者"
        }, {
            field: 'digest',
            sortable: false,
            title: "摘要"
        }, {
            field: 'cover',
            sortable: false,
            title: "封面"
        }, {
            field: 'tags',
            sortable: false,
            title: "分类标签"
        }, {
            field: 'source',
            sortable: false,
            title: "来源"
        }, {
            field: 'dateTime',
            sortable: false,
            title: "发布时间"
        }, {
            field: 'readNum',
            sortable: false,
            title: "阅读量"
        }, {
            field: 'likeNum',
            sortable: false,
            title: "点赞数"
        },
            {
                title: "详情",
                align:"center",
                manipulating:"1",
                formatter:function(value, row, index){
                    var ope = "<a target='dialog' onclick='detail(\""+row.id+"\")'>详情</a>";
                    return ope;
                }
            }
        ];

        jQuery(function ($) {
            $('#divSearch').slideToggle("slow");
            initTable('table-data', columns, ajaxListUrl, 'frmSearch');
            // 搜索域控制========================================

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
                    $('#formDlg').bootstrapTable('refresh', {silent: true});
                }, false, BootstrapDialog.SIZE_WIDE);
            });

            $('#del').on('click', function (e) {
                doDelete(ajaxDelUrl, "table-data", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });
        });
    });

    /**
     * 查看内容详情
     * @param id,type
     */
    function detail(id) {
        var flag = true;
        ajaxPathUrl = contextPath + '/sourceManage/spiderContent/detail';

        if (flag) {
            BootstrapDialog.show({
                size: BootstrapDialog.SIZE_WIDE,
                closable: true,
                draggable: true,
                closeByBackdrop: false,
                title: "详情查看",
                message: $('<div></div>').load(ajaxPathUrl + "/" + id),
                buttons: [{
                    id: 'btn-Cancel',
                    icon: 'glyphicon glyphicon-remove',
                    label: '关闭',
                    cssClass: 'btn-default',
                    autospin: false,
                    action: function (dialogRef) {
                        dialogRef.close();
                    }
                }]
            });
        }

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
