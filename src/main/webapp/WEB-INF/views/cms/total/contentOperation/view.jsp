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
                    <label> 相关性Id:&nbsp;
                        <input name="search_EQ_correlateId" class="form-control" placeholder="请输入相关性Id" type="text">
                    </label>
                    <label> 内容名称:&nbsp;
                        <input name="search_LIKE_content.name" class="form-control" placeholder="请输入内容名称" type="text">
                    </label>
                    <label> 注入状态:&nbsp;
                        <x:dictData dictCode="Syn_Type" var="synType">
                            <x:select defaultOption="请选择" hasDefault="true" var="synType"
                                      items="${synType}" id="synType" name="search_EQ_status"
                                      className="form-control">
                                <x:option value="${synType.code }" text="${synType.name }" selected=""></x:option>
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
                    <so:hasPermission name="Total:ContentOperation:remove">
                        <button id="send" class="btn btn-xs btn-success" type="button">
                            <span class="glyphicon glyphicon-edit"></span>&nbsp;重发
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Total:ContentOperation:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button">
                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Total:ContentOperation:search">
                        <button id="search" class="btn btn-xs btn-primary" type="button">
                            <span class="glyphicon glyphicon-search"></span>&nbsp;查询
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
                       data-id-field="id" data-unique-id="id" data-sort-name="id"
                       data-sort-order="asc">
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
            var ajaxListUrl = contextPath + '/total/contentOperation/list';
            var ajaxSendUrl = contextPath + '/total/contentOperation/send';
            var ajaxDelUrl = contextPath + '/total/contentOperation/remove';

            <x:dictData dictCode="Syn_Type" var="synType">//注入状态
            <x:jsonArray varName="synType" keyName="code" valName="name" items="${synType}" ></x:jsonArray>
            </x:dictData>

            var columns = [{
                field: 'state',
                checkbox: true,
                title: "选择"
            }, {
                field: 'correlateId',
                sortable: false,
                title: "相关性id"
            }, {
                field: 'content.id',
                sortable: false,
                title: "内容id"
            }, {
                field: 'content.name',
                sortable: false,
                title: "内容名称"
            }, {
                field: 'info',
                sortable: false,
                title: "其他信息"
            }, {
                field: 'status',
                sortable: false,
                title: "注入状态",
                formatter: function (value, row, index) {
                    var show = value;
                    $.each(synType, function () {
                        if (this.code == value) {
                            show = this.name;
                        }
                    });
                    return show;
                }
            }, {
                field: 'createDate',
                sortable: false,
                title: "创建时间"
            }, {
                field: 'modifyDate',
                sortable: false,
                title: "修改时间"
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

                $('#send').on('click', function (e) {
                    doAction(ajaxSendUrl, "table-data", "您确定要执行该操作么?", function () {
                        $('#table-data').bootstrapTable('selectPage', 1);
                    });
                });

                $('#del').on('click', function (e) {
                    doDelete(ajaxDelUrl, "table-data", function () {
                        $('#table-data').bootstrapTable('selectPage', 1);
                    });
                });
            });
        });
</script>