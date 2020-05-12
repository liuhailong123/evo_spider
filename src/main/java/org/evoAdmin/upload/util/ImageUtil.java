package org.evoAdmin.upload.util;

import org.evoAdmin.upload.image.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯云 图片/文件 上传工具类
 * 
 * @author Administrator
 *
 */
public class ImageUtil {

	public static long APPID;
	public static String SECRETID;
	public static String SECRETKEY;
	public static String BUCKETNAME;
	public static String COSREGION;
	public static String COSPRE = "";

	public ImageUtil(long appId, String secretId, String secretKey, String bucketName, String cosregion) {
		ImageUtil.APPID = appId;
		ImageUtil.SECRETID = secretId;
		ImageUtil.SECRETKEY = secretKey;
		ImageUtil.BUCKETNAME = bucketName;
		ImageUtil.COSREGION = cosregion;
	}

	/**
	 * 文件上传
	 * 
	 * @return
	 */
	public String imageUpload(Map<String, Object> param) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		String cosFilePath = (String) param.get("cosFilePath");// "/test201704191905.jpg"
		String localFilePath = (String) param.get("localFilePath");// "D:\\test.jpg"
		byte[] buffer = Demo.File2byte(localFilePath);
		UploadFileRequest uploadFileRequest = new UploadFileRequest(BUCKETNAME, cosFilePath, buffer);
		uploadFileRequest.setEnableSavePoint(false);
		uploadFileRequest.setEnableShaDigest(false);
		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		cosClient.shutdown();// 关闭释放资源
		return uploadFileRet;
	}

	/**
	 * 文件流上传
	 */
	public String fileBufferUpload(String cosFilePath, MultipartFile[] files) {
		try {
			COSClient cosClient = getCOSClient();// 获取客户端连接
			byte[] buffer = null;
			for (MultipartFile multipartFile : files) {
				FileInputStream fis = (FileInputStream) multipartFile.getInputStream();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] b = new byte[1024];
				int n;
				while ((n = fis.read(b)) != -1) {
					bos.write(b, 0, n);
				}
				fis.close();
				bos.close();
				buffer = bos.toByteArray();
			}
			UploadFileRequest uploadFileRequest = new UploadFileRequest(BUCKETNAME, cosFilePath, buffer);
			uploadFileRequest.setEnableSavePoint(false);
			uploadFileRequest.setEnableShaDigest(false);
			String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
			cosClient.shutdown();// 关闭释放资源
			return uploadFileRet;
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 文件流上传(单个文件)
	 */
	public String singleFileBufferUpload(String cosFilePath, MultipartFile file) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		try {
			byte[] buffer = null;
			FileInputStream fis = (FileInputStream) file.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();

			UploadFileRequest uploadFileRequest = new UploadFileRequest(BUCKETNAME, cosFilePath, buffer);
			uploadFileRequest.setEnableSavePoint(false);
			uploadFileRequest.setEnableShaDigest(false);
			String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
			return uploadFileRet;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			cosClient.shutdown();// 关闭释放资源
		}

	}

	/**
	 * 文件流上传(单个文件)
	 */
	public String singleFileBufferUpload(String cosFilePath, FileInputStream fis ) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		try {
			byte[] buffer = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();

			UploadFileRequest uploadFileRequest = new UploadFileRequest(BUCKETNAME, cosFilePath, buffer);
			uploadFileRequest.setEnableSavePoint(false);
			uploadFileRequest.setEnableShaDigest(false);
			String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
			return uploadFileRet;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			cosClient.shutdown();// 关闭释放资源
		}

	}
	
	/**
	 * 文件流上传(单个文件)-接收流
	 */
	public String singleFileBufferUploadByBuffer(String cosFilePath, byte[] buffer) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		try {
			UploadFileRequest uploadFileRequest = new UploadFileRequest(BUCKETNAME, cosFilePath, buffer);
			uploadFileRequest.setEnableSavePoint(false);
			uploadFileRequest.setEnableShaDigest(false);
			String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
			return uploadFileRet;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			cosClient.shutdown();// 关闭释放资源
		}

	}

	/**
	 * 获取(网络文件)
	 */
	public String interNetFileBufferUpload(String strUrl, String fileName) throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		DataInputStream input = new DataInputStream(conn.getInputStream());
		DataOutputStream output = new DataOutputStream(new FileOutputStream(fileName));
		byte[] buffer = new byte[1024 * 8];
		int count = 0;
		while ((count = input.read(buffer)) > 0) {
			output.write(buffer, 0, count);
		}
		output.close();
		input.close();
		return "ok";

	}

	/**
	 * 分片上传
	 */
	public String sliceUpload(String cosPath, String localPath) {
		try {
			COSClient cosClient = getCOSClient();// 获取客户端连接
			UploadSliceFileRequest request = new UploadSliceFileRequest(BUCKETNAME, cosPath, localPath, 1024 * 1024);
			/**
			 * 开启sha摘要计算，是否出发秒传
			 */
//			request.setEnableShaDigest(true);
			String uploadFileRet = cosClient.uploadSliceFile(request);
			cosClient.shutdown();// 关闭释放资源
			return uploadFileRet;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 获取文件属性
	 * 
	 * @return
	 */
	public String getImageDetail(Map<String, Object> param) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		String cosFilePath = (String) param.get("cosFilePath");// "/test201704191905.jpg"
		StatFileRequest statFileRequest = new StatFileRequest(BUCKETNAME, cosFilePath);
		String statFileRet = cosClient.statFile(statFileRequest);
		cosClient.shutdown();// 关闭释放资源
		return statFileRet;
	}

	/**
	 * 文件删除
	 * 
	 * @return
	 */
	public String imageDelete(Map<String, Object> param) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		String cosFilePath = (String) param.get("cosFilePath");
		DelFileRequest delFileRequest = new DelFileRequest(BUCKETNAME, cosFilePath);
		String delFileRet = cosClient.delFile(delFileRequest);
		cosClient.shutdown();// 关闭释放资源
		return delFileRet;
	}

	/**
	 * 创建存储目录
	 */
	public String createFolder(Map<String, Object> param) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		String cosPath = (String) param.get("cosPath");// 要创见的目录
		// String bizAttr=(String)param.get("bizAttr");//备注
		CreateFolderRequest createFolderRequest = new CreateFolderRequest(BUCKETNAME, cosPath);
		String createFolderRet = cosClient.createFolder(createFolderRequest);
		return createFolderRet;
	}

	/**
	 * 获取目录列表
	 */
	public String listFolder(Map<String, Object> param) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		String cosPath = (String) param.get("cosPath");// 要获取的目标目录
		ListFolderRequest listFolderRequest = new ListFolderRequest(BUCKETNAME, cosPath);
		String listFolderRet = cosClient.listFolder(listFolderRequest);
		return listFolderRet;
	}

	/**
	 * 删除目录
	 */
	public String delFolder(Map<String, Object> param) {
		COSClient cosClient = getCOSClient();// 获取客户端连接
		String cosPath = (String) param.get("cosPath");
		DelFolderRequest delFolderRequest = new DelFolderRequest(BUCKETNAME, cosPath);
		String delFolderRet = cosClient.delFolder(delFolderRequest);
		return delFolderRet;
	}

	/**
	 * 获取客户端连接
	 * 
	 * @return
	 */
	private static COSClient getCOSClient() {
		// 设置用户属性, 包括appid, secretId和SecretKey
		// 这些属性可以通过cos控制台获取(https://console.qcloud.com/cos)

		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如广州(gz)[华南], 天津(tj)[华北], 上海(sh)[华东]
		clientConfig.setRegion(COSREGION);
		// 初始化秘钥信息
		Credentials cred = new Credentials(APPID, SECRETID, SECRETKEY);
		// 初始化cosClient
		COSClient cosClient = new COSClient(clientConfig, cred);
		return cosClient;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		long appId = 1253586727;
		String secretId = "AKID5zpTvB47MUQAlAwYUIuLbfXCgySpfDIF";
		String secretKey = "xjIjx0rBQU0XxFNXbUBgz61lJhtsjRxG";
		String bucketName = "test";
		String cosregion = "tj";

		appId = 1253206712;
		secretId = "AKIDkOQaCE2YWbo8toNbynjgYxKb3hdfdags";
		secretKey = "vqkIBuWgzAP6t0y4EhJVGT7qr22lPTjF";
		bucketName = "evo";
		cosregion = "sh";

		appId = 1253836259;
		secretId = "AKIDvjLl04wc6PsCKGaWr3Up7IHBwWv9m5lF";
		secretKey = "jLlz4cw9D6mJf0DE3g6KNDsKaeWBfx3x";
		bucketName = "evomedia";
		cosregion = "tj";

		ImageUtil iu = new ImageUtil(appId, secretId, secretKey, bucketName, cosregion);

		Map<String, Object> param = new HashMap<>();

		// 上传，获取详情，删除
		param.put("cosFilePath", "/未来媒体内容资产库/图片/VIP/941518994304761858.png");
		// param.put("localFilePath", "D:\\test.jpg");
		param.put("localFilePath", "/Users/shilinxiao/Desktop/文件/临时/vodeo/frameExtracting/941518994304761858.png");

		// 创建目录
		/*
		 * param.put("cosPath", "/testFolder/test/test2/"); param.put("bizAttr",
		 * "test");
		 */

		// 获取目录列表
		/* param.put("cosPath", "/testFolder/"); */

		// 删除目录
		/* param.put("cosPath", "/testFolder/test/test2/"); */
		// param.put("cosPath", "/testFolder/test/");

		String result = "";
		// result = iu.singleFileBufferUpload2("/陆鑫大视频测试.mp4", new
//		 File("/Users/luxin/Downloads/陆鑫大视频测试.mp4"));
		result = iu.imageUpload(param);// 上传
		// result=iu.getImageDetail(param);//获取详情
		// result=iu.imageDelete(param);//删除
		// result=iu.createFolder(param);//创建目录
		// result=iu.listFolder(param);//获取目录列表
		// result=iu.delFolder(param);//删除目录
		// result=iu.interNetFileBufferUpload
		// ("http://1253206712.vod2.myqcloud.com/dd3c6ba4vodtransgzp1253206712/a7e4588a9031868222964113956/f0.f20.mp4",
		// "网络文件.mp4");//获取网络文件
		// result=iu.downInterNetFileBufferUploadCos
		// ("http://1253206712.vod2.myqcloud.com/dd3c6ba4vodtransgzp1253206712/a7e4588a9031868222964113956/f0.f20.mp4",
		// "/网络文件.mp4");//获取网络文件并上传至cos
//		result = iu.sliceUpload("/2017-1207-1639.zip", "/Users/shilinxiao/Desktop/videoEdit.zip");
		System.out.println("测试 返回结果：");
		System.out.println("\n");
		System.out.println(result);
	}
}
