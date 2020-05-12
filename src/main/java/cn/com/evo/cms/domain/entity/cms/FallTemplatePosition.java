package cn.com.evo.cms.domain.entity.cms;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the `cms_fall_ template_ position` database table.
 * 
 */
@Entity
@Table(name="`cms_fall_ template_ position`")
@NamedQuery(name="FallTemplatePosition.findAll", query="SELECT f FROM FallTemplatePosition f")
public class FallTemplatePosition extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	private int position;

	private int positionX;

	private int positionY;

	//bi-directional many-to-one association to FallTemplate
	@ManyToOne
	@JoinColumn(name="parentId")
	private FallTemplate fallTemplate;

	public FallTemplatePosition() {
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPositionX() {
		return this.positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return this.positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public FallTemplate getFallTemplate() {
		return fallTemplate;
	}

	public void setFallTemplate(FallTemplate fallTemplate) {
		this.fallTemplate = fallTemplate;
	}


}