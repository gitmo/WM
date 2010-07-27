package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import dbs.project.service.MatchService;
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

	public EventGoal(Player player,int minute, int addTime, Match match) {
		super(player,minute,addTime);
		this.scorringTeam = PlayerService.getTeamOfPlayer(player, match);
	}

	public EventGoal(Player player,int minute, Match match) {
		super(player,minute);
		this.scorringTeam = PlayerService.getTeamOfPlayer(player, match);
	}
}
