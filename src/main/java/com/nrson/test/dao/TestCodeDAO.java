package com.nrson.test.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nrson.test.vo.TestRequestVO;
import com.nrson.test.vo.TestResponseVO;

@Repository("TestCodeDAO")
public class TestCodeDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<TestResponseVO> selectCodeList() {
		return sqlSessionTemplate.selectList("TB_TEST.selectTestList");
	}

	public TestResponseVO selectCodeOne(TestRequestVO reqVo) {
		return sqlSessionTemplate.selectOne("TB_TEST.selectTestOne", reqVo);
	}	
	
	public int insertCode(TestRequestVO reqVo) {
		return sqlSessionTemplate.insert("TB_TEST.insertTestData", reqVo);
	}
	
	public int updateCode(TestRequestVO reqVo) {
		return sqlSessionTemplate.update("TB_TEST.updateTestData", reqVo); 
	}
	
	public int deleteCode(TestRequestVO reqVo) {
		return sqlSessionTemplate.delete("TB_TEST.deleteTestData", reqVo);
	}
}
