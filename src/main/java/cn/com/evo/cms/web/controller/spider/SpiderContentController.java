package cn.com.evo.cms.web.controller.spider;

import cn.com.evo.admin.manage.domain.entity.Organization;
import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.entity.spider.SpiderContent;
import cn.com.evo.cms.domain.vo.book.BookInfoVo;
import cn.com.evo.cms.domain.vo.spider.SpiderContentVo;
import cn.com.evo.cms.service.book.BookInfoService;
import cn.com.evo.cms.service.book.BookPictureService;
import cn.com.evo.cms.service.spider.SpiderContentService;
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

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sourceManage/spiderContent")
public class SpiderContentController extends BaseController {

    private static final String VIEW_PAGE = "cms/sourceManage/spiderContent/view";
    private static final String FORM_PAGE = "cms/sourceManage/spiderContent/form";
    private static final String SELECT_TAGS_PAGE = "cms/sourceManage/spiderContent/selectTags";
    private static final String LIST_PAGE = "cms/sourceManage/spiderContent/list";
    private static final String SELECT_PAGE = "cms/sourceManage/spiderContent/select";
    private static final String DETAIL_PAGE = "cms/sourceManage/spiderContent/detail";
    @Autowired
    private SpiderContentService spiderContentService;

    @Autowired
    private Mapper mapper;

    protected SpiderContentService getService() {
        return spiderContentService;
    }

    /**
     * 详情
     *
     * @param request
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:show"})
    @RequestMapping(value = "/detail/{id}", method = {RequestMethod.GET})
    public ModelAndView detail(HttpServletRequest request, @PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(DETAIL_PAGE);
        SpiderContent entity = getService().findById(id);
        SpiderContentVo vo = mapper.map(entity, SpiderContentVo.class);
        vo.setSpInfo(entity.getSpInfo());
        mav.addObject("entity", vo);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:show"})
    @RequestMapping(value = "/select", method = {RequestMethod.GET})
    public ModelAndView select() {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        Organization org = getLoginUser().getOrganization();
        try {
            Specification<SpiderContent> specification = DynamicSpecifications.bySearchFilter(request, SpiderContent.class, null);
            List<SpiderContent> entities = getService().findByCondition(specification, webPage);
            List<SpiderContentVo> lstVo = Lists.newArrayList();
            for (SpiderContent entity : entities) {
                SpiderContentVo vo = mapper.map(entity, SpiderContentVo.class);
                vo.setSpInfo(entity.getSpInfo());
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(HttpServletRequest request) {
        MsgResult msgRet = new MsgResult();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            char[] buf = new char[512];
            int len = 0;
            StringBuffer contentBuffer = new StringBuffer();
            while ((len = reader.read(buf)) != -1) {
                contentBuffer.append(buf, 0, len);
            }
            //获取参数串，替换文本域中的&符号
            String content = URLDecoder.decode(contentBuffer.toString(), "UTF-8");
            content = content.replaceAll("&amp;", "_");
            //先截取文本域中的标签内容
            String[] infos = content.split("spInfo=");
            String[] param = infos[0].split("&");
            Map<String, String> params = new HashMap<String, String>();
            params.put("spInfo", infos[1]);
            for (String par : param) {
                String[] split = par.split("=");
                params.put(split[0], split[1]);
            }
            //通过id查询当前对象并赋值更新
            SpiderContent entity = new SpiderContent();
            entity.setTitle(params.get("title"));
            entity.setSubtitle(params.get("subtitle"));
            entity.setAuthor(params.get("author"));
            entity.setDigest(params.get("digest"));
            entity.setCover(params.get("cover"));
            entity.setTags(params.get("tags"));
            entity.setSource(params.get("source"));
            entity.setDateTime(params.get("dateTime"));
            entity.setReadNum(Integer.valueOf(params.get("readNum")));
            entity.setLikeNum(Integer.valueOf(params.get("likeNum")));
            entity.setSpInfo(params.get("spInfo").getBytes());
            getService().update(entity);

            msgRet.pushOk("添加成功！");
            System.out.println(entity.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }

        return msgRet;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        SpiderContent entity = getService().findById(id);
        SpiderContentVo vo = mapper.map(entity, SpiderContentVo.class);
        vo.setSpInfo(entity.getSpInfo());
        mav.addObject("entity", vo);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(HttpServletRequest request) {
        MsgResult msgRet = new MsgResult();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            char[] buf = new char[512];
            int len = 0;
            StringBuffer contentBuffer = new StringBuffer();
            while ((len = reader.read(buf)) != -1) {
                contentBuffer.append(buf, 0, len);
            }
            //获取参数串，替换文本域中的&符号
            String content = URLDecoder.decode(contentBuffer.toString(), "UTF-8");
            content = content.replaceAll("&amp;", "_");
            //先截取文本域中的标签内容
            String[] infos = content.split("spInfo=");
            String[] param = infos[0].split("&");
            Map<String, String> params = new HashMap<String, String>();
            params.put("spInfo", infos[1]);
            for (String par : param) {
                String[] split = par.split("=");
                params.put(split[0], split[1]);
            }
            //通过id查询当前对象并赋值更新
            String id = params.get("id");
            SpiderContent entity = getService().findById(id);
            entity.setTitle(params.get("title"));
            entity.setSubtitle(params.get("subtitle"));
            entity.setAuthor(params.get("author"));
            entity.setDigest(params.get("digest"));
            entity.setCover(params.get("cover"));
            entity.setTags(params.get("tags"));
            entity.setSource(params.get("source"));
            entity.setDateTime(params.get("dateTime"));
            entity.setReadNum(Integer.valueOf(params.get("readNum")));
            entity.setLikeNum(Integer.valueOf(params.get("likeNum")));
            entity.setSpInfo(params.get("spInfo").getBytes());
            getService().update(entity);

            msgRet.pushOk("更新成功！");
            System.out.println(entity.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            msgRet.pushError("更新失败：" + e.getMessage());
            logger.error("更新时，发生异常！", e);
        }

        return msgRet;
    }

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:remove"})
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContent:remove"})
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

    @RequestMapping(value = "/addTags", method = {RequestMethod.GET})
    public ModelAndView addTags(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(SELECT_TAGS_PAGE);
        return mav;
    }


    @RequestMapping(value = "/selectTagsOrType/{type}", method = {RequestMethod.GET})
    public ModelAndView selectTagsOrType(HttpServletRequest request, @PathVariable("type") String type) {
        ModelAndView mav = new ModelAndView(SELECT_TAGS_PAGE);
        mav.addObject("selectType", type);
        return mav;
    }


    /**
     * 打开图书列表List
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addDel", method = {RequestMethod.GET})
    public ModelAndView addDel(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(LIST_PAGE);
        return mav;
    }

}
