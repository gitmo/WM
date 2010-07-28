package dbs.project.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbs.project.entity.event.player.CardEvent;

public class EventCardTest {

	CardEvent eventCard;

	@Before
	public void setUp() throws Exception {
		eventCard = new CardEvent();
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
		eventCard = new CardEvent(30, player, "red");
		assertNotNull(eventCard);
		assertEquals(player, eventCard.getInvolvedPlayer());
		assertEquals((Integer) 30, eventCard.getMinute().getFirst());
		assertEquals((Integer) 0, eventCard.getMinute().getSecond());
		assertEquals("red", eventCard.getColor());
	}

	@Test
	public void testEventCardPlayerIntIntString() {
		Player player = new Player();
		eventCard = new CardEvent(45, 3, player, "yellow");
		assertNotNull(eventCard);
		assertEquals(player, eventCard.getInvolvedPlayer());
		assertEquals((Integer) 45, eventCard.getMinute().getFirst());
		assertEquals((Integer) 3, eventCard.getMinute().getSecond());
		assertEquals("yellow", eventCard.getColor());
	}

	@Test
	public void testColor() {
		eventCard.setColor("red");
		assertEquals("red", eventCard.getColor());
	}

}
