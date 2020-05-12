package cn.com.evo.integration.chongqing.common;

import cn.com.evo.admin.manage.domain.entity.Province;
import com.frameworks.utils.FileUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author rf
 * @date 2019/6/5
 */
public class CqUtils {

    /**
     * 解析xml文件
     * Document document = reader.read(new File("/Users/luxin/Desktop/20190410224511462.xml"));
     *
     * @param localFilePath
     * @throws DocumentException
     */
    public static Map<String, String> transXmlData(String localFilePath) throws DocumentException {
        List<Map<String, String>> list = Lists.newArrayList();

        // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(localFilePath));
        // 通过document对象获取根节点bookstore
        Element xml = document.getRootElement();
        // 通过element对象的elementIterator方法获取迭代器
        Iterator it = xml.elementIterator();
        // 遍历迭代器，获取根节点中的信息（书籍）

        Map<String, String> map = Maps.newHashMap();
        while (it.hasNext()) {
            Element reply = (Element) it.next();
            Iterator itt = reply.elementIterator();
            while (itt.hasNext()) {
                Element property = (Element) itt.next();

                if (StringUtils.isNotBlank(property.getStringValue())) {
                    map.put(property.attributeValue("Name"), property.getStringValue());
                }
            }
        }
        return map;
    }
    /**
     * 从ftp下载文件
     * ftp://ccn_ao:ccn_ao@192.168.19.14/notifyToSp/c2_gzgd_cms_notify_20190708182916_1829.xml
     * @param url
     * @param targetDir
     * @return
     */
    public static String downFtpFile(String url, String targetDir) {
        String[] temps = url.replace("ftp://", "").split("/");
        String[] temps2 = temps[0].split("@");
        String[] temps3 = temps2[0].split(":");
        String ftpHost = temps2[1].split(":")[0];
        String ftpUserName = temps3[0];
        String ftpPassword = temps3[1];
        String ftpPort = "21";
        if(temps2[1].contains(":")){
            ftpPort = temps2[1].split(":")[1];
        }
        String fileName = temps[temps.length-1];
        String ftpPath = "/";
        for (int i = 1; i < temps.length - 1; i++) {
            ftpPath+=temps[i]+"/";
        }
        downloadFtpFile(ftpHost, ftpUserName, ftpPassword, Integer.valueOf(ftpPort),
                ftpPath, targetDir, fileName);
        return targetDir + fileName;
    }

    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = null;
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
            ftpClient.changeWorkingDirectory(ftpPath);
            ftpClient.enterLocalPassiveMode();

            File localFile = new File(localPath + File.separatorChar + fileName);
            if(localFile.exists()){
                localFile.createNewFile();
            }
            OutputStream os = new FileOutputStream(localFile);
            String ftpFile = ftpPath + fileName;
//            boolean b = ftpClient.retrieveFile(new String(ftpFile.getBytes("utf-8"), "iso-8859-1"), os);
            boolean b = ftpClient.retrieveFile(ftpFile, os);
            System.out.println(b);
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
     * 删除ftp文件
     * @param province
     * @param ftpPath
     * @param name
     */
    public static void deleteFtpFile(Province province, String ftpPath, String name){
        FTPClient ftpClient = getFTPClient(province.getFtpHost(), province.getFtpUser(), province.getFtpPassword(), Integer.valueOf(province.getFtpPort()));
        try {
            ftpClient.changeWorkingDirectory(ftpPath);
            boolean b = ftpClient.deleteFile(name);
            if(b){
                System.out.println("删除ftp文件:" + name + "成功！");

            }else {
                System.out.println("删除ftp文件:" + name + "失败！请手动删除。");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建6位随机数
     *
     * @return
     */
    public static String createSixBitRandomNum() {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            Integer randNum = random.nextInt(1000000000);
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

    public static void main(String[] args) {
        String sixBitRandomNum = createSixBitRandomNum();
        System.out.println(sixBitRandomNum);
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
}
