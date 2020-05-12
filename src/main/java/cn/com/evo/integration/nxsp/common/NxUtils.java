package cn.com.evo.integration.nxsp.common;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.integration.common.ConstantFactory;
import com.frameworks.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.security.SecureRandom;

/**
 * @author rf
 * @date 2019/5/14
 */
public class NxUtils {

    /**
     * 移动文件至指定目录
     *
     * @param beforePath
     * @param afterPath
     * @throws IOException
     * @throws InterruptedException
     */
    public static void moveFile(String beforePath, String afterPath) throws IOException, InterruptedException {
        String command1 = "chmod 777 " + beforePath;
        Runtime.getRuntime().exec(command1).waitFor();
        String command2 = "mv -f " + beforePath + " " + afterPath;
        Runtime.getRuntime().exec(command2).waitFor();
    }

    /**
     * 创建本地xml文件，并返回xml的ftp路径
     *
     * @param contentId 内容id
     * @param xmlStr    xml内容
     * @param province  省网配置
     * @return ftp路径
     */
    public static String createLoaclXML(String contentId, String xmlStr, Province province) throws IOException, InterruptedException {
        // 创建本地xml存储目录
        String localDir = "/data/xml/";
//        String localDir = "/home/xml/";
        FileUtils.mkdir(localDir);
        String localPath = localDir + contentId + ".xml";

        // 写xml文件
        write(localPath, xmlStr);
        return localPath;
    }

    /**
     * 写本地xml文件
     *
     * @param filePath
     * @param content
     */
    public static void write(String filePath, String content) {
        BufferedWriter out = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
            out.write(content);
        } catch (Exception e) {
            throw new RuntimeException("写本地xml文件异常" + e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("关闭本地xml文件流异常" + e.getMessage(), e);
            }
        }
    }

    /*
     * 从FTP服务器下载文件
     *
     * @param ftpHost FTP IP地址
     * @param ftpUserName FTP 用户名
     * @param ftpPassword FTP用户名密码
     * @param ftpPort FTP端口
     * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
     * @param localPath 下载到本地的位置 格式：H:/download
     * @param fileName 文件名称
     */
    public static void downloadFtpFile(String ftpHost, String ftpUserName,
                                       String ftpPassword, int ftpPort, String ftpPath, String localPath,
                                       String fileName) {
        FTPClient ftpClient = null;

        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            File localFile = new File(localPath + File.separatorChar + fileName);
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(fileName, os);
            os.close();
            ftpClient.logout();

        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }

    }

    /**
     * 生成ftp地址
     *
     * @param province
     * @return
     */
    public static String createFtpUrl(Province province) {
        if (StringUtils.isBlank(province.getFtpUser()) ||
                StringUtils.isBlank(province.getFtpPassword()) ||
                StringUtils.isBlank(province.getFtpUrl()) ||
                StringUtils.isBlank(province.getFtpPort())) {
            throw new RuntimeException("省网FTP相关信息配置不全!!!");
        }
        String path = ConstantFactory.map.get(NxConstantEnum.ftp_xml_path.getKey());

        // 根据接口需要拼装url
        String xmlFtpUrl = "ftp://" + province.getFtpUser() +
                ":" + province.getFtpPassword() +
                "@" + province.getFtpUrl() + ":" + province.getFtpPort() + "/" + path;
        return xmlFtpUrl;
    }

    /**
     * 创建6位随机数
     *
     * @return
     */
    public static String createSixBitRandomNum() {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            Integer randNum = random.nextInt(999999999);
            String temp = randNum.toString();
            StringBuilder buwei = new StringBuilder();
            if (temp.length() < 9) {
                int length = 9 - temp.length();
                for (int i = 0; i < length; i++) {
                    buwei.append(0);
                }
            }
            buwei.append(temp);
            return buwei.toString();
        } catch (Exception e) {
            throw new RuntimeException("创建6位随机数异常!!!" + e.getMessage(), e);
        }
    }

    /**
     * 获取文件名
     *
     * @param url
     * @return
     */
    private static String getFileName(String url) {
        Integer index = url.lastIndexOf("/");
        return url.substring(index + 1, url.length());
    }

    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }
}
