package org.evoAdmin.upload.image;


/**
 * @author chengwu
 * 删除文件请求
 */
public class DelFolderRequest extends AbstractDelRequest {

    public DelFolderRequest(String bucketName, String cosPath) {
        super(bucketName, cosPath);
    }
    
    @Override
    public void check_param() throws ParamException {
    	super.check_param();
    	CommonParamCheckUtils.AssertLegalCosFolderPath(this.getCosPath());
    	CommonParamCheckUtils.AssertNotRootCosPath(this.getCosPath());
    }
    
    @Override
    public String toString() {
    	return super.toString();
    }
}
