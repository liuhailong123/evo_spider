package cn.com.evo.cms.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class FileDown {
	/**
	 * 利用 commonio 库下载文件，依赖Apache Common IO ，官网
	 * https://commons.apache.org/proper/commons-io/
	 * 
	 * @param url
	 * @param saveDir
	 * @param fileName
	 */
	public static void downloadByApacheCommonIO(String url, String saveDir, String fileName) {
		try {
			FileUtils.copyURLToFile(new URL(url), new File(saveDir, fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		FileDown.downloadByApacheCommonIO("http://cdn.evomedia.cn/cms_asset/video/src/1520563721438_video1.mp4",
				"/Users/shilinxiao/Desktop/file/", "test.mp4");
	}
}
