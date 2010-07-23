package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

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
