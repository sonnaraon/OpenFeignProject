package com.nrson.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class TestOneResponseDTO {
	private String s_name;
	private String f_name;
}
