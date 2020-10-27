package com.nagneo.vo;

import java.util.List;

public class MatchVO {
	private List<ParticipantIdentityVO> participantIdentities;
	private List<TeamStatsVO> teams;
	private String gameMode;
	private List<ParticipantVO> participants;
	
	public List<ParticipantIdentityVO> getParticipantIdentities() {
		return participantIdentities;
	}
	public void setParticipantIdentities(List<ParticipantIdentityVO> participantIdentities) {
		this.participantIdentities = participantIdentities;
	}
	public List<TeamStatsVO> getTeams() {
		return teams;
	}
	public void setTeams(List<TeamStatsVO> teams) {
		this.teams = teams;
	}
	public String getGameMode() {
		return gameMode;
	}
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	public List<ParticipantVO> getParticipants() {
		return participants;
	}
	public void setParticipants(List<ParticipantVO> participants) {
		this.participants = participants;
	}
}
