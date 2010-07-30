package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Stadium {
	@Id
	@GeneratedValue
	protected long stadiumId;

	protected String name;

	protected String city;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	protected Country country;

	protected int capacity;

	public Stadium() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return getName();
	}
}
