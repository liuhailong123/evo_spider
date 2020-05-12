package cn.com.evo.provincial.factory;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.provincial.enums.ProvincialContant;
import cn.com.evo.provincial.service.Provincial;
import cn.com.evo.provincial.service.ProvincialService;
import cn.com.evo.provincial.utils.SpringContextUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author rf
 * @date 2019/7/15
 */
@Service
@Component
public class ProvincialFactory implements Provincial {

    @Override
    public ProvincialService createProvincial(String code) {
        try {
            ProvincialContant initialization = ProvincialContant.Initialization(code);
            assert initialization != null;
            ProvincialService provincialService = (ProvincialService)SpringContextUtil.getApplicationContext().getBean(initialization.getName());
            return provincialService;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        Provincial provincialFactory = new ProvincialFactory();
        ProvincialService chongqing = provincialFactory.createProvincial("chongqing");
        Province province = new Province();
        chongqing.registMovie("", province);
        chongqing.updateMovie("", province);
    }
}
