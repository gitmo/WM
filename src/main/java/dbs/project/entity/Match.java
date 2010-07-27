package dbs.project.entity;
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
public abstract class Match
{
	@Id
	@GeneratedValue
	Long id;
	
	String name = "";
	
	@ManyToOne(optional=true)
	@Cascade(CascadeType.ALL)
    Team hostTeam;
	
	@ManyToOne(optional=true)
	@Cascade(CascadeType.ALL)
    Team guestTeam;
	
    @OneToMany
	@Cascade(CascadeType.ALL)
	@Column(nullable=true)
    List<Player> hostLineup;
    
    @OneToMany
	@Cascade(CascadeType.ALL)
	@Column(nullable=true)
    List<Player> guestLineup; 
    
	@ManyToOne
	@Cascade(CascadeType.ALL)
    Stadium stadium;
	
    @OneToMany
	@Cascade(CascadeType.ALL)
    List<MatchEvent> events;
    
    @ManyToOne
    @Cascade(CascadeType.ALL)
    Tournament tournament;

   

	boolean played  = false;
    
    public Match() {
    	hostLineup = new LinkedList<Player>();
    	guestLineup = new LinkedList<Player>();
    	events = new LinkedList<MatchEvent>();
//    	tournament = new Tournament();
    }
    
    public Match(String name) {
    	this.name = name;
    	hostLineup = new LinkedList<Player>();
    	guestLineup = new LinkedList<Player>();
    	events = new LinkedList<MatchEvent>();
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
	
	public void addEvent(MatchEvent event) {
		if(this.events == null)
			this.events = new LinkedList<MatchEvent>();
		
		this.events.add(event);
	}
	
	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}
	
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(getHostTeam() + " vs " + getGuestTeam());
    	
    	return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	 public Tournament getTournament() {
//		return tournament;
//	}
//
//	public void setTournament(Tournament tournament) {
//		this.tournament = tournament;
//	}
}
