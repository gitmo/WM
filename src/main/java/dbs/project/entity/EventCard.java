package dbs.project.entity;

import javax.persistence.Entity;

@Entity
public class EventCard extends MatchEvent
{
    private String color;
    
    public EventCard() {}

	public EventCard(Player p1, int i, String color) {
		super(p1,i);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
