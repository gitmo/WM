package dbs.project.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.stage.KnockoutStage;

@Entity
public class Tournament
{
	@Id
    private String name;
    private Integer year;
    
    @OneToMany
	@Cascade(CascadeType.ALL)
	private List<Country> hostCountries;
    
    @ManyToOne
    @Cascade(CascadeType.ALL)
	private KnockoutMatch finalMatch;
    
    @Transient
    private KnockoutStage knockoutStage;
    
    @ManyToOne
	@Cascade(CascadeType.ALL)
	private GroupStage groupStage;
    
    @OneToMany
	@Cascade(CascadeType.ALL)
	private List<Stadium> stadiums;
    
    public Tournament() {
    	hostCountries = new LinkedList<Country>();
    	groupStage = new GroupStage();
    	stadiums = new LinkedList<Stadium>();
    }

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
		if(knockoutStage == null)
			knockoutStage = new KnockoutStage(finalMatch);
		return knockoutStage;
	}

	public void setKnockoutStage(KnockoutStage knockoutPhase) {
		this.knockoutStage = knockoutPhase;
		this.finalMatch = knockoutPhase.getFinalMatch();
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

	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("Name:\t" + getName() + "\n\n");
//		
//		sb.append("Host:\t");
//		for(Country country : getHostCountries())
//			sb.append(country.getName());
//		sb.append("\n\n");
//		
//		sb.append("GroupStage: ");
//		sb.append("\n\n");
//		sb.append(getGroupPhase());
//		
//		return sb.toString();
		return getName();
	}
}
