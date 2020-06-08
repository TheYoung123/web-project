package com.Share.exception;

@SuppressWarnings("serial")
public class BadwordException extends Exception {

	public BadwordException() {
		super();
	}

	public BadwordException(String message) {
		super(message);
	}

	public BadwordException(Throwable cause) {
		super(cause);
	}

	public BadwordException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadwordException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
