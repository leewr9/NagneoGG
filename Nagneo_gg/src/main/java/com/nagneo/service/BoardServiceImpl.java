package com.nagneo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagneo.mapper.BoardMapper;
import com.nagneo.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	private ArrayList<BoardVO> bList;
	private BoardMapper bM;
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(BoardVO bVO) {
		bM = sqlSession.getMapper(BoardMapper.class);
		bM.insert(bVO);
	}

	@Override
	public ArrayList<BoardVO> selectOne(int no) {
		bM = sqlSession.getMapper(BoardMapper.class);
		bList = bM.selectOne(no);
		return bList;
	}

	@Override
	public ArrayList<BoardVO> selectAll() {
		bM = sqlSession.getMapper(BoardMapper.class);
		bList = bM.selectAll();
		return bList;
	}

}
