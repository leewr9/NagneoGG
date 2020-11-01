package com.nagneo.mapper;

import java.util.ArrayList;

import com.nagneo.vo.ChampionSkillVO;
import com.nagneo.vo.ChampionSkinVO;
import com.nagneo.vo.ChampionVO;
import com.nagneo.vo.RuneVO;
import com.nagneo.vo.SpellVO;

public interface ChampionMapper {
	public ArrayList<ChampionVO> allChampion();
	
	public ArrayList<SpellVO> allSpell();
	
	public ArrayList<RuneVO> allRune();
	
	public ArrayList<ChampionSkinVO> championSkin(ChampionVO cVO);
	
	public ArrayList<ChampionSkillVO> championSkill(ChampionVO cVO);
	
	public void insert1(ChampionVO cVO);
	
	public void insert2(ChampionSkinVO csVO);
	
	public void insert3(ChampionSkillVO cskVO);
}
