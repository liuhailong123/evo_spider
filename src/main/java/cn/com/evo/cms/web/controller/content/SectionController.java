package cn.com.evo.cms.web.controller.content;

import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Section;
import cn.com.evo.cms.domain.entity.cms.SectionTemplate;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.domain.vo.cms.SectionVo;
import cn.com.evo.cms.domain.vo.cms.SourcePictureVo;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SectionService;
import cn.com.evo.cms.service.cms.SectionTemplateService;
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

@RequestMapping("/sourceManage/section")
@Controller
public class SectionController extends BaseController {

    private static final String VIEW_PAGE = "cms/sourceManage/section/view";
    private static final String FORM_PAGE = "cms/sourceManage/section/form";

    @Autowired
    private SectionService sectionService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private SectionTemplateService sectionTemplateService;
    @Autowired
    private SourceVoService sourceVoService;
    @Autowired
    private Mapper mapper;

    protected SectionService getService() {
        return sectionService;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Section:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Section:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Section> specification = DynamicSpecifications.bySearchFilter(request, Section.class, null);
            List<Section> entities = getService().findByCondition(specification, webPage);
            List<SectionVo> lstVo = Lists.newArrayList();
            for (Section entity : entities) {
                SectionVo vo = mapper.map(entity, SectionVo.class);
                SectionTemplate sectionTemplate = sectionTemplateService.getByTemplateCode(entity.getTemplateCode());
                vo.setTemplateName(sectionTemplate.getName());
                List<SourceRel> sourceRels = sourceRelService.findByFId(entity.getId(), SourceTypeEnum.image.getIndex());
                if (sourceRels.size() > 0) {
                    Picture picture = pictureService.findById(sourceRels.get(0).getSourceId());
                    vo.setPreviewUrl(pictureService.getImageUrl(picture.getId()));
                }
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Section:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("type", 1);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Section:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult store(Section entity) {
        DataResult dataRet = new DataResult();
        try {
            getService().save(entity);
            dataRet.pushOk("添加成功！");
            dataRet.setRows(entity);
        } catch (Exception e) {
            dataRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return dataRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Section:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Section entity = getService().findById(id);
        SectionVo vo = mapper.map(entity, SectionVo.class);
        SectionTemplate sectionTemplate = sectionTemplateService.getByTemplateCode(entity.getTemplateCode());
        vo.setTemplateName(sectionTemplate.getName());
        List<SourceRel> sourceRels = sourceRelService.findByFIdAndRelType(entity.getId(), SourceTypeEnum.image.getIndex());
        if (sourceRels.size() > 0) {
            Picture picture = pictureService.findById(sourceRels.get(0).getSourceId());
            vo.setPreviewUrl(pictureService.getImageUrl(picture.getId()));
        }
        mav.addObject("entity", vo);
        mav.addObject("type", 2);
        List<SourcePictureVo> pictureSources = sourceVoService.findPictureByFId(entity.getId(), 2);
        mav.addObject("pictureSources", pictureSources);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Section:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Section entity, @RequestParam(value = "contentImageHidden", required = false) String contentImage) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().update(entity, contentImage);
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Section:remove"})
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Section:remove"})
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
