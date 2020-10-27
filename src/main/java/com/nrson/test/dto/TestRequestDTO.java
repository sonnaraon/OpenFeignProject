package com.nrson.test.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestRequestDTO {
	
	@NotEmpty(message="필수 입력 항목 입니다.")
	private String ab1;
	
	private String ab2;
	
	private String ab3;

}
