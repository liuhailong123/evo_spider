package cn.com.evo.cms.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteLogUtil {
    /**
     * 写文件 文件存在则追加内容 不存在则创建，并写入内容
     *
     * @param filePath
     * @param content
     */
    public static void writrLog(String filePath, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
            out.write(content);
            out.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
