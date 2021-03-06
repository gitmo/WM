package dbs.project.entity.permission;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dbs.project.entity.Tournament;

@Entity
public class Role {
	@Id
	String name;

	@ManyToOne
	Tournament tournament;

	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	Role inheritedRole;

	@OneToMany
	@Cascade(CascadeType.SAVE_UPDATE)
	List<Permission> permissions;

	public Role(String name, Tournament tournament) {
		this.name = name;
		this.tournament = tournament;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tournament getTournament() {
		return this.tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Role getInheritedRole() {
		return this.inheritedRole;
	}

	public void setInheritedRole(Role inheritedRole) {
		this.inheritedRole = inheritedRole;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
