package cn.com.evo.cms.web.controller.content;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.AccountRole;
import cn.com.evo.admin.manage.domain.entity.DictData;
import cn.com.evo.admin.manage.service.AccountRoleService;
import cn.com.evo.admin.manage.service.DictDataService;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.entity.cms.Picture;
import cn.com.evo.cms.domain.entity.cms.Tree;
import cn.com.evo.cms.domain.entity.pay.LimitFree;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.enums.*;
import cn.com.evo.cms.domain.vo.cms.ColumnVo;
import cn.com.evo.cms.domain.vo.cms.SourcePictureVo;
import cn.com.evo.cms.service.cms.AuxiliaryFallTemplateService;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.cms.SourceRelService;
import cn.com.evo.cms.service.pay.LimitFreeService;
import cn.com.evo.cms.service.pay.ProductRelService;
import cn.com.evo.cms.web.voService.ColumnVoService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/columnManage/column")
public class ColumnController extends BaseController {
    private static final String SELECT_PAGE = "cms/contentManage/column/select";
    private static final String SITE_PAGE = "cms/contentManage/column/site";
    private static final String FORM_PAGE = "cms/contentManage/column/form";
    /**
     * 栏目图片form页面
     */
    private static final String PICTURE_PAGE = "cms/contentManage/column/picture/form";
    /**
     * 附属信息空白页
     */
    private static final String BLANK_PAGE = "cms/contentManage/column/blank";
    /**
     * 专题附属信息form页
     */
    private static final String SECTION_FORM_PAGE = "cms/contentManage/column/sectionTemplate/form";
    /**
     * 瀑布流模版附属信息form页
     */
    private static final String FALLTEMPLATE_FORM_PAGE = "cms/contentManage/column/fallTemplate/form";

    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private AuxiliaryFallTemplateService auxiliaryFallTemplateService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private DictDataService dictDataService;
    @Autowired
    private ProductRelService productRelService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private LimitFreeService limitFreeService;

    @Autowired
    private SourceVoService sourceVoService;
    @Autowired
    private ColumnVoService columnVoService;

    @Autowired
    private Mapper mapper;

    protected ColumnService getService() {
        return columnService;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public ModelAndView select() {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/site/{id}", method = RequestMethod.GET)
    public ModelAndView site(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(SITE_PAGE);
        mav.addObject("roleId", id);
        return mav;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:search"})
    @RequestMapping(value = "/list/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage, @PathVariable("id") String id) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Column> specification = DynamicSpecifications.bySearchFilter(request, Column.class, null);
            List<Column> entities = getService().findByCondition(specification, webPage);
            List<ColumnVo> lstVo = Lists.newArrayList();
            for (Column entity : entities) {
                ColumnVo vo = mapper.map(entity, ColumnVo.class);
                List<CatalogueRelation> catalogueRelations = catalogueRelationService.findByAIdAndType(id, 1);
                for (CatalogueRelation catalogueRelation : catalogueRelations) {
                    if (entity.getId().equals(catalogueRelation.getBId())) {
                        vo.setAssign("YES");
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

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/add/{columnPId}", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request, @PathVariable("columnPId") String columnPId) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        mav.addObject("columnPId", columnPId);
        mav.addObject("type", 1);
        mav.addObject("columnTypes", getColumnType(columnPId));
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:add"})
    @RequestMapping(value = "/add/{columnPId}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(@RequestBody Map<String, Object> map, @PathVariable("columnPId") String columnPId) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().save(map.get("main"), map.get("other"), columnPId);
            msgRet.pushOk("添加成功！");
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        try {
            // 获取栏目实体
            Column entity = getService().findById(id);
            String pId = entity.getParent() == null ? "0" : entity.getParent().getId();
            // 获取栏目背景图
            Picture bgPicture = sourceRelService.getByFIdAndBuinessTypeAndRelTypeAndSourceType(id,
                    BusinessTypeEnum.background.getIndex(), SourceRelTypeEnum.columnRel.getIndex(),
                    SourceTypeEnum.image.getIndex());
            // 获取栏目封面图
            Picture coverPicture = sourceRelService.getByFIdAndBuinessTypeAndRelTypeAndSourceType(id,
                    BusinessTypeEnum.cover.getIndex(), SourceRelTypeEnum.columnRel.getIndex(), SourceTypeEnum.image.getIndex());

            // 获取该栏目下挂载的内容数
            List<CatalogueRelation> catalogueRelations = catalogueRelationService.findByAIdAndType(id, CatalogueRelationType.columnsContentRel.getIndex());

            // 获取栏目下相关产品定价
            List<Product> products = productRelService.findByBizIdAndTypeAndIsShow(id, 1, 1);

            // 栏目下相关限免信息
            String appId = columnService.getAppId(id);
            LimitFree limitFree = limitFreeService.getByBizIdAndAppId(id, appId);

            mav.addObject("contentSize", catalogueRelations.size());
            mav.addObject("entity", entity);
            mav.addObject("type", 2);
            mav.addObject("columnPId", pId);
            mav.addObject("bgPicture", bgPicture);
            mav.addObject("coverPicture", coverPicture);
            mav.addObject("columnTypes", getColumnType(pId));
            mav.addObject("products", products);
            mav.addObject("limitFree", limitFree);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(@RequestBody Map<String, Object> map, @PathVariable("id") String id) {
        MsgResult msgRet = new MsgResult();
        try {
            Column entity = getService().findById(id);
            String pId = entity.getParent() == null ? "0" : entity.getParent().getId();
            getService().update(map.get("main"), map.get("other"), pId);
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:remove"})
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

    @RunLogger(value = "是否为超管", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:modify"})
    @RequestMapping(value = "/admin", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult admin() {
        MsgResult msgRet = new MsgResult();
        try {
            boolean falg = false;
            List<AccountRole> accountRoles = accountRoleService.findByAccountId(getLoginUser().getId());
            for (AccountRole accountRole : accountRoles) {
                if (accountRole.getRole().getCode().equals("Super_Admin")) {
                    falg = true;
                }
            }
            if (falg) {
                msgRet.pushOk("是超管!");
            } else {
                msgRet.pushError("无权限");// 站点 只能超管 操作
            }
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "为角色分配站点", isSaveRequest = true)
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/assign", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MsgResult assign(String roleId, @RequestParam(value = "siteIds[]", required = false) String[] siteIds) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().assign(roleId, siteIds);
            msgRet.pushOk("分配站点成功！");
        } catch (Exception e) {
            msgRet.pushError("分配站点失败：" + e.getMessage());
            logger.error("分配站点时，发生异常！", e);
        }
        return msgRet;
    }

    /**
     * 获取内容目录树
     *
     * @return
     */
    @RequestMapping(value = "/columnsTree", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Tree> columnsTree() {
        List<Tree> trees = Lists.newArrayList();
        try {
            // 创建根目录
            Tree baseTree = new Tree("0", null, "全部站点", 0, null, ColumnsAuxiliaryType.root.getIndex());
            trees.add(baseTree);
            Account account = getLoginUser();
            List<Column> sites = columnService.findByAccount(account);// 获取当前登录账号下的所有站点
            for (Column site : sites) {// 生成内容目录树
                Tree tree = new Tree(site.getId(), "0", site.getName(), 0, site.getId(),
                        ColumnsAuxiliaryType.webSite.getIndex());
                trees.add(tree);

                String siteId = site.getId();
                List<Column> list = columnService.findAllColumnBySiteId(siteId);
                for (Column column : list) {
                    if (!(column.getId()).equals(siteId)) {
                        Column coluParent = column.getParent();
                        String pId = "";
                        if (coluParent == null) {
                            pId = siteId;
                        } else {
                            pId = coluParent.getId();
                        }
                        Tree coluTree = new Tree(column.getId(), pId, column.getName(), 0, siteId, column.getType());
                        trees.add(coluTree);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trees;
    }

    /**
     * 打开图片信息页
     *
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/openPicture/{columnId}", method = {RequestMethod.GET})
    public ModelAndView openPicture(@PathVariable("columnId") String columnId) {
        ModelAndView mav = new ModelAndView(PICTURE_PAGE);
        mav.addObject("columnId", columnId);
        // 获取栏目图片资源
        List<SourcePictureVo> vos = sourceVoService.findPictureByFId(columnId, SourceTypeEnum.image.getIndex());
        mav.addObject("pictures", vos);
        return mav;
    }

    /**
     * 异步保存目录下挂载的图片信息
     */
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:add"})
    @RequestMapping(value = "/saveImages/{id}/{pId}", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult saveImages(@RequestBody Map<String, Object> map, @PathVariable("id") String id,
                                @PathVariable("pId") String pId) {
        MsgResult msgRet = new MsgResult();
        Map<String, Object> result = new HashMap<>();
        try {
            Column entity = new Column();
            // 判断 当前操作是目录新增还是目录修改
            if (Integer.valueOf(id) == 0) {// 新增
                entity.setParent(getService().findById(pId));
            } else {// 修改
                entity = getService().findById(id);
            }
            List<SourcePictureVo> vos = columnVoService.saveImages(map, entity);
            result.put("pictures", vos);
            result.put("columnId", entity.getId());
            msgRet.setExtra(result);
            msgRet.pushOk("添加成功!");
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    /**
     * 打开附属信息空白页
     *
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/openBlank", method = {RequestMethod.GET})
    public ModelAndView openBlank() {
        ModelAndView mav = new ModelAndView(BLANK_PAGE);
        return mav;
    }

    /**
     * 打开瀑布流模版附属信息页
     *
     * @param request
     * @param columnId
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/openTemplate/{columnId}", method = {RequestMethod.GET})
    public ModelAndView openTemplate(HttpServletRequest request, @PathVariable("columnId") String columnId) {
        ModelAndView mav = new ModelAndView(FALLTEMPLATE_FORM_PAGE);
        mav.addObject("columnId", columnId);
        mav.addObject("auxiliaryFallTemplate", auxiliaryFallTemplateService.getByColumnId(columnId));
        return mav;
    }

    /**
     * 打开专题模版附属信息页
     *
     * @param request
     * @param columnId
     * @return
     */
    @RequiresPermissions(value = {"ContentCenter:ContentManage:CatalogueRelation:show"})
    @RequestMapping(value = "/openSection/{columnId}", method = {RequestMethod.GET})
    public ModelAndView openSection(HttpServletRequest request, @PathVariable("columnId") String columnId) {
        ModelAndView mav = new ModelAndView(SECTION_FORM_PAGE);
        mav.addObject("columnId", columnId);
        return mav;
    }

    /**
     * 获取栏目类型list
     *
     * @param columnPId
     * @return
     */
    public List<DictData> getColumnType(String columnPId) {
        // 加入栏目类型的校验
        // 栏目类型
        List<DictData> dictDatas = dictDataService.findByDictClassifyCode("Column_Type");
        if ("0".equals(columnPId)) {
            // 全部站点时，只能添加站点
            DictData data = dictDatas.get(0);
            List<DictData> temp = Lists.newArrayList();
            temp.add(data);
            return temp;
        } else {
            // 非全部站点时，不能添加站点
            Column pColumn = columnService.findById(columnPId);
            dictDatas.remove(0);
            return dictDatas;
        }
    }

    @RequestMapping(value = "/columnsTreeBySelect", method = {RequestMethod.POST}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Tree> columnsTreeBySelect() {
        List<Tree> trees = Lists.newArrayList();
        List<Column> columns = getService().findAll();
        for (Column column : columns) {
            Column parent = column.getParent();
            String pId = "";
            if (parent != null) {
                pId = parent.getId();
            } else {
                pId = null;
            }
            Tree temp = new Tree(column.getId(), pId, column.getName(), column.getLevel());
            trees.add(temp);
        }
        return trees;
    }

    @RequestMapping(value = "/listBySelect", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Column> specification = DynamicSpecifications.bySearchFilter(request, Column.class,
                    null);
            List<Column> Columns = getService().findByCondition(specification, webPage);
            List<ColumnVo> lstVo = Lists.newArrayList();
            for (Column Column : Columns) {
                ColumnVo vo = mapper.map(Column, ColumnVo.class);
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


    /**
     * 设置推荐或者取消推荐
     *
     * @param columnId
     * @param type
     * @return
     */
    @RequestMapping(value = "/setRecommend", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult setRecommend(@RequestParam("columnId") String columnId,
                                   @RequestParam("type") Integer type) {
        DataResult dataRet = new DataResult();
        try {
            columnService.setRecommend(columnId, type);
            dataRet.pushOk("设置推荐或者取消推荐成功！");
        } catch (Exception e) {
            dataRet.pushError("设置推荐或者取消推荐失败！");
            logger.error("设置推荐或者取消推荐异常！", e);
        }
        return dataRet;
    }
}
