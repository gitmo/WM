package dbs.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.ListModel;

import org.hibernate.cfg.NotYetImplementedException;

import dbs.project.dao.TournamentDao;
import dbs.project.entity.EventGoal;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Tournament;
import dbs.project.service.event.filter.FilterGoals;

public class TournamentService {
	public static void weAreTheChampions(Tournament tournament) {
		throw new NotYetImplementedException("weAreTheChampions()");
	}

	public static ListModel getListModel() {
		final List<Tournament> tournaments = TournamentDao.fetchAll(); 
		
		class ListModel extends javax.swing.AbstractListModel {
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return tournaments.size();
			}
	        
			public Tournament getElementAt(int i) {
				return tournaments.get(i);
			}
		}
		
		return new ListModel();
	}

	public static String getTopscorers(Tournament tournament) {
		List<Match> allMatches = TournamentService.getAllMatches(tournament);
		HashMap<Player, Integer> hashMap = new HashMap<Player, Integer>();
		
		for(Match match : allMatches) {
			List<EventGoal> allGoals = new LinkedList<EventGoal>();
			dbs.project.util.Collections.filterAndChangeType(match.getEvents(), new FilterGoals(), allGoals);
			for(EventGoal eventGoal : allGoals) {
				int i = 0;
				try {
					i = hashMap.get(eventGoal.getInvolvedPlayer());
				} catch (Exception e) {}
				hashMap.put(eventGoal.getInvolvedPlayer(), ++i);
			}
		}
		
		int max = 0;
		for(Integer i : hashMap.values()){
			if(i>max)
				max = i;	
		}
		List<Player> scorer = new ArrayList<Player>();
		
		for(Player player : hashMap.keySet()){
			if(hashMap.get(player)==max)
				scorer.add(player);
		}
		
		String res = new String();
		
		for(Player player : scorer)
			res.concat(player.toString() + " ");
		
		return res;
	}
	
	

	public static List<Match> getAllMatches(Tournament tournament) {
		List<Match> matches = new LinkedList<Match>();
		matches.addAll(GroupStageService.getAllMatches(tournament.getGroupPhase()));
		matches.addAll(KnockoutStageService.getAllMatches(tournament.getKnockoutPhase()));
		return matches;
	}

	public static String getPlayerWithMostCards(Tournament tournament) {
		return "Nutze anderen Datentyp als Map";
	}
	
	public static List<Integer> getAllyears(){
		List<Integer> years = new ArrayList<Integer>();
		List<Tournament> tournaments = TournamentDao.fetchAll();
		for(Tournament t : tournaments){
			years.add(t.getYear());
		}
		return years;
		
	}
	
}
