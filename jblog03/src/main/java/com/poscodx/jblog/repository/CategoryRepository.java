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
	private SqlSession sqlsession;
	
	public List<CategoryVo> findByBlog_id(BlogVo blogvo) {
		return sqlsession.selectList("category.findByBlog_id",blogvo);
	}

}
