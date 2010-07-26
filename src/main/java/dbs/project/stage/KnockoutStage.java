package dbs.project.stage;
import java.util.List;

import dbs.project.entity.KnockoutMatch;

public class KnockoutStage
{
	private KnockoutMatch finalMatch;

    public KnockoutStage() {}

	public KnockoutStage(KnockoutMatch finalMatch) {
		this.finalMatch = finalMatch;
	}

	public KnockoutMatch getFinalMatch() {
		return finalMatch;
	}

	public void setFinalMatch(KnockoutMatch finalMatch) {
		this.finalMatch = finalMatch;
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
