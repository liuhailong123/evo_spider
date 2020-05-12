package cn.com.evo.integration.xjTvos.content;


import cn.com.evo.cms.utils.HttpUtil;
import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.xjTvos.common.XJConstantEnum;
import cn.com.evo.integration.xjTvos.content.wb.*;
import cn.com.evo.integration.xjnt.common.ConstantEnum;
import cn.com.evo.integration.xjnt.sdk.ContentSDK;
import cn.com.evo.integration.xjnt.sdk.dto.GetAuthorityInfoResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;
import java.rmi.RemoteException;

/**
 * @author rf
 * @date 2019/5/17
 */
public class XJWebserviceSDK {
    protected static Logger logger = LogManager.getLogger(XJWebserviceSDK.class);

    /**
     * 调用局方接口获取视频信息
     *
     * @param accesstoken 用户登陆标识
     * @param videoId     视频id
     * @return 播放地址
     */
    public static String getVideoAuthorityInfo(String accesstoken, String videoId) {
        try {
            String url = ConstantFactory.map.get(XJConstantEnum.get_authority_url.getKey()) +
                    "?accesstoken=" + accesstoken + "&programid=" + videoId + "&playtype=demand";
            logger.error("新疆tsyrmt鉴权接口地址" + url);
            // 调用局方接口
            String resultStr = HttpUtil.get(url, "调用局方接口获取视频鉴权信息");
            GetAuthorityInfoResponse response = JSONObject.parseObject(resultStr, GetAuthorityInfoResponse.class);

            if (response != null) {
                if (response.getRet() == 0) {
                    if (StringUtils.isNotBlank(response.getPlay_token())) {
                        // 获取到playToken后进行地址的拼接
                        String playHost = ConstantFactory.map.get(XJConstantEnum.video_host.getKey());
                        //接口调用成功
                        String playUrl = playHost + "?accesstoken=" + accesstoken + "&programid=" + videoId + "&playtype=demand&playtoken=" + response.getPlay_token() + "&protocol=http&auth=no";
                        logger.error(playUrl);
                        return playUrl;
                    } else {
                        logger.error("获取playToken失败:" + response.toString());
                        return null;
                    }
                } else {
                    logger.error("调用局方接口get_authority_url失败:" + response.toString());
                    return null;
                }
            } else {
                throw new RuntimeException("调用局方接口get_authority_url异常");
            }
        } catch (Exception e) {
            throw new RuntimeException("调用局方接口获取视频鉴权信息异常：" + e.getMessage(), e);
        }
    }

    /**
     * 产品发布
     * @param sequence 局方注入返回的唯一操作id,需要用来拼接播放地址
     * @param assetId
     * @param PPVId  产品包id（如果同媒资存在多个产品包，用|来分隔）
     * @param columnId 栏目id
     */
    public static PublishResponse publish(String sequence, String assetId, String PPVId, String columnId){
        IngestAssetServiceSOAPStub binding;
        try {
            binding = (IngestAssetServiceSOAPStub)
                    new IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        String lspid = ConstantFactory.map.get(XJConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(XJConstantEnum.AMSID.getKey());
        String prividerID = ConstantFactory.map.get(XJConstantEnum.ProviderID.getKey());
        binding.setTimeout(60000);
        System.out.println("发布参数打印:" +
                "\nLSPID:" + lspid +
                "\nAMSID:" + amsid +
                "\nsequence:" + sequence +
                "\nassetId:" + assetId +
                "\nprividerID:" + prividerID +
                "\nPPVId:" + PPVId +
                "\ncolumnId:" + columnId +
                "\n================================");
        try {
            Publish publishReq = new Publish(lspid, amsid, sequence, assetId, prividerID, PPVId, columnId);
            PublishResponse publish = binding.publishDeal(publishReq);
            return publish;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求局方接收注入请求
     * @param FtpPath 存放ADI、视频、海报等资源的服务器地址
     * @param AdiFileName ADI文件名，homed使用ftpPath和adiFileName拼装路径，下载adi文件(xml)
     * @param NotifyUrl 通知注入结果回调地址，以及媒资发布通知的接口地址
     */
    public static IngestAssetResponse callNXIngestAsset(String FtpPath, String AdiFileName, String NotifyUrl,
                                                        String Sequence){
        IngestAssetServiceSOAPStub binding;
        try {
            binding = (IngestAssetServiceSOAPStub)
                    new IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        String lspid = ConstantFactory.map.get(XJConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(XJConstantEnum.AMSID.getKey());
        // Time out after a minute
        binding.setTimeout(60000);
        System.out.println("媒资注入参数打印:" +
                "\nLSPID:" + lspid +
                "\nAMSID:" + amsid +
                "\nsequence:" + Sequence +
                "\nFtpPath:" + FtpPath +
                "\nAdiFileName:" + AdiFileName +
                "\nNotifyUrl:" + NotifyUrl +
                "\n================================");
        try {
            IngestAsset ingestAsset = new IngestAsset(lspid, amsid, Sequence, FtpPath, AdiFileName, NotifyUrl);
            IngestAssetResponse response = binding.ingestAssetDeal(ingestAsset);
            return response;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        // TBD - validate results
        return null;
    }

    /**
     * 取消发布
     * @param sequence
     * @param assetId
     */
    public static UnpublishResponse unPublish(String sequence, String assetId) {
        IngestAssetServiceSOAPStub binding;
        try {
            binding = (IngestAssetServiceSOAPStub)
                    new IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        binding.setTimeout(60000);

        String lspid = ConstantFactory.map.get(XJConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(XJConstantEnum.AMSID.getKey());
        String prividerID = ConstantFactory.map.get(XJConstantEnum.ProviderID.getKey());
        System.out.println("取消发布参数打印:" +
                "\nLSPID:" + lspid +
                "\nAMSID:" + amsid +
                "\nsequence:" + sequence +
                "\nprividerID:" + prividerID +
                "\nassetId:" + assetId +
                "\n================================");
        try {
            Unpublish unPublishReq = new Unpublish(lspid, amsid, sequence, prividerID, assetId);
            UnpublishResponse unpublish = binding.unpublishDeal(unPublishReq);
            return unpublish;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 产品绑定
     * @param sequence
     * @param operate
     * @param playId
     * @param ppvId
     */
    public static BindProductResponse bingProduct(String sequence, String operate, String playId, String ppvId) {
        IngestAssetServiceSOAPStub binding;
        try {
            binding = (IngestAssetServiceSOAPStub)
                    new IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        binding.setTimeout(60000);
        String lspid = ConstantFactory.map.get(XJConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(XJConstantEnum.AMSID.getKey());
        System.out.println("产品绑定参数打印:" +
                "\nLSPID:" + lspid +
                "\nAMSID:" + amsid +
                "\nsequence:" + sequence +
                "\noperate:" + operate +
                "\nplayId:" + playId +
                "\nPPVId:" + ppvId +
        "\n================================");
        try {
            BindProduct bindProduct = new BindProduct(lspid, amsid, sequence, operate, playId, ppvId);
            BindProductResponse response = binding.bindProductDeal(bindProduct);
            return response;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除媒资
     * @param sequence
     * @param assetId
     */
    public static DeleteAssetResponse deleteAsset(String sequence, String assetId) {
        IngestAssetServiceSOAPStub binding;
        try {
            binding = (IngestAssetServiceSOAPStub)
                    new IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        binding.setTimeout(60000);
        String lspid = ConstantFactory.map.get(XJConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(XJConstantEnum.AMSID.getKey());
        String ProviderID = ConstantFactory.map.get(XJConstantEnum.ProviderID.getKey());
        System.out.println("媒资删除参数打印:" +
                "\nLSPID:" + lspid +
                "\nAMSID:" + amsid +
                "\nsequence:" + sequence +
                "\nassetId:" + assetId +
                "\nprividerID:" + ProviderID +
                "\n================================");
        try {
            DeleteAsset deleteAsset = new DeleteAsset(lspid, amsid, sequence, ProviderID, assetId);
            DeleteAssetResponse response = binding.deleteAssetDeal(deleteAsset);
            return response;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
