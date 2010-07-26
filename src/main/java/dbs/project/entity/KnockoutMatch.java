package dbs.project.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class KnockoutMatch extends Match
{
	@OneToMany
	@Cascade(CascadeType.ALL)
	List<KnockoutMatch> childs;
	
    public KnockoutMatch() {}

	public KnockoutMatch(String name) {
		this.setName(name);
	}

	public List<KnockoutMatch> getChilds() {
		return childs;
	}

	public void setChilds(List<KnockoutMatch> childs) {
		this.childs = childs;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
