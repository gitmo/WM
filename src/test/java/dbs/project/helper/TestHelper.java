package dbs.project.helper;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.GroupStage;
import dbs.project.entity.KnockoutMatch;
import dbs.project.entity.Stadium;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;

public class TestHelper {

	public static Tournament manualTournament() {
		// generate teams for group A
		Team teamA1 = new Team("teamA1", null, null, null, null);
		Team teamA2 = new Team("teamA2", null, null, null, null);
		Team teamA3 = new Team("teamA3", null, null, null, null);
		Team teamA4 = new Team("teamA4", null, null, null, null);

		ArrayList<Team> teamsA = new ArrayList<Team>();
		teamsA.add(teamA1);
		teamsA.add(teamA2);
		teamsA.add(teamA3);
		teamsA.add(teamA4);

		// generate teams for group B
		Team teamB1 = new Team("teamB1", null, null, null, null);
		Team teamB2 = new Team("teamB2", null, null, null, null);
		Team teamB3 = new Team("teamB3", null, null, null, null);
		Team teamB4 = new Team("teamB4", null, null, null, null);

		ArrayList<Team> teamsB = new ArrayList<Team>();
		teamsB.add(teamB1);
		teamsB.add(teamB2);
		teamsB.add(teamB3);
		teamsB.add(teamB4);

		// generate teams for group C
		Team teamC1 = new Team("teamC1", null, null, null, null);
		Team teamC2 = new Team("teamC2", null, null, null, null);
		Team teamC3 = new Team("teamC3", null, null, null, null);
		Team teamC4 = new Team("teamC4", null, null, null, null);

		ArrayList<Team> teamsC = new ArrayList<Team>();
		teamsC.add(teamC1);
		teamsC.add(teamC2);
		teamsC.add(teamC3);
		teamsC.add(teamC4);

		// generate teams for group D
		Team teamD1 = new Team("teamD1", null, null, null, null);
		Team teamD2 = new Team("teamD2", null, null, null, null);
		Team teamD3 = new Team("teamD3", null, null, null, null);
		Team teamD4 = new Team("teamD4", null, null, null, null);

		ArrayList<Team> teamsD = new ArrayList<Team>();
		teamsD.add(teamD1);
		teamsD.add(teamD2);
		teamsD.add(teamD3);
		teamsD.add(teamD4);

		// generate teams for group E
		Team teamE1 = new Team("teamE1", null, null, null, null);
		Team teamE2 = new Team("teamE2", null, null, null, null);
		Team teamE3 = new Team("teamE3", null, null, null, null);
		Team teamE4 = new Team("teamE4", null, null, null, null);

		ArrayList<Team> teamsE = new ArrayList<Team>();
		teamsE.add(teamE1);
		teamsE.add(teamE2);
		teamsE.add(teamE3);
		teamsE.add(teamE4);

		// generate teams for group F
		Team teamF1 = new Team("teamF1", null, null, null, null);
		Team teamF2 = new Team("teamF2", null, null, null, null);
		Team teamF3 = new Team("teamF3", null, null, null, null);
		Team teamF4 = new Team("teamF4", null, null, null, null);

		ArrayList<Team> teamsF = new ArrayList<Team>();
		teamsF.add(teamF1);
		teamsF.add(teamF2);
		teamsF.add(teamF3);
		teamsF.add(teamF4);

		// generate teams for group G
		Team teamG1 = new Team("teamA1", null, null, null, null);
		Team teamG2 = new Team("teamG2", null, null, null, null);
		Team teamG3 = new Team("teamG3", null, null, null, null);
		Team teamG4 = new Team("teamG4", null, null, null, null);

		ArrayList<Team> teamsG = new ArrayList<Team>();
		teamsG.add(teamG1);
		teamsG.add(teamG2);
		teamsG.add(teamG3);
		teamsG.add(teamG4);

		// generate teams for group H
		Team teamH1 = new Team("teamA1", null, null, null, null);
		Team teamH2 = new Team("teamH2", null, null, null, null);
		Team teamH3 = new Team("teamH3", null, null, null, null);
		Team teamH4 = new Team("teamH4", null, null, null, null);

		ArrayList<Team> teamsH = new ArrayList<Team>();
		teamsH.add(teamH1);
		teamsH.add(teamH2);
		teamsH.add(teamH3);
		teamsH.add(teamH4);

		// all teams in one list
		ArrayList<Team> teams = new ArrayList<Team>();
		teams.addAll(teamsA);
		teams.addAll(teamsB);
		teams.addAll(teamsC);
		teams.addAll(teamsD);
		teams.addAll(teamsE);
		teams.addAll(teamsF);
		teams.addAll(teamsG);
		teams.addAll(teamsH);

		// set teams in Groups
		TournamentGroup groupA = new TournamentGroup();
		groupA.setTeams(teamsA);

		TournamentGroup groupB = new TournamentGroup();
		groupB.setTeams(teamsB);

		TournamentGroup groupC = new TournamentGroup();
		groupC.setTeams(teamsC);

		TournamentGroup groupD = new TournamentGroup();
		groupD.setTeams(teamsD);

		TournamentGroup groupE = new TournamentGroup();
		groupE.setTeams(teamsE);

		TournamentGroup groupF = new TournamentGroup();
		groupF.setTeams(teamsF);

		TournamentGroup groupG = new TournamentGroup();
		groupG.setTeams(teamsG);

		TournamentGroup groupH = new TournamentGroup();
		groupH.setTeams(teamsH);

		// name groups
		groupA.setName("Group A");
		groupB.setName("Group B");
		groupC.setName("Group C");
		groupD.setName("Group D");
		groupE.setName("Group E");
		groupF.setName("Group F");
		groupG.setName("Group G");
		groupH.setName("Group H");

		// generate groupmatches for team A
		GroupMatch groupMatchA1 = new GroupMatch();
		GroupMatch groupMatchA2 = new GroupMatch();
		GroupMatch groupMatchA3 = new GroupMatch();
		GroupMatch groupMatchA4 = new GroupMatch();
		GroupMatch groupMatchA5 = new GroupMatch();
		GroupMatch groupMatchA6 = new GroupMatch();

		groupMatchA1.setHostTeam(teamA1);
		groupMatchA1.setGuestTeam(teamA2);

		groupMatchA2.setHostTeam(teamA3);
		groupMatchA2.setGuestTeam(teamA1);

		groupMatchA3.setHostTeam(teamA1);
		groupMatchA3.setGuestTeam(teamA4);

		groupMatchA4.setHostTeam(teamA2);
		groupMatchA4.setGuestTeam(teamA3);

		groupMatchA5.setHostTeam(teamA4);
		groupMatchA5.setGuestTeam(teamA2);

		groupMatchA6.setHostTeam(teamA3);
		groupMatchA6.setGuestTeam(teamA4);

		// matches of group A in a list
		ArrayList<GroupMatch> matchesA = new ArrayList<GroupMatch>();
		matchesA.add(groupMatchA1);
		matchesA.add(groupMatchA2);
		matchesA.add(groupMatchA3);
		matchesA.add(groupMatchA4);
		matchesA.add(groupMatchA5);
		matchesA.add(groupMatchA6);

		groupA.setMatches(matchesA);

		// generate groupMatches for team B
		GroupMatch groupMatchB1 = new GroupMatch();
		GroupMatch groupMatchB2 = new GroupMatch();
		GroupMatch groupMatchB3 = new GroupMatch();
		GroupMatch groupMatchB4 = new GroupMatch();
		GroupMatch groupMatchB5 = new GroupMatch();
		GroupMatch groupMatchB6 = new GroupMatch();

		List<GroupMatch> matchesB = new ArrayList<GroupMatch>();
		matchesB.add(groupMatchB1);
		matchesB.add(groupMatchB2);
		matchesB.add(groupMatchB3);
		matchesB.add(groupMatchB4);
		matchesB.add(groupMatchB5);
		matchesB.add(groupMatchB6);

		groupB.setMatches(matchesB);

		// generate groupmatches for team C
		GroupMatch groupMatchC1 = new GroupMatch();
		GroupMatch groupMatchC2 = new GroupMatch();
		GroupMatch groupMatchC3 = new GroupMatch();
		GroupMatch groupMatchC4 = new GroupMatch();
		GroupMatch groupMatchC5 = new GroupMatch();
		GroupMatch groupMatchC6 = new GroupMatch();

		List<GroupMatch> matchesC = new ArrayList<GroupMatch>();
		matchesC.add(groupMatchC1);
		matchesC.add(groupMatchC2);
		matchesC.add(groupMatchC3);
		matchesC.add(groupMatchC4);
		matchesC.add(groupMatchC5);
		matchesC.add(groupMatchC6);

		groupC.setMatches(matchesC);

		// generate groupmatches for team D
		GroupMatch groupMatchD1 = new GroupMatch();
		GroupMatch groupMatchD2 = new GroupMatch();
		GroupMatch groupMatchD3 = new GroupMatch();
		GroupMatch groupMatchD4 = new GroupMatch();
		GroupMatch groupMatchD5 = new GroupMatch();
		GroupMatch groupMatchD6 = new GroupMatch();

		List<GroupMatch> matchesD = new ArrayList<GroupMatch>();
		matchesD.add(groupMatchD1);
		matchesD.add(groupMatchD2);
		matchesD.add(groupMatchD3);
		matchesD.add(groupMatchD4);
		matchesD.add(groupMatchD5);
		matchesD.add(groupMatchD6);

		groupD.setMatches(matchesD);

		// generate groupmatches for team E
		GroupMatch groupMatchE1 = new GroupMatch();
		GroupMatch groupMatchE2 = new GroupMatch();
		GroupMatch groupMatchE3 = new GroupMatch();
		GroupMatch groupMatchE4 = new GroupMatch();
		GroupMatch groupMatchE5 = new GroupMatch();
		GroupMatch groupMatchE6 = new GroupMatch();

		List<GroupMatch> matchesE = new ArrayList<GroupMatch>();
		matchesE.add(groupMatchE1);
		matchesE.add(groupMatchE2);
		matchesE.add(groupMatchE3);
		matchesE.add(groupMatchE4);
		matchesE.add(groupMatchE5);
		matchesE.add(groupMatchE6);

		groupE.setMatches(matchesE);

		// generate groupmatches for team F
		GroupMatch groupMatchF1 = new GroupMatch();
		GroupMatch groupMatchF2 = new GroupMatch();
		GroupMatch groupMatchF3 = new GroupMatch();
		GroupMatch groupMatchF4 = new GroupMatch();
		GroupMatch groupMatchF5 = new GroupMatch();
		GroupMatch groupMatchF6 = new GroupMatch();

		List<GroupMatch> matchesF = new ArrayList<GroupMatch>();
		matchesF.add(groupMatchF1);
		matchesF.add(groupMatchF2);
		matchesF.add(groupMatchF3);
		matchesF.add(groupMatchF4);
		matchesF.add(groupMatchF5);
		matchesF.add(groupMatchF6);

		groupF.setMatches(matchesF);

		// generate groupmatches for team G
		GroupMatch groupMatchG1 = new GroupMatch();
		GroupMatch groupMatchG2 = new GroupMatch();
		GroupMatch groupMatchG3 = new GroupMatch();
		GroupMatch groupMatchG4 = new GroupMatch();
		GroupMatch groupMatchG5 = new GroupMatch();
		GroupMatch groupMatchG6 = new GroupMatch();

		List<GroupMatch> matchesG = new ArrayList<GroupMatch>();
		matchesG.add(groupMatchG1);
		matchesG.add(groupMatchG2);
		matchesG.add(groupMatchG3);
		matchesG.add(groupMatchG4);
		matchesG.add(groupMatchG5);
		matchesG.add(groupMatchG6);

		groupG.setMatches(matchesG);

		// generate groupmatches for team H
		GroupMatch groupMatchH1 = new GroupMatch();
		GroupMatch groupMatchH2 = new GroupMatch();
		GroupMatch groupMatchH3 = new GroupMatch();
		GroupMatch groupMatchH4 = new GroupMatch();
		GroupMatch groupMatchH5 = new GroupMatch();
		GroupMatch groupMatchH6 = new GroupMatch();

		List<GroupMatch> matchesH = new ArrayList<GroupMatch>();
		matchesH.add(groupMatchH1);
		matchesH.add(groupMatchH2);
		matchesH.add(groupMatchH3);
		matchesH.add(groupMatchH4);
		matchesH.add(groupMatchH5);
		matchesH.add(groupMatchH6);

		groupH.setMatches(matchesH);

		// groups to list
		ArrayList<TournamentGroup> groups = new ArrayList<TournamentGroup>();
		groups.add(groupA);
		groups.add(groupB);
		groups.add(groupC);
		groups.add(groupD);
		groups.add(groupE);
		groups.add(groupF);
		groups.add(groupG);
		groups.add(groupH);

		// generate a list of stadiums
		Stadium stadium1 = new Stadium();
		Stadium stadium2 = new Stadium();
		Stadium stadium3 = new Stadium();
		Stadium stadium4 = new Stadium();
		Stadium stadium5 = new Stadium();
		Stadium stadium6 = new Stadium();
		Stadium stadium7 = new Stadium();
		Stadium stadium8 = new Stadium();

		ArrayList<Stadium> stadiums = new ArrayList<Stadium>();
		stadiums.add(stadium1);
		stadiums.add(stadium2);
		stadiums.add(stadium3);
		stadiums.add(stadium4);
		stadiums.add(stadium5);
		stadiums.add(stadium6);
		stadiums.add(stadium7);
		stadiums.add(stadium8);

		// generate groupStage
		GroupStage groupStage = new GroupStage();
		groupStage.setGroups(groups);

		// generate tournament
		Tournament tournament = new Tournament();
		tournament.setGroupStage(groupStage);
		tournament.setStadiums(stadiums);
		tournament.setYear(2010);

		return tournament;

	}

	public static List<GroupMatch> manualGroupMatches() {
		// generate teams for group A
		Team teamA1 = new Team("teamA1", null, null, null, null);
		Team teamA2 = new Team("teamA2", null, null, null, null);
		Team teamA3 = new Team("teamA3", null, null, null, null);
		Team teamA4 = new Team("teamA4", null, null, null, null);

		// generate groupmatches for team A
		GroupMatch groupMatchA1 = new GroupMatch();
		GroupMatch groupMatchA2 = new GroupMatch();
		GroupMatch groupMatchA3 = new GroupMatch();
		GroupMatch groupMatchA4 = new GroupMatch();
		GroupMatch groupMatchA5 = new GroupMatch();
		GroupMatch groupMatchA6 = new GroupMatch();

		groupMatchA1.setHostTeam(teamA1);
		groupMatchA1.setGuestTeam(teamA2);

		groupMatchA2.setHostTeam(teamA3);
		groupMatchA2.setGuestTeam(teamA1);

		groupMatchA3.setHostTeam(teamA1);
		groupMatchA3.setGuestTeam(teamA4);

		groupMatchA4.setHostTeam(teamA2);
		groupMatchA4.setGuestTeam(teamA3);

		groupMatchA5.setHostTeam(teamA4);
		groupMatchA5.setGuestTeam(teamA2);

		groupMatchA6.setHostTeam(teamA3);
		groupMatchA6.setGuestTeam(teamA4);

		// matches of group A in a list
		ArrayList<GroupMatch> matches = new ArrayList<GroupMatch>();
		matches.add(groupMatchA1);
		matches.add(groupMatchA2);
		matches.add(groupMatchA3);
		matches.add(groupMatchA4);
		matches.add(groupMatchA5);
		matches.add(groupMatchA6);

		// generate groupMatches for team B
		GroupMatch groupMatchB1 = new GroupMatch();
		GroupMatch groupMatchB2 = new GroupMatch();
		GroupMatch groupMatchB3 = new GroupMatch();
		GroupMatch groupMatchB4 = new GroupMatch();
		GroupMatch groupMatchB5 = new GroupMatch();
		GroupMatch groupMatchB6 = new GroupMatch();

		matches.add(groupMatchB1);
		matches.add(groupMatchB2);
		matches.add(groupMatchB3);
		matches.add(groupMatchB4);
		matches.add(groupMatchB5);
		matches.add(groupMatchB6);

		// generate groupmatches for team C
		GroupMatch groupMatchC1 = new GroupMatch();
		GroupMatch groupMatchC2 = new GroupMatch();
		GroupMatch groupMatchC3 = new GroupMatch();
		GroupMatch groupMatchC4 = new GroupMatch();
		GroupMatch groupMatchC5 = new GroupMatch();
		GroupMatch groupMatchC6 = new GroupMatch();

		matches.add(groupMatchC1);
		matches.add(groupMatchC2);
		matches.add(groupMatchC3);
		matches.add(groupMatchC4);
		matches.add(groupMatchC5);
		matches.add(groupMatchC6);

		// generate groupmatches for team D
		GroupMatch groupMatchD1 = new GroupMatch();
		GroupMatch groupMatchD2 = new GroupMatch();
		GroupMatch groupMatchD3 = new GroupMatch();
		GroupMatch groupMatchD4 = new GroupMatch();
		GroupMatch groupMatchD5 = new GroupMatch();
		GroupMatch groupMatchD6 = new GroupMatch();

		matches.add(groupMatchD1);
		matches.add(groupMatchD2);
		matches.add(groupMatchD3);
		matches.add(groupMatchD4);
		matches.add(groupMatchD5);
		matches.add(groupMatchD6);

		// generate groupmatches for team E
		GroupMatch groupMatchE1 = new GroupMatch();
		GroupMatch groupMatchE2 = new GroupMatch();
		GroupMatch groupMatchE3 = new GroupMatch();
		GroupMatch groupMatchE4 = new GroupMatch();
		GroupMatch groupMatchE5 = new GroupMatch();
		GroupMatch groupMatchE6 = new GroupMatch();

		matches.add(groupMatchE1);
		matches.add(groupMatchE2);
		matches.add(groupMatchE3);
		matches.add(groupMatchE4);
		matches.add(groupMatchE5);
		matches.add(groupMatchE6);

		// generate groupmatches for team F
		GroupMatch groupMatchF1 = new GroupMatch();
		GroupMatch groupMatchF2 = new GroupMatch();
		GroupMatch groupMatchF3 = new GroupMatch();
		GroupMatch groupMatchF4 = new GroupMatch();
		GroupMatch groupMatchF5 = new GroupMatch();
		GroupMatch groupMatchF6 = new GroupMatch();

		matches.add(groupMatchF1);
		matches.add(groupMatchF2);
		matches.add(groupMatchF3);
		matches.add(groupMatchF4);
		matches.add(groupMatchF5);
		matches.add(groupMatchF6);

		// generate groupmatches for team G
		GroupMatch groupMatchG1 = new GroupMatch();
		GroupMatch groupMatchG2 = new GroupMatch();
		GroupMatch groupMatchG3 = new GroupMatch();
		GroupMatch groupMatchG4 = new GroupMatch();
		GroupMatch groupMatchG5 = new GroupMatch();
		GroupMatch groupMatchG6 = new GroupMatch();

		matches.add(groupMatchG1);
		matches.add(groupMatchG2);
		matches.add(groupMatchG3);
		matches.add(groupMatchG4);
		matches.add(groupMatchG5);
		matches.add(groupMatchG6);

		// generate groupmatches for team H
		GroupMatch groupMatchH1 = new GroupMatch();
		GroupMatch groupMatchH2 = new GroupMatch();
		GroupMatch groupMatchH3 = new GroupMatch();
		GroupMatch groupMatchH4 = new GroupMatch();
		GroupMatch groupMatchH5 = new GroupMatch();
		GroupMatch groupMatchH6 = new GroupMatch();

		List<GroupMatch> matchesH = new ArrayList<GroupMatch>();
		matchesH.add(groupMatchH1);
		matchesH.add(groupMatchH2);
		matchesH.add(groupMatchH3);
		matchesH.add(groupMatchH4);
		matchesH.add(groupMatchH5);
		matchesH.add(groupMatchH6);

		return matches;
	}

	public static KnockoutMatch manualKnockoutMatches() {

		// generate teams
		Team team1 = new Team("team1", null, null, null, null);
		Team team2 = new Team("team2", null, null, null, null);
		Team team3 = new Team("team3", null, null, null, null);
		Team team4 = new Team("team4", null, null, null, null);
		Team team5 = new Team("team5", null, null, null, null);
		Team team6 = new Team("team6", null, null, null, null);
		Team team7 = new Team("team7", null, null, null, null);
		Team team8 = new Team("team8", null, null, null, null);
		Team team9 = new Team("team9", null, null, null, null);
		Team team10 = new Team("team10", null, null, null, null);
		Team team11 = new Team("team11", null, null, null, null);
		Team team12 = new Team("team12", null, null, null, null);
		Team team13 = new Team("team13", null, null, null, null);
		Team team14 = new Team("team14", null, null, null, null);
		Team team15 = new Team("team15", null, null, null, null);
		Team team16 = new Team("team16", null, null, null, null);

		// generate bestOf16
		KnockoutMatch bestOfSixteen1 = new KnockoutMatch();
		bestOfSixteen1.setHostTeam(team1);
		bestOfSixteen1.setGuestTeam(team2);

		KnockoutMatch bestOfSixteen2 = new KnockoutMatch();
		bestOfSixteen2.setHostTeam(team3);
		bestOfSixteen2.setGuestTeam(team4);

		KnockoutMatch bestOfSixteen3 = new KnockoutMatch();
		bestOfSixteen3.setHostTeam(team5);
		bestOfSixteen3.setGuestTeam(team6);

		KnockoutMatch bestOfSixteen4 = new KnockoutMatch();
		bestOfSixteen4.setHostTeam(team7);
		bestOfSixteen4.setGuestTeam(team8);

		KnockoutMatch bestOfSixteen5 = new KnockoutMatch();
		bestOfSixteen5.setHostTeam(team9);
		bestOfSixteen5.setGuestTeam(team10);

		KnockoutMatch bestOfSixteen6 = new KnockoutMatch();
		bestOfSixteen6.setHostTeam(team11);
		bestOfSixteen6.setGuestTeam(team12);

		KnockoutMatch bestOfSixteen7 = new KnockoutMatch();
		bestOfSixteen7.setHostTeam(team13);
		bestOfSixteen7.setGuestTeam(team14);

		KnockoutMatch bestOfSixteen8 = new KnockoutMatch();
		bestOfSixteen8.setHostTeam(team15);
		bestOfSixteen8.setGuestTeam(team16);

		// generate quarterFinale
		KnockoutMatch quarterFinal1 = new KnockoutMatch();
		quarterFinal1.setHostTeam(team1);
		quarterFinal1.setGuestTeam(team3);

		KnockoutMatch quarterFinal2 = new KnockoutMatch();
		quarterFinal2.setHostTeam(team5);
		quarterFinal2.setGuestTeam(team7);

		KnockoutMatch quarterFinal3 = new KnockoutMatch();
		quarterFinal3.setHostTeam(team9);
		quarterFinal3.setGuestTeam(team11);

		KnockoutMatch quarterFinal4 = new KnockoutMatch();
		quarterFinal4.setHostTeam(team13);
		quarterFinal4.setGuestTeam(team15);

		// generate halfFinale
		KnockoutMatch halfFinal1 = new KnockoutMatch();
		halfFinal1.setHostTeam(team1);
		halfFinal1.setGuestTeam(team7);

		KnockoutMatch halfFinal2 = new KnockoutMatch();
		halfFinal2.setHostTeam(team9);
		halfFinal2.setGuestTeam(team15);

		// generate finalMatch
		KnockoutMatch finalMatch = new KnockoutMatch();
		finalMatch.setHostTeam(team1);
		finalMatch.setGuestTeam(team15);

		// halfFinals in list
		List<KnockoutMatch> halfFinals = new ArrayList<KnockoutMatch>();
		halfFinals.add(halfFinal1);
		halfFinals.add(halfFinal2);

		// QuarterFinals in lists
		List<KnockoutMatch> quarterFinals1 = new ArrayList<KnockoutMatch>();
		quarterFinals1.add(quarterFinal1);
		quarterFinals1.add(quarterFinal2);

		List<KnockoutMatch> quarterFinals2 = new ArrayList<KnockoutMatch>();
		quarterFinals2.add(quarterFinal3);
		quarterFinals2.add(quarterFinal4);

		// bestOfSixteen in lists
		List<KnockoutMatch> bestOfSixteens1 = new ArrayList<KnockoutMatch>();
		bestOfSixteens1.add(bestOfSixteen1);
		bestOfSixteens1.add(bestOfSixteen2);

		List<KnockoutMatch> bestOfSixteens2 = new ArrayList<KnockoutMatch>();
		bestOfSixteens2.add(bestOfSixteen3);
		bestOfSixteens2.add(bestOfSixteen4);

		List<KnockoutMatch> bestOfSixteens3 = new ArrayList<KnockoutMatch>();
		bestOfSixteens3.add(bestOfSixteen5);
		bestOfSixteens3.add(bestOfSixteen6);

		List<KnockoutMatch> bestOfSixteens4 = new ArrayList<KnockoutMatch>();
		bestOfSixteens4.add(bestOfSixteen7);
		bestOfSixteens4.add(bestOfSixteen8);

		// set children
		finalMatch.setChilds(halfFinals);

		halfFinal1.setChilds(quarterFinals1);
		halfFinal2.setChilds(quarterFinals2);

		quarterFinal1.setChilds(bestOfSixteens1);
		quarterFinal2.setChilds(bestOfSixteens2);
		quarterFinal3.setChilds(bestOfSixteens3);
		quarterFinal4.setChilds(bestOfSixteens4);

		// set children of bestOfSixteen to null
		bestOfSixteen1.setChilds(null);
		bestOfSixteen2.setChilds(null);
		bestOfSixteen3.setChilds(null);
		bestOfSixteen4.setChilds(null);
		bestOfSixteen5.setChilds(null);
		bestOfSixteen6.setChilds(null);
		bestOfSixteen7.setChilds(null);
		bestOfSixteen8.setChilds(null);

		return finalMatch;
	}

	@Test
	public void testHelper() {
		Tournament tournament = null;
		tournament = manualTournament();
		assertNotNull(tournament);
	}
}
