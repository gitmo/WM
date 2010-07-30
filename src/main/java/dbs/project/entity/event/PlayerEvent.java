package dbs.project.entity.event;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.entity.Match;
import dbs.project.entity.MatchEvent;
import dbs.project.entity.Player;
import dbs.project.util.MatchMinute;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PlayerEvent extends MatchEvent {
	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Player involvedPlayer;

	public PlayerEvent() {
	}

	public PlayerEvent(Match match, int minute, Player involvedPlayer) {
		super(match, minute);
		setInvolvedPlayer(involvedPlayer);
	}

	public PlayerEvent(Match match, int minute, int additionalTime,
			Player involvedPlayer) {
		super(match, minute, additionalTime);
		setInvolvedPlayer(involvedPlayer);
	}

	@Override
	public MatchMinute getMinute() {
		return new MatchMinute(this.minute, this.additionalMinute);
	}

	@Override
	public void setMinute(int minute) {
		this.minute = minute;
	}

	@Override
	public void setMinute(int minute, int additionalTime) {
		this.minute = minute;
		this.additionalMinute = additionalTime;
	}

	public Player getInvolvedPlayer() {
		return this.involvedPlayer;
	}

	public void setInvolvedPlayer(Player involvedPlayer) {
		this.involvedPlayer = involvedPlayer;
	}
}
