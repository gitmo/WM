package dbs.project.service;

import java.util.List;

import dbs.project.entity.TournamentGroup;
import dbs.project.service.group.StandingRow;

public class GroupService {
	
	public static void printStandings(TournamentGroup group) {
		List<StandingRow> rows = StandingRow.getRows(group.getTeams(), group.getMatches());
		
		int i=1;
		for(StandingRow row : rows)
			System.out.println((i++) + ".\t" + row.toString());
	}
}
