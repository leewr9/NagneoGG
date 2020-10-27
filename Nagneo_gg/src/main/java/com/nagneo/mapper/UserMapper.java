package com.nagneo.mapper;

import com.nagneo.vo.UserVO;

public interface UserMapper {

	public void insert(UserVO uVO);

	public UserVO selectOne(UserVO uVO);

	public UserVO idInfo(UserVO uVO);

	public UserVO pwInfo(UserVO uVO);
}
