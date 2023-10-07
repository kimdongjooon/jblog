package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> findByBlog_id(BlogVo blogvo) {
		return sqlSession.selectList("category.findByBlog_id",blogvo);
	}

	public List<CategoryVo> findCountByBlog_id(BlogVo blogvo) {
		return sqlSession.selectList("category.findCountByBlog_id",blogvo);
	}

	public void deleteByCategory_id(Long category_id) {
		sqlSession.delete("category.deleteByCategory_id",category_id);
		
	}

	public void addCategory(CategoryVo categorVo) {
		sqlSession.insert("category.addCategory",categorVo);
		
	}

	public Long getCategoryNo(String category) {
		return sqlSession.selectOne("category.getCategoryNo",category);
	}

}
