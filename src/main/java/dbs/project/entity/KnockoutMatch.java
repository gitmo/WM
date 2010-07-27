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
	List<KnockoutMatch> childs = new LinkedList<KnockoutMatch>();

	boolean extraTime = false;
	int addTimeThird = 0;
	int addTimeForth = 0;

	public KnockoutMatch() {
		super();
	}

	public KnockoutMatch(String name) {
		super(name);
	}

	public List<KnockoutMatch> getChilds() {
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

	public boolean getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(boolean extraTime) {
		this.extraTime = extraTime;
	}

	public int getAddTimeThird() {
		return addTimeThird;
	}

	public void setAddTimeThird(int addTimeThird) {
		this.addTimeThird = addTimeThird;
	}

	public int getAddTimeForth() {
		return addTimeForth;
	}

	public void setAddTimeForth(int addTimeForth) {
		this.addTimeForth = addTimeForth;
	}
}
