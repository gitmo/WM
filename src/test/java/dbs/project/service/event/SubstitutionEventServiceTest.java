package dbs.project.service.event;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.util.Substitution;
import dbs.project.util.Tuple;

public class SubstitutionEventServiceTest {
	Match match;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSubstituedPlayersByTeam() {
		List<Substitution> players = SubstitutionEventService.getSubstituedPlayersByTeam(match.getHostTeam(), match);
		assertEquals(3, players.size());
	}

}
