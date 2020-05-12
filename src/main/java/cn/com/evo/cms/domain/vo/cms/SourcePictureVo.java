package cn.com.evo.cms.domain.vo.cms;

public class SourcePictureVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    private String resolution;

    private SourceVo source;

    private String sourceRelId;

    private String random;

    private int type;//类型 1:横版 2:竖版

    private String url;

    private String size;

    private String ext;

    private String cloudPath;

    private String name;//资源名称

    private String typeName;//类型名称

    private int businessType;//业务类型 1封面

    private String sourceId;

    private String pictureId;

    public String getCloudPath() {
        return cloudPath;
    }

    public void setCloudPath(String cloudPath) {
        this.cloudPath = cloudPath;
    }

    public SourceVo getSource() {
        return source;
    }

    public void setSource(SourceVo source) {
        this.source = source;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getSourceRelId() {
        return sourceRelId;
    }

    public void setSourceRelId(String sourceRelId) {
        this.sourceRelId = sourceRelId;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}