package cn.com.evo.cms.web.api.vo;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

public class EpisodeChildApiVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String name="";

	private int number;

	public EpisodeChildApiVo(ContentApiVo content) {
		this.id = content.getId();
		if(StringUtils.isNotBlank(content.getName())){
			this.name = content.getName();
		}
		this.number=content.getSort();
	}

	public EpisodeChildApiVo(String id,String name,Integer sort) {
		this.id = id;
		if(StringUtils.isNotBlank(name)){
			this.name = name;
		}
		this.number=sort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
