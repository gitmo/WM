package dbs.project.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;

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

	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
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
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Team> getTeams() {
		if (this.teams == null)
			return new LinkedList<Team>();

		return this.teams;
	}

	public void addTeam(Team team) {
		if (this.teams == null)
			this.teams = new LinkedList<Team>();

		this.teams.add(team);
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return String.format("%s %s", this.firstname, this.lastname);
	}
}
