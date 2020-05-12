$.ajaxSetup({
    contentType: "application/x-www-form-urlencoded;charset=utf-8",
    cache: false,
    success: function (result, status, xhr) {
    },
    complete: function (xhr, status) {
        // 对返回的数据data做判断，
        // session过期的话，就弹出登陆对话框
        var session_status = xhr.getResponseHeader("SESSION_STATUS");
        if (session_status == 'TIMEOUT') {
            $.each(BootstrapDialog.dialogs, function (id, dialog) {
                dialog.close();
            });
            BootstrapDialog.show({
                type: BootstrapDialog.TYPE_DEFAULT,
                closable: false,
                draggable: false,
                title: "会话过期，重新登陆!",
                message: $('<div></div>').load(contextPath + "/login/timeout")
            });
        }
    },
    error: function (xhr, status, error) {
        switch (xhr.status) {
            case (500):
                CmMsg.error("服务器系统内部错误", -1);
                break;
            case (400):
                CmMsg.error("请求参数无法识别或转换!", -1);
                break;
            case (401):
                CmMsg.error("未登录", -1);
                break;
            case (403):
                CmMsg.error("您没有权限访问此资源或进行此操作", -1);
                break;
            case (405):
                CmMsg.error("请求的HttpMethod不存在", -1);
                break;
            case (406):
                CmMsg.error("缺失方法，服务器转换参数异常", -1);
                break;
            case (408):
                CmMsg.error("请求超时", -1);
                break;
            default:
                CmMsg.error(error, -1);
        }
    }
});

var ComMsg = function ($) {

    this.success = function (message, pos) {
        var position = this.getPos(pos);
        this.send("提示：", message, position, "gritter-success");
    };

    this.error = function (message, pos) {
        var position = this.getPos(pos);
        this.send("错误：", message, position, "gritter-error");
    };

    this.info = function (message, pos) {
        var position = this.getPos(pos);
        this.send("信息：", message, position, "gritter-info");
    };

    this.warn = function (message, pos) {
        var position = this.getPos(pos);
        this.send("警告：", message, position, "gritter-warning");
    };

    this.send = function (title, message, pos, type) {
        // $.gritter.removeAll();
        $.gritter.add({
            title: title,
            text: message,
            time: 1500,
            speed: 500,
            class_name: type + ' gritter-light ' + pos
        });
    };

    this.getPos = function (pos) {
        var position = "";
        switch (pos) {
            case 0:
                position = "gritter-center";
                break;
            default:
                position = "";
        }
        return position;
    }
}
var CmMsg = new ComMsg($);

function getParams(formId) {
    var params = {};
    var formParams = $('#' + formId).serializeArray();
    $.each(formParams, function (name, val) {
        if (val.value !== '') {
            if (params[val.name] !== undefined) {
                if (!params[val.name].push) {
                    params[val.name] = [params[val.name]];
                }
                params[val.name].push(val.value);
            } else {
                params[val.name] = val.value;
            }
        }
    });
    return params;
}

function initTree(elemId, ajaxUrl, isShowCheck, funs) {
    $("#" + elemId).jstree({
        'core': {
            "animation": 200,
            "force_text": true,
            "check_callback": true,
            "themes": {
                "responsive": true,
                "stripes": false
            },
            'data': {
                'url': function (node) {
                    return ajaxUrl;
                },
                'data': function (node, elem) {
                    if ($.isFunction(funs.query)) {
                        return funs.query(node, elem);
                    }
                    return {
                        id: node.id == '#' ? null : node.id
                    };
                },
                'type': 'POST'
            }
        },
        "plugins": isShowCheck ? ["checkbox"] : []
    }).on('loaded.jstree', function (elem, selection) {
        if ($.isFunction(funs.loaded)) {
            funs.loaded(elem, selection);
        }
    }).on('load_node.jstree', function (elem, selection) {
        if ($.isFunction(funs.loadNode)) {
            funs.loadNode(elem, selection);
        }
    }).on('select_node.jstree', function (elem, selection) {
        if ($.isFunction(funs.selNode)) {
            funs.selNode(selection.node, elem);
        }
    });
}

function initTable(tableId, columns, ajaxUrl, searchId) {
    $('#' + tableId).bootstrapTable({
        columns: columns,
        url: ajaxUrl,
        method: 'post',
        contentType: 'application/x-www-form-urlencoded',
        clickToSelect: true,
        sidePagination: 'server',
        queryParamsType: '',
        pageSize: 10,
        paginationFirstText: '首页',
        paginationPreText: '上页',
        paginationNextText: '下页',
        paginationLastText: '末页',
        queryParams: function (params) {
            var finalParams = {};
            if (searchId) {
                var paras = getParams(searchId);
                finalParams = $.extend({}, params, paras);
            } else {
                finalParams = params;
            }
            return finalParams;
        }
    });
    $('#' + tableId).bootstrapTable('resetView');
}

/*
 * 父子表初始化
 */
function initTable2(tableId, columns, ajaxUrl, ajaxChildrenUrl, searchId) {
    $('#' + tableId).bootstrapTable({
        columns: columns,
        url: ajaxUrl,
        method: 'post',
        detailView: true,//父子表
        contentType: 'application/x-www-form-urlencoded',
        clickToSelect: true,
        sidePagination: 'server',
        queryParamsType: '',
        pageSize: 10,
        paginationFirstText: '首页',
        paginationPreText: '上页',
        paginationNextText: '下页',
        paginationLastText: '末页',
        queryParams: function (params) {
            var finalParams = {};
            if (searchId) {
                var paras = getParams(searchId);
                finalParams = $.extend({}, params, paras);
            } else {
                finalParams = params;
            }
            return finalParams;
        },
        //注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
            InitSubTable(index, row, $detail);
        },
        onEditableSave: function (field, row, oldValue, $el) {
            console.log(111)
            /*  $.ajax({
                  type: "post",
                  url: "/Editable/Edit",
                  data: row,
                  dataType: 'JSON',
                  success: function (data, status) {
                      if (status == "success") {
                          alert('提交数据成功');
                      }
                  },
                  error: function () {
                      alert('编辑失败');
                  },
                  complete: function () {

                  }

              });*/
        }
    });

    //初始化子表格(无线循环)
    function InitSubTable(index, row, $detail) {
        var parentid = row.id;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            showHeader: false,
            singleSelect: true,
            url: ajaxChildrenUrl + "?parentId=" + parentid,
            method: 'get',
            clickToSelect: true,
            sidePagination: 'server',
            detailView: true,//父子表
            uniqueId: "id",
            columns: columns,
            //无线循环取子表，直到子表里面没有记录
            onExpandRow: function (index, row, $Subdetail) {
                InitSubTable(index, row, $Subdetail);
            }
        });
    };
    $('#' + tableId).bootstrapTable('resetView');
}

function showAddDlg(title, ajaxUrl, formId, callback, hasFile, size) {
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
                                    callback();
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

function showPreviewDlg(title, ajaxUrl, size) {
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
        }]
    });
}

function showSelectDlg1(title, ajaxUrl, selectTableIds, callBack, size) {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_DEFAULT,
        closable: false,
        size: size,
        draggable: true,
        title: title,
        message: $('<div></div>').load(ajaxUrl),
        buttons: [{
            id: 'btn-OK',
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
                dialogRef.close();
            }
        }]
    });
}

function showDeliveryDlg(title, ajaxUrl, tableId, formId, callback, hasFile, size) {
    var formUrl = ajaxUrl;
    //打开编辑的模态框
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        size: size,
        closable: true,
        draggable: true,
        closeByBackdrop: false,
        title: title,
        //加载远程页面,即想页面发送请求参数为当前选中行的id
        message: $('<div></div>').load(formUrl),
        //定义模态框中的按钮
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
            //点击确认按钮之后需要执行的函数
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
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else {
                                CmMsg.error("编辑失败或其他错误", -1);
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

/**
 * 选中弹框中List列表的数据提交到action
 * @param title
 * @param ajaxUrl
 * @param tableId
 * @param formId
 * @param callback
 * @param hasFile
 * @param size
 */
function showEdtDlg2(title, ajaxUrl, tableId, callback, takeDeliveryId, size) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");//获取选中的行号
    //判断是否选中了一行
    if (selections !== undefined && selections !== null) {
        //如果选中了一行,则拿到该行的id
        if (selections.length === 1) {
            var id = selections[0].id;
            //把选中的行号拼装到该请求的路径的后面(用来区分不同的请求)
            var formUrl = ajaxUrl + "/" + id;
            //打开编辑的模态框
            BootstrapDialog.show({
                type: BootstrapDialog.TYPE_PRIMARY,
                size: size,
                closable: true,
                draggable: true,
                closeByBackdrop: false,
                title: title,
                //加载远程页面,即想页面发送请求参数为当前选中行的id
                message: $('<div></div>').load(formUrl),
                //定义模态框中的按钮
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
                    //点击确认按钮之后需要执行的函数
                    action: function (dialogRef) {
                        var $button = this;
                        $button.disable();
                        $button.toggleSpin(true);
                        var takeDeliveryIdstr = $('#' + takeDeliveryId).bootstrapTable("getSelections");//获取选中的行号
                        var takeDeliveryIds = new Array();
                        for (var i = 0; i < takeDeliveryIdstr.length; i++) {
                            takeDeliveryIds.push(takeDeliveryIdstr[i].id);
                        }
                        console.log(takeDeliveryIds);
                        $.post(ajaxUrl + "/" + id + "/" + takeDeliveryIds).done(function (data) {
                            if (data.status == 'OK') {
                                dialogRef.close();
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else {
                                CmMsg.error("编辑失败或其他错误", -1);
                            }
                            $button.toggleSpin(false);
                            $button.enable();
                        });
                    }
                }]
            });
        } else {
            CmMsg.warn("请选择一行数据记录进行操作.", -1);
        }
    } else {
        CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
    }
}

function showEdtDlg(title, ajaxUrl, tableId, formId, callback, hasFile, size) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");//获取选中的行号
    //判断是否选中了一行
    if (selections !== undefined && selections !== null) {
        //如果选中了一行,则拿到该行的id
        if (selections.length === 1) {
            var id = selections[0].id;
            //把选中的行号拼装到该请求的路径的后面(用来区分不同的请求)
            var formUrl = ajaxUrl + "/" + id;
            //打开编辑的模态框
            BootstrapDialog.show({
                type: BootstrapDialog.TYPE_PRIMARY,
                size: size,
                closable: true,
                draggable: true,
                closeByBackdrop: false,
                title: title,
                //加载远程页面,即想页面发送请求参数为当前选中行的id
                message: $('<div></div>').load(formUrl),
                //定义模态框中的按钮
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
                    //点击确认按钮之后需要执行的函数
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
                                            callback();
                                        }
                                    } else if (data.status == 'ERROR') {
                                        CmMsg.error(data.message, -1);
                                    } else if (data.status == 'TIMEOUT') {
                                        CmMsg.error(data.message, -1);
                                    } else {
                                        CmMsg.error("编辑失败或其他错误", -1);
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
        } else {
            CmMsg.warn("请选择一行数据记录进行操作.", -1);
        }
    } else {
        CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
    }
}


/**
 * 打开选择list页面
 * @param title
 * @param ajaxUrl
 * @param selectTableIds select页面的tableId数组,如果有多个table需要进行选择
 * @param callBack 回调方法，回传选中的数组obj
 */
function showSelectDlg(title, ajaxUrl, selectTableIds, callBack, size) {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_DEFAULT,
        size: size,
        closable: false,
        draggable: true,
        title: title,
        message: $('<div></div>').load(ajaxUrl),
        buttons: [{
            id: 'btn-OK',
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
                var flag = false;
                var temp = "";
                $.each(selectTableIds, function (i, n) {
                    var selections = $('#' + n).bootstrapTable("getSelections");
                    if (selections !== undefined && selections !== null) {
                        if (selections.length != 0) {
                            flag = true;
                            temp = selections;
                        }
                    }
                });
                if (flag) {
                    if (callBack) {
                        callBack(temp);
                    }
                    dialogRef.close();

                } else {
                    CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
                }
            }
        }]
    });
}

function doDelete(delUrl, tableId, callback) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");
    if (selections !== undefined && selections !== null) {
        if (selections.length == 1) {
            var url = delUrl + "/" + selections[0].id;
            BootstrapDialog.confirm({
                title: '确认提示',
                type: BootstrapDialog.TYPE_WARNING,
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                message: "您确定要删除当前选中的数据记录吗?",
                callback: function (result) {
                    if (result) {
                        $.post(url).done(function (data) {
                            if (data.status == 'OK') {
                                $('#' + tableId).bootstrapTable('selectPage', 1);
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else {
                                CmMsg.error("删除失败了", -1);
                            }
                        });
                    }
                }
            });
        } else if (selections.length > 1) {
            var ids = new Array();
            for (var i = 0; i < selections.length; i++) {
                ids.push(selections[i].id);
            }
            var params = {'ids': ids};
            BootstrapDialog.confirm({
                title: '确认提示',
                type: BootstrapDialog.TYPE_WARNING,
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                message: "您确定要删除当前选中的数据记录吗?",
                callback: function (result) {
                    if (result) {
                        $.post(delUrl, params).done(function (data) {
                            if (data.status == 'OK') {
                                $('#' + tableId).bootstrapTable('selectPage', 1);
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else {
                                CmMsg.error("删除失败了", -1);
                            }
                        });
                    }
                }
            });
        } else {
            CmMsg.warn("请至少选择一行数据记录进行删除.", -1);
        }
    } else {
        CmMsg.warn("没有选中任何数据记录,无法进行删除操作.", -1);
    }
}

function doDeleteByTree(delUrl, tableId, callback) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");
    var url = delUrl;
    BootstrapDialog.confirm({
        title: '确认提示',
        type: BootstrapDialog.TYPE_WARNING,
        btnCancelLabel: '取消', // <-- Default value is 'Cancel',
        btnOKLabel: '确认', // <-- Default value is 'OK',
        message: "您确定要删除当前选中的数据记录吗?",
        callback: function (result) {
            if (result) {
                $.post(url).done(function (data) {
                    if (data.status == 'OK') {
                        $('#' + tableId).bootstrapTable('selectPage', 1);
                        CmMsg.success(data.message, -1);
                        if (callback) {
                            callback();
                        }
                    } else if (data.status == 'ERROR') {
                        CmMsg.error(data.message, -1);
                    } else if (data.status == 'TIMEOUT') {
                        CmMsg.error(data.message, -1);
                    } else {
                        CmMsg.error("删除失败了", -1);
                    }
                });
            }
        }
    });
}

function doDeleteAnddelRelation(delUrl, tableId, callback, delRelationUrl) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");
    if (selections !== undefined && selections !== null) {
        if (selections.length == 1) {
            var url = delUrl + "/" + selections[0].id;
            BootstrapDialog.confirm({
                title: '确认提示',
                type: BootstrapDialog.TYPE_WARNING,
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                message: "您确定要删除当前选中的数据记录吗?",
                callback: function (result) {
                    if (result) {
                        $.post(url).done(function (data) {
                            if (data.status == 'OK') {
                                $('#' + tableId).bootstrapTable('selectPage', 1);
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'CONFIRM') {//删除时有关联信息，是否确认删除
//				    			CmMsg.error(data.message, -1);
                                BootstrapDialog.confirm({
                                    title: '确认提示',
                                    type: BootstrapDialog.TYPE_WARNING,
                                    btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                                    btnOKLabel: '确认', // <-- Default value is 'OK',
                                    message: data.message,
                                    callback: function (result) {
                                        if (result) {//用户点击确认删除
                                            $.post(delRelationUrl + "/" + selections[0].id).done(function (data) {
                                                if (data.status == 'OK') {
                                                    $('#' + tableId).bootstrapTable('selectPage', 1);
                                                    CmMsg.success(data.message, -1);
                                                    if (callback) {
                                                        callback();
                                                    }
                                                } else if (data.status == 'ERROR') {
                                                    CmMsg.error(data.message, -1);
                                                } else if (data.status == 'TIMEOUT') {
                                                    CmMsg.error(data.message, -1);
                                                } else {
                                                    CmMsg.error("删除失败了", -1);
                                                }
                                            });
                                        }
                                    }
                                });
                            } else {
                                CmMsg.error("删除失败了", -1);
                            }
                        });
                    }
                }
            });
        } else if (selections.length > 1) {
            var ids = new Array();
            for (var i = 0; i < selections.length; i++) {
                ids.push(selections[i].id);
            }
            var params = {'ids': ids};
            BootstrapDialog.confirm({
                title: '确认提示',
                type: BootstrapDialog.TYPE_WARNING,
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                message: "您确定要删除当前选中的数据记录吗?",
                callback: function (result) {
                    if (result) {
                        $.post(delUrl, params).done(function (data) {
                            if (data.status == 'OK') {
                                $('#' + tableId).bootstrapTable('selectPage', 1);
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'CONFIRM') {//删除时有关联信息，是否确认删除
//				    			CmMsg.error(data.message, -1);
                                BootstrapDialog.confirm({
                                    title: '确认提示',
                                    type: BootstrapDialog.TYPE_WARNING,
                                    btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                                    btnOKLabel: '确认', // <-- Default value is 'OK',
                                    message: data.message,
                                    callback: function (result) {
                                        if (result) {//用户点击确认删除
                                            $.post(delRelationUrl, params).done(function (data) {
                                                if (data.status == 'OK') {
                                                    $('#' + tableId).bootstrapTable('selectPage', 1);
                                                    CmMsg.success(data.message, -1);
                                                    if (callback) {
                                                        callback();
                                                    }
                                                } else if (data.status == 'ERROR') {
                                                    CmMsg.error(data.message, -1);
                                                } else if (data.status == 'TIMEOUT') {
                                                    CmMsg.error(data.message, -1);
                                                } else {
                                                    CmMsg.error("删除失败了", -1);
                                                }
                                            });
                                        }
                                    }
                                });
                            } else {
                                CmMsg.error("删除失败了", -1);
                            }
                        });
                    }
                }
            });
        } else {
            CmMsg.warn("请至少选择一行数据记录进行删除.", -1);
        }
    } else {
        CmMsg.warn("没有选中任何数据记录,无法进行删除操作.", -1);
    }
}


/**
 * 选中table数据，访问后台action
 * @param actionUrl
 * @param tableId
 * @param msg 确认框提示信息
 * @param callback
 */
function doAction(actionUrl, tableId, msg, callback) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");
    if (selections !== undefined && selections !== null) {
        if (selections.length == 1) {
            var url = actionUrl + "/" + selections[0].id;
            BootstrapDialog.confirm({
                title: '确认提示',
                type: BootstrapDialog.TYPE_WARNING,
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                message: msg,
                callback: function (result) {
                    if (result) {
                        $.post(url).done(function (data) {
                            if (data.status == 'OK') {
                                $('#' + tableId).bootstrapTable('selectPage', 1);
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else {
                                CmMsg.error("操作失败了", -1);
                            }
                        });
                    }
                }
            });
        } else if (selections.length > 1) {
            var ids = new Array();
            for (var i = 0; i < selections.length; i++) {
                ids.push(selections[i].id);
            }
            var params = {'ids': ids};
            BootstrapDialog.confirm({
                title: '确认提示',
                type: BootstrapDialog.TYPE_WARNING,
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                message: msg,
                callback: function (result) {
                    if (result) {
                        $.post(actionUrl, params).done(function (data) {
                            if (data.status == 'OK') {
                                $('#' + tableId).bootstrapTable('selectPage', 1);
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else {
                                CmMsg.error("操作失败了", -1);
                            }
                        });
                    }
                }
            });
        } else {
            CmMsg.warn("请至少选择一行数据记录进行操作.", -1);
        }
    } else {
        CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
    }
}


function showComSelRowDlg(title, ajaxUrl, tableId, buttons, size) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");
    if (selections !== undefined && selections !== null) {
        if (selections.length === 1) {
            var id = selections[0].id;
            var formUrl = ajaxUrl + "/" + id;
            BootstrapDialog.show({
                type: BootstrapDialog.TYPE_PRIMARY,
                size: size,
                closable: true,
                draggable: true,
                closeByBackdrop: false,
                title: title,
                message: $('<div></div>').load(formUrl),
                buttons: buttons
            });
        } else {
            CmMsg.warn("请选择一行数据记录进行操作.", -1);
        }
    } else {
        CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
    }
}

/**
 * 初始化文件上传input
 * @param formId
 */
function initFileUpLoad(formId, initData) {
    var $form = $("#" + formId);
    var file_input = $form.find("input[type=file]");
    paramFileUpLoad(file_input, initData);
}

/**
 * 文件上传控件，基本属性配置
 * @param file_input
 */
function paramFileUpLoad(file_input, initData) {
    var upload_in_progress = false;
    file_input.ace_file_input({
        no_file: '没有文件...',
        btn_choose: "选择或拖拽上传",
        btn_change: "变更文件",
        droppable: true,
        thumbnail: "large",
        maxSize: initData.maxSize,// 允许的最大容量 bytes字节
        denyExt: initData.denyExt,//禁止的文件类型
        allowExt: initData.allowExt,//允许的文件类型 多个格式:["jpg","png","jpeg"]
        before_remove: function () {
            if (upload_in_progress) {
                return false;// if we are in the middle of uploading a file, don"t allow resetting file input
            }
            return true;
        },
        preview_error: function (filename, code) {
        }
    });
    file_input.on("file.error.ace", function (ev, info) {
        if (info.error_count["ext"] || info.error_count["mime"])
            $.gritter.add({
                title: "系统信息",
                text: initData.allowExtMsg,
                class_name: "gritter-warning gritter-center"
            });
        if (info.error_count["size"])
            $.gritter.add({
                title: "系统信息",
                text: initData.maxSizeMsg,//超过最大size的提示
                class_name: "gritter-warning gritter-center"
            });
    });
}

/**
 * 增加上传控件
 * @param formId
 * @param divId
 * @param fileInputName
 * @param flag 是否需要删除按钮
 */
function addFileUpload(formId, divId, fileInputName, flag, initData) {
    var divClass = "col-lg-13";
    if (flag == true) {
        divClass = "col-lg-11";
    }
    var html = "<div><div class='" + divClass + "' style='padding-left:0px;'><input type=\"file\" id=\"" + fileInputName + "\" name=\"" + fileInputName + "\" /></div>";
    if (flag == true) {
        html += "<div class=\"col-lg-1\"><span class=\"glyphicon glyphicon-remove\" style=\"cursor: pointer;\" onclick=\"doFileDomDel(this)\"></span></div>";
    }
    html += "</div>"
    $(html).appendTo("#" + divId);
    var file_input = $("#" + formId).find("input[type=file]");
    paramFileUpLoad(file_input, initData);
}

/**
 * 增加上传控件时，删除按钮的调用方法
 * @param obj
 */
function doFileDomDel(obj) {
    $(obj).parent().parent().remove();
}

/**
 * 保存文件流和表单元素的save方法
 * @param formId 表单formId
 * @param saveOrUpdateUrl 保存或更新url
 * @param dialogRef 模态层对象
 * @param successCallBack 成功回调方法
 */
function saveOrUpdateFileAndForm(formId, saveOrUpdateUrl, dialogRef, successCallBack) {
    var $form = $("#" + formId);
    var upload_in_progress = false;
    var file_input = $form.find("input[type=file]");
    var ie_timeout = "";

    var _url = saveOrUpdateUrl;

    if ("FormData" in window) {//浏览器兼容性
        formData_object = new FormData($("#" + formId)[0]);
        upload_in_progress = true;
        file_input.ace_file_input("loading", false);
        deferred = $.ajax({
            url: _url,
            type: $form.attr("method"),
            processData: false,// important
            contentType: false,// important
            data: formData_object,
            xhr: function () {
                var req = $.ajaxSettings.xhr();
                if (req && req.upload) {
                    req.upload.addEventListener("progress", function (e) {
                        if (e.lengthComputable) {
                            var done = e.loaded || e.position, total = e.total || e.totalSize;
                            var percent = parseInt((done / total) * 100) + "%";
                        }
                    }, false);
                }
                return req;
            },
            beforeSend: function () {
            },
            success: function () {
            }
        });
    } else {
        deferred = new $.Deferred; // create a custom deferred object
        var temporary_iframe_id = "temporary-iframe-" + (new Date()).getTime() + "-" + (parseInt(Math.random() * 1000));
        var temp_iframe = $(
            "<iframe id='" + temporary_iframe_id + "' name='" + temporary_iframe_id +
            "' frameborder='0' width='0' height='0' src='about:blank' style='position:absolute; z-index:-1; visibility: hidden;'></iframe>").insertAfter($form);
        $form.append("<input type='hidden' name='temporary-iframe-id' value='" + temporary_iframe_id + "' />");
        temp_iframe.data("deferrer", deferred);
        $form.attr({
            method: "POST",
            enctype: "multipart/form-data",
            action: _url,
            target: temporary_iframe_id
        });
        upload_in_progress = true;
        file_input.ace_file_input("loading", true);// display an overlay with loading icon

        $form.get(0).submit();
        ie_timeout = setTimeout(function () {
            ie_timeout = null;
            temp_iframe.attr("src", "about:blank").remove();
            deferred.reject({
                "status": "fail",
                "message": "Timeout!"
            });
        }, 30000);
    }
    deferred.done(function (result) {// success
        var res = result;//the `result` is formatted by your server side response and is arbitrary
        if (res.status == "OK") {//保存成功
            if (successCallBack) {
                successCallBack(res, dialogRef);//调用回调方法
            }
        } else if (res.status == 'ERROR') {
            CmMsg.error(res.message, -1);
        } else if (res.status == 'TIMEOUT') {
            CmMsg.error(res.message, -1);
        } else {
            CmMsg.error("操作失败了", -1);
        }
    }).fail(function (result) {// failure
        alert("上传时发生错误");
    }).always(function () {// called on both success and failure
        if (ie_timeout)
            clearTimeout(ie_timeout)
        ie_timeout = null;
        upload_in_progress = false;
        file_input.ace_file_input("loading", false);
    });
    deferred.promise();
}

/**
 * 表单存在文件上传处理
 * @param $form
 * @param dialogRef
 * @param submitUrl
 */
function formFileProcess($form, dialogRef, submitUrl, callback) {
    var ie_timeout = null;
    var deferred;
    if ("FormData" in window) {
        //for modern browsers that support FormData and uploading files via ajax
        //we can do >>> var formData_object = new FormData($form[0]);
        //but IE10 has a problem with that and throws an exception
        //and also browser adds and uploads all selected files, not the filtered ones.
        //and drag&dropped files won't be uploaded as well

        //so we change it to the following to upload only our filtered files
        //and to bypass IE10's error
        //and to include drag&dropped files as well
        var formData_object = new FormData();//create empty FormData object
        //serialize our form (which excludes file inputs)
        $.each($form.serializeArray(), function (i, item) {
            //add them one by one to our FormData
            formData_object.append(item.name, item.value);
        });
        //and then add files
        $form.find('input[type=file]').each(function () {
            var field_name = $(this).attr('name');
            //for fields with "multiple" file support, field name should be something like `myfile[]`

            var files = $(this).data('ace_input_files');
            if (files && files.length > 0) {
                for (var f = 0; f < files.length; f++) {
                    formData_object.append(field_name, files[f]);
                }
            }
        });
        //console.log(formData_object.get('avatarfile'));
        deferred = $.ajax({
            url: submitUrl,
            type: "POST",
            processData: false,//important
            contentType: false,//important
            dataType: 'json',
            data: formData_object
        });
    } else {
        //for older browsers that don't support FormData and uploading files via ajax
        //we use an iframe to upload the form(file) without leaving the page
        deferred = new $.Deferred; //create a custom deferred object
        var temporary_iframe_id = 'temporary-iframe-' + (new Date()).getTime() + '-' + (parseInt(Math.random() * 1000));
        var temp_iframe = $('<iframe id="' + temporary_iframe_id + '" name="' + temporary_iframe_id + '" \
								frameborder="0" width="0" height="0" src="about:blank"\
								style="position:absolute; z-index:-1; visibility: hidden;"></iframe>')
            .insertAfter($form)
        $form.append('<input type="hidden" name="temporary-iframe-id" value="' + temporary_iframe_id + '" />');
        temp_iframe.data('deferrer', deferred);
        //we save the deferred object to the iframe and in our server side response
        //we use "temporary-iframe-id" to access iframe and its deferred object
        $form.attr({
            method: 'POST',
            enctype: 'multipart/form-data',
            target: temporary_iframe_id //important
        });
        $form.get(0).submit();

        //if we don't receive a response after 30 seconds, let's declare it as failed!
        ie_timeout = setTimeout(function () {
            ie_timeout = null;
            temp_iframe.attr('src', 'about:blank').remove();
            deferred.reject({'status': 'fail', 'message': 'Timeout!'});
        }, 30000);
    }

    ////////////////////////////
    //deferred callbacks, triggered by both ajax and iframe solution
    deferred.done(function (data) {//success
        //format of `result` is optional and sent by server
        //in this example, it's an array of multiple results for multiple uploaded files
        if (data.status == 'OK') {
            CmMsg.success(data.message, -1);
            dialogRef.close();
            if (callback) {
                callback();
            }
        } else if (data.status == 'ERROR') {
            CmMsg.error(data.message, -1);
        } else if (data.status == 'TIMEOUT') {
            CmMsg.error(data.message, -1);
        } else {
            CmMsg.error("保存失败或其他错误", -1);
        }
        var $button = dialogRef.getButton('btn-OK');
        $button.toggleSpin(false);
        $button.enable();
    }).fail(function (result) {//failure
        CmMsg.error("服务器请求失败", -1);
    }).always(function () {//called on both success and failure
        if (ie_timeout) clearTimeout(ie_timeout)
        ie_timeout = null;
    });
    deferred.promise();
}

function doOperate(delUrl, tableId, callback) {
    var selections = $('#' + tableId).bootstrapTable("getSelections");
    if (selections !== undefined && selections !== null) {
        if (selections.length == 1) {
            var url = delUrl + "/" + selections[0].id;
            BootstrapDialog.confirm({
                title: '确认提示',
                type: BootstrapDialog.TYPE_WARNING,
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                message: "您确定要操作当前选中的数据记录吗?",
                callback: function (result) {
                    if (result) {
                        $.post(url).done(function (data) {
                            if (data.status == 'OK') {
                                $('#' + tableId).bootstrapTable('selectPage', 1);
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else {
                                CmMsg.error("操作失败了", -1);
                            }
                        });
                    }
                }
            });
        } else if (selections.length > 1) {
            var ids = new Array();
            for (var i = 0; i < selections.length; i++) {
                ids.push(selections[i].id);
            }
            var params = {'ids': ids};
            BootstrapDialog.confirm({
                title: '确认提示',
                type: BootstrapDialog.TYPE_WARNING,
                btnCancelLabel: '取消', // <-- Default value is 'Cancel',
                btnOKLabel: '确认', // <-- Default value is 'OK',
                message: "您确定要操作当前选中的数据记录吗?",
                callback: function (result) {
                    if (result) {
                        $.post(delUrl, params).done(function (data) {
                            if (data.status == 'OK') {
                                $('#' + tableId).bootstrapTable('selectPage', 1);
                                CmMsg.success(data.message, -1);
                                if (callback) {
                                    callback();
                                }
                            } else if (data.status == 'ERROR') {
                                CmMsg.error(data.message, -1);
                            } else if (data.status == 'TIMEOUT') {
                                CmMsg.error(data.message, -1);
                            } else {
                                CmMsg.error("操作失败了", -1);
                            }
                        });
                    }
                }
            });
        } else {
            CmMsg.warn("请至少选择一行数据记录进行删除.", -1);
        }
    } else {
        CmMsg.warn("没有选中任何数据记录,无法进行删除操作.", -1);
    }
}

/**
 * 复制内容方法
 * @param domId 需要复制的元素id
 */
function copy(domId) {
    var obj = document.getElementById(domId);
    obj.select(); // 选择对象
    document.execCommand("Copy"); // 执行浏览器复制命令
    CmMsg.success("已复制", -1);
}