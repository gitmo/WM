package dbs.project.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class ExamplePlayerGenerator {

	private static final String INPUT_FOLDER = "dev/generator/";
	private static final String OUTPUT_FOLDER = "dev/";
	private static final String FIRSTNAME_FILE = Generator.getAbsoluteFilePath(INPUT_FOLDER + "spielerVor");
	private static final String LASTNAME_FILE = Generator.getAbsoluteFilePath(INPUT_FOLDER + "spielerNach");
	private static final int AMOUNT_OF_PLAYERS = 5000;
	
	private static final int MIN_HEIGHT = 155, MAX_HEIGHT = 205, MIN_WEIGHT = 55, MAX_WEIGHT = 95;
	
	private static final String CSV_OUTPUT_FILE = Generator.getAbsoluteFilePath(OUTPUT_FOLDER + "players.csv");
	
	/*
	 * 
	 * Erzeugt die Datei player_dev.csv
	 * Vorname, Nachname, Geburtszeitpunkt, Groesse, Gewicht
	 * 
	 */
	public static void generatePlayers() throws Exception {
		Vector<String> firstnames = new Vector<String>();
		Vector<String> lastnames = new Vector<String>();
		
		BufferedReader in = new BufferedReader(new FileReader(FIRSTNAME_FILE));
		String line;
		while((line = in.readLine()) != null){
			firstnames.add(line.trim());
		}
		in.close();
		
		in = new BufferedReader(new FileReader(LASTNAME_FILE));
		while((line = in.readLine()) != null){
			lastnames.add(line.trim());
		}
		in.close();

		Random randomizer = new Random();
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, 1973);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		long min = cal.getTimeInMillis();
		
		cal.set(Calendar.YEAR, 1993);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		
		long max = cal.getTimeInMillis();

		
		Date date;
				
		String firstname, lastname;
		int weight, height;
		int alias;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_OUTPUT_FILE));
		for(int i=0;i<AMOUNT_OF_PLAYERS;i++){

			date = new Date((long)(randomizer.nextDouble()*(max-min))+min);
			lastname = lastnames.get(randomizer.nextInt(lastnames.size()));
			firstname = firstnames.get(randomizer.nextInt(firstnames.size()));
			height = (randomizer.nextInt(MAX_HEIGHT - MIN_HEIGHT))+ MIN_HEIGHT;
			weight = (randomizer.nextInt(MAX_WEIGHT - MIN_WEIGHT)) + MIN_WEIGHT;
			alias = randomizer.nextInt(100);
			
			System.out.println("Generated Player: " + lastname);
			if (alias % 17 == 0) {
			
				bw.write(", " + lastname + ", " + date + 
						", " + height + ", " + weight + "\n");
			} else {
				bw.write(firstname + ", " + lastname + ", " + date +
						", " + height + ", " + weight + "\n");
				}
		}
		
		bw.close();
	}
	
	
	public static void main(String[] args) throws Exception{
		generatePlayers();
	}

}
