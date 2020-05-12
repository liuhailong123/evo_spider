package cn.com.evo.cms.web.controller.home;

import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.vo.pay.OrderVo;
import cn.com.evo.cms.web.voService.OrderVoService;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Autowired
    private OrderVoService orderVoService;

    @RequiresPermissions(value = {"Total:Order:search"})
    @RequestMapping(value = "/all", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request) {
        DataResult dataRet = new DataResult();
        try {
            JSONObject all = orderVoService.statisticsAll();
            dataRet.pushOk("获取数据列表成功！");
            dataRet.setRows(all);
        } catch (Exception e) {
            dataRet.pushError("获取数据列表失败！");
            logger.error("获取数据列表异常！", e);
        }
        return dataRet;
    }

}
