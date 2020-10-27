package com.nagneo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagneo.mapper.UserMapper;
import com.nagneo.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	private UserMapper uM;

	@Autowired
	private SqlSession sqlsession;

	public void init() {
		uM = sqlsession.getMapper(UserMapper.class);
	}

	@Override
	public void insert(UserVO uVO) {
		init();
		uM.insert(uVO);
	}

	@Override
	public String find(String idpw, UserVO uVO) {
		init();
		if (idpw.equals("id")) {
			UserVO userVO = uM.idInfo(uVO);
			return userVO.getId();
		} else {
			UserVO userVO = uM.pwInfo(uVO);
			return userVO.getPw();
		}
	}

	@Override
	public boolean selectOne(UserVO uVO) {
		init();
		UserVO userVO = uM.selectOne(uVO);
		if (userVO != null) {
			if (userVO.getPw().equals(uVO.getPw())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean idChk(UserVO uVO) {
		init();
		UserVO userVO = uM.selectOne(uVO);
		if (userVO == null)
			return true;
		return false;
	}

}
