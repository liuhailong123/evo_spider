<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
        <input type="hidden" name="sourceId" id="sourceId1" value="${sourceId}"/>
        <div class="col-lg-12">
            <div class="form-group">
                <div>
                    <label class="col-lg-2 control-label">图片文件:</label>
                    <div class="col-lg-9" id="fileDiv">
                        <input type="file" id="files" name="files" multiple/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">横/竖:</label>
                <div class="col-lg-9">
                    <x:dictData dictCode="Picture_Type" var="pictureType">
                        <x:select defaultOption="请选择" hasDefault="true" var="pictureType" items="${pictureType}"
                                  id="pictureType" name="pictureType" className="form-control">
                            <x:option value="${pictureType.code }" text="${pictureType.name }"
                                      selected="${entity.type eq pictureType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    // 上传附件
    var task_file_uplad_url = contextPath + "/sourceManage/picture/pictureUpload";
    $("#files").fileinput({
        language: 'zh',// 多语言设置，需要引入local中相应的js，例如locales/zh.js
        theme: "explorer-fa",// 主题
        uploadUrl: task_file_uplad_url,// 上传地址
        allowedFileExtensions:['jpg', 'jpeg', 'png', 'gif', 'BMP', 'PSD', 'PDD', 'PDF', 'PCX', 'DXF', 'WMF', 'EMF'],
        minFileCount: 1,// 最小上传数量
        maxFileCount: 500,// 最大上传数量
        overwriteInitial: false,// 覆盖初始预览内容和标题设置
        showCancel: false,// 显示取消按钮
        showZoom: false,// 显示预览按钮
        showCaption: false,          // 显示文件文本框
        dropZoneEnabled: false,  // 是否可拖拽
        uploadLabel: "上传附件", // 上传按钮内容
        browseLabel: '选择附件',    // 浏览按钮内容
        showRemove: false,   // 显示移除按钮
        browseClass: "layui-btn",// 浏览按钮样式
        uploadClass: "layui-btn",// 上传按钮样式
        uploadExtraData: function () {
            return {'type': $("#pictureType").val(), 'sourceId': $("#sourceId1").val()};
        },   // 上传数据
        hideThumbnailContent: true,      // 是否隐藏文件内容
        fileActionSettings: {       // 在预览窗口中为新选择的文件缩略图设置文件操作的对象配置
            showRemove: true,           // 显示删除按钮
            showUpload: true,           // 显示上传按钮
            showDownload: false,    // 显示下载按钮
            showZoom: false,// 显示预览按钮
            showDrag: false,    // 显示拖拽
            removeIcon: '<i class="fa fa-trash"></i>', // 删除图标
            uploadIcon: '<i class="fa fa-upload"></i>', // 上传图标
            uploadRetryIcon: '<i class="fa fa-repeat"></i>' // 重试图标
        },

    });
    // 上传成功回调
    $("#files").on("filebatchuploadcomplete", function () {
        $('#table-data').bootstrapTable('refresh', {silent: true});
    });
    // 上传失败回调
    $('#files').on('fileerror', function (event, data, msg) {
        $('#table-data').bootstrapTable('refresh', {silent: true});
    });


    /**
     * 保持session定时器
     * 每10分钟请求后台，保持连接
     */
    window.setInterval(keepSession, 10 * 60 * 1000);

    function keepSession() {
        $.ajax({
            type: "post",
            url: contextPath + '/sourceManage/source/keepSession',
            success: function (data) {
                console.log(data);
            }
        });
    }
</script>