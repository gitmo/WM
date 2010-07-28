package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import dbs.project.util.MatchMinute;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MatchEvent {
	@Id
	@GeneratedValue
	protected Long id;

	protected int minute;
	protected int additionalMinute = 0;

	public MatchEvent() {
	}

	public MatchEvent(int minute) {
		setMinute(minute);
	}

	public MatchEvent(int minute, int additionalTime) {
		setMinute(minute, additionalTime);
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
}
