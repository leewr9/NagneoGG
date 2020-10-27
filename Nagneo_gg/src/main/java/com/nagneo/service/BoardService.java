package com.nagneo.service;

import java.util.ArrayList;

import com.nagneo.vo.BoardVO;

public interface BoardService {
	
	public void insert(BoardVO bVO);
	
	public ArrayList<BoardVO> selectOne(int no);
	
	public ArrayList<BoardVO> selectAll();

}
