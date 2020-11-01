package com.nagneo.vo;

import java.util.ArrayList;

public class ChampionVO {
	private int key;
	private String engid;
	private String korid;
	private String title;

	private ArrayList<ChampionSkinVO> skin = new ArrayList<ChampionSkinVO>();
	private ArrayList<ChampionSkillVO> skill = new ArrayList<ChampionSkillVO>();

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getEngid() {
		return engid;
	}

	public void setEngid(String engid) {
		this.engid = engid;
	}

	public String getKorid() {
		return korid;
	}

	public void setKorid(String korid) {
		this.korid = korid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<ChampionSkinVO> getSkin() {
		return skin;
	}

	public void setSkin(ArrayList<ChampionSkinVO> skin) {
		this.skin = skin;
	}

	public void setSkill(ArrayList<ChampionSkillVO> skill) {
		this.skill = skill;
	}

	public ArrayList<ChampionSkillVO> getSkill() {
		return skill;
	}

}
