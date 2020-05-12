<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="so" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div id="login-box" class="login-box visible widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <div class="space-6"></div>
            <form method="post" action="${contextPath}/login" id="loginForm">
                <input type="hidden" name="subStr" id="subStr"/>
                <fieldset>
                    <c:if test="${msg!=null }">
                        <p style="color: red; margin-left: 10px;">${msg }</p>
                    </c:if>
                    <div class="form-group">
                        <label class="block clearfix">
                            <span class="block input-icon input-icon-left">
                            <input
                                id="uv" name="uv" type="text" class="form-control"
                                placeholder="用户名" value="${username }" required/> <i
                                class="ace-icon fa fa-user"></i>
						</span>
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="block clearfix"> <span
                                class="block input-icon input-icon-left"> <input
                                id="pv" name="pv" type="password"
                                class="form-control" placeholder="密码" required/> <i
                                class="ace-icon fa fa-lock"></i>
						</span>
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="block clearfix"> <span
                                class="block input-icon input-icon-left"> <input
                                id="captcha_key" name="captcha_key" type="text" class="col-xs-8"
                                placeholder="验证码" required/> <i class="ace-icon fa fa-ticket"></i>
								<img alt="点击刷新验证码" src="" id="captcha"/>
						</span>
                        </label>
                    </div>
                    <div class="clearfix">
                        <label class="inline"> <input type="checkbox" class="ace"
                                                      id="rememberMe" name="rememberMe"/> <span class="lbl">记住我</span>
                        </label>
                        <button type="button" class="btn btn-default" onclick="login('loginForm')">登录</button>
                    </div>
                    <div class="space-4"></div>
                </fieldset>
            </form>
        </div>
        <!-- /.widget-main -->
    </div>
</div>
<script type="text/javascript" src="${contextPath}/static/local/js/common/aes.js"></script>
<script type="text/javascript" src="${contextPath}/static/local/js/common/login.min.js"></script>
<script type="text/javascript">
    jQuery(function ($) {
        $('#loginForm').validate(
            {
                errorElement: 'div',
                errorClass: 'help-block',
                messages: {
                    username: '请输入用户名',
                    password: '请输入密码',
//						captcha_key : '请输入验证码',
                },
                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-info')
                        .addClass('has-error');
                },
                success: function (e) {
                    $(e).closest('.form-group').removeClass('has-error');//.addClass('has-info');
                    $(e).remove();
                },
            });
        $(document).on('click', '.toolbar a[data-target]', function (e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });


        // 请求验证码
        var captchaUrl = "${contextPath}/captcha";
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
    });

</script>