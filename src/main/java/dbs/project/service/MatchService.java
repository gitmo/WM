package dbs.project.service;

import java.util.LinkedList;
import java.util.List;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.Team;

public class MatchService {

	/**
	 * Rekursive Generation der Matches
	 * Rekursionsanker: nur noch 1 Mannschaft in groupTeams
	 * 
	 * @param matches
	 * @param groupTeams
	 * @return
	 */
	private static List<GroupMatch> generateRecursivlyMatches(List<GroupMatch> matches, List<Team> groupTeams) {
		if(groupTeams.size() <= 1)
			return matches;
		
		Team currentTeam  = groupTeams.remove(0);
		//Signalisiert ob es ein Heimspiel für currentTeam
		boolean homeMatch = true;
		//Lässt currentTeam einmal gegen jede andere Mannschaft spielen
		for(Team opponent : groupTeams) {
			GroupMatch match =  new GroupMatch();
			
			if(homeMatch) {
				match.setHostTeam(currentTeam);
				match.setGuestTeam(opponent);
			} else {
				match.setHostTeam(opponent);
				match.setGuestTeam(currentTeam);
			}
			
			homeMatch = !homeMatch;
			matches.add(match);
		}
		
		return generateRecursivlyMatches(matches, groupTeams);
	}
	
	public static List<GroupMatch> generateMatches(List<Team> groupTeams) {
		//Generiert rekursiv die Spiele.
		//Benutzt nur eine Kopie von groupTeams, da es die Liste selbst verändert
		return generateRecursivlyMatches(new LinkedList<GroupMatch>(),new LinkedList<Team>(groupTeams));
	}

}
