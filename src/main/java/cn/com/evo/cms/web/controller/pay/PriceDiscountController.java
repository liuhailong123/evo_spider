package cn.com.evo.cms.web.controller.pay;

import cn.com.evo.cms.domain.entity.pay.PriceDiscount;
import cn.com.evo.cms.domain.enums.PriceDiscountTypeEnum;
import cn.com.evo.cms.domain.vo.pay.PriceDiscountVo;
import cn.com.evo.cms.service.pay.PriceDiscountService;
import cn.com.evo.cms.utils.MathUtil;
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
@RequestMapping("/productCharge/discount/priceDiscount")
public class PriceDiscountController extends BaseController {
    private static final String VIEW_PAGE = "cms/productCharge/discount/priceDiscount/view";
    private static final String SELECT_PAGE = "cms/productCharge/discount/priceDiscount/select";
    private static final String FORM_PAGE = "cms/productCharge/discount/priceDiscount/form";

    @Autowired
    private PriceDiscountService PriceDiscountService;

    @Autowired
    private Mapper mapper;

    protected PriceDiscountService getService() {
        return PriceDiscountService;
    }

    @RequiresPermissions(value = {"ProductCharge:Discount:PriceDiscount:show"})
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView show(HttpServletRequest request) {
        return new ModelAndView(VIEW_PAGE);
    }

    @RequestMapping(value = "/select", method = {RequestMethod.GET})
    public ModelAndView videoSelect(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(SELECT_PAGE);
        return mav;
    }

    @RequiresPermissions(value = {"ProductCharge:Discount:PriceDiscount:search"})
    @RequestMapping(value = "/list", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DataResult list(HttpServletRequest request, Pager webPage) {
        DataResult dataRet = new DataResult();
        try {
            Specification<PriceDiscount> specification = DynamicSpecifications.bySearchFilter(request, PriceDiscount.class, null);
            List<PriceDiscount> entities = getService().findByCondition(specification, webPage);
            List<PriceDiscountVo> lstVo = Lists.newArrayList();
            for (PriceDiscount entity : entities) {
                PriceDiscountVo vo = mapper.map(entity, PriceDiscountVo.class);
                String targetValue = MathUtil.round(MathUtil.divide(vo.getTargetValue(), 100), 2) + " 元";
                String discountMoney = PriceDiscountService.getDiscountMoneyFromDB(vo.getDiscountValue(), vo.getType());
                String temp = vo.getType() == PriceDiscountTypeEnum.ZheKou.getIndex() ? " %" : " 元";
                vo.setTargetValue(targetValue);
                vo.setDiscountValue(discountMoney + temp);
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

    @RequiresPermissions(value = {"ProductCharge:Discount:PriceDiscount:show"})
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        return mav;
    }

    @RunLogger(value = "添加", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Discount:PriceDiscount:add"})
    @RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult store(PriceDiscount entity) {
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

    @RequiresPermissions(value = {"ProductCharge:Discount:PriceDiscount:show"})
    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView(FORM_PAGE);
        PriceDiscount entity = getService().findById(id);
        PriceDiscountVo vo = mapper.map(entity, PriceDiscountVo.class);
        String targetValue = MathUtil.round(MathUtil.divide(vo.getTargetValue(), 100), 2) + "";
        String discountMoney = PriceDiscountService.getDiscountMoneyFromDB(vo.getDiscountValue(), vo.getType());
        vo.setDiscountValue(discountMoney);
        vo.setTargetValue(targetValue);
        mav.addObject("entity", vo);
        return mav;
    }

    @RunLogger(value = "编辑", isSaveRequest = true)
    @RequiresPermissions(value = {"ProductCharge:Discount:PriceDiscount:modify"})
    @RequestMapping(value = "/edit", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MsgResult modify(PriceDiscount entity) {
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
    @RequiresPermissions(value = {"ProductCharge:Discount:PriceDiscount:remove"})
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
    @RequiresPermissions(value = {"ProductCharge:Discount:PriceDiscount:remove"})
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
