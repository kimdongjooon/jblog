package com.poscodx.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// 사용자 회원가입.
	public boolean insert(UserVo userVo) {
		int count = sqlSession.insert("jblogUser.insert",userVo);
		return count ==1;
	}
	
	// 사용자 ID중복 확인.
	public String checkDuplicateById(String id) {
		return sqlSession.selectOne("jblogUser.checkDuplicateById",id); 
	}
	
	// 사용자 로그인.
	public UserVo findByEmailAndPassword(String id, String password) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		return sqlSession.selectOne("jblogUser.findByEmailAndPassword",map);
	
	}
	
	
	

}
