<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="widget-box transparent" id="divSearch" hidden="true">
        <div class="widget-header widget-header-large">
            <h4 class="widget-title">搜索</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
                    <label> 名称:&nbsp;
                        <input name="search_LIKE_name" class="form-control" placeholder="请输入名称" type="text">
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
                    <so:hasPermission name="ProductCharge:Discount:WelfareDiscount:add">
                        <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ProductCharge:Discount:WelfareDiscount:modify">
                        <button id="edt" class="btn btn-xs btn-success" type="button"><span
                                class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ProductCharge:Discount:WelfareDiscount:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button"><span
                                class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ProductCharge:Discount:WelfareDiscount:search">
                        <button id="search" class="btn btn-xs btn-primary" type="button"><span
                                class="glyphicon glyphicon-search"></span>&nbsp;查询
                        </button>
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
                       data-single-select="true"
                       data-pagination="true"
                       data-id-field="id"
                       data-unique-id="id"
                       data-sort-name="id"
                       data-sort-order="asc">
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/productCharge/discount/welfareDiscount/list';
        var ajaxAddUrl = contextPath + '/productCharge/discount/welfareDiscount/add';
        var ajaxEdtUrl = contextPath + '/productCharge/discount/welfareDiscount/edit';
        var ajaxDelUrl = contextPath + '/productCharge/discount/welfareDiscount/remove';


        <x:dictData dictCode="WelfareDiscountType" var="welfareDiscountType">
        <x:jsonArray varName="welfareDiscountType" keyName="code" valName="name" items="${welfareDiscountType}" ></x:jsonArray>
        </x:dictData>

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: true,
            title: "福利优惠名称"
        }, {
            field: 'code',
            sortable: true,
            title: "服务优惠编码"
        }, {
            field: 'type',
            sortable: true,
            title: "类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(welfareDiscountType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'goodsId',
            sortable: false,
            title: "商品id"
        },
        ];

        jQuery(function ($) {
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
            //================================================
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