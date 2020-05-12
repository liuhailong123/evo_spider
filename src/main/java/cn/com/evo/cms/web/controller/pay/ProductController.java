package cn.com.evo.cms.web.controller.pay;

import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.domain.vo.pay.ProductVo;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/pay/product")
public class ProductController extends BaseController {
    private static final String VIEW_PAGE = "cms/pay/product/view";
    private static final String FORM_PAGE = "cms/pay/product/form";
    private static final String SELECT_PAGE = "cms/pay/product/select";

    @Autowired
    private ProductService ProductService;

    @Autowired
    private Mapper mapper;

    protected ProductService getService() {
        return ProductService;
    }

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    /**
     * 打开产品套餐选择页面
     *
     * @param type 类型 1-单选；2-多选
     * @return
     */
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:show"})
    @RequestMapping(value = "/select/{type}", method = {RequestMethod.GET})
    public ModelAndView select(@PathVariable("type") Integer type) {
        ModelAndView mov = new ModelAndView(SELECT_PAGE);
        mov.addObject("type", type);
        return mov;
    }

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<Product> specification = DynamicSpecifications.bySearchFilter(request, Product.class, null);
            List<Product> entities = getService().findByCondition(specification, webPage);
            List<ProductVo> lstVo = Lists.newArrayList();
            for (Product entity : entities) {
                ProductVo vo = mapper.map(entity, ProductVo.class);
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

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(Product entity) {
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

    @RequiresPermissions(value = {"ProductCharge:Prod:Product:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        Product entity = getService().findById(id);
        entity.setOriginalPrice(entity.getOriginalPrice()+ "");
        entity.setCurrentPrice(entity.getCurrentPrice() + "");
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(Product entity) {
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

    /**
     * @param ids
     * @return
     */
    @RequiresPermissions(value = {"ProductCharge:Prod:Product:modify"})
    @RequestMapping(value = "/calculate", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public MsgResult calculate(@RequestParam("ids") String[] ids) {
        MsgResult msgRet = new MsgResult();
        try {
            getService().calculateByIds(ids);
            msgRet.pushOk("价格计算成功!");
        } catch (Exception e) {
            msgRet.pushError("价格计算失败：" + e.getMessage());
            logger.error("价格计算时，发生异常！", e);
        }
        return msgRet;
    }
}
