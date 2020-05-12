package com.frameworks.core.exception;

public class IncorrectPasswordException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6270588145663531202L;

	public IncorrectPasswordException() {
		super();
	}

	public IncorrectPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IncorrectPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectPasswordException(String message) {
		super(message);
	}

	public IncorrectPasswordException(Throwable cause) {
		super(cause);
	}

	
}
