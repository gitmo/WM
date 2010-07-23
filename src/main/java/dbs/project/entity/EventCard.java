package dbs.project.entity;

import javax.persistence.Entity;

@Entity
public class EventCard extends MatchEvent
{
    private String color;
    
    public EventCard() {}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
