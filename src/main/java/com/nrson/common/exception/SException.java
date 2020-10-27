package com.nrson.common.exception;

public class SException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SException() {}
	
	public SException(String message) {
		super(message);
	}
	
	public SException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

}
