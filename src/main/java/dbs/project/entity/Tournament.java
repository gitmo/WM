package dbs.project.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Tournament {
	@Id
	protected int year;

	protected String name;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected List<Country> hostCountries;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected KnockoutMatch finalMatch;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected KnockoutMatch matchForThirdPlace;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected GroupStage groupStage;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected List<Stadium> stadiums;

	public Tournament() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Country> getHostCountries() {
		if (this.hostCountries == null)
			return new LinkedList<Country>();

		return this.hostCountries;
	}

	public void setHostCountries(List<Country> hostCountries) {
		this.hostCountries = hostCountries;
	}

	public GroupStage getGroupStage() {
		return this.groupStage;
	}

	public void setGroupStage(GroupStage groupStage) {
		this.groupStage = groupStage;
	}

	public List<Stadium> getStadiums() {
		if (this.stadiums == null)
			return new LinkedList<Stadium>();

		return this.stadiums;
	}

	public void setStadiums(List<Stadium> stadiums) {
		this.stadiums = stadiums;
	}

	public KnockoutMatch getFinalMatch() {
		return this.finalMatch;
	}

	public void setFinalMatch(KnockoutMatch finalMatch) {
		this.finalMatch = finalMatch;
	}

	public void setMatchForThirdPlace(KnockoutMatch matchForThirdPlace) {
		this.matchForThirdPlace = matchForThirdPlace;
	}

	public KnockoutMatch getMatchForThirdPlace() {
		return this.matchForThirdPlace;
	}

	@Override
	public String toString() {
		return getName() + " " + getYear();
	}
}
