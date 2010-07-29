package dbs.project.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.collections.filter.FilterSubstitutionEvent;
import dbs.project.dao.MatchDao;
import dbs.project.entity.KnockoutMatch;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.helper.TestHelper;
import dbs.project.util.Collections;

public class MatchServiceTest {

	KnockoutMatch match;
	
	@Before
	public void setUp() throws Exception {
		match = TestHelper.match();
		MatchDao.save(match);
	}
	
	@After
	public void tearDown() throws Exception {
		MatchDao.delete(match);
	}

	@Test
	public void testSubstitutePlayerFromSameTeamPlaying() {
		try {
			MatchService.substitutePlayers(match.getGuestTeam().getPlayers().get(0), match.getGuestTeam().getPlayers().get(11), 23, match);
		} catch (Exception e) {
			fail(e.getClass().toString());
		}
		
		List<SubstitutionEvent> substitutions = new ArrayList<SubstitutionEvent>();
		Collections.filterAndChangeType(match.getEvents(), new FilterSubstitutionEvent(), substitutions);

		assertEquals(1, substitutions.size());
		assertEquals(match.getGuestTeam().getPlayers().get(0),substitutions.get(0).getInvolvedPlayer());
		assertEquals(match.getGuestTeam().getPlayers().get(11),substitutions.get(0).getNewPlayer());
		assertEquals((Integer)23, substitutions.get(0).getMinute().getFirst());
		assertEquals((Integer)0, substitutions.get(0).getMinute().getSecond());
		
		
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
