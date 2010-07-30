package dbs.project.util;

import dbs.project.entity.Player;

public class Substitution {
	private MatchMinute minute;
	private Player playerIn, playerOut;
	
	public Substitution(MatchMinute minute, Player in, Player out) {
		setMinute(minute);
		setPlayerIn(in);
		setPlayerOut(out);
	}
	
	public MatchMinute getMinute() {
		return minute;
	}
	public void setMinute(MatchMinute minute) {
		this.minute = minute;
	}
	public Player getPlayerIn() {
		return playerIn;
	}
	public void setPlayerIn(Player playerIn) {
		this.playerIn = playerIn;
	}
	public Player getPlayerOut() {
		return playerOut;
	}
	public void setPlayerOut(Player playerOut) {
		this.playerOut = playerOut;
	}
}
