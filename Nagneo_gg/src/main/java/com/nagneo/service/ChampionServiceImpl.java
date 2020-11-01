package com.nagneo.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagneo.mapper.ChampionMapper;
import com.nagneo.vo.ChampionSkillVO;
import com.nagneo.vo.ChampionSkinVO;
import com.nagneo.vo.ChampionVO;
import com.nagneo.vo.RuneVO;
import com.nagneo.vo.SpellVO;

@Service
public class ChampionServiceImpl implements ChampionService {
	private ChampionMapper cM;
	private ArrayList<ChampionVO> championList = null;
	private ArrayList<SpellVO> spellList = null;
	private ArrayList<RuneVO> runeList = null;

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
			for(ChampionVO i : championList) {
				i.setSkill(cM.championSkill(i));
				i.setSkin(cM.championSkin(i));
			}
		}
		if (spellList == null) {
			this.spellList = cM.allSpell();
		}
		if (runeList == null) {
			this.runeList = cM.allRune();
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

	@Override
	public RuneVO rune(int id) {
		if (runeList == null) {
			list();
		}
		for (RuneVO i : runeList) {
			if (i.getKey() == id) {
				return i;
			}
		}
		return null;
	}

	@Override
	public void insert1(ChampionVO cVO) {
		init();
		cM.insert1(cVO);
	}

	@Override
	public void insert2(ChampionSkinVO csVO) {
		init();
		cM.insert2(csVO);
	}

	@Override
	public void insert3(ChampionSkillVO cskVO) {
		init();
		cM.insert3(cskVO);
	}
}
