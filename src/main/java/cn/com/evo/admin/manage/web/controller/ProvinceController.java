package cn.com.evo.admin.manage.web.controller;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.domain.vo.ProvinceVo;
import cn.com.evo.admin.manage.service.ProvinceService;
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
@RequestMapping("/manage/province")
public class ProvinceController extends BaseController {
    private static final String VIEW_PAGE = "manage/province/view";
    private static final String FORM_PAGE = "manage/province/form";

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private Mapper mapper;

    protected ProvinceService getService() {
        return provinceService;
    }

    @RequiresPermissions(value = {"Manage:Province:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"Manage:Province:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Province> specification = DynamicSpecifications.bySearchFilter(request, Province.class, null);
            List<Province> dictDatas = getService().findByCondition(specification, webPage);
            List<ProvinceVo> lstVo = Lists.newArrayList();
            for (Province dictData : dictDatas) {
                ProvinceVo vo = mapper.map(dictData, ProvinceVo.class);
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

    @RequiresPermissions(value = {"Manage:Province:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加省网数据", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Province:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Province entity) {
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

    @RequiresPermissions(value = {"Manage:Province:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Province data = getService().findById(id);
        mav.addObject("data", data);
        return mav;
    }

    @RunLogger(value = "编辑省网数据", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Province:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Province entity) {
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

    @RunLogger(value = "删除省网数据", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Province:remove"})
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

    @RunLogger(value = "批量省网数据", isSaveRequest = true)
    @RequiresPermissions(value = {"Manage:Province:remove"})
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