package com.nagneo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagneo.mapper.ChampionMapper;
import com.nagneo.vo.ChampionVO;
import com.nagneo.vo.SpellVO;

@Service
public class ChampionServiceImpl implements ChampionService {
	private ChampionMapper cM;
	private ArrayList<ChampionVO> championList = null;
	private ArrayList<SpellVO> spellList = null;

	@Autowired
	private SqlSession sqlsession;

	public void init() {
		cM = sqlsession.getMapper(ChampionMapper.class);
	}

	@Override
	public void list() {
		init();
		if (championList == null) {
			this.championList = cM.allChampion();
		}
		if (spellList == null) {
			this.spellList = cM.allSpell();
		}
	}

	@Override
	public ChampionVO champion(int id) {
		if (championList == null) {
			list();
		}

		for (ChampionVO i : championList) {
			if (i.getKey() == id) {
				return i;
			}
		}
		return null;
	}

	@Override
	public SpellVO spell(int id) {
		if (spellList == null) {
			list();
		}

		for (SpellVO i : spellList) {
			if (i.getKey() == id) {
				return i;
			}
		}
		return null;
	}

	@Override
	public ArrayList<ChampionVO> allCohampion() {
		if (championList == null) {
			list();
		}
		return championList;
	}
}
