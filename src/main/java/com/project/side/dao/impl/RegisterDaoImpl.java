package com.project.side.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.project.side.dao.RegisterDao;
import com.project.side.dto.UserDto;
import com.project.side.service.RegisterService;

@Repository("RegisterDao")
public class RegisterDaoImpl implements RegisterDao{
	
	@Autowired
    private SqlSession sqlSession;

	@Override
	public void registerUser(UserDto userDto) {
		sqlSession.insert("RegisterUser.insertUser", userDto);
	}
	
	

}
