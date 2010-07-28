package dbs.project.collections.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.Team;
import dbs.project.entity.event.PlayerEvent;
import dbs.project.util.Filter;

public class FilterTeam implements Filter<MatchEvent> {
	private Team team;

	public FilterTeam(Team team) {
		this.team = team;
	}
	
	public boolean apply(MatchEvent event) {
		if(!(event instanceof PlayerEvent))
			return false;
		
		boolean teamInvolved = ((PlayerEvent) event).getInvolvedPlayer().getTeams().contains(team);
		return teamInvolved ? true : false;
	}
}
