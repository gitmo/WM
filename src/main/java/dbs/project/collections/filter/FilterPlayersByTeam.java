package dbs.project.collections.filter;

import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.util.collections.Filter;

public class FilterPlayersByTeam implements Filter<Player> {
	Team team;

	public FilterPlayersByTeam(Team team) {
		this.team = team;
	}

	public boolean apply(Player player) {
		return team.getPlayers().contains(player) ? true : false;
	}

}
