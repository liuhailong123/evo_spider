<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form">
			<input type="hidden" id="id" name="id" value="${entity.id}" /> 
			<input type="hidden" id="indexConfId" name="indexConfId" value="${entity.indexConfId}" />
			<input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />

			<div class="col-lg-8">
				<div class="form-group">
					<label class="col-lg-3 control-label">类型:</label>
					<div class="col-lg-8">
						<x:dictData dictCode="Index_Position_type" var="IndexPositiontype">
							<x:select defaultOption="请选择" hasDefault="false" var="IndexPositiontype" items="${IndexPositiontype}" id="type" name="type" className="form-control">
								<x:option value="${IndexPositiontype.code }" text="${IndexPositiontype.name }" selected="${entity.type eq IndexPositiontype.code}"></x:option>
							</x:select>
						</x:dictData>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">内容Id:</label>
					<div class="col-lg-8">
						<input type="text"  class="form-control" id="contentId" name="contentId" value="${entity.contentId}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">内容名称:</label>
					<span class="text-danger">*</span>
					<div class="col-lg-5">
						<input type="text"  class="form-control" id="name" name="name" value="${entity.name}" />
					</div>
					<div class="col-lg-3">
						<input name="Select_btnSearch" type="button" value="选择"  onclick="selectByType()" class="btn btn-primary btn-sm form-control">
						</input>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">背景图:</label>
					<div class="col-lg-5">
						<input type="text"  class="form-control" id="bgImg" name="bgImg" value="${entity.bgImg}" />
					</div>
					<div class="col-lg-3">
						<input name="Select_btnSearch" type="button" value="选择"  onclick="selectPicture(1)" class="btn btn-primary btn-sm form-control">
						</input>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">焦点图:</label>
					<div class="col-lg-5">
						<input type="text"  class="form-control" id="focusImg" name="focusImg" value="${entity.focusImg}" />
					</div>
					<div class="col-lg-3">
						<input name="Select_btnSearch" type="button" value="选择"  onclick="selectPicture(2)" class="btn btn-primary btn-sm form-control">
						</input>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">跳转地址:</label>
					<div class="col-lg-8">
						<input type="text"  class="form-control" id="blankUrl" name="blankUrl" value="${entity.blankUrl}" />
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="form-group">
					<label class="col-lg-4 control-label">位置:</label>
					<span class="text-danger">*</span>
					<div class="col-lg-7">
						<input type="number" min="1" max="999" class="form-control" id="position" name="position" value="${entity.position}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-4 control-label">上:</label>
					<div class="col-lg-7">
						<input type="number" min="0" max="999" class="form-control" id="upPx" name="upPx" value="${entity.upPx}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-4 control-label">下:</label>
					<div class="col-lg-7">
						<input type="number" min="0" max="999" class="form-control" id="downPx" name="downPx" value="${entity.downPx}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-4 control-label">左:</label>
					<div class="col-lg-7">
						<input type="number" min="0" max="999" class="form-control" id="leftPx" name="leftPx" value="${entity.leftPx}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-4 control-label">右:</label>
					<div class="col-lg-7">
						<input type="number" min="0" max="999" class="form-control" id="rightPx" name="rightPx" value="${entity.rightPx}" />
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	var imgType=0;//图片类型

    /**
     * 封面图选择
     */
    function selectPicture(pictureType) {
        imgType=pictureType;
		showSelectDlg("选择封面图片", contextPath + '/sourceManage/picture/singlePictureSelect/2/?name=' + $("#name").val(),[ "Select_table-data" ],
            selectPictureCallBack,BootstrapDialog.SIZE_WIDE);
    }
    function selectPictureCallBack(obj) {
        if(imgType==1){
            $("#bgImg").val(obj[0].url);
		}else if(imgType==2){
            $("#focusImg").val(obj[0].url);
		}

    }

    function selectByType() {
        var url="";
        var type=$("#type").val();
        if(type==1){//直播
            url="/contentManage/liveBroadcast/select";
		}else if(type==2){//电影
            url="/contentManage/catalogueRelation/select/1";
		}else if(type==3){//剧集
            url="/contentManage/catalogueRelation/select/2";
        }else if(type==4){//栏目
            url="/columnManage/column/select";
        }else{//其他
            alert("请手动输入");
		}
		if(type<5){
            showSelectDlg("选择", contextPath + url,[ "Select_table-data" ],
                selectByTypeCallBack,BootstrapDialog.SIZE_WIDE);
		}

    }
    function selectByTypeCallBack(obj) {
		$("#contentId").val(obj[0].id);
		$("#name").val(obj[0].name);
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
                        "name" : {required : true},
                        // "bgImg" : {required : true},
						"position" : {required : true},
                        // "upPx" : {required : true},
                        // "downPx" : {required : true},
                        // "leftPx" : {required : true},
                        // "rightPx" : {required : true}
					},
					messages : {
						"name" : '请输入内容名称',
						// "bgImg" : '请输入背景图',
                        // "upPx" : '请输入正确上',
                        // "downPx" : '请输入正确下',
                        // "leftPx" : '请输入正确左',
                        // "rightPx" : '请输入正确右',
                        "position" : '请输入正确位置'
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
				});
	});
</script>