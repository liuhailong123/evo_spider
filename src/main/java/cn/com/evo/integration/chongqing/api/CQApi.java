package cn.com.evo.integration.chongqing.api;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.admin.manage.service.ProvinceService;
import cn.com.evo.cms.domain.entity.cms.Content;
import cn.com.evo.cms.domain.entity.cms.Video;
import cn.com.evo.cms.domain.entity.total.ContentOperation;
import cn.com.evo.cms.service.cms.ContentService;
import cn.com.evo.cms.service.cms.VideoService;
import cn.com.evo.cms.service.total.ContentOperationService;
import cn.com.evo.integration.chongqing.common.CQConstantEnum;
import cn.com.evo.integration.chongqing.common.CqUtils;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.guangxi.content.xml.model.Asset;
import cn.com.evo.integration.scyd.common.ScydUtils;
import com.frameworks.core.web.controller.BaseController;
import com.frameworks.core.web.result.DataResultForAPI;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author rf
 * @date 2019/6/12
 */
@Controller
@RequestMapping("/api/cq")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CQApi extends BaseController {
    protected Logger logger = LogManager.getLogger(this.getClass());
    /**
     * 接口返回成功标识
     */
    public static final String OK = "ORD-000";
    /**
     * 内容注入结果通知--成功标识
     */
    private final static String CMD_RESULT_OK = "0";

    @Autowired
    private ContentOperationService contentOperationService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ProvinceService provinceService;

    /**
     * 请求注入媒资回调接口
     * @param cspId
     * @param lspId
     * @param correlateId
     * @param cmdResult
     * @param resultFileUrl
     */
    @RequestMapping(value = "/IngestNotify", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DataResultForAPI auth(@RequestParam("CSPID")String cspId,
                                @RequestParam("LSPID")String lspId,
                                @RequestParam("CorrelateID")String correlateId,
                                @RequestParam("CmdResult") String cmdResult,
                                @RequestParam("ResultFileURL")String resultFileUrl){
        logger.error("接收到webservice结果返回。进行解析");
        DataResultForAPI dataResult = new DataResultForAPI();
        try {
            // 包含真实播放地址的xml的ftp访问路径
            if (CMD_RESULT_OK.equals(cmdResult)) {
                logger.error("成功");
                // 成功
                // 根据ftp地址下载xml文件，并解析
                if (StringUtils.isNotBlank(resultFileUrl)) {
                    String localPath = CqUtils.downFtpFile(resultFileUrl, ConstantFactory.map.get(CQConstantEnum.call_back_xml_dir.getKey()));
                    logger.error(localPath);
                    // TODO: 2019/6/26   解析xml
                    Map<String, String> data = CqUtils.transXmlData(localPath);
                    logger.error(data.toString());

                    ContentOperation contentOperation = contentOperationService.getByCorrelateId(correlateId);
                    if (contentOperation != null) {
                        // 更新内容注入状态
                        Content content = contentOperation.getContent();
                        content.setSynType(2);
                        contentService.update(content);

                        // 更新地址
                        List<Video> videos = videoService.findByContentId(content.getId());
                        // video临时文件路径
                        String videolocalPath = "";
                        for (Video video : videos) {
                            videolocalPath = video.getUrl();
                            video.setUrl(content.getId());
                            videoService.update(video);
                        }

                        // 更新注入记录状态
                        contentOperation.setStatus(2);
                        contentOperationService.update(contentOperation);

                        // TODO 删除ftp上已注入成功的视频
                        logger.error("注入成功，开始删除ftp视频源。");
                        Province province = provinceService.getByEnable(1);
                        String[] fileFtpName = videolocalPath.split("/");
                        String videoFtpPth = ConstantFactory.map.get(CQConstantEnum.ftp_video_path.getKey());
                        CqUtils.deleteFtpFile(province, videoFtpPth, fileFtpName[fileFtpName.length - 1]);

                    } else {
                        logger.error("内容注入记录不存在:" + correlateId);
                    }
                } else {
                    logger.error(correlateId + "|resultFileURL为空:" + resultFileUrl);
                }
            } else {
                logger.error("失败");
                // 失败
                ContentOperation contentOperation = contentOperationService.getByCorrelateId(correlateId);
                if (contentOperation != null) {
                    // 更新内容注入状态
                    Content content = contentOperation.getContent();
                    content.setSynType(3);
                    contentService.update(content);

                    // 更新注入记录状态
                    contentOperation.setStatus(3);
                    contentOperationService.update(contentOperation);
                } else {
                    logger.error("内容注入记录不存在:" + correlateId);
                }

                dataResult.pushError("内容注入失败!!!");
            }
        } catch (Exception e) {
            logger.error("处理内容注入结果异常:" + e.getMessage(), e);
            dataResult.pushError("处理内容注入结果异常:" + e.getMessage());
        }
        return dataResult;
    }

    /**
     * 请求注入媒资回调接口
     * @param code
     * @param msg
     * @param comboId
     */
    @RequestMapping(value = "/payNotify", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void payNotify(@RequestParam("code")String code,
                          @RequestParam("msg")String msg,
                          @RequestParam("comboId")String comboId){
        System.out.println(code);
        System.out.println(msg);
        System.out.println(comboId);
        //todo 未处理
    }
}
