package dbs.project.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventCardTest {

	EventCard eventCard;
	@Before
	public void setUp() throws Exception {
		eventCard = new EventCard();
	}

	@After
	public void tearDown() throws Exception {
		eventCard = null;
	}

	@Test
	public void testEventCard() {
		assertNotNull(eventCard);
	}

	@Test
	public void testEventCardPlayerIntString() {
		Player player = new Player();
		eventCard = new EventCard(player, 30, "red");
		assertNotNull(eventCard);
		assertEquals(player, eventCard.getInvolvedPlayer());
		assertEquals((Integer)30, eventCard.getMinute());
		assertEquals((Integer)0, eventCard.getAddTime());
		assertEquals("red", eventCard.getColor());
	}

	@Test
	public void testEventCardPlayerIntIntString() {
		Player player = new Player();
		eventCard = new EventCard(player, 45, 3, "yellow");
		assertNotNull(eventCard);
		assertEquals(player, eventCard.getInvolvedPlayer());
		assertEquals((Integer)45, eventCard.getMinute());
		assertEquals((Integer)3, eventCard.getAddTime());
		assertEquals("yellow", eventCard.getColor());
	}

	@Test
	public void testColor() {
		eventCard.setColor("red");
		assertEquals("red", eventCard.getColor());
	}

}
