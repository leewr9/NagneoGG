package com.nagneo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagneo.mapper.ChampionMapper;
import com.nagneo.vo.ChampionVO;

@Service
public class ChampionServiceImpl implements ChampionService {
	private ChampionMapper cM;
	private ArrayList<ChampionVO> list = null;

	@Autowired
	private SqlSession sqlsession;

	public void init() {
		cM = sqlsession.getMapper(ChampionMapper.class);
	}

	@Override
	public void list() {
		if (list == null) {
			init();
			this.list = cM.allChampion();
		}
	}

	@Override
	public ChampionVO champion(int id) {
		long start = System.currentTimeMillis();
		if (list == null) {
			System.out.println("리스트 가져오기");
			list();
		}
			
		for (ChampionVO i : list) {
			if (i.getKey() == id) {
				long end = System.currentTimeMillis();
				System.out.println("이미지 받아오기 "+(end - start) / 1000.0);
				return i;
			}
		}
		return null;
	}

}
