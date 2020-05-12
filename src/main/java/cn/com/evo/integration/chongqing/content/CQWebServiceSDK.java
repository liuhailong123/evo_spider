package cn.com.evo.integration.chongqing.content;

import cn.com.evo.integration.chongqing.common.CQConstantEnum;
import cn.com.evo.integration.chongqing.content.xml.wb.CSPRequestService;
import cn.com.evo.integration.chongqing.content.xml.wb.CSPRequestServiceLocator;
import cn.com.evo.integration.chongqing.content.xml.wb.CSPResult;
import cn.com.evo.integration.chongqing.content.xml.wb.CtmsSoapBindingStub;
import cn.com.evo.integration.common.ConstantFactory;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * @author rf
 * @date 2019/6/5
 */
public class CQWebServiceSDK {

    /**
     * 注入接口
     * @param correlateID  该注入流程的唯一识别标识
     * @param xmlFtpURL  当前xml的ftp全路径
     * @return
     */
    public static CSPResult callCmd(String correlateID, String xmlFtpURL){
        CSPRequestService service = new CSPRequestServiceLocator();
        try {
            //创建webservice请求对象
            CtmsSoapBindingStub binding = (CtmsSoapBindingStub) service.getctms();
            //调用接口请求注入
            CSPResult result = binding.execCmd(ConstantFactory.map.get(CQConstantEnum.csp_id.getKey()),
                    ConstantFactory.map.get(CQConstantEnum.lsp_id.getKey()), correlateID, xmlFtpURL);
            if (result.getResult() == 0) {
                // 通知成功
                System.out.println("调用局方接口成功，等待webservice返回");
                return result;
            } else {
                throw new RuntimeException("调用重庆局方webservice接口，通知局方获取内容注入xml错误，result:" + result.getResult() + "|errorMsg：" + result.getErrorDescription());
            }
        } catch (ServiceException e) {
            throw new RuntimeException("调用重庆局方webservice接口创建service对象，异常:" + e.getMessage(), e);
        } catch (RemoteException e) {
            throw new RuntimeException("调用重庆局方webservice接口请求注入，异常:" + e.getMessage(), e);
        }
    }
}
