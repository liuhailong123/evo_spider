<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
		
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			
			<div class="form-group">
				<label class="col-lg-3 control-label">通知类型:</label>
				<div class="col-lg-8">
					<x:dictData dictCode="Notice_Type" var="NoticeType">
						<x:select defaultOption="请选择" hasDefault="true" var="NoticeType" items="${NoticeType}" id="type" name="type" className="form-control">
							<x:option value="${NoticeType.code }" text="${NoticeType.name }" selected="${entity.type eq NoticeType.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">阀值:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="number" min="1" class="form-control" id="count" name="count" value="${entity.count}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">通知模版:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text"  class="form-control" id="info" name="info" value="${entity.info}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否发布:</label>
				<div class="col-lg-8">
					<x:dictData dictCode="OFF_ON" var="OFFON">
						<x:select defaultOption="请选择" hasDefault="true" var="OFFON" items="${OFFON}" id="status" name="status" className="form-control">
							<x:option value="${OFFON.code }" text="${OFFON.name }" selected="${entity.status eq OFFON.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">

jQuery(function($) {

	$('#formDlg').validate(
		{
			errorElement : 'span',
			errorClass : 'help-block',
			focusCleanup : false,
			focusInvalid : false,
			onsubmit : false,
			rules : {
                "type" : {
                    required : true
                },
                "count" : {
                    required : true
                },
                "info" : {
                    required : true
                },
                "status" : {
                    required : true
                }
			},
			messages : {
				"name" : '请输入通知名称',
                "type" : '请选择通知类型',
                "info" : '请输入通知模版',
                "status" : '请选择是否发布'
			},
			highlight : function(e) {
				$(e).closest('.form-group').removeClass('has-info')
						.addClass('has-error');
			},
			success : function(e) {
				$(e).closest('.form-group').removeClass('has-error')
						.addClass('has-success');
				$(e).remove();
			},
		}
	);
});


</script>














