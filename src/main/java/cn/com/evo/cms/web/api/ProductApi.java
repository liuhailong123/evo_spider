package cn.com.evo.cms.web.api;

import cn.com.evo.cms.domain.entity.pay.Product;
import cn.com.evo.cms.service.pay.ProductRelService;
import cn.com.evo.cms.service.pay.ProductService;
import cn.com.evo.cms.web.api.vo.ProductDetailApivo;
import cn.com.evo.cms.web.voService.ProductVoService;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品相关接口
 */
@Controller
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductApi extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRelService productRelService;

    @Autowired
    private ProductVoService productVoService;

    /**
     * 获取可订购套餐列表
     *
     * @param appId
     * @return
     */
    @RequestMapping(value = "setMeallist", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI setMeallist(@RequestParam("appId") String appId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        List<ProductDetailApivo> lvos = Lists.newArrayList();
        try {
            List<Product> productList = productRelService.findByBizIdAndIsShowOrderBySort(appId, 1);

            for (Product product : productList) {
                ProductDetailApivo productDetailApivo = productVoService.transProductData(product);
                lvos.add(productDetailApivo);
            }
            result.put("products", lvos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取可订购套餐列表时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 根据产品code查询产品详情 所带的服务 以及 时长 和次数
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "detail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI detail(@RequestParam("code") String code) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            Product product = productService.getByCode(code);
            //检测产品是否存在
            if (product != null) {
                ProductDetailApivo productDetailApivo = productVoService.transProductData(product);
                result.put("product", productDetailApivo);
                dataRet.setData(result);
                dataRet.pushOk("成功！");
            } else {
                logger.error("产品不存在");
            }
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取可订购套餐列表时，发生异常！", e);
        }
        return dataRet;
    }
}
