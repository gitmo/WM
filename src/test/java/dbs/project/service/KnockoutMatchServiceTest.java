package dbs.project.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.KnockoutMatch;
import dbs.project.helper.TestHelper;

public class KnockoutMatchServiceTest {

	KnockoutMatch finalMatch;

	@Before
	public void setUp() throws Exception {
		finalMatch = TestHelper.manualKnockoutMatches();
	}

	@After
	public void tearDown() throws Exception {
		finalMatch = null;
	}

	@Test
	public void testGetAsTreeModel() {
		TreeModel tModel = KnockoutMatchService.getAsTreeModel(finalMatch);
		// check final round
		assertEquals(finalMatch, tModel.getRoot());

		// check halfFinals
		assertEquals(finalMatch.getChilds().get(0), tModel.getChild(tModel
				.getRoot(), 0));
		assertEquals(finalMatch.getChilds().get(1), tModel.getChild(tModel
				.getRoot(), 1));

		// checkQuarterFinals
		assertEquals(finalMatch.getChilds().get(0).getChilds().get(0), tModel
				.getChild(tModel.getChild(tModel.getRoot(), 0), 0));
		assertEquals(finalMatch.getChilds().get(0).getChilds().get(1), tModel
				.getChild(tModel.getChild(tModel.getRoot(), 0), 1));
		assertEquals(finalMatch.getChilds().get(1).getChilds().get(0), tModel
				.getChild(tModel.getChild(tModel.getRoot(), 1), 0));
		assertEquals(finalMatch.getChilds().get(1).getChilds().get(1), tModel
				.getChild(tModel.getChild(tModel.getRoot(), 1), 1));

		// check bestOfSixteen
		assertEquals(finalMatch.getChilds().get(0).getChilds().get(0)
				.getChilds().get(0), tModel.getChild(tModel.getChild(tModel
				.getChild(tModel.getRoot(), 0), 0), 0));
		assertEquals(finalMatch.getChilds().get(0).getChilds().get(1)
				.getChilds().get(0), tModel.getChild(tModel.getChild(tModel
				.getChild(tModel.getRoot(), 0), 1), 0));
		assertEquals(finalMatch.getChilds().get(1).getChilds().get(0)
				.getChilds().get(0), tModel.getChild(tModel.getChild(tModel
				.getChild(tModel.getRoot(), 1), 0), 0));
		assertEquals(finalMatch.getChilds().get(1).getChilds().get(1)
				.getChilds().get(0), tModel.getChild(tModel.getChild(tModel
				.getChild(tModel.getRoot(), 1), 1), 0));
		assertEquals(finalMatch.getChilds().get(0).getChilds().get(0)
				.getChilds().get(1), tModel.getChild(tModel.getChild(tModel
				.getChild(tModel.getRoot(), 0), 0), 1));
		assertEquals(finalMatch.getChilds().get(0).getChilds().get(1)
				.getChilds().get(1), tModel.getChild(tModel.getChild(tModel
				.getChild(tModel.getRoot(), 0), 1), 1));
		assertEquals(finalMatch.getChilds().get(1).getChilds().get(0)
				.getChilds().get(1), tModel.getChild(tModel.getChild(tModel
				.getChild(tModel.getRoot(), 1), 0), 1));
		assertEquals(finalMatch.getChilds().get(1).getChilds().get(1)
				.getChilds().get(1), tModel.getChild(tModel.getChild(tModel
				.getChild(tModel.getRoot(), 1), 1), 1));

		// check number of children of finalMatch
		assertEquals(2, tModel.getChildCount(tModel.getRoot()));

		// check number of children of halfFinals
		assertEquals(2, tModel.getChildCount(tModel.getChild(tModel.getRoot(),
				0)));
		assertEquals(2, tModel.getChildCount(tModel.getChild(tModel.getRoot(),
				1)));

		// check number of children of quarterFinals
		assertEquals(2, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getRoot(), 0), 0)));
		assertEquals(2, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getRoot(), 0), 1)));
		assertEquals(2, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getRoot(), 1), 0)));
		assertEquals(2, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getRoot(), 1), 1)));

		// check number of children of bestOfSixteen
		assertEquals(0, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getChild(tModel.getRoot(), 0), 0), 0)));
		assertEquals(0, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getChild(tModel.getRoot(), 0), 0), 1)));
		assertEquals(0, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getChild(tModel.getRoot(), 0), 1), 0)));
		assertEquals(0, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getChild(tModel.getRoot(), 0), 1), 1)));
		assertEquals(0, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getChild(tModel.getRoot(), 1), 0), 0)));
		assertEquals(0, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getChild(tModel.getRoot(), 1), 0), 1)));
		assertEquals(0, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getChild(tModel.getRoot(), 1), 1), 0)));
		assertEquals(0, tModel.getChildCount(tModel.getChild(tModel.getChild(
				tModel.getChild(tModel.getRoot(), 1), 1), 1)));

	}

	@Test
	public void testGetAllMatches() {
		List<KnockoutMatch> matches = KnockoutMatchService
				.getAllMatches(finalMatch);

		List<KnockoutMatch> manMatches = new ArrayList<KnockoutMatch>();
		// add finalMatch
		manMatches.add(finalMatch);

		// add halfFinals
		manMatches.addAll(finalMatch.getChilds());

		// add quarterFinals
		manMatches.addAll(finalMatch.getChilds().get(0).getChilds());
		manMatches.addAll(finalMatch.getChilds().get(1).getChilds());

		// add bestOfSixteen
		manMatches.addAll(finalMatch.getChilds().get(0).getChilds().get(0)
				.getChilds());
		manMatches.addAll(finalMatch.getChilds().get(0).getChilds().get(1)
				.getChilds());
		manMatches.addAll(finalMatch.getChilds().get(1).getChilds().get(0)
				.getChilds());
		manMatches.addAll(finalMatch.getChilds().get(1).getChilds().get(1)
				.getChilds());

		assert (matches.containsAll(manMatches));

	}

}