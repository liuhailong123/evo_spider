package com.frameworks.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件操作工具类
 *
 * @author luxin
 */
public class FileUtils {
    /**
     * 创建文件夹
     *
     * @param dir
     * @return
     */
    public static void mkdir(String dir) {
        File folder = new File(dir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void deleatAllFiles(File f) {
        if (f == null) {
            return;
        } else if (f.exists()) {// 如果此抽象指定的对象存在并且不为空。
            if (f.isFile()) {
                f.delete();// 如果此抽象路径代表的是文件，直接删除。
            } else if (f.isDirectory()) {// 如果此抽象路径指代的是目录
                String[] str = f.list();// 得到目录下的所有路径
                if (str == null) {
                    f.delete();// 如果这个目录下面是空，则直接删除
                } else {// 如果目录不为空，则遍历名字，得到此抽象路径的字符串形式。
                    for (String st : str) {
                        deleatAllFiles(new File(f, st));
                    } // 遍历清楚所有当前文件夹里面的所有文件。
                    f.delete();// 清楚文件夹里面的东西后再来清楚这个空文件夹
                }
            }
        }
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
