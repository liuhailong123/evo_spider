package cn.com.evo.cms.web.voService;

import cn.com.evo.cms.repository.pay.OrderVoRepository;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderVoService {

    @Autowired
    private OrderVoRepository orderVoRepository;


    public JSONObject statisticsAll() {
        JSONObject result = new JSONObject();
        //总订购用户数
        Integer user = orderVoRepository.getUserCountAll();
        //订购总金额
        Long money = orderVoRepository.getMoneyAll();
        //订购最大金额
        Long maxMoney = orderVoRepository.getMaxMoney();
        //产品总订购情况
        List<Object[]> products = orderVoRepository.getProductAll();
        //数据转化
        Object[][] productsJa = productsJaTransform(products);
        result.put("user", user);
        result.put("money", money);
        result.put("maxMoney", maxMoney);
        result.put("product", productsJa);
        JSONObject day = new JSONObject();
        //昨日购用户数
        Integer dayUser = orderVoRepository.getDayUserCount(getYesToday() + "%");
        //昨日订购总金额
        Long dayMoney = orderVoRepository.getDayMoney(getYesToday() + "%");
        //昨日订购最大金额
        Long dayMaxMoney = orderVoRepository.getDayMaxMoney(getYesToday() + "%");
        result.put("dayUser", dayUser);
        result.put("dayMoney", dayMoney);
        result.put("dayMaxMoney", dayMaxMoney);
        return result;

    }

    private String getYesToday() {
        String str = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        try {
            Date date = df.parse(df.format(System.currentTimeMillis()));
            Date yesToday = DateUtil.addDate(date, Calendar.DAY_OF_YEAR, -1);
            str = df.format(yesToday);
        } catch (ParseException e) {
            throw new RuntimeException("日期获取失败");
        }
        return str;
    }

    private Object[][] productsJaTransform(List<Object[]> products) {
        //定义颜色 绿／紫／蓝／红 /黑色
        String[] color = {"#2dc6c8", "#b6a2dd", "#5ab1ee", "#d7797f", "#111111", "#aaaaaa", "#bbbbbb", "#cccccc", "#dddddd"};

        Object[][] productsArray = new Object[products.size()][3];
        for (int i = 0; i < products.size(); i++) {
            Integer count = Integer.valueOf(products.get(i)[0] + "");
            String name = products.get(i)[1] + "";
            productsArray[i][0] = count;
            productsArray[i][1] = color[i];
            productsArray[i][2] = name;
        }
        return productsArray;
    }
}
