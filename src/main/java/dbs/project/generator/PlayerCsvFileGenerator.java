package dbs.project.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Vector;

import dbs.project.generator.TournamentGenerator.CannotWriteToJARException;

public class PlayerCsvFileGenerator {

	private static final String OUTPUT_FOLDER = "/dev";
	private static final String OUTPUT_FILE = "players.csv";
	private static final String FIRSTNAME_FILE = "/dev/generator/spielerVor";
	private static final String LASTNAME_FILE = "/dev/generator/spielerNach";
	private static final int AMOUNT_OF_PLAYERS = 5000;

	private static final int MIN_HEIGHT = 155, MAX_HEIGHT = 205,
			MIN_WEIGHT = 55, MAX_WEIGHT = 95;

	/**
	 * Erzeugt die Datei player.csv Vorname, Nachname, Geburtszeitpunkt,
	 * Groesse, Gewicht
	 * 
	 * @throws IOException
	 */
	public static void generatePlayers() throws IOException {
		Vector<String> firstnames = new Vector<String>();
		Vector<String> lastnames = new Vector<String>();

		BufferedReader in = new BufferedReader(
				TournamentGenerator.inputStreamReaderFromPath(FIRSTNAME_FILE));
		String line;
		while ((line = in.readLine()) != null) {
			firstnames.add(line.trim());
		}
		in.close();

		in = new BufferedReader(
				TournamentGenerator.inputStreamReaderFromPath(LASTNAME_FILE));
		while ((line = in.readLine()) != null) {
			lastnames.add(line.trim());
		}
		in.close();

		Random randomizer = new Random();

		String firstname, lastname;
		int weight, height, alias;
		Calendar birthday = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(TournamentGenerator.createFileWriter(
					OUTPUT_FOLDER, OUTPUT_FILE));
		} catch (CannotWriteToJARException e) {
			return;
		}

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
				bw.write(", " + lastname + ", "
						+ formatter.format(birthday.getTime()) + ", " + height
						+ ", " + weight + "\n");
			} else {
				bw.write(firstname + ", " + lastname + ", "
						+ formatter.format(birthday.getTime()) + ", " + height
						+ ", " + weight + "\n");
			}
		}
		bw.close();
	}

	public static void main(String[] args) throws Exception {
		generatePlayers();
	}

}
