package cn.com.evo.cms.web.controller.content;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Source;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.domain.vo.cms.SourcePictureVo;
import cn.com.evo.cms.service.cms.PictureService;
import cn.com.evo.cms.service.cms.SourceService;
import cn.com.evo.cms.web.voService.PictureVoService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/sourceManage/picture")
@Controller
public class PictureController extends BaseController {

    private static final String FORM_PAGE = "cms/sourceManage/picture/form";
    private static final String VIEW_PAGE = "cms/sourceManage/picture/view";
    private static final String SELECT_PAGE = "cms/sourceManage/picture/select";
    private static final String SELECT_PICTURE_PAGE = "cms/sourceManage/picture/selectPictures";
    private static final String SINGLE_SELECT_PICTURE_PAGE = "cms/sourceManage/picture/singleSelectPictures";
    private static final String IMPORT_PAGE = "cms/sourceManage/picture/import";
    private static final String UPLOAD_PAGE = "cms/sourceManage/picture/pictureUpload";

    @Autowired
    private PictureVoService pictureVoService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private SourceService sourceService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private Mapper mapper;

    protected PictureService getService() {
        return pictureService;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "/select", method = {RequestMethod.GET})
    public ModelAndView select() {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "/pictureSelect/{type}", method = {RequestMethod.GET})
    public ModelAndView pictureSelect(HttpServletRequest request, @PathVariable("type") int type, @RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView(SELECT_PICTURE_PAGE);
        mav.addObject("type", type);
        mav.addObject("name", name);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "/singlePictureSelect2/{type}/{name}", method = {RequestMethod.GET})
    public ModelAndView singlePictureSelect2(HttpServletRequest request, @PathVariable("type") int type, @PathVariable("name") String name) {
        ModelAndView mav = new ModelAndView(SINGLE_SELECT_PICTURE_PAGE);
        mav.addObject("type", type);
        mav.addObject("name", name);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "/singlePictureSelect/{type}", method = {RequestMethod.GET})
    public ModelAndView singlePictureSelect(HttpServletRequest request, @PathVariable("type") int type, @RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView(SINGLE_SELECT_PICTURE_PAGE);
        mav.addObject("type", type);
        mav.addObject("name", name);
        return mav;
    }


    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView showPicture(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(VIEW_PAGE);
        mav.addObject("type", 2);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Picture> specification = DynamicSpecifications.bySearchFilter(request, Picture.class, null);
            List<Picture> entities = getService().findByCondition(specification, webPage);
            List<SourcePictureVo> lstVo = Lists.newArrayList();
            //获取当前省网资源服务器配置
            Province provice = provinceService.getByEnable(1);
            for (Picture entity : entities) {
                SourcePictureVo vo = mapper.map(entity, SourcePictureVo.class);
                vo.setUrl(provice.getImageHost() + entity.getFileName());
                lstVo.add(vo);
            }
            dataRet.pushOk("获取数据列表成功！");
            dataRet.setTotal(webPage.getTotalCount());
            dataRet.setPage(1L);
            dataRet.setRecords(webPage.getTotalCount());
            dataRet.setRows(lstVo);
        } catch (Exception e) {
            dataRet.pushError("获取数据列表失败！");
            logger.error("获取数据列表异常！", e);
        }
        return dataRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:search"})
    @RequestMapping(value = "/pictureList", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult pictureList(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Picture> specification = DynamicSpecifications.bySearchFilter(request, Picture.class, null);
            List<Picture> entities = pictureService.findByCondition(specification, webPage);
            List<SourcePictureVo> lstVo = Lists.newArrayList();
            lstVo = pictureVoService.handle(entities);
            dataRet.pushOk("获取数据列表成功！");
            dataRet.setTotal(webPage.getTotalCount());
            dataRet.setRows(lstVo);
        } catch (Exception e) {
            dataRet.pushError("获取数据列表失败！");
            logger.error("获取数据列表异常！", e);
        }
        return dataRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "/add/{sourceId}", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request, @PathVariable("sourceId") String sourceId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Picture entity = new Picture();
        entity.setSource(sourceService.findById(sourceId));
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:add"})
    @RequestMapping(value = "/add/{sourceId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Picture entity) {
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Picture entity = getService().findById(id);
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Picture entity) {
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:remove"})
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:remove"})
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:add"})
    @RequestMapping(value = "/dataImport", method = {RequestMethod.GET})
    public ModelAndView importFile(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(IMPORT_PAGE);
        return mav;
    }

    @RunLogger(value = "excel导入数据库", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:add"})
    @RequestMapping(value = "/dataImport", method = {RequestMethod.POST}, produces = {
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "/pictureUpload/{sourceId}", method = {RequestMethod.GET})
    public ModelAndView videoUploadPage(HttpServletRequest request, @PathVariable("sourceId") String sourceId) {
        ModelAndView mav = new ModelAndView(UPLOAD_PAGE);
        mav.addObject("sourceId", sourceId);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:show"})
    @RequestMapping(value = "/pictureUpload", method = {RequestMethod.GET})
    public ModelAndView pictureUploadsPage() {
        ModelAndView mav = new ModelAndView(UPLOAD_PAGE);
        mav.addObject("sourceId", "");
        return mav;
    }

    @RunLogger(value = "图片上传", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Picture:add"})
    @RequestMapping(value = "/pictureUpload", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult pictureUpload(@RequestParam("files") MultipartFile[] files,
                                   @RequestParam(value = "sourceId", required = false) String sourceId,
                                   @RequestParam("type") Integer type) {
        MsgResult msgRet = new MsgResult();
        try {
            if (files.length == 0) {
                msgRet.pushError("图片文件不能为空");
                return msgRet;
            }

            if (StringUtils.isNotBlank(sourceId)) {
                Source source = sourceService.findById(sourceId);

                Picture picture = new Picture();
                picture.setSource(source);
                picture.setType(type);

                pictureService.imageUpload(files, picture);
            } else {
                for (MultipartFile file : files) {
                    pictureService.imageUpload(file, type);
                }
            }

            msgRet.pushOk("图片上传成功！");
        } catch (Exception e) {
            msgRet.pushError("图片上传失败：" + e.getMessage());
            logger.error("图片上传时，发生异常！", e);
        }
        return msgRet;
    }
}
