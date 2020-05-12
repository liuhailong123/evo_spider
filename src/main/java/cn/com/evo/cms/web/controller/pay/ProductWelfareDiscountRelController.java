package cn.com.evo.cms.web.controller.pay;

import cn.com.evo.cms.domain.entity.pay.PriceDiscount;
import cn.com.evo.cms.domain.entity.pay.ProductWelfareDiscountRel;
import cn.com.evo.cms.domain.entity.pay.WelfareDiscount;
import cn.com.evo.cms.domain.vo.cms.WelfareDiscountVo;
import cn.com.evo.cms.domain.vo.pay.PriceDiscountVo;
import cn.com.evo.cms.service.pay.PriceDiscountService;
import cn.com.evo.cms.service.pay.ProductWelfareDiscountRelService;
import cn.com.evo.cms.service.pay.WelfareDiscountService;
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
@RequestMapping("/pay/productWelfareDiscountRel")
public class ProductWelfareDiscountRelController extends BaseController {
    private static final String FORM_PAGE = "cms/pay/productWelfareDiscountRel/form";

    @Autowired
    private ProductWelfareDiscountRelService productWelfareDiscountRelService;

    @Autowired
    private PriceDiscountService priceDiscountService;

    @Autowired
    private WelfareDiscountService welfareDiscountService;


    @Autowired
    private Mapper mapper;

    protected ProductWelfareDiscountRelService getService() {
        return productWelfareDiscountRelService;
    }


    @RequiresPermissions(value = {"ProductCharge:Discount:WelfareDiscount:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<ProductWelfareDiscountRel> specification = DynamicSpecifications.bySearchFilter(request, ProductWelfareDiscountRel.class, null);
            List<ProductWelfareDiscountRel> entities = getService().findByCondition(specification, webPage);
            List<PriceDiscountVo> pdVos = Lists.newArrayList();
            List<WelfareDiscountVo> wdVos = Lists.newArrayList();
            //获取查到的数据 类型 （价格优惠／福利优惠）
            Integer type = 0;
            if (entities != null) {
                if (entities.size() > 0) {
                    type = entities.get(0).getType();
                }
            }
            switch (type) {
                case 1://价格优惠
                    for (ProductWelfareDiscountRel entity : entities) {
                        PriceDiscount pd = priceDiscountService.findById(entity.getDiscountId());
                        PriceDiscountVo vo = mapper.map(pd, PriceDiscountVo.class);
                        vo.setId(entity.getId());
                        pdVos.add(vo);
                    }
                    dataRet.setRows(pdVos);
                    break;
                case 2://福利优惠
                    for (ProductWelfareDiscountRel entity : entities) {
                        WelfareDiscount wd = welfareDiscountService.findById(entity.getDiscountId());
                        WelfareDiscountVo vo = mapper.map(wd, WelfareDiscountVo.class);
                        vo.setId(entity.getId());
                        wdVos.add(vo);
                    }
                    dataRet.setRows(wdVos);
                    break;
                default:
                    dataRet.setRows(Lists.newArrayList());
                    break;
            }
            dataRet.pushOk("获取数据列表成功！");
            dataRet.setTotal(webPage.getTotalCount());

        } catch (Exception e) {
            dataRet.pushError("获取数据列表失败！");
            logger.error("获取数据列表异常！", e);
        }
        return dataRet;
    }

    @RequiresPermissions(value = {"ProductCharge:Discount:WelfareDiscount:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Discount:WelfareDiscount:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(ProductWelfareDiscountRel entity) {
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

    @RequiresPermissions(value = {"ProductCharge:Discount:WelfareDiscount:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        ProductWelfareDiscountRel entity = getService().findById(id);
        mav.addObject("entity", entity);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Discount:WelfareDiscount:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(ProductWelfareDiscountRel entity) {
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
    @RequiresPermissions(value = {"ProductCharge:Discount:WelfareDiscount:remove"})
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
    @RequiresPermissions(value = {"ProductCharge:Discount:WelfareDiscount:remove"})
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
