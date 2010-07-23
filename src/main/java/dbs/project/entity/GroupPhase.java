package dbs.project.entity;
import java.util.List;

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
