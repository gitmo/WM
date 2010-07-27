package dbs.project.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class ExampleTeamGenerator {

	private static final String INPUT_FOLDER = "dev/generator/";
	private static final String OUTPUT_FOLDER = "dev/";
	private static final String FIRSTNAME_FILE = Generator
			.getAbsoluteFilePath(INPUT_FOLDER + "staffVor");
	private static final String LASTNAME_FILE = Generator
			.getAbsoluteFilePath(INPUT_FOLDER + "staffNach");
	private static final String TEAM_FILE = Generator
			.getAbsoluteFilePath(INPUT_FOLDER + "teams");
	private static final int AMOUNT_OF_TEAMS = 32;

	private static final String CSV_OUTPUT_FILE = Generator
			.getAbsoluteFilePath(OUTPUT_FOLDER + "teams.csv");

	/*
	 * Erzeugt die Datei teams_dev.csv landname, Trainername,
	 * Assistenztrainername, Teamarztname
	 */

	public static void generateTeams() throws Exception {
		Vector<String> firstnames = new Vector<String>();
		Vector<String> lastnames = new Vector<String>();
		Vector<String> teams = new Vector<String>();

		BufferedReader in = new BufferedReader(new FileReader(FIRSTNAME_FILE));
		String line;
		while ((line = in.readLine()) != null) {
			firstnames.add(line.trim());
		}
		in.close();

		in = new BufferedReader(new FileReader(LASTNAME_FILE));
		while ((line = in.readLine()) != null) {
			lastnames.add(line.trim());
		}
		in.close();

		in = new BufferedReader(new FileReader(TEAM_FILE));
		while ((line = in.readLine()) != null) {
			teams.add(line.trim());
		}
		in.close();

		Random randomizer = new Random();

		String fnC, lnC, fnCC, lnCC, fnDr, lnDr, teamname;
		int alias;

		Set<String> usedTeams = new HashSet<String>();

		BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_OUTPUT_FILE));
		for (int i = 0; i < AMOUNT_OF_TEAMS; i++) {

			do {
				teamname = teams.get(randomizer.nextInt(teams.size()));
			} while (usedTeams.contains(teamname));
			usedTeams.add(teamname);

			lnC = lastnames.get(randomizer.nextInt(lastnames.size()));
			fnC = firstnames.get(randomizer.nextInt(firstnames.size()));
			lnCC = lastnames.get(randomizer.nextInt(lastnames.size()));
			fnCC = firstnames.get(randomizer.nextInt(firstnames.size()));
			lnDr = lastnames.get(randomizer.nextInt(lastnames.size()));
			fnDr = firstnames.get(randomizer.nextInt(firstnames.size()));

			alias = randomizer.nextInt(100);

			System.out.println("Generated Team: " + teamname);
			if (alias % 17 == 0) {

				bw.write(teamname + ", , " + lnC + ", " + fnCC + ", " + lnCC
						+ ", " + fnDr + ", Dr. " + lnDr + "\n");
			} else {
				bw.write(teamname + ", " + fnC + ", " + lnC + ", " + fnCC
						+ ", " + lnCC + ", " + fnDr + ", Dr. " + lnDr + "\n");
			}
		}

		bw.close();
	}

	public static void main(String[] args) throws Exception {
		generateTeams();
	}

}
