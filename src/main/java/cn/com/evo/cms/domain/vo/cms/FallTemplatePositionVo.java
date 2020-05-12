package cn.com.evo.cms.domain.vo.cms;

import com.frameworks.core.vo.BaseVo;

public class FallTemplatePositionVo extends BaseVo {
	private static final long serialVersionUID = 1L;

	private int position;

	private int positionX;

	private int positionY;

	private FallTemplateVo fallTemplate;

	public FallTemplatePositionVo() {
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public FallTemplateVo getCmsFallTemplate() {
		return fallTemplate;
	}

	public void setCmsFallTemplate(FallTemplateVo cmsFallTemplate) {
		this.fallTemplate = cmsFallTemplate;
	}

}