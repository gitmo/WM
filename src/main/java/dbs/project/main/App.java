package dbs.project.main;

import dbs.project.dao.TournamentGroupDao;
import dbs.project.entity.TournamentGroup;
import dbs.project.exception.NoGroupMatchesSet;
import dbs.project.service.GroupService;

public class App {

	public static void main(String[] args) {
		System.out.println("WM");

		// for(Tournament tournament : TournamentDao.fetchAll())
		// System.out.println(tournament);

		for (TournamentGroup group : TournamentGroupDao.fetchAll()) {
			System.out.println(group.getName());
			try {
				GroupService.printStandings(group);
			} catch (NoGroupMatchesSet e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n\n");
		}
	}

}
