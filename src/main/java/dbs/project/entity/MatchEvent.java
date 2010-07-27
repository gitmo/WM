package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MatchEvent {
	@Id
	@GeneratedValue
	Long id;
	private Integer minute = 0;
	private Integer addTime = 0;
	@ManyToOne
	private Player involvedPlayer;

	public MatchEvent() {
	}

	public MatchEvent(Player involvedPlayer, int minute) {
		this.minute = minute;
		this.involvedPlayer = involvedPlayer;
	}

	public MatchEvent(Player involvedPlayer, int minute, int addTime) {
		this.minute = minute;
		this.involvedPlayer = involvedPlayer;
		this.addTime = addTime;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Player getInvolvedPlayer() {
		return involvedPlayer;
	}

	public void setInvolvedPlayer(Player involvedPlayer) {
		this.involvedPlayer = involvedPlayer;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

	public Integer getAddTime() {
		return addTime;
	}
}
