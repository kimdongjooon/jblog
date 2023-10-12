package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
		
	public List<CategoryVo> getCategory(BlogVo blogvo) {
		return categoryRepository.findByBlog_id(blogvo);
		
	}

	public void deleteCategory(Long category_id) {
		categoryRepository.deleteByCategory_id(category_id);
		
	}

	public List<CategoryVo> getCategoryAndCount(BlogVo blogvo) {
		return categoryRepository.findCountByBlog_id(blogvo);
	}

	public void addCategory(CategoryVo categorVo) {
		categoryRepository.addCategory(categorVo);
		
	}

	public Long getCategoryNo(String category) {
		return categoryRepository.getCategoryNo(category);
	}
	
}
