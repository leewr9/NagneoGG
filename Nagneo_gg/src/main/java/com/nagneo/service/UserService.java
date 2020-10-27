package com.nagneo.service;

import com.nagneo.vo.UserVO;

public interface UserService {
	
	public void insert(UserVO uVO);
	
	public String find(String idpw, UserVO uVO);
	
	public boolean selectOne(UserVO uVO);
	
	public boolean idChk(UserVO uVO);
	
}
