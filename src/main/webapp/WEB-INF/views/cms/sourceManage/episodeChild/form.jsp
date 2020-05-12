<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="tabbable">
    <ul id="myTabs" class="nav nav-tabs">
        <li class="active">
            <a id="episodeTab" href="#episodeInfo" data-toggle="tab"><i
                    class="green ace-icon fa fa-pencil-square-o bigger-120"></i>剧集基本信息</a>
        </li>
        <li class="">
        <li class=""><a href="#contentVideo" data-toggle="tab"><i class="orange ace-icon fa fa-film bigger-120"></i>视频资源</a>
        </li>
    </ul>
    <form id="formDlg" class="form-horizontal" role="form">
        <input type="hidden" id="id" name="id" value="${entity.id}"/>
        <input type="hidden" id="pId" name="pId" value="${entity.pId}"/>
        <input type="hidden" id="synType" name="synType" value="${entity.synType}"/>
        <input type="hidden" id="classify" name="classify" value="${3}"/>
        <input type="hidden" id="createDate" name="createDate"
               value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        <!-- 内容视频隐藏域 -->
        <input type="hidden" id="contentVideoHidden" name="contentVideoHidden"/>
        <div class="tab-content">
            <!-- 基本信息 -->
            <div id="episodeInfo" class="tab-pane fade in active">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <label class="col-lg-2 control-label">编码:</label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control" readonly="readonly" id="code" name="code"
                                       value="${entity.code}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">名称:</label><span class="text-danger">*</span>
                            <div class="col-lg-9">
                                <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">标题:</label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control" id="title" name="title"
                                       value="${entity.title}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">简介:</label>
                            <div class="col-lg-9">
                                <textarea class="form-control" id="info" name="info">${entity.info}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">时长:</label><span class="text-danger">*</span>
                            <div class="col-lg-9">
                                <input type="number" min="1" class="form-control" id="runTime" name="runTime"
                                       value="${entity.runTime}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">第X集:</label><span class="text-danger">*</span>
                            <div class="col-lg-9">
                                <input type="number" class="form-control" min="1" max="9999" id="sort" name="sort"
                                       value="${entity.sort}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">是否启用:</label><span class="text-danger">*</span>
                            <div class="col-lg-9">
                                <x:dictData dictCode="Menu_Status" var="dictStatus">
                                    <x:select defaultOption="请选择" hasDefault="true" var="status" items="${dictStatus}"
                                              id="enable" name="enable" className="form-control">
                                        <x:option value="${status.code }" text="${status.name }"
                                                  selected="${entity.enable eq status.code}"></x:option>
                                    </x:select>
                                </x:dictData>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 视频资源 -->
            <div id="contentVideo" class="tab-pane">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-lg-12">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary btn-white btn-sm"
                                            onclick="addVideoAsset();">
                                        添加视频
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-12" style="height:420px; overflow:auto">
                                <table class="table table-striped table-hover" style="border: solid thin #C0C0C0">
                                    <thead>
                                    <tr>
                                        <th width="12%">
                                            <div class="th-inner sortable both">视频名称</div>
                                        </th>
                                        <th width="12%">
                                            <div class="th-inner sortable both">平台来源</div>
                                        </th>
                                        <th width="12%">
                                            <div class="th-inner sortable both">类型</div>
                                        </th>
                                        <th width="12%">
                                            <div class="th-inner sortable both">清晰度</div>
                                        </th>
                                        <th width="12%">
                                            <div class="th-inner sortable both">格式</div>
                                        </th>
                                        <th width="12%">
                                            <div class="th-inner sortable both">大小</div>
                                        </th>
                                        <th width="12%">
                                            <div class="th-inner sortable both">时长/秒</div>
                                        </th>
                                        <th width="12%">
                                            <div class="th-inner sortable both">分辨率</div>
                                        </th>
                                        <th>
                                            <div class="th-inner sortable both">操作</div>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="resultBox_video">
                                    <c:if test="${type == 1 }"><!-- 新增 -->
                                    <tr id="noRowsTr_video">
                                        <td colspan="8" align="center">暂无数据</td>
                                    </tr>
                                    </c:if>
                                    <input type="hidden" id="videoSourceName" value="${videoSourceName }"/>
                                    <c:if test="${type == 2 }"><!-- 修改 -->
                                    <c:forEach items="${videoSources }" var="videoSource">
                                        <tr class="contentVideoRow" data-id="${videoSource.id }"
                                            data-videoId="${videoSource.id }">
                                            <td>${videoSource.name }</td>
                                            <td>${videoSource.platformName }</td>
                                            <td>${videoSource.typeName }</td>
                                            <td>${videoSource.definitionName }</td>
                                            <td>${videoSource.ext }</td>
                                            <td><fmt:formatNumber value="${videoSource.size*0.001024*0.001024 }" type="currency"
                                                                  pattern="#.00"/>MB
                                            </td>
                                            <td>${videoSource.time}秒</td>
                                            <td>${videoSource.resolution }</td>
                                            <td>
                                                <button type="button" class="btn btn-white btn-purple btn-sm"
                                                        onclick="deleteContentVideo(this,1,'${videoSource.sourceRelId }');">
                                                    删除
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr id="noRowsTr_video"
                                            <c:if test="${fn:length(videoSources) != 0}">
                                                hidden="true"
                                            </c:if>>
                                        <td colspan="8" align="center">暂无数据</td>
                                    </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    jQuery(function ($) {
        // 页面校验
        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "name": {required: true},
                "sort": {required: true},
                "runTime": {required: true},
//                 "year": {required: true},
//                 "contentClassify": {required: true},
//                 "sourceType": {required: true},
// 			"classifyTags" : { required : true },
// 			"yearTags" : { required : true },
// 			"areaTags" : { required : true },
// 			"actorTags" : { required : true },
// 			"directorTags" : { required : true },
                "enable": {required: true},
            },
            messages: {
                "name": '请输入名称',
                "sort": '请输入当前集数',
                "runTime": '请输入视频时长（秒）',
//                 "year": '请输入年份',
//                 "contentClassify": '请选择内容分类',
//                 "sourceType": '请选择片源类型',
// 			"classifyTags" : '请输入分类标签',
// 			"yearTags" : '请输入年代标签',
// 			"areaTags" : '请输入区域标签',
// 			"actorTags" : '请输入演员标签',
// 			"directorTags" : '请输入导演标签',
                "enable": '请选择启用状态',
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },

        });
        // 去除tag控件的多余样式
//         $(".tt-hint").hide();
//         $(".bootstrap-tagsinput").addClass("col-lg-12");

        $('#myTabs a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            if ($("#id").val() == "") {
                if (e.target.id != "episodeTab") {
                    CmMsg.warn("请先保存子集基本信息", -1);
                    $('#episodeTab').tab('show');
                }
            }
        })

    });


    /**
     * 打开视频选择页
     */
    function addVideoAsset() {
        showSelectDlg("选择视频", contextPath + '/sourceManage/video/videoSelect/1/' + encodeURIComponent($("#name").val()), ["Select_table-data"],
            selectVideoCallBack, BootstrapDialog.SIZE_WIDE);
    }

    function selectVideoCallBack(obj) {
        // 隐藏无数据提醒区域
        $("#noRowsTr_video").hide();
        var html = "";
        $.each(obj, function (i, o) {// 循环添加视频信息进入列表中
            var id = parseInt(10000000000000000000 * Math.random());
            html += '<tr class=\'contentVideoRow\' data-id="' + id + '" data-videoId="' + o.id + '">' +
                '<td>' + o.name + '</td>' +
                '<td>' + o.typeName + '</td>' +
                '<td>' + o.definitionName + '</td>' +
                '<td>' + o.ext + '</td>' +
                '<td>' + o.size / 1024 + 'MB</td>' +
                '<td>' + o.time / (1000 * 1000) + '秒</td>' +
                '<td>' + o.resolution + '</td>' +
                '<td>' +
                '<button type=\'button\' class=\'btn btn-white btn-purple btn-sm\' onclick=\'deleteContentVideo(this,0);\'>删除</button>&nbsp;' +
                '</td>' +
                '</tr>';
        });
        $("#noRowsTr_video").before(html);
    }

    /**
     * 删除内容视频关系
     */
    function deleteContentVideo(obj, type, id) {
        $(obj).parent().parent().remove();// 删除dom
        if ($("#resultBox_video").children().length == 2) {
            $("#noRowsTr_video").show();
        }
        if (type == 1) {//1:删除数据库数据；0：无需删除数据库数据
            //调用ajax方法删除内容视频关系
            $.ajax({
                url: contextPath + "/sourceManage/sourceRel/remove/" + id,
                type: "post",
                data: {},
                success: function (data) {
                    if (data.status == 'OK') {
                        CmMsg.success(data.message, -1);
                    } else if (data.status == 'ERROR') {
                        CmMsg.error(data.message, -1);
                    } else if (data.status == 'TIMEOUT') {
                        CmMsg.error(data.message, -1);
                    } else {
                        CmMsg.error("删除失败了", -1);
                    }
                }
            });
        }
    }
</script>