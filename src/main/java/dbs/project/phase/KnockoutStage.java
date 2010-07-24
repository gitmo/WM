package dbs.project.phase;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import dbs.project.entity.KnockoutMatch;
import dbs.project.entity.Match;

public class KnockoutStage
{
	private KnockoutMatch finalMatch;
    // TODO: Baum bau'n
    Collection<KnockoutMatch> matchTree;
	Match nextMatch;
	List<Match> previousMatches;

    public KnockoutStage() {}

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
