package org.evoAdmin.upload.image;


/**
 * @author chengwu
 * 删除文件请求
 */
public class AbstractDelRequest extends AbstractBaseRequest {

    public AbstractDelRequest(String bucketName, String cosPath) {
        super(bucketName, cosPath);
    }
    
    @Override
    public void check_param() throws ParamException {
    	super.check_param();
    }
    
    @Override
    public String toString() {
    	return super.toString();
    }
}
