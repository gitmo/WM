package dbs.project.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class TournamentGroup {
	@Id
	@GeneratedValue
	long groupId;

	String name;

	@OneToMany
	@Cascade(CascadeType.ALL)
	List<Team> teams;

	@OneToMany
	@Cascade(CascadeType.ALL)
	List<GroupMatch> matches;

	public TournamentGroup() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<GroupMatch> getMatches() {
		return matches;
	}

	public void setMatches(List<GroupMatch> matches) {
		this.matches = matches;
	}

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
