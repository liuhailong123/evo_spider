<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="aId" name="aId" value="${entity.AId}" />
			<input type="hidden" id="bId" name="bId" value="${entity.BId}" />
			<input type="hidden" id="type" name="type" value="2" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="form-group">
                <label class="col-lg-3 control-label">类型:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="Publish_Content_Type" var="publishContentType">
                        <x:select defaultOption="请选择" hasDefault="true" var="publishContentType" items="${publishContentType}" id="contentType" name="contentType" className="form-control">
                            <x:option value="${publishContentType.code }" text="${publishContentType.name }" selected="${entity.contentType eq publishContentType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
			<div class="form-group">
				<label class="col-lg-3 control-label">名称:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
				<div class="col-lg-2">
					<input id="Select_btnSearch" name="Select_btnSearch" type="button" value="选择"  onclick="selectContent()" class="btn btn-primary btn-sm form-control">
					</input>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">排序:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="sort" name="sort" value="${entity.sort}" />
				</div>
			</div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否启用:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="OFF_ON" var="offOn">
                        <x:select defaultOption="请选择" hasDefault="true" var="offOn" items="${offOn}"  id="publish" name="publish" className="form-control">
                            <x:option value="${offOn.code }" text="${offOn.name }" selected="${entity.publish eq offOn.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
		</form>
	</div>
</div>
<script type="text/javascript">
	//查询
	function selectContent() {
		if($("#contentType").val()==''){
			CmMsg.error("请选择类型");
		}else{
	        showSelectDlg("选择内容", contextPath + '/sourceManage/television/select/' + $("#contentType").val(), ["Select_table-data"],
	        		selectContentCallBack, BootstrapDialog.SIZE_WIDE);
		}
	}
	
	function selectContentCallBack(obj) {
	//		console.log(obj);
	    $("#bId").val(obj[0].id);
	    $("#name").val(obj[0].name);
	    
	}
jQuery(function($) {
	
	$('#formDlg').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusCleanup : false,
		focusInvalid : false,
		onsubmit : false,
		rules : {
			"contentType" : {
				required : true
			},
			"name" : {
				required : true
			},
			"sort" : {
				required : true
			},
			"publish" : {
				required : true
			}
		},
		messages : {
			"contentType" : '请选择类型',
			"name" : '请选择相应内容',
			"sort" : '请输入排序',
			"publish" : '请选择发布状态'
		},
		highlight : function(e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},
		success : function(e) {
			$(e).closest('.form-group').removeClass('has-error').addClass('has-success');
			$(e).remove();
		},
	});	
});
</script>