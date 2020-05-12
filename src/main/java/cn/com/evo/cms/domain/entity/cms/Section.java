package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 专题
 * 
 * @author shilinxiao
 *
 */
@Entity
@Table(name = "cms_section")
@NamedQuery(name = "Section.findAll", query = "SELECT a FROM Section a")
public class Section extends com.frameworks.core.entity.BaseEntity {
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String templateCode;
	
	public Section() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}


}