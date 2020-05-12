package org.evoAdmin.upload.image;

import java.nio.charset.Charset;
import java.util.concurrent.Callable;

import org.json.JSONObject;


/**
 * @author chengwu 执行分片上传的任务, 每一个任务由一个线程执行
 */
public class SliceFileDataTask implements Callable<JSONObject> {


    private int TaskId;
    private int sliceIndex;
    private SliceCheckPoint scp;
    private UploadSliceFileContext context;
    private AbstractCosHttpClient httpClient;
    private Credentials cred;
    private String url;
    private long signExpired;

    public SliceFileDataTask(int taskId, int sliceIndex, SliceCheckPoint scp,
            UploadSliceFileContext context, AbstractCosHttpClient httpClient, Credentials cred,
            String url, long signExpired) {
        super();
        TaskId = taskId;
        this.sliceIndex = sliceIndex;
        this.scp = scp;
        this.context = context;
        this.httpClient = httpClient;
        this.cred = cred;
        this.url = url;
        this.signExpired = signExpired;
    }

    @Override
    public JSONObject call() throws Exception {
        JSONObject resultJson = null;
        try {
            HttpRequest httpRequest = new HttpRequest();
            SlicePart slicePart = scp.sliceParts.get(sliceIndex);
            httpRequest.addParam(RequestBodyKey.OP, RequestBodyValue.OP.UPLOAD_SLICE_DATA);
            if (this.context.isEnableShaDigest()) {
                httpRequest.addParam(RequestBodyKey.SHA, context.getEntireFileSha());
            }

            httpRequest.addParam(RequestBodyKey.SESSION, scp.sessionId);
            httpRequest.addParam(RequestBodyKey.OFFSET, String.valueOf(slicePart.getOffset()));
            String sliceContent = "";
            if (this.context.isUploadFromBuffer()) {
                sliceContent = new String(context.getContentBuffer(),
                        new Long(slicePart.getOffset()).intValue(), slicePart.getSliceSize(),
                        Charset.forName("ISO-8859-1"));
            } else {
                sliceContent = CommonFileUtils.getFileContent(scp.uploadFile, slicePart.getOffset(),
                        slicePart.getSliceSize());
            }
            httpRequest.addParam(RequestBodyKey.FILE_CONTENT, sliceContent);

            long signExpired = System.currentTimeMillis() / 1000 + this.signExpired;
            String sign = Sign.getPeriodEffectiveSign(context.getBucketName(), context.getCosPath(),
                    this.cred, signExpired);
            httpRequest.addHeader(RequestHeaderKey.AUTHORIZATION, sign);

            httpRequest.setUrl(this.url);
            httpRequest.setMethod(HttpMethod.POST);
            httpRequest.setContentType(HttpContentType.MULTIPART_FORM_DATA);

            String resultStr = httpClient.sendHttpRequest(httpRequest);
            resultJson = new JSONObject(resultStr);
            if (resultJson.getInt(ResponseBodyKey.CODE) == 0) {
                scp.update(sliceIndex, true);
                if (context.isEnableSavePoint()) {
                    scp.dump(context.getSavePointFile());
                }
            }

            return resultJson;
        } catch (Exception e) {
            String errMsg = new StringBuilder().append("taskInfo:").append(this.toString())
                    .append(", Exception:").append(e.toString()).toString();
            throw new UnknownException(errMsg);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TaskId:").append(TaskId).append(", SliceIndex:").append(sliceIndex)
                .append(", localPath:").append(context.getLocalPath()).append(", uploadUrl:")
                .append(this.url);
        return sb.toString();
    }
}
