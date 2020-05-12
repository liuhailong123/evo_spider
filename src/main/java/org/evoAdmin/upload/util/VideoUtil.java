package org.evoAdmin.upload.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.evoAdmin.upload.video.VodCall;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 腾讯云 音/视频 上传 工具类
 *
 * @author Administrator
 */
public class VideoUtil {
    private static String secretId;
    private static String secretKey;
    private static int isWatermark = 1;
    private static int isTranscode = 1;
    private static int m_iClassId = 0;

    public VideoUtil(String id, String key) {
        VideoUtil.secretId = id;
        VideoUtil.secretKey = key;
    }

    /**
     * 服务端 视频/音频 上传
     */
    public String videoUpload(Map<String, Object> param) {

        JSONObject jo = new JSONObject();

        int iUsage = VodCall.USAGE_UPLOAD;
        int threadNum = 12;

        String strFilePath = (String) param.get("strFilePath");// 文件路径
        String strFileName = (String) param.get("strFileName"); // 文件名称
        String strFileType = (String) param.get("strFileType");// 文件类型
//		int classId = (int) param.get("classId");// 文件类型
        int isTransCode = Integer.valueOf((String) param.get("isTranscode"));// 是否转码
        int isWaterMark = Integer.valueOf((String) param.get("isWatermark"));// 是否加水印
        int classId = 12;

        VodCall vodCall = new VodCall(isTransCode, isWaterMark, classId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        vodCall.SetFileInfo(strFilePath, strFileName, strFileType, classId);// vodCall.SetFileInfo("D:\\kenan.flv",
        // "test1",
        // "flv",
        // 12);

        int ret = vodCall.Upload();
        if (ret == 0) {
            jo.put("fileId", vodCall.m_strFileId);
        }
        jo.put("code", ret);
        return jo.toString();
    }

    /**
     * 视频封面上传
     */
    public String smallFileUpload(Map<String, Object> param) {

        JSONObject jo = new JSONObject();

        int iUsage = VodCall.USAGE_UPLOAD;
        int threadNum = 12;

        String strFilePath = (String) param.get("strFilePath");// 文件路径
        String strFileName = (String) param.get("strFileName");// 文件名称
        String strFileType = (String) param.get("strFileType");// 文件类型
        int classId = 12;
        // String usage=(String) param.get("usage");//标识该文件用途
        String fileId = (String) param.get("fileId");// 上传封面对应的视频文件id

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        vodCall.SetFileInfo(strFilePath, strFileName, strFileType, classId);
        // vodCall.SetFileInfo("D:\\QQ图片20170221131620.jpg", "test", "jpg", 12);
        vodCall.AddExtraPara("usage", "1");
        vodCall.AddExtraPara("fileId", fileId);

        int ret = vodCall.Upload();
        if (ret == 0) {
            jo.put("fileId", vodCall.m_strFileId);
        }
        jo.put("code", ret);
        return jo.toString();
    }

    /**
     * 删除视频
     */
    public String deleteVodFile(Map<String, Object> param) {

        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String fileId = (String) param.get("fileId");// 视频文件id

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<>();
        mapVals.put("Action", "DeleteVodFile");
        mapVals.put("fileId", fileId);
        mapVals.put("priority", 0);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        if (jsObj != null) {
            return jsObj.toJSONString();
        } else {
            return "";
        }
    }

    /**
     * 获取视频信息
     */
    public String getVideoInfo(Map<String, Object> param) {

        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String fileId = (String) param.get("fileId");// 视频文件id

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "GetVideoInfo");
        mapVals.put("fileId", fileId);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString();
    }

    /**
     * 增加视频标签
     */
    public String createVodTags(Map<String, Object> param) {

        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String fileId = (String) param.get("fileId");// 视频文件id
        String tagsName = (String) param.get("tagsName");// 标签名称
        String tagsIndex = (String) param.get("tagsIndex");// 标签位置
        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "CreateVodTags");
        mapVals.put("fileId", fileId);
        mapVals.put("tags." + tagsIndex, tagsName);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString();
    }

    /**
     * 删除视频标签
     */
    public String deleteVodTags(Map<String, Object> param) {

        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String fileId = (String) param.get("fileId");// 视频文件id
        String tagsName = (String) param.get("tagsName");// 标签名称
        String tagsIndex = (String) param.get("tagsIndex");// 标签位置

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "DeleteVodTags");
        mapVals.put("fileId", fileId);
        mapVals.put("tags." + tagsIndex, tagsName);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString();
    }

    /**
     * 修改视频属性
     */
    public String modifyVodInfo(Map<String, Object> param) {

        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String fileId = (String) param.get("fileId");// 视频文件id
        String fileName = (String) param.get("fileName");// 视频名称
        String fileIntro = (String) param.get("fileIntro");// 视频描述
        int classId = (int) param.get("classId");
        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, classId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "ModifyVodInfo");
        mapVals.put("fileId", fileId);
        mapVals.put("fileName", fileName);
        mapVals.put("fileIntro", fileIntro);
        mapVals.put("classId", classId);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 创建视频分类
     */
    public String createClass(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        Long parentId = (Long) param.get("parentId");
        String className = (String) param.get("className");
        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "CreateClass");
        mapVals.put("className", className);
        if (parentId > 0) {
            mapVals.put("parentId", parentId);
        }
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 获取视频分类层次结构
     */
    public String describeAllClass(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "DescribeAllClass");
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 获取视频分类信息
     */
    public String describeClass(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "DescribeClass");
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 修改视频分类
     */
    public String modifyClass(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        Long classId = (Long) param.get("classId");
        String className = (String) param.get("className");

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "ModifyClass");
        mapVals.put("classId", classId);
        mapVals.put("className", className);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 删除视频分类
     */
    public String deleteClass(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        Long classId = (Long) param.get("classId");

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "DeleteClass");
        mapVals.put("classId", classId);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 视频转码
     */
    public String convertVodFile(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String fileId = (String) param.get("fileId");

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "ConvertVodFile");
        mapVals.put("fileId", fileId);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 视频拼接
     */
    public String concatVideo(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        String[] fileIds = (String[]) param.get("fileIds");
        for (int i = 0; i < fileIds.length; i++) {
            String fileIdName = "srcFileList." + i + ".fileId";
            String fileId = fileIds[i];
            mapVals.put(fileIdName, fileId);
        }
        String[] dstTypes = (String[]) param.get("dstTypes");
        for (int i = 0; i < dstTypes.length; i++) {
            String dstTypename = "dstType." + i;
            String dstType = dstTypes[i];
            mapVals.put(dstTypename, dstType);
        }
        String name = (String) param.get("name");

        mapVals.put("Action", "ConcatVideo");

        mapVals.put("name", name);

        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 截取雪碧图
     */
    public String createImageSprite(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String fileId = (String) param.get("fileId");
        int definition = (int) param.get("definition");
        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "CreateImageSprite");
        mapVals.put("fileId", fileId);
        mapVals.put("definition", definition);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 按时间点截图
     */
    public String createSnapshotByTimeOffset(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String fileId = (String) param.get("fileId");
        int definition = (int) param.get("definition");
        int[] timeOffset = (int[]) param.get("timeOffset");
        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "CreateSnapshotByTimeOffset");
        mapVals.put("fileId", fileId);
        mapVals.put("definition", definition);

        for (int i = 0; i < timeOffset.length; i++) {
            mapVals.put("timeOffset." + i, timeOffset[i]);
        }

        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 可靠事件通知 拉取事件通知-确认事件通知
     */
    public String pullEvent(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "PullEvent");
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        String result = jsObj.containsKey("eventList") ? jsObj.get("eventList").toString() : "";
        if (!"".equals(result)) {
            JSONArray ja = JSONArray.parseArray(result);
            if (ja.size() > 0) {// 获取到通知 回调腾讯云确认消息已收到
                String[] msgHandles = new String[ja.size()];
                for (int i = 0; i < msgHandles.length; i++) {
                    msgHandles[i] = ja.getJSONObject(i).getString("msgHandle");// 获取事件通知句柄
                }
                Map<String, Object> params = new HashMap<>();
                params.put("msgHandles", msgHandles);
                this.confirmEvent(params);
            }
        }

        return jsObj.toString().toString();
    }

    /**
     * 确认事件通知
     */
    public String confirmEvent(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String[] msgHandles = (String[]) param.get("msgHandles");
        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "ConfirmEvent");
        for (int i = 0; i < msgHandles.length; i++) {
            mapVals.put("msgHandle." + i, msgHandles[i]);
        }

        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        System.out.println(jsObj.toString().toString());
        return jsObj.toString().toString();
    }

    /**
     * 依照VID查询视频信息
     * <p>
     * 腾讯云直播、互动直播录制文件会进入点播系统，每个录制文件会有唯一的video_id(简称vid)； 该接口用于依照vid获取视频信息。
     */
    public String describeRecordPlayInfo(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        String vid = (String) param.get("vid");

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "DescribeRecordPlayInfo");
        mapVals.put("vid", vid);
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * URL拉取视频上传
     */
    public String multiPullVodFile(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        JSONArray pullsetArray = (JSONArray) param.get("pullset");

        VodCall vodCall = new VodCall(1, 0, 12);// 是否转码 是否加水印 文件分类id
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "MultiPullVodFile");
        for (int i = 0; i < pullsetArray.size(); i++) {
            mapVals.put("pullset." + i + ".url", pullsetArray.getJSONObject(i).get("url"));
            mapVals.put("pullset." + i + ".fileName", pullsetArray.getJSONObject(i).get("name"));
            mapVals.put("pullset." + i + ".isTranscode", 1);
            mapVals.put("pullset." + i + ".priority", 1);// 优先级 优先级：0:中 1:高 2:低
        }
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString();
    }

    /**
     * 创建转码模板
     *
     * @param args
     */
    public String createTranscodeTemplate(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "CreateTranscodeTemplate");
        mapVals.put("name", (String) param.get("name"));
        mapVals.put("container", (String) param.get("container"));
//		mapVals.put("isFiltrateVideo", Integer.valueOf(param.get("isFiltrateVideo")+""));
//		mapVals.put("isFiltrateAudio", Integer.valueOf(param.get("isFiltrateAudio")+"") );
        mapVals.put("video.codec", (String) param.get("videoCodec"));
        mapVals.put("video.fps", Integer.valueOf(param.get("fps") + ""));
        mapVals.put("video.bitrate", Integer.valueOf(param.get("videoBitrate") + ""));
//		mapVals.put("video.width", Integer.valueOf(param.get("videoWidth")+""));//视频流宽度的最大值
//		mapVals.put("video.height", Integer.valueOf(param.get("videoHeight")+""));//视频流高度的最大值
        mapVals.put("audio.codec", (String) param.get("musicCodec"));
        mapVals.put("audio.bitrate", Integer.valueOf(param.get("musicBitrate") + ""));
        mapVals.put("audio.sampleRate", Integer.valueOf(param.get("sampleRate") + ""));
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 查询转码模板列表
     */
    public String queryTranscodeTemplateList(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "QueryTranscodeTemplateList");
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 删除转码模板
     */
    public String deleteTranscodeTemplate(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "DeleteTranscodeTemplate");
        mapVals.put("definition", (String) param.get("definition"));
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 修改视频模版
     *
     * @param param
     * @return
     */
    public String updateTranscodeTemplate(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "UpdateTranscodeTemplate");
        mapVals.put("definition", (String) param.get("definition"));
        mapVals.put("name", (String) param.get("name"));
        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

    /**
     * 对视频文件进行处理
     *
     * @param args
     */
    public String processFile(Map<String, Object> param) {
        int iUsage = VodCall.USAGE_VOD_REST_API_CALL;
        int threadNum = 0;

        VodCall vodCall = new VodCall(VideoUtil.isTranscode, VideoUtil.isWatermark, VideoUtil.m_iClassId);
        vodCall.OpenEcho();
        vodCall.Init(secretId, secretKey, iUsage, threadNum);
        TreeMap<String, Object> mapVals = new TreeMap<String, Object>();
        mapVals.put("Action", "ProcessFile");
        String[] definition = (String[]) param.get("definition");
        for (int i = 0; i < definition.length; i++) {
            mapVals.put("transcode.definition." + i, Integer.valueOf(definition[i]));
        }
        mapVals.put("fileId", (String) param.get("fileId"));
//		mapVals.put("transcode.disableHigherBitrate", Integer.valueOf(param.get("name")+"disableHigherBitrate"));
        mapVals.put("transcode.watermark", Integer.valueOf(param.get("watermark") + ""));
//		mapVals.put("notifyMode", (String)param.get("notifyMode"));

        JSONObject jsObj = vodCall.CallRestApi(mapVals);
        return jsObj.toString().toString();
    }

}
