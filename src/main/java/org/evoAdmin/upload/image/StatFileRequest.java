package org.evoAdmin.upload.image;


/**
 * @author chengwu
 * 获取文件属性信息
 */
public class StatFileRequest extends AbstractStatRequest {

    public StatFileRequest(String bucketName, String cosPath) {
        super(bucketName, cosPath);
    }
    
    @Override
    public void check_param() throws ParamException {
    	super.check_param();
    	CommonParamCheckUtils.AssertLegalCosFilePath(this.getCosPath());
    }
    
    @Override
    public String toString() {
    	return super.toString();
    }

}
