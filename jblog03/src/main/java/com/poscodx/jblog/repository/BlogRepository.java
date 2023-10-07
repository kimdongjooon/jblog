package com.poscodx.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	@Autowired
	SqlSession sqlSession; 
	
	public BlogVo find(UserVo vo) {
		System.out.println("sql 실행 및 UserVo:"+vo);
		
		return sqlSession.selectOne("blog.find",vo);
	}
	
	public boolean update(BlogVo vo) {
		System.out.println("sql update실행 및 BlogVo:"+vo);
		int count = sqlSession.update("blog.update",vo);
		return count == 1;
	}

	public String findByBlog_id(UserVo uservo) {
		return sqlSession.selectOne("blog.findByBlog_id",uservo);
	}
}
