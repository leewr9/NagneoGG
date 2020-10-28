package com.nagneo.vo;

public class ParticipantStatsVO {
	private int champLevel;
	private int kills;
	private int deaths;
	private int assists;
	private long totalDamageDealtToChampions;
	private int totalMinionsKilled;
	private int neutralMinionsKilled;
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int item6;
	private int goldEarned;
	private int minions;
	private String kda;
	
	public String getKda() {
		return kda;
	}

	public void setKda() {
		if (deaths == 0) {
			this.kda = "Perfect";
		} else {
			double kdas = (double) ((double) (kills + assists) / deaths);
			this.kda = String.format("%.2f", kdas);
		}
	}
	
	public int getItem0() {
		return item0;
	}
	public void setItem0(int item0) {
		this.item0 = item0;
	}
	public int getItem1() {
		return item1;
	}
	public void setItem1(int item1) {
		this.item1 = item1;
	}
	public int getItem2() {
		return item2;
	}
	public void setItem2(int item2) {
		this.item2 = item2;
	}
	public int getItem3() {
		return item3;
	}
	public void setItem3(int item3) {
		this.item3 = item3;
	}
	public int getItem4() {
		return item4;
	}
	public void setItem4(int item4) {
		this.item4 = item4;
	}
	public int getItem5() {
		return item5;
	}
	public void setItem5(int item5) {
		this.item5 = item5;
	}
	public int getItem6() {
		return item6;
	}
	public void setItem6(int item6) {
		this.item6 = item6;
	}
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
		setMinions(getMinions() + totalMinionsKilled);
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
		setMinions(getMinions() + neutralMinionsKilled);
	}
	public int getGoldEarned() {
		return goldEarned;
	}
	public void setGoldEarned(int goldEarned) {
		this.goldEarned = goldEarned;
	}
	public int getMinions() {
		return minions;
	}
	public void setMinions(int minions) {
		this.minions = minions;
	}
}
