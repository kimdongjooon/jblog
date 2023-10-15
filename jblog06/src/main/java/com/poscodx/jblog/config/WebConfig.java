package com.poscodx.jblog.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poscodx.jblog.interceptor.BlogInterceptor;
import com.poscodx.jblog.security.AuthInterceptor;
import com.poscodx.jblog.security.LoginInterceptor;
import com.poscodx.jblog.security.LogoutInterceptor;

@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {
	//
	// Security Interceptors
	//
	
	@Bean
	public HandlerInterceptor LoginInterceptor() {
		return new LoginInterceptor();
	}
	
	@Bean
	public HandlerInterceptor LogoutInterceptor() {
		return new LogoutInterceptor();
	}
	
	@Bean
	public HandlerInterceptor AuthInterceptor() {
		return new AuthInterceptor();
	}
	
	// BlogInterceptor
	@Bean
	public HandlerInterceptor blogInterceptor() {
		return new BlogInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(LoginInterceptor())
			.addPathPatterns("/user/auth");
		
		registry
			.addInterceptor(LogoutInterceptor())
			.addPathPatterns("/user/logout","/blog/logout");
		
		registry
			.addInterceptor(AuthInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/assets/**","/user/auth","/user/logout","/blog/logout");
		
		registry
		.addInterceptor(blogInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/assets/**");
	}
	
	
		


}
