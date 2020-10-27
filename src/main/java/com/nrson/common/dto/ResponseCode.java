package com.nrson.common.dto;

public enum ResponseCode {
	OK("200", "success"),
	INTERNAL_SERVER_ERROR("500", "Response message system error.");
	
	private final String code;
	private final String message;
	
	private ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	

}
