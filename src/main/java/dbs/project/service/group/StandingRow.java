package dbs.project.service.group;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.Team;
import dbs.project.service.MatchService;
import dbs.project.util.Tuple;

public class StandingRow implements Comparable<StandingRow> {
	private String teamName;
	private int points = 0, playedGames = 0, goalsScored = 0, goalsAgainst = 0;

	public StandingRow(String name) {
		this.teamName = name;
	}

	public int getPoints() {
		return this.points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public int getGoalsScored() {
		return this.goalsScored;
	}
	
	public int getGoalDifference() {
		return Math.abs(this.getGoalsScored() - this.getGoalsAgainst());
	}
	
	public void addGoalsScored(int goals) {
		this.goalsScored += goals;
	}
	
	public int getGoalsAgainst() {
		return this.goalsAgainst;
	}
	
	public void addGoalsAgainst(int goals) {
		this.goalsAgainst += goals;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public int getPlayedGames() {
		return playedGames;
	}

	public void incPlayedGames() {
		this.playedGames++;
	}

	public int compareTo(StandingRow row) {
		if(this.getPoints() > row.getGoalsScored()) {
			return -1;
		} else if(this.getPoints() < row.getGoalsScored()) {
			return 1;
		} else {
			if(this.getGoalDifference() > row.getGoalDifference()) {
				return -1;
			} else if(this.getGoalDifference() < row.getGoalDifference()) {
				return 1;
			} else {
				//*TODO* direkter Vergleich
				return this.getTeamName().compareTo(row.getTeamName());
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getTeamName() + "\t\t");
		sb.append(this.getPoints() + "\t\t");
		sb.append(this.getGoalsScored() + ":" + this.getGoalsAgainst() + "\t\t");
		sb.append(this.getPlayedGames() + "\n");
		
		return sb.toString();
	}

	public static List<StandingRow> getRows(
		List<Team> teams, java.util.List<GroupMatch> matches) {
		
		List<StandingRow> standings = new LinkedList<StandingRow>();
		
		for(Team team : teams) {
			StandingRow teamRow = new StandingRow(team.getName());
			for(GroupMatch match : matches) {
				//Falls unser Team nicht gespielt hat, ignoriere das Spiel
				if(!match.getHostTeam().equals(team) && !match.getGuestTeam().equals(team))
					continue;
				
				//Ermittelt die Tore
				Tuple<Integer> goals = MatchService.getGoalsByTeam(team, match);
				teamRow.addGoalsScored(goals.getFirst());
				teamRow.addGoalsAgainst(goals.getSecond());
				
				//Ermittelt die Punkte (Hat das Team gewonnen,
				//unentschieden gespielt oder verloren?
				teamRow.addPoints(MatchService.getPointsByTeam(team, match));
			
				//Wurde das Spiel gepspielt, dann erhöhe den Spiel-Counter
				if(match.isPlayed())
					teamRow.incPlayedGames();
			}
			
			standings.add(teamRow);
		}
		
		//Collections.sort(standings);
		
		return standings;
	}
	
}