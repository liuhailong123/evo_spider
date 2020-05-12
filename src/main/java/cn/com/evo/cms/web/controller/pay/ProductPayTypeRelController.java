package cn.com.evo.cms.web.controller.pay;

import cn.com.evo.cms.domain.entity.pay.ProductPayTypeRel;
import cn.com.evo.cms.domain.vo.pay.ProductPayTypeRelVo;
import cn.com.evo.cms.service.pay.PayConfigService;
import cn.com.evo.cms.service.pay.ProductPayTypeRelService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/productCharge/payTypeRel")
public class ProductPayTypeRelController extends BaseController {

    @Autowired
    private ProductPayTypeRelService productPayTypeRelService;
    @Autowired
    private PayConfigService payConfigService;

    @Autowired
    private Mapper mapper;

    protected ProductPayTypeRelService getService() {
        return productPayTypeRelService;
    }

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<ProductPayTypeRel> specification = DynamicSpecifications.bySearchFilter(request,
                    ProductPayTypeRel.class, null);
            List<ProductPayTypeRel> entities = getService().findByCondition(specification, webPage);
            List<ProductPayTypeRelVo> lstVo = Lists.newArrayList();
            for (ProductPayTypeRel entity : entities) {
                ProductPayTypeRelVo vo = mapper.map(entity, ProductPayTypeRelVo.class);
                vo.setConfig(payConfigService.findById(vo.getConfigId()));
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

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(@RequestParam("configId") String configId,
                           @RequestParam("productId") String productId) {
        MsgResult msgRet = new MsgResult();
        try {
            ProductPayTypeRel rel = new ProductPayTypeRel();
            rel.setConfigId(configId);
            rel.setProductId(productId);
            getService().save(rel);
            msgRet.pushOk("添加成功！");
        } catch (Exception e) {
            msgRet.pushError("添加失败：" + e.getMessage());
            logger.error("添加时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:remove"})
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
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:remove"})
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
