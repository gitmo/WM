package dbs.project.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import javax.swing.ListModel;

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
import dbs.project.service.event.filter.FilterCards;
import dbs.project.service.event.filter.FilterGoals;
import dbs.project.stage.KnockoutStage;

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

			public int compareTo(Topscorer o) {
				if (this.goals > o.goals)
					return -1;
				else if (this.goals < o.goals)
					return 1;
				else
					return 0;
			}
		}

		List<Match> allMatches = TournamentService.getAllMatches(tournament);
		if (allMatches.size() == 0)
			return "Es wurden noch keine Spiele gespielt";

		ArrayList<Topscorer> topscorer = new ArrayList<Topscorer>();
		for (Match match : allMatches) {
			List<GoalEvent> allGoals = new LinkedList<GoalEvent>();
			dbs.project.util.Collections.filterAndChangeType(match.getEvents(),
					new FilterGoals(), allGoals);
			for (GoalEvent eventGoal : allGoals) {
				int i = topscorer.indexOf(eventGoal.getInvolvedPlayer());
				topscorer.get(i).goals++;
			}
		}
		TreeSet<Topscorer> topscorerTree = new TreeSet<Topscorer>(topscorer);
		if (topscorerTree.size() == 0)
			return "Es wurden keine Tore geschoßen";

		return topscorerTree.first().player.toString();
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

			public int compareTo(PlayerWithCards o) {
				if (this.cards > o.cards)
					return -1;
				else if (this.cards < o.cards)
					return 1;
				else
					return 0;
			}
		}

		List<Match> allMatches = TournamentService.getAllMatches(tournament);
		if (allMatches.size() == 0)
			return "Es wurden noch keine Spiele gespielt";

		ArrayList<PlayerWithCards> playersWithCards = new ArrayList<PlayerWithCards>();
		for (Match match : allMatches) {
			List<CardEvent> allCards = new LinkedList<CardEvent>();
			dbs.project.util.Collections.filterAndChangeType(match.getEvents(),
					new FilterCards(), allCards);
			for (CardEvent eventCard : allCards) {
				int i = playersWithCards.indexOf(eventCard.getInvolvedPlayer());
				playersWithCards.get(i).cards++;
			}
		}

		TreeSet<PlayerWithCards> topscorerTree = new TreeSet<PlayerWithCards>(
				playersWithCards);
		if (topscorerTree.size() == 0)
			return "Es wurden keine Karten vergeben";

		return topscorerTree.first().player.toString();
	}

	public static List<KnockoutMatch> getAllMatches(KnockoutStage knockoutStage) {
		List<KnockoutMatch> matches = new LinkedList<KnockoutMatch>();

		// BFS iteration
		Stack<KnockoutMatch> stack = new Stack<KnockoutMatch>();
		stack.add(knockoutStage.getFinalMatch());
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
