package cn.com.evo.cms.web.controller.total;

import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.vo.pay.OrderVo;
import cn.com.evo.cms.service.pay.OrderService;
import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/total/order")
public class OrderController extends BaseController {
    private static final String VIEW_PAGE = "cms/total/order/view";
    /**
     * 人工处理
     */
    private static final String MANUAL_PROCESS_PAGE = "cms/total/order/manualProcess";

    @Autowired
    private OrderService orderService;

    @Autowired
    private Mapper mapper;

    protected OrderService getService() {
        return orderService;
    }

    @RequiresPermissions(value = {"Total:Order:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"Total:Order:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Order> specification = DynamicSpecifications.bySearchFilter(request, Order.class, null);
            List<Order> entities = getService().findByCondition(specification, webPage);
            List<OrderVo> lstVo = Lists.newArrayList();
            for (Order entity : entities) {
                OrderVo vo = mapper.map(entity, OrderVo.class);
                lstVo.add(vo);
            }
            dataRet.pushOk("获取数据列表成功！");
            dataRet.setTotal(webPage.getTotalCount());
            dataRet.setRows(lstVo);
        } catch (Exception e) {
            dataRet.pushError("获取数据列表失败！");
            logger.error("获取数据列表异常！", e);
        }
        return dataRet;
    }

    @RequiresPermissions(value = {"Total:Order:show"})
    @RequestMapping(value = "/manualProcess/{id}", method = {RequestMethod.GET})
    public ModelAndView manualProcess(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(MANUAL_PROCESS_PAGE);
        Order order = getService().findById(id);
        mav.addObject("entity", order);
        return mav;
    }


    @RunLogger(value = "人工处理", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Module:modify"})
    @RequestMapping(value = "/manualProcess", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult manualProcess(Order order) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().manualProcess(order);
            msgRet.pushOk("人工处理成功！");
        } catch (Exception e) {
            msgRet.pushError("人工处理失败：" + e.getMessage());
            logger.error("人工处理时，发生异常！", e);
        }
        return msgRet;
    }
}
