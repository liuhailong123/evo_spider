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
                    <label> 产品名称:&nbsp;
                        <input name="search_LIKE_name" class="form-control" placeholder="请输入产品名称" type="text">
                    </label>
                    <label> 产品编码:&nbsp;
                        <input name="search_LIKE_code" class="form-control" placeholder="请输入产品编码" type="text">
                    </label>
                    <label> 第三方产品编码:&nbsp;
                        <input name="search_LIKE_thirdPartyCode" class="form-control" placeholder="请输入第三方产品编码"
                               type="text">
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
                    <so:hasPermission name="ProductCharge:Prod:Product:add">
                        <button id="add" class="btn btn-xs btn-pink" type="button"><span
                                class="glyphicon glyphicon-plus"></span>&nbsp;新增
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ProductCharge:Prod:Product:modify">
                        <button id="edt" class="btn btn-xs btn-success" type="button"><span
                                class="glyphicon glyphicon-edit"></span>&nbsp;编辑
                        </button>
                    </so:hasPermission><%--
                    <so:hasPermission name="ProductCharge:Prod:Product:modify">
                        <button id="calculate" class="btn btn-xs btn-warning" type="button"><span
                                class="glyphicon glyphicon-yen"></span>&nbsp;优惠价计算
                        </button>
                    </so:hasPermission>--%>
                    <so:hasPermission name="ProductCharge:Prod:Product:remove">
                        <button id="del" class="btn btn-xs btn-danger" type="button"><span
                                class="glyphicon glyphicon-remove"></span>&nbsp;删除
                        </button>
                    </so:hasPermission>
                    <so:hasPermission name="ProductCharge:Prod:Product:search">
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
                       data-single-select="false"
                       data-pagination="true"
                       data-id-field="id"
                       data-unique-id="id"
                       data-sort-name="code"
                       data-sort-order="asc">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12">&nbsp;</div>
<div class="col-lg-12">
    <div class="tabbable">
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#productInfoService" data-toggle="tab">产品服务</a></li>
            <li><a href="#productDiscount" data-toggle="tab">产品优惠</a></li>
            <li><a href="#productWelfare" data-toggle="tab">产品福利</a></li>
            <li><a href="#productPayType" data-toggle="tab">支付方式</a></li>
            <li><a href="#productPicture" data-toggle="tab">产品海报</a></li>
        </ul>
        <div class="tab-content">
            <div id="productInfoService" class="tab-pane in active">
                <form id="frmProductInfoService">
                    <input type="hidden" id="productId" name="search_EQ_product.id" value="0"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <so:hasPermission name="ProductCharge:Prod:Product:modify">
                                        <button id="edtProductInfoService" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ProductCharge:Prod:Product:add">
                                        <button id="addProductInfoService" class="btn btn-xs btn-success" type="button">
                                            <span class="glyphicon glyphicon-search"></span>&nbsp;查找
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ProductCharge:Prod:Product:Group:remove">
                                        <button id="delProductInfoService" class="btn btn-xs btn-danger" type="button">
                                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                        </button>
                                    </so:hasPermission>
                                </div>
                            </div>
                            <div class="widget-toolbar">
                                <a data-action="reload" class="green2 bigger-120" href="#"><i
                                        class="ace-icon fa fa-refresh"></i></a>
                                <a data-action="fullscreen" class="blue2 bigger-120" href="#">
                                    <i class="ace-icon fa fa-arrows-alt"></i></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table id="table-data-productInfoService"
                                       data-classes="table table-striped table-hover"
                                       data-pagination="true" data-id-field="id" data-unique-id="id"
                                       data-sort-name="createDate" data-sort-order="asc">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="productDiscount" class="tab-pane">
                <form id="frmProductDiscount">
                    <input type="hidden" id="productId" name="search_EQ_productId" value="0"/>
                    <input type="hidden" id="type" name="search_EQ_type" value="1"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <so:hasPermission name="ProductCharge:Prod:Product:modify">
                                        <button id="addProductDiscount" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ProductCharge:Prod:Product:Group:remove">
                                        <button id="delProductDiscount" class="btn btn-xs btn-danger" type="button">
                                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                        </button>
                                    </so:hasPermission>
                                </div>
                            </div>
                            <div class="widget-toolbar">
                                <a data-action="reload" class="green2 bigger-120" href="#"><i
                                        class="ace-icon fa fa-refresh"></i></a>
                                <a data-action="fullscreen" class="blue2 bigger-120" href="#">
                                    <i class="ace-icon fa fa-arrows-alt"></i></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table id="table-data-productDiscount"
                                       data-classes="table table-striped table-hover"
                                       data-pagination="true" data-id-field="id" data-unique-id="id"
                                       data-sort-name="createDate" data-sort-order="asc">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="productWelfare" class="tab-pane">
                <form id="frmProductWelfare">
                    <input type="hidden" id="productId" name="search_EQ_productId" value="0"/>
                    <input type="hidden" id="type" name="search_EQ_type" value="2"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <so:hasPermission name="ProductCharge:Prod:Product:modify">
                                        <button id="addProductWelfare" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ProductCharge:Prod:Product:Group:remove">
                                        <button id="delProductWelfare" class="btn btn-xs btn-danger" type="button">
                                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                        </button>
                                    </so:hasPermission>
                                </div>
                            </div>
                            <div class="widget-toolbar">
                                <a data-action="reload" class="green2 bigger-120" href="#"><i
                                        class="ace-icon fa fa-refresh"></i></a>
                                <a data-action="fullscreen" class="blue2 bigger-120" href="#">
                                    <i class="ace-icon fa fa-arrows-alt"></i></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table id="table-data-productWelfare"
                                       data-classes="table table-striped table-hover"
                                       data-pagination="true"
                                       data-id-field="id"
                                       data-unique-id="id"
                                       data-sort-name="createDate"
                                       data-sort-order="asc">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="productPayType" class="tab-pane">
                <form id="frmProductPayType">
                    <input type="hidden" id="productId" name="search_EQ_productId" value="0"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <so:hasPermission name="ProductCharge:Prod:Product:modify">
                                        <button id="addProductPayType" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ProductCharge:Prod:Product:Group:remove">
                                        <button id="delProductPayType" class="btn btn-xs btn-danger" type="button">
                                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                        </button>
                                    </so:hasPermission>
                                </div>
                            </div>
                            <div class="widget-toolbar">
                                <a data-action="reload" class="green2 bigger-120" href="#"><i
                                        class="ace-icon fa fa-refresh"></i></a>
                                <a data-action="fullscreen" class="blue2 bigger-120" href="#">
                                    <i class="ace-icon fa fa-arrows-alt"></i></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table id="table-data-productPayType"
                                       data-classes="table table-striped table-hover"
                                       data-pagination="true"
                                       data-id-field="id"
                                       data-unique-id="id"
                                       data-sort-name="createDate"
                                       data-sort-order="asc">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="productPicture" class="tab-pane">
                <form id="frmProductPicture">
                    <input type="hidden" id="contentId" name="search_EQ_fId" value="0"/>
                    <input type="hidden" id="sourcetype" name="search_EQ_sourcetype" value="2"/>
                </form>
                <div class="tab-pane fade in active">
                    <div id="tbWidget" class="widget-box transparent ui-sortable-handle">
                        <div class="widget-header widget-header-large no-padding">
                            <div class="widget-title smaller">
                                <div id="toolbar" class="btn-group btn-corner">
                                    <so:hasPermission name="ProductCharge:Prod:Product:modify">
                                        <button id="addProductPicture" class="btn btn-xs btn-pink" type="button">
                                            <span class="glyphicon glyphicon-plus"></span>&nbsp;新增
                                        </button>
                                    </so:hasPermission>
                                    <so:hasPermission name="ProductCharge:Prod:Product:Group:remove">
                                        <button id="delProductPicture" class="btn btn-xs btn-danger" type="button">
                                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除
                                        </button>
                                    </so:hasPermission>
                                </div>
                            </div>
                            <div class="widget-toolbar">
                                <a data-action="reload" class="green2 bigger-120" href="#"><i
                                        class="ace-icon fa fa-refresh"></i></a>
                                <a data-action="fullscreen" class="blue2 bigger-120" href="#">
                                    <i class="ace-icon fa fa-arrows-alt"></i></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table id="table-data-productPicture"
                                       data-classes="table table-striped table-hover"
                                       data-pagination="true"
                                       data-id-field="id"
                                       data-unique-id="id"
                                       data-sort-name="createDate"
                                       data-sort-order="asc">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var scripts = [];
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        var ajaxListUrl = contextPath + '/pay/product/list';
        var ajaxAddUrl = contextPath + '/pay/product/add';
        var ajaxEdtUrl = contextPath + '/pay/product/edit';
        var ajaxDelUrl = contextPath + '/pay/product/remove';

        <x:dictData dictCode="App_Type" var="appType">
        <x:jsonArray varName="appType" keyName="code" valName="name" items="${appType}" ></x:jsonArray>
        </x:dictData>
        <x:dictData dictCode="OFF_ON" var="offOn">
        <x:jsonArray varName="offOn" keyName="code" valName="name" items="${offOn}" ></x:jsonArray>
        </x:dictData>
        var columns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: false,
            title: "产品名称"
        }, {
            field: 'code',
            sortable: false,
            title: "产品编码"
        }, {
            field: 'thirdPartyCode',
            sortable: false,
            title: "第三方产品编码"
        }, {
            field: 'contentCode',
            sortable: false,
            title: "产品内容编码"
        }, {
            field: 'originalPrice',
            sortable: false,
            title: "原价(分)"
        }, {
            field: 'currentPrice',
            sortable: false,
            title: "优惠价(分)"
        }, {
            field: 'appId',
            sortable: false,
            title: "应用Id"
        }, {
            field: 'shortMssageInform',
            sortable: false,
            title: "是否短信通知",
            formatter: function (value, row, index) {
                var show = value;
                $.each(offOn, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }
        ];

        var ajaxListProductInfoServiceUrl = contextPath + '/pay/productServerRel/list';
        var ajaxAddProductInfoServiceUrl = contextPath + '/pay/productServerRel/add';
        var ajaxEdtProductInfoServiceUrl = contextPath + '/pay/productServerRel/edit';
        var ajaxDelProductInfoServiceUrl = contextPath + '/pay/productServerRel/remove';

        var productInfoServiceColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'serverRuleRel.name',
            sortable: false,
            title: "产品名称"
        }, {
            field: 'serverRuleRel.server.name',
            sortable: false,
            title: "服务名称"
        }, {
            field: 'serverRuleRel.rule.name',
            sortable: false,
            title: "规则名称"
        }
        ];

        var ajaxListProductDiscountUrl = contextPath + '/pay/productWelfareDiscountRel/list';
        var ajaxAddProductDiscountUrl = contextPath + '/pay/productWelfareDiscountRel/add';
        var ajaxDelProductDiscountUrl = contextPath + '/pay/productWelfareDiscountRel/remove';

        <x:dictData dictCode="IsMore" var="isMore">
        <x:jsonArray varName="isMore" keyName="code" valName="name" items="${isMore}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="PriceDiscountType" var="priceDiscountType">
        <x:jsonArray varName="priceDiscountType" keyName="code" valName="name" items="${priceDiscountType}" ></x:jsonArray>
        </x:dictData>

        var productDiscountColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: true,
            title: "价格优惠名称"
        }, {
            field: 'code',
            sortable: true,
            title: "价格优惠编码"
        }, {
            field: 'type',
            sortable: true,
            title: "类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(priceDiscountType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'isMore',
            sortable: true,
            title: "累积触发",
            formatter: function (value, row, index) {
                var show = value;
                $.each(isMore, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'targetValue',
            sortable: false,
            title: "目标值"
        }, {
            field: 'discountValue',
            sortable: false,
            title: "优惠值"
        }, {
            field: 'priority',
            sortable: true,
            title: "优先级"
        },
        ];


        <x:dictData dictCode="WelfareDiscountType" var="welfareDiscountType">
        <x:jsonArray varName="welfareDiscountType" keyName="code" valName="name" items="${welfareDiscountType}" ></x:jsonArray>
        </x:dictData>

        var productWelfareColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: true,
            title: "福利优惠名称"
        }, {
            field: 'code',
            sortable: true,
            title: "服务优惠编码"
        }, {
            field: 'type',
            sortable: true,
            title: "类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(welfareDiscountType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'goodsId',
            sortable: false,
            title: "商品id"
        },
        ];

        var ajaxListProductPayTypeUrl = contextPath + '/productCharge/payTypeRel/list';
        var ajaxAddProductPayTypeUrl = contextPath + '/productCharge/payTypeRel/add';
        var ajaxDelProductPayTypeUrl = contextPath + '/productCharge/payTypeRel/remove';

        <x:dictData dictCode="Pay_Type" var="payType">
        <x:jsonArray varName="payType" keyName="code" valName="name" items="${payType}" ></x:jsonArray>
        </x:dictData>
        var productPayTypeColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'config.name',
            sortable: false,
            title: "名称"
        }, {
            field: 'config.payType',
            sortable: false,
            title: "支付方式",
            formatter: function (value, row, index) {
                var show = value;
                $.each(payType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'config.remark',
            sortable: false,
            title: "备注"
        }, {
            field: 'config.provinceCode',
            sortable: false,
            title: "省网编码"
        },
        ];

        var ajaxListProductPictureUrl = contextPath + '/sourceManage/sourceRel/pictureList';
        var ajaxAddProductPictureUrl = contextPath + '/sourceManage/sourceRel/add';
        var ajaxDelProductPictureUrl = contextPath + '/sourceManage/sourceRel/remove';


        //输出字典js变量
        <x:dictData dictCode="Picture_Type" var="pictureType">
        <x:jsonArray varName="pictureType" keyName="code" valName="name" items="${pictureType}" ></x:jsonArray>
        </x:dictData>

        <x:dictData dictCode="Picture_Business_Type" var="pictureBusinessType">
        <x:jsonArray varName="pictureBusinessType" keyName="code" valName="name" items="${pictureBusinessType}" ></x:jsonArray>
        </x:dictData>

        var productPictureColumns = [{
            field: 'state',
            checkbox: true,
            title: "选择"
        }, {
            field: 'name',
            sortable: false,
            title: "名称"
        }, {
            field: 'ext',
            sortable: false,
            title: "格式"
        }, {
            field: 'resolution',
            sortable: false,
            title: "分辨率"
        }, {
            field: 'size',
            sortable: false,
            title: "大小",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    value = (value / 1024).toFixed(2) + "KB";
                }
                return value;
            }
        }, {
            field: 'type',
            sortable: false,
            title: "横/竖",
            formatter: function (value, row, index) {
                var show = value;
                $.each(pictureType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'businessType',
            sortable: false,
            title: "业务类型",
            formatter: function (value, row, index) {
                var show = value;
                $.each(pictureBusinessType, function () {
                    if (this.code == value) {
                        show = this.name;
                    }
                });
                return show;
            }
        }, {
            field: 'url',
            sortable: false,
            title: "访问地址",
            formatter: function (value, row, index) {
                if (value != null && value != "") {
                    return "<a style=\"cursor:pointer\" href=\"" + value + "\" target=\"_Blank\">在线预览</a>";
                }
            }
        }
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
            $('#add').on('click', function (e) {
                showAddDlg("新增", ajaxAddUrl, "formDlg", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                }, false);
            });

            $('#edt').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtUrl, "table-data", "formDlg", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});
                }, false);
            });

            $('#del').on('click', function (e) {
                doDelete(ajaxDelUrl, "table-data", function () {
                    $('#table-data').bootstrapTable('selectPage', 1);
                });
            });


            $('#table-data').on('load-success.bs.table', function () {
                var $table = $(this);
                var data = $table.bootstrapTable('getData');
                if (data && data.length > 0) {
                    $table.bootstrapTable('check', 0);

                    var rowId = data[0].id;
                    if (rowId == null || rowId == '') {
                        $('#frmProductInfoService').find('#productId').val(0);
                        $('#frmProductDiscount').find('#productId').val(0);
                        $('#frmProductWelfare').find('#productId').val(0);
                        $('#frmProductPayType').find('#productId').val(0);
                    } else {
                        $('#frmProductInfoService').find('#productId').val(data[0].id);
                        $('#frmProductDiscount').find('#productId').val(data[0].id);
                        $('#frmProductWelfare').find('#productId').val(data[0].id);
                        $('#frmProductPayType').find('#productId').val(data[0].id);
                    }


                    initTable('table-data-productInfoService', productInfoServiceColumns, ajaxListProductInfoServiceUrl, 'frmProductInfoService');
                    initTable('table-data-productDiscount', productDiscountColumns, ajaxListProductDiscountUrl, 'frmProductDiscount');
                    initTable('table-data-productWelfare', productWelfareColumns, ajaxListProductDiscountUrl, 'frmProductWelfare');
                    initTable('table-data-productPayType', productPayTypeColumns, ajaxListProductPayTypeUrl, 'frmProductPayType');
                    initTable('table-data-productPicture', productPictureColumns, ajaxListProductPictureUrl, 'frmProductPicture');
                } else {
                    initTable('table-data-productInfoService', productInfoServiceColumns, ajaxListProductInfoServiceUrl, 'frmProductInfoService');
                    initTable('table-data-productDiscount', productDiscountColumns, ajaxListProductDiscountUrl, 'frmProductDiscount');
                    initTable('table-data-productWelfare', productWelfareColumns, ajaxListProductDiscountUrl, 'frmProductWelfare');
                    initTable('table-data-productPayType', productPayTypeColumns, ajaxListProductPayTypeUrl, 'frmProductPayType');
                    initTable('table-data-productPicture', productPictureColumns, ajaxListProductPictureUrl, 'frmProductPicture');
                }
            });

            $('#table-data').on('check.bs.table', function (elem, row) {

                $('#frmProductInfoService').find('#productId').val(row.id);
                $('#table-data-productInfoService').bootstrapTable('refresh', {silent: true});//刷新表单

                $('#frmProductDiscount').find('#productId').val(row.id);
                $('#table-data-productDiscount').bootstrapTable('refresh', {silent: true});//刷新表单

                $('#frmProductWelfare').find('#productId').val(row.id);
                $('#table-data-productWelfare').bootstrapTable('refresh', {silent: true});//刷新表单

                $('#frmProductPayType').find('#productId').val(row.id);
                $('#table-data-productPayType').bootstrapTable('refresh', {silent: true});//刷新表单

                $('#frmProductPicture').find('#contentId').val(row.id);
                $('#table-data-productPicture').bootstrapTable('refresh', {silent: true});//刷新表单
            });


            //==============产品服务===============================================================================================
            $('#addProductInfoService').on('click', function (e) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                //判断是否选中了一行
                if (selections !== undefined && selections !== null) {
                    if (selections.length === 1) {
                        showSelectDlg("选择产品服务", contextPath + '/pay/serverRuleRel/select' + "", ["Select_table-data"],
                            selectProductServerCallBack, BootstrapDialog.SIZE_WIDE);
                    } else {
                        CmMsg.error("请选择一条数据", -1);
                    }
                } else {
                    CmMsg.error("没有选择产品套餐", -1);
                }
                $('#table-data-productInfoService').bootstrapTable('refresh', {silent: true});//刷新表单
            });

            function selectProductServerCallBack(obj) {
                var objStr = JSON.stringify(obj);
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                $.post(
                    ajaxAddProductInfoServiceUrl,
                    {objStr: objStr, productId: selections[0].id},
                    function (result) {
                        if (result.status == 'OK') {
                            CmMsg.success(result.message, -1);
                            $('#table-data-productInfoService').bootstrapTable('refresh', {silent: true});
                        } else {
                            CmMsg.error(result.message, -1);
                        }
                    }, "json");
            }

            $('#edtProductInfoService').on('click', function (e) {
                showEdtDlg("编辑", ajaxEdtProductInfoServiceUrl, "table-data", "formDlg", function () {
                    $('#table-data-productInfoService').bootstrapTable('refresh', {silent: true});
                }, false)
            });

            $('#delProductInfoService').on('click', function (e) {
                doDelete(ajaxDelProductInfoServiceUrl, "table-data-productInfoService", function () {
                    $('#table-data-productInfoService').bootstrapTable('refresh', {silent: true});
                });
            });

            //==============产品优惠===============================================================================================
            $('#addProductDiscount').on('click', function (e) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                //判断是否选中了一行
                if (selections !== undefined && selections !== null) {
                    if (selections.length === 1) {
                        showSelectDlg("选择产品优惠", contextPath + '/productCharge/discount/priceDiscount/select', ["Select_table-data"],
                            selectProductDiscountCallBack, BootstrapDialog.SIZE_WIDE);
                    } else {
                        CmMsg.error("请选择一条数据", -1);
                    }
                } else {
                    CmMsg.error("没有选择产品套餐", -1);
                }
            });

            function selectProductDiscountCallBack(obj) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                $.post(
                    ajaxAddProductDiscountUrl,
                    {
                        discountId: obj[0].id,
                        productId: selections[0].id,
                        type: 1
                    },
                    function (result) {
                        if (result.status == 'OK') {
                            CmMsg.success(result.message, -1);
                            $('#table-data-productDiscount').bootstrapTable('refresh', {silent: true});//刷新表单
                        } else {
                            CmMsg.error(result.message, -1);
                        }
                    }, "json");

            }

            $('#delProductDiscount').on('click', function (e) {
                doDelete(ajaxDelProductDiscountUrl, "table-data-productDiscount", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});//刷新表单
                });

            });
            //==============产品福利===============================================================================================
            $('#addProductWelfare').on('click', function (e) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                //判断是否选中了一行
                if (selections !== undefined && selections !== null) {
                    if (selections.length === 1) {
                        showSelectDlg("选择产品福利", contextPath + '/productCharge/discount/welfareDiscount/select', ["Select_table-data"],
                            selectProductWelfareCallBack, BootstrapDialog.SIZE_WIDE);
                    } else {
                        CmMsg.error("请选择一条数据", -1);
                    }
                } else {
                    CmMsg.error("没有选择产品套餐", -1);
                }
            });

            function selectProductWelfareCallBack(obj) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                $.post(
                    ajaxAddProductDiscountUrl,
                    {
                        discountId: obj[0].id,
                        productId: selections[0].id,
                        type: 2
                    },
                    function (result) {
                        if (result.status == 'OK') {
                            CmMsg.success(result.message, -1);
                            $('#table-data-productWelfare').bootstrapTable('refresh', {silent: true});//刷新表单
                        } else {
                            CmMsg.error(result.message, -1);
                        }
                    }, "json");

            }

            $('#delProductWelfare').on('click', function (e) {
                doDelete(ajaxDelProductDiscountUrl, "table-data-productWelfare", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});//刷新表单
                });
            });

            // ==============支付方式===============================================================================================
            $('#addProductPayType').on('click', function (e) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                //判断是否选中了一行
                if (selections !== undefined && selections !== null) {
                    if (selections.length === 1) {
                        showSelectDlg("选择支付方式", contextPath + '/productCharge/payConfig/select', ["Select_table-data"],
                            selectProductPayTypeCallBack, BootstrapDialog.SIZE_WIDE);
                    } else {
                        CmMsg.error("请选择一条数据", -1);
                    }
                } else {
                    CmMsg.error("没有选择产品套餐", -1);
                }
            });

            function selectProductPayTypeCallBack(obj) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                var productId = selections[0].id;
                for (var i = 0; i < obj.length; i++) {
                    var configId = obj[i].id;
                    $.post(
                        ajaxAddProductPayTypeUrl,
                        {
                            configId: configId,
                            productId: productId,
                        },
                        function (result) {
                            if (result.status == 'OK') {
                                CmMsg.success(result.message, -1);
                                $('#table-data-productPayType').bootstrapTable('refresh', {silent: true});//刷新表单
                            } else {
                                CmMsg.error(result.message, -1);
                            }
                        }, "json");
                }

            }

            $('#delProductPayType').on('click', function (e) {
                doDelete(ajaxDelProductPayTypeUrl, "table-data-productPayType", function () {
                    $('#table-data').bootstrapTable('refresh', {silent: true});//刷新表单
                });
            });


            // ==============产品海报===============================================================================================
            $('#addProductPicture').on('click', function (e) {
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                //判断是否选中了一行
                if (selections !== undefined && selections !== null) {
                    if (selections.length === 1) {
                        showSelectDlg("选择图片", contextPath + '/sourceManage/picture/pictureSelect/2?name=', ["Select_table-data"],
                            selectImageCallBack, BootstrapDialog.SIZE_WIDE);
                    } else {
                        CmMsg.error("请选择一条数据", -1);
                    }
                } else {
                    CmMsg.error("没有选择产品套餐", -1);
                }
            });

            function selectImageCallBack(obj) {
                var objStr = JSON.stringify(obj);
                var selections = $('#table-data').bootstrapTable("getSelections");//获取选中的行
                $.post(
                    ajaxAddProductPictureUrl,
                    {objStr: objStr, type: 2, contentId: selections[0].id},
                    function (result) {
                        if (result.status == 'OK') {
                            $('#table-data-productPicture').bootstrapTable('refresh', {silent: true});//刷新表单
                            CmMsg.success(result.message, -1);
                        } else {
                            CmMsg.error(result.message, -1);
                        }
                    }, "json");
            }

            $('#delProductPicture').on('click', function (e) {
                doDelete(ajaxDelProductPictureUrl, "table-data-productPicture", function () {
                    $('#table-data-productPicture').bootstrapTable('refresh', {silent: true});//刷新表单
                });
            });

            //==================================================================================================================

        });
    });


    function doAction(actionUrl, tableId, msg, callback) {
        var selections = $('#' + tableId).bootstrapTable("getSelections");
        if (selections !== undefined && selections !== null) {
            if (selections.length >= 1) {
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
            }
        } else {
            CmMsg.warn("没有选中任何数据记录,无法进行操作.", -1);
        }
    }

</script>