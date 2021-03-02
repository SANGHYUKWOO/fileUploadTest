package com.example.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.MemberVO;

@Repository
public class InsertTestDAOImpl implements InsertTestDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.example.mapper.insertTestMapper";
	
	@Override
	public int InsertTest(String param) throws Exception {
		param = param.replaceAll("\\[", "(");
		param = param.replaceAll("\\]", ")");
		param = param.replaceAll(",", "),(");
		param = param.replaceAll(" ", "");
		//System.out.println("ret  >"+param);
		
		//return sqlSession.selectList(Namespace+".selectMember");
		MemberVO memberVO = new MemberVO();
		memberVO.setId(param);
		//return sqlSession.insert(Namespace+".insertTest",param);
		return sqlSession.insert(Namespace+".insertTest",memberVO);
	}

}
