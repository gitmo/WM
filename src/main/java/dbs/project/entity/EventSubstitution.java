package dbs.project.entity;

public class EventSubstitution extends MatchEvent
{
    private Player newPlayer;
    
    public EventSubstitution() {}

	public Player getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}
}
