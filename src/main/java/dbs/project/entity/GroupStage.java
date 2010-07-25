package dbs.project.entity;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.GroupLayout.Group;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class GroupStage
{
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<TournamentGroup> groups;

	public GroupStage() {}

	public List<TournamentGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<TournamentGroup> groups) {
		this.groups = groups;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Groups:\n");
		
		for(TournamentGroup group : getGroups())
			sb.append(group + "\n");
		
		return sb.toString();
	}
}
