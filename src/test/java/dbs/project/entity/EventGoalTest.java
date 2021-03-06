package dbs.project.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.event.player.GoalEvent;

public class EventGoalTest {

	GoalEvent eventGoal;

	@Before
	public void setUp() throws Exception {
		eventGoal = new GoalEvent();
	}

	@After
	public void tearDown() throws Exception {
		eventGoal = null;
	}

	@Test
	public void testScorringTeam() {
		Team team = new Team();
		eventGoal.setScorringTeam(team);
		assertEquals(team, eventGoal.getScorringTeam());
	}

	@Test
	public void testEventGoal() {
		assertNotNull(eventGoal);
	}

	@Test
	public void testEventGoalPlayerTeamIntInt() {
		Player player = new Player();
		Team team = new Team();
		eventGoal = new GoalEvent(new GroupMatch(), 120, 2, player, team);
		assertNotNull(eventGoal);
		assertEquals(player, eventGoal.getInvolvedPlayer());
		assertEquals(team, eventGoal.getScorringTeam());
		assertEquals((Integer) 120, eventGoal.getMinute().getFirst());
		assertEquals((Integer) 2, eventGoal.getMinute().getSecond());
	}

}
