<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="row">
<div class="col-lg-3">
	<div class="widget-box transparent">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title lighter smaller">区域</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main padding-8">
				<div id="treeview" class="tree"></div>
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
					<label> 名称:&nbsp;
						<input name="search_LIKE_name"class="form-control" placeholder="请输入名称" type="text">
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
	
	var ajaxListUrl = contextPath + '/manage/area/list';
	var ajaxTreeUrl = contextPath + '/manage/area/tree';	
    
	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'name',
		sortable : false,
		title : "地区"
	}, {
		field : 'shortName',
		sortable : false,
		title : "简称"
	}, {
		field : 'jd',
		sortable : false,
		title : "经度"
	}, {
		field : 'wd',
		sortable : false,
		title : "纬度"
	}, {
		field : 'level',
		sortable : false,
		title : "地区级别"
	}, {
		field : 'priority',
		sortable : true,
		title : "排序(优先级)"
	}, {
		field : 'parent.name',
		sortable : false,
		title : "上级区域"
	},
	];

	jQuery(function($) {
		initTree("treeview", ajaxTreeUrl, false, {
			loaded: function(){
				$('#Select_frmSearch').find('#pid').val("1");//区域树默认选中中国
				initTable('Select_table-data', columns, ajaxListUrl, 'Select_frmSearch');
			},
			selNode : function(node, elem) {
				$('#Select_frmSearch').find('#pid').val(node.id == '0' ? null :node.id);//node.id=0表示点击虚拟根：所有区域
				$('#Select_table-data').bootstrapTable('selectPage', 1);
			}
		});		
		
		
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