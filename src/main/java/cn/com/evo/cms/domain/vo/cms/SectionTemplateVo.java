package cn.com.evo.cms.domain.vo.cms;

public class SectionTemplateVo extends com.frameworks.core.vo.BaseVo{
	private static final long serialVersionUID = 1L;

	private int enable;

	private String info;

	private String name;

	private String content;

	private String templateCode;

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

}