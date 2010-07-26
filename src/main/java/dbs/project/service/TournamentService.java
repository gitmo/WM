package dbs.project.service;

import java.util.List;

import javax.swing.ListModel;

import org.hibernate.cfg.NotYetImplementedException;

import dbs.project.dao.TournamentDao;
import dbs.project.entity.Tournament;

public class TournamentService {
	public static void weAreTheChampions(Tournament tournament) {
		throw new NotYetImplementedException("weAreTheChampions()");
	}

	public static ListModel getListModel() {
		final List<Tournament> tournaments = TournamentDao.fetchAll(); 
		
		class ListModel extends javax.swing.AbstractListModel {
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return tournaments.size();
			}
	        
			public Tournament getElementAt(int i) {
				return tournaments.get(i);
			}
		}
		
		return new ListModel();
	}
}
