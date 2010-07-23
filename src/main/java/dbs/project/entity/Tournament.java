package dbs.project.entity;
import java.util.List;

public class Tournament
{
    private String name;
    private Integer year;
    private List<Country> hostCountries;
    private KnockoutPhase knockoutPhase;
    private GroupPhase groupPhase;
    private List<Stadium> stadiums;
    
    public Tournament() {}
}
