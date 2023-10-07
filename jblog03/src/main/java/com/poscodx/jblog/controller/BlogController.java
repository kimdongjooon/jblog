package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.security.BlogUpdate;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.CategoryService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/{blog_id:^(?!assets$|user$|blog$).*}") 
// 단 asset도 사용했을때도 들어와서 PathVariable옵션에서 제외시킬수있는 정규 표현식 찾기.
public class BlogController {
	// 스프링 컨테이너 
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	
	@RequestMapping({"", "/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index( 
			// Optional 기능 사용해보기.
			@PathVariable("blog_id") String blogId,
			@PathVariable(required=false) Optional<Long> categoryNo, //null 일수있음.,
			@PathVariable(required=false) Optional<Long> postNo,
			Model model) { //null 일수있음.,
		System.out.println("blogId :"+ blogId);
		System.out.println("categoryNo :"+ categoryNo);
		System.out.println("postNo :"+ postNo);
		
		
		
		// 있을때는 그래도 통과.
		UserVo uservo = new UserVo();
		
		uservo.setId(blogId);
		
		// 입력된 id가 회원db에서 조회시 없을때는 404 에러 페이지 및 로그인 메인페이지로 이동.
		String checkId = blogService.checkBlogId(uservo);
		if(checkId == null) {
			return "main/index";
		}
		
		// *** 회원이 존재할경우 
		// 1. blogVo 데이터 추가.
		BlogVo blogvo = blogService.getSite(uservo);
		model.addAttribute("blogVo",blogvo);
		
		// 2. categoryvo 데이터추가.
		List<CategoryVo> categoryList = categoryService.getCategory(blogvo);
		for(CategoryVo vo: categoryList) {
			System.out.println("카테고리 리스트: "+vo);
		}
		model.addAttribute("categoryList",categoryList);
		
		// 3. postvo 데이터 추가.
		return "blog/main";
	}
	
	
	//블로그 기본 관리 페이지.
	@RequestMapping("/admin/basic")
	public String adminBasic(Model model) {
		model.addAttribute("menu_option","basic");
		return "blog/admin-basic";
	}
	
	@BlogUpdate
	@RequestMapping(value = "/admin/basic/update", method=RequestMethod.POST)
	public String main(
			@RequestParam("logo_image") MultipartFile file,
			BlogVo blogvo) {
		System.out.println("블로그 업데이트실행 id:"+blogvo.getBlog_id());
		
		
		// 이미지 url 셋팅
		String url = fileUploadService.restore(file);
		blogvo.setImage(url);
		

		blogService.updateBlog(blogvo);
		
//		BlogVo blog = applicationContext.getBean(BlogVo.class);
//		
//		// 성공할때 servletContext에서 sitevo를 바꿔줘야함.		
//		servletContext.setAttribute("blogVo", blogvo);
//		
//		BeanUtils.copyProperties(blogvo, blog);
		
//		return "redirect:/"+blogvo.getBlog_id()+"/admin/basic";
		return "blog/admin-basic";
	}
	
	
	//블로그 기본 관리 페이지.
	@RequestMapping("/admin/category")
	public String adminCategory(Model model) {
		model.addAttribute("menu_option","category");
		return "blog/admin-category";
	}
	
	// 카테고리 추가 및 삭제
	
	//블로그 기본 관리 페이지.
	@RequestMapping("/admin/write")
	public String adminWrite(Model model) {
		model.addAttribute("menu_option","write");
		return "blog/admin-write";
	}
	
}
