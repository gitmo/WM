package dbs.project.service;

import dbs.project.entity.Match;
import dbs.project.entity.Player;

public class PlayerService {

	public static boolean playerHasPlayed(Player player, Match match) {
		/*TODO: Auswechslungen beachten*/
		if(match.getGuestLineup().contains(player) || match.getHostLineup().contains(player))
			return true;
		return false;
	}

}
