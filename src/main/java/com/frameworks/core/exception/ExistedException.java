package com.frameworks.core.exception;

/**
 * 已存在
 * @author cao.yong
 *
 */
public class ExistedException extends ServiceException {

	private static final long serialVersionUID = 8113663955113262262L;

	public ExistedException() {
		super();
	}

	public ExistedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistedException(String message) {
		super(message);
	}

	public ExistedException(Throwable cause) {
		super(cause);
	}
	
}
