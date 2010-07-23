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

	public KnockoutMatch getFinalMatch() {
		return finalMatch;
	}

	public void setFinalMatch(KnockoutMatch finalMatch) {
		this.finalMatch = finalMatch;
	}

	public Collection<KnockoutMatch> getMatchTree() {
		return matchTree;
	}

	public void setMatchTree(Collection<KnockoutMatch> matchTree) {
		this.matchTree = matchTree;
	}

	public Match getNextMatch() {
		return nextMatch;
	}

	public void setNextMatch(Match nextMatch) {
		this.nextMatch = nextMatch;
	}

	public List<Match> getPreviousMatches() {
		return previousMatches;
	}

	public void setPreviousMatches(List<Match> previousMatches) {
		this.previousMatches = previousMatches;
	}
}
