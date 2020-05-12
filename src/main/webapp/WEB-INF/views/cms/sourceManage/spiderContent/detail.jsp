<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<!--UEditor-->
<script type="text/javascript" charset="utf-8" src="${contextPath}/ueditor/ueditor.config.js"></script>

<script type="text/javascript" charset="utf-8" src="${contextPath}/ueditor/ueditor.all.min.js"></script>

<script type="text/javascript" src="${contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<div class="tabbable">
    <ul id="myTabs" class="nav nav-tabs">
        <li class="active">
            <a id="episodeTab" href="#spiderContent" data-toggle="tab"><i
                    class="green ace-icon fa fa-pencil-square-o bigger-120"></i>文章</a>
        </li>
    </ul>
    <form id="formDlg" class="form-horizontal" role="form" >
        <input type="hidden" id="id" name="id" value="${entity.id}"/>
        <input type="hidden" id="createDate" name="createDate"
               value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
        <!-- 图片隐藏域 -->
        <input type="hidden" id="contentImageHidden" name="contentImageHidden"/>
        <div class="tab-content">
            <!-- 基本信息 -->
            <div id="spiderContent" class="tab-pane fade in active">
                <div class="row">
                    <div class="col-lg-6 col-md-3">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">标题:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="title" name="title"
                                       value="${entity.title}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">副标题:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="subtitle" name="subtitle" value="${entity.subtitle}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">作者:</label><span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="author" name="author"
                                       value="${entity.author}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">摘要:</label>
                            <div class="col-lg-8">
                                <textarea class="form-control" id="digest" name="digest">${entity.digest}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">封面:</label>
                            <div class="col-lg-8">
                                <textarea class="form-control" id="cover" name="cover">${entity.cover}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-3">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">分类标签:</label>
                            <span class="text-danger">*</span>
                            <div class="col-lg-8">
                                <input type="text" id="tags" name="tags"
                                       value="${entity.tags}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">来源:</label>
                            <!-- <span class="text-danger">*</span> -->
                            <div class="col-lg-8">
                                <input type="text" id="source" name="source" value="${entity.source}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">发布时间:</label>
                            <!-- <span class="text-danger">*</span> -->
                            <div class="col-lg-8">
                                <input type="text" id="dateTime" name="dateTime" value="${entity.dateTime}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">阅读量:</label>
                            <!-- <span class="text-danger">*</span> -->
                            <div class="col-lg-8">
                                <input type="text" id="readNum" name="readNum" value="${entity.readNum}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">点赞数:</label>
                            <!-- <span class="text-danger">*</span> -->
                            <div class="col-lg-8">
                                <input type="text" id="likeNum" name="likeNum"
                                       value="${entity.likeNum}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <script id="spInfo" name="spInfo" type="text/plain" style="height:500px;"  >${entity.spInfo}</script>
                    </div>
                </div>
                <button onclick="getContent()">获得内容</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('info')就能拿到相关的实例
    var ue = UE.getEditor('spInfo');

    function getContent() {
        var arr = [];
        arr.push(UE.getEditor('spInfo').getContent());
        console.log(arr.join("\n"));
        return '<input type="hidden" id="spInfo" name="spInfo" value="'+arr+'"/>';
    }
</script>
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
                "title": {required: true},
                "subtitle": {required: true},
                "author": {required: true},
                "tags": {required: true},
                "spInfo": {required: true},
            },
            messages: {
                "title": '请输入标题',
                "subtitle": '请输入副标题',
                "author": '请输入作者',
                "tags": '请输入分类标签',
                "spInfo": '内容为空',
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
                    CmMsg.warn("请先保存文章信息", -1);
                    $('#episodeTab').tab('show');
                }
            }
        })

    });

</script>