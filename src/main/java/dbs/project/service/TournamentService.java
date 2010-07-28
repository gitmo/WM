package dbs.project.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import javax.swing.ListModel;

import dbs.project.collections.filter.FilterCardEvent;
import dbs.project.collections.filter.FilterGoalEvent;
import dbs.project.dao.TournamentDao;
import dbs.project.entity.KnockoutMatch;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.entity.event.player.CardEvent;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.exception.TiedMatch;
import dbs.project.exception.TournamentNotOver;
import dbs.project.util.Collections;

public class TournamentService {

	public static List<Team> weAreTheChampions(Tournament tournament)
			throws TournamentNotOver {
		Match finalMatch = tournament.getFinalMatch();
		Match forThirdPlace = tournament.getMatchForThirdPlace();
		if (!finalMatch.isPlayed() || !forThirdPlace.isPlayed())
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
			
			public Topscorer(Player player) {
				this.player = player;
			}

			public int compareTo(Topscorer o) {
				if (this.goals > o.goals)
					return -1;
				else if (this.goals < o.goals)
					return 1;
				else
					return 0;
			}
			
			@Override
			public boolean equals(Object obj) {
				if(obj instanceof Topscorer)
					return player == ((Topscorer) obj).player;
				
				return false;
			}
		}

		List<Match> allMatches = TournamentService.getAllMatches(tournament);
		if (allMatches.size() == 0)
			return "Es wurden noch keine Spiele gespielt";

		ArrayList<Topscorer> topscorers = new ArrayList<Topscorer>();
		for (Match match : allMatches) {
			List<GoalEvent> allGoals = new LinkedList<GoalEvent>();
			Collections.filterAndChangeType(match.getEvents(),
					new FilterGoalEvent(), allGoals);
			int i=-1;
			for (GoalEvent eventGoal : allGoals) {
				Topscorer player = new Topscorer(eventGoal.getInvolvedPlayer());
				i = topscorers.indexOf(player);
				if(i<0)
					topscorers.add(player);
				else
					topscorers.get(i).goals++;
			}
		}

		if (topscorers.size() == 0)
			return "Es wurden keine Tore geschoßen";
		
		java.util.Collections.sort(topscorers);
		
		Topscorer topscorer = topscorers.get(0);
		return String.format("%s %d Tore",topscorer.player.getName(), topscorer.goals);
	}

	public static List<Match> getAllMatches(Tournament tournament) {
		List<Match> matches = new LinkedList<Match>();
		matches.addAll(GroupStageService.getAllMatches(tournament
				.getGroupStage()));
		matches.addAll(KnockoutMatchService.getAllMatches(tournament
				.getFinalMatch()));
		matches.add(tournament.getMatchForThirdPlace());
		return matches;
	}

	public static String getPlayerWithMostCards(Tournament tournament) {
		class PlayerWithCards implements Comparable<PlayerWithCards> {
			Player player;
			int cards = 0;
			
			public PlayerWithCards(Player player) {
				this.player = player;
			}

			public int compareTo(PlayerWithCards o) {
				if (this.cards > o.cards)
					return -1;
				else if (this.cards < o.cards)
					return 1;
				else
					return 0;
			}
			
			@Override
			public boolean equals(Object obj) {
				if(obj instanceof PlayerWithCards)
					return player == ((PlayerWithCards) obj).player;
				
				return false;
			}
		}

		List<Match> allMatches = TournamentService.getAllMatches(tournament);
		if (allMatches.size() == 0)
			return "Es wurden noch keine Spiele gespielt";

		ArrayList<PlayerWithCards> playersWithCards = new ArrayList<PlayerWithCards>();
		for (Match match : allMatches) {
			List<CardEvent> allCards = new LinkedList<CardEvent>();
			Collections.filterAndChangeType(match.getEvents(),
					new FilterCardEvent(), allCards);
			int i = -1;
			for (CardEvent eventCard : allCards) {
				PlayerWithCards player = new PlayerWithCards(eventCard.getInvolvedPlayer());
				i = playersWithCards.indexOf(player);
				if(i<0)
					playersWithCards.add(player);
				else
					playersWithCards.get(i).cards++;
			}
		}

		if (playersWithCards.size() == 0)
			return "Es wurden keine Karten vergeben";
		
		java.util.Collections.sort(playersWithCards);

		PlayerWithCards player = playersWithCards.get(0);
		return String.format("%s %d Karten",player.player.getName(), player.cards);
	
	}

	public static List<KnockoutMatch> getAllMatches(KnockoutMatch root) {
		List<KnockoutMatch> matches = new LinkedList<KnockoutMatch>();

		// BFS iteration
		Stack<KnockoutMatch> stack = new Stack<KnockoutMatch>();
		stack.add(root);
		KnockoutMatch tmpNode;
		while (stack.size() > 0) {
			tmpNode = stack.pop();
			if (tmpNode.getChilds().size() == 0)
				matches.add(tmpNode);
			else
				stack.addAll(tmpNode.getChilds());
		}

		return matches;
	}

}
