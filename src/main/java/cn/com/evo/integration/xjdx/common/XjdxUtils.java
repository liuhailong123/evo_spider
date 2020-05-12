package cn.com.evo.integration.xjdx.common;

import cn.com.evo.admin.manage.domain.entity.Province;
import cn.com.evo.integration.chongqing.common.CQConstantEnum;
import cn.com.evo.integration.common.ConstantFactory;
import com.frameworks.utils.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author rf
 * @date 2019/8/14
 */
public class XjdxUtils {
    /**
     * 创建本地xml文件，并返回xml的ftp路径
     *
     * @param contentId 内容id
     * @param xmlStr    xml内容
     * @param province  省网配置
     * @return ftp路径
     */
    public static String createLoaclXML(String contentId, String xmlStr, Province province){
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

    /**
     * 上传xml-ftp
     * @param fileLocalPath
     * @param province
     * @return
     */
    public static String uploadXml(String fileLocalPath, Province province){
        FtpClient ftpClient = FtpClient.create();
        SocketAddress addr = new InetSocketAddress(province.getFtpHost(), Integer.parseInt(province.getFtpPort()));
        try {
            ftpClient.connect(addr);
            ftpClient.login(province.getFtpUser(), province.getFtpPassword().toCharArray());
            String path = ConstantFactory.map.get(XjdxConstantEnum.ftp_xml_path.getKey());
            String[] split = fileLocalPath.split("/");
            if(!"/".equals(path)){
                ftpClient.changeDirectory(path);
            }
            String command = "chmod 777 " + fileLocalPath;
            Runtime.getRuntime().exec(command).waitFor();

            File file_in = new File(fileLocalPath);

            try(OutputStream os = ftpClient.putFileStream(split[split.length-1]);
                FileInputStream is = new FileInputStream(file_in)) {
                byte[] bytes = new byte[1024];
                int c;
                while ((c = is.read(bytes)) != -1) {
                    os.write(bytes, 0, c);
                }
                System.out.println("upload success");
//                String command1 = "chmod 775 " + ConstantFactory.map.get(XjdxConstantEnum.ftp_path.getKey()) + path + split[split.length-1];
//                Runtime.getRuntime().exec(command1).waitFor();
            } catch (IOException ex) {
                System.out.println("not upload");
                ex.printStackTrace();
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }
            String ftpPath = "ftp://" + province.getFtpUser() +
                    ":" + province.getFtpPassword() +
                    "@" + province.getFtpUrl() + ":" + province.getFtpPort();
            if(!"/".equals(path)){
                return ftpPath + "/" + path + split[split.length-1];
            } else {
                return ftpPath + path + split[split.length-1];
            }

        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(ftpClient != null){
                try {
                    ftpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("--------------------失败");
        return "";
    }

    public static String importByExcelForDate(String value) {//value就是它的天数
        String currentCellValue = "";
        if(value != null && !value.equals("")){
            Calendar calendar = new GregorianCalendar(1900,0,-1);
            Date d = calendar.getTime();
            Date dd = DateUtils.addDays(d,Integer.valueOf(value));
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            currentCellValue = formater.format(dd);
        }
        return currentCellValue;
    }

    public static void main(String[] args) {
        String str = "01-一月-2012";
        String s = importByExcelForDate(str);
        System.out.println(s);
    }
}
