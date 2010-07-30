package dbs.project.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class KnockoutMatch extends Match {
	@ManyToMany
	@Cascade(CascadeType.ALL)
	@Column(nullable = true)
	protected List<KnockoutMatch> childs = null;

	public KnockoutMatch() {
		super();
	}

	public KnockoutMatch(String name) {
		super(name);
	}

	public List<KnockoutMatch> getChildren() {
		if (this.childs == null)
			return new LinkedList<KnockoutMatch>();

		return this.childs;
	}

	public void setChildren(List<KnockoutMatch> childs) {
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
