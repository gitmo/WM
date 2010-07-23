package dbs.project.entity;
import java.util.List;

public class Tournament
{
    private String name;
    private Integer year;
    private List<Country> hostCountries;
    private KnockoutPhase knockoutPhase;
    private GroupPhase groupPhase;
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
