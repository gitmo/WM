package dbs.project.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class GroupStage {
	@Id
	@GeneratedValue
	protected long id;

	@OneToMany
	@Cascade(CascadeType.ALL)
	protected List<TournamentGroup> groups;

	public GroupStage() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<TournamentGroup> getGroups() {
		if (this.groups == null)
			return new LinkedList<TournamentGroup>();

		return this.groups;
	}

	public void setGroups(List<TournamentGroup> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Groups:\n");

		for (TournamentGroup group : getGroups())
			sb.append(group + "\n");

		return sb.toString();
	}
}
