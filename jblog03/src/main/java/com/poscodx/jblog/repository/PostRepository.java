package com.poscodx.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	public List<PostVo> findByBlogIdAndCategoryNo(String blogId, Optional<Long> categoryNo) {
		Map<String,Object> map = new HashMap<>();
		map.put("blog_id", blogId);
		if(categoryNo.isEmpty()) {
			map.put("category_no", "");
		}else {
			map.put("category_no", categoryNo.get());
		}
		System.out.println("map:"+map);
		return sqlSession.selectList("post.findByBlogIdAndCategoryNo",map);
	}
	public void addPostByPostVo(PostVo postVo) {
		sqlSession.insert("post.addPostByPostVo",postVo);
		
	}
	

}
