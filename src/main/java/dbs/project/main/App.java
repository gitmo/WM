package dbs.project.main;

import dbs.project.dao.TournamentDao;
import dbs.project.dao.TournamentGroupDao;
import dbs.project.entity.GroupStage;
import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;
import dbs.project.exception.NoGroupMatchesSet;
import dbs.project.generator.GroupStageGenerator;
import dbs.project.service.GroupService;

public class App {

	public static void main(String[] args) {
		System.out.println("WM");

		 for(Tournament tournament : TournamentDao.fetchAll())
//			 System.out.println(tournament.getGroupStage().getGroups().get(0).getTeams().get(0).getPlayers());
				GroupStageGenerator.enterResults(tournament.getGroupStage());

	}

}
