package dbs.project.entity.event.player;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.event.PlayerEvent;

@Entity
public class LineUpEvent extends PlayerEvent {
	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Team team;

	public LineUpEvent() {
		super();
	}

	public LineUpEvent(Player player, Team team) {
		super(0, player);
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
