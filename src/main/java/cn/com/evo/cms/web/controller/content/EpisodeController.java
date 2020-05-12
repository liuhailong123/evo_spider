package cn.com.evo.cms.web.controller.content;

import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.domain.vo.cms.ContentVo;
import cn.com.evo.cms.domain.vo.cms.SourcePictureVo;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.cms.SourceRelService;
import cn.com.evo.cms.web.voService.SourceVoService;
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

@RequestMapping("/sourceManage/episode")
@Controller
public class EpisodeController extends BaseController {
    private static final String VIEW_PAGE = "cms/sourceManage/episode/view";
    private static final String FORM_PAGE = "cms/sourceManage/episode/form";

    @Autowired
    private ContentService contentService;
    @Autowired
    private SourceRelService sourceRelService;

    @Autowired
    private SourceVoService sourceVoService;

    @Autowired
    private Mapper mapper;

    protected ContentService getService() {
        return contentService;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Episode:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Episode:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Content> specification = DynamicSpecifications.bySearchFilter(request, Content.class, null);
            List<Content> entities = getService().findByCondition(specification, webPage);
            List<ContentVo> lstVo = Lists.newArrayList();
            for (Content entity : entities) {
                ContentVo vo = mapper.map(entity, ContentVo.class);
                List<SourceRel> imageRels = sourceRelService.findByFId(entity.getId(), SourceTypeEnum.image.getIndex());
                if (imageRels.size() > 0) {
                    vo.setIsImageRel(imageRels.size());
                } else {
                    vo.setIsImageRel(0);
                }

                List<Content> childs = contentService.findByPIdOrderBySortAsc(entity.getId());
                int i = 0;
                for (Content child : childs) {
                    List<SourceRel> videoRels = sourceRelService.findByFId(child.getId(), SourceTypeEnum.video.getIndex());
                    if (videoRels.size() > 0) {
                        i = 1;
                    } else {
                        i = 2;
                        break;
                    }
                }
                vo.setIsVideoRel(i);
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Episode:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("type", 1);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Episode:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult store(Content entity) {
        DataResult dataRet = new DataResult();
        try {
            entity.setSynType(0);
            getService().save(entity);
            dataRet.pushOk("添加成功！");
            dataRet.setRows(entity);
        } catch (Exception e) {
            dataRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return dataRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Episode:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Content entity = getService().findById(id);
        mav.addObject("entity", entity);
        mav.addObject("type", 2);
        List<SourcePictureVo> pictureSources = sourceVoService.findPictureByFId(entity.getId(), 2);
        mav.addObject("pictureSources", pictureSources);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Episode:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Content entity, @RequestParam(value = "contentImageHidden", required = false) String contentImage) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().update(entity, contentImage, null);
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }


    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Episode:remove"})
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Episode:remove"})
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
