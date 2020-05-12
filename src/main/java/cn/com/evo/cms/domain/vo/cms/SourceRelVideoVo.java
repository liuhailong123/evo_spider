package cn.com.evo.cms.domain.vo.cms;

public class SourceRelVideoVo extends com.frameworks.core.vo.BaseVo{
	private static final long serialVersionUID = 1L;
	private String videoId;
	private String name;
	private String ext;
	private String size;
	private String time;
	private String resolution;
	private String sourceId;
	private int type;//类型 1:2D【平面】 2:3D【立体】 3:VR【全景】
	private String typeName;//类型名称
	private int definition;//	清晰度 1:4K 2:蓝光 3:超清 4:高清 5:标清 6:手机
	private String definitionName;//清晰度名称
	private String url;
	
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getDefinition() {
		return definition;
	}
	public void setDefinition(int definition) {
		this.definition = definition;
	}
	public String getDefinitionName() {
		return definitionName;
	}
	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}