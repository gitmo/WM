package dbs.project.entity.permission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Permission {

	public enum AccessType {
		// CRUD
		CREATE, READ, UPDATE, DELETE;
	}

	@Id
	@GeneratedValue
	long id;

	AccessType typeOfAccess;

	@ManyToOne
	Resource resource;

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AccessType getTypeOfAccess() {
		return this.typeOfAccess;
	}

	public void setTypeOfAccess(Permission.AccessType typeOfAccess) {
		this.typeOfAccess = typeOfAccess;
	}

	@Override
	public String toString() {
		return String.format("Permission: (%s, %s)", getResource(),
				getTypeOfAccess().name());
	}
}
