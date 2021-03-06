package dbs.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import dbs.project.entity.Tournament;

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

	/**
	 * Executes the stored procedure createChampionship
	 * (creates a new tournament with sample teams)
	 * 
	 * @param year
	 * @param name
	 */
	@SuppressWarnings("deprecation")
	public static void generateTournament(int year, String name) {
		try {
			session.beginTransaction();
			Connection conn = session.connection();
			PreparedStatement stmt = conn.prepareStatement("SELECT createChampionship(?,?);");
			stmt.setInt(1, year);
			stmt.setString(2, name);
			stmt.execute();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
