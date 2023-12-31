package com.poscodx.jblog.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.UserVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public BlogVo getSite(UserVo vo) {
		return blogRepository.find(vo);
		
	}


	public void updateBlog(BlogVo blogvo) {
		blogRepository.update(blogvo);
		
	}


	public String checkBlogId(UserVo uservo) {
		return blogRepository.findByBlog_id(uservo);
		
	}


	public void addblog(UserVo userVo) {
		blogRepository.addBlogByBlog_id(userVo);
		
	}

}
