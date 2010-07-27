
package dbs.project.service;
import java.util.ArrayList;
import java.util.List;

import dbs.project.dao.EventSubstitutionDao;
import dbs.project.entity.EventSubstitution;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.service.event.filter.FilterSubstitutions;
import dbs.project.util.Collections;
import dbs.project.util.Tuple;

public class PlayerService {

	public static Tuple<Integer> playerOnField(Player player, Match match) throws PlayerDoesNotPlay{
		Tuple<Integer> tuple = null;
		List<EventSubstitution> subs = new ArrayList<EventSubstitution>();
		Collections.filterAndChangeType(match.getEvents(), new FilterSubstitutions(), subs);
	
		if (match.getGuestLineup().contains(player))
			tuple.setFirst(0);
		else if (match.getHostLineup().contains(player))
			tuple.setFirst(0);
		else{
			for(EventSubstitution es : subs){
				if(es.getNewPlayer()==player){
					tuple.setFirst(es.getMinute());
					break;
				}	
			}
		}
		
		if(tuple ==  null)
			throw new PlayerDoesNotPlay();
		
		for(EventSubstitution es : subs){
			if(es.getInvolvedPlayer()==player){
				tuple.setSecond(es.getMinute());
				return tuple;
			}
		}
		
		/*TODO Nachspielzeit*/
		tuple.setSecond(90);
		return tuple;
		
	}
	
	/**
	 * Has player played in match?
	 * @param player
	 * @param match
	 * @return
	 */
	public static boolean playerHasPlayed(Player player, Match match) {
		
		if(EventSubstitutionDao.findByPlayerAndMatch(player, match).size()>0)
			return true;
		if(match.getGuestLineup().contains(player) || match.getHostLineup().contains(player))
			return true;
		return false;
	}
	
	
	
}