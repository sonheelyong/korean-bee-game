package com.project.side.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.side.dto.UserDto;
import com.project.side.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
    private SqlSession sqlSession;

	@Override
	public void registerUser(UserDto userDto) {
		sqlSession.insert("RegisterUser.insertUser", userDto);
	}
	
	

}
