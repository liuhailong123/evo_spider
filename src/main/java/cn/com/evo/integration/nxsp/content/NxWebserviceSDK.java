package cn.com.evo.integration.nxsp.content;


import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.nxsp.common.NxConstantEnum;
import cn.com.evo.integration.nxsp.content.ws.*;

import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;
import java.rmi.RemoteException;

/**
 * @author rf
 * @date 2019/5/17
 */
public class NxWebserviceSDK {
    /**
     * 产品发布
     * @param sequence 局方注入返回的唯一操作id,需要用来拼接播放地址
     * @param assetId
     * @param PPVId  产品包id（如果同媒资存在多个产品包，用|来分隔）
     * @param columnId 栏目id
     */
    public static PublishResponse publish(String sequence, String assetId, String PPVId, String columnId){
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                    new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        String lspid = ConstantFactory.map.get(NxConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(NxConstantEnum.AMSID.getKey());
        String prividerID = ConstantFactory.map.get(NxConstantEnum.ProviderID.getKey());
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
            PublishResponse publish = binding.publish(new StringHolder(lspid), new StringHolder(amsid),
                    new StringHolder(sequence), assetId, prividerID, PPVId, columnId,
                    new IntHolder(), new StringHolder());
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
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                    new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        String lspid = ConstantFactory.map.get(NxConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(NxConstantEnum.AMSID.getKey());
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
            IngestAssetResponse response = binding.ingestAsset(new StringHolder(lspid), new StringHolder(amsid),
                    new StringHolder(Sequence), new String(FtpPath), new String(AdiFileName), new String(NotifyUrl),
                    new IntHolder(), new StringHolder());
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
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                    new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        binding.setTimeout(60000);

        String lspid = ConstantFactory.map.get(NxConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(NxConstantEnum.AMSID.getKey());
        String prividerID = ConstantFactory.map.get(NxConstantEnum.ProviderID.getKey());
        System.out.println("取消发布参数打印:" +
                "\nLSPID:" + lspid +
                "\nAMSID:" + amsid +
                "\nsequence:" + sequence +
                "\nprividerID:" + prividerID +
                "\nassetId:" + assetId +
                "\n================================");
        try {
            UnpublishResponse unpublish = binding.unpublish(new StringHolder(lspid),
                    new StringHolder(amsid),
                    new StringHolder(sequence),
                    prividerID,
                    assetId,
                    new IntHolder(),
                    new StringHolder());
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
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                    new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        binding.setTimeout(60000);
        String lspid = ConstantFactory.map.get(NxConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(NxConstantEnum.AMSID.getKey());
        System.out.println("产品绑定参数打印:" +
                "\nLSPID:" + lspid +
                "\nAMSID:" + amsid +
                "\nsequence:" + sequence +
                "\noperate:" + operate +
                "\nplayId:" + playId +
                "\nPPVId:" + ppvId +
        "\n================================");
        try {
            BindProductResponse response = binding.bindProduct(new StringHolder(lspid),
                    new StringHolder(amsid),
                    new StringHolder(sequence),
                    operate,
                    playId,
                    ppvId,
                    new IntHolder(), new StringHolder());
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
        cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub binding;
        try {
            binding = (cn.com.evo.integration.nxsp.content.ws.IngestAssetServiceSOAPStub)
                    new cn.com.evo.integration.nxsp.content.ws.IngestAssetService_ServiceLocator().getIngestAssetServiceSOAP();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        binding.setTimeout(60000);
        String lspid = ConstantFactory.map.get(NxConstantEnum.LSPID.getKey());
        String amsid = ConstantFactory.map.get(NxConstantEnum.AMSID.getKey());
        String ProviderID = ConstantFactory.map.get(NxConstantEnum.ProviderID.getKey());
        System.out.println("媒资删除参数打印:" +
                "\nLSPID:" + lspid +
                "\nAMSID:" + amsid +
                "\nsequence:" + sequence +
                "\nassetId:" + assetId +
                "\nprividerID:" + ProviderID +
                "\n================================");
        try {
            DeleteAssetResponse response = binding.deleteAsset(new StringHolder(lspid),
                    new StringHolder(amsid),
                    new StringHolder(sequence),
                    ProviderID, assetId,
                    new IntHolder(), new StringHolder());
            return response;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
