package com.poscodx.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
	//  
	@RequestMapping("")
	public String main() {
		return "main/index";
	}
	
	// 검색
	@RequestMapping("key")
	public String searchBlog(
			@RequestParam("keyword") String keyword
			) {
		return "redirect:/"+keyword;
	}
}
