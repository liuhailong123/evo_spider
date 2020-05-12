package cn.com.evo.cms.repository.pay;

import cn.com.evo.cms.domain.entity.pay.Order;
import com.frameworks.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderVoRepository extends BaseRepository<Order, String> {

    @Query(value = "select COUNT(1) 'count' from (SELECT COUNT(1) from pay_order_info where orderType =1  GROUP BY userId)a",nativeQuery = true)
    Integer getUserCountAll();

    @Query(value = "select sum(payAmountMoney)/100 'money' from pay_order_info where orderType =1",nativeQuery = true)
    Long getMoneyAll();

    @Query(value = "select COUNT(1) ," +
            "(SELECT `name` from pay_product where id =a.productId)'name' from pay_order_info a " +
            "where a.orderType =1 GROUP BY productId",nativeQuery = true)
    List<Object[]> getProductAll();

    @Query(value = "select payAmountMoney/100 'maxMoney' from pay_order_info  where orderType =1 ORDER BY payAmountMoney asc LIMIT 1",nativeQuery = true)
    Long getMaxMoney();

    @Query(value = "select COUNT(1) 'count' from (select * from pay_order_info where orderType =1 and  overDate like ?1 GROUP BY userId)a",nativeQuery = true)
    Integer getDayUserCount(String yesToday);

    @Query(value = "select sum(payAmountMoney)/100 'money' from pay_order_info where orderType =1 and  overDate like ?1",nativeQuery = true)
    Long getDayMoney(String yesToday);

    @Query(value = "select payAmountMoney/100 'maxMoney' from pay_order_info  where orderType =1 and overDate like ?1 ORDER BY payAmountMoney asc LIMIT 1",nativeQuery = true)
    Long getDayMaxMoney(String yesToday);
}
