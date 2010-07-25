package dbs.project.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import dbs.project.stage.GroupStage;
import dbs.project.stage.KnockoutStage;

@Entity
public class Tournament
{
	@Id
    private String name;
    private Integer year;
    @OneToMany
	private List<Country> hostCountries;
    @Transient
	private KnockoutStage knockoutStage;
    @Transient
	private GroupStage groupStage;
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

	public KnockoutStage getKnockoutPhase() {
		return knockoutStage;
	}

	public void setKnockoutPhase(KnockoutStage knockoutPhase) {
		this.knockoutStage = knockoutPhase;
	}

	public GroupStage getGroupPhase() {
		return groupStage;
	}

	public void setGroupPhase(GroupStage groupStage) {
		this.groupStage= groupStage;
	}

	public List<Stadium> getStadiums() {
		return stadiums;
	}

	public void setStadiums(List<Stadium> stadiums) {
		this.stadiums = stadiums;
	}
}
