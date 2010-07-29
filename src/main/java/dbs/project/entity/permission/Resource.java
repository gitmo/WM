package dbs.project.entity.permission;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Resource {
	@Id
	@GeneratedValue
	long id;

	String name;

	Serializable key;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Serializable getKey() {
		return key;
	}

	public void setKey(Serializable serObj) {
		this.key = serObj;
	}

	@Override
	public String toString() {
		return "Resource: " + this.getName();
	}
}
