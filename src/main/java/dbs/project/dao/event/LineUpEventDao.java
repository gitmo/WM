package dbs.project.dao.event;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.dao.DaoBase;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.LineUpEvent;

public class LineUpEventDao extends DaoBase {
	@SuppressWarnings("unchecked")
	public static List<LineUpEvent> findByTeam(Team team) {
		return (List<LineUpEvent>) session.createCriteria(LineUpEvent.class)
				.add(Restrictions.eq("team", team)).list();
	}
}
