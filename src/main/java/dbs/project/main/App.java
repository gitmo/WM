package dbs.project.main;

import java.io.IOException;
import java.util.List;

import dbs.project.dao.PlayerDao;
import dbs.project.dev.Generator;
import dbs.project.entity.Player;


public class App {

	public static void main(String[] args) throws IOException {
		System.out.println("App");
		
		List<Player> players = Generator.loadSamplePlayersFromCsvFile("dev/players.csv");
		PlayerDao.saveAll(players);
		
		for(Player player : PlayerDao.fetchAll()) {
			System.out.println(player);
		}
		
		
		
	}

}
