package dbs.project.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Player extends Person {
	protected String nickname;
	protected String club;

	public Player() {
	}

	public Player(String firstname, String lastname, String nick,
			Date birthday, String club, Integer height, Integer weight) {
		super(firstname, lastname, birthday, height, weight);

		setNickname(nick);
		setClub(club);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String toString() {
		return String.format("%s %s geboren am %s (Groesse: %d, Gewicht: %d)",
				getFirstname(), getLastname(), getBirthday(), getHeight(),
				getWeight());
	}

	public String getName() {
		return getFirstname() + " " + getLastname();
	}
}
