package dbs.project.entity.event;

import javax.persistence.Entity;

import dbs.project.entity.MatchEvent;

@Entity
public class MatchEndEvent extends MatchEvent {

	public MatchEndEvent() {
		super();
	}
	
	public MatchEndEvent(int min) {
		super(min);
	}
}
