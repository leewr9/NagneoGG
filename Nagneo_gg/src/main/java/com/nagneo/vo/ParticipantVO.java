package com.nagneo.vo;

public class ParticipantVO {
	private int championId;
	private int teamId;
	private ParticipantStatsVO stats;
	private int spell1Id;
	private int spell2Id;
	private ChampionVO champion;

	public ChampionVO getChampion() {
		return champion;
	}

	public void setChampion(ChampionVO champion) {
		this.champion = champion;
	}

	public int getSpell1Id() {
		return spell1Id;
	}

	public void setSpell1Id(int spell1Id) {
		this.spell1Id = spell1Id;
	}

	public int getSpell2Id() {
		return spell2Id;
	}

	public void setSpell2Id(int spell2Id) {
		this.spell2Id = spell2Id;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getChampionId() {
		return championId;
	}

	public void setChampionId(int championId) {
		this.championId = championId;
	}

	public ParticipantStatsVO getStats() {
		return stats;
	}

	public void setStats(ParticipantStatsVO stats) {
		this.stats = stats;
	}

}
