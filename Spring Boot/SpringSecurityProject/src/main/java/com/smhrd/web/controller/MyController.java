package com.smhrd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.web.entity.DataMember;
import com.smhrd.web.repo.DataMemberRepository;



@Controller
public class MyController {
//	DB 연결과 관련된 mapping 작성
	@Autowired
	DataMemberRepository repository;
	@Autowired
	PasswordEncoder encoder;
	
//	@PostMapping("/login-process")
//	public String main(DataMember member, HttpSession session) {
//		DataMember loginMember = repository.findByIdAndPw(member.getId(), member.getPw());
//		// HttpSession을 사용하고 싶다면, 파라미터 수집
//		session.setAttribute("login",loginMember);
//		return "redirect:/main";
//	}
	
	@PostMapping("join-process")
	public String postMethodName(DataMember member) {
		DataMember createMember =DataMember.createMember(member.getId(),member.getPw(), member.getRoles(),encoder);
		repository.save(createMember);
		return "redirect:/";
	}
	
	@GetMapping("/main")
	public String main(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("id", user.getUsername());
		model.addAttribute("roles", user.getAuthorities());
		return "main";
	}
	
}
