package dbs.project.generator;

import java.util.LinkedList;
import java.util.List;

import dbs.project.entity.KnockoutMatch;
import dbs.project.util.Tuple;

public class KnockoutGenerator {

	private static final String[] matchLevel = { "Finale", "Halbfinale",
			"Viertelfinal", "Achtelfinale" };
	private static final int[] matchLevelCount = new int[4];

	/**
	 * generates recursively the KnockoutPhase
	 * 
	 * @param parent
	 * @param height
	 */
	private static void addRecursivlyKnockoutMatch(KnockoutMatch parent,
			int height) {
		if (height > 3)
			return;

		String matchName1 = matchLevel[height] + " "
				+ (matchLevelCount[height]++);
		String matchName2 = matchLevel[height] + " "
				+ (matchLevelCount[height]++);

		KnockoutMatch match1 = new KnockoutMatch(matchName1);
		KnockoutMatch match2 = new KnockoutMatch(matchName2);

		List<KnockoutMatch> childs = new LinkedList<KnockoutMatch>();
		childs.add(match1);
		childs.add(match2);
		parent.setChildren(childs);

		int newHeight = ++height;
		addRecursivlyKnockoutMatch(match1, newHeight);
		addRecursivlyKnockoutMatch(match2, newHeight);
	}

	public static Tuple<KnockoutMatch, KnockoutMatch> getDefault() {
		KnockoutMatch root = new KnockoutMatch("Finale");
		for (int i = 0; i < matchLevel.length; i++)
			matchLevelCount[i] = 1;
		addRecursivlyKnockoutMatch(root, 1);

		KnockoutMatch thirdPlace = new KnockoutMatch("Spiel um Platz 3");
		// thirdPlace.setChilds(root.getChilds());

		return new Tuple<KnockoutMatch, KnockoutMatch>(root, thirdPlace);
	}

}
