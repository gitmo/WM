package dbs.project.entity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import dbs.project.phase.GroupPhase;
import dbs.project.phase.KnockoutPhase;

@Entity
public class Tournament
{
	@Id
    private String name;
    private Integer year;
    @OneToMany
	private List<Country> hostCountries;
    @Transient
	private KnockoutPhase knockoutPhase;
    @Transient
	private GroupPhase groupPhase;
    @OneToMany
	private List<Stadium> stadiums;
    
    public Tournament() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Country> getHostCountries() {
		return hostCountries;
	}

	public void setHostCountries(List<Country> hostCountries) {
		this.hostCountries = hostCountries;
	}

	public KnockoutPhase getKnockoutPhase() {
		return knockoutPhase;
	}

	public void setKnockoutPhase(KnockoutPhase knockoutPhase) {
		this.knockoutPhase = knockoutPhase;
	}

	public GroupPhase getGroupPhase() {
		return groupPhase;
	}

	public void setGroupPhase(GroupPhase groupPhase) {
		this.groupPhase = groupPhase;
	}

	public List<Stadium> getStadiums() {
		return stadiums;
	}

	public void setStadiums(List<Stadium> stadiums) {
		this.stadiums = stadiums;
	}
}
