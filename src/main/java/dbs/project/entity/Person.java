package dbs.project.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
	@Id
	@GeneratedValue
	protected Long id;

	protected Integer height = 0;

	protected Integer weight = 0;

	protected String firstname;

	protected String lastname;

	protected Date birthday;

	@ManyToMany
	protected List<Team> teams;

	public Person() {
	}

	public Person(String firstname, String lastname, Date birthday, int height,
			int weight) {
		setFirstname(firstname);
		setLastname(lastname);
		setBirthday(birthday);
		setHeight(height);
		setWeight(weight);
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Team> getTeams() {
		if (teams == null)
			return new LinkedList<Team>();

		return teams;
	}

	public void addTeam(Team team) {
		if (teams == null)
			teams = new LinkedList<Team>();

		teams.add(team);
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public String toString() {
		return String.format("%s %s", firstname, lastname);
	}
}
