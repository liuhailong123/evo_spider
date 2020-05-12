package com.frameworks.env.spring;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.frameworks.core.logger.LoggerAspect;
import com.google.common.collect.Lists;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

@Configuration
@Description(value = "Spring Web MVC 基础配置")
@ComponentScan(basePackages = {"cn.com.evo"}, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class,
                ControllerAdvice.class})})
@EnableAspectJAutoProxy
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurationSupport {

    private static final String CHARACTER_SET = "UTF-8";

    @Autowired
    private DefaultWebSecurityManager securityManager;

    /**
     * 注入拦截器
     */
    @Bean
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return super.requestMappingHandlerMapping();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /**
     * 消息转换
     */
    @Bean
    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        return super.requestMappingHandlerAdapter();
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
                Charset.forName(CHARACTER_SET));
        converters.add(stringHttpMessageConverter);
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastjsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        List<MediaType> supportedMediaTypes = Lists.newArrayList();
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastjsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        List<SerializerFeature> features = Lists.newArrayList();
        features.add(SerializerFeature.WriteMapNullValue);
        features.add(SerializerFeature.QuoteFieldNames);
        features.add(SerializerFeature.WriteDateUseDateFormat);
        features.add(SerializerFeature.DisableCircularReferenceDetect);
        fastjsonHttpMessageConverter.setFeatures(features.toArray(new SerializerFeature[]{}));

        converters.add(fastjsonHttpMessageConverter);
    }

    /**
     * 增加静态资源访问
     */
    @Bean
    @Override
    public HandlerMapping resourceHandlerMapping() {
        return super.resourceHandlerMapping();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/")
                .resourceChain(true);
        registry.addResourceHandler("/upload/**").addResourceLocations("/upload/")
                .resourceChain(true);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
                .resourceChain(true);
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(true);
        registry.addResourceHandler("/ueditor/**").addResourceLocations("/ueditor/")
                .resourceChain(true);
    }

    /**
     * 自定义视图跳转
     */
    @Bean
    @Override
    public HandlerMapping viewControllerHandlerMapping() {
        return super.viewControllerHandlerMapping();
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "redirect:/index");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding(CHARACTER_SET);
        multipartResolver.setMaxInMemorySize(1024);
        multipartResolver.setMaxUploadSize(5368709120L);//允许上传的最大字节数：2147483648 = 2GB
        return multipartResolver;
    }

    /**
     * freeMarker html视图解释器
     *
     * @return
     */
    @Bean
    public FreeMarkerViewResolver htmlFreeMarkerViewResolver() {
        FreeMarkerViewResolver htmlFreeMarkerViewResolver = new FreeMarkerViewResolver();
        // freeMarkerViewResolver.setPrefix("/WEB-INF/template/");
        htmlFreeMarkerViewResolver.setSuffix(".html");
        htmlFreeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
        htmlFreeMarkerViewResolver.setCache(false);
        htmlFreeMarkerViewResolver.setOrder(0);
        return htmlFreeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/html/");
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("default_encoding", "utf-8");
        settings.setProperty("number_format", "0.##########");
        settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.setProperty("classic_compatible", "true");
        settings.setProperty("template_exception_handler", "ignore");
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }

    /**
     * 视图层解析
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver jspResolver() {
        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
        jspViewResolver.setPrefix("/WEB-INF/views/");
        jspViewResolver.setViewClass(JstlView.class);
        jspViewResolver.setSuffix(".jsp");
        jspViewResolver.setOrder(30);
        return jspViewResolver;
    }

    /**
     * 全局异常
     *
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties exceptionMappings = new Properties();
        exceptionMappings.put("org.apache.shiro.authz.UnauthorizedException", "error/403");
        exceptionMappings.put("java.lang.Throwable", "error/500");
        exceptionResolver.setExceptionMappings(exceptionMappings);
        Properties statusCodes = new Properties();
        statusCodes.put("500", "500");
        statusCodes.put("404", "404");
        statusCodes.put("403", "403");
        exceptionResolver.setStatusCodes(statusCodes);
        return exceptionResolver;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAdvisor.setSecurityManager(securityManager);
        return authorizationAdvisor;
    }

    @Bean
    public LoggerAspect loggerAspect() {
        return new LoggerAspect();
    }


/*
    *//**
     * Redis Client配置 Begin
     *//*
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大能够保持idel状态的对象数
        config.setMaxIdle(300);
        //最大分配的对象数
        config.setMaxTotal(60000);
        //当调用borrow Oject方法时，是否进行有效性检查
        config.setTestOnBorrow(true);
        return config;
    }

    *//**
     * jedis Factory
     *
     * @return
     *//*
    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        //设置存储序号为0
        factory.setDatabase(0);
        factory.setHostName("svn.evomedia.cn");
        factory.setPort(6379);
        factory.setPassword("evomedia-redis");
        factory.setPoolConfig(jedisPoolConfig());
        return factory;
    }

    *//**
     * redis配置
     *
     * @return
     *//*
    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        initDomainRedisTemplate(redisTemplate, connectionFactory());
        return redisTemplate;
    }


    *//**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     *//*
    public void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }

    *//**
     * 注入封装RedisTemplate
     *
     * @return RedisUtil
     * @throws
     * @Title: redisUtil
     * @autor lpl
     * @date 2017年12月21日
     *//*
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }*/
}
