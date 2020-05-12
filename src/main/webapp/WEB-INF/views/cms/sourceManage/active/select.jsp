<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<div class="widget-box transparent" id="divSearch">
			<div class="widget-body">
				<div class="widget-main">
					<form name="frmSearch" id="Select_frmSearch" target="_self" class="form-inline searchCondition">
						<label> 名称:&nbsp;
							<input name="search_LIKE_name"class="form-control" placeholder="请输入活动名称" type="text">
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
	var ajaxListUrl = contextPath + '/sourceManage/active/list';
	
	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'name',
		sortable : false,
		title : "活动名称"
	}, {
		field : 'author',
		sortable : false,
		title : "作者"
	}, {
		field : 'synopsis',
		sortable : false,
		title : "简介"
	}, {
		field : 'sponsor',
		sortable : false,
		title : "主办方"
	}, {
		field : 'validTime',
		sortable : false,
		title : "生效时间"
	}, {
		field : 'unValidTime',
		sortable : false,
		title : "失效时间"
	}, {
		field : 'info',
		sortable : false,
		title : "详情",
        formatter : function(value, row, index) {
            if(value != null){
                var id = row.id;
                if(value != null){
                    return "<a style=\"cursor:pointer\" onclick=\"_preview('"+id+"')\">查看</a>";
                }
            }
        }
	}, {
        field : 'imgUrl',
        sortable : false,
        title : "活动海报",
        formatter : function(value, row, index) {
            if(value != null && value != ""){
                return "<a style=\"cursor:pointer\"title='"+value+"' href='"+value+"' target=\"_blank\">查看</a>";
            }
        }
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
function _preview(id){
    console.log(id);
    var ajaxPreviewUrl = contextPath + '/sourceManage/active/preview';
    var ajaxUrl = ajaxPreviewUrl + '/'+id;
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
 		size: BootstrapDialog.SIZE_WIDE,
        closable: true,
        draggable: true,
        closeByBackdrop: false,
        title: "预览",
        message: $('<div></div>').load(ajaxUrl),
        buttons: [{
            id: 'btn-Cancel',
            icon: 'glyphicon glyphicon-remove',
            label: '关闭',
            cssClass: 'btn-default',
            autospin: false,
            action: function(dialogRef){
                dialogRef.close();
            }
        }]
    });
}
</script>