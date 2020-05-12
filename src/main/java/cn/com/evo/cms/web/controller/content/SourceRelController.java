package cn.com.evo.cms.web.controller.content;

import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.domain.vo.cms.SourceRelPictureVo;
import cn.com.evo.cms.domain.vo.cms.SourceRelVideoVo;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceRelService;
import cn.com.evo.cms.service.cms.VideoService;
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

@RequestMapping("/sourceManage/sourceRel")
@Controller
public class SourceRelController extends BaseController {

    private static final String FORM_PAGE = "cms/sourceManage/sourceRel/form";

    @Autowired
    private SourceRelService sourceRelService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private Mapper mapper;

    protected SourceRelService getService() {
        return sourceRelService;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:search"})
    @RequestMapping(value = "/videoList", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult videoList(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<SourceRel> specification = DynamicSpecifications.bySearchFilter(request, SourceRel.class,
                    null);
            List<SourceRel> entities = getService().findByCondition(specification, webPage);
            List<SourceRelVideoVo> lstVo = Lists.newArrayList();
            for (SourceRel entity : entities) {
                Video video = videoService.findById(entity.getSourceId());
                SourceRelVideoVo vo = mapper.map(video, SourceRelVideoVo.class);
                vo.setId(entity.getId());
                vo.setVideoId(video.getId());
                vo.setName(video.getSource().getName());
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:search"})
    @RequestMapping(value = "/pictureList", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult pictureList(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<SourceRel> specification = DynamicSpecifications.bySearchFilter(request, SourceRel.class,
                    null);
            List<SourceRel> entities = getService().findByCondition(specification, webPage);
            List<SourceRelPictureVo> lstVo = Lists.newArrayList();
            for (SourceRel entity : entities) {
                Picture picture = pictureService.findById(entity.getSourceId());
                SourceRelPictureVo vo = mapper.map(picture, SourceRelPictureVo.class);
                vo.setId(entity.getId());
                vo.setPictureId(picture.getId());
                vo.setName(picture.getSource().getName());
                vo.setBusinessType(entity.getBusinessType());
                vo.setUrl(pictureService.getImageUrl(picture.getId()));
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        SourceRel entity = getService().findById(id);
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(SourceRel entity) {
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

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(@RequestParam("objStr") String objStr, @RequestParam("type") Integer type,
                           @RequestParam("contentId") String contentId) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().save(objStr, type, contentId);
            msgRet.pushOk("添加成功！");
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:remove"})
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

    @RunLogger(value = "批量关系删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:remove"})
    @RequestMapping(value = "/remove", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult removeRelation(@RequestParam("ids[]") String[] ids) {
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

    @RunLogger(value = "更改业务类型", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/changeBusinessType", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult changeBusinessType(@RequestParam("sourceRelId") String sourceRelId,
                                        @RequestParam("businessType") Integer businessType) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().changeBusinessType(sourceRelId, businessType);
            msgRet.pushOk("更改业务类型成功!");
        } catch (Exception e) {
            msgRet.pushError("更改业务类型失败：" + e.getMessage());
            logger.error("更改业务类型时，发生异常！", e);
        }
        return msgRet;
    }

}
