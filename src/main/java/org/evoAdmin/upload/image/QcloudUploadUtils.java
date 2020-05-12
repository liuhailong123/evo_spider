package org.evoAdmin.upload.image;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 上传文件
 * @author akali
 * @return
 */
public class QcloudUploadUtils 
{
	
	public static final long APPID=1253484141;
	public static final String SECRETID="AKID65sUwGAG2f6PXTnrLhy30BFGIYjjtGEC";
	public static final String SECRETKEY="3zkJgtvlU6XmpgXEj8LMBPn6DOUP83zl";
	public static final String BUCKETNAME="lediantuku1";
	public static final String COSREGION="gz";
	public static final String COSPRE="shop";
	private static COSClient cosClient = null;
	static
	{
		if (cosClient == null)
		{
	        // 初始化客户端配置
	        ClientConfig clientConfig = new ClientConfig();
	        // 设置bucket所在的区域，比如广州(gz), 天津(tj)
	        clientConfig.setRegion("gz");
	        // 初始化秘钥信息
	        Credentials cred = new Credentials(APPID, SECRETID, SECRETKEY);
	        // 初始化cosClient
	        cosClient = new COSClient(clientConfig, cred);
		}
	}
	
	
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		scanDir("/ldhl/20170405/");
 
	 
		
	}
	
	
	/**
	 * 上传文件
	 * @author akali
	 * @return JSONObject
	 */
	@SuppressWarnings("static-access")
    public static JSONObject uploadFile(String cosFilePath,String localFilePath)
	{
		JSONObject object = new JSONObject();
	       // 设置用户属性, 包括appid, secretId和SecretKey
        // 这些属性可以通过cos控制台获取(https://console.qcloud.com/cos)

        ///////////////////////////////////////////////////////////////
        // 文件操作 //
        ///////////////////////////////////////////////////////////////
        // 1. 上传文件(默认不覆盖)
        // 将本地的local_file_1.txt上传到bucket下的根分区下,并命名为sample_file.txt
        // 默认不覆盖, 如果cos上已有文件, 则返回错误

        byte [] buffer=Demo.File2byte(localFilePath);
        UploadFileRequest uploadFileRequest = new UploadFileRequest(BUCKETNAME, cosFilePath, buffer);
        uploadFileRequest.setEnableSavePoint(false);
        uploadFileRequest.setEnableShaDigest(false);
        String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
        System.out.println("upload file ret:" + uploadFileRet);
        
        return JSON.parseObject(uploadFileRet);
        
	}
	
	/**
	 * 浏览目录
	 * @author akali
	 * @param cosFolderPath COS路径
	 * @return
	 */
	@SuppressWarnings("static-access")
    public static JSONObject scanDir(String cosFolderPath){
		JSONObject object = new JSONObject();
	    ListFolderRequest listFolderRequest = new ListFolderRequest(BUCKETNAME, cosFolderPath);
	    String listFolderRet = cosClient.listFolder(listFolderRequest);
	    System.out.println("list folder ret:" + listFolderRet);
	    return JSON.parseObject(listFolderRet);
	}
	
	/**
	 * 将目录下的文件组装成 Collection集合。。用户百度编辑器在线浏览
	 * @author akali
	 * @param json qcloud获取列表
	 * @return Collection<File>
	 * @throws MalformedURLException 
	 */
	@SuppressWarnings("unused")
    public static String[] assembleFileList(String cosFolderPath,String... fileType)
	{
		
		JSONObject json = scanDir(cosFolderPath);
		
		Collection<File> listCollection = new ArrayList<File>();
		String[] arr = {};
		try {
			//非空判断
			if ( null == json || json.isEmpty() )
			{
				return arr;
			}
			
			//如果获取成功
			if (json.getInteger("code") == 0)
			{
				JSONArray jArray = json.getJSONObject("data").getJSONArray("infos");
				if (null!=jArray && !jArray.isEmpty())
				{
					int len = jArray.size();
					arr = new String[len];
					for (int i=0;i < len; i++)
					{
						JSONObject  jsonObject = (JSONObject) jArray.get(i);
						arr[i] = jsonObject.getString("source_url");
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取目录列表文件失败");
		}
	
		return arr;
	}
}
