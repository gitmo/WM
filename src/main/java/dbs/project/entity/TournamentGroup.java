package dbs.project.entity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TournamentGroup
{
	@Id
    String name;
	@OneToMany
	List<Team> teams;
	@OneToMany
	List<GroupMatch> matches;
    
    public TournamentGroup() {}

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
}
