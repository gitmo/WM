package dbs.project.entity.permission;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Permission {
	@Id
	String typeOfAccess;
	
	@ManyToOne
	Resource resource;

	public String getTypeOfAccess() {
		return typeOfAccess;
	}

	public void setTypeOfAccess(String typeOfAccess) {
		this.typeOfAccess = typeOfAccess;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
