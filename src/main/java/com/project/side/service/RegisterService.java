package com.project.side.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.side.dao.impl.RegisterDaoImpl;
import com.project.side.dto.UserDto;

@Service
public class RegisterService {
		
		@Autowired private RegisterDaoImpl registerDaoImpl;

		
		public void registerUser(UserDto userDto) {
			
			registerDaoImpl.registerUser(userDto);
		}
		 

	

}
