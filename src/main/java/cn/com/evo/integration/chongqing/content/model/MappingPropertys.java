package cn.com.evo.integration.chongqing.content.model;

/**
 * @author rf
 * @date 2019/6/4
 */
public class MappingPropertys {
    /**
     * 映射时的类型
     * 当Mapping的ParentType为Picture时：
     * 0: 缩略图
     * 1: 海报
     * 2: 剧照
     * 3: 图标
     * 4: 标题图
     * 5: 广告图
     * 6: 草图
     * 7: 背景图
     * 9: 频道图片
     * 10: 频道黑白图片
     * 11: 频道Logo
     * 12: 频道名字图片
     * 99: 其他
     */
    private String Type;
    /**
     * 序列号
     * 当Mapping关系涉及Picture时，此字段为必填，展示顺序有上有平台保证；
     * 当Mapping关系涉及和Series和Program间绑定时，Sequence（作为集号）必须填写；
     * 当Mapping关系涉及Category和Series/Program间绑定时，对于内容展示次序为内容发布时间的场景，Sequence传固定值；
     */
    private String Sequence;
    /**
     * 当Mapping的ParentType为SVOD时, 标识SVOD节目的服务起始时间
     *  (YYYYMMDDHH24MiSS)
     */
    private String ValidStart;
    /**
     * 当Mapping的ParentType为SVOD时, 标识SVOD节目的服务终止时间
     * (YYYYMMDDHH24MiSS)
     */
    private String ValidEnd;
    /**
     * 0: 成功
     * 其他: 错误代码
     */
    private String Result;
    /**
     * 错误描述
     */
    private String ErrorDescription;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSequence() {
        return Sequence;
    }

    public void setSequence(String sequence) {
        Sequence = sequence;
    }

    public String getValidStart() {
        return ValidStart;
    }

    public void setValidStart(String validStart) {
        ValidStart = validStart;
    }

    public String getValidEnd() {
        return ValidEnd;
    }

    public void setValidEnd(String validEnd) {
        ValidEnd = validEnd;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getErrorDescription() {
        return ErrorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        ErrorDescription = errorDescription;
    }
}
