<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<style type="text/css">
    div#rMenu {
        position: absolute;
        visibility: hidden;
        top: 0;
        left: 0;
        background-color: grey;
        text-align: left;
        padding: 0;
        border-radius: 4px;
        z-index: 999;
        border: 1px solid black;
    }

    div#rMenu ul {
        list-style: none;
        margin: 0 !important;
        padding: 0 !important;
    }

    div#rMenu ul li {
        width: 100%;
        margin: 0;
        padding: 5px 10px;
        cursor: pointer;
        list-style: none outside none;
        background-color: #FFFFFF;
    }

    div#rMenu ul li:hover {
        background-color: #E2EAF2;
    }
</style>
<div id="rMenu">
    <input type="hidden" id="treeNodeId" value=""/>
    <ul>
        <li id="m_add" onclick="addTreeNode();">添加</li>
        <li id="m_edit" onclick="editTreeNode();">修改</li>
        <li id="m_del" onclick="removeTreeNode();">删除</li>
        <li id="m_price" onclick="priceTreeNode();">产品定价</li>
        <li id="m_c_price" onclick="unPriceTreeNode();">取消定价</li>
        <li id="m_recommend" onclick="recommend();">推荐</li>
        <li id="m_c_recommend" onclick="unRecommend();">取消推荐</li>
    </ul>
</div>
<div class="col-lg-3">
    <div class="widget-box transparent">
        <div class="widget-header widget-header-large">
            <h4 class="widget-title">栏目</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main padding-8">
                <div id="treeview" class="ztree"></div>
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
                <form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
                    <input type="hidden" id="level" value="0"/>
                    <input type="hidden" id="columnsId" name="search_EQ_aId" value="0"/>
                    <input type="hidden" name="search_EQ_type" value="2"/>
                    <label>
                        内容类型:&nbsp;
                        <x:dictData dictCode="Publish_Content_Type" var="publishContentType">
                            <x:select defaultOption="请选择" hasDefault="true" var="publishContentType"
                                      items="${publishContentType}" id="contentType1" name="search_EQ_contentType"
                                      className="form-control">
                                <x:option value="${publishContentType.code }" text="${publishContentType.name }"
                                          selected="${entity.contentType eq publishContentType.code}"></x:option>
                            </x:select>
                        </x:dictData>
                    </label>
                    <label>
                        名称:&nbsp;
                        <input id="contentName" name="search_LIKE_contentName" class="form-control" placeholder="请输入名称"
                               type="text">
                    </label>
                    <label>
                        是否发布:&nbsp;
                        <x:dictData dictCode="OFF_ON" var="offOn">
                            <x:select defaultOption="请选择" hasDefault="true" var="offOn"
                                      items="${offOn}" id="enable" name="search_EQ_publish"
                                      className="form-control">
                                <x:option value="${offOn.code }" text="${offOn.name }"
                                          selected=""></x:option>
                            </x:select>
                        </x:dictData>
                    </label>
                    <input style="display: none"/>
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
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:search">
                        <button id="search" class="btn btn-xs btn-primary" type="button">
                            <span class="glyphicon glyphicon-search"></span>&nbsp;查询
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:add">
                        <button type="button" id="add" class="btn btn-xs btn-pink">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:modify">
                        <button id="edt" class="btn btn-xs btn-success" type="button">
                            <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button">
                            <span class="glyphicon glyphicon-trash"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                </div>
                <div id="toolbar4" class="btn-group btn-corner">
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:modify">
                        <button id="top" class="btn btn-xs btn-primary" type="button">
                            <span class="glyphicon glyphicon-signal"></span>&nbsp;内容排序
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:modify">
                        <button id="voiceSyn" class="btn btn-xs btn-purple" type="button">
                            <span class="glyphicon glyphicon-success"></span>&nbsp;全局搜索数据同步
                        </button>
                    </so:hasPermission>
                </div>
                <div id="toolbar2" class="btn-group btn-corner">
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:modify">
                        <button id="release" class="btn btn-xs btn-purple" type="button">
                            <span class="glyphicon glyphicon-ok"></span>&nbsp;内容发布
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:modify">
                        <button id="releaseNo" class="btn btn-xs btn-dark" type="button">
                            <span class="glyphicon glyphicon-remove"></span>&nbsp;取消发布
                        </button>
                    </so:hasPermission>
                </div>
                <div id="toolbar3" class="btn-group btn-corner">
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:modify">
                        <button id="recommend" class="btn btn-xs btn-purple" type="button">
                            <span class="glyphicon glyphicon-ok"></span>&nbsp;内容推荐
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:modify">
                        <button id="unRecommend" class="btn btn-xs btn-dark" type="button">
                            <span class="glyphicon glyphicon-remove"></span>&nbsp;取消推荐
                        </button>
                    </so:hasPermission>
                </div>
                <div id="toolbar5" class="btn-group btn-corner">
                    <so:hasPermission name="ContentCenter:ContentManage:CatalogueRelation:modify">
                        <button id="autoRel" class="btn btn-xs btn-purple" type="button">
                            <span class="glyphicon glyphicon-ok"></span>&nbsp;自动挂载
                        </button>
                    </so:hasPermission>
                </div>
            </div>
            <div class="widget-toolbar">
                <a data-action="reload" class="green2 bigger-120" href="#">
                    <i class="ace-icon fa fa-refresh"></i>
                </a>
                <a data-action="fullscreen" class="blue2 bigger-120" href="#">
                    <i class="ace-icon fa fa-arrows-alt"></i>
                </a>
            </div>
        </div>
        <div class="widget-body">
            <div class="widget-main no-padding">
                <table id="table-data"
                       data-classes="table table-striped table-hover"
                       data-pagination="true"
                       data-id-field="id"
                       data-unique-id="id"
                       data-sort-name="sort"
                       data-sort-order="asc">
                </table>
            </div>
        </div>
    </div>
</div>
<script src="${contextPath}/static/local/js/common/column.js"></script>
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxTreeUrl = contextPath + '/columnManage/column/columnsTree';
        var ajaxListUrl = contextPath + '/contentManage/catalogueRelation/list';
        var ajaxAddUrl = contextPath + '/contentManage/catalogueRelation/add';
        var ajaxEdtUrl = contextPath + '/contentManage/catalogueRelation/edit';
        var ajaxDelUrl = contextPath + '/contentManage/catalogueRelation/remove';
        var ajaxVoiceSynUrl = contextPath + '/sp/voiceSyn';

        <x:dictData dictCode="OFF_ON" var="offOn">
        <x:jsonArray varName="offOn" keyName="code" valName="name" items="${offOn}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="Publish_Content_Type" var="publishContentType">
        <x:jsonArray varName="publishContentType" keyName="code" valName="name" items="${publishContentType}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="Business_Type" var="business_Type">
        <x:jsonArray varName="business_Type" keyName="code" valName="name" items="${business_Type}" ></x:jsonArray>
        </x:dictData>

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'id',
            sortable: true,
            title: "ID"
        }, {
            field: 'contentId',
            sortable: true,
            title: "内容id"
        }, {
            field: 'name',
            sortable: true,
            title: "名称"
        }, {
            field: 'type',
            sortable: true,
            title: "类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(publishContentType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'businessType',
            sortable: true,
            title: "业务类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(business_Type, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'id',
            sortable: false,
            title: "详情",
            formatter: function (value, row, index) {
                var html = "<a style=\"cursor: pointer;\" onclick=\"detail('" + row.contentId + "','" + row.type + "');\">查看</a>";
                return html;
            }
        }, {
            field: 'sort',
            sortable: true,
            title: "排序"
        }, {
            field: 'createDate',
            sortable: true,
            title: "创建时间"
        }, {
            field: 'enable',
            sortable: false,
            title: "是否已发布",
            formatter: function (value, row, index) {
                var show = value;
                $.each(offOn, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }/*, {
            field: 'columnName',
            sortable: false,
            title: "所属栏目"
        }*/
        ];

        var setting = {
            treeId: "treeview",
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                }
            },
            async: {
                enable: true,
                url: ajaxTreeUrl,
            },
            callback: {
                onAsyncSuccess: function (event, treeId, treeNode, msg) {
                    openTree(id, 1);
                    initTable('table-data', columns, ajaxListUrl, 'frmSearch');
                },
                onClick: function (event, treeId, treeNode) {
                    var level = treeNode.level;
                    $("#level").val(level);
                    $("#columnsId").val(treeNode.id);
                    $('#table-data').bootstrapTable('selectPage', 1);
                    if (level >= 2) {
                        chengeButtonClass(false);//将所有按钮置灰-解除
                    } else {
                        chengeButtonClass(true);//将所有按钮置灰
                    }
                },
                onRightClick: OnRightClick
            }
        };

        jQuery(function ($) {
            chengeButtonClass(true);//将所有按钮置灰

            $.fn.zTree.init($("#treeview"), setting, null);
            zTree = $.fn.zTree.getZTreeObj("treeview");
            rMenu = $("#rMenu");
            $('#search').on('click', function () {
                $('#divSearch').slideToggle("slow");
            });

            // 搜索域控制
            $('#frmSearch').find('#btnSearch').on('click', function () {
                if ($("#contentName").val() != "") {
                    if ($("#contentType1").val() == "") {
                        CmMsg.warn("请选择内容类型", -1);
                    } else {
                        $('#table-data').bootstrapTable('selectPage', 1);
                    }
                } else {
                    $('#table-data').bootstrapTable('selectPage', 1);
                }
            });

            $('#tbWidget').on('reload.ace.widget', function (ev) {
                $('#table-data').bootstrapTable('refresh', {silent: true});
            });

            $('#add').on('click', function (e) {
                showAddDlg("新增", ajaxAddUrl + "/" + $("#columnsId").val(), "formDlg", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
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

            $('#release').on('click', function (e) {
                var actionUrl = contextPath + '/contentManage/catalogueRelation/release';
                doAction(actionUrl, "table-data", "是否确认内容发布", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });

            $('#releaseNo').on('click', function (e) {
                var actionUrl = contextPath + '/contentManage/catalogueRelation/releaseNo';
                doAction(actionUrl, "table-data", "是否确认取消内容发布", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });

            $('#recommend').on('click', function (e) {
                var actionUrl = contextPath + '/contentManage/catalogueRelation/setRecommend?type=1';
                doAction(actionUrl, "table-data", "是否确认推荐内容", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });

            $('#unRecommend').on('click', function (e) {
                var actionUrl = contextPath + '/contentManage/catalogueRelation/setRecommend?type=2';
                doAction(actionUrl, "table-data", "是否确认取消推荐内容", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });

            $('#autoRel').on('click', function (e) {
                if($('#columnsId').val() == "0"){
                    CmMsg.error("请先选择需要挂载的栏目树节点", -1);
                    return "";
                }
                BootstrapDialog.confirm({
                    title: '确认提示',
                    type: BootstrapDialog.TYPE_WARNING,
                    btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                    btnOKLabel: '确认', // <-- Default value is 'OK',
                    message: "您是否要自动挂载本栏目的所属内容？？？",
                    callback: function (result) {
                        if (result) {
                            var actionUrl = contextPath + '/contentManage/catalogueRelation/autoRel?columnId=' + $('#columnsId').val();
                            $.post(actionUrl).done(function (data) {
                                if (data.status == 'OK') {
                                    $('#table-data').bootstrapTable('selectPage', 1);
                                    CmMsg.success(data.message, -1);
                                } else if (data.status == 'ERROR') {
                                    CmMsg.error(data.message, -1);
                                } else if (data.status == 'TIMEOUT') {
                                    CmMsg.error(data.message, -1);
                                } else {
                                    CmMsg.error("操作失败了", -1);
                                }
                            });
                        }
                    }
                });
            });

            $('#top').on('click', function (e) {
                var actionUrl = contextPath + '/contentManage/catalogueRelation/setTop';
                showEdtDlg("排序", actionUrl, "table-data", "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: false});
                }, true);
            })

            $('#voiceSyn').on('click', function (e) {
                doAction(ajaxVoiceSynUrl, "table-data", "您确定要开始同步语音数据记录吗?", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                });
            })
        });
    });


    //改变排序字段
    function changeSort(id, sortId) {
        var sort = $("#sort" + id).val();
        if (sort == "") {
            CmMsg.warn("排序不能为空", -1);
        } else {
            $.ajax({
                url: contextPath + '/contentManage/content/changeSort',
                data: {
                    "id": id,
                    "sort": sort
                },
                type: "post",
                success: function (data) {
                    if (data.status == 'OK') {
                        CmMsg.success(data.message, -1);
                        $('#table-data').bootstrapTable('refresh', {silent: true});
                    } else if (data.status == 'ERROR') {
                        CmMsg.error(data.message, -1);
                    } else if (data.status == 'TIMEOUT') {
                        CmMsg.error(data.message, -1);
                    } else {
                        CmMsg.error("失败或其他错误", -1);
                    }

                }
            });
        }
    }

    /**
     * 将所有按钮置灰／解除
     */
    function chengeButtonClass(flag) {
        $("#add").attr("disabled", flag);
        $("#edt").attr("disabled", flag);
        $("#del").attr("disabled", flag);
//        $("#search").attr("disabled", flag);
        $("#release").attr("disabled", flag);
        $("#releaseNo").attr("disabled", flag);
        $("#sendGeHua").attr("disabled", flag);
        $("#recommend").attr("disabled", flag);
        $("#unRecommend").attr("disabled", flag);
        $("#push").hide();
    }


    /**
     * 查看内容详情
     * @param id,type
     */
    function detail(id, type) {
        var flag = true;
        var ajaxPathUrl = "";
        if (type == 1) {//电影
            ajaxPathUrl = contextPath + '/sourceManage/television/detail';
        } else if (type == 2) {//剧集
            ajaxPathUrl = contextPath + '/sourceManage/television/detail';
        } else if (type == 3) {//直播
            ajaxPathUrl = contextPath + '/contentManage/liveBroadcast/detail';
        } else if (type == 5) {//图书
            ajaxPathUrl = contextPath + '/sourceManage/book/detail';
        } else {
            flag = false;
        }
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
</script>