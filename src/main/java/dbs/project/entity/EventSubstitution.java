package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class EventSubstitution extends MatchEvent {
	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Player newPlayer;

	public EventSubstitution() {
		super();
	}

	public EventSubstitution(Player out, Player in, int minute) {
		super(out, minute);
		setNewPlayer(in);
	}

	public EventSubstitution(Player out, Player in, int minute,
			int additionalTime) {
		super(out, minute, additionalTime);
		setNewPlayer(in);
	}

	public Player getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}
}
