package com.nagneo.vo;

import java.util.List;

public class MatchVO {
	private List<ParticipantIdentityVO> participantIdentities;
	private List<TeamStatsVO> teams;
	private String gameMode;
	private long gameDuration;
	private List<ParticipantVO> participants;
	private String gameTime;

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

	public long getGameDuration() {
		return gameDuration;
	}

	public void setGameDuration(long gameDuration) {
		this.gameDuration = gameDuration;
	}

	public String getGameTime() {
		return gameTime;
	}

	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}

}
