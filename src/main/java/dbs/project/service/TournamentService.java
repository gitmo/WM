package dbs.project.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.ListModel;

import dbs.project.collections.filter.FilterCardEvent;
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
import dbs.project.service.event.GoalEventService;
import dbs.project.util.Collections;

/**
 * Service methods for tournament specific functionality.
 */
public class TournamentService {

	/**
	 * Use Case: Display 1st, 2nd and 3rd place of the tournament.
	 * 
	 * @param tournament
	 * @return A List of 1st, 2nd and 3rd (in that order) team.
	 * @throws TournamentNotOver
	 */
	public static List<Team> weAreTheChampions(Tournament tournament)
			throws TournamentNotOver {
		Match finalMatch = tournament.getFinalMatch();
		Match forThirdPlace = tournament.getMatchForThirdPlace();
		// The finalists are not known yet.
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

	/**
	 * ListModel for our GUI for tournament objects.
	 * 
	 * @return A javax.swing.ListModel to get the cell values and size of the
	 *         all list of all tournaments.
	 */
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

	/**
	 * Calculate top scorer of the tounament.
	 * 
	 * @param tournament
	 * @return A string of the top scorers's name and his number of goals.
	 */
	public static String getTopscorers(Tournament tournament) {

		/**
		 * Inner helper class to compare player object by number of goals
		 */
		class Topscorer implements Comparable<Topscorer> {
			Player player;
			Integer goals = 1;

			public Topscorer(Player player) {
				this.player = player;
			}

			public int compareTo(Topscorer o) {
				return o.goals.compareTo(this.goals);
			}

			@Override
			public boolean equals(Object obj) {
				if (obj instanceof Topscorer)
					return this.player == ((Topscorer) obj).player;

				return false; // Comparing apples and oranges
			}
		}

		// Fetch a list of all matches played so far.
		// List<Match> allMatches = TournamentService.getAllMatches(tournament);
		// if (allMatches.size() == 0)
		// return "Es wurden noch keine Spiele gespielt";

		// Create a list of players with their goals attached (Topscorer obj.)
		ArrayList<Topscorer> topscorers = new ArrayList<Topscorer>();
		List<GoalEvent> allGoals = GoalEventService
				.getGoalsByTournament(tournament);
		int i = -1;
		for (GoalEvent eventGoal : allGoals) {
			Topscorer player = new Topscorer(eventGoal.getInvolvedPlayer());
			i = topscorers.indexOf(player);
			// TODO: of by one? See getPlayerWithMostCards.
			if (i < 0) // First goal so far.
				topscorers.add(player);
			else
				// Known scorer, add to his record.
				topscorers.get(i).goals++;
		}

		// Special case: no goals yet.
		if (topscorers.size() == 0)
			return "Es wurden keine Tore geschoßen";

		// Sort (via compareTo) ...
		java.util.Collections.sort(topscorers);

		// ... and get the highest scorer.
		Topscorer topscorer = topscorers.get(0);
		return String.format("%s %d Tore", topscorer.player.getName(),
				topscorer.goals);
	}

	/**
	 * Get all the matches that have already been played this tournament (group
	 * + knockout stage).
	 * 
	 * @param tournament
	 * @return A list of this tournaments matches.
	 */
	public static List<Match> getAllMatches(Tournament tournament) {
		List<Match> matches = new LinkedList<Match>();
		matches.addAll(GroupStageService.getAllMatches(tournament
				.getGroupStage()));
		matches.addAll(KnockoutMatchService.getAllMatches(tournament
				.getFinalMatch()));
		matches.add(tournament.getMatchForThirdPlace());
		return matches;
	}

	/**
	 * Calculates the player with the most fouls and returns his name and number
	 * of cards.
	 * 
	 * @param tournament
	 * @return A String of the form "Player Name # Cards" (or a special message
	 *         if there's been no fouls yet).
	 */
	public static String getPlayerWithMostCards(Tournament tournament) {
		/**
		 * Inner helper class to rank player objects by number of cards (via
		 * compareTo).
		 */
		class PlayerWithCards implements Comparable<PlayerWithCards> {
			Player player;
			Integer cards = 1;

			public PlayerWithCards(Player player) {
				this.player = player;
			}

			// Compare by number of cards
			public int compareTo(PlayerWithCards o) {
				// TODO: Compare seems kinda reversed (see compareTo javadoc)?
				return o.cards.compareTo(this.cards);
				// if (this.cards > o.cards)
				// return -1;
				// else if (this.cards < o.cards)
				// return 1;
				// else
				// return 0;
			}

			@Override
			public boolean equals(Object obj) {
				if (obj instanceof PlayerWithCards)
					return this.player == ((PlayerWithCards) obj).player;

				return false; // Apples and oranges
			}
		}

		List<Match> allMatches = TournamentService.getAllMatches(tournament);
		if (allMatches.size() == 0)
			return "Es wurden noch keine Spiele gespielt";

		// Create a list of players with their cards attached (objects of our
		// helper)
		ArrayList<PlayerWithCards> playersWithCards = new ArrayList<PlayerWithCards>();
		for (Match match : allMatches) {
			List<CardEvent> allCards = new LinkedList<CardEvent>();
			// Filter events by cards
			Collections.filterAndChangeType(match.getEvents(),
					new FilterCardEvent(), allCards);
			int i = -1;
			for (CardEvent eventCard : allCards) {
				PlayerWithCards player = new PlayerWithCards(
						eventCard.getInvolvedPlayer());
				i = playersWithCards.indexOf(player);
				// TODO: Are we of by one? I.e. shouldn't a first offender get a
				// card count of one?
				if (i < 0) // not yet in the list
					playersWithCards.add(player);
				else
					// known offender add to his record
					playersWithCards.get(i).cards++;
			}
		}

		// Special case: no fouls yet.
		if (playersWithCards.size() == 0)
			return "Es wurden keine Karten vergeben";

		// Sort...
		java.util.Collections.sort(playersWithCards);

		// ... and get the top offender
		PlayerWithCards player = playersWithCards.get(0);
		return String.format("%s %d Karten", player.player.getName(),
				player.cards);

	}

	/**
	 * The tree of the knockout stage matches is flattened to a list of matches.
	 * 
	 * @param root
	 * @return Returns a list of matches in the knockout stage of the
	 *         tournament.
	 */
	public static List<KnockoutMatch> getAllMatches(KnockoutMatch root) {
		List<KnockoutMatch> matches = new LinkedList<KnockoutMatch>();

		// BFS tree traversal -> use a stack
		Stack<KnockoutMatch> stack = new Stack<KnockoutMatch>();
		stack.add(root);
		KnockoutMatch tmpNode;
		while (stack.size() > 0) {
			tmpNode = stack.pop();
			if (tmpNode.getChildren().size() == 0) // Leaf: add this match
				matches.add(tmpNode);
			else
				// Node: there's more to traverses
				stack.addAll(tmpNode.getChildren());
		}

		return matches;
	}

}
