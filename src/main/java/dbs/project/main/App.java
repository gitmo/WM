package dbs.project.main;

import dbs.project.dao.TeamDao;
import dbs.project.entity.Team;


public class App {

	public static void main(String[] args) {
		System.out.println("App");
		
		for(Team team : TeamDao.fetchAll())
			System.out.println(team);
		
	}

}
