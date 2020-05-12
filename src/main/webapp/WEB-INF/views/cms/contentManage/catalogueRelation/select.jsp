<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <div class="widget-box transparent" id="divSearch">
            <div class="widget-header widget-header-large">
                <h4 class="widget-title">搜索</h4>
            </div>
            <div class="widget-body">
                <div class="widget-main">
                    <form name="frmSearch" id="Select_frmSearch" target="_self" class="form-inline searchCondition">
                        <input type="hidden" name="search_EQ_contentType" value="${type}"/>
                        <label>
                            名称:&nbsp;
                            <input id="contentName" name="search_LIKE_contentName" class="form-control" placeholder="请输入名称"
                                   type="text">
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
        var ajaxListUrl = contextPath + '/contentManage/catalogueRelation/list';

        <x:dictData dictCode="Enable_Type" var="enable">
        <x:jsonArray varName="enable" keyName="code" valName="name" items="${enable}" ></x:jsonArray>
        </x:dictData>
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
            field: 'columnName',
            sortable: false,
            title: "所属栏目"
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