package cn.com.evo.cms.domain.vo.app;

import com.frameworks.core.vo.BaseVo;

public class AppVo extends BaseVo {
	private static final long serialVersionUID = 1L;

	private int classify;

	private Long collectCount;

	private Long downCount;

	private String info;

	private String name;

	private String spellLong;

	private String spellShort;

	private int type;

	private String code;

	public AppVo() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getClassify() {
		return this.classify;
	}

	public void setClassify(int classify) {
		this.classify = classify;
	}

	public Long getCollectCount() {
		return this.collectCount;
	}

	public void setCollectCount(Long collectCount) {
		this.collectCount = collectCount;
	}

	public Long getDownCount() {
		return this.downCount;
	}

	public void setDownCount(Long downCount) {
		this.downCount = downCount;
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

	public String getSpellLong() {
		return this.spellLong;
	}

	public void setSpellLong(String spellLong) {
		this.spellLong = spellLong;
	}

	public String getSpellShort() {
		return this.spellShort;
	}

	public void setSpellShort(String spellShort) {
		this.spellShort = spellShort;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
