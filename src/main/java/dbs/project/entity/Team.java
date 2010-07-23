package dbs.project.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Team {
	@Id
	@GeneratedValue
	Long id;
    String name;
    @OneToMany
	List<Player> players;
    /*TODO embedded*/
    @Transient
    Map<Integer, Player> trikotNumbers;
    @OneToMany
	List<Advisor> advisors;
    @ManyToOne
	Country country;

    public Team() {}
    
    public Team(String name, List<Player> players,
            Map<Integer, Player> trikotNumbers, List<Advisor> advisors,
            Country country) {
        this.advisors = advisors;
        this.country = country;
        this.name = name;
        this.players = players;
        this.trikotNumbers = trikotNumbers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer key : trikotNumbers.keySet()) {
            Player pl = trikotNumbers.get(key);
            sb.append(String.format("%s <%d>\n", pl, key));
        }
        return sb.toString();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Map<Integer, Player> getTrikotNumbers() {
		return trikotNumbers;
	}

	public void setTrikotNumbers(Map<Integer, Player> trikotNumbers) {
		this.trikotNumbers = trikotNumbers;
	}

	public List<Advisor> getAdvisors() {
		return advisors;
	}

	public void setAdvisors(List<Advisor> advisors) {
		this.advisors = advisors;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
