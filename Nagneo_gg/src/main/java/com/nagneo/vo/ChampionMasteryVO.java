package com.nagneo.vo;

public class ChampionMasteryVO {
	private long championId;
	private int championLevel;
	private int championPoints;
	private ChampionVO champion;

	public ChampionVO getChampion() {
		return champion;
	}

	public void setChampion(ChampionVO champion) {
		this.champion = champion;
	}

	public long getChampionId() {
		return championId;
	}

	public void setChampionId(long championId) {
		this.championId = championId;
	}

	public int getChampionLevel() {
		return championLevel;
	}

	public void setChampionLevel(int championLevel) {
		this.championLevel = championLevel;
	}

	public int getChampionPoints() {
		return championPoints;
	}

	public void setChampionPoints(int championPoints) {
		this.championPoints = championPoints;
	}

}
