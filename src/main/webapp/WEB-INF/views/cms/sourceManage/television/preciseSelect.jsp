<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<div class="widget-box transparent" id="divSearch">
			<div class="widget-body">
				<div class="widget-main">
					<form name="frmSearch" id="Select_frmSearch" target="_self" class="form-inline searchCondition">
						<label> 分类标签:&nbsp;
							<input name="search_LIKE_classifyTags"class="form-control" placeholder="请输入分类标签" type="text">
						</label>
						<label> 内容名称:&nbsp;
							<input name="search_EQ_name"class="form-control" placeholder="请输入内容名称" type="text" value="${name}">
						</label>
						<label> 内容标题:&nbsp;
							<input name="search_LIKE_title"class="form-control" placeholder="请输入内容标题" type="text">
						</label>
						<label> 内容编码:&nbsp;
							<input name="search_LIKE_code"class="form-control" placeholder="请输入内容编码" type="text">
						</label>
						<button id="btnSearch" type="button"class="btn btn-primary btn-sm form-control">
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
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	var ajaxListUrl = contextPath + '/sourceManage/television/list';
	
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
        title : "内容名称"
    }, {
		field : 'code',
		sortable : false,
		title : "内容编码"
	}, {
		field : 'title',
		sortable : false,
		title : "内容标题"
	}, {
		field : 'classifyTags',
		sortable : false,
		title : "分类标签"
	}, {
		field : 'areaTags',
		sortable : false,
		title : "区域标签"
	}, {
		field : 'yearTags',
		sortable : false,
		title : "年代标签"
	}
// 	, {
// 		field : 'enable',
// 		sortable : false,
// 		title : "是否启用",
//         formatter: function(value, row, index){
//             var show = value;
//             $.each(enable, function() {
//                 if (this.code == value) {
//                     show = this.name;
//                 }
//             });
//             return show;
//         }
// 	}
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