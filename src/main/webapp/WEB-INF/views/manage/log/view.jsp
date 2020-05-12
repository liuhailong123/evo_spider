<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../include.inc.jsp"%>
<div class="col-lg-12">
	<div class="widget-box transparent" id="divSearch" hidden="true">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title">搜索</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
					<label> 账号:&nbsp;<input name="search_LIKE_operator"
						class="form-control" placeholder="请输入操作人账号" type="text">
					</label>
					<label> 操作:&nbsp;<input name="search_LIKE_operation"
						class="form-control" placeholder="请输入业务操作" type="text">
					</label>
					<label> IP:&nbsp;<input name="search_EQ_hostIp"
						class="form-control" placeholder="请输入操作IP" type="text">
					</label>
					<label> 操作结果:&nbsp;<input name="search_EQ_opResult"
						class="form-control" placeholder="请输入操作结果" type="text">
					</label>
					<label> 日志类型:&nbsp;
						<select name="search_EQ_logType">
							<option value="">请选择</option>
							<option value="LOGIN">登录</option>
							<option value="VISIT">访问</option>
							<option value="OPERATION">业务</option>
							<option value="EXCEPTION">异常</option>
						</select>
					</label>
					<label> 操作时间:&nbsp;
						<input name="search_GTE_opDate" class="form-control" placeholder="请输入操作开始时间" type="text"> 到
						<input name="search_LTE_opDate" class="form-control" placeholder="请输入操作结束时间" type="text">
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
					<%--
					<so:hasPermission name="Manage:Log:add">
						<button id="add" class="btn btn-xs btn-pink" type="button">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;新增
						</button>
					</so:hasPermission>
					<so:hasPermission name="Manage:Log:modify">
						<button id="edt" class="btn btn-xs btn-success" type="button">
							<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑
						</button>
					</so:hasPermission>
					 --%>
					<so:hasPermission name="Manage:Log:remove"><%--赋值删除权限--%>
						<button id="del" class="btn btn-xs btn-danger" type="button">
							<span class="glyphicon glyphicon-trash"></span>&nbsp;删除
						</button>
					</so:hasPermission>
					<so:hasPermission name="Manage:Log:search">
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
					data-id-field="id"
					data-unique-id="id" 
					data-sort-name="opDate" 
					data-sort-order="desc">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
var scripts = [];
$('.page-content-area').ace_ajax( 'loadScripts', scripts, function() {
			var ajaxListUrl = contextPath + '/manage/log/list';
			var ajaxDelUrl = contextPath + '/manage/log/remove';

			var columns = [ {
				field : 'state',
				checkbox : true,
				title : "选择"
			}, {
				field : 'operator',
				sortable : false,
				title : "操作人(账号)"
			}, {
				field : 'operation',
				sortable : false,
				title : "业务操作"
			}, {
				field : 'opResult',
				sortable : false,
				title : "操作结果"
			}, {
				field : 'hostIp',
				sortable : false,
				title : "操作IP"
			}, {
				field : 'opDate',
				sortable : true,
				title : "操作时间"
			}, {
				field : 'logType',
				sortable : true,
				title : "日志类型",
				formatter: function(value, row, index){
					if(value == 'LOGIN'){
						return '登录';
					} else if(value == 'VISIT'){
						return '访问';
					} else if(value == 'OPERATION'){
						return '业务';
					} else if(value == 'EXCEPTION'){
						return '异常';
					}
				}
			}, {
				field : 'opParams',
				sortable : false,
				title : "请求参数"
			}, ];
			jQuery(function($) {

				initTable('table-data', columns, ajaxListUrl, 'frmSearch');

				$('#search').on('click', function() {
					$('#divSearch').slideToggle("slow");
				});

				// 搜索域控制
				$('#frmSearch').find('#btnSearch').on('click', function() {
					$('#table-data').bootstrapTable('selectPage', 1);
				});

				$('#tbWidget').on('reload.ace.widget', function(ev) {
					$('#table-data').bootstrapTable('refresh', {
						silent : true
					});
				});

				$('#del').on('click', function(e) {
					doDelete(ajaxDelUrl, "table-data", function() {
						$('#table-data').bootstrapTable('selectPage', 1);
					});
				});
			});
		});
//-->
</script>
	