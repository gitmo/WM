package dbs.project.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import dbs.project.service.event.Filter;
import dbs.project.service.event.FilterGoals;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class MatchEvent
{
	@Id
	@GeneratedValue
	Long id;
    private Integer minute;
	@ManyToOne
    private Player involvedPlayer;
    
    public MatchEvent() {}

	public MatchEvent(Player p1, int i) {
		this.minute = i;
		this.involvedPlayer = p1;
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
}
