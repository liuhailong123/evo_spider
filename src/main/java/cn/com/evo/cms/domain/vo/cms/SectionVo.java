package cn.com.evo.cms.domain.vo.cms;

public class SectionVo extends com.frameworks.core.vo.BaseVo{
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String templateCode;
	
	private String templateName;
	
	private String previewUrl;
	
	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}