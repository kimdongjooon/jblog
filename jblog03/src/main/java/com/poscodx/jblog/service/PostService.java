package com.poscodx.jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getPost(String blogId, Optional<Long> categoryNo) {
		return postRepository.findByBlogIdAndCategoryNo(blogId,categoryNo);
	}

	public void addPost(PostVo postVo) {
		postRepository.addPostByPostVo(postVo);
		
	}

	
}
