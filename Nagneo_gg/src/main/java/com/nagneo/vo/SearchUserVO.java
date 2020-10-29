package com.nagneo.vo;

public class SearchUserVO {
	private int participantId;
	private int teamId;
	private String win;
	private int champLevel;
	private int kills;
	private int deaths;
	private int assists;
	private String kda;
	private int totalMinionsKilled;
	private long totalDamageDealtToChampions;
	private SpellVO spell1Id;
	private SpellVO spell2Id;
	private ChampionVO champion;
	private String gameDuration;
	private String minuteMinionsKilled;
	
	public String getMinuteMinionsKilled() {
		return minuteMinionsKilled;
	}

	public void setMinuteMinionsKilled(long minute) {
		double kills = (double) ((double) totalMinionsKilled
				/ ((double) (minute / 60) + ((double) ((double) minute % 60) / 60)));
		this.minuteMinionsKilled = String.format("%.1f", kills);
	}

	public ChampionVO getChampion() {
		return champion;
	}

	public void setChampion(ChampionVO champion) {
		this.champion = champion;
	}

	public SpellVO getSpell1Id() {
		return spell1Id;
	}

	public void setSpell1Id(SpellVO spell1Id) {
		this.spell1Id = spell1Id;
	}

	public SpellVO getSpell2Id() {
		return spell2Id;
	}

	public void setSpell2Id(SpellVO spell2Id) {
		this.spell2Id = spell2Id;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public int getChampLevel() {
		return champLevel;
	}

	public void setChampLevel(int champLevel) {
		this.champLevel = champLevel;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

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

	public int getTotalMinionsKilled() {
		return totalMinionsKilled;
	}

	public void setTotalMinionsKilled(int totalMinionsKilled) {
		this.totalMinionsKilled = totalMinionsKilled;
	}

	public long getTotalDamageDealtToChampions() {
		return totalDamageDealtToChampions;
	}

	public void setTotalDamageDealtToChampions(long totalDamageDealtToChampions) {
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
	}

	public String getGameDuration() {
		return gameDuration;
	}

	public void setGameDuration(String gameDuration) {
		this.gameDuration = gameDuration;
	}

}
