package com.nagneo.mapper;

import java.util.ArrayList;

import com.nagneo.vo.BoardVO;

public interface BoardMapper {

	public void insert(BoardVO bVO);
	
	public ArrayList<BoardVO> selectOne(int no);
	
	public ArrayList<BoardVO> selectAll();
	
}
