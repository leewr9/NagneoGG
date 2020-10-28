package com.nagneo.service;

import com.nagneo.vo.ChampionVO;
import com.nagneo.vo.SpellVO;

public interface ChampionService {
	public void list();
	
	public ChampionVO champion(int id);
	
	public SpellVO spell(int id);
}
