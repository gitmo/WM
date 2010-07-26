package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class EventSubstitution extends MatchEvent
{
    @ManyToOne
	private Player newPlayer;
    
    public EventSubstitution() {}

	public EventSubstitution(Player p1, Player p2, int i) {
		super(p1,i);
		this.newPlayer = p2;
		}

	public Player getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}
}
