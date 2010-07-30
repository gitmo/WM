package dbs.project.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.GroupStage;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Stadium;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.exception.NewPlayerHasPlayedBefore;
import dbs.project.exception.NoMinuteSet;
import dbs.project.exception.NotInSameTeam;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.service.GroupStageService;
import dbs.project.service.MatchService;
import dbs.project.service.TeamService;
import dbs.project.util.MatchMinute;

public class GroupStageGenerator {
	private static final int MAX_TEAMS_PER_GROUP = 4;

	/**
	 * generates a GroupStage out of a list of Teams
	 * 
	 * @param teams
	 * @param stadiums
	 * @return
	 * @throws Exception
	 */
	public static GroupStage getByTeams(List<Team> teams,
			List<Stadium> stadiums, Tournament tournament) throws Exception {
		if (teams.size() % MAX_TEAMS_PER_GROUP != 0)
			throw new Exception("Could not generate equally groups");

		int numberOfGroups = teams.size() / MAX_TEAMS_PER_GROUP;
		List<TournamentGroup> allGroups = new LinkedList<TournamentGroup>();
		Random random = new Random();
		for (int i = 0; i < numberOfGroups; i++) {
			TournamentGroup currentGroup = new TournamentGroup();
			currentGroup.setName(String.format("Group %c", i + 65));
			currentGroup.setTournament(tournament);

			List<Team> groupTeams = new LinkedList<Team>();
			for (int j = 0; j < MAX_TEAMS_PER_GROUP; j++)
				groupTeams.add(teams.remove(random.nextInt(teams.size())));

			currentGroup.setTeams(groupTeams);
			// Benutzt nur eine Kopie von groupTeams, da es die Liste selbst
			// verändert
			currentGroup.setMatches(generateMatches(new LinkedList<Team>(
					groupTeams), stadiums, currentGroup));

			allGroups.add(currentGroup);
		}

		GroupStage groupStage = new GroupStage();
		groupStage.setGroups(allGroups);

		return groupStage;
	}

	/**
	 * Rekursive Generation der Matches Rekursionsanker: nur noch 1 Mannschaft
	 * in groupTeams
	 * 
	 * @param matches
	 * @param groupTeams
	 * @return
	 */
	private static List<GroupMatch> generateRecursivlyMatches(
			List<GroupMatch> matches, List<Team> groupTeams,
			List<Stadium> stadiums, TournamentGroup group) {
		if (groupTeams.size() <= 1)
			return matches;

		Team currentTeam = groupTeams.remove(0);
		// Signalisiert ob es ein Heimspiel für currentTeam
		boolean homeMatch = true;
		// Lässt currentTeam einmal gegen jede andere Mannschaft spielen
		for (Team opponent : groupTeams) {
			GroupMatch match = new GroupMatch();
			match.setStadium(stadiums.remove(0));
			stadiums.add(match.getStadium());
			match.setGroup(group);
			match.setTournament(group.getTournament());

			if (homeMatch) {
				match.setHostTeam(currentTeam);
				match.setGuestTeam(opponent);
			} else {
				match.setHostTeam(opponent);
				match.setGuestTeam(currentTeam);
			}

			homeMatch = !homeMatch;
			matches.add(match);
		}

		return generateRecursivlyMatches(matches, groupTeams, stadiums, group);
	}

	/**
	 * generates the groupGames for a list of teams
	 * 
	 * @param groupTeams
	 * @param stadiums
	 * @return
	 */
	public static List<GroupMatch> generateMatches(List<Team> groupTeams,
			List<Stadium> stadiums, TournamentGroup group) {
		// Generiert rekursiv die Spiele.
		return generateRecursivlyMatches(new LinkedList<GroupMatch>(),
				groupTeams, stadiums, group);
	}

	public static void enterResults(GroupStage groupStage) {
		Random randomizer = new Random();
		for (Match match : GroupStageService.getAllMatches(groupStage)) {
			if (match.isPlayed())
				continue;

			MatchGenerator.insertPlayersToMatch(match);
			int hazard, minute;
			for (int i = 0; i < 10; i++) {
				Team affectedTeam;
				if (randomizer.nextBoolean())
					affectedTeam = match.getGuestTeam();
				else
					affectedTeam = match.getHostTeam();

				hazard = randomizer.nextInt(100);
				minute = randomizer.nextInt(90);
				
				List<Player> players = TeamService.getPlayingPlayersInAMatchForTeam(
						match, affectedTeam, new MatchMinute(minute));
				Player affectedPlayer = players.get(randomizer.nextInt(11));

				if (hazard < 20) {
					GoalEvent goal = new GoalEvent();
					goal.setMatch(match);
					goal.setMinute(minute);
					goal.setScorringTeam(affectedTeam);
					try {
						MatchService.insertGoal(goal, affectedPlayer, match);
					} catch (PlayerDoesNotPlay e) {
					} catch (NoMinuteSet e) {
					}
				} else if (hazard < 40) {
					String color = randomizer.nextBoolean() ? "rot" : "gelb";
					MatchService.addCard(minute, affectedPlayer, color, match);
				} else if (hazard < 60) {
					Player newPlayer = TeamService.getPlayersOnTheBench(match,
							affectedTeam, new MatchMinute(minute, 0)).get(0);
					try {
						MatchService.substitutePlayers(affectedPlayer,
								newPlayer, minute, match);
					} catch (NewPlayerHasPlayedBefore e) {
					} catch (PlayerDoesNotPlay e) {
					} catch (NotInSameTeam e) {
					}
				}
				MatchService.setFinalWhistle(90, match);
			}
		}
	}

}
