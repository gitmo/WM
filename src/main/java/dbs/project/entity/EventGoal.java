package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import dbs.project.service.PlayerService;

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

	public EventGoal(Player player, Team team, int minute, int addTime){
		super();
		this.setScorringTeam(team);
		this.setMinute(minute);
		this.setAddTime(addTime);
		this.setInvolvedPlayer(player);
	}
}
