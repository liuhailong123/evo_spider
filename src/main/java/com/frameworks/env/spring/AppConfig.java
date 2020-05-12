package com.frameworks.env.spring;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.eventbus.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@Description(value = "Spring基础配置")
@PropertySource({"classpath:jdbc.properties"})
@ComponentScan(basePackages = {"cn.com.evo"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, ControllerAdvice.class})})
@EnableJpaRepositories(basePackages = {
        "cn.com.evo"}, transactionManagerRef = "transactionManager", entityManagerFactoryRef = "entityManagerFactory")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class AppConfig {

    private static final Logger logger = LogManager.getLogger(AppConfig.class);

    @Autowired(required = true)
    private Environment environment;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource dataSource = null;
        try {
            // 选择版本
            String chooseVersion = environment.getRequiredProperty("choose.version");

            String propertiesPath = "jdbc/" + chooseVersion + ".properties";
            logger.error("当前加载jdbc配置为：" + propertiesPath);

            Properties prop = new Properties();
            ///加载属性列表
            prop.load(getClass().getClassLoader().getResourceAsStream(propertiesPath));

            dataSource = new DruidDataSource();
            dataSource.setDriverClassName(prop.getProperty("jdbc.driver"));
            dataSource.setUrl(prop.getProperty("jdbc.url"));
            dataSource.setUsername(prop.getProperty("jdbc.username"));
            dataSource.setPassword(prop.getProperty("jdbc.password"));
            logger.error("jdbc.driver：" + prop.getProperty("jdbc.driver"));
            logger.error("jdbc.url：" + prop.getProperty("jdbc.url"));
            logger.error("jdbc.username：" + prop.getProperty("jdbc.username"));
            logger.error("jdbc.password：" + prop.getProperty("jdbc.password"));

            dataSource.setInitialSize(Integer.parseInt(environment.getRequiredProperty("druid.initialSize")));
            dataSource.setMaxActive(Integer.parseInt(environment.getRequiredProperty("druid.maxActive")));
            dataSource.setMinIdle(Integer.parseInt(environment.getRequiredProperty("druid.minIdle")));
            dataSource.setMaxWait(Long.parseLong(environment.getRequiredProperty("druid.maxWait")));
            dataSource.setFilters(environment.getRequiredProperty("druid.filters"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e, e);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            logger.error(e, e);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e, e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e, e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e, e);
        }
        return dataSource;
    }

    private Properties getJpaProperties() {
        Properties props = new Properties();
        /* 抓取策略 */
        props.put("hibernate.current_session_context_class", "thread");
        props.put("hibernate.default_batch_fetch_size", 2);
        props.put("hibernate.jdbc.fetch_size", 30);
        props.put("hibernate.jdbc.batch_size", 30);

		/* 缓存 */
        props.put("hibernate.cache.use_second_level_cache", true);
        props.put("hibernate.cache.use_query_cache", true);
        props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        props.put("net.sf.ehcache.configurationResourceName", "ehcache/ehcache-local.xml");

		/* 建表的命名规则 */
        props.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

		/* 用于调试的属性 */
        props.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        props.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        props.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        props.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return props;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        // entityManagerFactory.setPersistenceUnitName("default_jpa");
        // entityManagerFactory.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        String packages = environment.getRequiredProperty("hibernate.jpa.packageToScan");
        String[] packagesToScan = packages.split(",");
        entityManagerFactory.setPackagesToScan(packagesToScan);
        entityManagerFactory.setJpaProperties(getJpaProperties());
        entityManagerFactory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public DozerBeanMapperFactoryBean mapper() {
        DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
        Resource res = new ClassPathResource("mapper/dozer-mapping.xml");
        mapper.setMappingFiles(new Resource[]{res});
        return mapper;
    }

    /**
     * 事件总线,用于跨服务联动操作
     *
     * @return
     */
    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }
}
