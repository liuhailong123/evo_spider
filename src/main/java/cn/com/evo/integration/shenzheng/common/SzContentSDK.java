package cn.com.evo.integration.shenzheng.common;

import cn.com.evo.cms.utils.HttpUtil;
import cn.com.evo.integration.common.ConstantFactory;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.awt.im.InputMethodWindow;

/**
 * @author rf
 * @date 2019/5/30
 */
public class SzContentSDK {
    protected static Logger logger = LogManager.getLogger(SzContentSDK.class);

    /**
     * 主媒资请求注入
     * @param contentId
     * @param xml
     * @return
     */
    public AssetResponse registVideo(String contentId, String xml){
        try {
            String url = ConstantFactory.map.get(SzContantEnum.ingest_asset_url.getKey());
            JSONObject req = new JSONObject();
            req.put("func", "modify_asset");
            req.put("assets_id", contentId);
            req.put("id", contentId);
            req.put("assets_video_type", "0");
            req.put("assets_video_info", xml);
            String result = HttpUtil.post(url, req, "请求深圳天威主媒资注入接口");
            AssetResponse res = JSONObject.parseObject(result, AssetResponse.class);
            return res;
        }catch (Exception e){
            throw new RuntimeException("深圳天威请求主媒资注入失败，异常：" + e.getMessage(), e);
        }
    }

    /**
     * 分集请求注入(可预制打点信息)
     * @param contentId
     * @param childId
     * @param metadataXml
     * @param indexXml
     * @return
     */
    public AssetResponse registIndex(String contentId,String childId, String metadataXml, String indexXml){
        try {
            String url = ConstantFactory.map.get(SzContantEnum.ingest_asset_url.getKey());
            JSONObject req = new JSONObject();
            req.put("func", "import_video_clip");
            req.put("assets_id", contentId);
            req.put("assets_video_type", 0);
            req.put("id", childId);
            req.put("seekpoint", metadataXml);
            req.put("assets_clip_info", indexXml);
            String result = HttpUtil.post(url, req, "请求深圳天威分集注入接口");
            return JSONObject.parseObject(result, AssetResponse.class);
        } catch (Exception e){
            throw new RuntimeException("深圳天威请求分集注入失败，异常：" + e.getMessage(), e);
        }
    }

    /**
     * 请求片源注入
     * @param contentId
     * @param childId
     * @param videoId
     * @param medaiXml
     * @return
     */
    public AssetResponse registMedia(String contentId,String childId, String videoId, String medaiXml){
        try {
            String url = ConstantFactory.map.get(SzContantEnum.ingest_asset_url.getKey());
            JSONObject req = new JSONObject();
            req.put("func", "import_video_clip");
            req.put("assets_id", contentId);
            req.put("assets_video_type", 0);
            req.put("assets_clip_id", childId);
            req.put("assets_file_id", videoId);
            req.put("id", videoId);
            req.put("policy", "cdn1");
            req.put("assets_file_info", medaiXml);
            String result = HttpUtil.post(url, req, "请求深圳天威片源注入接口");
            return JSONObject.parseObject(result, AssetResponse.class);
        }catch (Exception e){
            throw new RuntimeException("深圳天威请求片源注入失败,异常:");
        }
    }


    /**
     * 主媒资上线/下线
     * @param contentId 主媒资id
     * @param type 0-上线、1-下线
     * @return
     */
    public AssetResponse publish(String contentId, String type){
        try {
            String url = ConstantFactory.map.get(SzContantEnum.modify_asset_url.getKey());
            JSONObject req = new JSONObject();
            req.put("func", "unline_assets");
            req.put("id", contentId);
            req.put("cp_id", ConstantFactory.map.get(SzContantEnum.cp_id.getKey()));
            req.put("assets_unline", type);
            req.put("assets_id_type", "1");
            req.put("assets_category", "");
            String result = HttpUtil.post(url, req, "请求深圳天威主媒资上下线接口");
            return JSONObject.parseObject(result, AssetResponse.class);
        }catch (Exception e){
            throw new RuntimeException("深圳天威主媒资上下线请求失败,异常:"+ contentId + "---" + type + e.getMessage(), e);
        }
    }

    /**
     * 删除主媒资
     * @param contentId 主媒资id
     * @param recycleBin 1-删除到回收站, 0-直接物理删除
     * @return
     */
    public AssetResponse delVideo(String contentId, String recycleBin){
        try {
            String url = ConstantFactory.map.get(SzContantEnum.modify_asset_url.getKey());
            JSONObject req = new JSONObject();
            req.put("func", "unline_assets");
            req.put("id", contentId);
            req.put("cp_id", ConstantFactory.map.get(SzContantEnum.cp_id.getKey()));
            req.put("assets_id_type", "1");
            req.put("recycle_bin", recycleBin);
            String result = HttpUtil.post(url, req, "请求深圳天威删除主媒资接口");
            return JSONObject.parseObject(result, AssetResponse.class);
        }catch (Exception e){
            throw new RuntimeException("深圳天威主媒资删除请求失败,异常:" + e.getMessage(), e);
        }
    }

    /**
     * 删除片源
     * @param videoId 视频id
     * @return
     */
    public AssetResponse delMedia(String videoId){
        try {
            String url = ConstantFactory.map.get(SzContantEnum.ingest_asset_url.getKey());
            JSONObject req = new JSONObject();
            req.put("func", "unline_assets");
            req.put("id", videoId);
            req.put("assets_file_id", videoId);
            req.put("cp_id", ConstantFactory.map.get(SzContantEnum.cp_id.getKey()));
            req.put("assets_id_type", "1");
            req.put("isintact", "1");
            String result = HttpUtil.post(url, req, "请求深圳天威删除片源接口");
            return JSONObject.parseObject(result, AssetResponse.class);
        }catch (Exception e){
            throw new RuntimeException("深圳天威片源删除请求失败,异常:" + e.getMessage(), e);
        }
    }

    /**
     * 删除分集
     * @param childId
     * @param contentId
     * @param realDelete
     * @return
     */
    public AssetResponse delIndex(String childId, String contentId, String realDelete){
        try {
            //http://cs.interface.hifuntv.com/nn_cms/nn_cms_manager_v2/admin.php?m=delete&a=1111
            String url = ConstantFactory.map.get(SzContantEnum.modify_asset_url.getKey());
            JSONObject req = new JSONObject();
            req.put("func", "delete_video_clip");
            req.put("assets_video_type", "0");
            req.put("id", childId);
            req.put("index_id_type", "0");
            req.put("cp_id", ConstantFactory.map.get(SzContantEnum.cp_id.getKey()));
            req.put("assets_id", contentId);
            req.put("assets_id_type", "1");
            req.put("real_delete", realDelete);
            String result = HttpUtil.post(url, req, "请求深圳天威删除分集接口");
            return JSONObject.parseObject(result, AssetResponse.class);
        }catch (Exception e){
            throw new RuntimeException("深圳天威删除分集请求失败,异常:" + e.getMessage(), e);
        }
    }

    public void authPlay(){

    }
}
