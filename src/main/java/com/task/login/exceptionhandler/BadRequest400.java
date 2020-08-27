package com.task.login.exceptionhandler;

/* class contains the messages and cause when 400 error code is thrown */

public class BadRequest400 extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequest400() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadRequest400(String msg) {
		super(msg);
	}

	public BadRequest400(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BadRequest400(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BadRequest400(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
