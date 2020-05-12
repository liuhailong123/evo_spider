<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
		
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="content" name="content" value="${entity.content}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			
			<div class="form-group">
				<label class="col-lg-3 control-label">名称:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">简介:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="info" name="info" value="${entity.info}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">模版编码:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="templateCode" name="templateCode" value="${entity.templateCode}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">模版图:</label> 
				<span class="text-danger">*</span>
				<div class="col-lg-8" id="fileDiv">
					<c:if test="${entity.content == '' || entity.content == null}">
						<input type="file" id="file" name="file" />
					</c:if>
					<c:if test="${entity.content != '' && entity.content != null}">
						<ul class="attachment-list pull-left list-unstyled">
							<li class="col-lg-12">
								<a class="attached-file" title="" href="${entity.content }" rel="downloadr" target="_blank"> 
									<i class="ace-icon fa fa-file-o bigger-100"></i> 
									<span>${entity.name}</span>
								</a> 
								<span class="action-buttons"> 
									<a style="cursor: pointer;" onclick="deleteFile(this,'${entity.id}');" title="删除"> 
										<i class="ace-icon fa fa-trash-o bigger-120 red"></i>
									</a>
								</span>
							</li>
						</ul>					
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否启用:</label><span class="text-danger">*</span>
				<div class="col-lg-8">
                    <x:dictData dictCode="IsRecommend" var="type">
                        <x:select items="${type }" var="type" name="enable" id="enable"
                            className="form-control">
                            <x:option selected="${entity.enable eq type.code}" value="${type.code }" text="${type.name }"></x:option>
                        </x:select>
                    </x:dictData>
				</div>				
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
//初始化附件上传控件
var initData = [];
initData['maxSize'] = "104857600";//上传文件的最大容量
initData['maxSizeMsg'] = "文件大小不能超过100M！";//最大容量提示
initData['denyExt'] = [ "exe", "mp4", "txt", "docx", "xlsx", "mp3" ];//禁止上传的文件类型
initData['allowExt'] = [ "jpg","png" ] ;//允许上传的文件类型
initData['allowExtMsg'] = "文件格式不正确！只能上传图片格式的文件";//文件格式提示
initFileUpLoad("formDlg", initData);

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
			},
			messages : {
				"name" : '请输入名称',
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

//删除附件
function deleteFile(obj, id) {
	BootstrapDialog.confirm({
		title : '确认提示',
		type : BootstrapDialog.TYPE_WARNING,
		btnCancelLabel : '取消', // <-- Default value is 'Cancel',
		btnOKLabel : '确认', // <-- Default value is 'OK',
		message : "您确定要删除该文件吗?",
		callback : function(result) {
			if (result) {
				$.ajax({
					url : contextPath + '/template/sectionTemplate/deleteFile',
					type : "post",
					data : {
						'id' : id,
					},
					success : function(data) {
						if (data.status == "OK") {//删除成功
							CmMsg.success(data.message, -1);
							$(obj).parent().parent().parent().remove();
							if ($("#fileDiv ul li").length == 0) {
								addFileUpload("formDlg", "fileDiv", "file",
										false, initData);
								//清空隐藏域内的信息
								$("#content").val("");
							}
						} else {
							CmMsg.error(data.message, -1);
						}
					}
				});
			}
		}
	});
};

</script>














