package com.smhrd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.web.entity.DataMember;
import com.smhrd.web.repo.DataMemberRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class MyController {
//	DB 연결과 관련된 mapping 작성
	@Autowired
	DataMemberRepository repository;
	
	@PostMapping("/login-process")
	public String main(DataMember member, HttpSession session) {
		DataMember loginMember = repository.findByIdAndPw(member.getId(), member.getPw());
		// HttpSession을 사용하고 싶다면, 파라미터 수집
		session.setAttribute("login",loginMember);
		return "redirect:/main";
	}
	
	@PostMapping("join-process")
	public String postMethodName(DataMember member) {
		repository.save(member);
		return "redirect:/";
	}
	
}
