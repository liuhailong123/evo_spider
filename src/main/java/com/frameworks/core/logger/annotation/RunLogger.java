package com.frameworks.core.logger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.frameworks.core.logger.LoggerType;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
//自定义注解,主要用来打印日志
public @interface RunLogger {
	String value() default "";
	boolean isSaveRequest() default false;
	LoggerType opType() default LoggerType.OPERATION;
}
