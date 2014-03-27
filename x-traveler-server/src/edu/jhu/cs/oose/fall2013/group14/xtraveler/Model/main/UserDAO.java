package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.transactions.TransactionRoutine;

/**
 * This is the dao layer for table user, the upper layer can query the table user by this class
 * @author kekekeng
 */

public class UserDAO {
	// password is the input user's password
	private String password;
	private boolean userInDatabase;

	SessionFactory sessionFactory;

	/**
	 * This method is the constructor of this class
	 * @param sessionFactory it passes in a sessionFactory
	 */
	public UserDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method is to get a user by given the user name
	 * @param userName the user's name
	 * @return user the user object with this name
	 */

	public User getUser(String userName) {
		TransactionRoutine<String, User, RuntimeException> routine = new TransactionRoutine<String, User, RuntimeException>(
				this.sessionFactory) {
			@Override
			public User executeWithinTransaction(Session session,
					Transaction tx, String userName) {
				Query query = session
						.createQuery("from User where username=:username");
				query.setParameter("username", userName);
				@SuppressWarnings("unchecked")
				List<User> list = query.list();
				for (User u : list) {
					return u;
				}
				return null; // TODO: should we throw an exception instead?
			}
		};
		return routine.execute(userName);
	}

	/**
	 * Given a user name will return the list of his friends' names
	 * @param userName the name of the user
	 * @return one person's all friends name
	 */

	public List<String> getFriendList(String userName) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<String> names = new ArrayList<String>();
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from User where username=:username");
			query.setParameter("username", userName);
			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			User user = new User();
			for (User u : list) {
				user = u;
			}

			if (user.getFriends().isEmpty()) {
				System.out.println("no friend for this user");
			} else {
				for (User u1 : user.getFriends()) {
					String name = u1.getUserName();
					names.add(name);
				}
			}

			tx.commit();
		} catch (Throwable e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return names;
	}

	/**
	 *Given the user name will return the plan list of this user
	 * @param userName the name of the user
	 * @return the plan list this user has
	 */
	public List<String> getPlanList(String userName) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<String> names = new ArrayList<String>();
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from User where username=:username");
			query.setParameter("username", userName);
			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			User user = new User();
			for (User u : list) {
				user = u;
			}

			if (user.getPlanList().isEmpty()) {
				System.out.println("no plan for this user");
			} else {
				for (Plan u1 : user.getPlanList()) {
					String name = u1.getPlanName();
					names.add(name);
				}
			}

			tx.commit();
		} catch (Throwable e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return names;
	}

	/**
	 * Given the user's name it will return if the user in database already
	 * @param userName the name of the user
	 * @return a boolean that if the user in the database
	 */
	public boolean userInDatabase(String userName) {
		return getUser(userName) != null;
	}

	/**
	 * Given the name of user it will return the user's password
	 * @param userName the user's name
	 * @return the user's password 
	 */

	public String getPassword(String userName) {
		User user = getUser(userName);
		if (user == null) {

			throw new IllegalStateException(); // TODO: what is the right
												// behavior here?
		} else {
			return user.getPassword();
		}
	}

	/**
	 * Given the user's name, it will return the user's age
	 * @param userName the user name
	 * @return the user's age
	 */
	public int getAge(String userName) {
		User user = getUser(userName);
		if (user == null) {

			throw new IllegalStateException(); // TODO: what is the right
												// behavior here?
		} else {
			return user.getAge();
		}
	}

	/**
	 * Given the user's name, it will return the sex of this user
	 * @param userName the user's name
	 * @return the user's sex 
	 */
	public String getSex(String userName) {
		User user = getUser(userName);
		if (user == null) {

			throw new IllegalStateException(); // TODO: what is the right
												// behavior here?
		} else {
			return user.getSex();
		}
	}

	/**
	 * Given the user's name, it will return the mobile of this user
	 * @param userName the user's name
	 * @return the user's mobile
	 */
	public String getMobile(String userName) {
		User user = getUser(userName);
		if (user == null) {

			throw new IllegalStateException(); // TODO: what is the right
												// behavior here?
		} else {
			return user.getMobile();
		}
	}

	public String getEamil(String userName) {
		User user = getUser(userName);
		if (user == null) {

			throw new IllegalStateException(); // TODO: what is the right
												// behavior here?
		} else {
			return user.getEmail();
		}
	}

	/*
	 * insert user into database
	 */

	public void insertUser(String userName, String password, int age,
			String email, String sex) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer userID = null;
		try {
			tx = session.beginTransaction();
			User user = new User();

			user.setUserName(userName);
			user.setEmail(email);
			user.setAge(age);
			user.setSex(sex);
			user.setPassword(password);

			userID = (Integer) session.save(user);
			tx.commit();
		} catch (Throwable e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	/**
	 * delete user from database
	 * @param userName user name
	 */
	public void deleteUser(String userName) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			Query query = session
					.createQuery("delete from User where username=:username");
			query.setParameter("username", userName);

			query.executeUpdate();
			System.out.println(query.executeUpdate());
			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}
	}

	/**
	 * Given the use's name and a friend name, it will add this friend to the user's friend
	 * @param userName the user's name
	 * @param friendName a friend's name
	 */
	public void addFriend(String userName, String friendName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		User u = null;
		User p = null;
		try {

			Query query = session
					.createQuery("from User where username=:username");
			query.setParameter("username", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			for (User use_r : list) {
				// return System.out.println(use_r.getUserName());
				u = use_r;
			}
			Query query1 = session
					.createQuery("from User where username= :username");
			query1.setParameter("username", friendName);

			@SuppressWarnings("unchecked")
			List<User> list1 = query1.list();
			for (User p1 : list1) {
				p = p1;
			}
			u.getFriends().add(p);
			session.save(u);
			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}
	}

	/**
	 * Given a user's name and friend's name it will delete the friend from the user's friend list
	 * @param userName user name
	 * @param friendName friend name
	 */
	public void deleteFriend(String userName, String friendName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		User u = null;
		User p = null;
		try {

			Query query = session
					.createQuery("from User where username=:username");
			query.setParameter("username", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			for (User use_r : list) {
				// return System.out.println(use_r.getUserName());
				u = use_r;
			}
			Query query1 = session
					.createQuery("from User where username= :username");
			query1.setParameter("username", friendName);

			@SuppressWarnings("unchecked")
			List<User> list1 = query1.list();
			for (User p1 : list1) {
				p = p1;
			}
			u.getFriends().remove(p);
			session.save(u);
			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}

	}

	/**
	 * Given the user name the method will list all the people visited the same places with the user
	 * @param userName user's name
	 * @return a list of people have visited the commen places with the user
	 */
	public List<String> listCommonVisitetPeople(String userName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> names = new ArrayList<String>();
		User u = null;
		try {

			Query query = session
					.createQuery("from User where username=:username");
			query.setParameter("username", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			for (User use_r : list) {
				// return System.out.println(use_r.getUserName());
				u = use_r;
			}

			for (Place p1 : u.getMyVisitedList()) {
				for (User u1 : p1.getVisitedPlace()) {
					names.add(u1.getUserName());
				}
			}
			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}

		return names;
	}

	/**
	 * This method is given a user name it will return a list of people who have same places in their wishlist
	 * @param userName user's name
	 * @return the people who have same interested places with the user
	 */
	public List<String> listCommonInterestPeople(String userName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> names = new ArrayList<String>();
		User u = null;
		try {

			Query query = session
					.createQuery("from User where username=:username");
			query.setParameter("username", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			for (User use_r : list) {
				// return System.out.println(use_r.getUserName());
				u = use_r;
			}

			for (Place p1 : u.getMywishList()) {
				for (User u1 : p1.getWishPlace()) {
					names.add(u1.getUserName());
				}
			}

			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}

		return names;
	}

	/**
	 * Given a user's name it will return a list of places with the same places' feature the where user has added to his wishlist
	 * @param userName the user's name
	 * @return a list of places with same place feature with the places in the user's wish list
	 */
	public List<String> listInterestedPlaces(String userName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> features = new ArrayList<String>();
		List<String> places = new ArrayList<String>();
		User u = null;
		try {

			Query query = session
					.createQuery("from User where username=:username");
			query.setParameter("username", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			for (User use_r : list) {
				u = use_r;
			}

			for (Place p1 : u.getMywishList()) {
				if (!features.contains(p1.getPlaceFeature())) {
					features.add(p1.getPlaceFeature());
				}
			}
			PlaceDAO placedao = new PlaceDAO(sessionFactory);
			for (String pf : features) {
				for (String s : placedao.placeWithOneFeature(pf)) {
					if (!places.contains(s)) {
						places.add(s);
					}
				}
			}

			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}
		return places;
	}

	/**
	 *  Given a user's name it will return a list of places with the same places' feature the where user has added to his visited place
	 * @param userName
	 * @return a list of places have same place features with the places the user add to his visited list
	 */
	public List<String> listInterestedByVisitedPlaces(String userName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> features = new ArrayList<String>();
		List<String> places = new ArrayList<String>();
		User u = null;
		try {

			Query query = session
					.createQuery("from User where username=:username");
			query.setParameter("username", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			for (User use_r : list) {
				u = use_r;
			}

			for (Place p1 : u.getMyVisitedList()) {
				if (!features.contains(p1.getPlaceFeature())) {
					features.add(p1.getPlaceFeature());
				}
			}
			PlaceDAO placedao = new PlaceDAO(sessionFactory);
			for (String pf : features) {
				for (String s : placedao.placeWithOneFeature(pf)) {
					if (!places.contains(s)) {
						places.add(s);
					}
				}
			}

			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}
		return places;

	}

	/**
	 * Given the user object to return its name
	 * @param user the user object
	 * @return the name of this user
	 */
	public String getName(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		String username = null;
		try {
			tx = session.beginTransaction();
			username = user.getUserName();

			tx.commit();
		} catch (Throwable e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return username;

	}

	/**
	 * This method will list three users randomly
	 * @param username the user name
	 * @return three place's name by randomly
	 */
	public List<String> threeAttraction(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> pl = new ArrayList<String>();
		try {

			Query query = session
					.createQuery("select P.userName from User P");
			List<String> places = query.list();
			
			pl.add(places.get(0));
			pl.add(places.get(1));
		//	pl.add(places.get(2));
			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}

		return pl;

	}
	
	/**
	 * This method will list three places randomly
	 * @param username the user name
	 * @return three user's name by randomly
	 */
	public List<String> threeUser(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> pl = new ArrayList<String>();
		try {

			Query query = session
					.createQuery("select P.imageURL from Place P");
			List<String> places = query.list();
			
			pl.add(places.get(0));
			pl.add(places.get(1));
		//	pl.add(places.get(2));
			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}

		return pl;

	}

}