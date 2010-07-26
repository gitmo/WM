package dbs.project.dev;

import java.io.*;
import java.text.*;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;

import dbs.project.service.GroupStageService;
import dbs.project.dao.TeamDao;
import dbs.project.dao.TournamentDao;
import dbs.project.entity.Advisor;
import dbs.project.entity.Country;
import dbs.project.entity.GroupStage;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
//import dbs.project.stage.KnockoutStage;

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
			
			if(line.length < 5) {
				System.out.println("Warning: not enough line informations");
				continue;
			}
			
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
			System.out.println("Generated Player:" + player);
		}
		
		return players;
	}
	
	public static String getRootpath() {
		return System.getProperty("user.dir");
	}
	
	public static String getAbsoluteFilePath(String fileName) {
		return getRootpath().concat("/target/classes/" + fileName);
	}
	
	private static List<String[]> getCsvList(String fileName) {
		String filePath = getAbsoluteFilePath(fileName);
		CSVReader reader;
		List<String[]> csvList = null;
		try {
			reader = new CSVReader(new FileReader(filePath));
			csvList = reader.readAll();
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("file " + filePath + "not found");
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
			System.out.println("Generated Team:" + team);
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
			System.out.println("Populated Team:" + team);
		}
		return teams;
	}
	
	public static void main(String[] args) throws Exception {
		
		List<Team> teams = Generator.LoadAndPopulateTeams("dev/teams.csv", "dev/players.csv");
		TeamDao.saveAll(teams);
		
		Tournament tournament = new Tournament();
		
		List<Country> hostCountries = new ArrayList<Country>();
		hostCountries.add(teams.get(0).getCountry());
		tournament.setHostCountries(hostCountries);
		
		Random random = new Random();
		
		int year = 1970 + random.nextInt(50);
		tournament.setName("WM " + year);
		tournament.setYear(year);
		
		GroupStage groupStage = GroupStageService.getByTeams(teams);
		tournament.setGroupPhase(groupStage);
		
//		KnockoutStage knockoutStage = new KnockoutStage();
//		knockoutStage.init(groupStage);
//		tournament.setKnockoutPhase(knockoutPhase);
		
		tournament.setStadiums(null);
		
		TournamentDao.save(tournament);
		
		System.out.println(tournament);
	}
	
}
