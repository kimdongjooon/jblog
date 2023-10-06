package com.poscodx.jblog.service;

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

}
