package cn.com.evo.admin.manage.web.controller;

import cn.com.evo.admin.manage.domain.entity.ProvinceConstant;
import cn.com.evo.admin.manage.domain.vo.ProvinceConstantVo;
import cn.com.evo.admin.manage.service.ProvinceConstantService;
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
@RequestMapping("/manage/provinceConstant")
public class ProvinceConstantController extends BaseController {
    private static final String FORM_PAGE = "manage/provinceConstant/form";

    @Autowired
    private ProvinceConstantService provinceConstantService;

    @Autowired
    private Mapper mapper;

    protected ProvinceConstantService getService() {
        return provinceConstantService;
    }

    @RequiresPermissions(value = {"Manage:ProvinceConstant:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<ProvinceConstant> specification = DynamicSpecifications.bySearchFilter(request, ProvinceConstant.class, null);
            List<ProvinceConstant> entities = getService().findByCondition(specification, webPage);
            List<ProvinceConstantVo> lstVo = Lists.newArrayList();
            for (ProvinceConstant entity : entities) {
                ProvinceConstantVo vo = mapper.map(entity, ProvinceConstantVo.class);
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

    @RequiresPermissions(value = {"Manage:ProvinceConstant:show"})
    @RequestMapping(value = "/add/{provinceId}", method = {RequestMethod.GET})
    public ModelAndView add(@PathVariable("provinceId") String provinceId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("provinceId", provinceId);
        mav.addObject("type", 1);
        return mav;
    }

    @RunLogger(value = "添加省网详细配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:ProvinceConstant:add"})
    @RequestMapping(value = "/add/{provinceId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(ProvinceConstant entity) {
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

    @RequiresPermissions(value = {"Manage:ProvinceConstant:show"})
    @RequestMapping(value = "/edit/{provinceId}/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("provinceId") String provinceId, @PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        ProvinceConstant data = getService().findById(id);
        mav.addObject("data", data);
        mav.addObject("provinceId", provinceId);
        mav.addObject("type", 2);
        return mav;
    }

    @RunLogger(value = "编辑省网详细配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:ProvinceConstant:modify"})
    @RequestMapping(value = "/edit/{provinceId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(ProvinceConstant entity) {
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

    @RunLogger(value = "删除省网详细配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:ProvinceConstant:remove"})
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

    @RunLogger(value = "批量省网详细配置", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:ProvinceConstant:remove"})
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