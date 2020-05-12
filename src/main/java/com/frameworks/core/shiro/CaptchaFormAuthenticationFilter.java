package com.frameworks.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.frameworks.utils.AESUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.util.WebUtils;

/**
 * 验证码过滤器
 * @author caoyong
 *
 */
public class CaptchaFormAuthenticationFilter extends BaseFormAuthenticationFilter {

	private String captchaParam = ShiroConsts.CAPTCHA_KEY;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		//提交参数
		String temp = WebUtils.getCleanParam(request, "subStr");
		String sign = AESUtils.aesDecrypt(new String(temp));
		String[] params = sign.split("#");

		// 解密密码
		String username = AESUtils.aesDecrypt(new String(params[0]));
		String password = AESUtils.aesDecrypt(new String(params[1]));
		String captcha = AESUtils.aesDecrypt(new String(params[2]));
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new CaptchaUsernamePasswordToken(username, password, rememberMe,
				host, captcha);
	}

}
