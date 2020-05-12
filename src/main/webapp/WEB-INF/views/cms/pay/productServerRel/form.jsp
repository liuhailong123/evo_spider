<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
		
			<input type="hidden" id="id" name="productId" value="${productId}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			
			<div class="form-group">
				<label class="col-lg-3 control-label">名称:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="name" name="name" value="" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">服务名称:</label><span class="text-danger">*</span>
				<div class="col-lg-6">
					<input type="hidden" id="serverId" name="serverId" value="" />
					<input type="text" class="form-control" id="serverName" name="serverName" value="" readonly="readonly"/>
				</div>
				<div class="col-lg-2">
					<input id="Select_btnSearch" name="Select_btnSearch" type="button" value="选择"  onclick="selectServer()" class="btn btn-primary btn-sm form-control">
					</input>
				</div>
			</div>

			<div class="form-group">
				<label class="col-lg-3 control-label">规则名称:</label><span class="text-danger">*</span>
				<div class="col-lg-6">
					<input type="hidden" id="ruleId" name="ruleId" value="" />
					<input type="text" class="form-control" id="ruleName" name="ruleName" value="" readonly="readonly"/>
				</div>
				<div class="col-lg-2">
					<input id="Select_btnSearch" name="Select_btnSearch" type="button" value="选择"  onclick="selectRule()" class="btn btn-primary btn-sm form-control">
					</input>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
    function selectServer(){
        showSelectDlg("选择服务", contextPath+ '/pay/server/select', ["Select_table-data"],
            selectServerCallBack,BootstrapDialog.SIZE_WIDE);
    }

    function selectServerCallBack(obj){
        $("#serverName").val(obj[0].name);

        $("#serverId").val(obj[0].id);
    }

    function selectRule(){
        showSelectDlg("选择规则", contextPath+ '/pay/rule/select', ["Select_table-data"],
            selectRuleCallBack,BootstrapDialog.SIZE_WIDE);
    }

    function selectRuleCallBack(obj){
        $("#ruleName").val(obj[0].name);
        $("#ruleId").val(obj[0].id);
    }
jQuery(function($) {
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
                "serverName" : {
                    required : true
                },
                "ruleName" : {
                    required : true
                }
			},
			messages : {
				"name" : '请输入名称',
                "serverName" : '请选择服务',
                "ruleName" : '请选择规则'
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














