package dbs.project.dev;

import java.io.*;
import java.text.*;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;

import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.main.App;

public class Generator {

	
	/**
	 * Example: loadPlayersFromCsvFile("dev/players.csv")
	 * @param String csvFileName
	 * @retun List<Player>
	 */
	public static List<Player> loadSamplePlayersFromCsvFile(String fileName) {
		List<Player> players = new ArrayList<Player>();
		List<String[]> csvList = getCsvList(fileName);
		
		for(String[] line : csvList) {
			Date birthday = null;
			Integer height = 0, weight = 0;
			
			try {
				height = Integer.parseInt(line[3].trim());
				weight = Integer.parseInt(line[4].trim());
				birthday = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(line[2].trim());
			}
			catch(ParseException e) {}
			catch(NumberFormatException e) {}

			Player player = new Player(
					line[0].trim(),
					line[1].trim(),
					"",
					birthday,
					"no club",
					height,
					weight
			);
			
			players.add(player);
		}
		
		return players;
	}
	
	private static List<String[]> getCsvList(String fileName) {
		String filePath = App.class.getClassLoader().getResource(fileName).getPath().replaceAll("%20", " ");
		CSVReader reader;
		List<String[]> csvList = null;
		try {
			reader = new CSVReader(new FileReader(filePath));
			csvList = reader.readAll();
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("file not found");
		} catch (IOException e) {
			System.err.println("could not read file");
		}
		
		return csvList;
	}
	
//	public static List<Team> loadSampleTeamsFromCsv(String fileName) {
//		List<Team> teams = new ArrayList<Team>();
//		List<String[]> csvList = getCsvList(fileName);
//		
//		return teams;
//	}
	
}
