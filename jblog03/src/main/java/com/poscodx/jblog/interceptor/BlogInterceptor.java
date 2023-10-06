package com.poscodx.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poscodx.jblog.security.BlogUpdate;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.UserVo;

public class BlogInterceptor implements HandlerInterceptor {
	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("BlogInterceptor : 실행. ");
		// 1. handler 종류 확인.
		if(!(handler instanceof HandlerMethod)) {
			// DefaultServletHandler가 처리하는 경우(정적 자원, /assets/**)
			System.out.println("DefaultServletHandler ");
			return true;
		}
		
		
		//3. BlogVo가 없으면 세션 등록.
		HttpSession session = request.getSession();
		BlogVo blogVo = (BlogVo)session.getAttribute("BlogVo");
		System.out.println("BlogVo interceptor :  "+ blogVo);
		
		
		if(blogVo == null) {
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			blogVo = blogService.getSite(authUser);
			session.setAttribute("blogVo", blogVo);
			return true;
		}
		
		
		
		return true;
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		// BlogVo가 있을 때 admin에서 update 실행 후 BlogVo 셋팅.
	
		//1. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		
		//2. Handler Method의 @BlogUpdate 가져오기.
		BlogUpdate blogUpdate  = handlerMethod.getMethodAnnotation(BlogUpdate.class);
		
		if(blogUpdate != null) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			BlogVo blogVo = blogService.getSite(authUser);
			session.setAttribute("blogVo", blogVo);
		}
	}
	
}
