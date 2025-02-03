package com.project.side.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.project.side.dto.UserDto;
import com.project.side.service.RegisterService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	@Autowired private RegisterService resgisterService;
	
	@RequestMapping(value = "/")
	public String main() {
		
		return "index";
	}
	
	// 게임시작
	@RequestMapping("/gameStart")  
	public String gameStart(HttpServletRequest request, Model model) {
		return "main";  
	}
	
	// 게임시작 시 자음,모음 랜덤 가져옴(기본 5개씩)
	@RequestMapping("/getSpelling")  
	@ResponseBody
	public List<JsonObject> getSpelling(HttpServletRequest request, Model model) {
	
		List<String> itemA = new ArrayList<>(Arrays.asList("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"));
		List<String> itemB = new ArrayList<>(Arrays.asList("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅔ"));
		
		Collections.shuffle(itemA);  
		List<String> spellingA = itemA.subList(0, 5);  
        
		Collections.shuffle(itemB);  // 리스트를 랜덤하게 섞음
		List<String> spellingB = itemB.subList(0, 5);  // 처음 N개 선택
        
		List<JsonObject> randomSepll = new ArrayList<>();
		
		
		for (int i = 0; i < 5; i++) {
			JsonObject obj = new JsonObject();
			obj.addProperty("spellingA", spellingA.get(i));
			obj.addProperty("spellingB", spellingB.get(i));
			randomSepll.add(obj);
		} 
		
		System.out.println(randomSepll);
		
		return randomSepll;  
	}
	
	// 답변확인
	@RequestMapping("/getAnswer")  
	@ResponseBody
	public List<JsonObject> getAnswer(HttpServletRequest request, Model model) {
	
		return randomSepll;  
	}
	
	
	
	
	//////////////////////////////////
	
	
	@RequestMapping("/toRegister")  
	public String toResgister(HttpServletRequest request, Model model) {
		
		return "register";  
	}
	
	
	@RequestMapping("/register")  
	public String resgister(HttpServletRequest request, Model model) {
		
		String userId = request.getParameter("userId");
		String passWord = request.getParameter("passWord");
		String name = request.getParameter("name");
		String age = request.getParameter("age");

		System.out.println(userId);
		System.out.println(passWord);
		System.out.println(name);
		System.out.println(age);
		
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setPassWord(passWord);
		userDto.setName(name);
		userDto.setAge(age);
		
		resgisterService.registerUser(userDto);
		
		return "index";  
	}
	

}
