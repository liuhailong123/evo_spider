package cn.com.evo.cms.web.controller.content;

import cn.com.evo.cms.domain.entity.cms.Source;
import cn.com.evo.cms.domain.vo.cms.SourceVo;
import cn.com.evo.cms.service.cms.SourceService;
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

@RequestMapping("/sourceManage/source")
@Controller
public class SourceController extends BaseController {

    private static final String FORM_PAGE = "cms/sourceManage/source/form";

    @Autowired
    private SourceService sourceService;

    @Autowired
    private Mapper mapper;

    protected SourceService getService() {
        return sourceService;
    }


    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Source> specification = DynamicSpecifications.bySearchFilter(request, Source.class, null);
            List<Source> entities = getService().findByCondition(specification, webPage);
            List<SourceVo> lstVo = Lists.newArrayList();
            for (Source entity : entities) {
                SourceVo vo = mapper.map(entity, SourceVo.class);
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:show"})
    @RequestMapping(value = "/add/{type}", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request, @PathVariable("type") int type) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("type", type);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:add"})
    @RequestMapping(value = "/add/{type}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Source entity) {
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:show"})
    @RequestMapping(value = "/edit/{type}/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id, @PathVariable("type") int type) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Source entity = getService().findById(id);
        mav.addObject("entity", entity);
        mav.addObject("type", type);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:modify"})
    @RequestMapping(value = "/edit/{type}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Source entity) {
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:remove"})
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:remove"})
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


    /**
     * 保持session
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/keepSession", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult keepSession(HttpServletRequest request) {
        DataResult dataRet = new DataResult();
        try {
            dataRet.pushOk("保持session成功！");
        } catch (Exception e) {
            dataRet.pushError("保持session失败：" + e.getMessage());
            logger.error("保持session失败！", e);
        }
        return dataRet;
    }
}
