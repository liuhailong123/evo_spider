<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
<div class="col-lg-12">
	<div class="widget-box transparent" id="divSearch">
		<div class="widget-body">
			<div class="widget-main">
				<form name="frmSearch" id="Select_frmSearch" target="_self" class="form-inline searchCondition">
					<label> 名称:&nbsp;
						<input name="search_LIKE_name"class="form-control" placeholder="请输入产品名称" type="text">
					</label>
					<input style="display: none"/>
					<button id="Select_btnSearch" type="button"
						class="btn btn-primary btn-sm form-control">
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
	var ajaxListUrl = contextPath + '/pay/serverRuleRel/list';
    var columns = [ {
        field : 'state',
        checkbox : true,
        title : "选择"
    }, {
        field : 'name',
        sortable : true,
        title : "产品名称"
    }, {
        field : 'server.name',
        sortable : true,
        title : "服务名称"
    }, {
        field : 'rule.name',
        sortable : true,
        title : "规则名称"
    }
    ];

	jQuery(function($) {
		initTable('Select_table-data', columns, ajaxListUrl, 'Select_frmSearch');
		$('#Select_search').on('click', function() {
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