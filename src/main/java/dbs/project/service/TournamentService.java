package dbs.project.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import java.util.TreeSet;

import javax.swing.ListModel;

import dbs.project.dao.TournamentDao;
import dbs.project.entity.EventCard;
import dbs.project.entity.EventGoal;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.exception.TiedMatch;
import dbs.project.exception.TournamentNotOver;
import dbs.project.service.event.filter.FilterCards;
import dbs.project.service.event.filter.FilterGoals;

public class TournamentService {
	
	public static List<Team> weAreTheChampions(Tournament tournament) throws TournamentNotOver {
		Match finalMatch = tournament.getKnockoutPhase().getFinalMatch();
		Match forThirdPlace = tournament.getKnockoutPhase().getMatchForThirdPlace();
		if(!finalMatch.isPlayed() || !forThirdPlace.isPlayed())
			throw new TournamentNotOver();
		
		List<Team> champions = new LinkedList<Team>();
		try {
			champions.add(MatchService.getWinner(finalMatch));
			champions.add(MatchService.getLooser(finalMatch));
			champions.add(MatchService.getWinner(forThirdPlace));
		} catch (TiedMatch e) {
			System.err.println("Uncompatible match type");
		}
		
		return champions;
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
		class Topscorer implements Comparable<Topscorer> {
			Player player;
			int goals = 0;
			
			public int compareTo(Topscorer o) {
				if(this.goals > o.goals)
					return -1;
				else if(this.goals < o.goals)
					return 1;
				else
					return 0;
			}
		}
		
		List<Match> allMatches = TournamentService.getAllMatches(tournament);
		if(allMatches.size() == 0)
			return "Es wurden noch keine Spiele gespielt";
		
		ArrayList<Topscorer> topscorer = new ArrayList<Topscorer>();
		for(Match match : allMatches) {
			List<EventGoal> allGoals = new LinkedList<EventGoal>();
			dbs.project.util.Collections.filterAndChangeType(match.getEvents(), new FilterGoals(), allGoals);
			for(EventGoal eventGoal : allGoals) {
				int i = topscorer.indexOf(eventGoal.getInvolvedPlayer());
				topscorer.get(i).goals++;
			}
		}
		TreeSet<Topscorer> topscorerTree = new TreeSet<Topscorer>(topscorer);
		if(topscorerTree.size() == 0)
			return "Es wurden keine Tore geschoßen";
		
		return topscorerTree.first().player.toString();
	}
	
	

	public static List<Match> getAllMatches(Tournament tournament) {
		List<Match> matches = new LinkedList<Match>();
		matches.addAll(GroupStageService.getAllMatches(tournament.getGroupPhase()));
		matches.addAll(KnockoutStageService.getAllMatches(tournament.getKnockoutPhase()));
		return matches;
	}

	public static String getPlayerWithMostCards(Tournament tournament) {
		class PlayerWithCards implements Comparable<PlayerWithCards> {
			Player player;
			int cards = 0;
			
			public int compareTo(PlayerWithCards o) {
				if(this.cards > o.cards)
					return -1;
				else if(this.cards < o.cards)
					return 1;
				else
					return 0;
			}
		}
		
		List<Match> allMatches = TournamentService.getAllMatches(tournament);
		if(allMatches.size() == 0)
			return "Es wurden noch keine Spiele gespielt";
		
		ArrayList<PlayerWithCards> playersWithCards = new ArrayList<PlayerWithCards>();
		for(Match match : allMatches) {
			List<EventCard> allCards = new LinkedList<EventCard>();
			dbs.project.util.Collections.filterAndChangeType(match.getEvents(), new FilterCards(), allCards);
			for(EventCard eventCard : allCards) {
				int i = playersWithCards.indexOf(eventCard.getInvolvedPlayer());
				playersWithCards.get(i).cards++;
			}
		}
		
		TreeSet<PlayerWithCards> topscorerTree = new TreeSet<PlayerWithCards>(playersWithCards);
		if(topscorerTree.size() == 0)
			return "Es wurden keine Karten vergeben";
		
		return topscorerTree.first().player.toString();
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
