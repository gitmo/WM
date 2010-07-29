package dbs.project.service;

import java.util.ArrayList;
import java.util.List;

import dbs.project.collections.filter.FilterSubstitutionEvent;
import dbs.project.dao.event.SubstitutionEventDao;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.exception.NoMatchWhistleEvent;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.util.Collections;
import dbs.project.util.MatchMinute;
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
	public static Tuple<MatchMinute, MatchMinute> playerOnField(Player player,
			Match match) throws PlayerDoesNotPlay, NoMatchWhistleEvent {
		MatchMinute in = null;
		MatchMinute out = null;
		List<SubstitutionEvent> subs = new ArrayList<SubstitutionEvent>();
		Collections.filterAndChangeType(match.getEvents(),
				new FilterSubstitutionEvent(), subs);

		if (MatchService.getGuestLineup(match).contains(player))
			in = new MatchMinute(0, 0);
		else if (MatchService.getHostLineup(match).contains(player))
			in = new MatchMinute(0, 0);
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
				return new Tuple<MatchMinute, MatchMinute>(in, out);
			}
		}

		out = MatchService.getFinalWhistleTime(match);

		return new Tuple<MatchMinute, MatchMinute>(in, out);

	}

	/**
	 * Has player played in match?
	 * 
	 * @param player
	 * @param match
	 * @return
	 */
	public static boolean playerHasPlayed(Player player, Match match) {

		if (SubstitutionEventDao.findByPlayerAndMatch(player, match).size() > 0)
			return true;
			return playerInLineUpOfMatch(player, match);
	}

	
	public static boolean playerInLineUpOfMatch(Player player, Match match){
		if (MatchService.getGuestLineup(match).contains(player)
				|| MatchService.getHostLineup(match).contains(player))
			return true;
		return false;

	}
	
}