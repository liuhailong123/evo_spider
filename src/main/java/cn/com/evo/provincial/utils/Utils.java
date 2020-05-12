package cn.com.evo.provincial.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resources;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author rf
 * @date 2019/7/18
 * Created by Kowalski on 2017/7/27
 * Updated by Kowalski on 2017/7/27
 * 活动引擎工具类
 */
@Slf4j
public class Utils {

    /**静态缓存*/
    private static final ConcurrentMap<Class<?>, ConcurrentMap<Field, Object>> map = new ConcurrentHashMap<Class<?>, ConcurrentMap<Field, Object>>();
    /**
     * 初始化bean时(如json parse)注入Service属性
     * 放在构造方法中初始化(Utils.initServce(this);)
     * @param object
     */
    public static void initServce(Object object) {

        Map<Field, Object> filedsBeansFromMap = map.get(object.getClass());

        if (filedsBeansFromMap != null) {
            /**遍历Filed与Spring Bean对应关系*/
            for (Map.Entry<Field, Object> filedsBeans : filedsBeansFromMap.entrySet()) {
                try {
                    filedsBeans.getKey().set(object, filedsBeans.getValue());
                } catch (IllegalAccessException e) {
                    log.error("Utils initServce IllegalAccessException:{}", e);
                }
            }
            return;
        }
        /**获得所有属性(包括私有不包括父类)*/
        Field[] fields = object.getClass().getDeclaredFields();
        /**Filed与Spring Bean对应关系*/
        ConcurrentMap<Field, Object> tofiledsBeansMap = new ConcurrentHashMap<Field, Object>();
        for (Field field : fields) {
            /**只针对Autowired 与 Resources注解属性使用*/
            if (field.getAnnotation(Autowired.class) != null || field.getAnnotation(Resources.class) != null) {
                try {
                    /**设置私有属性可写*/
                    field.setAccessible(true);
                    /**拿到单例Service 放入对象属性中*/
                    Object bean = SpringContextUtil.getApplicationContext().getBean(field.getType());
                    /**给属性赋值*/
                    field.set(object, bean);
                    tofiledsBeansMap.putIfAbsent(field, bean);
                } catch (IllegalAccessException e) {
                    log.error("Utils initServce IllegalAccessException:{}", e);
                } catch (Exception e) {
                    log.error("Utils initServce set filed failed:{}", e);
                }
            }
        }
        map.putIfAbsent(object.getClass(), tofiledsBeansMap);
    }

    public static void main(String[] args) {

    }
}
