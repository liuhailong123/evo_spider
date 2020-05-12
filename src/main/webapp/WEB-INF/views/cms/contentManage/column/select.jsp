<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-3">
		<div class="widget-box transparent">
			<div class="widget-header widget-header-large">
				<h4 class="widget-title lighter smaller">选择目录</h4>
			</div>
			<div class="widget-body">
				<div class="widget-main padding-8">
					<div id="treeview1" class="ztree"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-9">
		<div class="widget-box transparent" id="Select_divSearch" hidden="true">
			<div class="widget-header widget-header-large">
				<h4 class="widget-title">搜索</h4>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<form name="frmSearch" id="Select_frmSearch" target="_self"class="form-inline searchCondition">
						<input id="pid" name="search_EQ_parent.id" type="hidden" value="">
						<input id="level" name="search_EQ_level" type="hidden"/>
						<label> 目录名称:&nbsp;
							<input name="search_LIKE_name"class="form-control" placeholder="请输入目录名称" type="text">
						</label>
						<input style="display: none"/>
						<button id="Select_btnSearch" type="button"class="btn btn-primary btn-sm form-control">
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
						   data-pagination="true" data-single-select="true"
						   data-id-field="id" data-unique-id="id" data-sort-name="id"
						   data-sort-order="asc">
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
        var ajaxListUrl = contextPath + '/columnManage/column/listBySelect';
        var ajaxTreeUrl = contextPath + '/columnManage/column/columnsTree';
        //输出字典js变量
        <x:dictData dictCode="Enable_Type" var="enable">
        <x:jsonArray varName="enable" keyName="code" valName="name" items="${enable}" ></x:jsonArray>
        </x:dictData>

        var columns = [ {
            field : 'state',
            checkbox : true,
            title : "选择"
        }, {
            field : 'name',
            sortable : false,
            title : "名称"
        }, {
            field : 'columnCode',
            sortable : false,
            title : "栏目编码"
        }, {
            field : 'sort',
            sortable : true,
            title : "排序"
        }, {
            field : 'level',
            sortable : false,
            title : "级别"
        }, {
            field : 'enable',
            sortable : false,
            title : "是否启用",
            formatter: function(value, row, index){
                var show = value;
                $.each(enable, function() {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        },
        ];

        jQuery(function($) {
            var setting = {
                treeId :"treeview1",
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
                    onAsyncSuccess: function(event, treeId, treeNode, msg) {
                        initTable('Select_table-data', columns, ajaxListUrl, 'Select_frmSearch');
                    },
                    onClick: function(event, treeId, treeNode){
                        if(treeNode.pId == null){
                            $('#Select_frmSearch').find('#pid').val("");
                            $('#Select_frmSearch').find('#level').val(1);
                            $('#Select_table-data').bootstrapTable('selectPage', 1);
                        }else{
                            $('#Select_frmSearch').find('#pid').val(treeNode.id);
                            $('#Select_frmSearch').find('#level').val("");
                            $('#Select_table-data').bootstrapTable('selectPage', 1);
                        }
                    }
                }
            };

            $.fn.zTree.init($("#treeview1"), setting, null);

            $('#Select_frmsearch').on('click', function() {
                $('#Select_divSearch').slideToggle("slow");
            });

            // 搜索域控制
            $('#Select_frmSearch').find('#Select_btnSearch').on('click', function() {
                $('#Select_table-data').bootstrapTable('selectPage', 1);
            });

            $('#Select_tbWidget').on('reload.ace.widget', function(ev) {
                $('#Select_table-data').bootstrapTable('refresh', { silent : true });
            });

        });
    });
</script>