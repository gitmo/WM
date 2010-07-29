package dbs.project.entity.permission;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Resource {
	@Id
	String name;
	
	String keyDataType;

	String keyValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyDataType() {
		return keyDataType;
	}

	public void setKeyDataType(String keyDataType) {
		this.keyDataType = keyDataType;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
}
