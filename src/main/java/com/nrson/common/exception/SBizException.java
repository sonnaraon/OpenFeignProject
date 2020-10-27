package com.nrson.common.exception;

import org.apache.commons.lang3.StringUtils;

public class SBizException extends SException {
	
	private static final long serialVersionUID = 1L;
	
	private final String message;
	private final String code;
	private final String[] param;

	public SBizException(String code) {
		this.code = code;
		message = StringUtils.EMPTY;
		param = null;
	}
	
	public SBizException(String code, String message) {
		this.code = code;
		this.message = message;
		param = null;
	}
	
	public SBizException(String code, String[] params) {
		this.code = code;
		message = StringUtils.EMPTY;
		param = params;
	}
	
	public SBizException(String code, String message, Throwable rootCause) {
		super(String.format("code[%s] message[%s]", code, message), rootCause);
		this.code = code;
		this.message = message;
		param = null;
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
