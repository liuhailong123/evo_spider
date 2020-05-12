<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
        <input type="hidden" name="sourceId" id="sourceId1" value="${sourceId}"/>
        <div class="col-lg-12">
            <div class="form-group">
                <label class="col-lg-2 control-label">
                    <span class="text-danger fa fa-exclamation-circle tooltip-info"
                          data-rel="tooltip" data-placement="bottom"
                          title="选择MP4/TS时，直接选择视频文件即可；选择HLS时，需先将HLS文件夹压缩为ZIP文件，并输入文件夹名称后上传该压缩文件。">&nbsp;</span>
                    视频类型:
                </label>
                <div class="col-lg-9">
                    <select class="form-control" id="videoFileType" name="videoFileType">
                        <option value="1">MP4/TS</option>
                        <option value="2">HLS</option>
                    </select>
                </div>
            </div>
            <div class="form-group" id="dirNameDiv" hidden="hidden">
                <div>
                    <label class="col-lg-2 control-label">文件夹名称:</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="dirName" name="dirName" placeholder="HD1080_7、HD720_2 等形式"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">平台来源:</label><span class="text-danger">*</span>
                <div class="col-lg-9">
                    <x:dictData dictCode="PlatForm" var="platform">
                        <x:select defaultOption="请选择" hasDefault="false" var="platform"
                                  items="${platform}" id="platform" name="platform" className="form-control">
                            <x:option value="${platform.code }" text="${platform.name }"
                                      selected="${entity.platform eq platform.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">清晰度:</label><span class="text-danger">*</span>
                <div class="col-lg-9">
                    <x:dictData dictCode="videoDefinition" var="videodefinition">
                        <x:select var="videodefinition" items="${videodefinition}" id="definition" name="definition"
                                  className="form-control">
                            <x:option value="${videodefinition.code }" text="${videodefinition.name }"
                                      selected=""></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <div>
                    <label class="col-lg-2 control-label">视频文件:</label>
                    <div class="col-lg-9" id="fileDiv">
                        <input type="file" id="files" name="files" multiple/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $('[data-rel=tooltip]').tooltip();
    // 上传附件
    var task_file_uplad_url = contextPath + '/sourceManage/video/videoUpload';
    $("#files").fileinput({
        language: 'zh',// 多语言设置，需要引入local中相应的js，例如locales/zh.js
        theme: "explorer-fa",// 主题
        uploadUrl: task_file_uplad_url,// 上传地址
        minFileCount: 1,// 最小上传数量
        maxFileCount: 2000,// 最大上传数量
        minFileSize: 0,
        maxFileSize: 104857600,
        allowedFileExtensions:['ts', 'mp4', 'm3u8', 'AVI', 'MOV', 'QT', 'ASF', 'RM', 'NAVI', 'mpg', 'mpeg', 'hls'],
        overwriteInitial: false,// 覆盖初始预览内容和标题设置
        showCancel: false,// 显示取消按钮
        showZoom: false,// 显示预览按钮
        showCaption: false,          // 显示文件文本框
        dropZoneEnabled: false,  // 是否可拖拽
        uploadLabel: "开始上传", // 上传按钮内容
        browseLabel: '选择要上传的文件',    // 浏览按钮内容
        showRemove: false,   // 显示移除按钮
        browseClass: "layui-btn",// 浏览按钮样式
        uploadClass: "layui-btn",// 上传按钮样式
        uploadExtraData: function () {
            return {
                'definition': $("#definition").val(),
                'sourceId': $("#sourceId1").val(),
                'dirName': $("#dirName").val(),
                'platform': $("#platform").val(),
                'videoFileType': $("#videoFileType").val()
            };
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

    $("#videoFileType").on('change', function (e) {
        if ($(this).val() == 2) {
            $("#files").removeAttr("multiple");
            $("#dirNameDiv").show();
            $("#dirName").rules("add", "required");
        } else {
            $("#files").attr("multiple", "");
            $("#dirNameDiv").hide();
            $("#dirName").rules("remove", "required");
        }
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