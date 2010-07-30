package dbs.project.entity.event.player;

import javax.persistence.Entity;

import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.event.PlayerEvent;

@Entity
public class CardEvent extends PlayerEvent {
	protected String color;

	public CardEvent() {
		super();
	}

	public CardEvent(Match match, int minute, Player involvedPlayer,
			String color) {
		super(match, minute, involvedPlayer);
		setColor(color);
	}

	public CardEvent(Match match, int minute, int additionalTime,
			Player involvedPlayer, String color) {
		super(match, minute, additionalTime, involvedPlayer);
		setColor(color);
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
