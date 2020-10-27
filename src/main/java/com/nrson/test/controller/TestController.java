package com.nrson.test.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nrson.common.dto.StandardResponseDTO;
import com.nrson.common.exception.SBizException;
import com.nrson.test.dto.TestOneResponseDTO;
import com.nrson.test.dto.TestRequestDTO;
import com.nrson.test.dto.TestResponseDTO;
import com.nrson.test.service.TestService;

@RestController
public class TestController {

	@Resource(name = "TestService")
	private final TestService testService;

	// 화면 처리
	@RequestMapping(value = "/welcome.do")
	public ModelAndView welcome() {
		return new ModelAndView("welcome");
	}

	// 화면 처리
	@RequestMapping(value = "/testview.do")
	public ModelAndView testview() {
		return new ModelAndView("testview");
	}

	// 다건 조회
	@RequestMapping(value = "/testsellist", method = RequestMethod.GET)
	public StandardResponseDTO<TestResponseDTO> getTestSelList() throws Exception {
		return StandardResponseDTO.success(testService.getTestSelList());
	}

	// 단건 조회
	@RequestMapping(value = "/testselone", method = RequestMethod.GET)
	public StandardResponseDTO<TestOneResponseDTO> getTestSelOne(@Valid TestRequestDTO reqDto) throws Exception {
		return StandardResponseDTO.success(testService.getTestSelOne(reqDto));
	}

	// insert
	@RequestMapping(value = "/testsave", method = RequestMethod.POST)
	public StandardResponseDTO<?> saveTestData(@Valid TestRequestDTO reqDto) throws Exception {
		testService.saveInsTestData(reqDto);
		return StandardResponseDTO.success();
	}

	// update
	@RequestMapping(value = "/testupdate", method = RequestMethod.PUT)
	public StandardResponseDTO<?> updateTestData(@Valid TestRequestDTO reqDto) throws Exception {
		testService.updateTestData(reqDto);
		return StandardResponseDTO.success();
	}

	// delete
	@RequestMapping(value = "/testdelete", method = RequestMethod.DELETE)
	public StandardResponseDTO<?> deleteTestData(@Valid TestRequestDTO reqDto) throws Exception {
		testService.deleteTestData(reqDto);
		return StandardResponseDTO.success();
	}

	@RequestMapping(value = "/testerror", method = RequestMethod.GET)
	public StandardResponseDTO<?> testerror() throws Exception {
		if (true) {
			throw new SBizException("ERROR_CODE");
		}

//		try {
//			
//		}catch(IOException e) {
		// 에러처리 방법
//			throw new SBizException("ERROR_CODE");
//		    throw new SBizException("ERROR_CODE", "에러 내용");
//		    throw new SBizException("ERROR_CODE", "에러 내용", e);
//		}

		return StandardResponseDTO.success();
	}

	@Value("${test.abc}")
	private String abc;

	@RequestMapping(value = "/testprof", method = RequestMethod.GET)
	public StandardResponseDTO<?> testprofile() throws Exception {

		System.out.println("==================>" + abc);

		return StandardResponseDTO.success();
	}

	// 2. 서비스를 사용하기 위한 생성자를 만들어줍니다.
	public TestController(TestService testService) {
		this.testService = testService;
	}

	// 1) 메인 페이지로 접근
	// 2) Feign Client가 /testfeign 으로 get 호출
	// 3) 반환값 받고 메인에서 보여줍니다.
	@GetMapping("/")
	public String main() {
		return testService.testFeign();
	}

	// Feign Client 요청에 응답을 주기 위한 컨트롤러
	@GetMapping("/testfeign")
	public String testFeign() {
		return "Hello Feign Cleint~ 찡긋";
	}

}
