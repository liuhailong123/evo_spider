package cn.com.evo.provincial.service;

import org.springframework.stereotype.Component;

/**
 * 省网工厂接口
 * @author rf
 * @date 2019/7/15
 */
@Component
public interface Provincial {
    ProvincialService createProvincial(String code);
}
