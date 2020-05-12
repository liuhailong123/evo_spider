<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
<div class="col-lg-12">
	<div class="widget-box transparent" id="divSearch">
		<div class="widget-body">
			<div class="widget-main">
				<form name="frmSearch" id="Select_frmSearch" target="_self"
					class="form-inline searchCondition">
					<label> 名称:&nbsp;
						<input name="search_LIKE_name"class="form-control" placeholder="请输入名称" type="text">
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
					data-id-field="id" 
					data-unique-id="id" 
					data-sort-name="modifyDate"
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
	var ajaxListUrl = contextPath + '/contentManage/liveBroadcast/list';

    <x:dictData dictCode="LiveChannelType" var="liveChannelType">
    <x:jsonArray varName="liveChannelType" keyName="code" valName="name" items="${liveChannelType}" ></x:jsonArray>
    </x:dictData>

    <x:dictData dictCode="LiveChannelStatus" var="liveChannelStatus">
    <x:jsonArray varName="liveChannelStatus" keyName="code" valName="name" items="${liveChannelStatus}" ></x:jsonArray>
    </x:dictData>

	var columns = [ {
        field : 'state',
        checkbox : true,
        title : "选择"
    }, {
        field : 'id',
        sortable : false,
        title : "ID"
    }, {
        field : 'number',
        sortable : false,
        title : "频道号"
    }, {
        field : 'name',
        sortable : false,
        title : "频道名称"
    }, {
        field : 'type',
        sortable : false,
        title : "频道类型",
        formatter: function (value, row, index) {
            var show = value;
            $.each(liveChannelType, function () {
                if (this.code == value) {
                    show = this.name;
                }
            });
            return show;
        }
    }, {
        field : 'uptime',
        sortable : false,
        title : "上线时间"
    }, {
        field : 'downtime',
        sortable : false,
        title : "下线时间"
    }, {
        field : 'status',
        sortable : false,
        title : "状态",
        formatter: function (value, row, index) {
            var show = value;
            $.each(liveChannelStatus, function () {
                if (this.code == value) {
                    show = this.name;
                }
            });
            return show;
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


</script>