package com.frameworks.core.logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.frameworks.utils.Exceptions;

import cn.com.evo.admin.manage.service.LogService;

@Aspect
public class LoggerAspect {

	private static final Logger logger = LogManager.getLogger(LoggerAspect.class);

	@Autowired
	private HttpServletRequest request;

	@Resource(type=LogService.class)
	private LogService logService;

	/**
	 * 切入点：表示在哪个类的哪个方法进行切入。配置有切入点表达式 execution (*
	 * cn.com.evo..web.controller.*.*(..))
	 */
	@Pointcut("(execution(* cn.com.evo..web.controller.*.*(..))) && @annotation(com.frameworks.core.logger.annotation.RunLogger)")
	public void logExpression() {
	}

	@AfterReturning(pointcut = "logExpression()", returning = "retVal")
	public void afterReturning(JoinPoint jp, Object retVal) {
		LoggerRunnable logRun = new LoggerRunnable();
		logRun.putParams(request, logService, jp, retVal, null);
		logRun.run();
		logger.debug("系统日志写入完成");
	}

	@AfterThrowing(pointcut = "logExpression()", throwing = "e")
	public void afterThrowing(final JoinPoint jp, final Throwable e) {
		LoggerRunnable logRun = new LoggerRunnable();
		logRun.putParams(request, logService, jp, null, e);
		logRun.run();
		logger.debug("异常日志写入完成，异常解析：" + Exceptions.getStackTraceAsString(e) );
	}

	
}
