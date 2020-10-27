package com.nrson.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nrson.test.dao.TestCodeDAO;
import com.nrson.test.dto.TestOneResponseDTO;
import com.nrson.test.dto.TestRequestDTO;
import com.nrson.test.dto.TestResponseDTO;
import com.nrson.test.transform.TestTransform;
import com.nrson.test.vo.TestRequestVO;
import com.nrson.test.vo.TestResponseVO;

@Service("TestService")
public class TestService {
	
	@Autowired
	private TestCodeDAO testCodeDAO;

	public TestResponseDTO getTestSelList() throws Exception {
		
		final List<TestResponseVO> resVo = testCodeDAO.selectCodeList();		
		
//		return TestResponseDTO.builder()
//				.codeList(resVo)
//				.codeListCnt(resVo.size())
//				.build();
		
		return TestTransform.transform(resVo);
	}
	
	
	public TestOneResponseDTO getTestSelOne(TestRequestDTO reqDto) throws Exception {
//		TestRequestVO reqVo = TestRequestVO.builder()
//                .ab1(reqDto.getAb1())
//                .ab2(reqDto.getAb2())
//                .ab3(reqDto.getAb3())
//                .build();
		
		TestRequestVO reqVo = TestTransform.transform(reqDto);
		
		final TestResponseVO resVo = testCodeDAO.selectCodeOne(reqVo);
		
//		TestOneResponseDTO toRes = TestOneResponseDTO.builder().build();
//		
//		if(resVo != null) {
//			toRes.setS_name(resVo.getS_name());
//			toRes.setF_name(resVo.getF_name());
//		}
//		
//		return toRes;
		return TestTransform.transform(resVo);
	}	
	
	@Transactional(rollbackFor = Exception.class)
	public void saveInsTestData(TestRequestDTO reqDto) throws Exception {
//		TestRequestVO reqVo = TestRequestVO.builder()
//                .ab1(reqDto.getAb1())
//                .ab2(reqDto.getAb2())
//                .ab3(reqDto.getAb3())
//                .build();	
		
		TestRequestVO reqVo = TestTransform.transform(reqDto);
		
		testCodeDAO.insertCode(reqVo);		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void updateTestData(TestRequestDTO reqDto) throws Exception {
//		TestRequestVO reqVo = TestRequestVO.builder()
//                .ab1(reqDto.getAb1())
//                .ab2(reqDto.getAb2())
//                .ab3(reqDto.getAb3())
//                .build();	
		
		TestRequestVO reqVo = TestTransform.transform(reqDto);
		
		testCodeDAO.updateCode(reqVo);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteTestData(TestRequestDTO reqDto) throws Exception {
//		TestRequestVO reqVo = TestRequestVO.builder()
//                .ab1(reqDto.getAb1())
//                .ab2(reqDto.getAb2())
//                .ab3(reqDto.getAb3())
//                .build();	
		
		TestRequestVO reqVo = TestTransform.transform(reqDto);
		
		testCodeDAO.deleteCode(reqVo);
	}


}
