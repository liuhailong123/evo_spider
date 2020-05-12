package com.frameworks.core.exception;

public class NotAllowUpdateException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAllowUpdateException() {
		super();
	}

	public NotAllowUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotAllowUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowUpdateException(String message) {
		super(message);
	}

	public NotAllowUpdateException(Throwable cause) {
		super(cause);
	}
	
}
