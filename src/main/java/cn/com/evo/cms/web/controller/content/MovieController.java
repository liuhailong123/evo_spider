package cn.com.evo.cms.web.controller.content;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.SourceRel;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.domain.vo.cms.ContentVo;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceRelService;
import cn.com.evo.cms.service.cms.SpService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/sourceManage/television")
@Controller
public class MovieController extends BaseController {

    private static final String VIEW_PAGE = "cms/sourceManage/television/view";
    private static final String FORM_PAGE = "cms/sourceManage/television/form";
    private static final String PRECISE_SELECT_PAGE = "cms/sourceManage/television/preciseSelect";
    private static final String DIM_SELECT_PAGE = "cms/sourceManage/television/dimSelect";
    private static final String SELECT_PAGE = "cms/sourceManage/television/select";
    private static final String DETAIL_PAGE = "cms/sourceManage/television/detail";
    private static final String IMPORT_PAGE = "cms/sourceManage/television/import";
    private static final String XJDX_IMPORT_PAGE = "cms/sourceManage/television/xjdxImport";

    @Autowired
    private ContentService contentService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private SpService spService;
    @Autowired
    private Mapper mapper;

    protected ContentService getService() {
        return contentService;
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
        Content entity = getService().findById(id);
        //获取当前省网资源服务器配置
        Province provice = provinceService.getByEnable(1);
        String previewUrl = "";
        List<Picture> pictures = pictureService.findByContentId(entity.getId());
        for (Picture picture : pictures) {
            if (picture.getType() == 2) {
                previewUrl = provice.getImageHost() + picture.getFileName();
                break;
            }
        }
        ContentVo vo = mapper.map(entity, ContentVo.class);
        vo.setPreviewUrl(previewUrl);
        mav.addObject("entity", vo);
        return mav;
    }

    /**
     * 查询选择页
     *
     * @param request
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:show"})
    @RequestMapping(value = "/select/{type}", method = {RequestMethod.GET})
    public ModelAndView select(HttpServletRequest request, @PathVariable("type") String type) {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        mav.addObject("type", type);
        return mav;
    }

    /**
     * 模糊查询选择页
     *
     * @param request
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:show"})
    @RequestMapping(value = "/dimSelect/{name}", method = {RequestMethod.GET})
    public ModelAndView dimSelect(HttpServletRequest request, @PathVariable("name") String name) {
        ModelAndView mav = new ModelAndView(DIM_SELECT_PAGE);
        mav.addObject("name", name);
        return mav;
    }

    /**
     * 精确选择页
     *
     * @param request
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:show"})
    @RequestMapping(value = "/preciseSelect/{name}", method = {RequestMethod.GET})
    public ModelAndView preciseSelect(HttpServletRequest request, @PathVariable("name") String name) {
        ModelAndView mav = new ModelAndView(PRECISE_SELECT_PAGE);
        mav.addObject("name", name);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:search"})
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
                List<SourceRel> videoRels = sourceRelService.findByFId(entity.getId(), SourceTypeEnum.video.getIndex());
                if (videoRels.size() > 0) {
                    vo.setIsVideoRel(videoRels.size());
                } else {
                    vo.setIsVideoRel(0);
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("type", 1);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Content entity) {
        MsgResult msgRet = new MsgResult();
        try {
            entity.setSynType(0);
            getService().save(entity);
            msgRet.pushOk("添加成功！");
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Content entity = getService().findById(id);
        mav.addObject("entity", entity);
        mav.addObject("type", 2);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Content entity) {
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:remove"})
    @RequestMapping(value = "/removeRelation/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult removeRelation(@PathVariable("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().deleteById(id);
            msgRet.pushOk("删除成功!");
        } catch (Exception e) {
            msgRet.pushError("删除失败：" + e.getMessage());
            logger.error("删除时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "批量删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:remove"})
    @RequestMapping(value = "/removeRelation", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult removeRelation(@RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().deleteByIds(ids);
            msgRet.pushOk("删除成功!");
        } catch (Exception e) {
            msgRet.pushError("删除失败：" + e.getMessage());
            logger.error("批量删除时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/importFile", method = {RequestMethod.GET})
    public ModelAndView importFile(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(IMPORT_PAGE);
        return mav;
    }

    @RunLogger(value = "excel片单导入", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/importFile", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult importFile(@RequestParam(value = "file", required = false) MultipartFile[] files) {
        MsgResult msgRet = new MsgResult();
        try {
            if (files.length > 0) {
                getService().importFile(files);
                msgRet.pushOk("excel导入数据库成功！");
            } else {
                msgRet.pushError("excel导入数据库失败：未选择文件");
            }
        } catch (Exception e) {
            msgRet.pushError("excel导入数据库失败：" + e.getMessage());
            logger.error("excel导入数据库时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/xjdxImportFile", method = {RequestMethod.GET})
    public ModelAndView xjdxImportFile(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(XJDX_IMPORT_PAGE);
        return mav;
    }

    @RunLogger(value = "新疆电信excel片单导入", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/xjdxImportFile", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult xjdxImportFile(@RequestParam(value = "file", required = false) MultipartFile[] files) {
        MsgResult msgRet = new MsgResult();
        try {
            if (files.length > 0) {
                spService.registMovieForXjdx(files);
                msgRet.pushOk("excel导入数据库成功！");
            } else {
                msgRet.pushError("excel导入数据库失败：未选择文件");
            }
        } catch (Exception e) {
            msgRet.pushError("excel导入数据库失败：" + e.getMessage());
            logger.error("excel导入数据库时，发生异常！", e);
        }
        return msgRet;
    }


    @RunLogger(value = "自动绑定内容与视频、内容与图片关系", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/autoRel", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult autoRel(@RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            for (String id : ids) {
                Content content = contentService.findById(id);
                contentService.autoBindVideoAndImageRel(content);
            }
            msgRet.pushOk("excel导入数据库成功！");
        } catch (Exception e) {
            msgRet.pushError("excel导入数据库失败：" + e.getMessage());
            logger.error("excel导入数据库时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "自动绑定内容与视频、内容与图片关系", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/autoRel/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult autoRel(@PathVariable("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            Content content = contentService.findById(id);
            contentService.autoBindVideoAndImageRel(content);
            msgRet.pushOk("excel导入数据库成功！");
        } catch (Exception e) {
            msgRet.pushError("excel导入数据库失败：" + e.getMessage());
            logger.error("excel导入数据库时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "刷新整个内容库的检索字段", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Movie:modify"})
    @RequestMapping(value = "/refresh", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult refresh() {
        MsgResult msgRet = new MsgResult();
        try {
            contentService.refresh();
            msgRet.pushOk("刷新整个内容库的检索字段成功！");
        } catch (Exception e) {
            msgRet.pushError("刷新整个内容库的检索字段失败：" + e.getMessage());
            logger.error("刷新整个内容库的检索字段时，发生异常！", e);
        }
        return msgRet;
    }
}
