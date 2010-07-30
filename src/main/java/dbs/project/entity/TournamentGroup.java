package dbs.project.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class TournamentGroup {
	@Id
	@GeneratedValue
	protected long groupId;

	protected String name;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected List<Team> teams;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected List<GroupMatch> matches;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected Tournament tournament;

	public TournamentGroup() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Team> getTeams() {
		if (this.teams == null)
			return new LinkedList<Team>();

		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<GroupMatch> getMatches() {
		if (this.matches == null)
			return new LinkedList<GroupMatch>();

		return this.matches;
	}

	public void setMatches(List<GroupMatch> matches) {
		this.matches = matches;
	}

	public Tournament getTournament() {
		return this.tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Groupname: " + getName() + "\n");

		sb.append("Teams: ");
		for (Team team : getTeams())
			sb.append(" " + team);
		sb.append("\n");

		sb.append("Matches:\n");
		for (GroupMatch match : getMatches())
			sb.append(match + "\n");

		return sb.toString();
	}
}
