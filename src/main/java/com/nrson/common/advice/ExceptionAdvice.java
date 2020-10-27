package com.nrson.common.advice;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.nrson.common.component.MessageComponent;
import com.nrson.common.dto.StandardResponseDTO;
import com.nrson.common.enums.ValidCode;
import com.nrson.common.exception.SBizException;
import com.nrson.common.exception.SException;
import com.nrson.common.exception.SSysException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionAdvice {
	private static final String DEFAULT_ERROR = "SVRER00000";
	private static final String[] EMPTY = new String[] {};
	

	@Autowired
	private MessageComponent messageComponent;
	
	@ExceptionHandler(SBizException.class)
	protected StandardResponseDTO<?> handleSBizException(final SBizException e) {
		final StandardResponseDTO<?> responseDto =
				messageComponent.makeErrorMessage(e.getCode(), e.getMessage(), e.getParam(), e);
		log.error("handle SBizException: code={}, message={}, content={}, log={}",
		           responseDto.getCode(), responseDto.getMessage(), responseDto.getContent(),
		           responseDto.getErrorlog());
		return responseDto;
	}
	
	
	@ExceptionHandler(SSysException.class)
	protected StandardResponseDTO<?> handleSSysException(final SSysException e) {
		final StandardResponseDTO<?> responseDto =
				messageComponent.makeErrorMessage(e.getCode(), e.getMessage(), e.getParam(), e);
		log.error("handle SSysException: code={}, message={}, content={}, log={}",
                   responseDto.getCode(), responseDto.getMessage(), responseDto.getContent(),
                   responseDto.getErrorlog());	
		return responseDto;
	}
	
	@ExceptionHandler(SException.class)
	protected StandardResponseDTO<?> handleSException(final SException e) {
		final StandardResponseDTO<?> responseDto = handleException(e);
		log.error("handle SException: code={}, message={}, log={}",
                   responseDto.getCode(), responseDto.getMessage(), e);
		return responseDto;
	}
	
	@ExceptionHandler(Exception.class)
	protected StandardResponseDTO<?> handleException(final Exception e) {
		final String message = e.getMessage();
		final StandardResponseDTO<?> responseDto =
				messageComponent.makeErrorMessage(DEFAULT_ERROR, message, EMPTY, e);
		log.debug("handle Exception: code={}, message={}, content={}, log={}",
                   responseDto.getCode(), responseDto.getMessage(), responseDto.getContent(),
                   responseDto.getErrorlog());
		return responseDto;
	}

	
	@ExceptionHandler(RuntimeException.class)
	protected StandardResponseDTO<?> handleRuntimeException(final RuntimeException e) {
		final String message = e.getMessage();
		final StandardResponseDTO<?> responseDto =
				messageComponent.makeErrorMessage(DEFAULT_ERROR, message, EMPTY, e);
		log.debug("handle Exception: code={}, message={}, content={}, log={}",
                   responseDto.getCode(), responseDto.getMessage(), responseDto.getContent(),
                   responseDto.getErrorlog());
		return responseDto;
	}	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected StandardResponseDTO<?> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
		final BindingResult bindResult;
		
		try {
			bindResult = Optional.ofNullable(e.getBindingResult())
					           .orElseThrow(() -> new IllegalStateException(e));
		}catch(final IllegalStateException e1) {
			return handleRuntimeException(e1);
		}
		
		final List<FieldError> fieldErrorList = Optional.ofNullable(bindResult.getFieldErrors())
				.orElseGet(() -> Collections.emptyList());
		
		final FieldError fieldError;
		try {
			fieldError = fieldErrorList.stream()
					.findFirst()
					.orElseThrow(() -> new IllegalStateException(e));
		}catch(final IllegalStateException e1) {
			return handleRuntimeException(e1);
		}
		
		log.info("cause exception: {}", e.getMessage());
		
		final String message = fieldError.getDefaultMessage();
		final String[] params = new String[] {
				fieldError.getField()
		};
		
		final StandardResponseDTO<?> responseDto = messageComponent
				.makeErrorMessage(fieldError.getCode(), message, params, e);
		log.error("handle MethodArgumentNotValidException: code={}, message={}, content={}, log={}",
                   responseDto.getCode(), responseDto.getMessage(), responseDto.getContent(),
                   responseDto.getErrorlog());	
		return responseDto;
	}
	
	
	@ExceptionHandler(BindException.class)
	protected StandardResponseDTO<?> handleBindException(BindException e) {
		final BindingResult bindResult = e.getBindingResult();
		
		final List<FieldError> fieldErrorList = Optional.ofNullable(bindResult.getFieldErrors())
				.orElseGet(() -> Collections.emptyList());
		
		final FieldError fieldError;
		try {
			fieldError = fieldErrorList.stream()
					.findFirst()
					.orElseThrow(() -> new IllegalStateException(e));
		}catch(final IllegalStateException e1) {
			return handleRuntimeException(e1);
		}
		
		log.info("cause exception: {}", e.getMessage());
		
		final String message = fieldError.getDefaultMessage();
		
		final StandardResponseDTO<?> responseDto = StandardResponseDTO.fail(ValidCode.getEnum(fieldError.getCode()).getMsgCode(), message);
		log.error("handle BindException: code={}, message={}, content={}, log={}",
		           responseDto.getCode(), responseDto.getMessage(), e);
		
		return responseDto;
	}
	
}
