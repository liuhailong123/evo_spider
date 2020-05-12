<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="so" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <title>SP增值业务管理平台-登陆</title>
    <meta charset="utf-8">
    <meta name="keywords" content=" "/>
    <meta name="description" content=" "/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 移动设备 viewport -->
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,minimal-ui">
    <meta name="author" content="admui.com">
    <!-- 360浏览器默认使用Webkit内核 -->
    <meta name="renderer" content="webkit">
    <!-- 禁止百度SiteAPP转码 -->
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <!-- Chrome浏览器添加桌面快捷方式（安卓） -->
    <%--<link rel="icon" type="image/png" href="/public/images/favicon.png">--%>
    <meta name="mobile-web-app-capable" content="yes">
    <!-- Safari浏览器添加到主屏幕（IOS） -->
    <%--<link rel="icon" sizes="192x192" href="/public/images/apple-touch-icon.png">--%>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Admui">
    <!-- Win8标题栏及ICON图标 -->
    <meta name="msapplication-TileColor" content="#62a8ea">
    <%--<link rel="shortcut icon" href="${contextPath}/static/local/img/favicon.ico">--%>
    <link rel="stylesheet" href="${contextPath}/static/local/css/login.css">
</head>

<body>
<div class="login-body">
    <div class="login-wrapper" style="background-image: url(${contextPath}/static/local/images/login.jpg);">
        <div class="login-inner">
            <div class="lg-aside">
                <div class="lg-logo"></div>
                <div class="lg-aside-event">
                    <div class="lg-aside-event-tit">
                        <h2>Evomedia后台管理系统</h2>
                    </div>
                    <div class="lg-aside-event-det">
                        <p>
                            <i class="iconfont">&#xe615;</i>
                            <span>
                                  简单、奋斗、创新
                                </span>
                        </p>
                        <p>
                            <i class="iconfont">&#xe615;</i>
                            <span> 站在未来 连接媒体</span>
                        </p>
                        <p>
                            <i class="iconfont">&#xe615;</i>
                            <span>
                                  文化传媒领域、AI+VR前沿科技公司
                                </span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="lg-content">
                <div class="qc-pt-login-content" id="loginBox">
                    <div class="login-content">
                        <div class="login-tab">
                            <img src="${contextPath}/static/local/images/logo.png">
                        </div>
                        <form id="loginForm" method="post" action="${contextPath}/login">
                            <input type="hidden" name="subStr" id="subStr"/>
                            <c:if test="${msg!=null }">
                                <p style="color: red; margin-left: 10px;">${msg }</p>
                            </c:if>
                            <div class="form-group">
                                <i class="i1"></i>
                                <input type="text" class="form-control" placeholder="请输入用户名" id="uv"
                                       name="uv" autofocus="autofocus" value="${username}">
                            </div>
                            <div class="form-group">
                                <i class="i2"></i>
                                <input type="password" class="form-control" placeholder="请输入密码 " required="required"
                                       id="pv" name="pv" autofocus="autofocus" value="">
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="checkdiv">
                                        <i class="i3"></i>
                                        <input type="text" class="form-control" name="captcha_key"
                                               id="captcha_key"
                                               placeholder="请输入验证码" id="captcha_key"/>
                                    </div>
                                    <div class="Captcha">
                                        <img alt="点击刷新验证码" src="" id="captcha"/>
                                    </div>

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="check-box">
                                    <input type="checkbox" id="chkRememberPass" name="chkRememberPass">
                                    <span>记住密码</span>
                                </div>
                                <div class="forget" id="forget">
                                    <a href="#forgetPassword">忘记密码了？</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" onclick="login('loginForm')">登录</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="footer-inner">
                <p>Copyright © 2017 北京未来媒体科技股份有限公司</p>
                <span>未来媒体版权所有</span>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${contextPath}/static/assets/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/assets/js/bootstrap.js"></script>
<script type="text/javascript" src="${contextPath}/static/local/js/common/cookie.js"></script>
<script type="text/javascript" src="${contextPath}/static/assets/js/jquery.validate.js"></script>
<script type="text/javascript" src="${contextPath}/static/local/js/common/aes.js"></script>
<script type="text/javascript" src="${contextPath}/static/local/js/common/login.min.js"></script>
<script type="text/javascript">
    var captchaUrl = "${contextPath}/captcha";
    var loginUrl = "${contextPath}/login";
    jQuery(function ($) {
        var bodyH = $('body').height();
        $('.login-right').height(bodyH - 60);
        var formGroupH = $('.form-group').width();
        $('.checkdiv').width(formGroupH - 122);

        $('#loginForm').validate(
            {
                errorElement: 'div',
                errorClass: 'help-block',
                rules: {
                    "uv": {
                        required: true,
                        maxlength: 10,
                        minlength: 5
                    },
                    "pv": {
                        required: true,
                        maxlength: 32,
                        minlength: 6
                    },
                    "captcha_key": {
                        required: true,
                        maxlength: 4,
                        minlength: 4
                    }
                },
                messages: {
                    'uv': {
                        required: '请输入用户名',
                        maxlength: "用户名最多10个字母",
                        minlength: "用户名最少5个字母"
                    },
                    'pv': {
                        required: '请输入密码',
                        maxlength: "密码最多20个字母",
                        minlength: "密码最少6个字母"
                    },
                    'captcha_key': {
                        required: '请输入验证码',
                        maxlength: "验证码最多4个字母",
                        minlength: "验证码最少4个字母"
                    },
                },
                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-info')
                        .addClass('has-error');
                },
                success: function (e) {
                    $(e).closest('.form-group')
                        .removeClass('has-error'); //.addClass('has-info');
                    $(e).remove();
                },
            });

        // 请求验证码
        getCaptcha(captchaUrl, function (obj) {
            $("#captcha").attr("src", obj);
        });


        // 刷新验证码
        $("#captcha").click(
            function () {
                getCaptcha(captchaUrl, function (obj) {
                    $("#captcha").attr("src", obj);
                })
            }
        );

        $("#captcha_key").keydown(function (event) {
            if (event.keyCode == 13) {
                login('loginForm');
            }
        });
    })
</script>
</html>