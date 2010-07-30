package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.util.MatchMinute;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MatchEvent {
	@Id
	@GeneratedValue
	protected Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected Match match;

	protected int minute;
	protected int additionalMinute = 0;

	public MatchEvent() {
	}

	public MatchEvent(Match match, int minute) {
		setMinute(minute);
		setMatch(match);
	}

	public MatchEvent(Match match, int minute, int additionalTime) {
		setMinute(minute, additionalTime);
		setMatch(match);
	}

	public Match getMatch() {
		return this.match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public MatchMinute getMinute() {
		return new MatchMinute(this.minute, this.additionalMinute);
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setMinute(int minute, int additionalTime) {
		this.minute = minute;
		this.additionalMinute = additionalTime;
	}
}
