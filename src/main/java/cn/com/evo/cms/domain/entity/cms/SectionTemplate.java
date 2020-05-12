package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 专题模版表对应实体
 * @author shilinxiao
 *
 */
@Entity
@Table(name = "cms_section_template")
@NamedQuery(name = "SectionTemplate.findAll", query = "SELECT c FROM SectionTemplate c")
public class SectionTemplate extends com.frameworks.core.entity.BaseEntity {
	private static final long serialVersionUID = 1L;

	private int enable;

	private String info;

	private String name;

	private String content;

	private String templateCode;

	public SectionTemplate() {
	}

	public int getEnable() {
		return this.enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return this.name;
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
		return this.templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

}