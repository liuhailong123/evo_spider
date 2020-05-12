package cn.com.evo.cms.web.controller.app;

import cn.com.evo.cms.domain.entity.app.AppLockConf;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.vo.app.AppLockConfVo;
import cn.com.evo.cms.service.app.AppLockConfService;
import cn.com.evo.cms.service.cms.ColumnService;
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
@RequestMapping("app/appLockConf")
public class AppLockConfController extends BaseController {
    private static final String FORM_PAGE = "cms/app/appLockConf/form";

    @Autowired
    private AppLockConfService appLockConfService;

    @Autowired
    private ColumnService columnService;

    @Autowired
    private Mapper mapper;

    protected AppLockConfService getService() {
        return appLockConfService;
    }

    @RequiresPermissions(value = {"App:AppInfo:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<AppLockConf> specification = DynamicSpecifications.bySearchFilter(request, AppLockConf.class, null);
            List<AppLockConf> entities = getService().findByCondition(specification, webPage);
            List<AppLockConfVo> lstVo = Lists.newArrayList();
            for (AppLockConf entity : entities) {
                AppLockConfVo vo = mapper.map(entity, AppLockConfVo.class);
                String contentName = "";
                if (entity.getContentType() == 1) {
                    Column column = columnService.findById(entity.getContentId());
                    contentName = column.getName();
                }
                vo.setContentName(contentName);
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

    @RequiresPermissions(value = {"App:AppInfo:show"})
    @RequestMapping(value = "/add/{appId}", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request, @PathVariable("appId") String appId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        AppLockConf entity = new AppLockConf();
        entity.setAppId(appId);
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"App:AppInfo:add"})
    @RequestMapping(value = "/add/{appId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(AppLockConf entity) {
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

    @RequiresPermissions(value = {"App:AppInfo:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        AppLockConf entity = getService().findById(id);
        mav.addObject("entity", entity);
        String contentName = "";
        if (entity.getContentType() == 1) {
            Column column = columnService.findById(entity.getContentId());
            contentName = column.getName();
        }
        mav.addObject("contentName", contentName);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"App:AppInfo:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(AppLockConf entity) {
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
    @RequiresPermissions(value = {"App:AppInfo:remove"})
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
    @RequiresPermissions(value = {"App:AppInfo:remove"})
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
