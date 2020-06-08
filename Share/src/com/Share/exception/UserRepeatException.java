package com.Share.exception;
/**
 * 用户名重复异常
 *
 */
@SuppressWarnings("serial")
public class UserRepeatException extends Exception {

	public UserRepeatException() {
		super();
	}

	public UserRepeatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserRepeatException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserRepeatException(String message) {
		super(message);
	}

	public UserRepeatException(Throwable cause) {
		super(cause);
	}
	
}
