package dbs.project.entity;
import java.util.List;

/**
 * Abstract class Match - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Match
{
    Team hostTeam;
    Team guestTeam;
    List<Player> hostLineup;
    List<Player> guestLineup;    
    Stadium stadium;
    List<MatchEvent> events;
    
    public Match() {}
}
