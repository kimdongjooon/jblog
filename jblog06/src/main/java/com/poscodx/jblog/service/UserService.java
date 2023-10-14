package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo userVo) {
		userRepository.insert(userVo);
	}
	
	public UserVo getUser(String id, String password) {
		return userRepository.findByEmailAndPassword(id, password);
	}
	
	public String checkDuplicate(String id) {
		return userRepository.checkDuplicateById(id);
	
	}
	
	
	
	
	
	
}
