package dbs.project.service;

import java.util.ArrayList;
import java.util.List;

import dbs.project.dao.EventSubstitutionDao;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.exception.NoMatchWhistleEvent;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.service.event.filter.FilterSubstitutions;
import dbs.project.util.Collections;
import dbs.project.util.Tuple;

public class PlayerService {

	/**
	 * returns the entry and exitTime of the player
	 * 
	 * @param player
	 * @param match
	 * @return
	 * @throws PlayerDoesNotPlay
	 * @throws NoMatchWhistleEvent
	 */
	public static Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> playerOnField(
			Player player, Match match) throws PlayerDoesNotPlay,
			NoMatchWhistleEvent {
		Tuple<Integer, Integer> in = null;
		Tuple<Integer, Integer> out = null;
		List<SubstitutionEvent> subs = new ArrayList<SubstitutionEvent>();
		Collections.filterAndChangeType(match.getEvents(),
				new FilterSubstitutions(), subs);

		if (match.getGuestLineup().contains(player))
			in = new Tuple<Integer, Integer>(0, 0);
		else if (match.getHostLineup().contains(player))
			in = new Tuple<Integer, Integer>(0, 0);
		else {
			for (SubstitutionEvent es : subs) {
				if (es.getNewPlayer() == player) {
					in = es.getMinute();
					break;
				}
			}
		}

		if (in == null)
			throw new PlayerDoesNotPlay();

		for (SubstitutionEvent es : subs) {
			if (es.getInvolvedPlayer() == player) {
				out = es.getMinute();
				return new Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>>(
						in, out);
			}
		}

		out = MatchService.getFinalWhistleTime(match);

		return new Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>>(in,
				out);

	}

	/**
	 * Has player played in match?
	 * 
	 * @param player
	 * @param match
	 * @return
	 */
	public static boolean playerHasPlayed(Player player, Match match) {

		if (EventSubstitutionDao.findByPlayerAndMatch(player, match).size() > 0)
			return true;
		if (match.getGuestLineup().contains(player)
				|| match.getHostLineup().contains(player))
			return true;
		return false;
	}

}