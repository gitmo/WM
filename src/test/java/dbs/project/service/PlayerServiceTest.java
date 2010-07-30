package dbs.project.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.exception.NoMatchWhistleEvent;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.util.MatchMinute;
import dbs.project.util.Tuple;

public class PlayerServiceTest {
	Match match = null;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlayerOnField() {
		Player player = match.getHostTeam().getPlayers().get(0);
		Tuple<MatchMinute, MatchMinute> inOut = null;
		try {
			inOut = PlayerService.getPlayingTimeOfAPlayer(player, match);
		} catch (PlayerDoesNotPlay e) {
			fail("player does not play");
		} catch (NoMatchWhistleEvent e) {
			fail("no match whistle event");
		}
		assertEquals(new Integer(0), inOut.getFirst().getFirst());
		assertEquals(new Integer(90), inOut.getSecond().getFirst());
	}

	@Test
	public void testPlayerHasPlayedWithPlayedPlayer() {
		Player player = match.getHostTeam().getPlayers().get(0);
		assertTrue(PlayerService.playerHasPlayed(player, match));
	}
	
	@Test
	public void testPlayerHasPlayedWithPlayerOnBench() {
		List<Player> players = match.getHostTeam().getPlayers();
		Player player = players.get(players.size());
		assertFalse(PlayerService.playerHasPlayed(player, match));
	}

}
