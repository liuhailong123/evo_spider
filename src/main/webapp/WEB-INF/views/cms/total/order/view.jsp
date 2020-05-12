<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../include.inc.jsp" %>
<div class="col-lg-12">
    <div class="widget-box transparent" id="divSearch" hidden="true">
        <div class="widget-header widget-header-large">
            <h4 class="widget-title">搜索</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <form name="frmSearch" id="frmSearch" target="_self" class="form-inline searchCondition">
                    <label> 用户卡号:&nbsp;
                        <input name="search_LIKE_userAccount.accountNo" class="form-control" placeholder="请输入用户卡号"
                               type="text">
                    </label>
                    <label> 订单号:&nbsp;
                        <input name="search_LIKE_orderNo" class="form-control" placeholder="请输入订单号" type="text">
                    </label>
                    <label> 三方订单号:&nbsp;
                        <input name="search_LIKE_thirdPartyOrderNo" class="form-control" placeholder="请输入三方订单号"
                               type="text">
                    </label>
                    <label> 产品名称:&nbsp;
                        <input name="search_LIKE_product.name" class="form-control" placeholder="请输入产品编码" type="text">
                    </label>
                    <label> 产品名称:&nbsp;
                        <input name="search_LIKE_product.name" class="form-control" placeholder="请输入产品编码" type="text">
                    </label>
                    <label> 支付状态:&nbsp;
                        <x:dictData dictCode="Order_Type" var="orderType">
                            <x:select defaultOption="请选择" hasDefault="true" var="orderType" items="${orderType}"
                                      id="" name="search_EQ_orderType" className="form-control">
                                <x:option value="${orderType.code }" text="${orderType.name }"
                                          selected=""></x:option>
                            </x:select>
                        </x:dictData>
                    </label>
                    <button id="btnSearch" type="button" class="btn btn-primary btn-sm form-control">
                        <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>
    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
        <div class="widget-header widget-header-large no-padding">
            <div class="widget-title smaller">
                <div id="toolbar" class="btn-group btn-corner">
                    <%--<so:hasPermission name="Total:Order:add">
                        <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Total:Order:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button"><span
                                class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>--%>
                    <so:hasPermission name="Total:Order:modify">
                        <button id="manualProcess" class="btn btn-xs btn-success" type="button"><span
                                class="glyphicon glyphicon-edit"></span>&nbsp;人工处理
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="Total:Order:search">
                        <button id="search" class="btn btn-xs btn-primary" type="button"><span
                                class="glyphicon glyphicon-search"></span>&nbsp;查询
                        </button>
                    </so:hasPermission>
                </div>
            </div>
            <div class="widget-toolbar">
                <a data-action="reload" class="green2 bigger-120" href="#"><i class="ace-icon fa fa-refresh"></i></a>
                <a data-action="fullscreen" class="blue2 bigger-120" href="#"><i class="ace-icon fa fa-arrows-alt"></i></a>
            </div>
        </div>
        <div class="widget-body">
            <div class="widget-main no-padding">
                <table id="table-data"
                       data-classes="table table-striped table-hover"
                       data-single-select="true"
                       data-pagination="true"
                       data-id-field="id"
                       data-unique-id="id"
                       data-sort-name="createDate"
                       data-sort-order="desc">
                </table>
            </div>
        </div>
    </div>
    <%--<div>
        请求：<textarea id="msg"></textarea>
        <button onclick="sendMessage();">提交</button>
    </div>
    <div>
        响应：<textarea id="result"></textarea>
    </div>--%>
</div>
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/total/order/list';
        var ajaxManualProcessUrl = contextPath + '/total/order/manualProcess';

        <x:dictData dictCode="OFF_ON" var="offOn">
        <x:jsonArray varName="offOn" keyName="code" valName="name" items="${offOn}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="Order_Type" var="orderType">
        <x:jsonArray varName="orderType" keyName="code" valName="name" items="${orderType}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="Manual_Process_Type" var="manualProcessType">
        <x:jsonArray varName="manualProcessType" keyName="code" valName="name" items="${manualProcessType}" ></x:jsonArray>
        </x:dictData>
        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'orderNo',
            sortable: false,
            title: "订单号"
        }, {
            field: 'thirdPartyOrderNo',
            sortable: false,
            title: "三方订单号"
        }, {
            field: 'payConfig.name',
            sortable: false,
            title: "支付方式"
        }, {
            field: 'userAccount.accountNo',
            sortable: false,
            title: "智能卡号"
        }, {
            field: 'product.name',
            sortable: false,
            title: "产品套餐"
        }, {
            field: 'payAbleMoney',
            sortable: false,
            title: "应付金额(分)"
        }, {
            field: 'payAmountMoney',
            sortable: false,
            title: "实付金额(分)"
        }, {
            field: 'orderType',
            sortable: false,
            title: "订单状态",
            formatter: function (value, row, index) {
                var show = value;
                $.each(orderType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'createDate',
            sortable: false,
            title: "下单时间"
        }, {
            field: 'overDate',
            sortable: false,
            title: "完成时间"
        }, {
            field: 'isManualHandling',
            sortable: false,
            title: "是否人工处理",
            formatter: function (value, row, index) {
                var show = value;
                $.each(offOn, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'manualProcessType',
            sortable: false,
            title: "人工处理类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(manualProcessType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'manualHandlingInfo',
            sortable: false,
            title: "人工处理意见"
        }, {
            field: 'app.name',
            sortable: false,
            title: "所属应用"
        },
        ];


        jQuery(function ($) {
            initTable('table-data', columns, ajaxListUrl, 'frmSearch');

            // 搜索域控制
            $('#search').on('click', function () {
                $('#divSearch').slideToggle("slow");
            });
            $('#frmSearch').find('#btnSearch').on('click', function () {
                $('#table-data').bootstrapTable('selectPage', 1);
            });

            $('#tbWidget').on('reload.ace.widget', function (ev) {
                $('#table-data').bootstrapTable('refresh', {silent: true});
            });

            $('#manualProcess').on('click', function () {
                showEdtDlg("人工处理", ajaxManualProcessUrl, "table-data", "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                })
            })

        });
    });
</script>
<%--

<!-- webSocket相关方法 -->
<script type="text/javascript">
    var webSocket;
    var webSocketUrl = "127.0.0.1:8999/cms_sp";
    // 首先判断当前浏览器是否 支持 WebSocket
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://" + webSocketUrl + "/websocket");
    } else if ('MozWebSocket' in window) {
        webSocket = new MozWebSocket("ws://" + webSocketUrl + "/websocket");
    } else {
        webSocket = new SockJS("ws://" + webSocketUrl + "/websocket");
    }

    // 打开连接时
    webSocket.onopen = function (event) {
        //发送消息
        var msg = {'type': '1', 'message': 'login'};
        webSocket.send(JSON.stringify(msg));
    };

    // 收到消息时
    webSocket.onmessage = function (event) {
        console.log(event.data);
        $("#result").val(event.data)
        /*var data = JSON.parse(event.data);
        if (data.count > 0) {
            CmMsg.info(data.msg, -1);
        }*/
        //发送消息
//        var msg = {'type': '2', 'message': 'recvOK'};
//        webSocket.send(JSON.stringify(msg));
    };

    webSocket.onerror = function (event) {
    };

    webSocket.onclose = function (event) {
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        webSocket.close();
        webSocket = null;
    }

    function sendMessage(){
        //发送消息
        var msg = {'type': '2', 'message': $("#msg").val()};
        webSocket.send(JSON.stringify(msg));
    }
</script>--%>
