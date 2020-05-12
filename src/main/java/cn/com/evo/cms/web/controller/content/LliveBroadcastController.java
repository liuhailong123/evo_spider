package cn.com.evo.cms.web.controller.content;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.LliveBroadcast;
import cn.com.evo.cms.domain.vo.cms.LliveBroadcastVo;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.cms.LliveBroadcastService;
import com.frameworks.core.logger.annotation.RunLogger;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.page.Pager;
import com.frameworks.core.web.result.DataResult;
import com.frameworks.core.web.result.MsgResult;
import com.frameworks.core.web.search.DynamicSpecifications;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("contentManage/liveBroadcast")
public class LliveBroadcastController extends BaseController {
    private static final String VIEW_PAGE = "cms/contentManage/liveBroadcast/view";
    private static final String FORM_PAGE = "cms/contentManage/liveBroadcast/form";
    private static final String SELECT_PAGE = "cms/contentManage/liveBroadcast/select";
    private static final String DETAIL_PAGE = "cms/contentManage/liveBroadcast/detail";

    @Autowired
    private LliveBroadcastService liveBroadcastService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;

    @Autowired
    private Mapper mapper;

    protected LliveBroadcastService getService() {
        return liveBroadcastService;
    }

    /**
     * 详情
     *
     * @param request
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:show"})
    @RequestMapping(value = "/detail/{id}", method = {RequestMethod.GET})
    public ModelAndView detail(HttpServletRequest request, @PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(DETAIL_PAGE);
        LliveBroadcast entity = getService().findById(id);
        LliveBroadcastVo vo = mapper.map(entity, LliveBroadcastVo.class);
        mav.addObject("entity", vo);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:show"})
    @RequestMapping(value = "/select", method = {RequestMethod.GET})
    public ModelAndView videoSelect(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<LliveBroadcast> specification = DynamicSpecifications.bySearchFilter(request, LliveBroadcast.class, null);
            List<LliveBroadcast> entities = getService().findByCondition(specification, webPage);
            List<LliveBroadcastVo> lstVo = Lists.newArrayList();
            for (LliveBroadcast entity : entities) {
                LliveBroadcastVo vo = mapper.map(entity, LliveBroadcastVo.class);
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

    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(LliveBroadcast entity) {
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

    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        LliveBroadcast entity = getService().findById(id);
        mav.addObject("entity", entity);
        if(StringUtils.isNotBlank(entity.getContentId())){
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(entity.getContentId());
            if (catalogueRelation != null) {
                Content content = contentService.findById(catalogueRelation.getBId());
                mav.addObject("contentName", content == null ? "" : content.getName());
            } else {
                mav.addObject("contentName", "");
            }
        }else{
            mav.addObject("contentName", "");
        }
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(LliveBroadcast entity) {
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
    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:remove"})
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
    @RequiresPermissions(value = {"ContentCenter:ContentManage:LliveBroadcast:remove"})
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
