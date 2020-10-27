package com.nrson.common.exception;

public class SSysException extends SException {

	private static final long serialVersionUID = 1L;
	
	private String message = "";
	private String code = "";
	private String[] param;
	
	public SSysException(String code) {
		this.code = code;
	}
	
	public SSysException(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public SSysException(String code, String[] param) {
		this.code = code;
		this.param = param;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public String[] getParam() {
		return param;
	}	
	
}
