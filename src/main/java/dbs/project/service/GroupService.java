package dbs.project.service;

import java.util.List;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.TournamentGroup;
import dbs.project.service.group.StandingRow;

public class GroupService {

	/**
	 * print Standings of group
	 * 
	 * Style: 
	 * rank \t Team \t points \t scoredGoals:receivedGoals \t playedGames
	 * 
	 * @param group
	 */
	public static void printStandings(TournamentGroup group) {
		List<StandingRow> rows = StandingRow.getRows(group.getTeams(), group
				.getMatches());

		int i = 1;
		for (StandingRow row : rows)
			System.out.println((i++) + ".\t" + row.toString());
	}

	/**
	 * returns a string with all games in a group
	 * 
	 * Style:
	 * HostTeam vs GuestTeam am Date in Stadion
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
}
