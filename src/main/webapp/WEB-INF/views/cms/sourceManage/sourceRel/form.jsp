<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="sourceId" name="sourceId" value="${entity.sourceId}" />
			<input type="hidden" id="fId" name="fId" value="${entity.fId}" />
			<input type="hidden" id="relType" name="relType" value="${entity.relType}" />
			<input type="hidden" id="relType" name="sourcetype" value="${entity.sourcetype}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="form-group">
                <label class="col-lg-2 control-label">业务类型:</label><span class="text-danger">*</span>
                <div class="col-lg-9">
                    <x:dictData dictCode="Picture_Business_Type" var="pictureBusinessType">
                        <x:select defaultOption="请选择" hasDefault="true" var="pictureBusinessType" items="${pictureBusinessType}" id="businessType" name="businessType" className="form-control">
                            <x:option value="${pictureBusinessType.code }" text="${pictureBusinessType.name }" selected="${entity.businessType eq pictureBusinessType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
		</form>
	</div>
</div>
<script type="text/javascript">
jQuery(function($) {
	$('#formDlg').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusCleanup : false,
		focusInvalid : false,
		onsubmit : false,
		rules : {
			"businessType" : {
				required : true
			}
		},
		messages : {
			"businessType" : '请选择业务类型'
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