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
import dbs.project.entity.Player;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.exception.NewPlayerHasPlayedBefore;
import dbs.project.exception.NotInSameTeam;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.exception.PlayerDoesNotPlayForTeam;
import dbs.project.exception.TeamLineUpComplete;
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
		TestHelper.matchLineUp(match);
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
	
	
	@Test
	public void testSubstitutePlayerFromSameTeamNotPlaying() {
		TestHelper.matchLineUp(match);
		try {
			MatchService.substitutePlayers(match.getGuestTeam().getPlayers().get(11), match.getGuestTeam().getPlayers().get(12), 23, match);
		} catch (NewPlayerHasPlayedBefore e) {
			fail("wrong exception: " + e.getMessage());
		} catch (PlayerDoesNotPlay e) {
			assert(true);
		} catch (NotInSameTeam e) {
			fail("wrong exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testSubstitutePlayersFromOtherTeamPlaying() {
		TestHelper.matchLineUp(match);
		try {
			MatchService.substitutePlayers(match.getHostTeam().getPlayers().get(5), match.getGuestTeam().getPlayers().get(20), 23, match);
		} catch (NewPlayerHasPlayedBefore e) {
			fail("wrong exception: " + e.getMessage());
		} catch (PlayerDoesNotPlay e) {
			fail("wrong exception: " + e.getMessage());
		} catch (NotInSameTeam e) {
			assert(true);
		}
	}
	
	@Test
	public void testSubstitutePlayerThatPlayedBefore() {
		TestHelper.matchLineUp(match);
		try {
			MatchService.substitutePlayers(match.getGuestTeam().getPlayers().get(6), match.getGuestTeam().getPlayers().get(20), 23, match);
		} catch (NewPlayerHasPlayedBefore e) {
			fail("wrong exception: " + e.getMessage());
		} catch (PlayerDoesNotPlay e) {
			fail("wrong exception: " + e.getMessage());
		} catch (NotInSameTeam e) {
			fail("wrong exception: " + e.getMessage());
		}

		try {
			MatchService.substitutePlayers(match.getGuestTeam().getPlayers().get(5), match.getGuestTeam().getPlayers().get(6), 23, match);
		} catch (NewPlayerHasPlayedBefore e) {
			assert(true);
		} catch (PlayerDoesNotPlay e) {
			fail("wrong exception: " + e.getMessage());
		} catch (NotInSameTeam e) {
			fail("wrong exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testInsertPlayerToMatch() {
		try {
			MatchService.insertPlayerToMatch(match.getGuestTeam().getPlayers().get(0), match);
		} catch (PlayerDoesNotPlayForTeam e) {
			fail("exception : " + e.getMessage());
		} catch (TeamLineUpComplete e) {
			fail("exception : " + e.getMessage());
		}
	}

	@Test
	public void testInsertPlayerToMatchNotInTeam() {
		Player player = new Player("first","last","nick",null,null,12,23);
		try {
			MatchService.insertPlayerToMatch(player, match);
		} catch (PlayerDoesNotPlayForTeam e) {
			assert(true);
		} catch (TeamLineUpComplete e) {
			fail("wrong exception : " + e.getMessage());
		}
	}

	@Test
	public void testInsertPlayerToMatchLineUpComplete() {
		TestHelper.matchLineUp(match);
		try {
			MatchService.insertPlayerToMatch(match.getGuestTeam().getPlayers().get(0), match);
		} catch (PlayerDoesNotPlayForTeam e) {
			fail("exception : " + e.getMessage());
		} catch (TeamLineUpComplete e) {
			assert(true);
		}
	}

	@Test
	public void testGetResult() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertGoal() {
		TestHelper.matchLineUp(match);
		
		GoalEvent goal = new GoalEvent(match, 11, match.getHostTeam().getPlayers().get(5), match.getHostTeam());
		try {
			MatchService.insertGoal(goal, match.getHostTeam().getPlayers().get(5), match);
		} catch (PlayerDoesNotPlay e) {
			fail("exception : "+e.getMessage());
		}
		
	}

	@Test
	public void testInsertGoalPlayerDoesNotPlay() {
		Player player = new Player("f", "l", "n", null, null, 12, 23);
		GoalEvent goal = new GoalEvent(match, 11, player, match.getHostTeam());
		try {
			MatchService.insertGoal(goal, match.getHostTeam().getPlayers().get(5), match);
		} catch (PlayerDoesNotPlay e) {
			fail("exception : "+e.getMessage());
		}
		
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
