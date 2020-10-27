package com.nagneo.mapper;

import java.util.ArrayList;

import com.nagneo.vo.UserVO;

public interface UserMapper {
	
	public void insert(UserVO uVO);
	
	public UserVO selectOne(UserVO uVO);
	
	public UserVO nextNo();
}
