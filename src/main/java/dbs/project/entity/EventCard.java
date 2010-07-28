package dbs.project.entity;

import javax.persistence.Entity;

@Entity
public class EventCard extends MatchEvent {
	protected String color;

	public EventCard() {
		super();
	}

	public EventCard(Player involvedPlayer, int minute, String color) {
		super(involvedPlayer, minute);
		setColor(color);
	}

	public EventCard(Player involvedPlayer, String color, int minute,
			int additionalTime) {
		super(involvedPlayer, minute, additionalTime);
		setColor(color);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
