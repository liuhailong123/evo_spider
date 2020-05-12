<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div id="resource">
        <div class="widget-box transparent" id="divSearch" hidden="true">
            <div class="widget-header widget-header-large">
                <h4 class="widget-title">搜索</h4>
            </div>
            <div class="widget-body">
                <div class="widget-main">
                    <form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
                        <label> 智能卡号:&nbsp;<input name="search_LIKE_cardNo" class="form-control"
                                                  placeholder="请输入智能卡号" type="text">
                        </label>
                        <label> 手机号:&nbsp;<input name="search_LIKE_phone" class="form-control"
                                                 placeholder="请输入收货人手机号" type="text">
                        </label>
                        <label> 是否中奖:&nbsp;
                            <x:dictData dictCode="isNeedOrder" var="isOwn">
                                <x:select defaultOption="请选择" hasDefault="true" var="isOwn" items="${isOwn}"
                                          id="" name="search_EQ_isOwn" className="form-control">
                                    <x:option value="${isOwn.code }" text="${isOwn.name }"
                                              selected=""></x:option>
                                </x:select>
                            </x:dictData>
                        </label>
                        <label> 是否发货:&nbsp;
                            <x:dictData dictCode="isNeedOrder" var="isSend">
                                <x:select defaultOption="请选择" hasDefault="true" var="isSend" items="${isSend}"
                                          id="" name="search_EQ_isSend" className="form-control">
                                    <x:option value="${isSend.code }" text="${isSend.name }"
                                              selected=""></x:option>
                                </x:select>
                            </x:dictData>
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
                        <%--<so:hasPermission name="Activity:DrawQuery:add">
                            <button id="add" class="btn btn-xs btn-pink" type="button">
                                <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                            </button>
                        </so:hasPermission>--%>
                        <so:hasPermission name="Activity:DrawQuery:modify">
                            <button id="edt" class="btn btn-xs btn-success" type="button">
                                <span class="glyphicon glyphicon-edit"></span>&nbsp;发货
                            </button>
                        </so:hasPermission>
                        <so:hasPermission name="Activity:DrawQuery:remove">
                            <button id="del" class="btn btn-xs btn-danger" type="button">
                                <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                            </button>
                        </so:hasPermission>
                        <so:hasPermission name="Activity:DrawQuery:search">
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
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/activity/drawQuery/list';
        var ajaxAddUrl = contextPath + '/activity/drawQuery/add';
        var ajaxEdtUrl = contextPath + '/activity/drawQuery/edit';
        var ajaxDelUrl = contextPath + '/activity/drawQuery/remove';
        //====================================================================================================
        //输出字典js变量
        <x:dictData dictCode="isNeedOrder" var="dictStatus">
        <x:jsonArray varName="mStatus" keyName="code" valName="name" items="${dictStatus}" ></x:jsonArray>
        </x:dictData>

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'userId',
            sortable: true,
            title: "用户id"
        }, {
            field: 'cardNo',
            sortable: true,
            title: "智能卡号"
        }, {
            field: 'drawChildName',
            sortable: false,
            title: "中奖商品"
        }, {
            field: 'phone',
            sortable: true,
            title: "收货人手机"
        }, {
            field: 'address',
            sortable: true,
            title: "收货地址"
        }, {
            field: 'orderNo',
            sortable: false,
            title: "物流单号"
        }, {
            field: 'isOwn',
            sortable: true,
            title: "是否中奖",
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
            field: 'isSend',
            sortable: true,
            title: "是否发货",
            formatter: function (value, row, index) {
                var showName = value;
                $.each(mStatus, function () {
                    if (this.code == value) {
                        showName = this.name;
                    }
                });
                return showName;
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
        });
    });
</script>