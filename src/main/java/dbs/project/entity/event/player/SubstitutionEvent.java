package dbs.project.entity.event.player;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.entity.Player;
import dbs.project.entity.event.PlayerEvent;

@Entity
public class SubstitutionEvent extends PlayerEvent {
	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Player newPlayer;

	public SubstitutionEvent() {
		super();
	}

	public SubstitutionEvent(int minute,Player out, Player in) {
		super(minute,out);
		setNewPlayer(in);
	}

	public SubstitutionEvent(int minute,
			int additionalTime,Player out, Player in) {
		super(minute, additionalTime,out);
		setNewPlayer(in);
	}

	public Player getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}
}
