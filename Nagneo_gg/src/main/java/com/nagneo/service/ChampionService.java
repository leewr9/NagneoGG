package com.nagneo.service;

import java.util.ArrayList;

import com.nagneo.vo.ChampionSkillVO;
import com.nagneo.vo.ChampionSkinVO;
import com.nagneo.vo.ChampionVO;
import com.nagneo.vo.RuneVO;
import com.nagneo.vo.SpellVO;

public interface ChampionService {
	public void list();
	
	public ChampionVO champion(int id);
	
	public ArrayList<ChampionVO> allCohampion();
	
	public SpellVO spell(int id);
	
	public RuneVO rune(int id);
	
	public void insert1(ChampionVO cVO);
	
	public void insert2(ChampionSkinVO csVO);

	public void insert3(ChampionSkillVO cskVO);
}
