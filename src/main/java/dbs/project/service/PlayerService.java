
package dbs.project.service;
import dbs.project.dao.EventSubstitutionDao;
import dbs.project.entity.Match;
import dbs.project.entity.Player;

public class PlayerService {
	public static boolean playerHasPlayed(Player player, Match match) {
		
		if(EventSubstitutionDao.findByPlayerAndMatch(player, match).size()>0)
			return true;
		if(match.getGuestLineup().contains(player) || match.getHostLineup().contains(player))
			return true;
		return false;
	}
}