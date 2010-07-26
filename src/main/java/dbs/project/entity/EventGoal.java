package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class EventGoal extends MatchEvent
{
	@ManyToOne
	Team scorringTeam;
	
    public Team getScorringTeam() {
		return scorringTeam;
	}

	public void setScorringTeam(Team scorringTeam) {
		this.scorringTeam = scorringTeam;
	}

	public EventGoal() {}
}
