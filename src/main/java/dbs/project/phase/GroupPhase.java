package dbs.project.phase;
import java.util.List;

import dbs.project.entity.Group;

public class GroupPhase
{
	private List<Group> groups;

    public GroupPhase() {}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
