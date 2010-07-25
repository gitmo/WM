package dbs.project.main;

import dbs.project.dao.TournamentDao;
import dbs.project.entity.Tournament;


public class App {

	public static void main(String[] args) {
		System.out.println("WM");
		
		for(Tournament tournament : TournamentDao.fetchAll())
			System.out.println(tournament);
		
	}

}
