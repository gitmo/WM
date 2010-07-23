package dbs.project.entity;

public abstract class MatchEvent
{
    private Integer minute;
    private Player involvedPlayer;
    
    public MatchEvent() {}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Player getInvolvedPlayer() {
		return involvedPlayer;
	}

	public void setInvolvedPlayer(Player involvedPlayer) {
		this.involvedPlayer = involvedPlayer;
	}
}
