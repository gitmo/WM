package dbs.project.service;

import java.util.List;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.Match;
import dbs.project.entity.Team;
import dbs.project.entity.TournamentGroup;
import dbs.project.exception.NoGroupMatchesSet;
import dbs.project.service.group.StandingRow;

public class GroupService {

	/**
	 * print Standings of group
	 * 
	 * Style: rank \t Team \t points \t scoredGoals:receivedGoals \t playedGames
	 * 
	 * @param group
	 * @throws NoGroupMatchesSet
	 */
	public static void printStandings(TournamentGroup group)
			throws NoGroupMatchesSet {

		if (group.getMatches() == null)
			throw new NoGroupMatchesSet();
		List<StandingRow> rows = StandingRow.getRows(group.getTeams(), group
				.getMatches());

		int i = 1;
		for (StandingRow row : rows)
			System.out.println((i++) + ".\t" + row.toString());
	}

	/**
	 * returns a string with all games in a group
	 * 
	 * Style: HostTeam vs GuestTeam am Date in Stadion
	 * 
	 * @param group
	 * @return
	 */
	public static String getSchedule(TournamentGroup group) {
		StringBuilder sb = new StringBuilder();
		for (GroupMatch match : group.getMatches())
			sb.append(match + "\n");

		return sb.toString();
	}
	
	public static boolean areAllMatchesPlayed(TournamentGroup group) {
		for(Match match : group.getMatches())
			if(!match.isPlayed())
				return false;
		
		return true;
	}

	public static Team getFirst(TournamentGroup group) {
		return StandingRow.getRows(group.getTeams(), group.getMatches()).get(0).getTeam();
	}

	public static Team getSecond(TournamentGroup group) {
		return StandingRow.getRows(group.getTeams(), group.getMatches()).get(1).getTeam();
	}
}
