package org.evoAdmin.upload.image;


/**
 * @author chengwu
 * 删除目录请求
 */
public class DelFileRequest extends AbstractDelRequest {

    public DelFileRequest(String bucketName, String cosPath) {
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
