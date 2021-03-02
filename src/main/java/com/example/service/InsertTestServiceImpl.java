package com.example.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.dao.InsertTestDAO;
import com.example.dao.MemberDAO;
import com.example.dto.MemberVO;

@Service
public class InsertTestServiceImpl implements InsertTestService {

	@Inject
	private InsertTestDAO dao;
	
	@Override
	public int InsertTest(String parm) throws Exception {

		return dao.InsertTest(parm);
	}

}
 