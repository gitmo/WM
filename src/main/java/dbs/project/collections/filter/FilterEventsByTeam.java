package dbs.project.collections.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.Team;
import dbs.project.entity.event.PlayerEvent;
import dbs.project.util.collections.Filter;

public class FilterEventsByTeam implements Filter<MatchEvent> {
	private final Team team;

	public FilterEventsByTeam(Team team) {
		this.team = team;
	}

	public boolean apply(MatchEvent event) {
		if (!(event instanceof PlayerEvent))
			return false;

		boolean teamInvolved = ((PlayerEvent) event).getInvolvedPlayer()
				.getTeams().contains(this.team);
		return teamInvolved ? true : false;
	}
}
