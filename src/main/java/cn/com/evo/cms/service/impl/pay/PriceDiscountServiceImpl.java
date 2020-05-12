package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.PriceDiscount;
import cn.com.evo.cms.domain.enums.PriceDiscountTypeEnum;
import cn.com.evo.cms.repository.pay.PriceDiscountRepository;
import cn.com.evo.cms.service.pay.PriceDiscountService;
import cn.com.evo.cms.utils.MathUtil;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PriceDiscountServiceImpl extends AbstractBaseService<PriceDiscount, String> implements PriceDiscountService {

    @Autowired
    private PriceDiscountRepository repository;

    @Override
    protected BaseRepository<PriceDiscount, String> getBaseRepository() {
        return repository;
    }

    @Override
    public void save(PriceDiscount entity) {
        entity.setCode(getCode().toString());
        entity.setTargetValue(MathUtil.multiply(entity.getTargetValue(), 100) + "");
        entity.setDiscountValue(getDiscountMoneyFromJSP(entity.getDiscountValue(), entity.getType()));
        super.save(entity);
    }

    @Override
    public void update(PriceDiscount entity) {
        entity.setTargetValue(MathUtil.multiply(entity.getTargetValue(), 100) + "");
        entity.setDiscountValue(getDiscountMoneyFromJSP(entity.getDiscountValue(), entity.getType()));
        super.update(entity);
    }

    @Override
    public String calDiscountValue(List<PriceDiscount> list, String money) {
        // list排序
        Collections.sort(list, (arg0, arg1) -> arg1.getPriority().compareTo(arg0.getPriority()));

        // 初始化金额
        Double srcMoney = new Double(money);
        Double discountMoney = new Double(0);
        for (PriceDiscount entity : list) {
            // 当前金额 大于 目标金额
            if (new BigDecimal(srcMoney).compareTo(new BigDecimal(entity.getTargetValue())) >= 0) {
                Integer type = entity.getType();
                if (PriceDiscountTypeEnum.ZheKou.getIndex().equals(type)) {
                    //优惠金额 = 原始金额 * 优惠规则上配置的折扣率
                    Double temp = MathUtil.multiply(srcMoney, entity.getDiscountValue());
                    //累加优惠金额
                    discountMoney = MathUtil.add(discountMoney, temp);
                    //原始金额 - 目标金额
                    srcMoney = MathUtil.subtract(srcMoney, entity.getTargetValue());
                } else if (PriceDiscountTypeEnum.ManJian.getIndex().equals(type)) {
                    if (entity.getIsMore() == 1) {
                        //累加
                        while (new BigDecimal(srcMoney).compareTo(new BigDecimal(entity.getTargetValue())) >= 0) {
                            //累加优惠金额
                            discountMoney = MathUtil.add(discountMoney, entity.getDiscountValue());

                            // 原始金额 - 目标金额
                            srcMoney = MathUtil.subtract(srcMoney, entity.getTargetValue());
                        }
                    } else if (entity.getIsMore() == 0) {
                        //不累加
                        //累加优惠金额
                        discountMoney = MathUtil.add(discountMoney, entity.getDiscountValue());

                        //原始金额 - 目标金额
                        srcMoney = MathUtil.subtract(srcMoney, entity.getTargetValue());
                    } else {
                        throw new RuntimeException("价格优惠是否累加字段错误");
                    }
                } else {
                    throw new RuntimeException("价格优惠类型错误");
                }
            }
        }
        return MathUtil.subtract(money, discountMoney) + "";
    }

    @Override
    public String getDiscountMoneyFromJSP(String discountValue, Integer type) {
        Float temp = Float.valueOf(discountValue);
        String num;
        switch (PriceDiscountTypeEnum.val(type)) {
            case ManJian:
                num = MathUtil.multiply(temp, 100) + "";
                break;
            case ZheKou:
                num = MathUtil.round(MathUtil.divide(temp, 100), 10) + "";
                break;
            case ChuZhi:
                num = MathUtil.multiply(temp, 100) + "";
                break;
            default:
                throw new RuntimeException("优惠类型错误");
        }
        return num;
    }

    @Override
    public String getDiscountMoneyFromDB(String discountValue, Integer type) {
        Float temp = Float.valueOf(discountValue);
        String num;
        switch (PriceDiscountTypeEnum.val(type)) {
            case ManJian:
                num = MathUtil.round(MathUtil.divide(temp, 100), 2) + "";
                break;
            case ZheKou:
                num = MathUtil.multiply(temp, 100) + "";
                break;
            case ChuZhi:
                num = MathUtil.round(MathUtil.divide(temp, 100), 2) + "";
                break;
            default:
                throw new RuntimeException("优惠类型错误");
        }
        return num;
    }

    /**
     * 获取优惠编码
     *
     * @return
     */
    private Integer getCode() {
        Integer code = repository.getMaxCode();
        if (code == null) {
            return 1;
        } else {
            return code + 1;
        }
    }

}
