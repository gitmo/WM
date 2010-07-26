package dbs.project.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Player extends Person {
    String nickname = "";
    String club = "";

    public Player() {}
    
    public Player(String first, String last, String nick, Date birthday,
            String club, Integer height, Integer weight) {
        super(first, last, birthday, height, weight);
        
        this.nickname = nick;
        this.club = club;
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
		return String.format(
				"%s %s geboren am %s (Groesse: %d, Gewicht: %d)",
			 	this.firstname,
			 	this.lastname,
			 	this.birthday,
			 	this.height,
			 	this.weight
		);
	}
}
