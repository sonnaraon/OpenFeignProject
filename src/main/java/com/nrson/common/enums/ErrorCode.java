package com.nrson.common.enums;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	DEFAULT("SVRER00000", "SYSTEM 오류 입니다."),
	SVRER10001("SVRER10001", "오류~~ 오류~~")
	;	

	private final String code;
	private final String errMsg;
	
	private static final Map<String, ErrorCode> codeMap =
			Stream.of(values()).collect(Collectors.toMap(ErrorCode::getCode, Function.identity()));
	
	public static ErrorCode getEnum(String code) {
		return Optional.ofNullable(codeMap.get(code)).orElseGet(() -> DEFAULT);
	}
}
