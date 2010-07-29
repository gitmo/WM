package dbs.project.service;

import static org.junit.Assert.*;

import dbs.project.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sound.sampled.LineEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.collections.filter.FilterLineUpEvent;
import dbs.project.collections.filter.FilterSubstitutionEvent;
import dbs.project.entity.GroupMatch;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.LineUpEvent;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.exception.NewPlayerHasPlayedBefore;
import dbs.project.exception.NotInSameTeam;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.util.Collections;

public class MatchServiceTest {

	Match match;
	Player team1Player1;
	Player team1Player2;
	Player team1Player3;
	Player team2Player1;
	Player team2Player2;
	Player team2Player3;
	
	Team team1;
	Team team2;
	
	@Before
	public void setUp() throws Exception {
		team1Player1 = new Player("team1", "player1", "t1p1", null, null, 11, 101);
		team1Player2 = new Player("team1", "player2", "t1p2", null, null, 12, 102);
		team1Player3 = new Player("team1", "player3", "t1p3", null, null, 13, 102);
		List<Player> team1players = new ArrayList<Player>();
		team1players.add(team1Player1);
		team1players.add(team1Player2);
		team1players.add(team1Player3);
		
		team2Player1 = new Player("team2", "player1", "t2p1", null, null, 21, 201);
		team2Player2 = new Player("team2", "player2", "t2p2", null, null, 22, 202);
		team2Player3 = new Player("team2", "player3", "t2p3", null, null, 23, 203);
		List<Player> team2players = new ArrayList<Player>();
		team2players.add(team2Player1);
		team2players.add(team2Player2);
		team2players.add(team2Player3);
		
		
		team1 = new Team("team1", team1players, null, null, null);
		team2 = new Team("team2", team2players, null, null, null);
		
		match = new GroupMatch();
		match.setHostTeam(team1);
		match.setGuestTeam(team2);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSubstitutePlayerFromSameTeamPlaying() {
		LineUpEvent event = new LineUpEvent(match,team1Player1,team1);
		
		match.addEvent(event);
		
		try {
			MatchService.substitutePlayers(team1Player1, team1Player2, 23, match);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		List<SubstitutionEvent> substitutions = new ArrayList<SubstitutionEvent>();
		Collections.filterAndChangeType(match.getEvents(), new FilterSubstitutionEvent(), substitutions);

		assertEquals(1, substitutions.size());
		assertEquals(team1Player1,substitutions.get(0).getInvolvedPlayer());
		assertEquals(team1Player2,substitutions.get(0).getNewPlayer());
		assertEquals(team1Player2,substitutions.get(0).getNewPlayer());
		assertEquals(23, substitutions.get(0).getMinute());
		
	}
	
//	
//	@Test
//	public void testSubstitutePlayerFromSameTeamNotPlaying() {
//		MatchService.substitutePlayers(team1Player1, team1Player2, 23, match);
//	}
//	
//	@Test
//	public void testSubstitutePlayersFromOtherTeamPlaying() {
//		MatchService.substitutePlayers(team1Player1, team1Player2, 23, match);
//	}
//	
//	@Test
//	public void testSubstitutePlayersFromOtherTeamNotPlaying() {
//		MatchService.substitutePlayers(team1Player1, team1Player2, 23, match);
//	}
	
	@Test
	public void testInsertPlayerToMatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetResult() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertGoal() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPointsByTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGoalsByTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWinner() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLooser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFinalWhistleTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHostLineup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGuestLineup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLineup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLineupForTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLineup() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubstitutedPlayersByTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFinalWhistle() {
		fail("Not yet implemented");
	}

}
