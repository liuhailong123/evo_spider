package com.frameworks.app.event;

import java.io.Serializable;

public class AppEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object source;

	public AppEvent(Object source) {
		super();
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

}
