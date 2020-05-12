package cn.com.evo.cms.web.api.vo;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

import cn.com.evo.cms.domain.entity.cms.AuxiliaryFallTemplate;
import cn.com.evo.cms.domain.entity.cms.Column;

public class WaterfallApiVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 瀑布流标题
	 */
	private String title;
	/**
	 * 下标
	 */
	private int index;
	/**
	 * 是否显示标题
	 */
	private int isShowTitle;
	/**
	 * 内容list
	 */
	private List<WaterApiVo> datas = Lists.newArrayList();

	public WaterfallApiVo(Column column,List<WaterApiVo> datas, AuxiliaryFallTemplate auxiliaryFallTemplate){
		this.title=column.getName();
		this.index=Integer.valueOf(column.getId());
		this.isShowTitle=auxiliaryFallTemplate.getIsShowTitle();
		this.datas=datas;
	}
	
	public int getIsShowTitle() {
		return isShowTitle;
	}

	public void setIsShowTitle(int isShowTitle) {
		this.isShowTitle = isShowTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<WaterApiVo> getDatas() {
		return datas;
	}

	public void setDatas(List<WaterApiVo> datas) {
		this.datas = datas;
	}

}
