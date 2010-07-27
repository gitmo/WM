package dbs.project.service;

import java.util.List;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.TournamentGroup;
import dbs.project.service.group.StandingRow;

public class GroupService {
	
	public static void printStandings(TournamentGroup group) {
		List<StandingRow> rows = StandingRow.getRows(group.getTeams(), group.getMatches());
		
		int i=1;
		for(StandingRow row : rows)
			System.out.println((i++) + ".\t" + row.toString());
	}
	
	public static String getSchedule(TournamentGroup group) {
		StringBuilder sb = new StringBuilder();
		for(GroupMatch match : group.getMatches())
			sb.append(match + "\n");
		
		return sb.toString();
	}
}
