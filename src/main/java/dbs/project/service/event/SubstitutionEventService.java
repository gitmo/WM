package dbs.project.service.event;

import java.util.LinkedList;
import java.util.List;

import dbs.project.dao.event.SubstitutionEventDao;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.util.Substitution;
import dbs.project.util.Tuple;

public class SubstitutionEventService {

	public static List<Substitution> getSubstituedPlayersByTeam(
			Team team, Match match) {
		List<Substitution> substitutions = getSubstitutedPlayersForMatch(match);
		for (Substitution substitution : substitutions)
			if (!substitution.getPlayerOut().getTeams().contains(team))
				substitutions.remove(substitution);

		return substitutions;
	}

	public static List<Substitution> getSubstitutedPlayersForMatch(
			Match match) {

		List<SubstitutionEvent> events = SubstitutionEventDao
				.findAllByMatch(match);
		List<Substitution> substitutions = new LinkedList<Substitution>();

		for (SubstitutionEvent event : events) {
			Substitution substitution = new Substitution(event.getMinute(), event.getInvolvedPlayer(), event.getNewPlayer());
			substitutions.add(substitution);
		}

		return substitutions;
	}

	public static List<SubstitutionEvent> getSubstitutionEventsForMatch(
			Match match) {
		
		return SubstitutionEventDao.findAllByMatch(match);
	}

}
