package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Stadium
{
	@Id
	@GeneratedValue
	long stadiumId;
	
    private String city;
	
    @ManyToOne
	private Country country;
    private Integer capacity;

    public Stadium() {}

	public String getCity() {
		return city;
	}

	public Stadium setCity(String city) {
		this.city = city;
		
		return this;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}
