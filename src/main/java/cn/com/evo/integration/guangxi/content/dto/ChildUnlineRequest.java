package cn.com.evo.integration.guangxi.content.dto;import cn.com.evo.integration.common.ConstantFactory;import cn.com.evo.integration.guangxi.common.ConstantEnum;import com.alibaba.fastjson.JSONObject;import lombok.Data;import lombok.EqualsAndHashCode;/** * @Description: 分集上下线接口请求对象 * @author: lu.xin * @create: 2019-05-15 1:52 PM **/@Data@EqualsAndHashCode(callSuper = false)public class ChildUnlineRequest {    /**     * 接口     */    private String func = "unline_video_clip";    /**     * 主媒资id     */    private String assetsId;    /**     * 主媒资id类型     */    private int assetsIdType = 1;    /**     * 分集id     */    private String id;    /**     * 分集id类型     */    private int indexIdType = 1;    /**     * cpId     */    private String cpId = ConstantFactory.map.get(ConstantEnum.cp_id.getKey());    /**     * 媒资类型 0：点播；1：直播     */    private String assetsVideoType = "0";    /**     * 0:上线     * 1:下线     */    private int assetsClipUnline;    /**     * 是否强制上下线     * 0：是     * 1：否     */    private int isForceUnline = 0;    /**     * 分集上下线     *     * @param assetsId         主媒资id     * @param id               分集id     * @param assetsClipUnline 0:上线     *                         1:下线     */    public ChildUnlineRequest(String assetsId, String id, int assetsClipUnline) {        this.assetsId = assetsId;        this.id = id;        this.assetsClipUnline = assetsClipUnline;    }    /**     * 转换为json对象     *     * @return     */    public JSONObject toJson() {        JSONObject jsonObject = new JSONObject();        jsonObject.put("func", this.func);        jsonObject.put("assets_id", this.assetsId);        jsonObject.put("assets_Id_Type", this.assetsIdType);        jsonObject.put("id", this.id);        jsonObject.put("index_Id_Type", this.indexIdType);        jsonObject.put("cp_id", this.cpId);        jsonObject.put("assets_video_type", this.assetsVideoType);        jsonObject.put("assets_clip_unline", this.assetsClipUnline);        jsonObject.put("is_force_unline", this.isForceUnline);        return jsonObject;    }}