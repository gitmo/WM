package dbs.project.helper;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dbs.project.entity.GroupMatch;
import dbs.project.entity.GroupStage;
import dbs.project.entity.KnockoutMatch;
import dbs.project.entity.Match;
import dbs.project.entity.MatchEvent;
import dbs.project.entity.Player;
import dbs.project.entity.Stadium;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;
import dbs.project.entity.event.player.CardEvent;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.entity.event.player.LineUpEvent;
import dbs.project.entity.event.player.SubstitutionEvent;

public class TestHelper {

	public static List<Player> players(){
		List<Player> players = new ArrayList<Player>();
		for(Integer i=1;i<=23*32;i++)
			players.add(new Player("player", i.toString(), "p"+i.toString(), null, null, 0,0));
		return players;
	}

	public static List<Team> teams(){
		List<Team> teams = new ArrayList<Team>();
		List<Player> players = players();
		for(Integer i=1;i<=32;i++)
			teams.add(new Team("team "+i.toString(), players.subList((i-1)*23, i*23), null, null, null));
		return teams;
	}
	
	private static List<GroupMatch> groupMatches(List<Team> teams, TournamentGroup group){
		List<GroupMatch> matches = new ArrayList<GroupMatch>();
		for(int i=0;i<4;i++){
			Team host = teams.get(i);
			for(int j=i+1; j<4; j++){
				if(i==j)
					continue;
				Team guest = teams.get(j);
				GroupMatch tmp = new GroupMatch();
				tmp.setHostTeam(host);
				tmp.setGuestTeam(guest);
				tmp.setGroup(group);
				//generate LineUp
				matchLineUp(tmp);
				
				matches.add(tmp);
			}
		}
		return matches;
		
	}
	
	public static List<TournamentGroup> groups(){
		List<Team> teams = teams();
		List<TournamentGroup> groups = new ArrayList<TournamentGroup>();
		for(Integer i=1;i<=8;i++){
		
			//generate temporary group
			TournamentGroup tmp = new TournamentGroup();
			tmp.setTeams(teams.subList((i-1)*4, (i)*4));
			tmp.setName("Group "+ ((char)(i+64)));

			groups.add(tmp);
			
			//add groupMatches to group
			List<GroupMatch> matches = groupMatches(tmp.getTeams(), tmp);
			tmp.setMatches(matches);
	
		}
		
		
		return groups;
	}
	
	public static GroupStage groupStage(){
		GroupStage groupStage = new GroupStage();
		List<TournamentGroup> groups = groups();
		
		groupStage.setGroups(groups);
		
		return groupStage;
	}

	private static List<Stadium> stadiums() {
		List<Stadium> stadiums = new ArrayList<Stadium>();
		for(Integer i=1; i<=8; i++){
			Stadium tmp = new Stadium();
			tmp.setCapacity(0);
			tmp.setName("stadium "+ i.toString());
			tmp.setCity("city "+ i.toString());
			
			stadiums.add(tmp);
		}
		return stadiums;
	}
	
	public static Tournament tournament(){
		Tournament tournament = new Tournament();
		tournament.setGroupStage(groupStage());
		tournament.setFinalMatch(knockoutMatch());
		tournament.setYear(0);
		tournament.setName("testTournament");
		tournament.setStadiums(stadiums());

		return tournament;
	}
	
	public static GroupStage playedGroupStage(){
		GroupStage groupStage = groupStage();
		List<TournamentGroup> groups = groupStage.getGroups();
		for(TournamentGroup group : groups)
			for(GroupMatch match : group.getMatches()){
				
				match.setPlayed(true);
				
				//generate LineUp
				matchLineUp(match);
				
				//generate Goal Event
				//player1 of hostTeam
				MatchEvent event = new GoalEvent(match,90,match.getHostTeam().getPlayers().get(0),match.getHostTeam());
				match.addEvent(event);
				
				//generate Substitution Event
				//player2 of guestTeam against player12 of guestTeam
				event = new SubstitutionEvent(match,45,match.getGuestTeam().getPlayers().get(1),match.getGuestTeam().getPlayers().get(11));
				match.addEvent(event);
				
				//generate Card Event
				//player1 of guestTeam
				event = new CardEvent(match, 1, match.getGuestTeam().getPlayers().get(0), "yellow");
				match.addEvent(event);
				
			}
				
		return groupStage;
	}

	public static KnockoutMatch knockoutMatch(){
		KnockoutMatch finalMatch = new KnockoutMatch("Finale");
		GroupStage groupStage = playedGroupStage();
		
		//initialize halfFinals
		List<KnockoutMatch> halfFinals = new ArrayList<KnockoutMatch>();
		halfFinals.add(new KnockoutMatch("Halbfinale1"));
		halfFinals.add(new KnockoutMatch("Halbfinale2"));
		finalMatch.setChildren(halfFinals);
		
		//initialize quarterFinals
		List<KnockoutMatch> quarterFinals1 = new ArrayList<KnockoutMatch>();
		quarterFinals1.add(new KnockoutMatch("Viertelfinale1"));
		quarterFinals1.add(new KnockoutMatch("Viertelfinale2"));
		halfFinals.get(0).setChildren(quarterFinals1);
		
		List<KnockoutMatch> quarterFinals2 = new ArrayList<KnockoutMatch>();
		quarterFinals2.add(new KnockoutMatch("Viertelfinale3"));
		quarterFinals2.add(new KnockoutMatch("Viertelfinale4"));
		halfFinals.get(1).setChildren(quarterFinals2);
		
		//initialize bestOfSixteen
		List<KnockoutMatch> bestOfSixteen1 = new ArrayList<KnockoutMatch>();
		bestOfSixteen1.add(new KnockoutMatch());
		bestOfSixteen1.add(new KnockoutMatch());
		quarterFinals1.get(0).setChildren(bestOfSixteen1);
		
		List<KnockoutMatch> bestOfSixteen2 = new ArrayList<KnockoutMatch>();
		bestOfSixteen2.add(new KnockoutMatch());
		bestOfSixteen2.add(new KnockoutMatch());
		quarterFinals1.get(1).setChildren(bestOfSixteen2);
		
		List<KnockoutMatch> bestOfSixteen3 = new ArrayList<KnockoutMatch>();
		bestOfSixteen3.add(new KnockoutMatch());
		bestOfSixteen3.add(new KnockoutMatch());
		quarterFinals2.get(0).setChildren(bestOfSixteen3);
		
		List<KnockoutMatch> bestOfSixteen4 = new ArrayList<KnockoutMatch>();
		bestOfSixteen4.add(new KnockoutMatch());
		bestOfSixteen4.add(new KnockoutMatch());
		quarterFinals2.get(1).setChildren(bestOfSixteen4);
		
		//generate best of 16
		bestOfSixteen1.get(0).setHostTeam(groupStage.getGroups().get(0).getTeams().get(0));
		bestOfSixteen1.get(0).setGuestTeam(groupStage.getGroups().get(1).getTeams().get(1));
		bestOfSixteen1.get(0).setChildren(new ArrayList<KnockoutMatch>());
		
		bestOfSixteen1.get(1).setHostTeam(groupStage.getGroups().get(1).getTeams().get(0));
		bestOfSixteen1.get(1).setGuestTeam(groupStage.getGroups().get(0).getTeams().get(1));
		bestOfSixteen1.get(1).setChildren(new ArrayList<KnockoutMatch>());
		
		bestOfSixteen2.get(0).setHostTeam(groupStage.getGroups().get(2).getTeams().get(0));
		bestOfSixteen2.get(0).setGuestTeam(groupStage.getGroups().get(3).getTeams().get(1));
		bestOfSixteen2.get(0).setChildren(new ArrayList<KnockoutMatch>());
		
		bestOfSixteen2.get(1).setHostTeam(groupStage.getGroups().get(3).getTeams().get(0));
		bestOfSixteen2.get(1).setGuestTeam(groupStage.getGroups().get(2).getTeams().get(1));
		bestOfSixteen2.get(1).setChildren(new ArrayList<KnockoutMatch>());
		
		bestOfSixteen3.get(0).setHostTeam(groupStage.getGroups().get(4).getTeams().get(0));
		bestOfSixteen3.get(0).setGuestTeam(groupStage.getGroups().get(5).getTeams().get(1));
		bestOfSixteen3.get(0).setChildren(new ArrayList<KnockoutMatch>());
		
		bestOfSixteen3.get(1).setHostTeam(groupStage.getGroups().get(5).getTeams().get(0));
		bestOfSixteen3.get(1).setGuestTeam(groupStage.getGroups().get(4).getTeams().get(1));
		bestOfSixteen3.get(1).setChildren(new ArrayList<KnockoutMatch>());
		
		bestOfSixteen4.get(0).setHostTeam(groupStage.getGroups().get(6).getTeams().get(0));
		bestOfSixteen4.get(0).setGuestTeam(groupStage.getGroups().get(7).getTeams().get(1));
		bestOfSixteen4.get(0).setChildren(new ArrayList<KnockoutMatch>());
		
		bestOfSixteen4.get(1).setHostTeam(groupStage.getGroups().get(7).getTeams().get(0));
		bestOfSixteen4.get(1).setGuestTeam(groupStage.getGroups().get(6).getTeams().get(1));
		bestOfSixteen4.get(1).setChildren(new ArrayList<KnockoutMatch>());
		
		return finalMatch;
	}

	public static KnockoutMatch playedKnockoutMatch(){
		KnockoutMatch finalMatch = knockoutMatch();
		
		List<KnockoutMatch> halfFinals = finalMatch.getChildren();
		
		List<KnockoutMatch> quarterFinals1 =  halfFinals.get(0).getChildren();
		List<KnockoutMatch> quarterFinals2 =  halfFinals.get(1).getChildren();
		
		List<KnockoutMatch> bestOfSixteen1 = quarterFinals1.get(0).getChildren();
		List<KnockoutMatch> bestOfSixteen2 = quarterFinals1.get(1).getChildren();
		List<KnockoutMatch> bestOfSixteen3 = quarterFinals2.get(0).getChildren();
		List<KnockoutMatch> bestOfSixteen4 = quarterFinals2.get(1).getChildren();
		
		//generate quarterFinals
		quarterFinals1.get(0).setHostTeam(bestOfSixteen1.get(0).getHostTeam());
		quarterFinals1.get(0).setGuestTeam(bestOfSixteen1.get(1).getHostTeam());
		
		quarterFinals1.get(1).setHostTeam(bestOfSixteen2.get(0).getHostTeam());
		quarterFinals1.get(1).setGuestTeam(bestOfSixteen2.get(1).getHostTeam());
		
		quarterFinals2.get(0).setHostTeam(bestOfSixteen3.get(0).getHostTeam());
		quarterFinals2.get(0).setGuestTeam(bestOfSixteen3.get(1).getHostTeam());
		
		quarterFinals2.get(1).setHostTeam(bestOfSixteen4.get(0).getHostTeam());
		quarterFinals2.get(1).setGuestTeam(bestOfSixteen4.get(1).getHostTeam());
		
		//generate halfFinals
		halfFinals.get(0).setHostTeam(quarterFinals1.get(0).getHostTeam());
		halfFinals.get(0).setGuestTeam(quarterFinals1.get(1).getHostTeam());
		
		halfFinals.get(1).setHostTeam(quarterFinals2.get(0).getHostTeam());
		halfFinals.get(1).setGuestTeam(quarterFinals2.get(1).getHostTeam());
		
		//generate finalMatch
		finalMatch.setHostTeam(halfFinals.get(0).getHostTeam());
		finalMatch.setGuestTeam(halfFinals.get(1).getHostTeam());
		
		matchLineUp(finalMatch);
		
		
		//generate matchEvents
		List<KnockoutMatch> tmpKnockoutMatches = new ArrayList<KnockoutMatch>();
		KnockoutMatch match;
		tmpKnockoutMatches.add(finalMatch);
		while(tmpKnockoutMatches.size()>0){
			match = tmpKnockoutMatches.remove(0);
			
			
			tmpKnockoutMatches.addAll(match.getChildren());
			MatchEvent event;
			
			//generate Goal Event
			event = new GoalEvent(match, 90, match.getHostTeam().getPlayers().get(0), match.getHostTeam());
			match.addEvent(event);
			
			//generate Substitution Event
			event = new SubstitutionEvent(match, 45, match.getHostTeam().getPlayers().get(1), match.getHostTeam().getPlayers().get(2));
			match.addEvent(event);			
			
			//generate Card Event
			event = new CardEvent(match, 1, match.getHostTeam().getPlayers().get(3), "yellow");
			match.addEvent(event);
		
			//set match to played
			match.setPlayed(true);
			
		}
		
		return finalMatch;
	}

	public static Tournament playedTournament(){
		Tournament t = new Tournament();
		t.setFinalMatch(playedKnockoutMatch());
		t.setGroupStage(playedGroupStage());
		t.setName("test tournament");
		t.setYear(0);
		t.setStadiums(stadiums());
//		t.setMatchForThirdPlace(matchForThirdPlace());
		
		return t;
	}

	public static KnockoutMatch match(){
		KnockoutMatch match = new KnockoutMatch();
		match.setHostTeam(teams().get(0));
		match.setGuestTeam(teams().get(1));
		match.setStadium(stadiums().get(0));
		
		matchLineUp(match);
		
		return match;
	}
	
	private static KnockoutMatch matchForThirdPlace() {
		KnockoutMatch third = new KnockoutMatch();
		KnockoutMatch finalMatch = playedKnockoutMatch();
		
		third.setHostTeam(finalMatch.getChildren().get(0).getGuestTeam());
		third.setGuestTeam(finalMatch.getChildren().get(1).getGuestTeam());
		
		MatchEvent event = new GoalEvent(third,90,third.getHostTeam().getPlayers().get(0), third.getHostTeam());
		third.addEvent(event);
		
		event = new SubstitutionEvent(third,45,third.getHostTeam().getPlayers().get(1), third.getHostTeam().getPlayers().get(2));
		third.addEvent(event);
		
		event = new CardEvent(third, 1, third.getHostTeam().getPlayers().get(3),"red");
		third.addEvent(event);
		
		matchLineUp(third);
		
		third.setPlayed(true);
		
		return third;
		
	}

	private static void matchLineUp(Match match){
		for(int i=0;i<11;i++){
			MatchEvent event = new LineUpEvent(match,match.getHostTeam().getPlayers().get(i),match.getHostTeam());
			match.addEvent(event);
			event = new LineUpEvent(match,match.getGuestTeam().getPlayers().get(i),match.getGuestTeam());
			match.addEvent(event);
		}
	}

	@Test
	public void testHelper() {
		Tournament tournament = null;
		tournament = tournament();
		assertNotNull(tournament);
	}
}
