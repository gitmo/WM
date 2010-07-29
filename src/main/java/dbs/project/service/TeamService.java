package dbs.project.service;

import java.util.List;

import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.util.Tuple;

public class TeamService {

	public static List<Player> getPlayingPlayersForTeam(Match match, Team team) {

		List<Player> playingPlayers = MatchService
				.getLineupForTeam(team, match);
		int i;
		for (Tuple<Player, Player> substitution : MatchService
				.getSubstitutedPlayersByTeam(team, match)) {
			i = playingPlayers.indexOf(substitution.getFirst());
			if (i >= 0) {
				playingPlayers.remove(i);
				playingPlayers.add(substitution.getSecond());
			}
		}
		return playingPlayers;
	}

	public static List<Player> getPlayersOnTheBench(Match match, Team team) {
		List<Player> allPlayers = team.getPlayers();
		// allPlayers.removeAll(getPlayingPlayersForTeam(match, team));
		return allPlayers;
	}

}
