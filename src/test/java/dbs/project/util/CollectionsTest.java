package dbs.project.util;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.util.collections.Filter;

public class CollectionsTest {
	List<Integer> list;

	Filter<Integer> filterEven = new Filter<Integer>() {
		public boolean apply(Integer i) {
			return i % 2 == 0 ? true : false;
		}
	};

	@Before
	public void setUp() throws Exception {
		list = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++)
			list.add(i);
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testFilter() {
		List<Integer> newList = Collections.filter(list, filterEven);
		assertEquals(5, newList.size());
	}

	@Test
	public void testFilterAndChangeType() {
		List<String> newList = new LinkedList<String>();
		Collections.filterAndChangeType(list, filterEven, newList);
		Integer j = 0;
		for (int i = 0; i < 5; i++) {
			assertEquals(j, newList.get(i));
			j += 2;
		}
	}

}
