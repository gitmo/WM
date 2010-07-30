package dbs.project.entity.permission;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.service.ActorService;

@Entity
public class Actor {
	@Id
	String email;

	@OneToMany
	@Cascade(CascadeType.SAVE_UPDATE)
	@Column(nullable = true)
	List<Role> roles;

	@OneToMany
	@Cascade(CascadeType.SAVE_UPDATE)
	@Column(nullable = true)
	List<Permission> permissions;

	String password_hash;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String plaintext) {
		setPassword_hash(ActorService.encryptPassword(plaintext));
	}

	public String getPassword_hash() {
		return this.password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
