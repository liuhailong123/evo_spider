package com.frameworks.utils;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ftp工具类
 *
 * @author luxin
 */
public class FtpUtils {
    /**
     * 本地字符编码
     */
    private static String LOCAL_CHARSET = "GBK";

    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     */
    private static String SERVER_CHARSET = "ISO-8859-1";

    /**
     * FTP单个文件上传
     *
     * @param url
     * @param username
     * @param password
     * @param file     MultipartFile
     * @param path
     * @return
     */
    public static Map<String, String> upload(String url, String username, String password, Integer port,
                                             MultipartFile file, String path) {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        Map<String, String> map = new HashMap<>();
        try {
            ftpClient.connect(url, port);
            ftpClient.login(username, password);
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                LOCAL_CHARSET = "UTF-8";
            }

            fis = (FileInputStream) file.getInputStream();
            ftpClient.setRemoteVerificationEnabled(false);
            ftpClient.setControlEncoding(LOCAL_CHARSET);
//            ftpClient.enterLocalActiveMode();
            // 设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            // 原文件名
            String name = file.getOriginalFilename().substring(0,
                    file.getOriginalFilename().lastIndexOf("."));

            // 扩展名
            String extName = file.getOriginalFilename().substring(
                    file.getOriginalFilename().lastIndexOf(".") + 1,
                    file.getOriginalFilename().length());
            String[] split = path.split("/");
            String ftpPath = "";
            for (int i = 0; i < split.length-1; i++) {
                ftpPath += split[i] + "/";
            }
            ftpClient.changeWorkingDirectory(ftpPath);

            // 上传
            boolean flag = ftpClient.storeFile(new String(split[split.length-1].getBytes("UTF-8"), "iso-8859-1"), fis);
            // 文件大小
            Long size = file.getSize();

            map.put("flag", flag + "");
            map.put("name", name);
            map.put("extName", extName);
            map.put("path", path);
            map.put("size", size.toString());
        } catch (IOException e) {
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        return map;
    }

    /**
     * FTP单个文件上传 名称中加入随机数
     *
     * @param url
     * @param username
     * @param password
     * @param file     MultipartFile
     * @param dir
     * @return
     */
    public static Map<String, String> uploadAutoName(String url, String username, String password,
                                                     MultipartFile file, String dir) {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        Map<String, String> map = new HashMap<>();
        try {
            ftpClient.connect(url, 21);
            ftpClient.login(username, password);

            fis = (FileInputStream) file.getInputStream();
            // 设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            String name = file.getOriginalFilename().substring(0,
                    file.getOriginalFilename().lastIndexOf("."));// 原文件名

            String extName = file.getOriginalFilename().substring(
                    file.getOriginalFilename().lastIndexOf(".") + 1,
                    file.getOriginalFilename().length());// 扩展名
            String path = dir + "/" + name + "_" + System.currentTimeMillis() + "." + extName;
            ftpClient.storeFile(path, fis);// 上传
            Long size = file.getSize();// 文件大小
            map.put("url", path);
            map.put("name", name);
            map.put("extName", extName);
            map.put("path", path);
            map.put("size", size.toString());

        } catch (IOException e) {
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        return map;
    }

    /**
     * FTP单个文件上传
     *
     * @param url
     * @param username
     * @param password
     * @param is       InputStream
     * @param path
     */
    public static void upload(String url, String username, String password, Integer port, InputStream is,
                              String path) {
        FTPClient ftpClient = new FTPClient();
        try {
            if (port == null) {
                port = 21;
            }
            ftpClient.connect(url, port);
            ftpClient.login(username, password);
            // 设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            // 上传
            boolean flag = ftpClient.storeFile(new String(path.getBytes(LOCAL_CHARSET), SERVER_CHARSET), is);

            System.out.println(flag);
        } catch (IOException e) {
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(is);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    /**
     * 读取图片，返回base64
     *
     * @param url
     * @param username
     * @param password
     * @param path
     * @return base64
     */
    public static String readImg(String url, String username, String password, String path,
                                 String extName) {
        FTPClient ftpClient = new FTPClient();
        InputStream ins = null;
        byte[] data = null;
        String base64Img = "";
        try {
            ftpClient.connect(url);
            ftpClient.login(username, password);

            // 从服务器上读取指定的文件
            ins = ftpClient.retrieveFileStream(path);
            data = new byte[ins.available()];
            ins.read(data);
            base64Img = Base64Utils.encodeToString(data);
        } catch (IOException e) {
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(ins);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        return "data:image/" + extName + ";base64," + base64Img;

    }

    /**
     * 在服务器上删除文件
     *
     * @param url      FTP连接地址
     * @param username FTP用户名
     * @param password FTP密码
     * @param path     删除文件路径
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(String url, String username, String password, String path)
            throws IOException {
        FTPClient ftpClient = new FTPClient();
        boolean flag = false;
        try {
            ftpClient.connect(url);
            ftpClient.login(username, password);
            flag = ftpClient.deleteFile(new String(path.getBytes("gb2312"), "iso-8859-1"));
        } catch (Exception e) {
            throw new RuntimeException("FTP删除文件异常", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        return flag;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建
     */
    public static boolean downFile(String url, int port, String username, String password, String remotePath, String fileName, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    /**
     * 获取目录下的全部图片
     *
     * @param url      ftp url
     * @param username ftp username
     * @param password ftp password
     * @param dir      获取目录
     * @return 图片地址
     */
    public static List<Map<String, String>> findImgs(String url, String username, String password,
                                                     String dir) {
        List<Map<String, String>> lists = Lists.newArrayList();
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(url, 21);
            ftpClient.login(username, password);

            FTPFile[] files = ftpClient.listFiles(dir);
            for (FTPFile ftpFile : files) {
                Map<String, String> map = new HashMap<>();
                map.put("name", ftpFile.getName());
                map.put("path", dir + "/" + ftpFile.getName());
                lists.add(map);
            }
        } catch (Exception e) {
            throw new RuntimeException("获取目录下的全部图片异常", e);
        }
        return lists;
    }

    /**
     * 创建目录
     *
     * @param url
     * @param username
     * @param password
     * @param dir
     * @return
     */
    public static boolean createDir(String url, String username, String password, String dir) {
        boolean flag = false;
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(url, 21);
            ftpClient.login(username, password);
            flag = ftpClient.makeDirectory(dir);
        } catch (Exception e) {
            throw new RuntimeException("创建目录异常", e);
        }
        return flag;
    }

    public static Boolean Mkdirs(FTPClient ftpClient, String path) {

        Boolean success = false;
        String[] subDirs = path.split("/");

        String LOCAL_CHARSET = "GBK";
        String SERVER_CHARSET = "ISO-8859-1";

        // check if is absolute path
        if ("/".equalsIgnoreCase(path.substring(0, 0))) {
            subDirs[0] = "/" + subDirs[0];
        }
        boolean tmpMkdirs = false;
        try {
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                LOCAL_CHARSET = "UTF-8";
            }
            ftpClient.setControlEncoding(LOCAL_CHARSET);
            ftpClient.printWorkingDirectory();
            for (String subDir : subDirs) {
                // encoding
                String strSubDir = new String(subDir.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
                tmpMkdirs = ftpClient.makeDirectory(strSubDir);
                ftpClient.sendSiteCommand("chmod 755 " + strSubDir);
                ftpClient.changeWorkingDirectory(strSubDir);
                success = success || tmpMkdirs;
            }
            // ftpClient.changeWorkingDirectory(orginPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return success;
    }

    public static void clearFiles(FTPClient ftpClient, String dirPath) {
        try {
            FTPFile[] files = ftpClient.listFiles(dirPath);
            for (FTPFile ftpFile : files) {
                if (ftpFile.isFile()) {
                    ftpClient.deleteFile(dirPath + "/" + ftpFile.getName());
                } else {
                    clearFiles(ftpClient, dirPath + "/" + ftpFile.getName());
                }
            }
            ftpClient.removeDirectory(dirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            downFile("112.18.251.71", 21, "evo_ftp", "Evomedia-ftp", "/ftp/pub/xml/", "ff8080816a92a9b1016a92bdbc740fec.xml","/data/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
