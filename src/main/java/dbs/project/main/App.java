package dbs.project.main;

import dbs.project.dao.TournamentGroupDao;
import dbs.project.entity.TournamentGroup;
import dbs.project.service.GroupService;


public class App {

	public static void main(String[] args) {
		System.out.println("WM");
		
//		for(Tournament tournament : TournamentDao.fetchAll())
//			System.out.println(tournament);
		
		for(TournamentGroup group : TournamentGroupDao.fetchAll()) {
			System.out.println(group.getName());
			GroupService.printStandings(group);
			System.out.println("\n\n");
		}
	}

}
