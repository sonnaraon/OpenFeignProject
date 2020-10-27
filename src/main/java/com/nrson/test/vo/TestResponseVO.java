package com.nrson.test.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class TestResponseVO {
	private String s_name;
	private String f_name;
}
