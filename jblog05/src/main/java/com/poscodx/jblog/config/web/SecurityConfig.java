package com.poscodx.jblog.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poscodx.jblog.security.AuthInterceptor;
import com.poscodx.jblog.security.LoginInterceptor;
import com.poscodx.jblog.security.LogoutInterceptor;

@Configuration
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer {
		
	//
	// Interceptors
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
	}
	
	
}
