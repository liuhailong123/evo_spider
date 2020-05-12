<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <input type="hidden" id="id" name="id" value="${data.id}"/>
            <input type="hidden" id="createDate" name="createDate" value="<fmt:formatDate value="${data.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
            <div class="form-group">
                <label class="col-lg-2 control-label">省网名称:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="name" name="name"
                           value="${data.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">省网编码:</label> <span
                    class="text-danger">*</span>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="code" name="code"
                           value="${data.code}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">FTP服务器:</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="ftpUrl" name="ftpUrl"
                           value="${data.ftpUrl}" placeholder="请输入ftp服务器地址"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">FTP用户名:</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="ftpUser" name="ftpUser"
                           value="${data.ftpUser}" placeholder="请输入ftp服务器用户名"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">FTP密码:</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="ftpPassword" name="ftpPassword"
                           value="${data.ftpPassword}" placeholder="请输入ftp服务器密码"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">FTP端口:</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="ftpPort" name="ftpPort"
                           value="${data.ftpPort}" placeholder="请输入ftp端口"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">FTP域名:</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="ftpHost" name="ftpHost"
                           value="${data.ftpHost}" placeholder="请输入ftp服务器访问域名"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">图片域名:</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="imageHost" name="imageHost"
                           value="${data.imageHost}" placeholder="请输入图片访问域名"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">状态:</label>
                <div class="col-lg-9">
                    <x:dictData dictCode="Menu_Status" var="dictStatus">
                        <x:select var="status" items="${dictStatus}" id="enable" name="enable" className="form-control">
                            <x:option value="${status.code }" text="${status.name }"
                                      selected="${data.enable eq status.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    jQuery(function ($) {
        $('#formDlg').validate(
            {
                errorElement: 'span',
                errorClass: 'help-block',
                focusCleanup: false,
                focusInvalid: false,
                onsubmit: false,
                rules: {
                    "name": {
                        required: true
                    },
                    "code": {
                        required: true
                    }
                },
                messages: {
                    "name": '请输入省网名称',
                    "code": '请输入省网编码',
                },
                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-info')
                        .addClass('has-error');
                },
                success: function (e) {
                    $(e).closest('.form-group').removeClass('has-error')
                        .addClass('has-success');
                    $(e).remove();
                },
            });
    });
</script>