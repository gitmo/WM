package dbs.project.entity;

import java.util.List;
import java.util.Map;

public class Team {
    String name;
    List<Player> players;
    Map<Integer, Player> trikotNumbers;
    List<Advisor> advisors;
    Country country;

    public Team() {}
}
