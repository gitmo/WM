package dbs.project.dao.event;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.dao.DaoBase;
import dbs.project.entity.Match;
import dbs.project.entity.event.player.CardEvent;

public class CardEventDao extends DaoBase{

	@SuppressWarnings("unchecked")
	public static List<CardEvent> findAllByMatch(Match match) {
		return session.createCriteria(CardEvent.class)
				.add(Restrictions.eq("match", match)).list();
	}
	
	@SuppressWarnings("unchecked")
	public static List<CardEvent> findAll(){
		return session.createCriteria(CardEvent.class).list();
	}

}
