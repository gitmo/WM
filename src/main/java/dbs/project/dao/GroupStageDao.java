package dbs.project.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import dbs.project.entity.GroupStage;
import dbs.project.entity.Team;
import dbs.project.entity.TournamentGroup;

public class GroupStageDao {
	private static final int MAX_TEAMS_PER_GROUP = 4;

	public static GroupStage getByTeams(List<Team> teams) throws Exception {
		if(teams.size() % MAX_TEAMS_PER_GROUP != 0)
			throw new Exception("Could not generate equally groups");
		
		int numberOfGroups = teams.size() / MAX_TEAMS_PER_GROUP;
		List<TournamentGroup> allGroups = new LinkedList<TournamentGroup>();
		Random random = new Random();
		for(int i = 0; i < numberOfGroups; i++) {
			TournamentGroup currentGroup = new TournamentGroup();
			currentGroup.setName(String.format("Group %c", i + 65));
			
			List<Team> groupTeams = new LinkedList<Team>();
			for(int j=0; j < MAX_TEAMS_PER_GROUP; j++)
				groupTeams.add(teams.remove(random.nextInt(teams.size())));
			
			currentGroup.setTeams(groupTeams);
			currentGroup.setMatches(MatchesDao.generateMatches(groupTeams));

			allGroups.add(currentGroup);
		}
		
		GroupStage groupStage = new GroupStage();
		groupStage.setGroups(allGroups);
		
		return groupStage;
	}

}
