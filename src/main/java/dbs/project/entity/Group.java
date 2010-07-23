package dbs.project.entity;
import java.util.List;

public class Group
{
    String name;
    List<Team> teams;
    List<GroupMatch> matches;
    
    public Group() {}

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
