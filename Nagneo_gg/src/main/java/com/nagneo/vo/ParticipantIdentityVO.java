package com.nagneo.vo;

import java.util.ArrayList;

public class ParticipantIdentityVO {
	private int participantId;
	private PlayerVO player;
	
	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public PlayerVO getPlayer() {
		return player;
	}

	public void setPlayer(PlayerVO player) {
		this.player = player;
	}
}
