package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.hibernate.SessionFactory;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceCommentDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlanDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * ExecutionContext provide DAO class and session factory to the concrete
 * command
 * 
 * @version java7
 * @author iamrick86
 * 
 */
public class ExecutionContext {
	private SessionFactory sessionFactory;
	private UserDAO userDAO;
	private PlaceDAO placeDAO;
	private PlanDAO planDAO;
	private PlaceCommentDAO placeCommentDAO;

	/**
	 * 
	 * @param sessionFactory
	 *            sent from server class
	 * @param userDAO
	 *            DAO class can get the user's information
	 * @param placeDAO
	 *            DAO class can get the place's information
	 * @param planDAO
	 *            DAO class get the plan's information
	 */
	public ExecutionContext(SessionFactory sessionFactory, UserDAO userDAO,
			PlaceDAO placeDAO, PlanDAO planDAO, PlaceCommentDAO placeCommentDAO) {
		super();
		this.sessionFactory = sessionFactory;
		this.userDAO = userDAO;
		this.placeDAO = placeDAO;
		this.planDAO = planDAO;
		this.placeCommentDAO = placeCommentDAO;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public PlaceDAO getPlaceDAO() {
		return placeDAO;
	}

	public PlanDAO getPlanDAO() {
		return planDAO;
	}

	public PlaceCommentDAO getPlaceCommentDAO() {
		return placeCommentDAO;
	}

}
