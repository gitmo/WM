package dbs.project.entity;

import javax.persistence.Entity;

@Entity
public class EventCard extends MatchEvent {
	private String color;

	public EventCard() {
	}

	public EventCard(Player involevedPlayer, int minute, String color) {
		super(involevedPlayer, minute);
		this.color = color;
	}

	public EventCard(Player involevedPlayer, int minute, int addTime,
			String color) {
		super(involevedPlayer, minute, addTime);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
