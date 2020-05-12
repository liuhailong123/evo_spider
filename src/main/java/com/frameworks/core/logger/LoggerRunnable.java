package com.frameworks.core.logger;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.alibaba.fastjson.JSON;
import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.utils.Exceptions;

import cn.com.evo.admin.manage.service.LogService;

public class LoggerRunnable implements Runnable {

	private static final Logger logger = LogManager.getLogger(LoggerRunnable.class);
	
	private ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<>();
	
	private LogService logService;
	
	private JoinPoint jp;
	
	private Object retVal;
	
	private Throwable e;
	
	@Override
	public void run() {
		processlogger();
	}

	public void putParams(HttpServletRequest request, LogService logService, JoinPoint jp, Object retVal, Throwable e){
		localRequest.set(request);
		this.logService = logService;
		this.jp = jp;
		this.retVal = retVal;
		this.e = e;
	}
	
	private RunLogger getAnnotation(JoinPoint jp) {
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method method = signature.getMethod();
		if (null != method) {
			return method.getAnnotation(RunLogger.class);
		}
		return null;
	}

	private MsgResult getResult(Object retVal) {
		if (MsgResult.class.isInstance(retVal)) {
			return (MsgResult) retVal;
		} else {
			return null;
		}
	}

	private void processlogger() {
		try {
			RunLogger runLogger = this.getAnnotation(jp);
			String operation = runLogger.value();
			boolean isSaveRequest = runLogger.isSaveRequest();
			String opParams = "";
			if (isSaveRequest) {
				opParams = JSON.toJSONString(localRequest.get().getParameterMap());
			}
			String opResult = "";
			LoggerType logType = runLogger.opType();
			String opType = logType.name();
			switch (logType) {
			case LOGIN:
			case VISIT:
				opResult = "成功";
				break;
			case OPERATION:
				MsgResult msgResult = this.getResult(retVal);
				if (null != msgResult) {
					opResult = msgResult.getStatus() + ":" + msgResult.getMessage();
				}
				break;
			case EXCEPTION:
				opResult = Exceptions.getStackTraceAsString(e);
				break;
			default:
				opResult = "注解日志错误，无法进行解析!";
				break;
			}
			String hostIp = localRequest.get().getRemoteHost();
			String[] out = {operation, opParams, opResult, hostIp, opType};
			logger.debug(JSON.toJSONString(out));
			logService.logWriter(operation, opParams, opResult, hostIp, opType);
		} catch (Exception ex) {
			logger.error(ex, ex);
		}
	}
}
