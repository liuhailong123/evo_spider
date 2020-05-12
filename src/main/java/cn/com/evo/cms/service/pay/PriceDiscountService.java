package cn.com.evo.cms.service.pay;

import cn.com.evo.cms.domain.entity.pay.PriceDiscount;
import com.frameworks.core.service.BaseService;

import java.util.List;

public interface PriceDiscountService extends BaseService<PriceDiscount, String> {

    /**
     * 根据 '价格优惠list' 和 '原始金额' 计算 '实际金额'
     * 1. 价格优惠list中，只能有类型是满减类和折扣类的优惠规则。
     * 2. 保证list是按照优先级从大到小排序的。
     * 3. 多价格优惠规则时，第一次优惠计算完成后，将会减去相对应的目标金额，再进行判断是否匹配下次规则。
     * 4. 实际金额 = 原始金额 - 具体优惠金额
     *
     * @param list  价格优惠list
     * @param money 原价 单位:分
     * @return 返回：实际金额，
     */
    String calDiscountValue(List<PriceDiscount> list, String money);

    /**
     * 页面传入的优惠金额和优惠类型，计算需要存入数据库的数值
     *
     * @param discountValue 优惠金额（元）
     * @param type
     * @return
     */
    String getDiscountMoneyFromJSP(String discountValue, Integer type);

    /**
     * 数据库读取的优惠金额和优惠类型，计算需要返回页面的数值
     *
     * @param discountValue
     * @param type
     * @return
     */
    String getDiscountMoneyFromDB(String discountValue, Integer type);
}
