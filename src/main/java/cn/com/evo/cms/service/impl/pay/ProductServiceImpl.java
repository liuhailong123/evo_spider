package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.domain.entity.pay.*;
import cn.com.evo.cms.domain.enums.UnitEnum;
import cn.com.evo.cms.repository.pay.ProductRepository;
import cn.com.evo.cms.service.pay.*;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.frameworks.utils.DateUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl extends AbstractBaseService<Product, String> implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServerRelService productServerRelService;
    @Autowired
    private ProductWelfareDiscountRelService productWelfareDiscountRelService;
    @Autowired
    private PriceDiscountService priceDiscountService;
    @Autowired
    private ProductRelService productRelService;

    @Override
    protected BaseRepository<Product, String> getBaseRepository() {
        return productRepository;
    }

    @Override
    public boolean hasBookServer(String prodCode) {
        // 图书服务编码
        String serverCode = "10002";
        Product product = productRepository.getByProductCodeAndServerCode(prodCode, serverCode);
        if (product == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Date getOverDate(Date beginDate, String productCode) {
        // 视频服务编码
        String serverCode = "10001";
        Product product = productRepository.getByProductCodeAndServerCode(productCode, serverCode);
        //根据产品id 获取产品服务配置关系 List
        List<ProductServerRel> productServerRels = productServerRelService.findByProductId(product.getId());
        //遍历 产品服务配置关系 List
        for (ProductServerRel productServerRel : productServerRels) {
            //获取产品服务配置 （服务+规则）
            ServerRuleRel serverRuleRel = productServerRel.getServerRuleRel();
            //获取 规则
            Rule rule = serverRuleRel.getRule();

            //规则类型
            UnitEnum unitEnum = UnitEnum.getByValue(Integer.valueOf(rule.getUnit()));
            if (unitEnum.equals(UnitEnum.min)
                    || unitEnum.equals(UnitEnum.hour)
                    || unitEnum.equals(UnitEnum.day)
                    || unitEnum.equals(UnitEnum.month)
                    || unitEnum.equals(UnitEnum.year)) {
                //计算 服务到期时间
                String maturityTime = calculateMaturityTime(beginDate.getTime(), rule.getUnit(), rule.getCount());
                return DateUtil.stringToDate(maturityTime);
            }
        }
        return null;
    }


    /**
     * 计算到期时间
     *
     * @param startTime 开始时间
     * @param unit      单位
     * @param count     时长
     * @return
     */
    private String calculateMaturityTime(Long startTime, String unit, Integer count) {
        //到期时间初始化
        Date maturityTime = new Date();
        switch (UnitEnum.getByValue(Integer.valueOf(unit))) {
            case min:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.MINUTE, count);
                break;
            case hour:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.HOUR_OF_DAY, count);
                break;
            case day:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.DAY_OF_MONTH, count);
                break;
            case month:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.MONTH, count);
                break;
            case year:
                maturityTime = DateUtil.addDate(new Date(startTime), Calendar.YEAR, count);
                break;
            default:
                break;
        }
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(maturityTime);
    }


    @Override
    public void update(Product entity) {
        List<ProductServerRel> productServerRels = productServerRelService.findByProductId(entity.getId());
        if (productServerRels != null) {
            if (productServerRels.size() > 0) {
                entity.setProductServerRels(productServerRels);
            }
        }
        super.update(entity);
    }

    @Override
    public void save(Product entity) {
        Product product = productRepository.getByCode(entity.getCode());
        if (product != null) {
            throw new RuntimeException("套餐编码重复,请重新录入");
        }
        super.save(entity);
    }

    @Override
    public void deleteById(String id) {
        // 检测是否存在产品与应用、栏目、内容之间关系
        List<Product> rels = productRelService.findByBizId(id);
        if (rels.size() > 0) {
            throw new RuntimeException("产品存在与应用、栏目、内容之间关系!!!不能删除，如需删除请先解除该关系！！！");
        }
        List<ProductServerRel> productServerRels = productServerRelService.findByProductId(id);
        if (productServerRels != null) {
            if (productServerRels.size() > 0) {
                productServerRelService.deleteByEntities(productServerRels);
            }
        }
        super.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public List<Product> findByAppId(String appId) {
        return productRepository.findByAppIdOrderBySort(appId);
    }

    @Override
    public Product getByCode(String prodCode) {
        return productRepository.getByCode(prodCode);
    }


    @Override
    public void calculateByIds(String[] ids) {
        for (String id : ids) {
            Product entity = this.findById(id);
            //获取产品下挂在的所有价格优惠方式
            List<ProductWelfareDiscountRel> productWelfareDiscountRels = productWelfareDiscountRelService.findByProductIdAndType(id, 1);
            List<PriceDiscount> priceDiscounts = Lists.newArrayList();
            for (ProductWelfareDiscountRel productWelfareDiscountRel : productWelfareDiscountRels) {
                PriceDiscount priceDiscount = priceDiscountService.findById(productWelfareDiscountRel.getDiscountId());
                priceDiscounts.add(priceDiscount);
            }
            //根据价格优惠方式List,原价获取该产品的优惠价
            String currentPrice = priceDiscountService.calDiscountValue(priceDiscounts, entity.getId());
            entity.setCurrentPrice(currentPrice);
            this.save(entity);
        }
    }

    @Override
    public Product getByThirdPartyCodeAndCode(String prodCode, String code) {
        return productRepository.getByThirdPartyCodeAndCode(prodCode, code);
    }

    @Override
    public Product getByThirdPartyCode(String prodCode) {
        return productRepository.getByThirdPartyCode(prodCode);
    }

}
