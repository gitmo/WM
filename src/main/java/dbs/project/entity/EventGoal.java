package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class EventGoal extends MatchEvent {
	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Team scorringTeam;

	public EventGoal() {
		super();
	}

	public EventGoal(Player player, Team team, int minute) {
		super(player, minute);
		setScorringTeam(team);
	}

	public EventGoal(Player player, Team team, int minute, int additionalTime) {
		super(player, minute, additionalTime);
		setScorringTeam(team);
	}

	public Team getScorringTeam() {
		return scorringTeam;
	}

	public void setScorringTeam(Team scorringTeam) {
		this.scorringTeam = scorringTeam;
	}
}
