<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="../../../include.inc.jsp"%>
<%--<link rel="stylesheet" href="${contextPath}/static/rule/qddPhoneRule/main.css"/>--%>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/assets/js/jeDate/skin/jedate.css"/>
<script type="text/javascript" src="${contextPath}/static/assets/js/jeDate/jquery.jedate.js"></script>
<div class="row">
	<div class="col-lg-12">
		<form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
			<input type="hidden" id="id" name="id" value="${entity.id}" />
			<input type="hidden" id="createDate" name="createDate"
				   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<div class="form-group">
				<label class="col-lg-2 control-label">活动名称:</label><span class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="name" name="name" value="${entity.name}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">作者:</label><span class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="author" name="author" value="${entity.author}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">简介:</label><span class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="synopsis" name="synopsis" value="${entity.synopsis}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">主办方:</label><span class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="sponsor" name="sponsor" value="${entity.sponsor}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">生效时间:</label><span class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="validTime" name="validTime" value="${entity.validTime}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">失效时间:</label><span class="text-danger">*</span>
				<div class="col-lg-9">
					<input type="text" class="form-control" id="unValidTime" name="unValidTime" value="${entity.unValidTime}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">活动海报:</label>
				<div class="col-lg-7">
					<input type="text" class="form-control" id="imgName" name="imgName" value="${imgName}" />
					<input type="hidden" class="form-control" id="imgId" name="imgId" value="${imgId}" />
				</div>
				<div class="col-lg-2">
					<input id="Select_btnSearch" name="Select_btnSearch" type="button" value="选择"  onclick="selectImage()" class="btn btn-primary btn-sm form-control">
					</input>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">详情:</label><span class="text-danger">*</span>
				<textarea hidden="hidden"id="info" name="info" value=""/>
				<textarea hidden="hidden"id="otherInfo" name="otherInfo">${entity.otherInfo}</textarea>
				<div class="col-lg-9">
					<div class="wysiwyg-editor" id="remark" onmouseover="voluation()">${entity.info}</div>
				</div>
			</div>

		</form>
	</div>
</div>
<script type="text/javascript">
/**
 * 日期选择
 */
$("#validTime").jeDate({
    isTime:true,
    format: "YYYY-MM-DD hh:mm:ss",
    minDate:"2014-09-19 00:00:00"

});

/**
 * 日期选择
 */
$("#unValidTime").jeDate({
    isTime:true,
    format: "YYYY-MM-DD hh:mm:ss",
    minDate:"2014-09-19 00:00:00"

});
	function voluation(){
	    var cons = $('#remark').html();
	    $("#info").val(cons);
	}
    jQuery(function($) {
        $('#remark').ace_wysiwyg({
            toolbar : [ 'font', null, 'fontSize', null, {
                name : 'bold',
                className : 'btn-info'
            }, {
                name : 'italic',
                className : 'btn-info'
            }, {
				name : 'underline',
				className : 'btn-info'
            }, null, {
				name : 'insertunorderedlist',
				className : 'btn-success'
			}, {
				name : 'insertorderedlist',
				className : 'btn-success'
			}, {
				name : 'outdent',
				className : 'btn-purple'
			}, {
				name : 'indent',
				className : 'btn-purple'
			}, null, {
				name : 'justifyleft',
				className : 'btn-primary'
			}, {
				name : 'justifycenter',
				className : 'btn-primary'
			}, {
				name : 'justifyright',
				className : 'btn-primary'
			}, {
				name : 'justifyfull',
				className : 'btn-inverse'
			}, null, null, {
				name : 'insertImage',
				className : 'btn-success'
			}, null, 'foreColor', null, {
				name : 'undo',
				className : 'btn-grey'
			}, {
				name : 'redo',
				className : 'btn-grey'
			} ],
            'wysiwyg' : {
                fileUploadError : showErrorAlert
            }
        }).prev().addClass('wysiwyg-style1');

        function showErrorAlert(reason, detail) {
            var msg = '';
            if (reason === 'unsupported-file-type') {
                msg = "Unsupported format " + detail;
            } else {
            }
            $(
                '<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
                + '<strong>File upload error</strong> '
                + msg + ' </div>').prependTo('#alerts');
        }

    	$('#formDlg').validate({
    		errorElement : 'span',
    		errorClass : 'help-block',
    		focusCleanup : false,
    		focusInvalid : false,
    		onsubmit : false,
    		rules : {
    			"name" : {
    				required : true
    			}
    		},
    		messages : {
    			"name" : '请输入名称'
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

    function selectImage(){
        showSelectDlg("选择图片", contextPath+ '/sourceManage/picture/singlePictureSelect2/2/'+$("#name").val(), ["Select_table-data"],
            selectImageCallBack,BootstrapDialog.SIZE_WIDE);
    }

    function selectImageCallBack(obj){
    	console.log(obj);
        $("#imgName").val(obj[0].source.name);
        $("#imgId").val(obj[0].id);
    }

</script>
