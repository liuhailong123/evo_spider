package cn.com.evo.cms.web.api;

import cn.com.evo.cms.domain.entity.cms.*;
import cn.com.evo.cms.domain.entity.vip.UserAccount;
import cn.com.evo.cms.service.cms.*;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.VerificationCodeService;
import cn.com.evo.cms.web.api.vo.ActiveApiVo;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import com.frameworks.utils.DateUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动相关接口
 */
@Controller
@RequestMapping("/api/active")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ActiveApi extends BaseController {
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private ActiveService activeService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ActiveApplyService activeApplyService;

    /**
     * 活动列表
     *
     * @param columnId
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI list(@RequestParam("columnId") String columnId) {
        DataResultForAPI dataRet = new DataResultForAPI();
        List<ActiveApiVo> vos = Lists.newArrayList();
        try {
            List<CatalogueRelation> list = catalogueRelationService.findByAIdAndTypeAndIsPublishOrderBySortAsc(columnId,
                    2);

            for (CatalogueRelation catalogueRelation : list) {
                Active active = activeService.findById(catalogueRelation.getBId());

                ActiveApiVo vo = new ActiveApiVo(active, catalogueRelation);
                vo.setInfo("");
                List<SourceRel> sourceRels = sourceRelService.findByFId(active.getId(), 2);
                if (sourceRels != null) {
                    if (sourceRels.size() > 0) {
                        Picture picture = pictureService.findById(sourceRels.get(0).getSourceId());
                        vo.setImgUrl(pictureService.getImageUrl(picture.getId()));
                    }
                }
                vo.setId(catalogueRelation.getId());
                vos.add(vo);
            }

            JSONObject result = new JSONObject();
            result.put("actives", vos);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据栏目id获取活动列表时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 活动详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI info(@RequestParam("id") String id) {
        DataResultForAPI dataRet = new DataResultForAPI();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(id);
            Active entity = activeService.findById(catalogueRelation.getBId());

            ActiveApiVo vo = new ActiveApiVo(entity, catalogueRelation);
            JSONObject result = new JSONObject();
            result.put("info", vo);
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("根据id获取活动详情时，发生异常！", e);
        }
        return dataRet;
    }

    /**
     * 活动报名
     *
     * @param id     活动ID
     * @param phone  手机号
     * @param code   验证码
     * @param cardNo 卡号
     * @return
     */
    @RequestMapping(value = "/apply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI apply(@RequestParam("id") String id, @RequestParam("phone") String phone,
                                  @RequestParam("code") String code, @RequestParam("cardNo") String cardNo) {
        DataResultForAPI dataRet = new DataResultForAPI();
        JSONObject result = new JSONObject();
        try {
            CatalogueRelation catalogueRelation = catalogueRelationService.findById(id);
            Active entity = activeService.findById(catalogueRelation.getBId());

            //检测验证码是否失效
            Integer checkCode = verificationCodeService.judgeCodeOK(phone, code);
            if (checkCode == 0) {
                //验证码有效,检测活动是否在有效期
                String thisTime = DateUtil.getDateTime();
                Active active = activeService.getByIdAndThisTime(entity.getId(), thisTime);
                if (active != null) {
                    //活动在有效期,检测卡号是否存在
                    UserAccount userAccount = userAccountService.getByAccountNoAndAccountType(cardNo, 2, null);
                    if (userAccount != null) {
                        //卡号存在,检测是否已报名
                        ActiveApply activeApply = activeApplyService.getByActiveIdAndUserId(entity.getId(), userAccount.getUserId());
                        if (activeApply == null) {
                            //未报名,则进行报名
                            activeApply = new ActiveApply();
                            activeApply.setActiveId(entity.getId());
                            activeApply.setPhone(phone);
                            activeApply.setUserAccountNo(cardNo);
                            activeApply.setUserId(userAccount.getUserId());
                            activeApplyService.save(activeApply);
                            //报名成功
                            result.put("applyResult", 0);
                        } else {
                            //已报名
                            result.put("applyResult", 5);
                        }
                    } else {
                        //未知的卡号
                        result.put("applyResult", 4);
                    }
                } else {
                    //活动过期
                    result.put("applyResult", 1);
                }
            } else {
                //2 验证码过期；3 验证码错误
                result.put("applyResult", checkCode);
            }
            dataRet.setData(result);
            dataRet.pushOk("成功！");
        } catch (Exception e) {
            dataRet.pushError("失败：" + e.getMessage());
            logger.error("申请活动时异常！", e);
        }
        return dataRet;
    }
}
