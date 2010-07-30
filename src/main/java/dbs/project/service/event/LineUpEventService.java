package dbs.project.service.event;

import java.util.LinkedList;
import java.util.List;

import dbs.project.dao.event.LineUpEventDao;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.LineUpEvent;

public class LineUpEventService {

	public static List<Player> getPlayersByMatchForTeam(Match match, Team team) {
		List<Player> players = new LinkedList<Player>();
		for (LineUpEvent event : LineUpEventDao.findByMatchForTeam(match, team))
			players.add(event.getInvolvedPlayer());

		return players;
	}
	
	public static List<Player> getPlayersByMatch(Match match) {
		List<Player> players = new LinkedList<Player>();
		for(LineUpEvent event : LineUpEventDao.findByMatch(match))
			players.add(event.getInvolvedPlayer());
		
		return players;
	}
}
