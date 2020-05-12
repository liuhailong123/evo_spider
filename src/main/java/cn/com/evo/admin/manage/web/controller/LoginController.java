package cn.com.evo.admin.manage.web.controller;

import com.frameworks.core.logger.LoggerType;
import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.shiro.IncorrectCaptchaException;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.utils.Exceptions;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.security.auth.login.AccountLockedException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    private static final String LOGIN_PAGE = "login";
    private static final String LOGIN_DIALOG = "ajaxLogin";

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return LOGIN_PAGE;
    }

    @RequestMapping(method = {RequestMethod.GET}, params = "ajax=true", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MsgResult loginDialog2AJAX(HttpServletResponse response) {
        return loginDialog(response);
    }

    @RunLogger(value = "会话超时，用户重新登陆", opType = LoggerType.LOGIN)
    @RequestMapping(method = {
            RequestMethod.GET}, headers = "X-Requested-With=XMLHttpRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MsgResult loginDialog(HttpServletResponse response) {
        response.setHeader("SESSION_STATUS", "TIMEOUT");
        MsgResult retMsg = new MsgResult();
        retMsg.pushTimeout("会话已超时，请重新登陆");
        return retMsg;
    }

    @RequestMapping(value = "/timeout", method = {RequestMethod.GET})
    public String timeout() {
        return LOGIN_DIALOG;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView fail(ServletRequest request) {
        ModelAndView mav = new ModelAndView(LOGIN_PAGE);
        String msg = parseException(request);
        mav.addObject("msg", msg);
        mav.addObject("username", "");
        return mav;
    }

    @RequestMapping(value = "/ajax/success", method = {
            RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MsgResult timeoutSuccess() {
        MsgResult msgRet = new MsgResult();
        msgRet.pushOk("登陆成功");
        return msgRet;
    }

    private String parseException(ServletRequest request) {
        String errorString = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        Class<?> error = null;
        try {
            if (errorString != null) {
                error = Class.forName(errorString);
            }
        } catch (ClassNotFoundException e) {
            logger.error(Exceptions.getStackTraceAsString(e));
        }

        String msg = "其他错误！";
        if (error != null) {
            if (error.equals(UnknownAccountException.class)) {
                msg = "未知帐号错误！";
            } else if (error.equals(IncorrectCredentialsException.class)) {
                msg = "密码错误！";
            } else if (error.equals(IncorrectCaptchaException.class)) {
                msg = "验证码错误！";
            } else if (error.equals(AuthenticationException.class)) {
                msg = "认证失败！";
            } else if (error.equals(AccountLockedException.class)) {
                msg = "账号被锁定！";
            }
        }
        return "登录失败，" + msg;
    }
}
