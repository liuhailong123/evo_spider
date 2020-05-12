<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="tabbable">
    <ul id="myTabs" class="nav nav-tabs">
        <li class="active">
            <a id="episodeTab" href="#episodeInfo" data-toggle="tab"><i
                    class="green ace-icon fa fa-pencil-square-o bigger-120"></i>剧集基本信息</a>
        </li>
        <li class="">
            <a href="#contentImage" data-toggle="tab"><i class="pink ace-icon fa fa-picture-o bigger-120"></i>图片资源</a>
        </li>
    </ul>
    <form id="formDlg" class="form-horizontal" role="form">
        <input type="hidden" id="id" name="id" value="${entity.id}"/>
        <input type="hidden" id="classify" name="classify" value="2"/>
        <input type="hidden" id="synType" name="synType" value="${entity.synType}"/>
        <input type="hidden" id="nameSpellLong" name="nameSpellLong" value="${entity.nameSpellLong}"/>
        <input type="hidden" id="nameSpellShort" name="nameSpellShort" value="${entity.nameSpellShort}"/>
        <input type="hidden" id="titleSpellLong" name="titleSpellLong" value="${entity.titleSpellLong}"/>
        <input type="hidden" id="titleSpellShort" name="titleSpellShort" value="${entity.titleSpellShort}"/>
        <input type="hidden" id="createDate" name="createDate"
               value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        <!-- 图片隐藏域 -->
        <input type="hidden" id="contentImageHidden" name="contentImageHidden"/>
        <div class="tab-content">
            <!-- 基本信息 -->
            <div id="episodeInfo" class="tab-pane fade in active">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">编码:</label>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" readonly="readonly" id="code" name="code"
                                       value="${entity.code}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">名称:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">评分:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="grade" name="grade"
                                       value="${entity.grade}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">标题:</label>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="title" name="title"
                                       value="${entity.title}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">年份:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="number" min="1900" max="3000" class="form-control" id="year" name="year"
                                       value="${entity.year}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">简介:</label>
                            <div class="col-lg-8">
                                <textarea class="form-control" id="info" name="info">${entity.info}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">时长:</label>
                            <div class="col-lg-8">
                                <input type="number" min="1" class="form-control" id="runTime" name="runTime" value="${entity.runTime}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">排序:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="number" class="form-control" min="1" max="9999" id="sort" name="sort"
                                       value="${entity.sort}"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">分类标签:</label>
                            <span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="text" id="classifyTags" name="classifyTags"
                                       value="${entity.classifyTags}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">年代标签:</label>
                            <!-- <span class="text-danger">*</span> -->
                            <div class="col-lg-8">
                                <input type="text" id="yearTags" name="yearTags" value="${entity.yearTags}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">区域标签:</label>
                            <!-- <span class="text-danger">*</span> -->
                            <div class="col-lg-8">
                                <input type="text" id="areaTags" name="areaTags" value="${entity.areaTags}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">演员标签:</label>
                            <!-- <span class="text-danger">*</span> -->
                            <div class="col-lg-8">
                                <input type="text" id="actorTags" name="actorTags" value="${entity.actorTags}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">导演标签:</label>
                            <!-- <span class="text-danger">*</span> -->
                            <div class="col-lg-8">
                                <input type="text" id="directorTags" name="directorTags"
                                       value="${entity.directorTags}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">总集数:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="number" class="form-control" min="1" max="9999" id="sumNum" name="sumNum"
                                       value="${entity.sumNum}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">语言:</label>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="language" name="language"
                                       value="${entity.language}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">是否启用:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
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
            <!-- 图片资源 -->
            <div id="contentImage" class="tab-pane">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <div class="col-lg-12">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary btn-white btn-sm"
                                            onclick="addImageAsset();">
                                        添加图片
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-12" style="height:420px; overflow:auto">
                                <table class="table table-striped table-hover" style="border: solid thin #C0C0C0">
                                    <thead>
                                    <tr>
                                        <th width="15%">
                                            <div class="th-inner sortable both">图片名称</div>
                                        </th>
                                        <th width="15%">
                                            <div class="th-inner sortable both">大小</div>
                                        </th>
                                        <th width="15%">
                                            <div class="th-inner sortable both">横/竖</div>
                                        </th>
                                        <th width="15%">
                                            <div class="th-inner sortable both">业务类型</div>
                                        </th>
                                        <th width="15%">
                                            <div class="th-inner sortable both">格式</div>
                                        </th>
                                        <th width="15%">
                                            <div class="th-inner sortable both">分辨率</div>
                                        </th>
                                        <th>
                                            <div class="th-inner sortable both">操作</div>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="resultBox_image">
                                    <c:if test="${type == 1 }"><!-- 新增 -->
                                    <tr id="noRowsTr_image">
                                        <td colspan="7" align="center">暂无数据</td>
                                    </tr>
                                    </c:if>
                                    <c:if test="${type == 2 }"><!-- 修改 -->
                                    <c:forEach items="${pictureSources }" var="pictureSource">
                                        <tr class="contentPictureRow" data-id="${pictureSource.random }"
                                            data-imageId="${pictureSource.id }">
                                            <td>${pictureSource.name }</td>
                                            <td><fmt:formatNumber type="number" value="${pictureSource.size/1024 }" pattern="0.00" maxFractionDigits="2"/> KB</td>
                                            <td>${pictureSource.typeName }</td>
                                            <td>
                                                <x:dictData dictCode="Picture_Business_Type" var="pictureBusinessType">
                                                    <x:select defaultOption="请选择" hasDefault="true"
                                                              onchange="changeBusinessType(\"${pictureSource.sourceRelId }\",this);"
                                                              id="${pictureSource.random }" var="pictureBusinessType"
                                                              name="businessType" items="${pictureBusinessType}"
                                                              className="form-control">
                                                        <x:option value="${pictureBusinessType.code }"
                                                                  text="${pictureBusinessType.name }"
                                                                  selected="${pictureSource.businessType eq pictureBusinessType.code}"></x:option>
                                                    </x:select>
                                                </x:dictData>
                                            </td>
                                            <td>${pictureSource.ext }</td>
                                            <td>${pictureSource.resolution }</td>
                                            <td>
                                                <button type="button" class="btn btn-white btn-purple btn-sm"
                                                        onclick="deleteContentPicture(this,1,'${pictureSource.sourceRelId }');">
                                                    删除
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr id="noRowsTr_image"
                                            <c:if test="${fn:length(pictureSources) != 0}">
                                                hidden="true"
                                            </c:if>>
                                        <td colspan="7" align="center">暂无数据</td>
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
        // 初始化分类标签自动完成组件
        $('#classifyTags').tagsinput();
        // 初始化年代标签自动完成组件
        $('#yearTags').tagsinput();
        // 初始化区域标签自动完成组件
        $('#areaTags').tagsinput();
        // 初始化演员标签自动完成组件
        $('#actorTags').tagsinput();
        // 初始化导演标签自动完成组件
        $('#directorTags').tagsinput();
        // 页面校验
        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "name": {required: true},
                "year": {required: true},
                "contentClassify": {required: true},
                "sourceType": {required: true},
                "sort": {required: true},
                "classifyTags": {required: true},
                "grade": {required: true},
// 			"yearTags" : { required : true },
// 			"areaTags" : { required : true },
// 			"actorTags" : { required : true },
// 			"directorTags" : { required : true },
                "enable": {required: true},
                "sumNum": {required: true},
            },
            messages: {
                "name": '请输入内容名称',
                "year": '请输入年份',
                "sort": '请输入排序',
                "grade": '请输入评分',
                "contentClassify": '请选择内容分类',
                "sourceType": '请选择片源类型',
                "classifyTags": '请输入分类标签',
// 			"yearTags" : '请输入年代标签',
// 			"areaTags" : '请输入区域标签',
// 			"actorTags" : '请输入演员标签',
// 			"directorTags" : '请输入导演标签',
                "enable": '请选择启用状态',
                "sumNum": '请选择启用状态',
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
        $(".tt-hint").hide();
        $(".bootstrap-tagsinput").addClass("col-lg-12");

        $('#myTabs a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            if ($("#id").val() == "") {
                if (e.target.id != "episodeTab") {
                    CmMsg.warn("请先保存剧集基本信息", -1);
                    $('#episodeTab').tab('show');
                }
            }
        })

    });


    /**
     * 打开图片选择页
     */
    function addImageAsset() {
        showSelectDlg("选择图片", contextPath + '/sourceManage/picture/pictureSelect/2?name=' + encodeURIComponent($("#name").val()), ["Select_table-data"],
            selectImageCallBack, BootstrapDialog.SIZE_WIDE);
    }


    function selectImageCallBack(obj) {
        // 隐藏无数据提醒区域
        $("#noRowsTr_image").hide();
        var html = "";
        $.each(obj, function (i, o) {// 循环添加图片信息进入列表中
            var id = parseInt(10000000000000000000 * Math.random());
            html += '<tr class=\'contentImageRow\' data-id="' + id + '" data-imageId="' + o.id + '">' +
                '<td>' + o.name + '</td>' +
                '<td>' + o.size / 1024 + 'KB</td>' +
                '<td>' + o.typeName + '</td>' +
                '<td>' +
                '<select id="' + id + '" class="form-control">' +
                '<option value ="1">封面</option>' +
                '<option value ="7">推荐</option>' +
                '<option value ="8">瀑布流</option>' +
                '<option value ="9">焦点</option>' +
                '</select>' +
                '</td>' +
                '<td>' + o.ext + '</td>' +
                '<td>' + o.resolution + '</td>' +
                '<td>' +
                '<button type=\'button\' class=\'btn btn-white btn-purple btn-sm\' onclick=\'deleteContentPicture(this,0);\'>删除</button>&nbsp;' +
                '</td>' +
                '</tr>';
        });
        $("#noRowsTr_image").before(html);
    }

    /**
     * 删除内容图片关系
     */
    function deleteContentPicture(obj, type, id) {
        $(obj).parent().parent().remove();// 删除dom
        if ($("#resultBox_image").children().length == 1) {
            $("#noRowsTr_image").show();
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

    function changeBusinessType(id, obj) {
        $.ajax({
            url: contextPath + "/sourceManage/sourceRel/changeBusinessType",
            type: "post",
            data: {
                sourceRelId: id,
                businessType: $(obj).val()
            },
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
</script>