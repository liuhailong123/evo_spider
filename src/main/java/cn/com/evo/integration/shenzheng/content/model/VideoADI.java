package cn.com.evo.integration.shenzheng.content.model;


import cn.com.evo.integration.common.utils.BeanToXml;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rf
 * @date 2019/5/29
 */
@XmlRootElement(name = "video")
@SuppressWarnings("all")
public class VideoADI {

    public static void main(String[] args) throws JAXBException {
        VideoADI videoADI = new VideoADI();
        videoADI.setId("1");
        videoADI.setAsset_source("qewqe");
        videoADI.setTag("asdasd");
        String s = BeanToXml.beanToXml(videoADI, false);
        System.out.println(s);

    }

    /**
     * 媒资注入ID
     */
    private String assets_id;
    /**
     * 媒资注入id
     */
    private String id;
    /**
     * 媒资名称
     */
    private String name;
    /**
     * cpid(建议弃用)
     */
    private String asset_source;
    /**
     * cpid 不为空则用改属性为准
     */
    private String cp_id;
    /**
     * 影片种类0-电影、1-电视剧、2-综艺、3-动漫
     */
    private String view_type;
    /**
     * cp名称
     */
    private String cp_name;
    /**
     * 资源库栏目名称，如：电影
     */
    private String assets_category;
    /**
     * 定价id
     */
    private String svc_item_id;
    /**
     * 媒资类型 0-点播 1-直播  默认0
     */
    private String video_type;
    /**
     * 影片关键字 如：国医养生馆，青海卫视
     */
    private String sreach_key;
    /**
     * 导演
     */
    private String director;
    /**
     * 影片导演新注入格式
     */
    private String directornew;
    /**
     * 演员以逗号，隔开
     */
    private String player;
    /**
     *
     */
    private String playernew;
    /**
     * 标签
     */
    private String tag;
    /**
     * 地区
     */
    private String region;
    /**
     * 上映时间
     */
    private String release_time;
    /**
     * 总集数
     */
    private String totalnum;
    /**
     * 国别
     */
    private String national;
    /**
     * 海报小图
     */
    private String smallpic;
    /**
     * 海报大图
     */
    private String bigpic;
    /**
     * 海报竖图
     */
    private String verticality_img;
    /**
     * 海报横图
     */
    private String horizontal_img;
    /**
     * 海报方图
     */
    private String square_img;
    /**
     * 剧情简介
     */
    private String intro;
    /**
     * 供片商
     */
    private String producer;
    /**
     * 编剧
     */
    private String adaptor;
    /**
     *
     */
    private String adaptornew;
    /**
     * 演员表
     */
    private String play_role;
    /**
     * 别名
     */
    private String subordinate_name;
    /**
     * 英文名称
     */
    private String english_name;
    /**
     * 语言
     */
    private String language;
    /**
     * 字幕语种
     */
    private String caption_language;
    /**
     * 影片名首字母
     */
    private String initials;
    /**
     * 版权过期时间
     */
    private String copyright_range;
    /**
     * 总点击
     */
    private String total_clicks;
    /**
     * 月点击
     */
    private String month_clicks;
    /**
     * 周点击
     */
    private String week_clicks;
    /**
     * 日点击
     */
    private String day_clicks;
    /**
     * EPG输出标识
     */
    private String etag;
    /**
     * 影片时长
     */
    private String view_len;
    /**
     * 主媒资原始id
     */
    private String original_id;
    /**
     * 主媒资原始id
     */
    private String cp_video_id;
    /**
     * 是否支持离线播放
     */
    private String offline_play_support;
    /**
     * 主媒资看点
     */
    private String watch_focus;
    private String category_type;
    private String third_asset_id;
    private String third_expand;
    private String series_id;
    private String assets_definition;
    private String is_updated;
    private String assets_format;
    private String assets_dimension;
    private String copyright_area;
    private String index_info;
    private String online_identify;
    private String us_level;
    private String uk_level;
    private String point;
    private String org_id;
    private String copyright;
    private String is_needed_auth;
    private String cite_id;
    private String cite_cp_id;
    private String copyright_begin_date;
    private String nns_collect_count;
    private String user_score_count;
    private String user_score_arg;
    private String assets_charge;
    private String preview_type;
    private String fusion_definition;
    private String douban_score;

    @XmlAttribute
    public String getAssets_id() {
        return assets_id;
    }

    public void setAssets_id(String assets_id) {
        this.assets_id = assets_id;
    }
    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getView_type() {
        return view_type;
    }

    public void setView_type(String view_type) {
        this.view_type = view_type;
    }

    @XmlAttribute
    public String getCp_name() {
        return cp_name;
    }

    public void setCp_name(String cp_name) {
        this.cp_name = cp_name;
    }

    @XmlAttribute
    public String getAssets_category() {
        return assets_category;
    }

    public void setAssets_category(String assets_category) {
        this.assets_category = assets_category;
    }

    @XmlAttribute
    public String getSvc_item_id() {
        return svc_item_id;
    }

    public void setSvc_item_id(String svc_item_id) {
        this.svc_item_id = svc_item_id;
    }

    @XmlAttribute
    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }

    @XmlAttribute
    public String getSreach_key() {
        return sreach_key;
    }

    public void setSreach_key(String sreach_key) {
        this.sreach_key = sreach_key;
    }

    @XmlAttribute
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @XmlAttribute
    public String getDirectornew() {
        return directornew;
    }

    public void setDirectornew(String directornew) {
        this.directornew = directornew;
    }

    @XmlAttribute
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @XmlAttribute
    public String getPlayernew() {
        return playernew;
    }

    public void setPlayernew(String playernew) {
        this.playernew = playernew;
    }

    @XmlAttribute
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @XmlAttribute
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @XmlAttribute
    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    @XmlAttribute
    public String getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }

    @XmlAttribute
    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    @XmlAttribute
    public String getSmallpic() {
        return smallpic;
    }

    public void setSmallpic(String smallpic) {
        this.smallpic = smallpic;
    }

    @XmlAttribute
    public String getBigpic() {
        return bigpic;
    }

    public void setBigpic(String bigpic) {
        this.bigpic = bigpic;
    }

    @XmlAttribute
    public String getVerticality_img() {
        return verticality_img;
    }

    public void setVerticality_img(String verticality_img) {
        this.verticality_img = verticality_img;
    }

    @XmlAttribute
    public String getHorizontal_img() {
        return horizontal_img;
    }

    public void setHorizontal_img(String horizontal_img) {
        this.horizontal_img = horizontal_img;
    }

    @XmlAttribute
    public String getSquare_img() {
        return square_img;
    }

    public void setSquare_img(String square_img) {
        this.square_img = square_img;
    }

    @XmlAttribute
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @XmlAttribute
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @XmlAttribute
    public String getAdaptor() {
        return adaptor;
    }

    public void setAdaptor(String adaptor) {
        this.adaptor = adaptor;
    }

    @XmlAttribute
    public String getAdaptornew() {
        return adaptornew;
    }

    public void setAdaptornew(String adaptornew) {
        this.adaptornew = adaptornew;
    }

    @XmlAttribute
    public String getPlay_role() {
        return play_role;
    }

    public void setPlay_role(String play_role) {
        this.play_role = play_role;
    }

    @XmlAttribute
    public String getSubordinate_name() {
        return subordinate_name;
    }

    public void setSubordinate_name(String subordinate_name) {
        this.subordinate_name = subordinate_name;
    }

    @XmlAttribute
    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    @XmlAttribute
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlAttribute
    public String getCaption_language() {
        return caption_language;
    }

    public void setCaption_language(String caption_language) {
        this.caption_language = caption_language;
    }

    @XmlAttribute
    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @XmlAttribute
    public String getCopyright_range() {
        return copyright_range;
    }

    public void setCopyright_range(String copyright_range) {
        this.copyright_range = copyright_range;
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
    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    @XmlAttribute
    public String getView_len() {
        return view_len;
    }

    public void setView_len(String view_len) {
        this.view_len = view_len;
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
    public String getOffline_play_support() {
        return offline_play_support;
    }

    public void setOffline_play_support(String offline_play_support) {
        this.offline_play_support = offline_play_support;
    }

    @XmlAttribute
    public String getWatch_focus() {
        return watch_focus;
    }

    public void setWatch_focus(String watch_focus) {
        this.watch_focus = watch_focus;
    }

    @XmlAttribute
    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    @XmlAttribute
    public String getThird_asset_id() {
        return third_asset_id;
    }

    public void setThird_asset_id(String third_asset_id) {
        this.third_asset_id = third_asset_id;
    }

    @XmlAttribute
    public String getThird_expand() {
        return third_expand;
    }

    public void setThird_expand(String third_expand) {
        this.third_expand = third_expand;
    }

    @XmlAttribute
    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    @XmlAttribute
    public String getAssets_definition() {
        return assets_definition;
    }

    public void setAssets_definition(String assets_definition) {
        this.assets_definition = assets_definition;
    }

    @XmlAttribute
    public String getIs_updated() {
        return is_updated;
    }

    public void setIs_updated(String is_updated) {
        this.is_updated = is_updated;
    }

    @XmlAttribute
    public String getAssets_format() {
        return assets_format;
    }

    public void setAssets_format(String assets_format) {
        this.assets_format = assets_format;
    }

    @XmlAttribute
    public String getAssets_dimension() {
        return assets_dimension;
    }

    public void setAssets_dimension(String assets_dimension) {
        this.assets_dimension = assets_dimension;
    }

    @XmlAttribute
    public String getCopyright_area() {
        return copyright_area;
    }

    public void setCopyright_area(String copyright_area) {
        this.copyright_area = copyright_area;
    }

    @XmlAttribute
    public String getIndex_info() {
        return index_info;
    }

    public void setIndex_info(String index_info) {
        this.index_info = index_info;
    }

    @XmlAttribute
    public String getOnline_identify() {
        return online_identify;
    }

    public void setOnline_identify(String online_identify) {
        this.online_identify = online_identify;
    }

    @XmlAttribute
    public String getUs_level() {
        return us_level;
    }

    public void setUs_level(String us_level) {
        this.us_level = us_level;
    }

    @XmlAttribute
    public String getUk_level() {
        return uk_level;
    }

    public void setUk_level(String uk_level) {
        this.uk_level = uk_level;
    }

    @XmlAttribute
    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @XmlAttribute
    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    @XmlAttribute
    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @XmlAttribute
    public String getIs_needed_auth() {
        return is_needed_auth;
    }

    public void setIs_needed_auth(String is_needed_auth) {
        this.is_needed_auth = is_needed_auth;
    }

    @XmlAttribute
    public String getCite_id() {
        return cite_id;
    }

    public void setCite_id(String cite_id) {
        this.cite_id = cite_id;
    }

    @XmlAttribute
    public String getCite_cp_id() {
        return cite_cp_id;
    }

    public void setCite_cp_id(String cite_cp_id) {
        this.cite_cp_id = cite_cp_id;
    }

    @XmlAttribute
    public String getCopyright_begin_date() {
        return copyright_begin_date;
    }

    public void setCopyright_begin_date(String copyright_begin_date) {
        this.copyright_begin_date = copyright_begin_date;
    }

    @XmlAttribute
    public String getNns_collect_count() {
        return nns_collect_count;
    }

    public void setNns_collect_count(String nns_collect_count) {
        this.nns_collect_count = nns_collect_count;
    }

    @XmlAttribute
    public String getUser_score_count() {
        return user_score_count;
    }

    public void setUser_score_count(String user_score_count) {
        this.user_score_count = user_score_count;
    }

    @XmlAttribute
    public String getUser_score_arg() {
        return user_score_arg;
    }

    public void setUser_score_arg(String user_score_arg) {
        this.user_score_arg = user_score_arg;
    }

    @XmlAttribute
    public String getAssets_charge() {
        return assets_charge;
    }

    public void setAssets_charge(String assets_charge) {
        this.assets_charge = assets_charge;
    }

    @XmlAttribute
    public String getPreview_type() {
        return preview_type;
    }

    public void setPreview_type(String preview_type) {
        this.preview_type = preview_type;
    }

    @XmlAttribute
    public String getFusion_definition() {
        return fusion_definition;
    }

    public void setFusion_definition(String fusion_definition) {
        this.fusion_definition = fusion_definition;
    }

    @XmlAttribute
    public String getDouban_score() {
        return douban_score;
    }

    public void setDouban_score(String douban_score) {
        this.douban_score = douban_score;
    }
}

