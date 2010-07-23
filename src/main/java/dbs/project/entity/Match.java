package dbs.project.entity;
import java.util.List;

/**
 * Abstract class Match - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Match
{
    Team hostTeam;
    Team guestTeam;
    List<Player> hostLineup;
    List<Player> guestLineup;    
    Stadium stadium;
    List<MatchEvent> events;
    
    public Match() {}

	public Team getHostTeam() {
		return hostTeam;
	}

	public void setHostTeam(Team hostTeam) {
		this.hostTeam = hostTeam;
	}

	public Team getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(Team guestTeam) {
		this.guestTeam = guestTeam;
	}

	public List<Player> getHostLineup() {
		return hostLineup;
	}

	public void setHostLineup(List<Player> hostLineup) {
		this.hostLineup = hostLineup;
	}

	public List<Player> getGuestLineup() {
		return guestLineup;
	}

	public void setGuestLineup(List<Player> guestLineup) {
		this.guestLineup = guestLineup;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public List<MatchEvent> getEvents() {
		return events;
	}

	public void setEvents(List<MatchEvent> events) {
		this.events = events;
	}
}
