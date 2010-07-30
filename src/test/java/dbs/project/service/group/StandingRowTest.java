package dbs.project.service.group;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.swing.table.TableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.Team;

public class StandingRowTest {
	List<Team> teams;
	List<GroupMatch> matches;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRows() {
		List<StandingRow> standings = StandingRow.getRows(this.teams,
				this.matches);
		assertEquals(4, standings.size());
	}

	@Test
	public void testGetModel() {
		TableModel model = StandingRow.getModel(this.matches.get(0)
				.getTournament().getGroupStage().getGroups().get(0));
		assertEquals(4, model.getRowCount());
	}

}
