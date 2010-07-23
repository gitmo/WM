package dbs.project.entity;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Player extends Person {
    String nickname;
    String club;

    public Player() {}
    
    public Player(String first, String last, String nick, Date today,
            String club, int height, int weight) {
        super(first, last, today, height, weight);
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
}
