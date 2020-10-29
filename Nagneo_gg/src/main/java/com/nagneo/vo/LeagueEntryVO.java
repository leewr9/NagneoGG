package com.nagneo.vo;

public class LeagueEntryVO {
	private String summonerId;
	private String summonerName;
	private String queueType;
	private String tier = "UNRANKED";
	private String rank = "X";
	private int leaguePoints = 0;
	private int wins = 0;
	private int losses = 0;
	private int percentages;

	public String getSummonerId() {
		return summonerId;
	}

	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}

	public String getQueueType() {
		return queueType;
	}

	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getLeaguePoints() {
		return leaguePoints;
	}

	public void setLeaguePoints(int leaguePoints) {
		this.leaguePoints = leaguePoints;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getPercentages() {
		return percentages;
	}

	public void setPercentages() {
		if (wins == 0 && losses == 0) {
			this.percentages = 0;
		} else {
			this.percentages = (this.wins * 100) / (this.wins + this.losses);
		}
	}

	public void QueueType() {
		if (this.queueType.indexOf("SOLO") > -1) {
			this.queueType = "¼Ö·Î ·©Å©";
		} else {
			this.queueType = "ÀÚÀ¯ ·©Å©";
		}
	}
}
