package com.frameworks.core.exception;

public class NotAllowDeleteException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1877753244127323374L;

	public NotAllowDeleteException() {
		super();
	}

	public NotAllowDeleteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotAllowDeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowDeleteException(String message) {
		super(message);
	}

	public NotAllowDeleteException(Throwable cause) {
		super(cause);
	}

}
