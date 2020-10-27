package com.nrson.test.dto;

import java.util.List;

import com.nrson.test.vo.TestResponseVO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class TestResponseDTO {
	
	private List<TestResponseVO> codeList;
	private int codeListCnt;
	private int page;
	private int total;
	private String id;

}
