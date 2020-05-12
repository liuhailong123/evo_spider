package org.evoAdmin.upload.util;

import java.util.HashMap;
import java.util.Map;

public class VideoUtilTest {
	public static void main(String[] args) {
		String id = "AKID5zpTvB47MUQAlAwYUIuLbfXCgySpfDIF";
		String key = "xjIjx0rBQU0XxFNXbUBgz61lJhtsjRxG";

		id = "AKIDkOQaCE2YWbo8toNbynjgYxKb3hdfdags";
		key = "vqkIBuWgzAP6t0y4EhJVGT7qr22lPTjF";

		id = "AKIDvjLl04wc6PsCKGaWr3Up7IHBwWv9m5lF";
		key = "jLlz4cw9D6mJf0DE3g6KNDsKaeWBfx3x";

		VideoUtil vu = new VideoUtil(id, key);
		Map<String, Object> param = new HashMap<>();

		// 视频参数

		// param.put("strFilePath", "D:\\test.mp4");
		// param.put("strFilePath", "/Users/shilinxiao/Downloads/test3.mp4");
		// param.put("strFileName","test100");
		// param.put("strFileType", "mp4");
		// param.put("isTranscode","0");
		// param.put("isWatermark", "0");
		// param.put("classId", "12");

		// 视频封面参数
		/*
		 * param.put("strFilePath", "D:\\yayaya.jpg"); param.put("strFileName",
		 * "201704211856"); param.put("strFileType", "jpg"); param.put("fileId",
		 * "9031868222912493146");
		 */

		// 加标签 参数
		/*
		 * param.put("fileId", "9031868222912493146"); param.put("tagsName",
		 * "test"); param.put("tagsIndex", "1");
		 */

		// 删标签参数
		/*
		 * param.put("fileId", "9031868222912493146"); param.put("tagsName",
		 * "test"); param.put("tagsIndex", "1");
		 */

		// 删视频参数
		/* param.put("fileId", "9031868222912493146"); */

		// 视频详情 参数
		 param.put("fileId", "4564972818603760478");

		// 修改视频参数
		/*
		 * param.put("fileId", "9031868222951599460"); param.put("fileName",
		 * "test"); param.put("fileIntro", "test"); param.put("classId", 89677);
		 */

		// 添加分类
		/*
		 * param.put("className", "测试分类2"); //
		 * {"codeDesc":"Success","message":"","newClassId":"318549","code":0}
		 * param.put("parentId", 318958);
		 */

		// 修改视频分类
		/*
		 * param.put("classId", 318550); param.put("className", "测试分类修改");
		 */

		// 删除视频分类
		/* param.put("classId", 318958); */

		// 视频转码
		/* param.put("fileId", "9031868222949217563"); */

		// 视频拼接
		/*
		 * String [] fileIds={"9031868222949217563","9031868222949210864"};
		 * param.put("fileIds", fileIds); param.put("name", "拼接测试"); String []
		 * dstTypes={"mp4"}; param.put("dstTypes", dstTypes);
		 */

		// 截取雪碧图
		/*
		 * param.put("fileId", "9031868222950504604");
		 * param.put("definition",10);
		 */

		// 按时间点截图
		/*
		 * param.put("fileId", "9031868222950504604"); param.put("definition",
		 * 10); int[] timeOffset=new int[2]; timeOffset[0]=1000;
		 * timeOffset[1]=2000; param.put("timeOffset", timeOffset);
		 */

		// URL拉取视频上传
		/*
		 * JSONArray pullset= new JSONArray(); JSONObject jo= new JSONObject();
		 * jo.put("url", "http://evo-1253206712.cossh.myqcloud.com/test.mp4");
		 * jo.put("name", "test"); pullset.add(jo);
		 * param.put("pullset",pullset);
		 */

		// 创建转码模板
		// param.put("name","10Mbps_mp4");
		// param.put("container","mp4");
		// param.put("videoCodec","libx264");
		// param.put("fps","25");
		// param.put("videoBitrate","10000");
		// param.put("musicCodec","libfdk_aac");
		// param.put("musicBitrate","256");
		// param.put("sampleRate","48000");

		// param.put("name","8Mbps_hls");
		// param.put("container","mp4");
		// param.put("videoCodec","libx264");
		// param.put("fps","25");
		// param.put("videoBitrate","8192");
		// param.put("musicCodec","libfdk_aac");
		// param.put("musicBitrate","256");
		// param.put("sampleRate","48000");

		// param.put("name","128kbps_mp4");
		// param.put("container","mp4");
		// param.put("videoCodec","libx264");
		// param.put("fps","25");
		// param.put("videoBitrate","128");
		// param.put("musicCodec","libfdk_aac");
		// param.put("musicBitrate","256");
		// param.put("sampleRate","48000");
		/**
		 * 转码模版参数 （自定义分辨率）
		 */
		// param.put("name","10Mbps_mp4");
		// param.put("container","mp4");
		// param.put("videoCodec","libx264");
		// param.put("fps","25");
		// param.put("videoBitrate","10000");
		// param.put("videoWidth","3840");
		// param.put("videoHeight","2160");
		// param.put("musicCodec","libfdk_aac");
		// param.put("musicBitrate","256");
		// param.put("sampleRate","48000");

		// param.put("name","8Mbps_mp4");
		// param.put("container","mp4");
		// param.put("videoCodec","libx264");
		// param.put("fps","25");
		// param.put("videoBitrate","8192");
		// param.put("videoWidth","1920");
		// param.put("videoHeight","1080");
		// param.put("musicCodec","libfdk_aac");
		// param.put("musicBitrate","256");
		// param.put("sampleRate","48000");

		// param.put("name","5Mbps_mp4");
		// param.put("container","mp4");
		// param.put("videoCodec","libx264");
		// param.put("fps","25");
		// param.put("videoBitrate","5120");
		// param.put("videoWidth","1280");
		// param.put("videoHeight","720");
		// param.put("musicCodec","libfdk_aac");
		// param.put("musicBitrate","256");
		// param.put("sampleRate","48000");

		// param.put("name","2Mbps_mp4");
		// param.put("container","mp4");
		// param.put("videoCodec","libx264");
		// param.put("fps","25");
		// param.put("videoBitrate","2048");
		// param.put("videoWidth","1920");
		// param.put("videoHeight","1080");
		// param.put("musicCodec","libfdk_aac");
		// param.put("musicBitrate","256");
		// param.put("sampleRate","48000");

		/**
		 * 7Mbps 分辨率原画 加水印
		 */
//		param.put("name", "7Mbps_mp4");
//		param.put("container", "mp4");
//		param.put("videoCodec", "libx264");
//		param.put("fps", "25");
//		param.put("videoBitrate", "7168");
//		param.put("musicCodec", "libfdk_aac");
//		param.put("musicBitrate", "256");
//		param.put("sampleRate", "48000");

		// 删除转码模板
		// param.put("definition", "10389");

		// 对视频文件进行处理
		// String[] tcDefinition=new String[3];
		// tcDefinition[0]="10446";
		// tcDefinition[1]="10447";
		// tcDefinition[2]="10448";
		// param.put("fileId", "9031868223005546962");
		// param.put("definition", tcDefinition);
		// param.put("watermark", "0");

		// 修改视频模版
		// param.put("definition", "10401");
		// param.put("name", "2M_Mp4");

		String result = "";

		// result=vu.videoUpload(param);//视频上传

		// result=vu.smallFileUpload(param);//封面上传

		// result=vu.createVodTags(param);//增加标签

		// result=vu.deleteVodTags(param);//删除标签

		// result=vu.deleteVodFile(param);//删除视频

		 result = vu.getVideoInfo(param);// 获取视频详情

		// result=vu.modifyVodInfo(param);//修改视频信息

		// result=vu.createClass(param);//添加分类 318550

		// result=vu.describeAllClass(param);//获取视频分类层次结构 空参

		// result=vu.describeClass(param);//获取视频分类信息

		// result=vu.modifyClass(param);//修改视频分类

		// result=vu.deleteClass(param);//删除视频分类

		// result=vu.convertVodFile(param);//视频转码

		// result=vu.concatVideo(param);//视频拼接

		// result=vu.createImageSprite(param);//截取雪碧图

		// result=vu.createSnapshotByTimeOffset(param);//按时间点截图

		// result=vu.pullEvent(param);//拉取事件通知

		// result=vu.describeRecordPlayInfo(param);//依照VID查询视频信息

		// result=vu.multiPullVodFile(param);//URL拉取视频上传

//		result = vu.createTranscodeTemplate(param);// 创建转码模版

		// result=vu.queryTranscodeTemplateList(param);//查询转码模板列表

		// result=vu.processFile(param);//对视频文件进行处理

		// result=vu.deleteTranscodeTemplate(param);//删除转码模板

		// result=vu.updateTranscodeTemplate(param);

		System.out.println("测试返回结果：\n" + result);
	}
}
