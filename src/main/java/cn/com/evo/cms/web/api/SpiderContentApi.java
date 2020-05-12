package cn.com.evo.cms.web.api;

import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.pay.Order;
import cn.com.evo.cms.domain.entity.spider.SpiderContent;
import cn.com.evo.cms.domain.entity.vip.User;
import cn.com.evo.cms.service.cms.CatalogueRelationService;
import cn.com.evo.cms.service.cms.ColumnService;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.spider.SpiderContentService;
import cn.com.evo.cms.service.vip.UserService;
import cn.com.evo.cms.web.api.vo.ArticleApiVo;
import cn.com.evo.cms.web.api.vo.CataloguArticleApiVo;
import cn.com.evo.cms.web.voService.PictureVoService;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章接口
 * @author rf
 * @date 2020/5/9
 */
@Controller
@RequestMapping("/api/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SpiderContentApi extends BaseController{
    @Autowired
    private ColumnService columnService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private PictureVoService pictureVoService;
    @Autowired
    private SpiderContentService spiderContentService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    /**
     * 获取详情接口
     * @param cId 栏目id
     * @param sId 文章id
     * @param uId 用户id
     * @return
     */
    @RequestMapping(value = "/article", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI article(@RequestParam(value = "cId", required = true) String cId,
                                    @RequestParam(value = "sId", required = true) String sId,
                                    @RequestParam(value = "uId", required = false) String uId){
        DataResultForAPI dataRet = new DataResultForAPI();
        boolean flag = false;
        //0、通过栏目id和文章id查询当前文章定价详情，免费则直接执行第三步
        CatalogueRelation catalogueRelation = catalogueRelationService.getByAIdAndBId(cId, sId);
        if(catalogueRelation == null){
            dataRet.pushError("内容不存在");
            return dataRet;
        }
        if(StringUtils.isEmpty(catalogueRelation.getPrice()) || catalogueRelation.getPrice().equals("0")){
            flag = true;
        } else {
            //1、判断用户是否存在，存在下一步，否则弹出失败提示
            if(StringUtils.isEmpty(uId)){
                dataRet.pushError("用户不存在");
                return dataRet;
            }
            User user = userService.findById(uId);
            if(user == null){
                dataRet.pushError("用户不存在");
                return dataRet;
            }
            //2、否则通过用户和文章id查询订单表，已订购则下一步，否则提示未订购
            List<Order> orders = orderService.findByUserIdAndContentId(uId, sId);
            if(!orders.isEmpty()){
                for (Order order : orders) {
                    if(order.getOrderType() == 1){
                        flag = true;
                    }
                }
            }
        }
//        3、用户鉴权通过，则查询当前文章的详细内容并返回，否则未订购
        if(flag){
            SpiderContent entity = spiderContentService.getById(sId);
            ArticleApiVo vo = new ArticleApiVo(entity.getTitle(), entity.getSubtitle(), entity.getAuthor(), entity.getDigest(), entity.getCover()
                    , entity.getDateTime(), entity.getReadNum(), entity.getLikeNum(), new String(entity.getSpInfo()));
            JSONObject result = new JSONObject();
            result.put("article", vo);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
            return dataRet;
        } else {
            dataRet.pushError("当前内容未订购");
            return dataRet;
        }
    }

    /**
     * 根据栏目id获取文章列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI list(@RequestParam("id") String id,
                                 @RequestParam("pageSize") Integer pageSize,
                                 @RequestParam("pageNum") Integer pageNum){
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            List<CataloguArticleApiVo> vo = catalogueRelationService.findByAId(id);
            JSONObject result = new JSONObject();
            result.put("articles", vo);

            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("获取文章列表异常！", e);
        }
        return dataRet;
    }
}
