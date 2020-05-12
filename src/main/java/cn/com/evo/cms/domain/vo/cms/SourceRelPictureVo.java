package cn.com.evo.cms.domain.vo.cms;

public class SourceRelPictureVo extends com.frameworks.core.vo.BaseVo{
	private static final long serialVersionUID = 1L;
	private String pictureId;
	private String sourceId;
	private String name;//资源名称
    private String resolution;
    private String random;
    private int type;//类型 1:横版 2:竖版
	private String url;
	private Long size;
	private String ext;
	private String cloudPath;	
	private int businessType;//业务类型 1封面 9焦点
	
	public String getPictureId() {
		return pictureId;
	}
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getCloudPath() {
		return cloudPath;
	}
	public void setCloudPath(String cloudPath) {
		this.cloudPath = cloudPath;
	}
	public int getBusinessType() {
		return businessType;
	}
	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

}