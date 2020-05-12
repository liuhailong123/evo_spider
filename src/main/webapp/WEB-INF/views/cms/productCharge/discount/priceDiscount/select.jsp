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
        var ajaxListUrl = contextPath + '/productCharge/discount/priceDiscount/list';

        <x:dictData dictCode="IsMore" var="isMore">
        <x:jsonArray varName="isMore" keyName="code" valName="name" items="${isMore}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="PriceDiscountType" var="priceDiscountType">
        <x:jsonArray varName="priceDiscountType" keyName="code" valName="name" items="${priceDiscountType}" ></x:jsonArray>
        </x:dictData>

        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: true,
            title: "价格优惠名称"
        }, {
            field: 'code',
            sortable: true,
            title: "价格优惠编码"
        }, {
            field: 'type',
            sortable: true,
            title: "类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(priceDiscountType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'isMore',
            sortable: true,
            title: "累积触发",
            formatter: function (value, row, index) {
                var show = value;
                $.each(isMore, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'targetValue',
            sortable: false,
            title: "目标值(元)",
            formatter: function (value, row, index) {
                return (value / 100).toFixed(2);
            }
        }, {
            field: 'discountValue',
            sortable: false,
            title: "优惠值(元/折扣)",
            formatter: function (value, row, index) {
                return (value / 100).toFixed(2);
            }
        }, {
            field: 'priority',
            sortable: true,
            title: "优先级"
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