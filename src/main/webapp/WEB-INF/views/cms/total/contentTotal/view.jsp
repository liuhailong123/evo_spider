<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="widget-box transparent" id="divSearch" hidden="true">
        <div class="widget-header widget-header-large">
            <h4 class="widget-title">搜索</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <form name="frmSearch" id="frmSearch" target="_self"
                      class="form-inline searchCondition">
                    <label> 行为类型:&nbsp;
                        <x:dictData dictCode="Content_Total_Type" var="contentTotalType">
                            <x:select defaultOption="请选择" hasDefault="true" var="contentTotalType"
                                      items="${contentTotalType}"
                                      id="" name="search_EQ_type" className="form-control">
                                <x:option value="${contentTotalType.code }" text="${contentTotalType.name }"
                                          selected=""></x:option>
                            </x:select>
                        </x:dictData>
                    </label>
                    <label> 用户账号:&nbsp;
                        <input class="form-control" name="search_LIKE_accountNo"/>
                    </label>
                    <label> 应用id:&nbsp;
                        <input class="form-control" name="search_EQ_app.id"/>
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
                    <so:hasPermission name="Total:ContentTotal:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button">
                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Total:ContentTotal:search">
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
                       data-id-field="id" data-unique-id="id" data-sort-name="createDate"
                       data-sort-order="desc">
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax(
        'loadScripts',
        scripts,
        function () {
            var ajaxListUrl = contextPath + '/total/contentTotal/list';
            var ajaxDelUrl = contextPath + '/total/contentTotal/remove';

            <x:dictData dictCode="Content_Total_Type" var="contentTotalType">
            <x:jsonArray varName="contentTotalType" keyName="code" valName="name" items="${contentTotalType}" ></x:jsonArray>
            </x:dictData>

            var columns = [{
                field: 'state',
                checkbox: true,
                title: "选择"
            }, {
                field: 'app.name',
                sortable: false,
                title: "应用"
            }, {
                field: 'type',
                sortable: false,
                title: "用户行为类型",
                formatter: function (value, row, index) {
                    var show = value;
                    $.each(contentTotalType, function () {
                        if (this.code == value) {
                            show = this.name;
                        }
                    });
                    return show;
                }
            }, {
                field: 'accountNo',
                sortable: false,
                title: "用户账号"
            }, {
                field: 'bizValue',
                sortable: false,
                title: "内容"
            }, {
                field: 'number',
                sortable: false,
                title: "集数"
            }, {
                field: 'duration',
                sortable: false,
                title: "时长(秒)"
            }, {
                field: 'createDate',
                sortable: false,
                title: "创建时间"
            },];
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

                $('#del').on('click', function (e) {
                    doDelete(ajaxDelUrl, "table-data", function () {
                        $('#table-data').bootstrapTable('selectPage', 1);
                    });
                });
            });
        });
</script>