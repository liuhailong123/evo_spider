package cn.com.evo.cms.web.controller.pay;

import cn.com.evo.cms.domain.entity.pay.LimitFree;
import cn.com.evo.cms.domain.vo.pay.LimitFreeVo;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.web.voService.ContentVoService;
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
@RequestMapping("/pay/limitFree")
public class LimitFreeController extends BaseController {
    private static final String VIEW_PAGE = "cms/pay/limitFree/view";
    private static final String FORM_PAGE = "cms/pay/limitFree/form";
    private static final String TIME_FORM_PAGE = "cms/pay/limitFree/timeForm";
    @Autowired
    private LimitFreeService limitFreeService;
    @Autowired
    private ColumnService columnService;

    @Autowired
    private ContentVoService contentVoService;

    @Autowired
    private Mapper mapper;

    protected LimitFreeService getService() {
        return limitFreeService;
    }

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<LimitFree> specification = DynamicSpecifications.bySearchFilter(request, LimitFree.class, null);
            List<LimitFree> entities = getService().findByCondition(specification, webPage);
            List<LimitFreeVo> lstVo = Lists.newArrayList();
            for (LimitFree entity : entities) {
                LimitFreeVo vo = mapper.map(entity, LimitFreeVo.class);
                vo.setAppName(columnService.findById(entity.getAppId()).getName());

                vo.setBizName(getService().getBizName(entity.getId()));
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

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(LimitFree entity) {
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

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:show"})
    @RequestMapping(value = "/addFromSite/{bizId}/{type}/{contentId}/{contentType}", method = {RequestMethod.GET})
    public ModelAndView addFromSite(@PathVariable(value = "bizId") String bizId,
                                    @PathVariable(value = "type") Integer type,
                                    @PathVariable(value = "contentId") String contentId,
                                    @PathVariable(value = "contentType") Integer contentType) {
        ModelAndView mav = new ModelAndView(TIME_FORM_PAGE);
        if (type == 1) {
            mav.addObject("appId", bizId);
            mav.addObject("columnId", "");
            mav.addObject("contentId", "");
            mav.addObject("contentType", "");
        } else if (type == 2) {
            mav.addObject("appId", columnService.getAppId(bizId));
            mav.addObject("columnId", bizId);
            mav.addObject("contentId", "");
            mav.addObject("contentType", "");
        } else if (type == 3) {
            mav.addObject("appId", columnService.getAppId(bizId));
            mav.addObject("columnId", "");
            mav.addObject("contentId", contentId);
            mav.addObject("contentType", contentType);
        }
        mav.addObject("bizId", bizId);
        mav.addObject("type", type);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:add"})
    @RequestMapping(value = "/addFromSite/{bizId}/{type}/{contentId}/{contentType}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult storeFromSite(LimitFree entity) {
        DataResult dataResult = new DataResult();
        try {
            getService().save(entity);
            dataResult.setRows(entity);
            dataResult.pushOk("添加成功！");
        } catch (Exception e) {
            dataResult.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return dataResult;
    }

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        LimitFree entity = getService().findById(id);
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(LimitFree entity) {
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
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:remove"})
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
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:remove"})
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
