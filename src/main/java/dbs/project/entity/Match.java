package dbs.project.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public abstract class Match {
	@Id
	@GeneratedValue
	protected Long id;

	protected String name;

	@ManyToOne(optional = true)
	@Cascade(CascadeType.ALL)
	protected Team hostTeam;

	@ManyToOne(optional = true)
	@Cascade(CascadeType.ALL)
	protected Team guestTeam;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@Column(nullable = true)
	protected List<Player> hostLineup;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@Column(nullable = true)
	protected List<Player> guestLineup;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Stadium stadium;

	@OneToMany
	@Cascade(CascadeType.ALL)
	protected List<MatchEvent> events;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Tournament tournament;

	protected Date date;

	protected boolean played = false;

	public Match() {
	}

	public Match(String name) {
		this.name = name;
	}

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
		if (hostLineup == null)
			return new LinkedList<Player>();

		return hostLineup;
	}

	public void setHostLineup(List<Player> hostLineup) {
		this.hostLineup = hostLineup;
	}

	public List<Player> getGuestLineup() {
		if (guestLineup == null)
			return new LinkedList<Player>();
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
		if (events == null)
			return new LinkedList<MatchEvent>();

		return events;
	}

	public void setEvents(List<MatchEvent> events) {
		this.events = events;
	}

	public void addEvent(MatchEvent event) {
		if (this.events == null)
			this.events = new LinkedList<MatchEvent>();

		this.events.add(event);
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(getHostTeam());
		sb.append(" vs ");
		sb.append(getGuestTeam());
		sb.append(" am " + getDate());
		sb.append(" in " + getStadium());

		return sb.toString();
	}

}
