package cn.com.evo.cms.web.controller.total;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.total.ContentTotal;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.domain.vo.total.ContentTotalSearchVo;
import cn.com.evo.cms.domain.vo.total.ContentTotalVo;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.total.ContentTotalService;
import cn.com.evo.cms.service.vip.UserAccountService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/total/contentTotal")
public class ContentTotalController extends BaseController {
    private static final String VIEW_PAGE = "cms/total/contentTotal/view";

    @Autowired
    private ContentTotalService contentTotalService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;

    @Autowired
    private Mapper mapper;

    protected ContentTotalService getService() {
        return contentTotalService;
    }

    @RequiresPermissions(value = {"Total:ContentTotal:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequiresPermissions(value = {"Total:ContentTotal:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            List<ContentTotal> entities = Lists.newArrayList();
            ContentTotalSearchVo contentTotalSearchVo = new ContentTotalSearchVo(request);

            if (StringUtils.isNotBlank(contentTotalSearchVo.getAccountNo())) {
                // 自定义查询
                entities = contentTotalService.findByPage(webPage, contentTotalSearchVo);
            } else {
                // 默认查询方式
                Specification<ContentTotal> specification = DynamicSpecifications.bySearchFilter(request, ContentTotal.class, null);
                entities = getService().findByCondition(specification, webPage);
            }

            // 数据处理
            List<ContentTotalVo> lstVo = Lists.newArrayList();
            for (ContentTotal entity : entities) {
                ContentTotalVo vo = mapper.map(entity, ContentTotalVo.class);

                if (StringUtils.isNotBlank(entity.getBizValue())) {
                    CatalogueRelation catalogueRelation = catalogueRelationService.findById(entity.getBizValue());
                    if (catalogueRelation != null) {
                        Content content = contentService.findById(catalogueRelation.getBId());
                        vo.setBizValue(content.getName());
                    } else {
                        vo.setBizValue("");
                    }
                }
                if (StringUtils.isNotBlank(entity.getUserId())) {
                    UserAccount userAccount = userAccountService.getUserIdAndAccountType(entity.getUserId(), 3);
                    vo.setAccountNo(userAccount.getAccountNo());
                } else {
                    vo.setAccountNo("");
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

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"Total:ContentTotal:remove"})
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
    @RequiresPermissions(value = {"Total:ContentTotal:remove"})
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
