package cn.com.evo.cms.web.controller.content;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.domain.entity.book.BookInfo;
import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.entity.pay.LimitFree;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.spider.SpiderContent;
import cn.com.evo.cms.domain.enums.CatalogueRelationType;
import cn.com.evo.cms.domain.enums.ContentTypeEnum;
import cn.com.evo.cms.domain.enums.SourceTypeEnum;
import cn.com.evo.cms.domain.vo.cms.CatalogueRelationVo;
import cn.com.evo.cms.domain.vo.cms.ContentSearchVo;
import cn.com.evo.cms.domain.vo.cms.PublishContentVo;
import cn.com.evo.cms.service.book.BookInfoService;
import cn.com.evo.cms.service.cms.*;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.service.pay.ProductRelService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/contentManage/catalogueRelation")
public class CatalogueRelationController extends BaseController {
    private static final String VIEW_PAGE = "cms/contentManage/catalogueRelation/view";
    private static final String FORM_PAGE = "cms/contentManage/catalogueRelation/form";
    private static final String SELECT_PAGE = "cms/contentManage/catalogueRelation/select";
    private static final String SECTION_FORM_PAGE = "cms/contentManage/catalogueRelation/section/form";
    private static final String SET_TOP_FORM_PAGE = "cms/contentManage/catalogueRelation/setTop";


    @Autowired
    private ContentService contentService;
    @Autowired
    private ProductRelService productRelService;
    @Autowired
    private LimitFreeService limitFreeService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private ActiveService activeService;
    @Autowired
    private LliveBroadcastService lliveBroadcastService;
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private SpiderContentService spiderContentService;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ProvinceService provinceService;

    protected CatalogueRelationService getService() {
        return catalogueRelationService;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/select/{type}", method = {RequestMethod.GET})
    public ModelAndView select(HttpServletRequest request, @PathVariable("type") Integer type) {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        mav.addObject("type", type);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        List<PublishContentVo> lstVo = Lists.newArrayList();
        // 请求搜索数据转换
        ContentSearchVo contentSearchVo = new ContentSearchVo(request);
        try {
            if (contentSearchVo.getContentType() != null) {
                // 根据内容名称和内容类型检索
                ContentTypeEnum contentTypeEnum = ContentTypeEnum.val(contentSearchVo.getContentType());
                switch (contentTypeEnum) {
                    case movie:
                        lstVo = catalogueRelationService.findMovieAndEpisodeContent(webPage, contentSearchVo);
                        break;
                    case episode:
                        lstVo = catalogueRelationService.findMovieAndEpisodeContent(webPage, contentSearchVo);
                        break;
                    case live:
                        lstVo = catalogueRelationService.findLiveContent(webPage, contentSearchVo);
                        break;
                    case active:
                        lstVo = catalogueRelationService.findActiveContent(webPage, contentSearchVo);
                        break;
                    case book:
                        lstVo = catalogueRelationService.findBookContent(webPage, contentSearchVo);
                        break;
                    case column:
                        lstVo = catalogueRelationService.findColumnContent(webPage, contentSearchVo);
                        break;
                    case spiderContent:
                        lstVo = catalogueRelationService.findSpiderContent(webPage, contentSearchVo);
                        break;
                    default:
                        break;
                }

                dataRet.setRows(lstVo);
            } else {
                // 内容分类为空，默认查询方法
                Specification<CatalogueRelation> specification = DynamicSpecifications.bySearchFilter(request,
                        CatalogueRelation.class, null);
                List<CatalogueRelation> entities = getService().findByCondition(specification, webPage);
                for (CatalogueRelation entity : entities) {
                    ContentTypeEnum contentTypeEnum = ContentTypeEnum.val(entity.getContentType());
                    switch (contentTypeEnum) {
                        case movie:
                            Content movie = contentService.findById(entity.getBId());
                            if (movie != null) {
                                PublishContentVo vo = new PublishContentVo(entity, movie);
                                Column column = columnService.findById(entity.getAId());
                                vo.setColumnName(column.getName());
                                lstVo.add(vo);
                            } else {
                                logger.error("该电影内容：" + entity.getBId() + " 没有对应的内容数据!!!");
                            }
                            break;
                        case episode:
                            Content episode = contentService.findById(entity.getBId());
                            if (episode != null) {
                                PublishContentVo vo = new PublishContentVo(entity, episode);
                                lstVo.add(vo);
                            } else {
                                logger.error("该剧集内容：" + entity.getBId() + " 没有对应的内容数据!!!");
                            }
                            break;
                        case live:
                            LliveBroadcast lliveBroadcast = lliveBroadcastService.findById(entity.getBId());
                            if (lliveBroadcast != null) {
                                PublishContentVo vo = new PublishContentVo(entity, lliveBroadcast);
                                lstVo.add(vo);
                            } else {
                                logger.error("该直播内容：" + entity.getBId() + " 没有对应的内容数据!!!");
                            }
                            break;
                        case active:
                            Active active = activeService.findById(entity.getBId());
                            if (active != null) {
                                PublishContentVo vo = new PublishContentVo(entity, active);
                                lstVo.add(vo);
                            } else {
                                logger.error("该活动内容：" + entity.getBId() + " 没有对应的内容数据!!!");
                            }
                            break;
                        case book:
                            BookInfo bookInfo = bookInfoService.findById(entity.getBId());
                            if (bookInfo != null) {
                                PublishContentVo vo = new PublishContentVo(entity, bookInfo);
                                lstVo.add(vo);
                            } else {
                                logger.error("该图书内容：" + entity.getBId() + " 没有对应的内容数据!!!");
                            }
                            break;
                        case column:
                            Column column = columnService.findById(entity.getBId());
                            if (column != null) {
                                PublishContentVo vo = new PublishContentVo(entity, column);
                                lstVo.add(vo);
                            } else {
                                logger.error("该栏目内容：" + entity.getBId() + " 没有对应的内容数据!!!");
                            }
                            break;
                        case spiderContent:
                            SpiderContent content = spiderContentService.findById(entity.getBId());
                            if (content != null) {
                                PublishContentVo vo = new PublishContentVo(entity, content);
                                lstVo.add(vo);
                            } else {
                                logger.error("该文章内容：" + entity.getBId() + " 没有对应的内容数据!!!");
                            }
                            break;
                        default:
                            logger.error("内容类型错误：" + entity.toString());
                            break;
                    }
                }
                dataRet.setRows(lstVo);
            }

            dataRet.pushOk("获取数据列表成功！");
            dataRet.setTotal(webPage.getTotalCount());
        } catch (Exception e) {
            dataRet.pushError("获取数据列表失败！");
            logger.error("获取数据列表异常！", e);
        }
        return dataRet;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        try {
            CatalogueRelation entity = getService().findById(id);
            CatalogueRelationVo vo = mapper.map(entity, CatalogueRelationVo.class);
            ContentTypeEnum contentTypeEnum = ContentTypeEnum.val(entity.getContentType());
            switch (contentTypeEnum) {
                case live:
                    LliveBroadcast live = lliveBroadcastService.findById(entity.getBId());
                    vo.setName(live.getName());
                    break;
                case active:
                    Active active = activeService.findById(entity.getBId());
                    vo.setName(active.getName());
                    break;
                case book:
                    BookInfo bookInfo = bookInfoService.findById(entity.getBId());
                    vo.setName(bookInfo.getName());
                    break;
                case column:
                    Column column = columnService.findById(entity.getBId());
                    vo.setName(column.getName());
                    break;
                case spiderContent:
                    SpiderContent spiderContent = spiderContentService.findById(entity.getBId());
                    vo.setName(spiderContent.getTitle());
                    break;
                default:
                    Content content = contentService.findById(entity.getBId());
                    vo.setName(content.getName());
                    break;
            }
            mav.addObject("entity", vo);

            // 获取栏目下相关产品定价
            List<Product> products = productRelService.findByBizId(id);

            // 栏目下相关限免信息
            String appId = columnService.getAppId(entity.getAId());
            LimitFree limitFree = limitFreeService.getByBizIdAndAppId(entity.getBId(), appId);
            mav.addObject("products", products);
            mav.addObject("limitFree", limitFree);
        }catch (Exception e){
            e.printStackTrace();
        }

        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(CatalogueRelation entity) {
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

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/add/{columnId}", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request, @PathVariable("columnId") String columnId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        CatalogueRelation entity = new CatalogueRelation();
        entity.setAId(columnId);
        entity.setType(CatalogueRelationType.columnsContentRel.getIndex());
        CatalogueRelationVo vo = mapper.map(entity, CatalogueRelationVo.class);
        List<CatalogueRelation> catalogueRelations = getService().findByAIdAndType(columnId, CatalogueRelationType.columnsContentRel.getIndex());
        Integer sort = 1;
        if (catalogueRelations != null) {
            if (catalogueRelations.size() > 0) {
                sort = catalogueRelations.size() + 1;
            }
        }
        vo.setSort(sort);
        mav.addObject("entity", vo);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:add"})
    @RequestMapping(value = "/add/{columnId}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult store(CatalogueRelation entity) {
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

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:remove"})
    @RequestMapping(value = "/remove/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult remove(@PathVariable("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.deleteById(id);
            msgRet.pushOk("删除成功！");
        } catch (Exception e) {
            msgRet.pushError("删除失败：" + e.getMessage());
            logger.error("删除时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "批量删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:remove"})
    @RequestMapping(value = "/remove", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult remove(@RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.deleteByIds(ids);
            msgRet.pushOk("批量删除成功!");
        } catch (Exception e) {
            msgRet.pushError("批量删除失败：" + e.getMessage());
            logger.error("批量删除时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "发布", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/release/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult release(@PathVariable("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.release(id);
            msgRet.pushOk("发布成功！");
        } catch (Exception e) {
            msgRet.pushError("发布失败：" + e.getMessage());
            logger.error("发布时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "批量发布", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/release", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult release(@RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.release(ids);
            msgRet.pushOk("批量发布成功!");
        } catch (Exception e) {
            msgRet.pushError("批量发布失败：" + e.getMessage());
            logger.error("批量发布时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "取消发布", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/releaseNo/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult releaseNo(@PathVariable("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.releaseNo(id);
            msgRet.pushOk("取消发布成功！");
        } catch (Exception e) {
            msgRet.pushError("取消发布失败：" + e.getMessage());
            logger.error("取消发布时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "批量取消发布", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/releaseNo", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult releaseNo(@RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.releaseNo(ids);
            msgRet.pushOk("批量取消发布成功!");
        } catch (Exception e) {
            msgRet.pushError("批量取消发布失败：" + e.getMessage());
            logger.error("批量取消发布时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "修改内容排序", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/changeSort", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult changeSort(@RequestParam("id") String id, @RequestParam("sort") Integer sort) {
        MsgResult msgRet = new MsgResult();
        try {
//             getService().changeSort(id, sort);
            msgRet.pushOk("修改内容排序成功！");
        } catch (Exception e) {
            msgRet.pushError("修改内容排序失败：" + e.getMessage());
            logger.error("修改内容排序时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:search"})
    @RequestMapping(value = "/listSectionContent", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult listSectionContent(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<CatalogueRelation> specification = DynamicSpecifications.bySearchFilter(request,
                    CatalogueRelation.class, null);
            List<CatalogueRelation> entities = getService().findByCondition(specification, webPage);
            List<PublishContentVo> lstVo = Lists.newArrayList();
            //获取当前省网资源服务器配置
            Province provice = provinceService.getByEnable(1);
            for (CatalogueRelation entity : entities) {
                switch (entity.getContentType()) {
                    case 3:
                        break;
                    default:// 视频内容
                        Content content = contentService.findById(entity.getBId());
                        if (content != null) {
                            PublishContentVo vo = new PublishContentVo(entity, content);
                            List<SourceRel> sourceRels = sourceRelService.findByFId(content.getId(),
                                    SourceTypeEnum.image.getIndex());
                            if (sourceRels.size() > 0) {
                                Picture picture = pictureService.findById(sourceRels.get(0).getSourceId());
                                vo.setPreviewUrl(provice.getImageHost() + picture.getFileName());
                            }
                            lstVo.add(vo);
                        }
                        break;
                }
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

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/editSectionContent/{id}", method = {RequestMethod.GET})
    public ModelAndView editSectionContent(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(SECTION_FORM_PAGE);
        CatalogueRelation entity = getService().findById(id);
        CatalogueRelationVo vo = mapper.map(entity, CatalogueRelationVo.class);
        Content content = contentService.findById(entity.getBId());
        vo.setName(content.getName());
        mav.addObject("entity", vo);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/editSectionContent", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modifySectionContent(CatalogueRelation entity) {
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

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/addSectionContent/{sectionId}", method = {RequestMethod.GET})
    public ModelAndView addSectionContent(HttpServletRequest request, @PathVariable("sectionId") String sectionId) {
        ModelAndView mav = new ModelAndView(SECTION_FORM_PAGE);
        CatalogueRelation entity = new CatalogueRelation();
        entity.setAId(sectionId);
        entity.setType(CatalogueRelationType.columnsContentRel.getIndex());
        CatalogueRelationVo vo = mapper.map(entity, CatalogueRelationVo.class);
        mav.addObject("entity", vo);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:add"})
    @RequestMapping(value = "/addSectionContent/{sectionId}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult storeSectionContent(CatalogueRelation entity) {
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

    @RunLogger(value = "设置内容推荐或者取消内容推荐", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/setRecommend", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult setRecommend(@RequestParam("type") Integer type,
                                  @RequestParam("ids[]") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.setRecommend(ids, type);
            msgRet.pushOk("设置内容推荐或者取消内容推荐成功!");
        } catch (Exception e) {
            msgRet.pushError("设置内容推荐或者取消内容推荐失败：" + e.getMessage());
            logger.error("设置内容推荐或者取消内容推荐时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/setTop/{id}", method = {RequestMethod.GET})
    public ModelAndView setTopForm(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(SET_TOP_FORM_PAGE);
        mav.addObject("id", id);
        return mav;
    }

    @RunLogger(value = "置顶", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/setTop", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult setTop(@RequestParam("id") String id,
                            @RequestParam("type") Integer type,
                            @RequestParam(value = "index", required = false) Integer index) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.setSort(id, type, index);
            msgRet.pushOk("设置置顶成功!");
        } catch (Exception e) {
            msgRet.pushError("设置置顶失败：" + e.getMessage());
            logger.error("设置置顶时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "自动挂载", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/autoRel", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult autoRel(@RequestParam("columnId") String columnId) {
        MsgResult msgRet = new MsgResult();
        try {
            catalogueRelationService.autoRel(columnId);
            msgRet.pushOk("设置置顶成功!");
        } catch (Exception e) {
            msgRet.pushError("设置置顶失败：" + e.getMessage());
            logger.error("设置置顶时，发生异常！", e);
        }
        return msgRet;
    }
}
