<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="col-lg-12">
	<div class="widget-box transparent" id="divSearch" hidden="true">
		<div class="widget-header widget-header-large">
			<h4 class="widget-title">搜索</h4>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<form name="frmSearch" id="frmSearch" target="_self"class="form-inline searchCondition">
					<label> 专题名称:&nbsp;
						<input name="search_LIKE_name"class="form-control" placeholder="请输入专题名称" type="text">
					</label>
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
				    <so:hasPermission name="ContentCenter:SourceConfig:Television:Section:add">	
				    	<button id="add" class="btn btn-xs btn-pink" type="button"><span class="glyphicon glyphicon-plus"></span>&nbsp;新增</button>
				    </so:hasPermission>
				    <so:hasPermission name="ContentCenter:SourceConfig:Television:Section:modify">
				    	<button id="edt" class="btn btn-xs btn-success" type="button"><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</button>
				    </so:hasPermission>
				    <so:hasPermission name="ContentCenter:SourceConfig:Television:Section:remove">
				    	<button id="del" class="btn btn-xs btn-danger" type="button"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</button>
				    </so:hasPermission>
					<so:hasPermission name="ContentCenter:SourceConfig:Television:Section:search">
						<button id="search" class="btn btn-xs btn-primary" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
					</so:hasPermission>
				</div>
			</div>
			<div class="widget-toolbar">
			   <a data-action="reload" class="green2 bigger-120" href="#"><i class="ace-icon fa fa-refresh"></i></a>
			   <a data-action="fullscreen" class="blue2 bigger-120" href="#"><i class="ace-icon fa fa-arrows-alt"></i></a>
			 </div>
		</div>
		<div class="widget-body">
			<div class="widget-main no-padding">
				<table id="table-data"
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
<div class="col-lg-12">&nbsp;</div>
<div class="col-lg-12">
	<div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#content" data-toggle="tab">专题下内容</a></li>
<!--             <li ><a href="#picture" data-toggle="tab">图片</a></li> -->
        </ul>
		<div class="tab-content">
			<div id="content" class="tab-pane in active">
		        <form id="frmSearchChild">
		            <input type="hidden" id="sectionId" name="search_EQ_aId" value="0"/>
		        </form>
				<div class="tab-pane fade in active" >
	                <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
	                    <div class="widget-header widget-header-large no-padding">
	                        <div class="widget-title smaller">
	                            <div id="toolbar" class="btn-group btn-corner">
				                    <so:hasPermission name="ContentCenter:SourceConfig:Television:Section:add">
										<button type="button" id="addChild"  class="btn btn-xs btn-pink">
											<span class="glyphicon glyphicon-plus"></span>&nbsp;新增
										</button>
				                    </so:hasPermission>
	                                <so:hasPermission name="ContentCenter:SourceConfig:Television:Section:remove">
	                                    <button id="delChild" class="btn btn-xs btn-danger" type="button">
	                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
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
	                            <table id="table-data-child"
	                                data-classes="table table-striped table-hover"
	                                data-pagination="true" data-id-field="id" data-unique-id="id"
	                                data-sort-name="sort" data-sort-order="asc">
	                            </table>
	                        </div>
	                    </div>
	                </div>
	            </div>		        
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var scripts = [];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	var ajaxListUrl = contextPath + '/sourceManage/section/list';
	var ajaxAddUrl = contextPath + '/sourceManage/section/add';
	var ajaxEdtUrl = contextPath + '/sourceManage/section/edit';
	var ajaxDelUrl = contextPath + '/sourceManage/section/remove';

	var columns = [ {
		field : 'state',
		checkbox : true,
		title : "选择"
	}, {
		field : 'name',
		sortable : false,
		title : "专题名称"
	}, {
		field : 'templateName',
		sortable : false,
		title : "所用模版"
	}, {
		field : 'previewUrl',
		sortable : false,
		title : "预览图",
		width : "200px",
		formatter : function(value, row, index) {
			if(value != null && value != ''){
				var html ="<img src="+value+'?imageView2/0/w/192/h/108'+" height=\"108\" width=\"192\"/>";
				return html;
			}
		}
	}, {
		field : 'createDate',
		sortable : false,
		title : "创建时间"
	}, {
		field : 'modifyDate',
		sortable : false,
		title : "修改时间"
	}
	];
	
    <x:dictData dictCode="OFF_ON" var="offOn">
    <x:jsonArray varName="offOn" keyName="code" valName="name" items="${offOn}" ></x:jsonArray>
    </x:dictData>
    
    <x:dictData dictCode="Publish_Content_Type" var="publishContentType">
    <x:jsonArray varName="publishContentType" keyName="code" valName="name" items="${publishContentType}" ></x:jsonArray>
    </x:dictData>

    var childColumns = [{
        field: 'state',
        checkbox: true,
        title: "选择"
    }, {
        field: 'name',
        sortable: true,
        title: "名称"
    }, {
        field: 'type',
        sortable: true,
        title: "类型",
        formatter: function (value, row, index) {
            var show = value;
            $.each(publishContentType, function () {
                if (this.code == value) {
                    show = this.name;
                }
            });
            return show;
        }
    }, {
		field : 'previewUrl',
		sortable : false,
		title : "预览图",
		width : "200px",
		formatter : function(value, row, index) {
			if(value != null && value != ''){
				var html ="<img src="+value+'?imageView2/0/w/192/h/108'+" height=\"108\" width=\"192\"/>";
				return html;
			}
		}
	}, {
        field: 'sort',
        sortable: true,
        title: "排序"
    }, {
        field: 'enable',
        sortable: false,
        title: "是否启用",
        formatter: function (value, row, index) {
            var show = value;
            $.each(offOn, function () {
                if (this.code == value) {
                    show = this.name;
                }
            });
            return show;
        }
    }
    ];
	jQuery(function($) {
		initTable('table-data', columns, ajaxListUrl, 'frmSearch');

		// 搜索域控制
		$('#search').on('click', function() {
			$('#divSearch').slideToggle("slow");
		});
		$('#frmSearch').find('#btnSearch').on('click', function() {
			$('#table-data').bootstrapTable('selectPage', 1);
		});

		$('#tbWidget').on('reload.ace.widget', function(ev) {
			$('#table-data').bootstrapTable('refresh', { silent : true });
		});		
		$('#add').on('click', function(e) {
            showAddOrEditDlg("新增", ajaxAddUrl, ajaxEdtUrl, "formDlg",
                    function (obj) {
                        $("#id").val(obj.id);
                        $("#code").val(obj.code);
                        $(".bootstrap-dialog-title").text("编辑");
                        $('#table-data').bootstrapTable('refresh', {silent: true});
                    }, function (obj) {
                        $('#table-data').bootstrapTable('refresh', {silent: true});
                    }, false, BootstrapDialog.SIZE_WIDE,1); 
		});

		$('#edt').on('click', function(e) {
            showEdtDlg2("编辑", ajaxEdtUrl, "table-data", "formDlg", function () {
                $('#table-data').bootstrapTable('refresh', {silent: true});
            }, false, BootstrapDialog.SIZE_WIDE,1);
		});

		$('#del').on('click', function(e) {
			doDelete(ajaxDelUrl, "table-data", function() {
				$('#table-data').bootstrapTable('selectPage', 1);
			});
		});
        //==========================================================================================================
        var ajaxListChildUrl = contextPath + '/contentManage/catalogueRelation/listSectionContent';
        var ajaxAddChildUrl = contextPath + '/contentManage/catalogueRelation/addSectionContent';
        var ajaxEdtChildUrl = contextPath + '/contentManage/catalogueRelation/editSectionContent';
        var ajaxdelChildUrl = contextPath + '/contentManage/catalogueRelation/remove';
        
        $('#table-data').on('load-success.bs.table', function() {
            var $table = $(this);
            var data = $table.bootstrapTable('getData');
            if(data && data.length > 0){
                $table.bootstrapTable('check', 0);
                var rowId=data[0].id;
	        	if(rowId==null||rowId==''){
	        		$('#frmSearchChild').find('#sectionId').val(0);
	        	}else{
	            	$('#frmSearchChild').find('#sectionId').val(data[0].id);
	        	}
                initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
            }else{
                initTable('table-data-child', childColumns, ajaxListChildUrl, 'frmSearchChild');
            }
        });
        
        $('#table-data').on('check.bs.table', function(elem, row) {
            $('#frmSearchChild').find('#sectionId').val(row.id);
            $('#table-data-child').bootstrapTable('refresh',{ silent : true });//刷新表单
        });
        
		$('#addChild').on('click', function(e) {
			showAddDlg("新增", ajaxAddChildUrl + "/" + $("#sectionId").val(), "formDlg", function() {
				$('#table-data-child').bootstrapTable('selectPage', 1);
			}, false);
		});

		$('#edtChild').on('click', function(e) {
			showEdtDlg("编辑", ajaxEdtChildUrl, "table-data-child", "formDlg", function() {
				$('#table-data-child').bootstrapTable('refresh', { silent : true });
			}, false);
		});

        $('#delChild').on('click', function (e) {
            doDelete(ajaxDelChildUrl, "table-dat-childa", function () {
                $('#table-data-child').bootstrapTable('selectPage', 1);
            });
        });        
        
	});
});
//==============================================js==========================================================
/**
 * 打开内容编辑页
 */
function showEdtDlg2(title, ajaxUrl, tableId, formId, callback, hasFile, size,type) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");
    if (selections !== undefined && selections !== null) {
        if (selections.length === 1) {
                var id = selections[0].id;
                var formUrl = ajaxUrl + "/" + id;
                BootstrapDialog.show({
                    type: BootstrapDialog.TYPE_PRIMARY,
                    size: size,
                    closable: true,
                    draggable: true,
                    closeByBackdrop: false,
                    title: title,
                    message: $('<div></div>').load(formUrl),
                    buttons: [{
                        id: 'btn-Cancel',
                        icon: 'glyphicon glyphicon-remove',
                        label: '关闭',
                        cssClass: 'btn-default',
                        autospin: false,
                        action: function (dialogRef) {
                            dialogRef.close();
                        }
                    }, {
                        id: 'btn-OK',
                        icon: 'glyphicon glyphicon-check',
                        label: '确认',
                        cssClass: 'btn-primary',
                        autospin: false,
                        action: function (dialogRef) {
                            var $button = this;
                            $button.disable();
                            $button.toggleSpin(true);
                            if ($('#' + formId).valid()) {
                                if (hasFile) {
                                    formFileProcess($('#' + formId), dialogRef, ajaxUrl, callback);
                                } else {
                                    // 设置内容与图片关系
                                    var jsonArrayImage = [];
                                    $.each($(".contentImageRow"), function (i, o) {
                                        var contentImage = new Object();
                                        contentImage.contentId = $("#id").val();
                                        var businessTypeTDId = $(o).attr("data-id");
                                        contentImage.businessType = $("#" + businessTypeTDId).val();
                                        contentImage.imageId = $(o).attr("data-imageId");
                                        contentImage.type = $(o).attr("data-type");
                                        jsonArrayImage.push(contentImage);
                                    })
                                    $("#contentImageHidden").val(JSON.stringify(jsonArrayImage));

                                    $.post(ajaxUrl, getParams(formId)).done(function (data) {
                                        if (data.status == 'OK') {
                                            dialogRef.close();
                                            CmMsg.success(data.message, -1);
                                            if (callback) {
                                                callback();
                                            }
                                        } else if (data.status == 'ERROR') {
                                            CmMsg.error(data.message, -1);
                                        } else if (data.status == 'TIMEOUT') {
                                            CmMsg.error(data.message, -1);
                                        } else {
                                            CmMsg.error("编辑失败或其他错误", -1);
                                        }
                                        $button.toggleSpin(false);
                                        $button.enable();
                                    });
                                }
                            } else {
                                $button.toggleSpin(false);
                                $button.enable();
                            }
                        }
                    }]
                });
        } else {
            CmMsg.warn("请选择一行数据记录进行操作.", -1);
        }
    } else {
        CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
    }
}
/**
 * 打开新增或者编辑页面
 * @param title
 * @param ajaxUrl
 * @param formId
 * @param callback
 * @param hasFile
 * @param size
 * @param type 1 图片 2 视频
 */
function showAddOrEditDlg(title, addAjaxUrl, editAjaxUrl, formId, addCallback, editCallback, hasFile, size,type) {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        size: size,
        closable: true,
        draggable: true,
        closeByBackdrop: false,
        title: title,
        message: $('<div></div>').load(addAjaxUrl),
        buttons: [{
            id: 'btn-Cancel',
            icon: 'glyphicon glyphicon-remove',
            label: '关闭',
            cssClass: 'btn-default',
            autospin: false,
            action: function (dialogRef) {
                dialogRef.close();
            }
        }, {
            id: 'btn-OK',
            icon: 'glyphicon glyphicon-check',
            label: '确认',
            cssClass: 'btn-primary',
            autospin: false,
            action: function (dialogRef) {
                saveOrUpdate(dialogRef, formId, addAjaxUrl, editAjaxUrl, addCallback, editCallback, hasFile, this,type);
            },
        }]
    });
}
/**
 * 打开内容添加页
 */
function saveOrUpdate(dialogRef, formId, addAjaxUrl, editAjaxUrl, addCallback, editCallback, hasFile, obj,type) {
    var $button = obj;
    $button.disable();
    $button.toggleSpin(true);
    if ($('#' + formId).valid()) {
        if (hasFile) {
            formFileProcess($('#' + formId), dialogRef, addAjaxUrl, callback);
        } else {
            if ($("#id").val() == "") {//新增
                $.post(addAjaxUrl, getParams(formId)).done(function (data) {
                    if (data.status == 'OK') {
//                    dialogRef.close();
                        CmMsg.success(data.message, -1);
                        if (addCallback) {
                            addCallback(data.rows);
                        }
                    } else if (data.status == 'ERROR') {
                        CmMsg.error(data.message, -1);
                    } else if (data.status == 'TIMEOUT') {
                        CmMsg.error(data.message, -1);
                    } else {
                        CmMsg.error("保存失败或其他错误", -1);
                    }
                    $button.toggleSpin(false);
                    $button.enable();
                });
            } else {// 修改
                // 设置内容与图片关系
                var jsonArrayImage = [];
                $.each($(".contentImageRow"), function (i, o) {
                    var contentImage = new Object();
                    contentImage.contentId = $("#id").val();
                    var businessTypeTDId = $(o).attr("data-id");
                    contentImage.businessType = $("#" + businessTypeTDId).val();
                    contentImage.imageId = $(o).attr("data-imageId");
                    contentImage.type = $(o).attr("data-type");
                    jsonArrayImage.push(contentImage);
                })
                $("#contentImageHidden").val(JSON.stringify(jsonArrayImage));

                $.post(editAjaxUrl, getParams(formId)).done(function (data) {
                    if (data.status == 'OK') {
//                    dialogRef.close();
                        CmMsg.success(data.message, -1);
                        if (editCallback) {
                            editCallback();
                        }
                    } else if (data.status == 'ERROR') {
                        CmMsg.error(data.message, -1);
                    } else if (data.status == 'TIMEOUT') {
                        CmMsg.error(data.message, -1);
                    } else {
                        CmMsg.error("编辑失败或其他错误", -1);
                    }
                    $button.toggleSpin(false);
                    $button.enable();
                });
            }
        }
    } else {
        $button.toggleSpin(false);
        $button.enable();
    }
}
</script>