package cn.com.evo.cms.web.controller.book;

import cn.com.evo.admin.manage.domain.entity.Organization;
import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.vo.book.BookInfoVo;
import cn.com.evo.cms.service.book.BookInfoService;
import cn.com.evo.cms.service.book.BookPictureService;
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

@Controller
@RequestMapping("/sourceManage/book")
public class BookInfoController extends BaseController {

    private static final String VIEW_PAGE = "cms/sourceManage/book/view";
    private static final String FORM_PAGE = "cms/sourceManage/book/form";
    private static final String SELECT_TAGS_PAGE = "cms/sourceManage/book/selectTags";
    private static final String BOOK_LIST = "cms/sourceManage/book/bookList";
    private static final String SELECT_PAGE = "cms/sourceManage/book/select";
    private static final String DETAIL_PAGE = "cms/sourceManage/book/detail";
    private static final String IMPORT_PAGE = "cms/sourceManage/book/import";
    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookPictureService bookPictureService;

    @Autowired
    private Mapper mapper;

    protected BookInfoService getService() {
        return bookInfoService;
    }

    /**
     * 详情
     *
     * @param request
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:show"})
    @RequestMapping(value = "/detail/{id}", method = {RequestMethod.GET})
    public ModelAndView detail(HttpServletRequest request, @PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(DETAIL_PAGE);
        BookInfo entity = getService().findById(id);
        BookInfoVo vo = mapper.map(entity, BookInfoVo.class);
        mav.addObject("entity", vo);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:show"})
    @RequestMapping(value = "/select", method = {RequestMethod.GET})
    public ModelAndView select() {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        Organization org = getLoginUser().getOrganization();
        try {
            Specification<BookInfo> specification = DynamicSpecifications.bySearchFilter(request, BookInfo.class, null);
            List<BookInfo> entities = getService().findByCondition(specification, webPage);
            List<BookInfoVo> lstVo = Lists.newArrayList();
            for (BookInfo entity : entities) {
                BookInfoVo vo = mapper.map(entity, BookInfoVo.class);
//                vo.setPictureUrl(getPictureByBookInfoId(entity.getId()));
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(BookInfo entity) {
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

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        BookInfo entity = getService().findById(id);
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(BookInfo entity) {
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:remove"})
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
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:remove"})
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


    @RunLogger(value = "改变图书海报启用状态", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:modify"})
    @RequestMapping(value = "/changeBookPictureEnable", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult changeBookPictureEnable(@RequestParam("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().changeBookPictureEnable(id);
            msgRet.pushOk("改变图书海报启用状态成功!");
        } catch (Exception e) {
            msgRet.pushError("改变图书海报启用状态失败：" + e.getMessage());
            logger.error("改变图书海报启用状态时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除图书海报关系信息", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:remove"})
    @RequestMapping(value = "/removeBookPicture", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult removeBookPicture(@RequestParam("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            bookPictureService.deleteById(id);
            msgRet.pushOk("删除成功!");
        } catch (Exception e) {
            msgRet.pushError("删除失败：" + e.getMessage());
            logger.error("删除时，发生异常！", e);
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
        ModelAndView mav = new ModelAndView(BOOK_LIST);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:modify"})
    @RequestMapping(value = "/importFile", method = {RequestMethod.GET})
    public ModelAndView importFile(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(IMPORT_PAGE);
        return mav;
    }

    @RunLogger(value = "excel书单导入", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:SourceConfig:Television:Book:modify"})
    @RequestMapping(value = "/importFile", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult importFile(
            @RequestParam(value = "bookInfoFile", required = false) MultipartFile[] bookInfoFile,
            @RequestParam(value = "pictureInfoFile", required = false) MultipartFile[] pictureInfoFile,
            @RequestParam(value = "relFile", required = false) MultipartFile[] relFile) {
        MsgResult msgRet = new MsgResult();
        try {
            if (bookInfoFile.length > 0) {
                getService().importFile(bookInfoFile, pictureInfoFile, relFile);
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

}
