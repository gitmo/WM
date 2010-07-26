package dbs.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.GroupStage;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;
import dbs.project.service.group.StandingRow;

public class GroupStageService {
	private static final int MAX_TEAMS_PER_GROUP = 4;

	public static GroupStage getByTeams(List<Team> teams) throws Exception {
		if(teams.size() % MAX_TEAMS_PER_GROUP != 0)
			throw new Exception("Could not generate equally groups");
		
		int numberOfGroups = teams.size() / MAX_TEAMS_PER_GROUP;
		List<TournamentGroup> allGroups = new LinkedList<TournamentGroup>();
		Random random = new Random();
		for(int i = 0; i < numberOfGroups; i++) {
			TournamentGroup currentGroup = new TournamentGroup();
			currentGroup.setName(String.format("Group %c", i + 65));
			
			List<Team> groupTeams = new LinkedList<Team>();
			for(int j=0; j < MAX_TEAMS_PER_GROUP; j++)
				groupTeams.add(teams.remove(random.nextInt(teams.size())));
			
			currentGroup.setTeams(groupTeams);
			//Benutzt nur eine Kopie von groupTeams, da es die Liste selbst verändert
			currentGroup.setMatches(generateMatches(new LinkedList<Team>(groupTeams)));

			allGroups.add(currentGroup);
		}
		
		GroupStage groupStage = new GroupStage();
		groupStage.setGroups(allGroups);
		
		return groupStage;
	}
	
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
		return generateRecursivlyMatches(new LinkedList<GroupMatch>(),groupTeams);
	}

	public static TableModel getTableModel(Tournament tournament) {
		List<TournamentGroup> groups = tournament.getGroupPhase().getGroups();
		List<Team> teams = groups.get(0).getTeams();
		List<GroupMatch> matches = groups.get(0).getMatches();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		for(StandingRow sr : StandingRow.getRows(teams, matches)) {
			Vector<String> row = new Vector<String>();
			row.add(sr.getTeamName());
			row.add(sr.getPlayedGames().toString());
			row.add(sr.getPoints().toString());
			row.add(sr.getGoalsScored() + ":" + sr.getGoalsAgainst());
			data.add(row);
		}
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Team");
		columnNames.add("Spiele");
		columnNames.add("Punkte");
		columnNames.add("Tordifferenz");
		DefaultTableModel model = new javax.swing.table.DefaultTableModel(
				data,
		        columnNames
		);
		return model;
	}

}
