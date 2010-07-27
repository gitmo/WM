package dbs.project.service;

import java.util.ArrayList;
import java.util.List;

import dbs.project.dao.EventSubstitutionDao;
import dbs.project.entity.EventSubstitution;
import dbs.project.entity.KnockoutMatch;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
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
	 */
	public static Tuple<Tuple<Integer>> playerOnField(Player player, Match match)
			throws PlayerDoesNotPlay {
		Tuple<Integer> in = null;
		Tuple<Integer> out = null;
		List<EventSubstitution> subs = new ArrayList<EventSubstitution>();
		Collections.filterAndChangeType(match.getEvents(),
				new FilterSubstitutions(), subs);

		if (match.getGuestLineup().contains(player))
			in = new Tuple<Integer>(0, 0);
		else if (match.getHostLineup().contains(player))
			in = new Tuple<Integer>(0, 0);
		else {
			for (EventSubstitution es : subs) {
				if (es.getNewPlayer() == player) {
					in = new Tuple<Integer>(es.getMinute(), es.getAddTime());
					break;
				}
			}
		}

		if (in == null)
			throw new PlayerDoesNotPlay();

		for (EventSubstitution es : subs) {
			if (es.getInvolvedPlayer() == player) {
				out = new Tuple<Integer>(es.getMinute(), es.getAddTime());
				return new Tuple<Tuple<Integer>>(in, out);
			}
		}

		if (match.getClass() == KnockoutMatch.class) {
			if (((KnockoutMatch) match).getExtraTime())
				out = new Tuple<Integer>(120, ((KnockoutMatch) match)
						.getAddTimeForth());
		} else
			out = new Tuple<Integer>(90, match.getAddTimeSecond());
		return new Tuple<Tuple<Integer>>(in, out);

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