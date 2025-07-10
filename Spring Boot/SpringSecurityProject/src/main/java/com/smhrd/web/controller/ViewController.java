package com.smhrd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	

	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/userPage")
	public String userPage() {
		return "userPage";
	}
	
	@GetMapping("/adminPage")
	public String adminPage() {
		return "adminPage";
	}
	

	
}
