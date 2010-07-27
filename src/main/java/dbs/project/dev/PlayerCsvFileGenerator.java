package dbs.project.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

public class PlayerCsvFileGenerator {

	private static final String INPUT_FOLDER = "dev/generator/";
	private static final String OUTPUT_FOLDER = "dev/";
	private static final String FIRSTNAME_FILE = TournamentGenerator
			.getAbsoluteFilePath(INPUT_FOLDER + "spielerVor");
	private static final String LASTNAME_FILE = TournamentGenerator
			.getAbsoluteFilePath(INPUT_FOLDER + "spielerNach");
	private static final int AMOUNT_OF_PLAYERS = 5000;

	private static final int MIN_HEIGHT = 155, MAX_HEIGHT = 205,
			MIN_WEIGHT = 55, MAX_WEIGHT = 95;

	private static final String CSV_OUTPUT_FILE = TournamentGenerator
			.getAbsoluteFilePath(OUTPUT_FOLDER + "players.csv");

	/*
	 * 
	 * Erzeugt die Datei player_dev.csv Vorname, Nachname, Geburtszeitpunkt,
	 * Groesse, Gewicht
	 */
	public static void generatePlayers() throws Exception {
		Vector<String> firstnames = new Vector<String>();
		Vector<String> lastnames = new Vector<String>();

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

		Random randomizer = new Random();

		String firstname, lastname;
		int weight, height, alias;
		Calendar birthday = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_OUTPUT_FILE));
		for (int i = 0; i < AMOUNT_OF_PLAYERS; i++) {
			lastname = lastnames.get(randomizer.nextInt(lastnames.size()));
			firstname = firstnames.get(randomizer.nextInt(firstnames.size()));
			height = (randomizer.nextInt(MAX_HEIGHT - MIN_HEIGHT)) + MIN_HEIGHT;
			weight = (randomizer.nextInt(MAX_WEIGHT - MIN_WEIGHT)) + MIN_WEIGHT;
			alias = randomizer.nextInt(100);
			birthday.set(Calendar.YEAR, 1970 + randomizer.nextInt(30));
			birthday.set(Calendar.DAY_OF_YEAR, 1 + randomizer.nextInt(356));

			System.out.println("Generated Player: " + lastname);
			if (alias % 17 == 0) {
				bw.write(", " + lastname + ", " + formatter.format(birthday) + ", " + height + ", "
						+ weight + "\n");
			} else {
				bw.write(firstname + ", " + lastname + ", " + formatter.format(birthday) + ", "
						+ height + ", " + weight + "\n");
			}
		}
		bw.close();
	}

	public static void main(String[] args) throws Exception {
		generatePlayers();
	}

}
