package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class EventSubstitution extends MatchEvent
{
    @ManyToOne
	private Player newPlayer;
    
    public EventSubstitution() {}

	public EventSubstitution(Player out, Player in, int minute) {
		super(out,minute);
		this.newPlayer = in;
	}

	public EventSubstitution(Player out, Player in, int minute, int addTime) {
		super(out,minute,addTime);
		this.newPlayer = in;
	}
	
	public Player getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}
}
