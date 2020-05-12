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
						<input id="searchNameInput" name="search_LIKE_source.name"class="form-control" placeholder="请输入名称" type="text" value="${name}">
					</label>
					<label> 类型:&nbsp;
						<x:dictData dictCode="videoType" var="videotype">
							<x:select defaultOption="请选择" hasDefault="true" var="videotype" items="${videotype}" id="videotype" name="search_EQ_type" className="form-control">
								<x:option value="${videotype.code }" text="${videotype.name }" selected=""></x:option>
							</x:select>
						</x:dictData>
					</label>
					<label> 清晰度:&nbsp;
						<x:dictData dictCode="videoDefinition" var="videodefinition">
							<x:select defaultOption="请选择" hasDefault="true" var="videodefinition" items="${videodefinition}" id="videodefinition" name="search_EQ_definition" className="form-control">
								<x:option value="${videodefinition.code }" text="${videodefinition.name }" selected=""></x:option>
							</x:select>
						</x:dictData>
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
					data-sort-name="type"
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
	var ajaxListUrl = contextPath + '/sourceManage/video/videoList';
	<x:dictData dictCode="videoType" var="videotype">
    <x:jsonArray varName="videotype" keyName="code" valName="name" items="${videotype}" ></x:jsonArray>
    </x:dictData>
    <x:dictData dictCode="videoDefinition" var="videodefinition">
    <x:jsonArray varName="videodefinition" keyName="code" valName="name" items="${videodefinition}" ></x:jsonArray>
    </x:dictData>
    <x:dictData dictCode="PlatForm" var="platform">
    <x:jsonArray varName="platform" keyName="code" valName="name" items="${platform}" ></x:jsonArray>
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
        field: 'platform',
        sortable: false,
        title: "平台来源",
        formatter: function (value, row, index) {
            var show = value;
            $.each(platform, function () {
                if (this.code == value) {
                    show = this.name;
                }
            });
            return show;
        }
    }, {
		field : 'type',
		sortable : true,
		title : "类型",
        formatter: function (value, row, index) {
            var showName = value;
            $.each(videotype, function () {
                if (this.code == value) {
                    showName = this.name;
                }
            });
            return showName;
        }
	}, {
		field : 'definition',
		sortable : true,
		title : "清晰度",
        formatter: function (value, row, index) {
            var showName = value;
            $.each(videodefinition, function () {
                if (this.code == value) {
                    showName = this.name;
                }
            });
            return showName;
        }
	}, {
		field : 'ext',
		sortable : false,
		title : "格式"
	},{
		field : 'size',
		sortable : false,
		title : "大小",
        formatter: function (value, row, index) {
        	if(value != null && value != ""){
        		return (value / 1024).toFixed(2) + "MB";
        	}
        }
	},{
		field : 'time',
		sortable : false,
		title : "时长",
		formatter: function (value, row, index) {
			if(value != null && value != ""){
                return (value / (1000 * 1000)).toFixed(2) + "秒";
			}
        }
	}, {
		field : 'resolution',
		sortable : false,
		title : "分辨率"
	}, {
		field : 'url',
		sortable : false,
		title : "地址"
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