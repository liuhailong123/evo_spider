package cn.com.evo.cms.service.impl.pay;

import cn.com.evo.cms.constant.PayTypeEnum;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.pay.OrderAuthDTO;
import cn.com.evo.cms.domain.entity.pay.PayConfig;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.repository.pay.OrderRepository;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.PayConfigService;
import cn.com.evo.cms.service.pay.ProductService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.cms.service.vip.UserService;
import cn.com.evo.cms.utils.OrderUtils;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl extends AbstractBaseService<Order, String> implements OrderService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserServerService userServerService;
    @Autowired
    private PayConfigService payConfigService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ColumnService columnService;

    @Override
    protected BaseRepository<Order, String> getBaseRepository() {
        return orderRepository;
    }

    @Override
    public Order getByThirdPartyOrderNo(String thirdPartyOrderNo) {

        return orderRepository.getByThirdPartyOrderNo(thirdPartyOrderNo);
    }

    @Override
    public Order getByOrderNo(String orderNo) {
        return orderRepository.getByOrderNo(orderNo);
    }

    @Override
    public List<Order> findByUserIdAndOrderTypeAndAppIdOrderByCreateDateDesc(String userId, int orderType, String appId) {
        return orderRepository.findByUserIdAndOrderTypeAndAppIdOrderByCreateDateDesc(userId, orderType, appId);
    }

    @Override
    public boolean orderAuth(String serverCode, String userId, String appId, List<Product> list) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT i.productId,i.overDate,r.unit,r.count FROM")
                .append(" pay_order_info i,pay_product_server_rel sr,")
                .append(" pay_server_rule_rel rr,pay_rule r,pay_server s")
                .append(" WHERE sr.product_id = i.productId ")
                .append(" and rr.id = sr.server_rule_rel_id and rr.rule_id = r.id")
                .append(" and s.id = rr.server_id AND i.orderType = 1 ")
                .append(" and r.loseEfficacyTime >= now()")
                .append(" and i.appId = ?3")
                .append(" and i.productId = ?4")
                .append(" and s.code = ?1 AND i.userId = ?2 ")
                .append(" ORDER BY i.overDate DESC");


        // 是否过期标识：默认过期
        boolean orderFlag = true;
        if (list.size() == 0) {
            // 如果未配置产品list 则直接返回未过期
            orderFlag = false;
            return orderFlag;
        } else {
            for (Product product : list) {
                SQLQuery sqlQuery = entityManager.createNativeQuery(sql.toString())
                        .setParameter(1, serverCode).setParameter(2, userId)
                        .setParameter(3, appId).setParameter(4, product.getId())
                        .unwrap(SQLQuery.class);

                // 返回接口映射为map
                List temps = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

                // 转换list
                List<OrderAuthDTO> dtos = OrderAuthDTO.toDto(temps);

                // 关闭连接
                entityManager.clear();

                if (temps.size() > 0) {
                    // 存在订购记录,判断是否过期
                    orderFlag = OrderAuthDTO.isOverTime(dtos.get(0));
                } else {
                    // 用户无订购记录，设置过期
                    orderFlag = true;
                }

                if (!orderFlag) {
                    // 未过期 直接返回
                    return orderFlag;
                }
            }
            return orderFlag;
        }

    }

    @Override
    public void manualProcess(Order order) {
        Order entity = this.findById(order.getId());
        Integer manualProcessType = order.getManualProcessType();
        // 设置订单数据
        entity.setIsManualHandling(1);
        entity.setManualProcessType(manualProcessType);
        entity.setManualHandlingInfo(order.getManualHandlingInfo());

        if (manualProcessType == 1) {
            //手动支付
            if (entity.getOrderType() != 0) {
                throw new RuntimeException("只有未支付的订单可以手动支付");
            }
            entity.setOverDate(new Date());
            entity.setOrderType(1);
            //开通服务
            userServerService.openServer(entity.getUser().getId(), entity.getApp().getId(), entity.getProduct().getId());
        } else if (manualProcessType == 2) {
            if (entity.getOrderType() != 1) {
                throw new RuntimeException("只有已支付的订单可以取消取消订购");
            }
            entity.setOrderType(3);
            //取消订购
            userServerService.closeServer(entity.getUser().getId(), entity.getApp().getId(), entity.getProduct().getId());
        } else {
            throw new RuntimeException("人工处理类型错误！！！");
        }

        super.update(entity);
    }

    @Override
    public Order createOrder(String thirdOrderNo, String payConfigId, String userId, String productId, String payAbleMoney, String payAmountMoney, Integer payType, String appId, String accountId) {
        Order order = new Order();
        order.setOrderNo(OrderUtils.createOrderNo(payType));
        order.setThirdPartyOrderNo(thirdOrderNo);
        PayConfig payConfig = payConfigService.findById(payConfigId);
        order.setPayConfig(payConfig);
        User user = userService.findById(userId);
        order.setUser(user);
        UserAccount userAccount = userAccountService.findById(accountId);
        order.setUserAccount(userAccount);
        Product product = productService.findById(productId);
        order.setProduct(product);
        order.setPayAbleMoney(payAbleMoney);
        order.setPayAmountMoney(payAmountMoney);
        //未支付
        order.setOrderType(0);
        Column app = columnService.findById(appId);
        order.setApp(app);
        if (PayTypeEnum.BOSS.getType().equals(payType) || PayTypeEnum.SP.getType().equals(payType)) {
            order.setOrderType(1);
            order.setOverDate(new Date());
        }
        this.save(order);
        return order;
    }

    @Override
    public Order getByUserIdAndProductCodeAndOrderTypeAndOverDateAndAppId(String userId, String productCode, int orderType, Date overDate, String appId) {
        return orderRepository.getByUserIdAndProductCodeAndOrderTypeAndOverDateAndAppId(userId, productCode, orderType, overDate, appId);
    }

    @Override
    public List<Order> findByUserIdAndContentId(String uId, String sId) {
        return orderRepository.findByUserIdAndContentId(uId, sId);
    }
}

