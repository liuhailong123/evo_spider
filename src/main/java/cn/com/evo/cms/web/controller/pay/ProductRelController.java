package cn.com.evo.cms.web.controller.pay;

import cn.com.evo.admin.manage.domain.entity.DictClassify;
import cn.com.evo.admin.manage.domain.vo.DictClassifyVo;
import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.entity.pay.ProductRel;
import cn.com.evo.cms.domain.vo.pay.ProductRelVo;
import cn.com.evo.cms.service.pay.ProductRelService;
import cn.com.evo.cms.service.pay.ProductService;
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
@RequestMapping("/pay/productRel")
public class ProductRelController extends BaseController {

    @Autowired
    private ProductRelService productRelService;
    @Autowired
    private ProductService productService;

    @Autowired
    private Mapper mapper;

    protected ProductRelService getService() {
        return productRelService;
    }

    @RunLogger(value = "栏目右键添加定价", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(@RequestParam("bizId") String bizId,
                           @RequestParam("productId") String productId,
                           @RequestParam(value = "level",required = false) Integer level,
                           @RequestParam(value = "relType",required = false) Integer relType) {
        MsgResult msgRet = new MsgResult();
        try {
            Integer type = 0;
            if(level != null){
                if (level == 1) {
                    // 应用定价
                    type = 1;
                } else {
                    // 栏目定价
                    type = 2;
                }
            }
            if(relType != null){
                type = relType;
            }
            List<ProductRel> all = getService().findAll();
            int size = all.size();
            ProductRel rel = new ProductRel();
            rel.setBizId(bizId);
            rel.setProductId(productId);
            rel.setType(type);
            rel.setSort(size + 1);
            rel.setIsShow(1);
            getService().save(rel);
            msgRet.pushOk("栏目右键添加定价成功！");
        } catch (Exception e) {
            msgRet.pushError("栏目右键添加定价失败：" + e.getMessage());
            logger.error("栏目右键添加定价时，发生异常！", e);
        }
        return msgRet;
    }

    @RunLogger(value = "删除产品定价", isSaveRequest = true)
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

    @RunLogger(value = "批量删除产品定价", isSaveRequest = true)
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

    @RunLogger(value = "编辑字典分类", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(@RequestParam(value = "id", required = true) String id,
                            @RequestParam(value = "sort", required = false)Integer sort,
                            @RequestParam(value = "isShow", required = false)Integer isShow) {
        MsgResult msgRet = new MsgResult();
        try {
            ProductRel productRel = getService().findById(id);
            if(productRel != null){
                if(sort != null){
                    productRel.setSort(sort);
                }
                if(isShow != null){
                    productRel.setIsShow(isShow);
                }
                getService().update(productRel);
            }
            msgRet.pushOk("修改成功!");
        } catch (Exception e) {
            msgRet.pushError("修改失败：" + e.getMessage());
            logger.error("修改时，发生异常！", e);
        }
        return msgRet;
    }

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<ProductRel> specification = DynamicSpecifications.bySearchFilter(request,
                    ProductRel.class, null);
            List<ProductRel> entities = getService().findByCondition(specification, webPage);

            List<ProductRelVo> lstVo = Lists.newArrayList();
            for (ProductRel entity : entities) {
                ProductRelVo vo = mapper.map(entity, ProductRelVo.class);
                Product product = productService.findById(entity.getProductId());
                if(product != null){
                    vo.setProductName(product.getName());
                    Integer originalPrice = Integer.parseInt(product.getOriginalPrice()) / 100;
                    Integer currentPrice = Integer.parseInt(product.getCurrentPrice()) / 100;
                    vo.setOriginalPrice(originalPrice + "元");
                    vo.setCurrentPrice(currentPrice + "元");
                }else{
                    vo.setProductName("");
                    vo.setOriginalPrice("");
                    vo.setCurrentPrice("");
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
}
