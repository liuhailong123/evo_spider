package cn.com.evo.integration.chongqing.common;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author rf
 * @date 2019/8/8
 */
public class FtpUtils {



    /**
     * 创建ftp连接
     * @param url
     * @param port
     * @param username
     * @param password
     * @return
     */
    public static FtpClient connectFTP(String url, int port, String username, String password) {
        //创建ftp
        FtpClient ftp = null;
        try {

            //创建地址
            SocketAddress addr = new InetSocketAddress(url, port);
            //连接
            ftp = FtpClient.create();
            ftp.connect(addr);

            //登陆
            ftp.login(username, password.toCharArray());
//            ftp.setBinaryType();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     * 上传
     * @param localFile
     */
    public static void upload(String localFile, String ftpPath, String ftpName, FtpClient ftp) {
        FileInputStream fis = null;
        try {
            ftp.changeDirectory(ftpPath);

            File file_in = new File(localFile);

            try(OutputStream os = ftp.putFileStream(ftpName);
                FileInputStream is = new FileInputStream(file_in)) {
                byte[] bytes = new byte[1024];
                int c;
                while ((c = is.read(bytes)) != -1) {
                    os.write(bytes, 0, c);
                }
                System.out.println("upload success");
            } catch (IOException ex) {
                System.out.println("not upload");
                ex.printStackTrace();
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis!=null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FtpClient ftpClient = connectFTP("211.159.146.159", 9000, "xjdx_ftp", "Evomedia-ftp");
        download("/data/xml/qwe.xml", "pub/xml/test.xml", ftpClient);

    }

    /**
     * 下载
     * @param localFile
     * @param ftpFile
     * @param ftp
     */
    public static void download(String localFile, String ftpFile, FtpClient ftp) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            // 获取ftp上的文件
            is = ftp.getFileStream(ftpFile);
            File file = new File(localFile);
            byte[] bytes = new byte[1024];
            int i;
            fos = new FileOutputStream(file);
            while((i = is.read(bytes)) != -1){
                fos.write(bytes, 0, i);
            }
            System.out.println("download success!!");
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null) {
                    fos.close();
                }
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
