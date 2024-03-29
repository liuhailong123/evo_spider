package org.evoAdmin.upload.image;


/**
 * @author chengwu
 * 获取目录属性信息
 */
public class StatFolderRequest extends AbstractStatRequest {

    public StatFolderRequest(String bucketName, String cosPath) {
        super(bucketName, cosPath);
    }
    
    @Override
    public void check_param() throws ParamException {
    	super.check_param();
    	CommonParamCheckUtils.AssertLegalCosFolderPath(this.getCosPath());
    }

    @Override
    public String toString() {
    	return super.toString();
    }
}
