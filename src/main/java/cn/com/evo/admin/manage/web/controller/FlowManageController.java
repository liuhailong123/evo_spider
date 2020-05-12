package cn.com.evo.admin.manage.web.controller;

import cn.com.evo.admin.manage.domain.entity.FlowManage;
import cn.com.evo.admin.manage.domain.vo.FlowManageVo;
import cn.com.evo.admin.manage.service.FlowManageService;
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
@RequestMapping("/manage/flowManage")
public class FlowManageController extends BaseController {
    private static final String FORM_PAGE = "manage/flowManage/form";
    private static final String VIEW_PAGE = "manage/flowManage/view";
    private static final String COPY_PAGE = "manage/flowManage/copy";

    @Autowired
    private FlowManageService flowManageService;

    @Autowired
    private Mapper mapper;

    protected FlowManageService getService() {
        return flowManageService;
    }

    @RequiresPermissions(value = {"Manage:FlowManage:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"Manage:FlowManage:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<FlowManage> specification = DynamicSpecifications.bySearchFilter(request, FlowManage.class, null);
            List<FlowManage> entities = getService().findByCondition(specification, webPage);
            List<FlowManageVo> lstVo = Lists.newArrayList();
            for (FlowManage entity : entities) {
                FlowManageVo vo = mapper.map(entity, FlowManageVo.class);
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

    @RequiresPermissions(value = {"Manage:FlowManage:show"})
    @RequestMapping(value = "/add/{provinceId}", method = {RequestMethod.GET})
    public ModelAndView add(@PathVariable("provinceId") String provinceId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("provinceId", provinceId);
        mav.addObject("type", 1);
        return mav;
    }

    @RunLogger(value = "添加播放鉴权流程配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:FlowManage:add"})
    @RequestMapping(value = "/add/{provinceId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(FlowManage entity) {
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

    @RequiresPermissions(value = {"Manage:FlowManage:show"})
    @RequestMapping(value = "/edit/{provinceId}/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("provinceId") String provinceId, @PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        FlowManage data = getService().findById(id);
        mav.addObject("data", data);
        mav.addObject("provinceId", provinceId);
        mav.addObject("type", 2);
        return mav;
    }

    @RunLogger(value = "编辑播放鉴权流程配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:FlowManage:modify"})
    @RequestMapping(value = "/edit/{provinceId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(FlowManage entity) {
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

    @RequiresPermissions(value = {"Manage:FlowManage:show"})
    @RequestMapping(value = "/copy", method = {RequestMethod.GET})
    public ModelAndView copy() {
        ModelAndView mav = new ModelAndView(COPY_PAGE);
        mav.addObject("ids", "");
        return mav;
    }

    @RunLogger(value = "编辑播放鉴权流程配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:FlowManage:modify"})
    @RequestMapping(value = "/copy/{code}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult copy(@PathVariable("code") String code, @RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().copy(code, ids);
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除播放鉴权流程配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:FlowManage:remove"})
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

    @RunLogger(value = "批量播放鉴权流程配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:FlowManage:remove"})
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