package dbs.project.entity;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public abstract class Match
{
	@Id
	@GeneratedValue
	Long id;
	@ManyToOne
    Team hostTeam;
	@ManyToOne
    Team guestTeam;
    @OneToMany
    List<Player> hostLineup;
    @OneToMany
    List<Player> guestLineup;    
	@ManyToOne
    Stadium stadium;
    @OneToMany
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
