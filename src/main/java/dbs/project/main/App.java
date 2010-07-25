package dbs.project.main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import dbs.project.dao.CountryDao;
import dbs.project.dao.PlayerDao;
import dbs.project.dao.TeamDao;
import dbs.project.dev.ExamplePlayerGenerator;
import dbs.project.dev.Generator;
import dbs.project.entity.Country;
import dbs.project.entity.Player;
import dbs.project.entity.Team;


public class App {

	public static void main(String[] args) throws IOException {
		System.out.println("App");
		
//		List<Player> players = Generator.loadSamplePlayersFromCsvFile("dev/players.csv");
//		PlayerDao.saveAll(players);
//		
//		for(Player player : PlayerDao.fetchAll()) {
//			System.out.println(player);
//		}
		
		
		List<Team> teams = Generator.LoadAndPopulateTeams("dev/teams.csv", "dev/players.csv");
		
		TeamDao.saveAll(teams);
		
		for(Team team : TeamDao.fetchAll())
			System.out.println(team);
		
//		List<Player> players = Generator.loadSamplePlayersFromCsvFile("dev/players.csv");
//		PlayerDao.saveAll(players);
//		
//		Team ladida = new Team("Holland", null, null, null, null);
//		for(Player player : players)
//			ladida.addPlayer(player);
//		
//		TeamDao.save(ladida);
	}

}
