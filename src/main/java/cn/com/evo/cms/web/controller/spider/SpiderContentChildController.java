//package cn.com.evo.cms.web.controller.spider;
//
//import cn.com.evo.admin.manage.domain.entity.Organization;
//import cn.com.evo.cms.domain.entity.spider.SpiderContentChild;
//import cn.com.evo.cms.domain.vo.spider.SpiderContentChildVo;
//import cn.com.evo.cms.service.spider.SpiderContentChildService;
//import com.frameworks.core.logger.annotation.RunLogger;
//import com.frameworks.core.web.controller.BaseController;
//import com.frameworks.core.web.page.Pager;
//import com.frameworks.core.web.result.DataResult;
//import com.frameworks.core.web.result.MsgResult;
//import com.frameworks.core.web.search.DynamicSpecifications;
//import com.google.common.collect.Lists;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.dozer.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.sql.rowset.serial.SerialBlob;
//import java.io.UnsupportedEncodingException;
//import java.sql.Blob;
//import java.sql.SQLException;
//import java.util.List;
//
//@Controller
//@RequestMapping("/sourceManage/SpiderContentChild")
//public class SpiderContentChildController extends BaseController {
//
//    private static final String FORM_PAGE = "cms/sourceManage/spiderContentChild/form";
//    private static final String SELECT_TAGS_PAGE = "cms/sourceManage/spiderContentChild/selectTags";
//    private static final String BOOK_LIST = "cms/sourceManage/spiderContentChild/List";
//    private static final String SELECT_PAGE = "cms/sourceManage/spiderContentChild/select";
//    private static final String DETAIL_PAGE = "cms/sourceManage/spiderContentChild/detail";
//    @Autowired
//    private SpiderContentChildService spiderContentChildService;
//
//    @Autowired
//    private Mapper mapper;
//
//    protected SpiderContentChildService getService() {
//        return spiderContentChildService;
//    }
//
//    /**
//     * 详情
//     *
//     * @param request
//     * @return
//     */
//    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContentChild:show"})
//    @RequestMapping(value = "/detail/{id}", method = {RequestMethod.GET})
//    public ModelAndView detail(HttpServletRequest request, @PathVariable("id") String id) {
//        ModelAndView mav = new ModelAndView(DETAIL_PAGE);
//        SpiderContentChild entity = getService().findById(id);
//        SpiderContentChildVo vo = mapper.map(entity, SpiderContentChildVo.class);
//        mav.addObject("entity", vo);
//        return mav;
//    }
//
//    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContentChild:search"})
//    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public DataResult list(HttpServletRequest request, Pager webPage) {
//        DataResult dataRet = new DataResult();
//        Organization org = getLoginUser().getOrganization();
//        try {
//            Specification<SpiderContentChild> specification = DynamicSpecifications.bySearchFilter(request, SpiderContentChild.class, null);
//            List<SpiderContentChild> entities = getService().findByCondition(specification, webPage);
//            List<SpiderContentChildVo> lstVo = Lists.newArrayList();
//            for (SpiderContentChild entity : entities) {
//                SpiderContentChildVo vo = mapper.map(entity, SpiderContentChildVo.class);
//                lstVo.add(vo);
//            }
//            dataRet.pushOk("获取数据列表成功！");
//            dataRet.setTotal(webPage.getTotalCount());
//            dataRet.setRows(lstVo);
//        } catch (Exception e) {
//            dataRet.pushError("获取数据列表失败！");
//            logger.error("获取数据列表异常！", e);
//        }
//        return dataRet;
//    }
//
//    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContentChild:show"})
//    @RequestMapping(value = "/add/{fId}", method = {RequestMethod.GET})
//    public ModelAndView add(HttpServletRequest request, @PathVariable("fId") String fId) {
//        ModelAndView mav = new ModelAndView(FORM_PAGE);
//        SpiderContentChild entity = new SpiderContentChild();
//        entity.setfId(fId);
//        mav.addObject("entity", entity);
//        return mav;
//    }
//
//    @RunLogger(value = "添加", isSaveRequest = true)
//    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContentChild:add"})
//    @RequestMapping(value = "/add/{fId}", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public MsgResult store(SpiderContentChild entity) {
//        MsgResult msgRet = new MsgResult();
//        try {
//            getService().save(entity);
//            msgRet.pushOk("添加成功！");
//        } catch (Exception e) {
//            msgRet.pushError("添加失败：" + e.getMessage());
//            logger.error("添加时，发生异常！", e);
//        }
//        return msgRet;
//    }
//
//    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContentChild:show"})
//    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
//    public ModelAndView edit(@PathVariable("id") String id) {
//        ModelAndView mav = new ModelAndView(FORM_PAGE);
//        SpiderContentChild entity = getService().findById(id);
//        mav.addObject("entity", entity);
//        return mav;
//    }
//
//    @RunLogger(value = "编辑", isSaveRequest = true)
//    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContentChild:modify"})
//    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public MsgResult modify(SpiderContentChild entity) {
//        MsgResult msgRet = new MsgResult();
//        try {
//            getService().update(entity);
//            msgRet.pushOk("修改成功!");
//        } catch (Exception e) {
//            msgRet.pushError("修改失败：" + e.getMessage());
//            logger.error("修改时，发生异常！", e);
//        }
//        return msgRet;
//    }
//
//    @RunLogger(value = "删除", isSaveRequest = true)
//    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContentChild:remove"})
//    @RequestMapping(value = "/remove/{id}", method = {RequestMethod.POST}, produces = {
//            MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public MsgResult remove(@PathVariable("id") String id) {
//        MsgResult msgRet = new MsgResult();
//        try {
//            getService().deleteById(id);
//            msgRet.pushOk("删除成功！");
//        } catch (Exception e) {
//            msgRet.pushError("删除失败：" + e.getMessage());
//            logger.error("删除时，发生异常！", e);
//        }
//        return msgRet;
//    }
//
//    @RunLogger(value = "批量删除", isSaveRequest = true)
//    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:SpiderContentChild:remove"})
//    @RequestMapping(value = "/remove", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public MsgResult remove(@RequestParam("ids[]") String[] ids) {
//        MsgResult msgRet = new MsgResult();
//        try {
//            getService().deleteByIds(ids);
//            msgRet.pushOk("批量删除成功!");
//        } catch (Exception e) {
//            msgRet.pushError("批量删除失败：" + e.getMessage());
//            logger.error("批量删除时，发生异常！", e);
//        }
//        return msgRet;
//    }
//
//    @RequestMapping(value = "/addTags", method = {RequestMethod.GET})
//    public ModelAndView addTags(HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView(SELECT_TAGS_PAGE);
//        return mav;
//    }
//
//
//    @RequestMapping(value = "/selectTagsOrType/{type}", method = {RequestMethod.GET})
//    public ModelAndView selectTagsOrType(HttpServletRequest request, @PathVariable("type") String type) {
//        ModelAndView mav = new ModelAndView(SELECT_TAGS_PAGE);
//        mav.addObject("selectType", type);
//        return mav;
//    }
//
//
//    /**
//     * 打开图书列表List
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/addDel", method = {RequestMethod.GET})
//    public ModelAndView addDel(HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView(BOOK_LIST);
//        return mav;
//    }
//
//}
