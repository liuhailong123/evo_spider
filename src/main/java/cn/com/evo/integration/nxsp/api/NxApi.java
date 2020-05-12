package cn.com.evo.integration.nxsp.api;

import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.domain.entity.total.ContentOperation;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.cms.VideoService;
import cn.com.evo.cms.service.pay.OrderService;
import cn.com.evo.cms.service.pay.PayConfigService;
import cn.com.evo.cms.service.pay.ProductService;
import cn.com.evo.cms.service.total.ContentOperationService;
import cn.com.evo.cms.service.vip.UserAccountService;
import cn.com.evo.cms.service.vip.UserServerService;
import cn.com.evo.cms.service.vip.UserService;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.nxsp.common.NxConstantEnum;
import cn.com.evo.integration.nxsp.common.NxUtils;
import cn.com.evo.integration.nxsp.content.NxWebserviceSDK;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rf
 * @date 2019/5/17
 */
@Controller
@RequestMapping("/api/nx")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NxApi extends BaseController {
    /**
     * 内容注入结果通知--成功标识
     */
    private final static Integer CMD_RESULT_OK = 0;

    @Autowired
    private ContentOperationService contentOperationService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserServerService userServerService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private PayConfigService payConfigService;

    /**
     * 请求注入媒资回调接口
     * @param lspId
     * @param amsId
     * @param sequence
     * @param assetId
     * @param assetId
     * @param resultCode
     * @return
     */
    @RequestMapping(value = "/IngestNotify", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI IngestNotify(@RequestParam("LSPID")String lspId,
                                         @RequestParam("AMSID")String amsId,
                                         @RequestParam("Sequence")String sequence,
                                         @RequestParam("AssetId") String assetId,
                                         @RequestParam("PlayId")String playId,
                                         @RequestParam("ResultCode")Integer resultCode){
        DataResultForAPI dataResult = new DataResultForAPI();
        try {
            if(resultCode.equals(CMD_RESULT_OK)){
                logger.error("内容注入成功，修改注入内容状态");
                //注入成功
                ContentOperation contentOperation = new ContentOperation();
                Content content = contentService.findByVideoId(assetId);
                content.setSynType(2);
                contentService.update(content);
                contentOperation.setContent(content);
                contentOperation.setStatus(2);
                contentOperation.setInfo("注入成功");
                String correlateId = NxUtils.createSixBitRandomNum();
                contentOperation.setCorrelateId(correlateId);
                contentOperationService.save(contentOperation);
                Video video = videoService.findById(assetId);
                video.setUrl(playId);
                videoService.update(video);

                //判断子集状态全部为已注入的话。就修改剧头状态
                List<Content> ckContents = contentService.findByPIdAndSynType(content.getpId(), 2);
                Content pContent = contentService.findById(content.getpId());
                if(ckContents.size() == pContent.getSumNum()){
                    pContent.setSynType(2);
                    contentService.update(pContent);
                    ContentOperation byCorrelateId = contentOperationService.getByCorrelateId(sequence);
                    byCorrelateId.setStatus(2);
                    contentOperationService.update(byCorrelateId);
                }
            }else{
                logger.error("内容注入失败,code:"+resultCode);
                //更新内容注入状态（通过回传的sequence查询当前注入信息）
                ContentOperation contentOperation = new ContentOperation();
                Content content = contentService.findByVideoId(assetId);
                content.setSynType(3);
                contentService.update(content);
                contentOperation.setContent(content);
                contentOperation.setStatus(3);
                contentOperation.setInfo("注入失败");
                String correlateId = NxUtils.createSixBitRandomNum();
                contentOperation.setCorrelateId(correlateId);
                contentOperationService.save(contentOperation);
                Video video = videoService.findById(assetId);
                video.setUrl(playId);
                videoService.update(video);

                //判断子集状态全部为已注入的话。就修改剧头状态
                List<Content> ckContents = contentService.findByPIdAndSynType(content.getpId(), 3);
                Content pContent = contentService.findById(content.getpId());
                if(ckContents.size() == pContent.getSumNum()){
                    pContent.setSynType(3);
                    contentService.update(pContent);
                    ContentOperation byCorrelateId = contentOperationService.getByCorrelateId(sequence);
                    byCorrelateId.setStatus(3);
                    contentOperationService.update(byCorrelateId);
                }

            }
        } catch (Exception e){
            logger.error("处理内容注入结果异常:" + e.getMessage(), e);
            dataResult.pushError("处理内容注入结果异常:" + e.getMessage());
        }
        return dataResult;
    }

    /**
     * 发布回调
     * @param sequence
     * @param assetId
     * @param playId
     */
    @RequestMapping(value = "/publishNotify", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void publishNotify(@RequestParam("Sequence")String sequence,
                              @RequestParam("AssetId")String assetId,
                              @RequestParam("PlayId")String playId){
        DataResultForAPI result = new DataResultForAPI();
        try {
            ContentOperation contentOperation = contentOperationService.getByCorrelateId(sequence);
            Content content = contentService.findById(contentOperation.getContent().getId());
            content.setSynType(4);
            contentService.update(content);
            String info = contentOperation.getInfo() + "@发布成功";
            contentOperation.setInfo(info);
            contentOperation.setStatus(4);
            contentOperationService.update(contentOperation);
        }catch (Exception e){
            throw new RuntimeException("宁夏局方发布回调，异常：" + e.getMessage(), e);
        }
    }

    /**
     * 宁夏产品定价绑定接口
     * @param correlateId
     * @return
     */
    @RequestMapping(value = "/bindProduct", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI bindProduct(@RequestParam("correlateId") String correlateId){
        DataResultForAPI result = new DataResultForAPI();
        ContentOperation contentOperation = contentOperationService.getByCorrelateId(correlateId);
        if(contentOperation.getBindStatus() == 0){
            result.pushInvalid("该产品已绑定成功，无需重复操作!");
            return result;
        }
        String[] info = contentOperation.getInfo().split("@");
//        BindProductResponse response = NxWebserviceSDK.bindProduct(info[0], "Add", info[1], ConstantFactory.map.get(NxConstantEnum.PPVId.getKey()));
//        if(response.getResultCode() == 1 || response.getResultCode() == 6 || response.getResultCode() == 7 || response.getResultCode() == 8){
//            result.pushError("调用局方接口：进行产品和产品包绑定，异常：" + response.getResultCode()+ ": " +response.getResultMsg());
//        } else if(response.getResultCode() == 0){
//            contentOperation.setBindStatus(0);
//            contentOperationService.update(contentOperation);
//            result.pushOk("请求绑定成功");
//        } else {
//            throw new RuntimeException("宁夏广电产品绑定接口webservice请求异常，请联系管理员!");
//        }
        return result;
    }
}
