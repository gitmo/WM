package dbs.project.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.table.TableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.Tournament;
import dbs.project.helper.TestHelper;

public class GroupStageServiceTest {

	Tournament tournament;
	
	@Before
	public void setUp() throws Exception {
		tournament = TestHelper.manualTournament();
	}

	@After
	public void tearDown() throws Exception {
		tournament = null;
	}

	@Test
	public void testGetTableModel() {
		TableModel tModel = null;
		tModel = GroupStageService.getTableModel(tournament);
		
		assertNotNull(tModel);
		assertEquals(4, tModel.getColumnCount());
		assertEquals("Team", tModel.getColumnName(0));
		assertEquals("Spiele", tModel.getColumnName(1));
		assertEquals("Punkte", tModel.getColumnName(2));
		assertEquals("Tordifferenz", tModel.getColumnName(3));
		assertEquals(4, tModel.getRowCount());
		
	}

	@Test
	public void testGetAllMatches() {
		List<GroupMatch> groupMatches = GroupStageService.getAllMatches(tournament.getGroupStage());
		
		List<GroupMatch> manualGroupMatchs = TestHelper.manualGroupMatches();
		
		groupMatches.containsAll(manualGroupMatchs);
		
	}

}
