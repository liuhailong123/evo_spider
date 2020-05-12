<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<div class="widget-box transparent" id="divSearch">
			<div class="widget-body">
				<div class="widget-main">
					<form name="frmSearch" id="Select_frmSearch" target="_self" class="form-inline searchCondition">
						<input type="hidden" id="classify" name="search_EQ_classify" value="${type}" />
						<input type="hidden" id="classify" name="search_EQ_enable" value="1" />
						<label> 分类:&nbsp;
							<input name="search_LIKE_classifyTags"class="form-control" placeholder="请输入分类" type="text">
						</label>
						<label> 名称:&nbsp;
							<input name="search_LIKE_name"class="form-control" placeholder="请输入名称" type="text">
						</label>
						<label> 标题:&nbsp;
							<input name="search_LIKE_title"class="form-control" placeholder="请输入标题" type="text">
						</label>
						<label> 编码:&nbsp;
							<input name="search_LIKE_code"class="form-control" placeholder="请输入编码" type="text">
						</label>
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
        title : "名称"
    }, {
		field : 'code',
		sortable : false,
		title : "编码"
	}, {
		field : 'title',
		sortable : false,
		title : "标题"
	}, {
		field : 'classifyTags',
		sortable : false,
		title : "分类"
	}, {
		field : 'areaTags',
		sortable : false,
		title : "区域"
	}, {
		field : 'yearTags',
		sortable : false,
		title : "年代"
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