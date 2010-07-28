package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class GroupMatch extends Match {

	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	TournamentGroup group;
	
	public GroupMatch() {
		super();
	}

	public TournamentGroup getGroup() {
		return group;
	}

	public void setGroup(TournamentGroup group) {
		this.group = group;
	}

	public String toString() {
		return super.toString();
	}
}
