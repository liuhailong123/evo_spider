package com.frameworks.core.exception;

/**
 * 不存在
 * @author cao.yong
 *
 */
public class NotExistedException extends ServiceException{

	private static final long serialVersionUID = 5333972948005449387L;

	public NotExistedException() {
		super();
	}

	public NotExistedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotExistedException(String message) {
		super(message);
	}

	public NotExistedException(Throwable cause) {
		super(cause);
	}

	
}
