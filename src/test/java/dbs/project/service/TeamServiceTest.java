package dbs.project.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;

public class TeamServiceTest {
	Team team = null;
	Match match = null;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetPlayingPlayersForTeam() {
		int i = 1;
		for(Player player : TeamService.getPlayingPlayersForTeam(match, team))
			assertEquals("Player "+i, player.getName());
	}

	@Test
	public void testGetPlayersOnTheBench() {
		int i = 12;
		for(Player player : TeamService.getPlayersOnTheBench(match, team))
			assertEquals("Player "+i, player.getName());
	}

}
