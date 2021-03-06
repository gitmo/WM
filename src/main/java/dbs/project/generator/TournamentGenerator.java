package dbs.project.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;
import dbs.project.dao.TournamentDao;
import dbs.project.entity.Advisor;
import dbs.project.entity.Country;
import dbs.project.entity.GroupStage;
import dbs.project.entity.KnockoutMatch;
import dbs.project.entity.Player;
import dbs.project.entity.Stadium;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.util.Tuple;

public class TournamentGenerator {

	private static TournamentGenerator instance;
	static {
		instance = new TournamentGenerator();
	}

	public class CannotWriteToJARException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	private static final String TEAMS_CSV = "/dev/teams.csv";
	private static final String PLAYERS_CSV = "/dev/players.csv";
	private static final String STADIUMS_CSV = "/dev/stadiums.csv";

	/**
	 * Example: loadPlayersFromCsvFile("/dev/players.csv")
	 * 
	 * @param String
	 *            csvFileName
	 * @return List<Player>
	 */
	public static List<Player> loadSamplePlayersFromCsvFile(String fileName) {
		List<Player> players = new ArrayList<Player>();
		List<String[]> csvList = getCsvList(fileName);

		for (String[] line : csvList) {

			if (line.length < 5) {
				System.out.println("Warning: not enough line informations");
				continue;
			}

			Date birthday = null;
			Integer height = 0, weight = 0;

			try {
				height = Integer.parseInt(line[3].trim());
				weight = Integer.parseInt(line[4].trim());
				birthday = new SimpleDateFormat("dd-MM-yyyy").parse(line[2]
						.trim());
			} catch (ParseException e) {
			} catch (NumberFormatException e) {
			}

			Player player = new Player(line[0].trim(), line[1].trim(), "",
					birthday, "no club", height, weight);

			players.add(player);
			System.out.println("Generated Player:" + player);
		}

		return players;
	}

	public static URL getFileUrl(String fileName) {
		return ClassLoader.class.getResource(fileName);

	}

	public static InputStreamReader inputStreamReaderFromPath(String fileName) {
		try {
			return new InputStreamReader(getFileUrl(fileName).openStream());
		} catch (IOException e) {
			System.out.println("Error creating InputStreamReader from "
					+ fileName);
			e.printStackTrace();
		}
		return null;
	}

	private static List<String[]> getCsvList(String fileName) {
		CSVReader reader = null;
		List<String[]> csvList = null;
		try {
			reader = new CSVReader(inputStreamReaderFromPath(fileName));
			csvList = reader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvList;
	}

	public static List<Team> loadSampleTeamsFromCsv(String fileName) {
		List<Team> teams = new ArrayList<Team>();
		List<String[]> csvList = getCsvList(fileName);

		for (String[] line : csvList) {
			Country country = new Country(line[0]);
			List<Advisor> advisors = new ArrayList<Advisor>();

			advisors.add(new Advisor(line[1], line[2], null, 0, 0, "Trainer"));
			advisors.add(new Advisor(line[3], line[4], null, 0, 0, "Co-Trainer"));
			advisors.add(new Advisor(line[5], line[6], null, 0, 0,
					"Mannschaftsarzt"));

			Team team = new Team(line[0], null, null, advisors, country);
			teams.add(team);
			System.out.println("Generated Team:" + team);
		}

		return teams;
	}

	public static List<Team> LoadAndPopulateTeams(String teamFile,
			String playerFile) {
		List<Team> teams = loadSampleTeamsFromCsv(teamFile);
		List<Player> players = loadSamplePlayersFromCsvFile(playerFile);
		Random random = new Random();

		for (Team team : teams) {
			// team.setTrikotNumbers(new HashMap<Integer, Player>());
			System.out.println("Populating " + team);
			for (int i = 0; i < 23; i++) {
				if (players.size() > 0) {
					int tmpIndex = random.nextInt(players.size());
					Player tmpPlayer = players.remove(tmpIndex);
					tmpPlayer.addTeam(team);
					team.addPlayer(tmpPlayer);
					System.out.println("Adding " + tmpPlayer.getLastname()
							+ " to Team " + team.getName());
				} else {
					System.out.println("Warning: no players left for team "
							+ team.getName());
				}
			}
			System.out.println("Populated Team:" + team);
		}
		return teams;
	}
	
	public static int randomYear() {
		Calendar tournamentYear = new GregorianCalendar();
		int year = new Random().nextInt(100);
		year -= year % 4;
		tournamentYear.set(Calendar.YEAR, 1970 + year);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

		String yearString = formatter.format(tournamentYear.getTime());
		
		return Integer.parseInt(yearString);
	}

	public static void generateTournament() throws Exception {

		PlayerCsvFileGenerator.generatePlayers();
		TeamCsvFileGenerator.generateTeams();

		List<Team> teams = TournamentGenerator.LoadAndPopulateTeams(TEAMS_CSV,
				PLAYERS_CSV);

		Tournament tournament = new Tournament();

		List<Country> hostCountries = new ArrayList<Country>();
		hostCountries.add(teams.get(0).getCountry());
		tournament.setHostCountries(hostCountries);
		
		tournament.setName("Weltmeisterschaft");
		tournament.setYear(randomYear());

		List<Stadium> stadiums = loadSampleStadiumsFromCsv(STADIUMS_CSV);
		Collections.shuffle(stadiums);
		tournament.setStadiums(stadiums.subList(0, 8));

		GroupStage groupStage = GroupStageGenerator.getByTeams(teams,
				tournament.getStadiums(), tournament);
		tournament.setGroupStage(groupStage);

		Tuple<KnockoutMatch, KnockoutMatch> knockoutStage = KnockoutGenerator
				.getDefault();
		tournament.setFinalMatch(knockoutStage.getFirst());
		tournament.setMatchForThirdPlace(knockoutStage.getSecond());

		TournamentDao.save(tournament);

		System.out.println(tournament);
	}

	private static List<Stadium> loadSampleStadiumsFromCsv(String fileName) {
		List<Stadium> stadiums = new ArrayList<Stadium>();
		List<String[]> csvList = getCsvList(fileName);
		for (String[] line : csvList) {
			int capacity = 0;
			try {
				capacity = Integer.parseInt(line[1].trim());
			} catch (NumberFormatException e) {
			}

			Stadium tmpStadium = new Stadium();
			tmpStadium.setName(line[0].trim());
			tmpStadium.setCity(line[0].trim());
			tmpStadium.setCountry(new Country("Honolulu"));
			tmpStadium.setCapacity(capacity);
			stadiums.add(tmpStadium);
		}
		return stadiums;
	}

	public static FileWriter createFileWriter(String outputFolder,
			String outputFile) throws IOException, CannotWriteToJARException {

		URL fileUrl = TournamentGenerator.getFileUrl(outputFolder);
		if (fileUrl.getProtocol() != "file")
			throw instance.new CannotWriteToJARException();

		String path = fileUrl.getPath() + "/" + outputFile;
		return new FileWriter(path);
	}

	public static void main(String[] args) throws Exception {
		generateTournament();
	}
}
