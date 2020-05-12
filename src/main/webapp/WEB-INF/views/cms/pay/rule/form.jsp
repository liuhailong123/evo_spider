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
				<label class="col-lg-3 control-label">名称:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">单位:</label>
				<div class="col-lg-8">
					<x:dictData dictCode="Charge_Mode_Type" var="ChargeModeType">
						<x:select defaultOption="请选择" hasDefault="true" var="ChargeModeType" items="${ChargeModeType}" id="unit" name="unit" className="form-control">
							<x:option value="${ChargeModeType.code }" text="${ChargeModeType.name }" selected="${entity.unit eq ChargeModeType.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">数量:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="number" min="1" class="form-control" id="count" name="count" value="${entity.count}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">生效时间:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="takeEffectTime" name="takeEffectTime" value="${entity.takeEffectTime}" readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">失效时间:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="loseEfficacyTime" name="loseEfficacyTime" value="${entity.loseEfficacyTime}" readonly="readonly" />
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">

jQuery(function($) {

    $("#takeEffectTime").jeDate({
        isTime:true,
        format: "YYYY-MM-DD hh:mm:ss",
    });

    $("#loseEfficacyTime").jeDate({
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
                "unit" : {
                    required : true
                },
                "count" : {
                    required : true
                },
                "takeEffectTime" : {
                    required : true
                },
                "loseEfficacyTime" : {
                    required : true
                }
			},
			messages : {
				"name" : '请输入名称',
                "unit" : '请输入单位',
                "count" : '请输入数量',
                "takeEffectTime" : '请输入生效时间',
                "loseEfficacyTime" : '请输入失效时间'
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














