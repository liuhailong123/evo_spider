package cn.com.evo.integration.shenzheng.content.model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rf
 * @date 2019/5/30
 */
@XmlRootElement(name = "media")
@SuppressWarnings("all")
public class MediaADI {
    /**
     * 来源片源id(* 必传参数，建议弃用)",
     */
    private String file_id;
    /**
     * 来源片源id(* 必传参数。如果【id】属性值不为空，【file_id】以此为准)",
     */
    private String id;
    /**
     * 片源名称",
     */
    private String file_name;
    /**
     * CP ID(* 必传参数，建议弃用)",
     */
    private String asset_source;
    /**
     * CP ID(* 必传参数，如果【cp_id】属性值不为空，【asset_source】以此为准)",
     */
    private String cp_id;
    /**
     * CP 名称",
     */
    private String cp_name;
    /**
     * 文件类型。如:mp4(* 必传参数)",
     */
    private String file_type;
    /**
     * 清晰度。如:std(* 必传参数)",
     */
    private String file_definition;
    /**
     * 文件大小。如:1024(* 必传参数)",
     */
    private String file_size;
    /**
     * 片源服务类型，如:msp(* 必传参数)",
     */
    private String media_service_type;
    /**
     * 编码格式。如:H264(* 必传参数)",
     */
    private String file_coding;
    /**
     * 是否加密。0:否;1:是(* 必传参数)",
     */
    private String drm_flag;
    /**
     * 加密方案",
     */
    private String drm_encrypt_solution;
    /**
     * 片源物理文件地址",
     */
    private String file_path;
    /**
     * 分辨率。如:800*600",
     */
    private String file_resolution;
    /**
     * 视频码率。如:3322",
     */
    private String file_bit_rate;
    /**
     * 文件描述。(未入库参数)",
     */
    private String file_desc;
    /**
     * 视频维度标志位。0:2D;1:3D",
     */
    private String file_3d;
    /**
     * 视频维度为3D分类。0:左右3D;1:上下3D;2:混合3D;其他:3D",
     */
    private String file_3d_type;
    /**
     * 原始ID(建议弃用)",
     */
    private String original_id;
    /**
     * 原始ID(如果【cp_video_id】属性值不为空，【original_id】以此为准)",
     */
    private String cp_video_id;
    /**
     * 第三方片源ID",
     */
    private String third_file_id;
    /**
     * 片源类型。1:点播片源;2:回看片源",
     */
    private String media_type;
    /**
     * 回看片源时，频道ID",
     */
    private String original_live_id;
    /**
     * 回看片源时，开始时间。00000000 00:00:00",
     */
    private String start_time;
    /**
     * 片源媒体ID",
     */
    private String file_core_id;
    /**
     * 一个可能的播放串",
     */
    private String ex_url;
    /**
     *  片源时长",
     */
    private String file_time_len;
    /**
     * EPG分组(注:nns_vod_media使用)",
     */
    private String Tags;
    /**
     * EPG分组(注:nns_preview_media使用，暂未使用)",
     */
    private String file_etag;
    /**
     * 分集CP ID",
     */
    private String clip_cp_id;
    /**
     * 主媒资CP ID",
     */
    private String asset_cp_id;
    /**
     * 外部源ID(文件为外部文件的时候是必须的)",
     */
    private String cdn_ext_source_id;
    /**
     * 1 代表整片;2 短片;3预告片;(注:暂未使用)",
     */
    private String isintact;
    /**
     * 0"
     */
    private String is_source_media;

    @XmlAttribute
    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    @XmlAttribute
    public String getAsset_source() {
        return asset_source;
    }

    public void setAsset_source(String asset_source) {
        this.asset_source = asset_source;
    }

    @XmlAttribute
    public String getCp_id() {
        return cp_id;
    }

    public void setCp_id(String cp_id) {
        this.cp_id = cp_id;
    }

    @XmlAttribute
    public String getCp_name() {
        return cp_name;
    }

    public void setCp_name(String cp_name) {
        this.cp_name = cp_name;
    }

    @XmlAttribute
    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    @XmlAttribute
    public String getFile_definition() {
        return file_definition;
    }

    public void setFile_definition(String file_definition) {
        this.file_definition = file_definition;
    }

    @XmlAttribute
    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    @XmlAttribute
    public String getMedia_service_type() {
        return media_service_type;
    }

    public void setMedia_service_type(String media_service_type) {
        this.media_service_type = media_service_type;
    }

    @XmlAttribute
    public String getFile_coding() {
        return file_coding;
    }

    public void setFile_coding(String file_coding) {
        this.file_coding = file_coding;
    }

    @XmlAttribute
    public String getDrm_flag() {
        return drm_flag;
    }

    public void setDrm_flag(String drm_flag) {
        this.drm_flag = drm_flag;
    }

    @XmlAttribute
    public String getDrm_encrypt_solution() {
        return drm_encrypt_solution;
    }

    public void setDrm_encrypt_solution(String drm_encrypt_solution) {
        this.drm_encrypt_solution = drm_encrypt_solution;
    }

    @XmlAttribute
    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    @XmlAttribute
    public String getFile_resolution() {
        return file_resolution;
    }

    public void setFile_resolution(String file_resolution) {
        this.file_resolution = file_resolution;
    }

    @XmlAttribute
    public String getFile_bit_rate() {
        return file_bit_rate;
    }

    public void setFile_bit_rate(String file_bit_rate) {
        this.file_bit_rate = file_bit_rate;
    }

    @XmlAttribute
    public String getFile_desc() {
        return file_desc;
    }

    public void setFile_desc(String file_desc) {
        this.file_desc = file_desc;
    }

    @XmlAttribute
    public String getFile_3d() {
        return file_3d;
    }

    public void setFile_3d(String file_3d) {
        this.file_3d = file_3d;
    }

    @XmlAttribute
    public String getFile_3d_type() {
        return file_3d_type;
    }

    public void setFile_3d_type(String file_3d_type) {
        this.file_3d_type = file_3d_type;
    }

    @XmlAttribute
    public String getOriginal_id() {
        return original_id;
    }

    public void setOriginal_id(String original_id) {
        this.original_id = original_id;
    }

    @XmlAttribute
    public String getCp_video_id() {
        return cp_video_id;
    }

    public void setCp_video_id(String cp_video_id) {
        this.cp_video_id = cp_video_id;
    }

    @XmlAttribute
    public String getThird_file_id() {
        return third_file_id;
    }

    public void setThird_file_id(String third_file_id) {
        this.third_file_id = third_file_id;
    }

    @XmlAttribute
    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    @XmlAttribute
    public String getOriginal_live_id() {
        return original_live_id;
    }

    public void setOriginal_live_id(String original_live_id) {
        this.original_live_id = original_live_id;
    }

    @XmlAttribute
    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    @XmlAttribute
    public String getFile_core_id() {
        return file_core_id;
    }

    public void setFile_core_id(String file_core_id) {
        this.file_core_id = file_core_id;
    }

    @XmlAttribute
    public String getEx_url() {
        return ex_url;
    }

    public void setEx_url(String ex_url) {
        this.ex_url = ex_url;
    }

    @XmlAttribute
    public String getFile_time_len() {
        return file_time_len;
    }

    public void setFile_time_len(String file_time_len) {
        this.file_time_len = file_time_len;
    }

    @XmlAttribute
    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    @XmlAttribute
    public String getFile_etag() {
        return file_etag;
    }

    public void setFile_etag(String file_etag) {
        this.file_etag = file_etag;
    }

    @XmlAttribute
    public String getClip_cp_id() {
        return clip_cp_id;
    }

    public void setClip_cp_id(String clip_cp_id) {
        this.clip_cp_id = clip_cp_id;
    }

    @XmlAttribute
    public String getAsset_cp_id() {
        return asset_cp_id;
    }

    public void setAsset_cp_id(String asset_cp_id) {
        this.asset_cp_id = asset_cp_id;
    }

    @XmlAttribute
    public String getCdn_ext_source_id() {
        return cdn_ext_source_id;
    }

    public void setCdn_ext_source_id(String cdn_ext_source_id) {
        this.cdn_ext_source_id = cdn_ext_source_id;
    }

    @XmlAttribute
    public String getIsintact() {
        return isintact;
    }

    public void setIsintact(String isintact) {
        this.isintact = isintact;
    }

    @XmlAttribute
    public String getIs_source_media() {
        return is_source_media;
    }

    public void setIs_source_media(String is_source_media) {
        this.is_source_media = is_source_media;
    }
}
