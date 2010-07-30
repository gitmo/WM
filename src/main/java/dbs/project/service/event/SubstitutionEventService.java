package dbs.project.service.event;

import java.util.LinkedList;
import java.util.List;

import dbs.project.dao.event.SubstitutionEventDao;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.util.Tuple;

public class SubstitutionEventService {

	public static List<Tuple<Player, Player>> getSubstituedPlayersByTeam(
			Team team, Match match) {
		List<Tuple<Player, Player>> substitutions = getSubstitutedPlayersForMatch(match);
		for (Tuple<Player, Player> substitution : substitutions)
			if (!substitution.getFirst().getTeams().contains(team))
				substitutions.remove(substitution);

		return substitutions;
	}

	public static List<Tuple<Player, Player>> getSubstitutedPlayersForMatch(
			Match match) {

		List<SubstitutionEvent> events = SubstitutionEventDao
				.findAllByMatch(match);
		List<Tuple<Player, Player>> substitutions = new LinkedList<Tuple<Player, Player>>();

		for (SubstitutionEvent event : events) {
			Tuple<Player, Player> substitution = new Tuple<Player, Player>();
			substitution.setFirst(event.getInvolvedPlayer());
			substitution.setSecond(event.getNewPlayer());
			substitutions.add(substitution);
		}

		return substitutions;
	}

	public static List<SubstitutionEvent> getSubstitutionEventsForMatch(
			Match match) {
		
		return SubstitutionEventDao.findAllByMatch(match);
	}

}
