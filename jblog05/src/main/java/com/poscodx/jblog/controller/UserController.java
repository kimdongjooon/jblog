package com.poscodx.jblog.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(
			@ModelAttribute @Valid UserVo userVo, 
			BindingResult result, 
			Model model) {
		
		String checkID = userService.checkDuplicate(userVo.getId());
		boolean assets = "assets".equals(userVo.getId());
		
		if(result.hasErrors() || checkID != null || assets) {		
			model.addAllAttributes(result.getModel());
			model.addAttribute("checkId", checkID);
			if(assets) {
				model.addAttribute("assets",1);
			}
			return "user/join";
		}
		
		userService.join(userVo);
		blogService.addblog(userVo);
		return "user/joinsuccess";
	}
	

	
	// 로그인, 로그아웃 Auth인터프리터 사용해서 세션으로 로그인기능 구현하기.
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserVo userVo) {
		return "user/login";
	}
	
	
	
	
	
	
}
