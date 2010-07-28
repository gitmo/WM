package dbs.project.entity.event;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.Player;
import dbs.project.util.MatchMinute;
import dbs.project.util.Tuple;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PlayerEvent extends MatchEvent {
	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Player involvedPlayer;

	public PlayerEvent() {
	}

	public PlayerEvent(int minute, Player involvedPlayer) {
		super(minute);
		setInvolvedPlayer(involvedPlayer);
	}

	public PlayerEvent(int minute, int additionalTime, Player involvedPlayer) {
		super(minute, additionalTime);
		setInvolvedPlayer(involvedPlayer);
	}

	public MatchMinute getMinute() {
		return new MatchMinute(minute, additionalMinute);
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setMinute(int minute, int additionalTime) {
		this.minute = minute;
		this.additionalMinute = additionalTime;
	}

	public Player getInvolvedPlayer() {
		return involvedPlayer;
	}

	public void setInvolvedPlayer(Player involvedPlayer) {
		this.involvedPlayer = involvedPlayer;
	}
}
