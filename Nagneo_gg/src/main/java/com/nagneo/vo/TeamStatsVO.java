package com.nagneo.vo;

public class TeamStatsVO {
	private int teamId;
	private String win;
	private int towerKills;
	private int dragonKills;
	private int baronKills;
	private int score;
	private int gold;
	private int reset;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getDragonKills() {
		return dragonKills;
	}

	public void setDragonKills(int dragonKills) {
		this.dragonKills = dragonKills;
	}

	public int getBaronKills() {
		return baronKills;
	}

	public void setBaronKills(int baronKills) {
		this.baronKills = baronKills;
	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getTowerKills() {
		return towerKills;
	}

	public void setTowerKills(int towerKills) {
		this.towerKills = towerKills;
	}

	public int getReset() {
		return reset;
	}

	public void setReset(int reset) {
		this.gold = reset;
		this.score = reset;
	}

}
