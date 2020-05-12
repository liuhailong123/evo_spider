package cn.com.evo.cms.web.controller.content;

import cn.com.evo.cms.domain.entity.cms.Source;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.domain.vo.cms.SourceVideoVo;
import cn.com.evo.cms.service.cms.SourceService;
import cn.com.evo.cms.service.cms.VideoService;
import cn.com.evo.cms.web.voService.VideoVoService;
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

@RequestMapping("/sourceManage/video")
@Controller
public class VideoController extends BaseController {

    private static final String FORM_PAGE = "cms/sourceManage/video/form";
    private static final String VIEW_PAGE = "cms/sourceManage/video/view";
    private static final String SELECT_PAGE = "cms/sourceManage/video/select";
    private static final String IMPORT_PAGE = "cms/sourceManage/video/import";
    private static final String UPLOAD_PAGE = "cms/sourceManage/video/videoUpload";

    @Autowired
    private VideoVoService videoVoService;

    @Autowired
    private VideoService videoService;
    @Autowired
    private SourceService sourceService;


    @Autowired
    private Mapper mapper;

    protected VideoService getService() {
        return videoService;
    }


    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView showVideo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(VIEW_PAGE);
        mav.addObject("type", 1);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:show"})
    @RequestMapping(value = "/videoSelect/{type}/{name}", method = {RequestMethod.GET})
    public ModelAndView videoSelect(HttpServletRequest request, @PathVariable("type") int type, @PathVariable("name") String name) {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        mav.addObject("type", type);
        mav.addObject("name", name);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Video> specification = DynamicSpecifications.bySearchFilter(request, Video.class, null);
            List<Video> entities = getService().findByCondition(specification, webPage);
            List<SourceVideoVo> lstVo = Lists.newArrayList();
            for (Video entity : entities) {
                SourceVideoVo vo = mapper.map(entity, SourceVideoVo.class);
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:search"})
    @RequestMapping(value = "/videoList", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult videoList(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Video> specification = DynamicSpecifications.bySearchFilter(request, Video.class, null);
            List<Video> entities = videoService.findByCondition(specification, webPage);
            List<SourceVideoVo> lstVo = Lists.newArrayList();
            lstVo = videoVoService.handle(entities);
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
    @RequestMapping(value = "/add/{sourceId}", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request, @PathVariable("sourceId") String sourceId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Video entity = new Video();
        entity.setSource(sourceService.findById(sourceId));
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:add"})
    @RequestMapping(value = "/add/{sourceId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Video entity) {
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
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Video entity = getService().findById(id);
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Video entity) {
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:add"})
    @RequestMapping(value = "/dataImport", method = {RequestMethod.GET})
    public ModelAndView importFile(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(IMPORT_PAGE);
        return mav;
    }

    @RunLogger(value = "excel导入数据库", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:add"})
    @RequestMapping(value = "/dataImport", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult importFile(@RequestParam(value = "file", required = false) MultipartFile[] files,
                                @RequestParam(value = "isOverwrite") int isOverwrite) {
        MsgResult msgRet = new MsgResult();
        try {
            if (files.length > 0) {
                getService().importFile(files, isOverwrite);
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:show"})
    @RequestMapping(value = "/videoUpload/{sourceId}", method = {RequestMethod.GET})
    public ModelAndView videoUploadPage(HttpServletRequest request, @PathVariable("sourceId") String sourceId) {
        ModelAndView mav = new ModelAndView(UPLOAD_PAGE);
        mav.addObject("sourceId", sourceId);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:show"})
    @RequestMapping(value = "/videoUpload", method = {RequestMethod.GET})
    public ModelAndView videoUploadsPage() {
        ModelAndView mav = new ModelAndView(UPLOAD_PAGE);
        mav.addObject("sourceId", "");
        return mav;
    }

    @RunLogger(value = "视频上传", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Video:add"})
    @RequestMapping(value = "/videoUpload", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult videoUpload(@RequestParam("files") MultipartFile[] files,
                                 @RequestParam(value = "sourceId", required = false) String sourceId,
                                 @RequestParam(value = "dirName", required = false) String dirName,
                                 @RequestParam(value = "platform", required = false) String platform,
                                 @RequestParam("definition") Integer definition,
                                 @RequestParam("videoFileType") Integer videoFileType) {
        MsgResult msgRet = new MsgResult();
        try {
            if (files.length == 0) {
                msgRet.pushError("视频文件不能为空");
                return msgRet;
            }

            if (videoFileType == 1) {
                // MP4、TS
                if (StringUtils.isNotBlank(sourceId)) {
                    Source source = sourceService.findById(sourceId);

                    Video video = new Video();
                    video.setDefinition(definition);
                    video.setSource(source);
                    video.setPlatform(platform);

                    videoService.videoUpload(files, video);
                } else {
                    for (MultipartFile file : files) {
                        videoService.videoUpload(file, definition, platform);
                    }
                }

                msgRet.pushOk("视频上传成功！");
            } else {
                // HLS
                if (StringUtils.isNotBlank(sourceId)) {
                    Source source = sourceService.findById(sourceId);

                    Video video = new Video();
                    video.setDefinition(definition);
                    video.setSource(source);
                    video.setPlatform(platform);

                    videoService.videoHlsUpload(files, video, dirName);
                } else {
                    for (MultipartFile file : files) {
                        videoService.videoHlsUpload(file, definition, dirName, platform);
                    }
                }
                msgRet.pushOk("视频上传成功！");
            }

        } catch (Exception e) {
            msgRet.pushError("视频上传失败：" + e.getMessage());
            logger.error("视频上传时，发生异常！", e);
        }
        return msgRet;
    }

}
