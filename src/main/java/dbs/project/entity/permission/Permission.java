package dbs.project.entity.permission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Permission {
	@Id
	@GeneratedValue
	long id;

	PermissionAccessType typeOfAccess;

	@ManyToOne
	Resource resource;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PermissionAccessType getTypeOfAccess() {
		return typeOfAccess;
	}

	public void setTypeOfAccess(PermissionAccessType typeOfAccess) {
		this.typeOfAccess = typeOfAccess;
	}

	@Override
	public String toString() {
		return String.format("Permission: (%s, %s)", getResource(),
				getTypeOfAccess().name());
	}
}
