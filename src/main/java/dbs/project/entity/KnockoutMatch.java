package dbs.project.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class KnockoutMatch extends Match {
	@OneToMany
	@Cascade(CascadeType.ALL)
	@Column(nullable = true)
	protected List<KnockoutMatch> childs;

	public KnockoutMatch() {
		super();
	}

	public KnockoutMatch(String name) {
		super(name);
	}

	public List<KnockoutMatch> getChildren() {
		if (childs == null)
			return new LinkedList<KnockoutMatch>();

		return childs;
	}

	public void setChilds(List<KnockoutMatch> childs) {
		this.childs = childs;
	}

	@Override
	public String toString() {
		if (this.getGuestTeam() == null || this.getHostTeam() == null)
			return this.getName();
		else
			return this.getHostTeam().getName() + " vs "
					+ this.getGuestTeam().getName();
	}
}
