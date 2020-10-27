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
public enum ValidCode {
	
	NOT_BLANK("NotBlank", ValidCode.SVRER00001),
	NOT_EMPTY("NotEmpty", ValidCode.SVRER00001),
	NOT_NULL("NotNull", ValidCode.SVRER00001),
	SIZE("Size", ValidCode.SVRER00002),
	MAX("Max", ValidCode.SVRER00002),
	POSITVE("Positive", ValidCode.SVRER00003),
	POSITIVE_OR_ZERO("PositiveOrZero", ValidCode.SVRER00003),
	PATTERN("Pattern", ValidCode.SVRER00004),
	EMAIL("Email", ValidCode.SVRER00004),
	DEFAULT("Default", ValidCode.SVRER00000)
	;
	
	private final String code;
	private final String msgCode;
	
	private static final Map<String, ValidCode> codeMap =
			Stream.of(values()).collect(Collectors.toMap(ValidCode::getCode, Function.identity()));
	
	public static ValidCode getEnum(String code) {
		return Optional.ofNullable(codeMap.get(code)).orElseGet(() -> DEFAULT);
	}
	
	private static final String SVRER00000 = "SVRER00000";
	private static final String SVRER00001 = "SVRER00001";
	private static final String SVRER00002 = "SVRER00002";
	private static final String SVRER00003 = "SVRER00003";
	private static final String SVRER00004 = "SVRER00004";
}
