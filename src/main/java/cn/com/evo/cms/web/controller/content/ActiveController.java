package cn.com.evo.cms.web.controller.content;

import cn.com.evo.cms.domain.entity.cms.Active;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.vo.cms.ActiveVo;
import cn.com.evo.cms.service.cms.ActiveService;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceRelService;
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

@RequestMapping("/sourceManage/active")
@Controller
public class ActiveController extends BaseController {

    private static final String VIEW_PAGE = "cms/sourceManage/active/view";
    private static final String FORM_PAGE = "cms/sourceManage/active/form";
    private static final String PREVIEW_PAGE = "cms/sourceManage/active/preview";
    private static final String SELECT_PAGE = "cms/sourceManage/active/select";
    @Autowired
    private ActiveService activeService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private Mapper mapper;

    protected ActiveService getService() {
        return activeService;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:show"})
    @RequestMapping(value = "/select", method = {RequestMethod.GET})
    public ModelAndView select() {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:show"})
    @RequestMapping(value = "preview/{id}", method = {RequestMethod.GET})
    public ModelAndView preview(@PathVariable("id") String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(PREVIEW_PAGE);
        Active entity = getService().findById(id);
        String[] info = entity.getOtherInfo().split("xxxx");
        String src = info[0] + entity.getInfo() + info[1];
        mav.addObject("src", src);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Active> specification = DynamicSpecifications.bySearchFilter(request, Active.class, null);
            List<Active> entities = getService().findByCondition(specification, webPage);
            List<ActiveVo> lstVo = Lists.newArrayList();
            for (Active entity : entities) {
                ActiveVo vo = mapper.map(entity, ActiveVo.class);
                List<SourceRel> sourceRels = sourceRelService.findByFId(entity.getId(), 2);
                if (sourceRels != null) {
                    if (sourceRels.size() > 0) {
                        String pictureId = sourceRels.get(0).getSourceId();
                        Picture picture = pictureService.findById(pictureId);
                        if (picture != null) {
                            vo.setImgUrl(pictureService.getImageUrl(pictureId));
                            vo.setImgName(picture.getSource().getName());
                        }
                    }
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Active entity, @RequestParam("imgId") String imgId) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().save(entity, imgId);
            msgRet.pushOk("添加成功！");
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Active entity = getService().findById(id);
        mav.addObject("entity", entity);
        List<SourceRel> sourceRels = sourceRelService.findByFId(entity.getId(), 2);
        if (sourceRels != null) {
            if (sourceRels.size() > 0) {
                String pictureId = sourceRels.get(0).getSourceId();
                Picture picture = pictureService.findById(pictureId);
                if (picture != null) {
                    mav.addObject("imgId", picture.getId());
                    mav.addObject("imgName", picture.getSource().getName());
                }
            }
        }
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Active entity, @RequestParam("imgId") String imgId) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().update(entity, imgId);
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:remove"})
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Active:remove"})
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
