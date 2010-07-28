package dbs.project.entity.event.player;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.event.PlayerEvent;

@Entity
public class GoalEvent extends PlayerEvent {
	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Team scorringTeam;

	public GoalEvent() {
		super();
	}

	public GoalEvent(int minute, Player player, Team team) {
		super(minute, player);
		setScorringTeam(team);
	}

	public GoalEvent(int minute, int additionalTime, Player player, Team team) {
		super(minute, additionalTime, player);
		setScorringTeam(team);
	}

	public Team getScorringTeam() {
		return scorringTeam;
	}

	public void setScorringTeam(Team scorringTeam) {
		this.scorringTeam = scorringTeam;
	}
}
