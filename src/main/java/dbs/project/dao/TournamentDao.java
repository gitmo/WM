package dbs.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;

public class TournamentDao extends DaoBase {

	/**
	 * Creates or updates a tournament
	 * 
	 * @param tournament
	 */
	public static void save(Tournament tournament) {
		session.beginTransaction();
		session.saveOrUpdate(tournament);
		session.getTransaction().commit();
	}

	/**
	 * Deletes a tournament
	 * 
	 * @param tournament
	 */
	public static void delete(Tournament tournament) {
		session.beginTransaction();
		session.delete(tournament);
		session.getTransaction().commit();
	}

	/**
	 * Tries to find a tournament
	 * 
	 * @param name
	 * @return
	 */
	public static Tournament find(String name) {
		return (Tournament) session.load(Tournament.class, name);
	}

	/**
	 * Fetches all tournament entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Tournament> fetchAll() {
		return session.createQuery("From Tournament").list();
	}

	@SuppressWarnings("deprecation")
	public static void generateTournament(int year, String name) {
		try {
			Connection conn = session.connection();
//			PreparedStatement stmt = conn.prepareStatement("SELECT createChampionship(?,?);");
//			stmt.setInt(1, year);
//			stmt.setString(2, name);
//			stmt.execute();
			Statement stmt = conn.createStatement();
			stmt.executeQuery("SELECT createChampionship(252, 'Weltmeisterschaft');");
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
