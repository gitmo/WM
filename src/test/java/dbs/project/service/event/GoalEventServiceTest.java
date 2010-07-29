package dbs.project.service.event;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.Match;
import dbs.project.entity.Team;
import dbs.project.util.Tuple;

public class GoalEventServiceTest {
	Match match;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGoalsForMatchByTeam() {
		Team team = match.getHostTeam();
		Tuple<Integer, Integer> goals = GoalEventService.getGoalsForMatchByTeam(team, match);
		assertEquals(new Integer(1), goals.getFirst());
		assertEquals(new Integer(0), goals.getSecond());
		
	}

	@Test
	public void testGetGoalsByTournament() {
		assertEquals(50, GoalEventService.getGoalsByTournament(match.getTournament()));
	}

}
