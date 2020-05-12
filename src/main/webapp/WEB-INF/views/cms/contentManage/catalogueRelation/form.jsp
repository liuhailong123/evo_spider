<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="aId" name="aId" value="${entity.AId}"/>
            <input type="hidden" id="bId" name="bId" value="${entity.BId}"/>
            <input type="hidden" id="type" name="type" value="2"/>
            <input type="hidden" id="createDate" name="createDate"
                   value="<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <h4 class="widget-title lighter smaller">基本信息</h4>
            <div class="form-group">
                <label class="col-lg-3 control-label">类型:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="Publish_Content_Type" var="publishContentType">
                        <x:select defaultOption="请选择" hasDefault="false" var="publishContentType"
                                  items="${publishContentType}" id="contentType" name="contentType"
                                  className="form-control">
                            <x:option value="${publishContentType.code }" text="${publishContentType.name }"
                                      selected="${entity.contentType eq publishContentType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">业务类型:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="Business_Type" var="businessType">
                        <x:select defaultOption="请选择" hasDefault="false" var="businessType" items="${businessType}"
                                  id="businessType" name="businessType" className="form-control">
                            <x:option value="${businessType.code }" text="${businessType.name }"
                                      selected="${entity.businessType eq businessType.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">名称:</label>
                <div class="col-lg-6">
                    <input type="text" class="form-control" id="name" name="name" value="${entity.name}"
                           readonly="readonly"/>
                </div>
                <div class="col-lg-2">
                    <input id="Select_btnSearch" name="Select_btnSearch" type="button" value="选择"
                           onclick="selectContent()" class="btn btn-primary btn-sm form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">定价:</label><span class="text-danger">*</span>
                <div class="col-lg-5">
                    <input type="number" min="0" class="form-control" id="price" name="price" value="${entity.price}" />
                </div>
                <div class="col-lg-3">
                    <span class="form-control">分</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">排序:</label>
                <div class="col-lg-8">
                    <input type="number" class="form-control" id="sort" name="sort" value="${entity.sort}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否发布:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="OFF_ON" var="offOn">
                        <x:select defaultOption="请选择" hasDefault="false" var="offOn" items="${offOn}" id="publish"
                                  name="publish" className="form-control">
                            <x:option value="${offOn.code }" text="${offOn.name }"
                                      selected="${entity.publish eq offOn.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">是否热门:</label><span class="text-danger">*</span>
                <div class="col-lg-8">
                    <x:dictData dictCode="OFF_ON" var="offOn">
                        <x:select defaultOption="请选择" hasDefault="false" var="offOn" items="${offOn}" id="isHot"
                                  name="isHot" className="form-control">
                            <x:option value="${offOn.code }" text="${offOn.name }"
                                      selected="${entity.isHot eq offOn.code}"></x:option>
                        </x:select>
                    </x:dictData>
                </div>
            </div>
            <div class="form-group" id="freeNumDiv" hidden="hidden">
                <label class="col-lg-3 control-label">前N集免费:</label>
                <div class="col-lg-8">
                    <input type="number" min="0" class="form-control" id="freeNum" name="freeNum" value="${entity.freeNum}"/>
                </div>
            </div>
            <%--<h4 class="widget-title lighter smaller">产品定价</h4>
            <div class="col-lg-12">
                <div class="form-group">
                    <label class="col-lg-3 control-label">
                        <input class="form-control" type="button" id="productPrice" value="关联产品"/>
                    </label>
                    <div class="col-lg-9">
                        <ul class="list-unstyled" id="priceList">
                            <c:forEach items="${products}" var="obj">
                                <li>
                                    <i class="ace-icon fa fa-caret-right blue"></i>
                                    <input type="hidden" name="productRelId" value="${obj.id}"/>
                                    <span>${obj.name} | 原价：${obj.originalPrice/100} 元 | 优惠价：${obj.currentPrice/100} 元</span>
                                    <i onclick="deletePrice('${obj.id}','${entity.id}',this)"
                                       class="ace-icon glyphicon glyphicon-remove red" style="cursor: pointer"></i>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <h4 class="widget-title lighter smaller">限时免费</h4>
            <div class="col-lg-12">
                <div class="form-group">
                    <label class="col-lg-3 control-label">
                        <input class="form-control" type="button" id="setLimitFree" value="设置限免"/>
                    </label>
                    <div class="col-lg-9">
                        <ul class="list-unstyled" id="freeList">
                            <c:if test="${limitFree != null}">
                                <li>
                                    <i class="ace-icon fa fa-caret-right blue"></i>
                                    <input type="hidden" name="limitFreeId" value="${limitFree.id}"/>
                                    <span>开始时间：${limitFree.beginFreeTime} | 结束时间：${limitFree.endFreeTime}</span>
                                    <i onclick="deleteLimitFree('${limitFree.id}',this)"
                                       class="ace-icon glyphicon glyphicon-remove red" style="cursor: pointer"></i>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>--%>
        </form>
    </div>
</div>
<script type="text/javascript">
    if ($("#id").val() != "") {
        var tempType = $("#contentType").val();
        $("#contentType").attr("disabled", "disabled");
        $("#formDlg").append("<input type='hidden' name='contentType' value='" + tempType + "'/>");
    }
    if($("#contentType").val() == 2){
        $("#freeNumDiv").show();
    }

    $("#contentType").change(function () {
        if($("#contentType").val() == 2){
            $("#freeNumDiv").show();
        }else{
            $("#freeNumDiv").hide();
        }
    });

    //选择内容
    function selectContent() {
        if ($("#contentType").val() == '') {
            CmMsg.error("请选择类型");
        } else {
            if ($("#contentType").val() == 5) {//图书
                showSelectDlg("选择图书", contextPath + '/sourceManage/book/select/', ["Select_table-data"],
                    selectContentCallBack, BootstrapDialog.SIZE_WIDE);
            } else if ($("#contentType").val() == 4) {//活动
                showSelectDlg("选择活动", contextPath + '/sourceManage/active/select/', ["Select_table-data"],
                    selectContentCallBack, BootstrapDialog.SIZE_WIDE);
            } else if ($("#contentType").val() == 3) {//直播
                showSelectDlg("选择直播", contextPath + '/contentManage/liveBroadcast/select', ["Select_table-data"],
                    selectContentCallBack, BootstrapDialog.SIZE_WIDE);
            } else if ($("#contentType").val() == 6) {//栏目
                showSelectDlg("选择栏目", contextPath + '/columnManage/column/select/', ["Select_table-data"],
                    selectContentCallBack, BootstrapDialog.SIZE_WIDE);
            } else if($("#contentType").val() == 7){//文章
                showSelectDlg("选择文章", contextPath + '/sourceManage/spiderContent/select/', ["Select_table-data"],
                    selectContentCallBack, BootstrapDialog.SIZE_WIDE);
            } else {//电影／剧集
                showSelectDlg("选择内容", contextPath + '/sourceManage/television/select/' + $("#contentType").val(), ["Select_table-data"],
                    selectContentCallBack, BootstrapDialog.SIZE_WIDE);
            }
        }
    }

    function selectContentCallBack(obj) {
        $("#bId").val(obj[0].id);
        console.log()
        $("#name").val(obj[0].name);
        if($("#name").val() == ''){
            $("#name").val(obj[0].title);
        }
    }

    jQuery(function ($) {
        $('#formDlg').validate({
            errorElement: 'span',
            errorClass: 'help-block',
            focusCleanup: false,
            focusInvalid: false,
            onsubmit: false,
            rules: {
                "contentType": {
                    required: true
                },
                "name": {
                    required: true
                },
                "publish": {
                    required: true
                }
            },
            messages: {
                "contentType": '请选择类型',
                "name": '请选择相应内容',
                "publish": '请选择发布状态'
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
        });


        $("#productPrice").on('click', function () {
            if ($("#id").val() != "") {
                showSelectDlg("选择栏目", contextPath + '/pay/product/select/2', ["Select_table-data"],
                    function (obj) {
                        for (var i = 0; i < obj.length; i++) {
                            var productId = obj[i].id;
                            var bizId = $("#id").val();
                            var html = "<li>" +
                                "<i class=\"ace-icon fa fa-caret-right blue\"></i>" +
                                "<input type=\"hidden\" name=\"productId\" value=\"" + productId + "\"/>" +
                                obj[i].name + " | 原价：" + obj[i].originalPrice + " 元 | 优惠价：" + obj[i].currentPrice + " 元" +
                                "<i onclick=\"deletePrice('" + productId + "','" + $("#id").val() + "',this)\" " +
                                "class=\"ace-icon glyphicon glyphicon-remove red\" style=\"cursor: pointer\"></i>" +
                                "</li>";
                            $.ajax({
                                url: contextPath + '/pay/productRel/add',
                                data: {
                                    "bizId": bizId,
                                    "productId": productId,
                                    "relType": 3
                                },
                                type: "post",
                                async: false,
                                success: function (data) {
                                    if (data.status == 'OK') {
                                        $("#priceList").append(html);
                                        CmMsg.info(data.message, -1);
                                    } else if (data.status == 'ERROR') {
                                        CmMsg.error(data.message, -1);
                                    } else if (data.status == 'TIMEOUT') {
                                        CmMsg.error(data.message, -1);
                                    } else {
                                        CmMsg.error("失败或其他错误", -1);
                                    }
                                }
                            });
                        }
                    }, BootstrapDialog.SIZE_WIDE);
            } else {
                CmMsg.warn("请先保存对象", -1)
            }
        })

        $("#setLimitFree").on('click', function () {
            if ($("#id").val() != "") {
                var type = 3;
                var columnId = $("#aId").val();
                var contentId = $("#bId").val();
                var contentType = $("#contentType").val();
                var ajaxAddUrl = contextPath + '/pay/limitFree/addFromSite/' + columnId + "/" + type
                    + "/" + contentId + "/" + contentType;
                showAddDlg1("新增", ajaxAddUrl, "formDlg2", function (obj) {
                    var html = "<li>" +
                        "<i class=\"ace-icon fa fa-caret-right blue\"></i>" +
                        "<input type=\"hidden\" name=\"limitFreeId\" value=\"" + obj.rows.id + "\"/>" +
                        "<span>开始时间：" + obj.rows.beginFreeTime + " | 结束时间：" + obj.rows.endFreeTime + "</span>" +
                        "<i onclick=\"deleteLimitFree('" + obj.rows.id + "',this)\" " +
                        "class=\"ace-icon glyphicon glyphicon-remove red\" style=\"cursor: pointer\"></i>" +
                        "</li>";
                    $("#freeList").append(html);
                    $('#table-data').bootstrapTable('selectPage', 1);
                }, false, BootstrapDialog.SIZE_WIDE);
            } else {
                CmMsg.warn("请先保存对象", -1)
            }
        });
    });


    /**
     * 删除定价
     * @param id
     * @param obj
     */
    function deletePrice(productId, id, obj) {
        $.ajax({
            url: contextPath + '/pay/productRel/remove',
            data: {
                "productId": productId,
                "bizId": id,
            },
            type: "post",
            success: function (data) {
                if (data.status == 'OK') {
                    CmMsg.success(data.message, -1);
                    $(obj).parent().remove();
                } else if (data.status == 'ERROR') {
                    CmMsg.error(data.message, -1);
                } else if (data.status == 'TIMEOUT') {
                    CmMsg.error(data.message, -1);
                } else {
                    CmMsg.error("失败或其他错误", -1);
                }

            }
        });
    }


    /**
     * 删除限免信息
     * @param limitFreeId
     * @param obj
     */
    function deleteLimitFree(limitFreeId, obj) {
        $.ajax({
            url: contextPath + '/pay/limitFree/remove/' + limitFreeId,
            data: {},
            type: "post",
            success: function (data) {
                if (data.status == 'OK') {
                    CmMsg.success(data.message, -1);
                    $(obj).parent().remove();
                } else if (data.status == 'ERROR') {
                    CmMsg.error(data.message, -1);
                } else if (data.status == 'TIMEOUT') {
                    CmMsg.error(data.message, -1);
                } else {
                    CmMsg.error("失败或其他错误", -1);
                }

            }
        });
    }

    function showAddDlg1(title, ajaxUrl, formId, callback, hasFile, size) {
        BootstrapDialog.show({
            type: BootstrapDialog.TYPE_PRIMARY,
            size: size,
            closable: true,
            draggable: true,
            closeByBackdrop: false,
            title: title,
            //加载远程页面
            message: $('<div></div>').load(ajaxUrl),
            buttons: [{
                id: 'btn-Cancel',
                icon: 'glyphicon glyphicon-remove',
                label: '关闭',
                cssClass: 'btn-default',
                autospin: false,
                action: function (dialogRef) {
                    dialogRef.close();
                }
            }, {
                id: 'btn-OK',
                icon: 'glyphicon glyphicon-check',
                label: '确认',
                cssClass: 'btn-primary',
                autospin: false,
                action: function (dialogRef) {
                    var $button = this;
                    $button.disable();
                    $button.toggleSpin(true);
                    if ($('#' + formId).valid()) {
                        if (hasFile) {
                            formFileProcess($('#' + formId), dialogRef, ajaxUrl, callback);
                        } else {
                            $.post(ajaxUrl, getParams(formId)).done(function (data) {
                                if (data.status == 'OK') {
                                    dialogRef.close();
                                    CmMsg.success(data.message, -1);
                                    if (callback) {
                                        callback(data);
                                    }
                                } else if (data.status == 'ERROR') {
                                    CmMsg.error(data.message, -1);
                                } else if (data.status == 'TIMEOUT') {
                                    CmMsg.error(data.message, -1);
                                } else {
                                    CmMsg.error("保存失败或其他错误", -1);
                                }
                                $button.toggleSpin(false);
                                $button.enable();
                            });
                        }
                    } else {
                        $button.toggleSpin(false);
                        $button.enable();
                    }
                }
            }]
        });
    }
</script>