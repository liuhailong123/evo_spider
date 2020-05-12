<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <form id="formDlg" class="form-horizontal" role="form" enctype="application/x-www-form-urlencoded">
            <input type="hidden" id="addOrEditType" value="${type}"/>
            <input type="hidden" id="contentSize" value="${contentSize}"/>
            <input type="hidden" id="thirdCode" name="thirdCode" value="${entity.thirdCode}"/>
            <input type="hidden" id="pId" name="parent.id" value="${columnPId}"/>
            <input type="hidden" id="level" name="level" value="${entity.level}"/>
            <h4 class="widget-title lighter smaller">基础信息</h4>
            <div class="col-lg-6">
                <div class="form-group">
                    <label class="col-lg-3 control-label">ID:</label>
                    <div class="col-lg-8">
                        <input type="text" readonly class="form-control" id="id" name="id"
                               value="${entity.id}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">类型:</label>
                    <div class="col-lg-8">
                        <x:select var="columnType" items="${columnTypes}" id="type" name="type"
                                  className="form-control">
                            <x:option value="${columnType.code }" text="${columnType.name }"
                                      selected="${entity.type eq columnType.code}">
                            </x:option>
                        </x:select>
                    </div>
                </div>
                <div class="form-group" hidden="true" id="templateCodeDiv">
                    <label class="col-lg-3 control-label">模板编码:</label>
                    <div class="col-lg-8">
                        <x:dictData dictCode="Column_Template_Code" var="oFFoN">
                            <x:select defaultOption="请选择" hasDefault="true" var="oFFoN"
                                      items="${oFFoN}" id="templateCode" name="templateCode" className="form-control">
                                <x:option value="${oFFoN.code }" text="${oFFoN.name }"
                                          selected="${entity.templateCode eq oFFoN.code}"></x:option>
                            </x:select>
                        </x:dictData>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">编码:</label>
                    <div class="col-lg-8">
                        <input type="text" readonly class="form-control" id="columnCode" name="columnCode"
                               value="${entity.columnCode}"/>
                    </div>
                </div>
                <div class="form-group" id="coverPictureDiv">
                    <label class="col-lg-3 control-label">封面图片:</label>
                    <div class="col-lg-4">
                        <input type="hidden" id="coverPictureId" name="coverPictureId" value="${coverPicture.id}"/>
                        <input type="text" class="form-control" id="coverPictureName"
                               value="${coverPicture.source.name}" readonly="readonly"/>
                    </div>
                    <div class="col-lg-2">
                        <input name="Select_btnSearch" type="button" value="选择" onclick="selectPictureCover()"
                               class="btn btn-primary btn-sm form-control">
                        </input>
                    </div>
                    <div class="col-lg-2">
                        <input name="Delete_btnSearch" type="button" value="删除" onclick="deletePictureCover()"
                               class="btn btn-primary btn-sm form-control">
                        </input>
                    </div>
                </div>
                <div class="form-group" id="bgPictureDiv">
                    <label class="col-lg-3 control-label">背景图片:</label>
                    <div class="col-lg-4">
                        <input type="hidden" id="bgPictureId" name="bgPictureId" value="${bgPicture.id}"/>
                        <input type="text" class="form-control" id="bgPictureName" value="${bgPicture.source.name}"
                               readonly="readonly"/>
                    </div>
                    <div class="col-lg-2">
                        <input name="Select_btnSearch" type="button" value="选择" onclick="selectPictureBg()"
                               class="btn btn-primary btn-sm form-control">
                        </input>
                    </div>
                    <div class="col-lg-2">
                        <input name="Delete_btnSearch" type="button" value="删除" onclick="deletePictureBg()"
                               class="btn btn-primary btn-sm form-control">
                        </input>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">名称:</label><span
                        class="text-danger">*</span>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="name" name="name" value="${entity.name}"/>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">

                <div class="form-group">
                    <label class="col-lg-3 control-label">标题:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="title" name="title" value="${entity.title}"/>
                    </div>
                </div>
                <div class="form-group" hidden="true" id="classifyTagsDiv">
                    <label class="col-lg-3 control-label">分类标签:</label>
                    <!-- <span class="text-danger">*</span> -->
                    <div class="col-lg-8">
                        <input type="text" id="columnClassifyTags" name="classifyTags"
                               value="${entity.classifyTags}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">排序:</label><span class="text-danger">*</span>
                    <div class="col-lg-8">
                        <input type="number" class="form-control" id="sort" name="sort" value="${entity.sort}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">是否推荐:</label><span class="text-danger">*</span>
                    <div class="col-lg-8">
                        <x:dictData dictCode="OFF_ON" var="oFFoN">
                            <x:select defaultOption="请选择" hasDefault="true" var="oFFoN"
                                      items="${oFFoN}" id="isRecommend" name="isRecommend" className="form-control">
                                <x:option value="${oFFoN.code }" text="${oFFoN.name }"
                                          selected="${entity.isRecommend eq oFFoN.code}"></x:option>
                            </x:select>
                        </x:dictData>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">是否启用:</label><span class="text-danger">*</span>
                    <div class="col-lg-8">
                        <x:dictData dictCode="Menu_Status" var="dictStatus">
                            <x:select defaultOption="请选择" hasDefault="true" var="status"
                                      items="${dictStatus}" id="enable" name="enable" className="form-control">
                                <x:option value="${status.code }" text="${status.name }"
                                          selected="${entity.enable eq status.code}"></x:option>
                            </x:select>
                        </x:dictData>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <form id="formDlgOther" class="form-horizontal" role="form">
        <div class="col-lg-12" id="auxiliaryDiv">
        </div>
    </form>
    <form name="frmSearch1" id="frmSearch1" target="_self">
        <input type="hidden" name="search_EQ_bizId" value="${entity.id}">
    </form>
    <form class="form-horizontal" role="form">
        <div class="col-lg-12">

            <h4 class="widget-title lighter smaller">产品定价</h4>

            <div class="col-lg-12">
                <div class="form-group">
                    <%--<label class="col-lg-2 control-label">--%>
                    <%--<input class="form-control" type="button" id="productPrice" value="关联产品"/>--%>
                    <%--</label>--%>
                    <div id="toolbar" class="btn-group btn-corner" style="margin-bottom: 10px">

                        <button type="button" id="add_product" class="btn btn-xs btn-success">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>

                        <button id="del_product" class="btn btn-xs btn-danger" type="button">
                            <span class="glyphicon glyphicon-trash"></span>&nbsp;删除

                        </button>
                    </div>
                    <div class="widget-main no-padding">
                        <table class="table  table-bordered table-hover" id="table-data-sheet"
                               data-classes="table table-striped table-hover"
                               data-pagination="true"
                               data-id-field="id"
                               data-unique-id="id"
                               data-sort-name="sort"
                               data-sort-name="isShow"
                               data-sort-order="asc">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <form class="form-horizontal" role="form">
        <div class="col-lg-12">
            <h4 class="widget-title lighter smaller">限时免费</h4>
            <div class="col-lg-12">
                <div class="form-group">
                    <label class="col-lg-2 control-label">
                        <input class="form-control" type="button" id="setLimitFree" value="设置限免"/>
                    </label>
                    <div class="col-lg-10">
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
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">

    /**
     * 背景图选择
     */
    function selectPictureBg() {
// 	showSelectDlg("选择背景图片", contextPath + '/sourceManage/picture/singlePictureSelect/2/?name=' + $("#name").val(),[ "Select_table-data" ], 
        showSelectDlg("选择背景图片", contextPath + '/sourceManage/picture/singlePictureSelect/2/?name=' + '', ["Select_table-data"],
            selectPictureBgCallBack, BootstrapDialog.SIZE_WIDE);
    }

    function selectPictureBgCallBack(obj) {
        $("#bgPictureName").val(obj[0].name);
        $("#bgPictureId").val(obj[0].id);
    }

    /**
     * 背景图删除
     */
    function deletePictureBg() {
        $("#bgPictureName").val("");
        $("#bgPictureId").val("");
    }


    /**
     * 封面图选择
     */
    function selectPictureCover() {
// 	showSelectDlg("选择封面图片", contextPath + '/sourceManage/picture/singlePictureSelect/2/?name=' + $("#name").val(),[ "Select_table-data" ], 
        showSelectDlg("选择封面图片", contextPath + '/sourceManage/picture/singlePictureSelect/2/?name=' + '', ["Select_table-data"],
            selectPictureCoverCallBack, BootstrapDialog.SIZE_WIDE);
    }

    function selectPictureCoverCallBack(obj) {
        $("#coverPictureName").val(obj[0].name);
        $("#coverPictureId").val(obj[0].id);
    }

    /**
     * 封面图删除
     */
    function deletePictureCover() {
        $("#coverPictureName").val("");
        $("#coverPictureId").val("");
    }

    jQuery(function ($) {
        // 初始化分类标签自动完成组件
        $('#columnClassifyTags').tagsinput();

        //页面初始化时判断目录类型 为栏目时，显示 分类标签div
        if ($("#type").val() == 3) {
            $("#classifyTagsDiv").show();
            $("#templateCodeDiv").show();
        }
        else if ($("#type").val() == 1) {
            $("#platFormDiv").show();
        }


        $('#formDlg').validate(
            {
                errorElement: 'span',
                errorClass: 'help-block',
                focusCleanup: false,
                focusInvalid: false,
                onsubmit: false,
                rules: {
                    "name": {required: true},
                    "enable": {required: true},
                    "sort": {required: true},
                    "isRecommend": {required: true},
                },
                messages: {
                    "name": '请输入名称',
                    "enable": '请选择启用状态',
                    "sort": '请输入排序',
                    "isRecommend": '请选择推荐状态',
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

        // 去除tag控件的多余样式
        $(".tt-hint").hide();
        $(".bootstrap-tagsinput").addClass("col-lg-12");


        //添加目录时，监听目录类型变更，动态加载栏目附属信息区域
        $("#type").change(function () {
            dynamicLoadAuxiliary($("#type").val());
            //如果目录类型为栏目则显示 分类标签 否则 隐藏
            if ($("#type").val() == 3) {
                $("#classifyTagsDiv").show();
                $("#templateCodeDiv").show();
            } else {
                $("#classifyTagsDiv").hide();
                $("#templateCodeDiv").hide();
                //隐藏的同时将分类标签 的值 置空
                $("#classifyTagsDiv").val("");
            }
        });

        // 如果是编辑页面自动显示附属信息区域
        if ($("#addOrEditType").val() == 2) {
            var tempType = $("#type").val();
            dynamicLoadAuxiliary(tempType);
            if ($("#contentSize").val() > 0) {//如果 当前栏目下 关联的内容 数大于0，不允许修改栏目类型
                $("#type").attr("disabled", true);
                $("#type").attr("name", "");
                $("#type").after("<input type=\"hidden\" name=\"type\" value=\"" + tempType + "\"/>");
            }
        }

        $("#add_product").on('click', function () {
            if ($("#id").val() != "") {
                var temp = 1;
                if ($("#level").val() == 1) {
                    temp = 2;
                } else {
                    temp = 1
                }
                showSelectDlg("选择产品定价", contextPath + '/pay/product/select/' + temp, ["Select_table-data"],
                    function (obj) {
                        for (var i = 0; i < obj.length; i++) {
                            var productId = obj[i].id;
                            var bizId = $("#id").val();
                            var level = $("#level").val();
                            var html = "<li>" +
                                "<i class=\"ace-icon fa fa-caret-right blue\"></i>" +
                                "<input type=\"hidden\" name=\"productId\" value=\"" + productId + "\"/>" +
                                obj[i].name + " | 原价：" + obj[i].originalPrice / 100 + " 元 | 优惠价：" + obj[i].currentPrice / 100 + " 元" +
                                "<i onclick=\"deletePrice('" + productId + "','" + $("#id").val() + "',this)\" " +
                                "class=\"ace-icon glyphicon glyphicon-remove red\" style=\"cursor: pointer\"></i>" +
                                "</li>";
                            $.ajax({
                                url: contextPath + '/pay/productRel/add',
                                data: {
                                    "bizId": bizId,
                                    "productId": productId,
                                    "level": level
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
                        $('#table-data-sheet').bootstrapTable('refresh', {silent: true});
                        refreshTree();
                    }, BootstrapDialog.SIZE_WIDE);
            } else {
                CmMsg.warn("请先保存对象", -1)
            }
        })

        /**
         * 删除产品定价
         **/
        $('#del_product').on('click', function () {
            doDelete(contextPath + '/pay/productRel/remove', 'table-data-sheet', function () {
                $('#table-data-sheet').bootstrapTable('refresh', {silent: true});
            });
        })
    });

    $("#setLimitFree").on('click', function () {
        if ($("#id").val() != "") {
            var type;
            if ($("#level").val() == 1) {
                type = 1
//                CmMsg.warn("暂不支持应用直接设置限免!如有需求，请针对栏目进行设置!!!", -1)
//                return "";
            } else {
                type = 2
            }
            var ajaxAddUrl = contextPath + '/pay/limitFree/addFromSite/' + $("#id").val() + "/" + type + "/0/0";
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

    /**
     * 动态加载栏目附属信息区域
     * @param columnType
     */
    function dynamicLoadAuxiliary(columnType) {
        var columnId = $("#id").val() == "" ? 0 : $("#id").val();
        if (columnType == 1) {//站点
            $("#auxiliaryDiv").load(contextPath + "/columnManage/column/openBlank");
        } else if (columnType == 2) {//应用
            $("#auxiliaryDiv").load(contextPath + "/columnManage/column/openBlank");
        } else if (columnType == 3) {//栏目
            $("#auxiliaryDiv").load(contextPath + "/columnManage/column/openBlank");
        } else if (columnType == 4) {//专题
            $("#auxiliaryDiv").load(contextPath + "/columnManage/column/openSection/" + columnId);
        } else if (columnType == 5) {//剧集
            $("#auxiliaryDiv").load(contextPath + "/columnManage/column/openEpisode/" + columnId);
        } else if (columnType == 6) {//瀑布流模版
            $("#auxiliaryDiv").load(contextPath + "/columnManage/column/openTemplate/" + columnId);
        } else {// 其他
            $("#auxiliaryDiv").load(contextPath + "/columnManage/column/openBlank");
        }
    }


    // 是否 显示 海报选择 div
    $('#type').on('change', function () {
        //判断是否选取type属性，无返回值；
        if ($(this).val()) {
            var selectValue = $(this).find('option:selected').val();
            if (selectValue == 1) {// 不显示
                $("#coverPictureDiv").hide();
                $("#bgPictureDiv").hide();

                // 增加校验规则
                $("#platform").rules("add", "required");
            } else {
                $("#coverPictureDiv").show();
                $("#bgPictureDiv").show();
            }
        }
    });
    //是否 显示 海报选择 div    
    //判断是否选取type属性，无返回值；
    if ($("#type").val()) {
        var selectValue = $("#type").find('option:selected').val();
        if (selectValue == 1) {// 不显示
            $("#coverPictureDiv").hide();
            $("#bgPictureDiv").hide();

            // 增加校验规则
            $("#platform").rules("add", "required");
        } else {
            $("#coverPictureDiv").show();
            $("#bgPictureDiv").show();
        }
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


    var ajaxProductList = contextPath + "/pay/productRel/list"
    var ajaxProductEdit = contextPath + "/pay/productRel/edit"

    function editSort(id) {
        console.log(id);
        console.log($("#form-field-mask-1_" + id).val());
        $.ajax({
            type: "post",
            url: ajaxProductEdit,
            data: {
                "id": id,
                "sort": $("#form-field-mask-1_" + id).val()
            },
            success: function (data) {
                CmMsg.success(data.message, -1);
                $("#table-data-sheet").bootstrapTable('refresh', {silent: true});
            }
        })
    }

    function editIsShow(id) {
        var isDisplay = 0;
        var state = $("#id-button-borders_" + id)[0].checked;
        if (state) {
            isDisplay = 1;
        }
        $.ajax({
            type: "post",
            url: ajaxProductEdit,
            data: {
                "id": id,
                "isShow": isDisplay
            },
            success: function (data) {
                CmMsg.success(data.message, -1);
            }
        })
    }

    var sheet = [{
        field: 'state',
        checkbox: true,
        title: "选择"
    }, {
        field: 'productName',
        sortable: false,
        title: "产品"
    }, {
        field: 'originalPrice',
        sortable: false,
        title: "原价"
    }, {
        field: 'currentPrice',
        sortable: false,
        title: "优惠价"
    }, {
        field: 'sort',
        sortable: false,
        title: "排序",
        formatter: function (value, row, index) {
            var domId = "'" + row.id + "'";
            var dom = '<div class="input-group" >' +
                '<input class="form-control " type="text" value="' + value + '"  id="form-field-mask-1_' + row.id + '">' +
                '<span class="input-group-btn">' +
                '<button class="btn btn-sm btn-default" type="button" onclick="editSort(' + domId + ')">' +
                '<i class="ace-icon fa fa-calendar bigger-110"></i>' +
                '修改' +
                '</button>' +
                '</div>';
            return dom;
        }
    }, {
        field: 'isShow',
        sortable: false,
        title: "是否展示",
        formatter: function (value, row, index) {
            var domId = "'" + row.id + "'";
            var isChecked = "";
            if (value == 1) {
                isChecked = "checked='checked'";
            }
            var dom = '<label class="inline">' +
                '<input id="id-button-borders_' + row.id + '" ' + isChecked + ' ' +
                'type="checkbox" onclick="editIsShow(' + domId + ')" class="ace ace-switch ace-switch-5">' +
                '<span class="lbl middle"></span>' +
                '</label>';
            return dom;
        }
    },
    ];

    jQuery(function ($) {
        initTable('table-data-sheet', sheet, ajaxProductList, 'frmSearch1');

    });
</script>