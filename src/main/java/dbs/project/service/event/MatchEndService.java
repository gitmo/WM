package dbs.project.service.event;

import java.util.ArrayList;
import java.util.List;

import dbs.project.dao.event.MatchEndEventDao;
import dbs.project.entity.Match;
import dbs.project.entity.event.MatchEndEvent;

public class MatchEndService {

	public static List<MatchEndEvent> getByMatch(Match match) {
		return MatchEndEventDao.findAllByMatch(match);
	}

}
