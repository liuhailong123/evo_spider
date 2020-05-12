package cn.com.evo.cms.utils;

import com.alibaba.fastjson.JSONObject;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

/**
 * 视频处理工具类
 *
 * @author shilinxiao
 */
public class XugglerUtil {
    public static IContainer container = null;

    public XugglerUtil(String filePath) {
        try{
            container = init(filePath);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    /**
     * 初始化IContainer
     *
     * @param filePath
     * @return
     */
    public static IContainer init(String filePath) {
        try{
            IContainer container = IContainer.make();
            int result = container.open(filePath, IContainer.Type.READ, null);
            if (result < 0) {// check if the operation was successful
                return null;
            } else {
                return container;
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取视频时长
     *
     * @return 毫秒
     */
    public Long getDuration() {
        try {
			if (container != null) {
			    Long duration = container.getDuration();
			    return duration;
			} else {
			    return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    /**
     * 获取视频大小
     */
    public Long getFileSize() {
        try {
			if (container != null) {
			    Long fileSize = container.getFileSize();
			    return fileSize;
			} else {
			    return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    /**
     * 获取视频码率
     *
     * @return
     */
    public Integer getBitRate() {
        try{
            if (container != null) {
                Integer bitRate = container.getBitRate();
                return bitRate;
            } else {
                return null;
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取宽高
     *
     * @return
     */
    public JSONObject getWidthAndHeigth() {
        try {
            JSONObject jo = new JSONObject();
            if (container != null) {
                IStream stream = container.getStream(0);
                IStreamCoder coder = stream.getStreamCoder();
                if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
                    int width = coder.getWidth();
                    int height = coder.getHeight();
                    jo.put("width", width);
                    jo.put("height", height);
                    return jo;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("获取视频宽高发生异常");
        }
    }

    public static void main(String[] args) {
        String filePath = "http://cdn.evomedia.cn/cms_asset/video/src/%E5%98%9F%E6%8B%89%E5%8D%81%E4%B8%87%E4%B8%AA%E4%B8%BA%E4%BB%80%E4%B9%88220.mp4?crazycache=1";
        XugglerUtil xu = new XugglerUtil(filePath);
        JSONObject jo = xu.getWidthAndHeigth();
        Integer bitrate = xu.getBitRate();
        Long time = xu.getDuration();
        Long size = xu.getFileSize();
        System.out.println("地址：" + filePath);
        System.out.println("分辨率：" + jo.toString());
        System.out.println("码率" + bitrate + " bit");
        System.out.println("时长" + time / 1000 + "秒");
        System.out.println("大小" + size + "bit");
    }

}
