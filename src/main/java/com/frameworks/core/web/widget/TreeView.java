package com.frameworks.core.web.widget;

import java.io.Serializable;

public class TreeView implements Serializable {

	private static final long serialVersionUID = 4802672541170524541L;

	public static final String ICON_FOLDER = "fa fa-folder fa-lg";
	public static final String ICON_ITEM = "fa fa-file fa-lg";
	
	public static enum NodeType{
		folder, item;
	}
	
	private String id;
	private String parent;
	private String text;
	private NodeType type;
	private String icon;
	private Boolean children;
	private State state;
	private String level;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Boolean getChildren() {
		return children;
	}

	public void setChildren(Boolean children) {
		this.children = children;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public static class State{
		private Boolean opened = false;
		private Boolean disabled = false;
		private Boolean selected = false;
		public Boolean getOpened() {
			return opened;
		}
		public void setOpened(Boolean opened) {
			this.opened = opened;
		}
		public Boolean getDisabled() {
			return disabled;
		}
		public void setDisabled(Boolean disabled) {
			this.disabled = disabled;
		}
		public Boolean getSelected() {
			return selected;
		}
		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
	}
}
