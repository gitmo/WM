package dbs.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country
{
	@Id
    private String name;

    public Country() {}

	public Country(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
