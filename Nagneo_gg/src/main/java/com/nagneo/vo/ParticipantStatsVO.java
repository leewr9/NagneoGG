package com.nagneo.vo;

public class ParticipantStatsVO {
	private int champLevel;
	private int deaths;
	private long totalDamageDealtToChampions;
	private int totalMinionsKilled;
	private int kills;
	private int assists;
	private int neutralMinionsKilled;
	
	public int getChampLevel() {
		return champLevel;
	}
	public void setChampLevel(int champLevel) {
		this.champLevel = champLevel;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public long getTotalDamageDealtToChampions() {
		return totalDamageDealtToChampions;
	}
	public void setTotalDamageDealtToChampions(long totalDamageDealtToChampions) {
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
	}
	public int getTotalMinionsKilled() {
		return totalMinionsKilled;
	}
	public void setTotalMinionsKilled(int totalMinionsKilled) {
		this.totalMinionsKilled = totalMinionsKilled;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public int getNeutralMinionsKilled() {
		return neutralMinionsKilled;
	}
	public void setNeutralMinionsKilled(int neutralMinionsKilled) {
		this.neutralMinionsKilled = neutralMinionsKilled;
	}
}
