package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.util.Tuple;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MatchEvent {
	@Id
	@GeneratedValue
	protected Long id;

	protected int minute;
	protected int additionalMinute = 0;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Player involvedPlayer;

	public MatchEvent() {
	}

	public MatchEvent(Player involvedPlayer, int minute) {
		setInvolvedPlayer(involvedPlayer);
		setMinute(minute);
	}

	public MatchEvent(Player involvedPlayer, int minute, int additionalTime) {
		setInvolvedPlayer(involvedPlayer);
		setMinute(minute, additionalTime);
	}

	public Tuple<Integer, Integer> getMinute() {
		return new Tuple<Integer, Integer>(minute, additionalMinute);
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
