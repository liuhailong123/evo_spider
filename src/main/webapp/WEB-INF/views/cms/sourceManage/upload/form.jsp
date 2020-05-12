<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<style type="text/css">
    .tips-content {
        padding: 20px 0;
        text-align: center;
        width: 100%;
        font-size: 16px;
    }

    .resultBox {
        width: 100%;
        height: 100%;
        border: 01px solid #888;
        padding: 5px;
        overflow: auto;
        margin-bottom: 20px;
    }

    [act=cancel-upload] {
        text-decoration: none;
        cursor: pointer;
    }
</style>
<div class="row">
    <form id="formDlg" class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
        <input type="file" id="file" name="multFile" class="multFile" style="display: none;" multiple="multiple">
        <input id="contentFileRef" name="refs" type="text" hidden="hidden"/>
        <div class="col-lg-12">
            <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                <div class="widget-header widget-header-large no-padding">
                    <div class="widget-title smaller">
                        <div id="toolbar" class="btn-group btn-corner">
                            <button id="addVideoFiles" class="btn btn-xs btn-pink" type="button">
                                <span class="glyphicon glyphicon-plus"></span>&nbsp;添加视频文件
                            </button>
                            <button id="emptyDom" class="btn btn-xs btn-default" type="button">
                                <span class="glyphicon glyphicon-remove"></span>&nbsp;清空
                            </button>
                        </div>
                    </div>
                </div>
                <div class="widget-body">
                    <div class="widget-main no-padding">
                        <div class="row resultBox">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th width="30%">内容名称</th>
                                    <th width="30%">文件名称</th>
                                    <th width="10%">文件大小(MB)</th>
                                    <th width="7%">上传状态</th>
                                </tr>
                                </thead>
                                <tbody id="resultBox">
                                <tr id="noRowsTr" hidden="hidden">
                                    <td colspan="5">
                                        <p class="tips-content">请添加上传文件</p>
                                    </td>
                                </tr>
                                <c:forEach items="${entities}" var="entity">
                                    <tr class="content">
                                        <td name="videoname">${entity.name}</td>
                                        <td name="fileName">
                                            <input type="file" class="singleFile" name="singleFile">
                                        </td>
                                        <td name="videoSize"></td>
                                        <td name="videoresult"></td>
                                        <td name="videoId" hidden="hidden">${entity.id}</td>
                                    </tr>
                                </c:forEach>
                                <%--底部按钮栏--%>
                                <tr id="btnBarTr" hidden="hidden">
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    /**
     * 打开文件选择框
     */
    $('#addVideoFiles').on('click', function () {// 添加文件按钮事件
        $('#file').click();
        $('#emptyDom').click();
    });

    /**
     * 上传视频
     */
    $('.multFile').on('change', multUpload);
    $('.singleFile').on('change', singleUpload);

    function multUpload(e) {
        // 循环添加文件信息进入上传列表中
        $.each(this.files, function (i, o) {
            var videoFile = o;
            var name = videoFile.name.split(".")[0];
            $(".content").children("[name=videoname]").each(function (j) {
                if (name == $(this).text()) {
                    $($(this).nextAll("[name=fileName]")[0]).text(videoFile.name);
                    $($(this).nextAll("[name=videoSize]")[0]).text((videoFile.size / (1024 * 1024)).toFixed(5));
                    $($(this).nextAll("[name=videoresult]")[0]).text("未上传");

                    var temp = [];
                    // 创建内容与文件索引对应关系
                    var ref = {'contentId': $($(this).nextAll("[name=videoId]")[0]).text(), 'fileName': videoFile.name};

                    if ($("#contentFileRef").val() != "") {
                        temp = JSON.parse($("#contentFileRef").val());
                    }
                    temp.push(ref);
                    $("#contentFileRef").val(JSON.stringify(temp));
                    $($(this).nextAll("[name=videoId]")[0]).text();
                }
            })
        });
    }

    function singleUpload(e) {
        // 循环添加文件信息进入上传列表中_slugDefault: function_slugDefault: function
        var contentId = $($(this)[0]).parent().nextAll("[name=videoId]").text();
        $.each(this.files, function (i, o) {
            var videoFile = o;
            var name = videoFile.name.split(".")[0];
            console.log("文件名" + name);
            var temp = [];
            // 创建内容与文件索引对应关系
            var ref = {'contentId': contentId, 'fileName': videoFile.name};

            if ($("#contentFileRef").val() != "") {
                temp = JSON.parse($("#contentFileRef").val());
            }
            temp.push(ref);
            $("#contentFileRef").val(JSON.stringify(temp));
        });
    }

    /**
     *清空数据上传区域Dom
     */
    $('#emptyDom').click(function () {
        // 重置内容区域
        $(".content").children("[name=videoname]").each(function (i) {
            $($(this).nextAll("[name=fileName]")[0]).html("<input type=\"file\" class=\"singleFile\" name=\"singleFile\">");
            $($(this).nextAll("[name=videoSize]")[0]).text("");
            $($(this).nextAll("[name=videoresult]")[0]).text("");
            $("#contentFileRef").val("");
        })

        $('.singleFile').unbind("change");
        $('.singleFile').on('change', singleUpload);

        // 重置inputfile
        $("#file").val("");
    });

</script>