package dbs.project.stage;

import java.util.List;

import dbs.project.entity.KnockoutMatch;

public class KnockoutStage {
	private KnockoutMatch finalMatch;
	private KnockoutMatch matchForThirdPlace;

	public KnockoutStage() {
	}

	public KnockoutStage(KnockoutMatch finalMatch,
			KnockoutMatch matchForThirdPlace) {
		this.finalMatch = finalMatch;
		this.setMatchForThirdPlace(matchForThirdPlace);
	}

	public KnockoutMatch getFinalMatch() {
		return finalMatch;
	}

	public void setFinalMatch(KnockoutMatch finalMatch) {
		this.finalMatch = finalMatch;
	}

	public void setMatchForThirdPlace(KnockoutMatch matchForThirdPlace) {
		this.matchForThirdPlace = matchForThirdPlace;
	}

	public KnockoutMatch getMatchForThirdPlace() {
		return matchForThirdPlace;
	}

	public List<KnockoutMatch> getSemiFinals() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<KnockoutMatch> getQuarterFinals() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<KnockoutMatch> getRoundOfSixteen() {
		// TODO Auto-generated method stub
		return null;
	}
}
