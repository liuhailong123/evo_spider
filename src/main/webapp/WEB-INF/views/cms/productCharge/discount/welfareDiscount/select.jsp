<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <div class="widget-box transparent" id="divSearch">
            <div class="widget-body">
                <div class="widget-main">
                    <form name="frmSearch" id="Select_frmSearch" target="_self" class="form-inline searchCondition">
                        <input type="hidden" id="classify" name="search_EQ_classify" value="${type}"/>
                        <label> 名称:&nbsp;
                            <input name="search_LIKE_name" class="form-control" placeholder="请输入名称" type="text">
                        </label>
                        <input style="display: none"/>
                        <button id="Select_btnSearch" type="button" class="btn btn-primary btn-sm form-control">
                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div id="Select_tbWidget" class="widget-box transparent ui-sortable-handle">
            <div class="widget-body">
                <div class="widget-main no-padding">
                    <table id="Select_table-data"
                           data-classes="table table-striped table-hover"
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
</div>
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/productCharge/discount/welfareDiscount/list';

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
            initTable('Select_table-data', columns, ajaxListUrl, 'Select_frmSearch');
            $('#Select_search').on('click', function () {
                $('#Select_divSearch').slideToggle("slow");
            });
            // 搜索域控制
            $('#Select_frmSearch').find('#Select_btnSearch').on('click', function () {
                $('#Select_table-data').bootstrapTable('selectPage', 1);
            });
            $('#Select_tbWidget').on('reload.ace.widget', function (ev) {
                $('#Select_table-data').bootstrapTable('refresh', {silent: true});
            });
        });
    });
</script>