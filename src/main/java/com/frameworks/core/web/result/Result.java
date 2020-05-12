package com.frameworks.core.web.result;

import java.io.Serializable;

public interface Result extends Serializable {
	public static enum Status{
		OK, ERROR, INVALID, TIMEOUT,CONFIRM
	}
}
