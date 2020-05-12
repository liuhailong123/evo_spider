<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<div class="widget-box transparent" id="divSearch">
			<div class="widget-body">
				<div class="widget-main">
					<form name="frmSearch" id="Select_frmSearch" target="_self" class="form-inline searchCondition">
						<label> 标题:&nbsp;
							<input name="search_LIKE_title" class="form-control" placeholder="请输入标题" type="text">
						</label>
						<label> 副标题:&nbsp;
							<input name="search_LIKE_subtitle" class="form-control" placeholder="请输入副标题"
								   type="text">
						</label>
						<label> 分类标签:&nbsp;
							<input name="search_LIKE_tags" class="form-control" placeholder="请输入分类标签"
								   type="text">
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
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
    var ajaxListUrl = contextPath + '/sourceManage/spiderContent/list';

    var columns = [{
        field: 'state',
        checkbox: true,
        title: "选择"
    }, {
        field: 'title',
        sortable: false,
        title: "标题"
    }, {
        field: 'subtitle',
        sortable: false,
        title: "副标题"
    }, {
        field: 'tags',
        sortable: false,
        title: "分类"
    }, {
        field: 'author',
        sortable: false,
        title: "作者"
    }, {
        field: 'publishDate',
        sortable: false,
        title: "出版日期"
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