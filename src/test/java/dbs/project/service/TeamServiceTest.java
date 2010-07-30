package dbs.project.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.dao.MatchDao;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.helper.TestHelper;
import dbs.project.util.MatchMinute;

public class TeamServiceTest {
	Team team = null;
	Match match = null;

	@Before
	public void setUp() throws Exception {
		match = TestHelper.match();
		MatchDao.save(match);
		team = match.getHostTeam();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetPlayingPlayersForTeam() {
		int i = 1;
		for(Player player : TeamService.getPlayingPlayersInAMatchForTeam(match, team, new MatchMinute(30)))
			assertEquals("Player "+i, player.getName());
	}

	@Test
	public void testGetPlayersOnTheBench() {
		int i = 12;
		for(Player player : TeamService.getPlayersOnTheBench(match, team,new MatchMinute(30)))
			assertEquals("Player "+i, player.getName());
	}

}
