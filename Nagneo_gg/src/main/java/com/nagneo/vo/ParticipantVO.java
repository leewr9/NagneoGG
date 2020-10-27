package com.nagneo.vo;

public class ParticipantVO {
	private int participantId;
	private int championId;
	private ParticipantStatsVO stats;
	
	public int getParticipantId() {
		return participantId;
	}
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
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
