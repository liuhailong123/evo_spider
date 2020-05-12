<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../include.inc.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>SP增值业务管理平台</title>

    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <!-- <link rel="stylesheet" href="${contextPath}/static/assets/css/font-awesome.css" />-->
    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css"/>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${contextPath}/static/assets/css/jquery-ui.custom.css">
    <link rel="stylesheet" href="${contextPath}/static/assets/css/jquery.datetimepicker.css">
    <link rel="stylesheet" href="${contextPath}/static/assets/css/jquery.gritter.css">
    <link rel="stylesheet" href="${contextPath}/static/assets/css/jstree/themes/default/style.css">
    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-dialog.css">
    <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-table.css">
    <link rel="stylesheet" href="${contextPath}/static/local/css/common.css">
    <!-- text fonts -->
    <link rel="stylesheet" href="${contextPath}/static/assets/css/ace-fonts.css"/>
    <link rel="stylesheet" href="${contextPath}/static/assets/css/font-awesome.css"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="${contextPath}/static/assets/css/ace.css" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <!-- 自定义字体 -->
    <link rel="stylesheet" href="${contextPath}/static/local/css/font-defined.css">
    <link rel="stylesheet" href="${contextPath}/static/local/css/indefined.css"/>


    <link href="${contextPath}/static/assets/css/bootstrap-editable.css" rel="stylesheet"/>
    <link href="${contextPath}/static/assets/css/ztree/metroStyle.css" rel="stylesheet"/>
    <link href="${contextPath}/static/assets/css/tags/bootstrap-tagsinput.css" rel="stylesheet"/>
    <link href="${contextPath}/static/assets/css/ztree/metroStyle.css" rel="stylesheet"/>

    <!-- 文件上传-->
    <link href="${contextPath}/static/assets/bootstrapFileInput/css/fileinput.css" rel="stylesheet"/>
    <link href="${contextPath}/static/assets/bootstrapFileInput/themes/explorer-fa/theme.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="all"
          rel="stylesheet" type="text/css"/>
    <!-- 图片分页列表 -->
    <link href="${contextPath}/static/assets/imgPageGrid/css/imggrid.css" rel="stylesheet"/>


    <link href="${contextPath}/static/assets/bootstrapFileInput/css/fileinput.css" rel="stylesheet"/>
    <link href="${contextPath}/static/assets/bootstrapFileInput/themes/explorer-fa/theme.css" rel="stylesheet"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${contextPath}/static/assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${contextPath}/static/assets/css/ace-ie.css"/>
    <![endif]-->


    <!-- inline styles related to this page -->
    <!-- ace settings handler -->
    <script src="${contextPath}/static/assets/js/ace-extra.js"></script>
    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
    <!--[if lte IE 8]>
    <script src="${contextPath}/static/assets/js/html5shiv.js"></script>
    <script src="${contextPath}/static/assets/js/respond.js"></script>
    <![endif]-->
    <script type="text/javascript">var contextPath = "${contextPath}";</script>
    <style>
        body {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif ! import;
        }
    </style>
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<!-- 顶部导航部分 -->
<div id="navbar" class="navbar navbar-default fixed nav-updata">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">侧边栏</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="main" class="navbar-brand">
                <small>

                    <img src="${contextPath}/static/assets/img/logo.png"/>
                </small>
            </a>

            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->
        </div>

        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${contextPath}/static/assets/avatars/user.png"
                             alt="Jason's Photo"/>
                        <span class="user-info">
									<small>欢迎,</small> ${login_user.realname}.
								</span>
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>
                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="javascript:modifyPassword();">
                                <i class="ace-icon fa fa-lock"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${contextPath}/logout">
                                <i class="ace-icon fa fa-power-off"></i>
                                注销
                            </a>
                        </li>
                    </ul>
                </li>
                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div><!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<!-- 下面部分 -->
<div class="main-container fixed" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <!-- #section:basics/sidebar -->
    <!-- 左侧导航部分 -->
    <div id="sidebar" class="sidebar fixed responsive sidebar-update" style="background-color:#525E7E;">
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
        </script>
        <ul class="nav nav-list bg-ul">
            <c:set var="menu" value="${menuModule }"></c:set>
            <li class="">
                <a href='<c:out value="${menu.url }" />'>

                    <i class='<c:out value="${menu.icon }" />'></i>

                    <span class="menu-text"> <c:out value="${menu.name }"/> </span>
                </a>
                <b class="arrow"></b>
            </li>
            <c:forEach var="firstMenu" items="${menu.children }">
                <li class="">
                    <a href='<c:out value="${firstMenu.url }" />' class="dropdown-toggle">
                        <i class='<c:out value="${firstMenu.icon }" />'></i>

                        <span class="menu-text">
								<c:out value="${firstMenu.name }"/>
							</span>
                        <b class="arrow fa fa-angle-down"></b>
                    </a>
                    <b class="arrow"></b>
                    <ul class="submenu">
                        <c:forEach var="secMenu" items="${firstMenu.children }">
                            <li class="">
                                <a href="#<c:out value='${secMenu.url eq "#" ? "" : secMenu.url }' />"
                                   data-url='<c:out value="${secMenu.url }" />'
                                   <c:if test="${secMenu.children.size() > 0 }">class="dropdown-toggle"</c:if>
                                >

                                    <i class='<c:out value="${secMenu.icon }" />'></i>
                                    <c:out value="${secMenu.name }"/>
                                    <c:if test="${secMenu.children.size() > 0 }">
                                        <b class="arrow fa fa-angle-down"></b>
                                    </c:if>
                                </a>
                                <b class="arrow"></b>
                                <ul class="submenu">
                                    <c:forEach var="threeMenu" items="${secMenu.children }">
                                        <li class="">
                                            <a href="#<c:out value='${threeMenu.url eq "#" ? "" : threeMenu.url }' />"
                                               data-url='<c:out value="${threeMenu.url }" />'
                                               <c:if test="${threeMenu.children.size() > 0 }">class="dropdown-toggle"</c:if>
                                            >
                                                <i class='<c:out value="${threeMenu.icon }" />'></i>
                                                <c:out value="${threeMenu.name }"/>
                                                <c:if test="${threeMenu.children.size() > 0 }">
                                                    <b class="arrow fa fa-angle-down"></b>
                                                </c:if>
                                            </a>
                                            <b class="arrow"></b>
                                            <ul class="submenu">
                                                <c:forEach var="fourMenu" items="${threeMenu.children }">
                                                    <li class="">
                                                        <a href="#<c:out value='${fourMenu.url eq "#" ? "" : fourMenu.url }' />"
                                                           data-url='<c:out value="${fourMenu.url }" />'
                                                           <c:if test="${fourMenu.children.size() > 0 }">class="dropdown-toggle"</c:if>
                                                        >
                                                            <i class='<c:out value="${fourMenu.icon }" />'></i>
                                                            <c:out value="${fourMenu.name }"/>
                                                            <c:if test="${fourMenu.children.size() > 0 }">
                                                                <b class="arrow fa fa-angle-down"></b>
                                                            </c:if>
                                                        </a>
                                                        <b class="arrow"></b>
                                                        <ul class="submenu" style="margin-left: 25px;">
                                                            <c:forEach var="fiveMenu" items="${fourMenu.children }">
                                                                <li class="">
                                                                    <a href="#<c:out value='${fiveMenu.url eq "#" ? "" : fiveMenu.url }' />"
                                                                       data-url='<c:out value="${fiveMenu.url }" />'
                                                                    >
                                                                        <i class='<c:out value="${fiveMenu.icon }" />'></i>
                                                                        <c:out value="${fiveMenu.name }"/>
                                                                    </a>
                                                                    <b class="arrow"></b>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul><!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>
        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <!-- 内容部分 -->
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <!-- 主页header 部分 -->
            <div class="breadcrumbs fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#page/manage/home">主页</a>
                    </li>
                </ul><!-- /.breadcrumb -->

                <!-- #section:basics/content.searchbox -->
                <!--
                <div class="nav-search" id="nav-search">
                    <form class="form-search">
                        <span class="input-icon">
                            <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                            <i class="ace-icon fa fa-search nav-search-icon"></i>
                        </span>
                    </form>
                </div>
                 -->
                <!-- /.nav-search -->

                <!-- /section:basics/content.searchbox -->
            </div>
            <!-- 内容部分 -->
            <!-- /section:basics/content.breadcrumbs -->
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="page-content-area" data-ajax-content=true></div>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
    <!-- footer部分 -->
    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">
						<span class="bigger-120" style="font-size:14px;">
							 北京未来媒体科技股份有限公司 &copy; 2016-2018
						</span>
            </div>
            <!-- /section:basics/footer -->
        </div>
    </div>
</div><!-- /.main-container -->

<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${contextPath}/static/assets/js/jquery.js'>" + "<" + "/script>");
</script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${contextPath}/static/assets/js/jquery1x.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${contextPath}/static/assets/js/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<script src="${contextPath}/static/assets/js/bootstrap.js"></script>
<!-- page specific plugin scripts -->
<script src="${contextPath}/static/assets/js/jquery-ui.custom.js"></script>
<script src="${contextPath}/static/assets/js/jquery.validate.js"></script>
<script src="${contextPath}/static/assets/js/jquery.gritter.js"></script>
<script src="${contextPath}/static/assets/js/jquery.datetimepicker.js"></script>
<script src="${contextPath}/static/assets/js/typeahead.bundle.js"></script>
<script src='${contextPath}/static/assets/js/jwplayer/jwplayer.js'></script>
<script src='${contextPath}/static/assets/js/jwplayer/jwplayer.html5.js'></script>
<!-- table plugins -->
<script src="${contextPath}/static/assets/js/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${contextPath}/static/assets/js/bootstrap-table/bootstrap-table-mobile.js'>" + "<" + "/script>");
</script>
<script src="${contextPath}/static/assets/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="${contextPath}/static/assets/js/bootstrap-table/bootstrap-table-resizable.js"></script>
<script src="${contextPath}/static/assets/js/bootstrap-dialog/bootstrap-dialog.js"></script>
<script src="${contextPath}/static/assets/js/jstree/jstree.js"></script>
<!-- ace scripts -->
<script src="${contextPath}/static/assets/js/ace/elements.scroller.js"></script>
<script src="${contextPath}/static/assets/js/ace/elements.colorpicker.js"></script>
<script src="${contextPath}/static/assets/js/ace/elements.fileinput.js"></script>
<script src="${contextPath}/static/assets/js/ace/elements.typeahead.js"></script>
<script src="${contextPath}/static/assets/js/ace/elements.wysiwyg.js"></script>
<script src="${contextPath}/static/assets/js/ace/elements.spinner.js"></script>
<script src="${contextPath}/static/assets/js/ace/elements.treeview.js"></script>
<script src="${contextPath}/static/assets/js/ace/elements.wizard.js"></script>
<script src="${contextPath}/static/assets/js/ace/elements.aside.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.ajax-content.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.touch-drag.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.sidebar.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.submenu-hover.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.widget-box.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.settings.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.settings-rtl.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.settings-skin.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.widget-on-reload.js"></script>
<script src="${contextPath}/static/assets/js/ace/ace.searchbox-autocomplete.js"></script>
<script src="${contextPath}/static/assets/js/bootstrap-wysiwyg.js"></script>
<script src="${contextPath}/static/assets/js/jquery.hotkeys.js"></script>
<!-- custom -->
<script src="${contextPath}/static/local/js/common/common.js"></script>
<script src="${contextPath}/static/assets/js/jquery.timers-1.2.js"></script>

<script src="${contextPath}/static/assets/js/bootstrap-editable.js"></script>

<script src="${contextPath}/static/assets/js/bootstrap-table-editable.js"></script>
<script src="${contextPath}/static/assets/js/tags/bootstrap-tagsinput.min.js"></script>
<script src="${contextPath}/static/assets/js/tags/typeahead.bundle.min.js"></script>
<!-- ztree js -->
<script src="${contextPath}/static/assets/js/ztree/jquery.ztree.all.js"></script>
<!-- copy js -->
<script src="${contextPath}/static/assets/js/clipboard.min.js"></script>
<!-- 腾讯云点播上传js -->
<script src="${contextPath}/static/assets/js/vodUpload/ugcUploader.js"></script>
<!-- 腾讯云对象上传js-->
<script src="${contextPath}/static/assets/js/cosUpload/cos-js-sdk-v4.js"></script>
<!-- 批量上传-->
<script src="${contextPath}/static/assets/js/fileUpload/fileUpload.js"></script>
<script src="${contextPath}/static/assets/js/fileUpload/iconfont.js"></script>
<!-- ztree js -->
<script src="${contextPath}/static/assets/js/ztree/jquery.ztree.all-3.5.js"></script>
<!-- 文件上传-->
<script src="${contextPath}/static/assets/bootstrapFileInput/js/plugins/sortable.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/js/fileinput.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/js/locales/fr.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/js/locales/es.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/themes/explorer-fa/theme.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/themes/fa/theme.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" type="text/javascript"></script>

<!-- 图片分页列表-->
<script src="${contextPath}/static/assets/imgPageGrid/js/imggrid.js"></script>


<script src="${contextPath}/static/assets/bootstrapFileInput/js/plugins/sortable.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/js/fileinput.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/js/locales/zh.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/themes/explorer-fa/theme.js"></script>
<script src="${contextPath}/static/assets/bootstrapFileInput/themes/fa/theme.js"></script>

<script type="text/javascript">
    function modifyPassword() {
        var ajaxModifyPassUrl = contextPath + '/manage/account/modifyPassword';
        showAddDlg("修改密码", ajaxModifyPassUrl, "frmPwd", function () {
        });
    }
</script>
</body>
</html>
