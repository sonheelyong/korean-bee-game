package com.project.side.controller;

import java.io.IOException;
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
import com.project.side.service.CheckService;
import com.project.side.service.RegisterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
	
	@Autowired private RegisterService resgisterService;
	@Autowired private CheckService checkService;
	
	//랜덤 자모음 전역변수 설정
	List<Character> spellA = new ArrayList<>();
	List<Character> spellB = new ArrayList<>();
	
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
	public List<JsonObject> getSpelling(HttpServletRequest request) {
	
//		List<Character> itemAB = new ArrayList<>(Arrays.asList("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"));
//		List<Character> itemBB = new ArrayList<>(Arrays.asList("ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅔ"));
		
		List<Character> itemA = Arrays.asList('ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅍ','ㅌ','ㅎ');
		List<Character> itemB = Arrays.asList('ㅏ','ㅑ','ㅓ','ㅕ','ㅗ','ㅛ','ㅜ','ㅠ','ㅡ','ㅣ');
		
		Collections.shuffle(itemA);  
		List<Character> spellingA = itemA.subList(0, 5);
		spellA = spellingA;
        
		Collections.shuffle(itemB);  // 리스트를 랜덤하게 섞음
		List<Character> spellingB = itemB.subList(0, 5);  // 처음 N개 선택
		spellB = spellingB;
        
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
	public JsonObject getAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JsonObject requestJson = new JsonObject();
		
		String answer = request.getParameter("answer");
		System.out.println(answer);
		
		// 답변 확인
		String checkRequest = checkService.checkAnswer(spellA,spellB,answer);
		// 0: 자,모음 포함(정상) , 1: 미포함 , 2: 한글아님
		if(!"0".equals(checkRequest)) {
			requestJson.addProperty("word", checkRequest);
		}
		
		else {		
		// 사전api 호출
		apiTest apiTest = new apiTest();		
		requestJson = apiTest.apiTest2(answer);
		
		System.out.println("메인컨트롤러" + requestJson);
		}
		
		return requestJson;
		
	}
	
	
	
	
	
	//////////////////////////////////
	//
	
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
