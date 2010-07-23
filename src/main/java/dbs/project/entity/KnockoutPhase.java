package dbs.project.entity;
import java.util.Collection;
import java.util.List;

public class KnockoutPhase
{
    private KnockoutMatch finalMatch;
    // TODO: Baum bau'n
    Collection<KnockoutMatch> matchTree;
    Match nextMatch;
    List<Match> previousMatches;

    public KnockoutPhase() {}
}
