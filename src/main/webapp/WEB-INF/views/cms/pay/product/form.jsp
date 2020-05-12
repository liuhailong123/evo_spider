<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="application/x-www-form-urlencoded" method="post">
		
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="form-group">
				<label class="col-lg-3 control-label">应用Id:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="appId" name="appId" value="${entity.appId}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">产品类型:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<x:dictData dictCode="Product_Type" var="productType">
						<x:select defaultOption="请选择" hasDefault="false" var="productType" items="${productType}" id="type" name="type" className="form-control">
							<x:option value="${productType.code }" text="${productType.name }" selected="${entity.type eq productType.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">产品套餐名称:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">产品套餐信息:</label>
				<div class="col-lg-8">
					<textarea class="form-control" id="info" name="info">${entity.info}</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">产品套餐编码:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="code" name="code" value="${entity.code}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">三方产品Id:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="thirdPartyId" name="thirdPartyId" value="${entity.thirdPartyId}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">三方产品编码:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="thirdPartyCode" name="thirdPartyCode" value="${entity.thirdPartyCode}" />
				</div>
			</div>
			<%--<div class="form-group">
				<label class="col-lg-3 control-label">三方boss产品编码:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="thirdssPartyCode" name="thirdBossPartyCode" value="${entity.thirdBossPartyCode}" />
				</div>
			</div>--%>
			<div class="form-group">
				<label class="col-lg-3 control-label">产品内容编码:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="contentCode" name="contentCode" value="${entity.contentCode}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">原价:</label><span class="text-danger">*</span>
				<div class="col-lg-5">
					<input type="number" min="0" class="form-control" id="originalPrice" name="originalPrice" value="${entity.originalPrice}" />
				</div>
				<div class="col-lg-3">
					<span class="form-control">分</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">优惠价:</label>
				<div class="col-lg-5">
					<input type="number" min="0" class="form-control" id="currentPrice" name="currentPrice"  value="${entity.currentPrice}" />
				</div>
				<div class="col-lg-3">
					<span class="form-control">分</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否短信通知:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<x:dictData dictCode="OFF_ON" var="OFFON">
						<x:select defaultOption="请选择" hasDefault="true" var="OFFON" items="${OFFON}" id="shortMssageInform" name="shortMssageInform" className="form-control">
							<x:option value="${OFFON.code }" text="${OFFON.name }" selected="${entity.shortMssageInform eq OFFON.code}"></x:option>
						</x:select>
					</x:dictData>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">排序:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="number" class="form-control" id="sort" name="sort" value="${entity.sort}" />
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
                "appId" : {
                    required : true
                },
				"name" : {
					required : true
				},
                "code" : {
                    required : true
                },
                "thirdPartyId" : {
                    required : true
                },
                "thirdPartyCode" : {
                    required : true
                },
                "contentCode" : {
                    required : true
                },
                "originalPrice" : {
                    required : true
                },
                "shortMssageInform" : {
                    required : true
                },
                "sort" : {
                    required : true
                }
			},
			messages : {
                "appId" : '请输入应用Id',
				"name" : '请输入名称',
                "code" : '请输入编码',
                "thirdPartyId" : '请输入三方id',
                "thirdPartyCode" : '请输入三方编码',
                "contentCode" : '请输入产品内容编码',
                "originalPrice" : '请输入原价',
                "shortMssageInform" : '请选择短信通知',
                "sort" : '请输入排序'
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














