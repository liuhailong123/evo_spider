package cn.com.evo.cms.web.controller.pay;

import cn.com.evo.cms.domain.entity.pay.PayConfigParams;
import cn.com.evo.cms.domain.vo.pay.PayConfigParamsVo;
import cn.com.evo.cms.service.pay.PayConfigParamsService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/productCharge/payConfigParams")
public class PayConfigParamsController extends BaseController {
    private static final String VIEW_PAGE = "cms/productCharge/payConfigParams/view";
    private static final String FORM_PAGE = "cms/productCharge/payConfigParams/form";

    @Autowired
    private PayConfigParamsService payConfigParamsService;

    @Autowired
    private Mapper mapper;

    protected PayConfigParamsService getService() {
        return payConfigParamsService;
    }

    @RequiresPermissions(value = {"ProductCharge:PayConfig:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ProductCharge:PayConfig:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<PayConfigParams> specification = DynamicSpecifications.bySearchFilter(request, PayConfigParams.class, null);
            List<PayConfigParams> entities = getService().findByCondition(specification, webPage);
            List<PayConfigParamsVo> lstVo = Lists.newArrayList();
            for (PayConfigParams entity : entities) {
                PayConfigParamsVo vo = mapper.map(entity, PayConfigParamsVo.class);
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

    @RequiresPermissions(value = {"ProductCharge:PayConfig:show"})
    @RequestMapping(value = "/add/{configId}", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request, @PathVariable("configId") String configId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("configId", configId);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:PayConfig:add"})
    @RequestMapping(value = "/add/{configId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(PayConfigParams entity) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().save(entity);
            msgRet.pushOk("添加成功！");
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ProductCharge:PayConfig:show"})
    @RequestMapping(value = "/edit/{configId}/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id, @PathVariable("configId") String configId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        PayConfigParams entity = getService().findById(id);
        mav.addObject("entity", entity);
        mav.addObject("configId", configId);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:PayConfig:modify"})
    @RequestMapping(value = "/edit/{configId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(PayConfigParams entity) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().update(entity);
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:PayConfig:remove"})
    @RequestMapping(value = "/remove/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult remove(@PathVariable("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().deleteById(id);
            msgRet.pushOk("删除成功！");
        } catch (Exception e) {
            msgRet.pushError("删除失败：" + e.getMessage());
            logger.error("删除时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "批量删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:PayConfig:remove"})
    @RequestMapping(value = "/remove", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult remove(@RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().deleteByIds(ids);
            msgRet.pushOk("批量删除成功!");
        } catch (Exception e) {
            msgRet.pushError("批量删除失败：" + e.getMessage());
            logger.error("批量删除时，发生异常！", e);
        }
        return msgRet;
    }
}
