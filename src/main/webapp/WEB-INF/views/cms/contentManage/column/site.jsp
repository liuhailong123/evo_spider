<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
<div class="col-lg-12">
	<div class="widget-box transparent" id="divSearch" hidden="true">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title">搜索</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form name="frmSearchSite" id="frmSearchSite" target="_self"class="form-inline searchCondition">
					<input id="level" name="search_EQ_level" type="hidden" value="1"/>
					<input id="roleId" name="roleId" type="hidden" value="${roleId}"/>
<!-- 					<label> 站点名称:&nbsp; -->
<!-- 						<input name="search_LIKE_name"class="form-control" placeholder="请输入站点名称" type="text"> -->
<!-- 					</label> -->
					<button id="btnSearch" type="button"class="btn btn-primary btn-sm form-control">
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
				    <so:hasPermission name="ContentManage:Content:show">	
				    	<button id="assignTo" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;授权</button>
				    </so:hasPermission>
<%-- 					<so:hasPermission name="ContentManage:Content:search"> --%>
<!-- 						<button id="search" class="btn btn-xs btn-primary" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button> -->
<%-- 					</so:hasPermission> --%>
				</div>
			</div>
			<div class="widget-toolbar">
			   <a data-action="reload" class="green2 bigger-120" href="#"><i class="ace-icon fa fa-refresh"></i></a>
			   <a data-action="fullscreen" class="blue2 bigger-120" href="#"><i class="ace-icon fa fa-arrows-alt"></i></a>
			 </div>
		</div>
		<div class="widget-body">
			<div class="widget-main no-padding">
				<table id="table-site"
					data-classes="table table-striped table-hover" 
					   data-pagination="true"
				       data-id-field="id"
				       data-unique-id="id"
				       data-sort-name="createDate"
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
	var ajaxListUrl = contextPath + '/columnManage/column/list/' + $('#roleId').val();
    
	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择", 
		formatter:function(value, row, index){
			if( row.assign ){
				return { checked:true };
			}
			return value;
		}
	}, {
		field : 'name',
		sortable : false,
		title : "站点名称", 
		clickToSelect: true
	}
	];

	jQuery(function($) {
		initTable('table-site', columns, ajaxListUrl, 'frmSearchSite');

		// 搜索域控制
		$('#search').on('click', function() {
			$('#divSearch').slideToggle("slow");
		});
		$('#frmSearchSite').find('#btnSearch').on('click', function() {
			$('#table-site').bootstrapTable('selectPage', 1);
		});

		$('#tbWidget').on('reload.ace.widget', function(ev) {
			$('#table-site').bootstrapTable('refresh', { silent : true });
		});	
		
		$('#assignTo').on('click', function(e){
			var selections = $('#table-site').bootstrapTable("getSelections");
			var ids = new Array();
			if(selections.length > 0){
				for (var i = 0; i < selections.length; i++) {
					ids.push(selections[i].id);
				}
			}
			var params = {
					roleId: $('#roleId').val(),
					siteIds: ids
			}
			$.post("${contextPath}/columnManage/column/assign", params).done(function(data){
				if(data.status == 'OK'){
	    			CmMsg.success(data.message, -1);
				} else if (data.status == 'ERROR'){
	    			CmMsg.error(data.message, -1);
				} else if (data.status == 'TIMEOUT'){
	    			CmMsg.error(data.message, -1);
				} else {
					CmMsg.error("分配站点失败!", -1);
				}
			});
		});
		
		
	});
});
</script>