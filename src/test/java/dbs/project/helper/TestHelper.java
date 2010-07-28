package dbs.project.helper;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.GroupStage;
import dbs.project.entity.Stadium;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;

public class TestHelper {

	public static Tournament manualTournament(){
		//generate teams for group A
		Team teamA1 = new Team("teamA1",null,null,null,null);
		Team teamA2 = new Team("teamA2",null,null,null,null);
		Team teamA3 = new Team("teamA3",null,null,null,null);
		Team teamA4 = new Team("teamA4",null,null,null,null);
		
		ArrayList<Team> teamsA = new ArrayList<Team>();
		teamsA.add(teamA1);
		teamsA.add(teamA2);
		teamsA.add(teamA3);
		teamsA.add(teamA4);
		
		//generate teams for group B
		Team teamB1 = new Team("teamB1",null,null,null,null);
		Team teamB2 = new Team("teamB2",null,null,null,null);
		Team teamB3 = new Team("teamB3",null,null,null,null);
		Team teamB4 = new Team("teamB4",null,null,null,null);
		
		ArrayList<Team> teamsB = new ArrayList<Team>();
		teamsB.add(teamB1);
		teamsB.add(teamB2);
		teamsB.add(teamB3);
		teamsB.add(teamB4);
		
		//generate teams for group C
		Team teamC1 = new Team("teamC1",null,null,null,null);
		Team teamC2 = new Team("teamC2",null,null,null,null);
		Team teamC3 = new Team("teamC3",null,null,null,null);
		Team teamC4 = new Team("teamC4",null,null,null,null);
		
		ArrayList<Team> teamsC = new ArrayList<Team>();
		teamsC.add(teamC1);
		teamsC.add(teamC2);
		teamsC.add(teamC3);
		teamsC.add(teamC4);
		
		//generate teams for group D
		Team teamD1 = new Team("teamD1",null,null,null,null);
		Team teamD2 = new Team("teamD2",null,null,null,null);
		Team teamD3 = new Team("teamD3",null,null,null,null);
		Team teamD4 = new Team("teamD4",null,null,null,null);
		
		ArrayList<Team> teamsD = new ArrayList<Team>();
		teamsD.add(teamD1);
		teamsD.add(teamD2);
		teamsD.add(teamD3);
		teamsD.add(teamD4);
		
		//generate teams for group E
		Team teamE1 = new Team("teamE1",null,null,null,null);
		Team teamE2 = new Team("teamE2",null,null,null,null);
		Team teamE3 = new Team("teamE3",null,null,null,null);
		Team teamE4 = new Team("teamE4",null,null,null,null);
		
		ArrayList<Team> teamsE = new ArrayList<Team>();
		teamsE.add(teamE1);
		teamsE.add(teamE2);
		teamsE.add(teamE3);
		teamsE.add(teamE4);
		
		//generate teams for group F
		Team teamF1 = new Team("teamF1",null,null,null,null);
		Team teamF2 = new Team("teamF2",null,null,null,null);
		Team teamF3 = new Team("teamF3",null,null,null,null);
		Team teamF4 = new Team("teamF4",null,null,null,null);
		
		ArrayList<Team> teamsF = new ArrayList<Team>();
		teamsF.add(teamF1);
		teamsF.add(teamF2);
		teamsF.add(teamF3);
		teamsF.add(teamF4);
		
		//generate teams for group G
		Team teamG1 = new Team("teamA1",null,null,null,null);
		Team teamG2 = new Team("teamG2",null,null,null,null);
		Team teamG3 = new Team("teamG3",null,null,null,null);
		Team teamG4 = new Team("teamG4",null,null,null,null);
		
		ArrayList<Team> teamsG = new ArrayList<Team>();
		teamsG.add(teamG1);
		teamsG.add(teamG2);
		teamsG.add(teamG3);
		teamsG.add(teamG4);
		
		//generate teams for group H
		Team teamH1 = new Team("teamA1",null,null,null,null);
		Team teamH2 = new Team("teamH2",null,null,null,null);
		Team teamH3 = new Team("teamH3",null,null,null,null);
		Team teamH4 = new Team("teamH4",null,null,null,null);
		
		ArrayList<Team> teamsH = new ArrayList<Team>();
		teamsH.add(teamH1);
		teamsH.add(teamH2);
		teamsH.add(teamH3);
		teamsH.add(teamH4);
		
		//all teams in one list
		ArrayList<Team> teams = new ArrayList<Team>();
		teams.addAll(teamsA);
		teams.addAll(teamsB);
		teams.addAll(teamsC);
		teams.addAll(teamsD);
		teams.addAll(teamsE);
		teams.addAll(teamsF);
		teams.addAll(teamsG);
		teams.addAll(teamsH);
		
		//set teams in Groups
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
		
		//name groups
		groupA.setName("Group A");
		groupB.setName("Group B");
		groupC.setName("Group C");
		groupD.setName("Group D");
		groupE.setName("Group E");
		groupF.setName("Group F");
		groupG.setName("Group G");
		groupH.setName("Group H");
		
		//generate groupmatches for team A
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
		
		
		//matches of group A in a list
		ArrayList<GroupMatch> matchesA = new ArrayList<GroupMatch>();
		matchesA.add(groupMatchA1);
		matchesA.add(groupMatchA2);
		matchesA.add(groupMatchA3);
		matchesA.add(groupMatchA4);
		matchesA.add(groupMatchA5);
		matchesA.add(groupMatchA6);
		
		groupA.setMatches(matchesA);
		
		//generate groupMatches for team B
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
		
		//generate groupmatches for team C
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
		
		//generate groupmatches for team D
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
		
		//generate groupmatches for team E
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
		
		//generate groupmatches for team F
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
		
		//generate groupmatches for team G
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
		
		//generate groupmatches for team H
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
		
		//groups to list
		ArrayList<TournamentGroup> groups = new ArrayList<TournamentGroup>();
		groups.add(groupA);
		groups.add(groupB);
		groups.add(groupC);
		groups.add(groupD);
		groups.add(groupE);
		groups.add(groupF);
		groups.add(groupG);
		groups.add(groupH);
		
		//generate a list of stadiums
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
		
		//generate groupStage
		GroupStage groupStage = new GroupStage();
		groupStage.setGroups(groups);
		
		//generate tournament
		Tournament tournament = new Tournament();
		tournament.setGroupStage(groupStage);
		tournament.setStadiums(stadiums);
		tournament.setYear(2010);
		
		return tournament;
		
	}
	
	public static List<GroupMatch> manualGroupMatches(){
		//generate teams for group A
		Team teamA1 = new Team("teamA1",null,null,null,null);
		Team teamA2 = new Team("teamA2",null,null,null,null);
		Team teamA3 = new Team("teamA3",null,null,null,null);
		Team teamA4 = new Team("teamA4",null,null,null,null);
		
		//generate groupmatches for team A
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
		
		
		//matches of group A in a list
		ArrayList<GroupMatch> matches = new ArrayList<GroupMatch>();
		matches.add(groupMatchA1);
		matches.add(groupMatchA2);
		matches.add(groupMatchA3);
		matches.add(groupMatchA4);
		matches.add(groupMatchA5);
		matches.add(groupMatchA6);
		
		//generate groupMatches for team B
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
		
		//generate groupmatches for team C
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
		
		//generate groupmatches for team D
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

		//generate groupmatches for team E
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
		
		//generate groupmatches for team F
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
		
		//generate groupmatches for team G
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

	
		//generate groupmatches for team H
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
	
	@Test
	public void testHelper() {
		Tournament tournament = null;
		tournament = manualTournament();
		assertNotNull(tournament);
	}
}
