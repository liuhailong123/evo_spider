<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <div class="widget-box transparent" id="divSearch">
            <div class="widget-body">
                <div class="widget-main">
                    <form name="frmSearch" id="Select_frmSearch" target="_self" class="form-inline searchCondition">
                        <label> 产品名称:&nbsp;
                            <input name="search_LIKE_name" class="form-control" placeholder="请输入产品名称" type="text">
                        </label>
                        <label> 产品编码:&nbsp;
                            <input name="search_LIKE_code" class="form-control" placeholder="请输入产品编码" type="text">
                        </label>
                        <label> 第三方产品编码:&nbsp;
                            <input name="search_LIKE_thirdPartyCode" class="form-control" placeholder="请输入第三方产品编码"
                                   type="text">
                        </label>
                        <button id="btnSearch" type="button" class="btn btn-primary btn-sm form-control">
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
                           data-pagination="true"<c:if test="${type == 1}">data-single-select="true"</c:if>
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
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/pay/product/list';

        <x:dictData dictCode="App_Type" var="appType">
        <x:jsonArray varName="appType" keyName="code" valName="name" items="${appType}" ></x:jsonArray>
        </x:dictData>
        <x:dictData dictCode="OFF_ON" var="offOn">
        <x:jsonArray varName="offOn" keyName="code" valName="name" items="${offOn}" ></x:jsonArray>
        </x:dictData>
        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: false,
            title: "产品名称"
        }, {
            field: 'code',
            sortable: false,
            title: "产品编码"
        }, {
            field: 'thirdPartyCode',
            sortable: false,
            title: "第三方产品编码"
        }, {
            field: 'originalPrice',
            sortable: false,
            title: "原价(分)"
        }, {
            field: 'currentPrice',
            sortable: false,
            title: "优惠价(分)"
        }, {
            field: 'appId',
            sortable: false,
            title: "应用Id"
        }, {
            field: 'shortMssageInform',
            sortable: false,
            title: "是否短信通知",
            formatter: function (value, row, index) {
                var show = value;
                $.each(offOn, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }
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