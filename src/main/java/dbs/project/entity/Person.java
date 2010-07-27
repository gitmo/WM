package dbs.project.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person {
	@Id
	@GeneratedValue
	Long id;
    Integer height = 0;
    Integer weight = 0;
    String firstname = "";
    String lastname = "";
    Date birthday;
    @OneToMany
    @Cascade(CascadeType.ALL)
    List<Team> teams;

    public Person() {}
    
    public Person(String first, String last, Date birth, int height, int weight) {
        this.height = height;
        this.weight = weight;
        firstname = first;
        lastname = last;
        this.birthday = birth;
    }
    
    public String toString() {
        return String.format("%s %s", firstname, lastname);
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
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
