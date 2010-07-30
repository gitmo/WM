package dbs.project.generator;

import java.util.List;
import java.util.Random;

import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.exception.PlayerDoesNotPlayForTeam;
import dbs.project.exception.TeamLineUpComplete;
import dbs.project.service.MatchService;
import dbs.project.service.TeamService;
import dbs.project.util.MatchMinute;

public class MatchGenerator {

	public static void insertPlayersToMatch(Match match) {
		Random randomizer = new Random();
		Team[] teams = new Team[2];
		teams[0] = match.getHostTeam();
		teams[1] = match.getGuestTeam();

		int j = 0;
		for (int i = 0; i < 22; i++) {
			List<Player> players = TeamService.getPlayersOnTheBench(match,
					teams[j], new MatchMinute(0));

			Player newPlayer = players.get(randomizer.nextInt(players.size()));
			try {
				MatchService.insertPlayerToMatch(newPlayer, match);
			} catch (PlayerDoesNotPlayForTeam e) {
			} catch (TeamLineUpComplete e) {
			}

			j = (++j) % 2;
		}
	}

}
