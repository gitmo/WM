package dbs.project.phase;
import java.util.List;

import dbs.project.entity.TournamentGroup;

public class GroupStage
{
	private List<TournamentGroup> groups;

  public GroupStage() {}

	public List<TournamentGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<TournamentGroup> groups) {
		this.groups = groups;
	}
}
