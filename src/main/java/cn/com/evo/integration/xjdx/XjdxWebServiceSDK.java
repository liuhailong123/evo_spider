package cn.com.evo.integration.xjdx;

import cn.com.evo.integration.common.ConstantFactory;
import cn.com.evo.integration.xjdx.common.XjdxConstantEnum;
import cn.com.evo.integration.xjdx.webservice.CSPRequestService;
import cn.com.evo.integration.xjdx.webservice.CSPRequestServiceLocator;
import cn.com.evo.integration.xjdx.webservice.CSPResult;
import cn.com.evo.integration.xjdx.webservice.CtmsSoapBindingStub;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * @author rf
 * @date 2019/8/14
 */
public class XjdxWebServiceSDK {
    /**
     * 注入接口
     * @param correlateID  该注入流程的唯一识别标识
     * @param xmlFtpURL  当前xml的ftp全路径
     * @return
     */
    public static CSPResult callCmd(String correlateID, String xmlFtpURL){
        System.out.println("================进入新疆电信注入操作==========测试ftp地址问题==========");
        CSPResult result = null;
        try {
            CSPRequestService service = new CSPRequestServiceLocator();
            //创建webservice请求对象
            CtmsSoapBindingStub binding = (CtmsSoapBindingStub) service.getctms();

            String cspid = ConstantFactory.map.get(XjdxConstantEnum.csp_id.getKey());
            String lspid = ConstantFactory.map.get(XjdxConstantEnum.lsp_id.getKey());
            String file = ConstantFactory.map.get(XjdxConstantEnum.ftp_file_path.getKey());

            System.out.println("新疆电信注入操作参数打印：" +
                    "\nCSPID:" + cspid +
                    "\nLSPID:" + lspid +
                    "\nCorrelateID:" + correlateID +
                    "\nCmdFileURL:" + xmlFtpURL);
            //调用接口请求注入
            result = binding.execCmd(cspid,lspid
                    , correlateID, xmlFtpURL);
            if (result.getResult() == 0) {
                // 通知成功
                System.out.println("调用局方接口成功，等待webservice返回");
                return result;
            } else {
                throw new RuntimeException("调用新疆电信webservice接口，通知局方获取内容注入xml错误，result:" + result.getResult() + "|errorMsg：" + result.getErrorDescription());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return result;
    }
}
