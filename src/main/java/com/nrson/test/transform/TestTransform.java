package com.nrson.test.transform;

import java.util.List;

import com.nrson.test.dto.TestOneResponseDTO;
import com.nrson.test.dto.TestRequestDTO;
import com.nrson.test.dto.TestResponseDTO;
import com.nrson.test.vo.TestRequestVO;
import com.nrson.test.vo.TestResponseVO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestTransform {
	
	public static TestResponseDTO transform(List<TestResponseVO> resVo) {
		return TestResponseDTO.builder()
				.codeList(resVo)
		        .codeListCnt(resVo.size())
		        .page(1)
		        .total(1)
		        .id("")
		        .build();
	}
	
	public static TestOneResponseDTO transform(TestResponseVO resVo) {
		TestOneResponseDTO toRes = TestOneResponseDTO.builder().build();
		
		if(resVo != null) {
			toRes.setS_name(resVo.getS_name());
			toRes.setF_name(resVo.getF_name());
		}
		
		return toRes;
	}
	
	
	public static TestRequestVO transform(TestRequestDTO dto) {
		return TestRequestVO.builder()
                .ab1(dto.getAb1())
                .ab2(dto.getAb2())
                .ab3(dto.getAb3())
                .build();
	}

}
