package cn.com.evo.integration.shenzheng.content.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rf
 * @date 2019/5/30
 */
@XmlRootElement(name = "index")
@SuppressWarnings("all")
public class IndexADI {
    /**
     *
     */
    private String clip_id;
    /**
     *
     */
    private String id;
    /**
     *
     */
    private String asset_source;
    /**
     *
     */
    private String cp_id;
    /**
     *
     */
    private String cp_name;
    private String name;
    private String watch_focus;
    /**
     *
     */
    private String index;
    /**
     *
     */
    private String time_len;
    private String summary;
    private String pic;

    /**
     *
     */
    private String director;
    /**
     *
     */
    private String player;
    /**
     *
     */
    private String directornew;
    /**
     *
     */
    private String playernew;
    private String total_clicks;
    private String month_clicks;
    private String week_clicks;
    private String day_clicks;
    /**
     *
     */
    private String update_time;
    /**
     *
     */
    private String release_time;
    /**
     *
     */
    private String original_id;
    /**
     *
     */
    private String cp_video_id;
    /**
     *
     */
    private String drm_flag;
    /**
     *
     */
    private String isintact;
    /**
     *
     */
    private String third_clip_id;
    /**
     *
     */
    private String series_id;
    /**
     *
     */
    private String is_updated;
    /**
     *
     */
    private String asset_cp_id;
    private String sale_info;
    private String image_h;
    private String image_v;
    private String image_s;

    @XmlAttribute
    public String getClip_id() {
        return clip_id;
    }

    public void setClip_id(String clip_id) {
        this.clip_id = clip_id;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getWatch_focus() {
        return watch_focus;
    }

    public void setWatch_focus(String watch_focus) {
        this.watch_focus = watch_focus;
    }

    @XmlAttribute
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @XmlAttribute
    public String getTime_len() {
        return time_len;
    }

    public void setTime_len(String time_len) {
        this.time_len = time_len;
    }

    @XmlAttribute
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @XmlAttribute
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @XmlAttribute
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @XmlAttribute
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @XmlAttribute
    public String getDirectornew() {
        return directornew;
    }

    public void setDirectornew(String directornew) {
        this.directornew = directornew;
    }

    @XmlAttribute
    public String getPlayernew() {
        return playernew;
    }

    public void setPlayernew(String playernew) {
        this.playernew = playernew;
    }

    @XmlAttribute
    public String getTotal_clicks() {
        return total_clicks;
    }

    public void setTotal_clicks(String total_clicks) {
        this.total_clicks = total_clicks;
    }

    @XmlAttribute
    public String getMonth_clicks() {
        return month_clicks;
    }

    public void setMonth_clicks(String month_clicks) {
        this.month_clicks = month_clicks;
    }

    @XmlAttribute
    public String getWeek_clicks() {
        return week_clicks;
    }

    public void setWeek_clicks(String week_clicks) {
        this.week_clicks = week_clicks;
    }

    @XmlAttribute
    public String getDay_clicks() {
        return day_clicks;
    }

    public void setDay_clicks(String day_clicks) {
        this.day_clicks = day_clicks;
    }

    @XmlAttribute
    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @XmlAttribute
    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
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
    public String getDrm_flag() {
        return drm_flag;
    }

    public void setDrm_flag(String drm_flag) {
        this.drm_flag = drm_flag;
    }

    @XmlAttribute
    public String getIsintact() {
        return isintact;
    }

    public void setIsintact(String isintact) {
        this.isintact = isintact;
    }

    @XmlAttribute
    public String getThird_clip_id() {
        return third_clip_id;
    }

    public void setThird_clip_id(String third_clip_id) {
        this.third_clip_id = third_clip_id;
    }

    @XmlAttribute
    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    @XmlAttribute
    public String getIs_updated() {
        return is_updated;
    }

    public void setIs_updated(String is_updated) {
        this.is_updated = is_updated;
    }

    @XmlAttribute
    public String getAsset_cp_id() {
        return asset_cp_id;
    }

    public void setAsset_cp_id(String asset_cp_id) {
        this.asset_cp_id = asset_cp_id;
    }

    @XmlAttribute
    public String getSale_info() {
        return sale_info;
    }

    public void setSale_info(String sale_info) {
        this.sale_info = sale_info;
    }

    @XmlAttribute
    public String getImage_h() {
        return image_h;
    }

    public void setImage_h(String image_h) {
        this.image_h = image_h;
    }

    @XmlAttribute
    public String getImage_v() {
        return image_v;
    }

    public void setImage_v(String image_v) {
        this.image_v = image_v;
    }

    @XmlAttribute
    public String getImage_s() {
        return image_s;
    }

    public void setImage_s(String image_s) {
        this.image_s = image_s;
    }
}
