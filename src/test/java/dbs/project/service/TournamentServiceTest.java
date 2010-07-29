package dbs.project.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.ListModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.exception.TournamentNotOver;

public class TournamentServiceTest {

	Tournament completedTournament = null, uncompletedTournament = null;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWeAreTheChampionsWithCompletedTournament() {
		List<Team> champions = null;
		try {
			champions = TournamentService.weAreTheChampions(completedTournament);
		} catch (TournamentNotOver e) {
			fail("tournament not over");
		}
		assertEquals(3, champions.size());
	}
	
	@Test
	public void testWeAreTheChampionsWithUncompletedTournament() {
		try {
			 TournamentService.weAreTheChampions(uncompletedTournament);
		} catch (TournamentNotOver e) {}
		
		fail("tournament not over");
		
	}

	@Test
	public void testGetListModel() {
		ListModel listModel = TournamentService.getListModel();
		assertEquals(2, listModel.getSize());
	}

	@Test
	public void testGetTopscorers() {
		assertEquals("Player 1", TournamentService.getTopscorers(completedTournament));
	}

	@Test
	public void testGetAllMatchesTournament() {
		int size = TournamentService.getAllMatches(completedTournament).size();
		assertEquals(8*6*8+4+2+1+1, size);
	}

	@Test
	public void testGetPlayerWithMostCards() {
		assertEquals("Player 2", TournamentService.getTopscorers(completedTournament));
	}

	@Test
	public void testGetAllMatchesKnockoutMatch() {
		int size = TournamentService.getAllMatches(completedTournament.getFinalMatch()).size();
		assertEquals(8+4+2+1, size);
	}

}
