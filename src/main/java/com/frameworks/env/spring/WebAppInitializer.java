package com.frameworks.env.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;

import com.frameworks.core.shiro.CaptchaServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
//		ServletRegistration.Dynamic captchaServlet = servletContext.addServlet("Captcha", CaptchaServlet.class);
//		captchaServlet.addMapping("/captcha");
	}

}
