package com.nagneo.mapper;

import java.util.ArrayList;

import com.nagneo.vo.ChampionVO;
import com.nagneo.vo.RuneVO;
import com.nagneo.vo.SpellVO;

public interface ChampionMapper {
	public ArrayList<ChampionVO> allChampion();
	
	public ArrayList<SpellVO> allSpell();
	
	public ArrayList<RuneVO> allRune();
}
