/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package org.evoAdmin.upload.image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



/**
 * @author chengwu cos Demo代码
 */
public class Demo {

	
  	public static final int APP_ID_V1 = 1253586727;
 
	public static final long APPID=1253586727;
	public static final String SECRETID="AKID5zpTvB47MUQAlAwYUIuLbfXCgySpfDIF";
	public static final String SECRETKEY="xjIjx0rBQU0XxFNXbUBgz61lJhtsjRxG";
	public static final String BUCKETNAME="test";
	public static final String COSREGION="tj";
	public static final String COSPRE="";
	
	
    public static void main(String[] args) throws Exception {

        // 设置用户属性, 包括appid, secretId和SecretKey
        // 这些属性可以通过cos控制台获取(https://console.qcloud.com/cos)
   
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如广州(gz), 天津(tj)
        clientConfig.setRegion("tj");
        // 初始化秘钥信息
        Credentials cred = new Credentials(APPID, SECRETID, SECRETKEY);
        // 初始化cosClient
        COSClient cosClient = new COSClient(clientConfig, cred);
        ///////////////////////////////////////////////////////////////
        // 文件操作 //
        ///////////////////////////////////////////////////////////////
        // 1. 上传文件(默认不覆盖)
        // 将本地的local_file_1.txt上传到bucket下的根分区下,并命名为sample_file.txt
        // 默认不覆盖, 如果cos上已有文件, 则返回错误
        String cosFilePath = "/test201704251039.jpg";
        String localFilePath1 = "D:\\test.jpg";
        byte [] buffer=Demo.File2byte(localFilePath1);
        UploadFileRequest uploadFileRequest =
                new UploadFileRequest(BUCKETNAME, cosFilePath, buffer);
        uploadFileRequest.setEnableSavePoint(false);
        uploadFileRequest.setEnableShaDigest(false);
        String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
        System.out.println("upload file ret:" + uploadFileRet);

        // 2. 下载文件
      /*  String localPathDown = "src/test/resources/local_file_down.txt";
        GetFileLocalRequest getFileLocalRequest =
                new GetFileLocalRequest(bucketName, cosFilePath, localPathDown);
        getFileLocalRequest.setUseCDN(false);
        getFileLocalRequest.setReferer("*.myweb.cn");
        String getFileResult = cosClient.getFileLocal(getFileLocalRequest);
        System.out.println("getFileResult:" + getFileResult);

        // 3. 上传文件(覆盖)
        // 将本地的local_file_2.txt上传到bucket下的根分区下,并命名为sample_file.txt
        String localFilePath2 = "src/test/resources/local_file_2.txt";
        byte[] contentBuffer = CommonFileUtils.getFileContent(localFilePath2)
                .getBytes(Charset.forName(("ISO-8859-1")));
        UploadFileRequest overWriteFileRequest =
                new UploadFileRequest(bucketName, cosFilePath, contentBuffer);
        overWriteFileRequest.setInsertOnly(InsertOnly.OVER_WRITE);
        String overWriteFileRet = cosClient.uploadFile(overWriteFileRequest);
        System.out.println("overwrite file ret:" + overWriteFileRet);

        // 4. 获取文件属性
        StatFileRequest statFileRequest = new StatFileRequest(bucketName, cosFilePath);
        String statFileRet = cosClient.statFile(statFileRequest);
        System.out.println("stat file ret:" + statFileRet);

        // 5. 更新文件属性
        UpdateFileRequest updateFileRequest = new UpdateFileRequest(bucketName, cosFilePath);
        updateFileRequest.setBizAttr("测试目录");
        updateFileRequest.setAuthority(FileAuthority.WPRIVATE);
        updateFileRequest.setCacheControl("no cache");
        updateFileRequest.setContentDisposition("cos_sample.txt");
        updateFileRequest.setContentLanguage("english");
        updateFileRequest.setContentType("application/json");
        updateFileRequest.setXCosMeta("x-cos-meta-xxx", "xxx");
        updateFileRequest.setXCosMeta("x-cos-meta-yyy", "yyy");
        updateFileRequest.setContentEncoding("gzip");
        String updateFileRet = cosClient.updateFile(updateFileRequest);
        System.out.println("update file ret:" + updateFileRet);

        // 6. 更新文件后再次获取属性
        statFileRet = cosClient.statFile(statFileRequest);
        System.out.println("stat file ret:" + statFileRet);
        
        // 6.1 move文件，从/sample_file.txt移动为./sample_file.txt.bak
        String dstFilePath = cosFilePath + ".bak";
        MoveFileRequest moveRequest = new MoveFileRequest(bucketName, cosFilePath, dstFilePath);
        String moveFileRet = cosClient.moveFile(moveRequest);
        System.out.println("first move file ret:" + moveFileRet);
        // 6.2 在从/sample_file.txt.bak移动为/sample_file.txt
        moveRequest = new MoveFileRequest(bucketName, dstFilePath, cosFilePath);
        moveFileRet = cosClient.moveFile(moveRequest);
        System.out.println("second move file ret:" + moveFileRet);

        // 7. 删除文件
        DelFileRequest delFileRequest = new DelFileRequest(bucketName, cosFilePath);
        String delFileRet = cosClient.delFile(delFileRequest);
        System.out.println("del file ret:" + delFileRet);

        ///////////////////////////////////////////////////////////////
        // 目录操作 //
        ///////////////////////////////////////////////////////////////
        // 1. 生成目录, 目录名为sample_folder
        String cosFolderPath = "/xxsample_folder/";
        CreateFolderRequest createFolderRequest =
                new CreateFolderRequest(bucketName, cosFolderPath);
        String createFolderRet = cosClient.createFolder(createFolderRequest);
        System.out.println("create folder ret:" + createFolderRet);

        // 2. 更新目录的biz_attr属性
        UpdateFolderRequest updateFolderRequest =
                new UpdateFolderRequest(bucketName, cosFolderPath);
        updateFolderRequest.setBizAttr("这是一个测试目录");
        String updateFolderRet = cosClient.updateFolder(updateFolderRequest);
        System.out.println("update folder ret:" + updateFolderRet);

        // 3. 获取目录属性
        StatFolderRequest statFolderRequest = new StatFolderRequest(bucketName, cosFolderPath);
        String statFolderRet = cosClient.statFolder(statFolderRequest);
        System.out.println("stat folder ret:" + statFolderRet);

        // 4. list目录, 获取目录下的成员
        ListFolderRequest listFolderRequest = new ListFolderRequest(bucketName, cosFolderPath);
        String listFolderRet = cosClient.listFolder(listFolderRequest);
        System.out.println("list folder ret:" + listFolderRet);

        // 5. 删除目录
        DelFolderRequest delFolderRequest = new DelFolderRequest(bucketName, cosFolderPath);
        String delFolderRet = cosClient.delFolder(delFolderRequest);
        System.out.println("del folder ret:" + delFolderRet);
*/
        // 关闭释放资源
        cosClient.shutdown();
        System.out.println("shutdown!");

    }
    public static byte[] File2byte(String filePath)  
    {  
        byte[] buffer = null;  
        try  
        {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1)  
            {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        }  
        catch (FileNotFoundException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return buffer;  
    } 
}
