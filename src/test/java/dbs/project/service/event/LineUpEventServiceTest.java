package dbs.project.service.event;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.Player;
import dbs.project.entity.Team;

public class LineUpEventServiceTest {
	Team team;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPlayersByTeam() {
		List<Player> players = LineUpEventService.getPlayersByTeam(team);
		assertEquals(23, players.size());
		int i=1;
		for(Player player : players)
			assertEquals("Player "+(i++), player.getName());
	}

}
