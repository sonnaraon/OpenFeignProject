package com.nrson.common.dto;

import com.nrson.common.util.ExceptionUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class StandardResponseDTO<T> {
	private static final int MAX_LENGTH_OF_STACK_TRACK_LOG = 700;
	
	private String code;
	private String message;
	private T content;
	private String errorlog;
	
	public static <T> StandardResponseDTO<T> success() {
		return new StandardResponseDTO<>(ResponseCode.OK);
	}
	
	public static <T> StandardResponseDTO<T> success(T responseData) {
		log.info("Success ResponseData:: {}", responseData);
		return new StandardResponseDTO<>(ResponseCode.OK, responseData);
	}
	
	public static <T> StandardResponseDTO<T> fail(String responseCode, String errorMessageDetail) {
		return new StandardResponseDTO<>(responseCode, errorMessageDetail);
	}
	
	public static <T> StandardResponseDTO<T> fail(String responseCode, String errorMessageDetail, Throwable e) {
		final StandardResponseDTO<T> responseDto = new StandardResponseDTO<>(responseCode, errorMessageDetail);
		responseDto.setErrorLogByThrowable(e);
		return responseDto;
	}	
	
	public static <T> StandardResponseDTO<T> fail(ResponseCode responseCode, T responseData) {
		return new StandardResponseDTO<>(responseCode, responseData);
	}
	
	public static <T> StandardResponseDTO<T> fail(String responseCode, String errorMessageDetail, T responseData) {
		return new StandardResponseDTO<>(responseCode, errorMessageDetail, responseData);
	}
	
	
	StandardResponseDTO(ResponseCode responseCode, T content) {
		this.code = responseCode.getCode();
		this.message = responseCode.getMessage();
		this.content = content;
	}
	
	StandardResponseDTO(ResponseCode responseCode) {
		this.code = responseCode.getCode();
		this.message = responseCode.getMessage();
	}
	
	StandardResponseDTO(String responseCode, String responseMessage) {
		this.code = responseCode;
		this.message = responseMessage;
	}
	
	StandardResponseDTO(String responseCode, String responseMessage, T content) {
		this.code = responseCode;
		this.message = responseMessage;
		this.content = content;
	}	
	
	
	void setErrorLogByThrowable(Throwable e) {
		final StringBuilder logBuf = ExceptionUtil.getStackTraceStrings(e);
		
		log.error("ErrorLog : {}", logBuf);
		
		final int logLength = logBuf.length();
		final int remains = logLength - MAX_LENGTH_OF_STACK_TRACK_LOG;
		if(remains > 0) {
			logBuf.setLength(MAX_LENGTH_OF_STACK_TRACK_LOG);
			logBuf.append("...").append(logLength - MAX_LENGTH_OF_STACK_TRACK_LOG);
		}
		
		errorlog = logBuf.toString();
	}

}
