<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
		
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			
			<div class="form-group">
				<label class="col-lg-3 control-label">公告名称:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">公告类型:</label>
				<div class="col-lg-8">
					<x:dictData dictCode="AnnouncementType" var="announcementType">
						<x:select defaultOption="请选择" hasDefault="true" var="announcementType" items="${announcementType}" id="type" name="type" className="form-control">
							<x:option value="${announcementType.code }" text="${announcementType.name }" selected="${entity.type eq announcementType.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">公告内容:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text"  class="form-control" id="content" name="content" value="${entity.content}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">跳转地址:</label>
				<div class="col-lg-8">
					<input type="text"  class="form-control" id="blankUrl" name="blankUrl" value="${entity.blankUrl}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">生效时间:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="startTime" name="startTime" value="${entity.startTime}" readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">失效时间:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="endTime" name="endTime" value="${entity.endTime}" readonly="readonly" />
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

    $("#startTime").jeDate({
        isTime:true,
        format: "YYYY-MM-DD hh:mm:ss",
    });

    $("#endTime").jeDate({
        isTime:true,
        format: "YYYY-MM-DD hh:mm:ss",
    });

	$('#formDlg').validate(
		{
			errorElement : 'span',
			errorClass : 'help-block',
			focusCleanup : false,
			focusInvalid : false,
			onsubmit : false,
			rules : {
				"name" : {
					required : true
				},
                "type" : {
                    required : true
                },
                "content" : {
                    required : true
                },
                "startTime" : {
                    required : true
                },
                "endTime" : {
                    required : true
                },
                "status" : {
                    required : true
                },
                "blankUrl" : {
                    required : true
                }
			},
			messages : {
				"name" : '请输入公告名称',
                "type" : '请选择公告类型',
                "content" : '请输入公告内容',
                "startTime" : '请输入生效时间',
                "endTime" : '请输入失效时间',
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














