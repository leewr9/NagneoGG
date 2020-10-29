package com.nagneo.vo;

import java.util.ArrayList;

public class PlayerVO {
	private String summonerName;
	private String summonerId;
	private ArrayList<LeagueEntryVO> lVO;

	public ArrayList<LeagueEntryVO> getlVO() {
		return lVO;
	}

	public void setlVO(ArrayList<LeagueEntryVO> lVO) {
		this.lVO = lVO;
	}


	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}

	public String getSummonerId() {
		return summonerId;
	}

	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}

}
