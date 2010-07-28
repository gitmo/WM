package dbs.project.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
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

	@OneToMany
	@Cascade(CascadeType.ALL)
	protected List<Country> hostCountries;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected KnockoutMatch finalMatch;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected KnockoutMatch matchForThirdPlace;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected GroupStage groupStage;

	@OneToMany
	@Cascade(CascadeType.ALL)
	protected List<Stadium> stadiums;

	public Tournament() {
	}

	public String getName() {
		return name;
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
		if (hostCountries == null)
			return new LinkedList<Country>();

		return hostCountries;
	}

	public void setHostCountries(List<Country> hostCountries) {
		this.hostCountries = hostCountries;
	}

	public GroupStage getGroupStage() {
		return groupStage;
	}

	public void setGroupStage(GroupStage groupStage) {
		this.groupStage = groupStage;
	}

	public List<Stadium> getStadiums() {
		if (stadiums == null)
			return new LinkedList<Stadium>();

		return stadiums;
	}

	public void setStadiums(List<Stadium> stadiums) {
		this.stadiums = stadiums;
	}

	public KnockoutMatch getFinalMatch() {
		return finalMatch;
	}

	public void setFinalMatch(KnockoutMatch finalMatch) {
		this.finalMatch = finalMatch;
	}

	public void setMatchForThirdPlace(KnockoutMatch matchForThirdPlace) {
		this.matchForThirdPlace = matchForThirdPlace;
	}

	public KnockoutMatch getMatchForThirdPlace() {
		return matchForThirdPlace;
	}

	public String toString() {
		return getName() + " " + getYear();
	}
}
