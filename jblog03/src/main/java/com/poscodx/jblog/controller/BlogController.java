package com.poscodx.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{id:(?!assets).*}") 
// 단 asset도 사용했을때도 들어와서 PathVariable옵션에서 제외시킬수있는 정규 표현식 찾기.
public class BlogController {

	@RequestMapping({"", "/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index( 
			// Optional 기능 사용해보기.
			@PathVariable("id") String blogId,
			@PathVariable(required=false) Optional<Long> categoryNo, //null 일수있음.,
			@PathVariable(required=false) Optional<Long> postNo) { //null 일수있음.,
		System.out.println("blogId :"+ blogId);
		System.out.println("categoryNo :"+ categoryNo);
		System.out.println("postNo :"+ postNo);
		
		// 입력된 id가 회원db에서 조회시 없을때는 
		
		// 있을때는 홈페이지에 접속.
		
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/admin/basic")
	public String adminBasic() {
		return "BlogController.adminBasic()";
	}
	
}
