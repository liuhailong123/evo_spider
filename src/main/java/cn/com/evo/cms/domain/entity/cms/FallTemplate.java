package cn.com.evo.cms.domain.entity.cms;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;


/**
 * The persistent class for the `cms_fall_ template` database table.
 * 
 */
@Entity
@Table(name="`cms_fall_ template`")
@NamedQuery(name="FallTemplate.findAll", query="SELECT f FROM FallTemplate f")
public class FallTemplate extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	private int count;

	private String imgUrl;

	private String info;

	private String name;

	//bi-directional many-to-one association to FallTemplatePosition
	@OneToMany(mappedBy="fallTemplate")
	private List<FallTemplatePosition> fallTemplatePositions=Lists.newArrayList();

	public FallTemplate() {
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public List<FallTemplatePosition> getCmsFallTemplatePositions() {
		return this.fallTemplatePositions;
	}

	public void setCmsFallTemplatePositions(List<FallTemplatePosition> cmsFallTemplatePositions) {
		this.fallTemplatePositions = cmsFallTemplatePositions;
	}

	public FallTemplatePosition addCmsFallTemplatePosition(FallTemplatePosition cmsFallTemplatePosition) {
		getCmsFallTemplatePositions().add(cmsFallTemplatePosition);
		cmsFallTemplatePosition.setFallTemplate(this);

		return cmsFallTemplatePosition;
	}

	public FallTemplatePosition removeCmsFallTemplatePosition(FallTemplatePosition cmsFallTemplatePosition) {
		getCmsFallTemplatePositions().remove(cmsFallTemplatePosition);
		cmsFallTemplatePosition.setFallTemplate(null);

		return cmsFallTemplatePosition;
	}

}