package dbs.project.service;

import java.util.LinkedList;
import java.util.List;
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

	/**
	 * JTable requires a TableModel for data representation getTableModel
	 * returns the TableModel for a tournament (for the GUI)
	 * 
	 * @param tournament
	 * @return
	 */
	public static TableModel getTableModel(Tournament tournament) {
		List<TournamentGroup> groups = tournament.getGroupStage().getGroups();
		List<Team> teams = groups.get(0).getTeams();
		List<GroupMatch> matches = groups.get(0).getMatches();
		Vector<Vector<String>> data = new Vector<Vector<String>>();

		for (StandingRow sr : StandingRow.getRows(teams, matches)) {
			Vector<String> row = new Vector<String>();
			row.add(sr.getTeam().getName());
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
		DefaultTableModel model = new javax.swing.table.DefaultTableModel(data,
				columnNames);
		return model;
	}

	/**
	 * return all group matches of a tournament
	 * 
	 * @param groupStage
	 * @return
	 */
	public static List<GroupMatch> getAllMatches(GroupStage groupStage) {
		List<GroupMatch> groupMatches = new LinkedList<GroupMatch>();

		for (TournamentGroup group : groupStage.getGroups()) {
			groupMatches.addAll(group.getMatches());
		}

		return groupMatches;
	}
}
