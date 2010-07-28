package dbs.project.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Team {
	@Id
	@GeneratedValue
	protected Long id;

	protected String name;

	@OneToMany
	protected Map<Integer, Player> trikotNumbers;

	@OneToMany
	@Cascade(CascadeType.ALL)
	protected List<Advisor> advisors;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	protected Country country;

	@OneToMany
	@Cascade(CascadeType.ALL)
	protected List<Player> players;

	public Team() {
	}

	public Team(String name, List<Player> players,
			Map<Integer, Player> trikotNumbers, List<Advisor> advisors,
			Country country) {
		setName(name);
		setPlayers(players);
		setTrikotNumbers(trikotNumbers);
		setAdvisors(advisors);
		setCountry(country);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		if (players == null)
			return new LinkedList<Player>();

		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Map<Integer, Player> getTrikotNumbers() {
		if (trikotNumbers == null)
			return new HashMap<Integer, Player>();

		return trikotNumbers;
	}

	public void setTrikotNumbers(Map<Integer, Player> trikotNumbers) {
		this.trikotNumbers = trikotNumbers;
	}

	public List<Advisor> getAdvisors() {
		if (advisors == null)
			return new LinkedList<Advisor>();

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

	public void addPlayer(Player player) {
		if (this.players == null)
			this.players = new LinkedList<Player>();

		this.players.add(player);
	}

	public boolean equals(Team obj) {
		return (this.getName() == obj.getName()) ? true : false;
	}

	public String toString() {
		return getName();
	}

}
