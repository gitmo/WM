package dbs.project.dev;

import java.io.*;
import java.text.*;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;

import dbs.project.entity.Advisor;
import dbs.project.entity.Country;
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
	
	public static List<Team> loadSampleTeamsFromCsv(String fileName) {
		List<Team> teams = new ArrayList<Team>();
		List<String[]> csvList = getCsvList(fileName);
		
		for(String[] line : csvList) {
			Country country = new Country(line[0]);
			List<Advisor> advisors = new ArrayList<Advisor>();
			
			advisors.add(new Advisor(line[1], line[2], null, 0, 0));
			advisors.add(new Advisor(line[3], line[4], null, 0, 0));
			advisors.add(new Advisor(line[5], line[6], null, 0, 0));
			
			Team team = new Team(line[0], null, null, advisors, country);
			teams.add(team);
		}
		
		return teams;
	}
	
	public static List<Team> LoadAndPopulateTeams(String teamFile, String playerFile) {
		List<Team> teams = loadSampleTeamsFromCsv(teamFile);
		List<Player> players = loadSamplePlayersFromCsvFile(playerFile);
		
		Random random = new Random();
		
		for(Team team : teams) {
			for(int i=0; i<23; i++) {
				if(players.size() > 0) {
					int tmpIndex = random.nextInt(players.size());
					team.addPlayer(players.remove(tmpIndex));
				} else {
					System.out.println("Warning: no players left for team " + team.getName());
				}
			}
		}
		return teams;
	}
	
}
