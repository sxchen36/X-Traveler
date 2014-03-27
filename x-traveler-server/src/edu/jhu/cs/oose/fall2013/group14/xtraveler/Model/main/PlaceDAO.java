package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.transactions.TransactionRoutine;

/**
 * This class is place dao which help the upper layer of the project to query the database, which is related to the table place
 * @author kekekeng
 * 
 */
public class PlaceDAO {

	SessionFactory sessionFactory;
	private boolean placeInWishList;
	private List<String> wishList;
	private List<String> visitedlist;
     
	/**
	 * This is the constructor of this class
	 * @param sessionFactory The sessionFactory is passed in 
	 */
	public PlaceDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    /**
     * This method is used to insert a place into database
     * @param placeName the name of one new place
     * @param city the city this place belongs to 
     * @param state the state this place belongs to 
     * @param description the description of this place which saved in our database
     * @param placeFeature the feature of this place
     * @param imageURL the image of this place
     * @param star the evaluation of this place
     */
	public void insertPlace(String placeName, String city, String state,
			String description, String placeFeature, String imageURL, int star) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer placeID = null;
		try {
			tx = session.beginTransaction();
			Place p = new Place();

			p.setPlaceName(placeName);
			p.setCity(city);
			p.setState(state);
			p.setDescription(description);
			p.setPlaceFeature(placeFeature);
			p.setImageURL(imageURL);
			p.setStar(star);

			placeID = (Integer) session.save(p);
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
	 * Given a place name we can get the a place object. We use template pattern to write it, in which we can get the 
	 * current session and transaction.
	 * @param placeName the name of this place
	 * @return
	 */
	public Place getPlace(String placeName) {
		TransactionRoutine<String, Place, RuntimeException> routine = new TransactionRoutine<String, Place, RuntimeException>(
				this.sessionFactory) {
			@Override
			public Place executeWithinTransaction(Session session,
					Transaction tx, String placeName) {
				Query query = session
						.createQuery("from Place where placename=:placename");
				query.setParameter("placename", placeName);
				@SuppressWarnings("unchecked")
				List<Place> list = query.list();
				for (Place u : list) {
					return u;
				}
				return null;
			}
		};
		return routine.execute(placeName);
	}

	/**
	 * Given a place name, it will return a place image
	 * @param placeName the name of one place
	 * @return the place image url
	 */
	public String getPlaceImage(String placeName) {
		Place place = getPlace(placeName);
		if (place == null) {

			throw new IllegalStateException();

		} else {
			return place.getImageURL();
		}
	}

	/**
	 * Given a name of one place, it will return a place description
	 * @param placeName the name of one place
	 * @return a place description
	 */
	public String getPlaceDescription(String placeName) {
		Place place = getPlace(placeName);
		if (place == null) {

			throw new IllegalStateException();

		} else {
			return place.getDescription();
		}
	}

	/**
	 * Given a name of one place it will return the feature of this place
	 * @param placeName the name of one place
	 * @return the place feature
	 */
	public String getPlaceFeature(String placeName) {
		Place place = getPlace(placeName);
		if (place == null) {

			throw new IllegalStateException();

		} else {
			return place.getPlaceFeature();
		}
	}
 
	/**
	 * Given a name of one place it will return the rate of this place
	 * @param placeName name of a place
	 * @return rate of a place
	 */
	public int getPlaceRate(String placeName) {
		Place place = getPlace(placeName);
		if (place == null) {

			throw new IllegalStateException();

		} else {
			return place.getStar();
		}
	}

	/**
	 * Given a name of one place it will return the city of this place
	 * @param placeName the place name
	 * @return the city it belongs to
	 */
	public String getPlaceCity(String placeName) {
		Place place = getPlace(placeName);
		if (place == null) {

			throw new IllegalStateException();

		} else {
			return place.getCity();
		}
	}

	/**
	 * Given a name of one place it will return the state of this place
	 * @param placeName the name of a place
	 * @return the state the city belongs to 
	 */
	public String getPlaceState(String placeName) {
		Place place = getPlace(placeName);
		if (place == null) {

			throw new IllegalStateException();

		} else {
			return place.getState();
		}
	}

	/**
	 * This method is to add one place into a user's wishlist
	 * @param userName the user's name
	 * @param placeName the place's name
	 */
	@SuppressWarnings("unchecked")
	public void addToWishList(String userName, String placeName) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			User u = null;
			Place p = null;
			Query query = session
					.createQuery("from User where username= :name");
			query.setParameter("name", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			for (User use_r : list) {
				u = use_r;
			}
			
			Query query1 = session
					.createQuery("from Place where placename= :placename");
			query1.setParameter("placename", placeName);

			@SuppressWarnings("unchecked")
			List<Place> list1 = query1.list();
			for (Place p1 : list1) {
				p = p1;
			}

			u.getMywishList().add(p);
			session.save(u);
			tx.commit();

		} catch (Throwable t) {
			if (tx != null)
				tx.rollback();
			throw t;
		} finally {
			session.close();
		}

	}
    
	/**
	 * This method is to delete one place from one user's wish list
	 * @param userName the name of a user
	 * @param placeName the name of a place
	 */
	public void deleteFromMywishList(String userName, String placeName) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// User u =
			// (User)(session.createQuery("FROM User where username=:username").setParameter("username",
			// userName).uniqueResult());
			// Place p =
			// (Place)(session.createQuery("FROM Place where placename=:placename").setParameter("placename",
			// placeName).uniqueResult());
			User u = null;
			Place p = null;
			Query query = session
					.createQuery("from User where username= :name");
			query.setParameter("name", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			for (User use_r : list) {
				u = use_r;
			}

			Query query1 = session
					.createQuery("from Place where placename= :placename");
			query1.setParameter("placename", placeName);

			@SuppressWarnings("unchecked")
			List<Place> list1 = query1.list();
			for (Place p1 : list1) {
				p = p1;
			}
			u.getMywishList().remove(p);
			session.save(u);
			transaction.commit();
		} catch (Throwable tx) {
			if (tx != null)
				transaction.rollback();
			throw tx;
		} finally {
			session.close();
		}

	}

	/**
	 * This method is to add a place to a user's visited list
	 * @param userName the name of a user
	 * @param placeName the name of a place
	 */
	@SuppressWarnings("unchecked")
	public void addToVisitedList(String userName, String placeName) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			// User u =
			// (User)(session.createQuery("FROM User where username=:username").setParameter("username",
			// userName).uniqueResult());
			// Place p =
			// (Place)(session.createQuery("FROM Place where placename=:placename").setParameter("placename",
			// placeName).uniqueResult());
			User u = null;
			Place p = null;
			Query query = session
					.createQuery("from User where username= :name");
			query.setParameter("name", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			for (User use_r : list) {
				u = use_r;
			}

			Query query1 = session
					.createQuery("from Place where placename= :placename");
			query1.setParameter("placename", placeName);

			@SuppressWarnings("unchecked")
			List<Place> list1 = query1.list();
			for (Place p1 : list1) {
				p = p1;
			}

			u.getMyVisitedList().add(p);
			session.save(u);
			tx.commit();

		} catch (Throwable t) {
			if (tx != null)
				tx.rollback();
			throw t;
		} finally {
			session.close();
		}

	}

	/**
	 * This method is to delete a place from a user's visited list
	 * @param userName the name of a user
	 * @param placeName the name of a place
	 */
	public void deleteFromMyVisitedList(String userName, String placeName) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			User u = null;
			Place p = null;
			Query query = session
					.createQuery("from User where username= :name");
			query.setParameter("name", userName);

			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			for (User use_r : list) {
				u = use_r;
			}

			Query query1 = session
					.createQuery("from Place where placename= :placename");
			query1.setParameter("placename", placeName);

			@SuppressWarnings("unchecked")
			List<Place> list1 = query1.list();
			for (Place p1 : list1) {
				p = p1;
			}
			u.getMyVisitedList().remove(p);
			session.save(u);
			transaction.commit();
		} catch (Throwable tx) {
			if (tx != null)
				transaction.rollback();
			throw tx;
		} finally {
			session.close();
		}

	}
    /**
     * Given a city name it will return all the places in this city 
     * @param cityName the name of a city
     * @return an arrayList of arrayList of string which include each place's place name, image url, place star
     */
	@SuppressWarnings({ "unchecked", "null" })
	public ArrayList<ArrayList<String>> listPlaces(String cityName) {

		ArrayList<Place> list3 = new ArrayList<Place>();
		// List<String> placeDetail = new ArrayList<String>();
		ArrayList<ArrayList<String>> placeList = new ArrayList<ArrayList<String>>();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query2 = session
					.createQuery("from Place where city=:cityname");
			query2.setParameter("cityname", cityName);
			list3 = (ArrayList<Place>) query2.list();
			for (Place l : list3) {
				ArrayList<String> placeDetail = new ArrayList<String>();
				placeDetail.add(l.getPlaceName());
				placeDetail.add(l.getImageURL());
				// placeDetail.add(l.getDescription());
				// placeDetail.add(l.getPlaceFeature());
				placeDetail.add(String.valueOf(l.getStar()));
				placeList.add((ArrayList<String>) placeDetail);
			}
			transaction.commit();

		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}
		return placeList;

	}

	/**
	 * Given a user's name and a place's name, it will return if the place is in the user's wish list
	 * @param userName the user's name
	 * @param placeName the place's name
	 * @return if the place is in the user's wish list
	 */
	public boolean placeInWishList(String userName, String placeName) {
		if (listWishList(userName) == null) {
			return false;
		}

		for (String s : listWishList(userName)) {
			if (s.equals(placeName)) {
				return true;
			}

		}

		return false;
	}


	/**
	 * Given a user's name and a place's name, it will return if the place is in the user's visited list
	 * @param userName the user's name
	 * @param placeName the place's name
	 * @return if the place is in the user's visited list
	 */
	public boolean placeInVisitedList(String userName, String placeName) {
		if (listVisitedList(userName) == null) {
			return false;
		}

		for (String s : listVisitedList(userName)) {
			if (s.equals(placeName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Given a place feature it will return all the places with this feature 
	 * @param placeFeature the feature of a place
	 * @return a list of place name which have this feature
	 */
	public List<String> placeWithOneFeature(String placeFeature) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> placeNames = new ArrayList<String>();
		try {
			Query query = session
					.createQuery("from Place where placeFeature=:placefeature");
			query.setParameter("placefeature", placeFeature);
			List<Place> l = query.list();
			for (Place p : l) {
				placeNames.add(p.getPlaceName());
			}
			transaction.commit();
		} catch (Throwable t) {
			transaction.rollback();
			throw t;
		} finally {
			session.close();
		}
		return placeNames;
	}

	/**
	 * Given a user name it will return all the places in his wishlist
	 * @param userName the name of user
	 * @return the places in the user's wish list
	 */
	public List<String> listWishList(String userName) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			// Query query =
			// session.createQuery("SELECT U.mywishList from User U WHERE U.userName=:userName");
			Query query = session
					.createQuery("from User where username=:userName");
			query.setParameter("userName", userName);
			@SuppressWarnings("unchecked")
			List<User> list = query.list();

			if (list.isEmpty()) {
				wishList = null;
			} else {
				for (User u : list) {
					List<Place> lt = (List<Place>) u.getMywishList();
					System.out.println(lt.size());
					wishList = new ArrayList<String>();
					if (lt.isEmpty()) {
						wishList = null;
					} else {

						for (Place p : lt) {
							wishList.add(p.getPlaceName());
						}
						break;
					}
					break;

				}
			}
			tx.commit();
		} catch (Throwable t) {
			if (tx != null)
				tx.rollback();
			throw t;
		} finally {
			session.close();
		}

		return wishList;
	}

	/**
	 * Given a user name it will return all the places in his visited list
	 * @param userName the name of user
	 * @return the places in the user's visited list
	 */
	public List<String> listVisitedList(String userName) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("from User where username=:userName");
			query.setParameter("userName", userName);
			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			if (list.isEmpty()) {
				visitedlist = null;

			} else {
				for (User u : list) {
					List<Place> lt = (List<Place>) u.getMyVisitedList();
					System.out.println(lt.size());

					visitedlist = new ArrayList<String>();
					if (lt.isEmpty()) {
						visitedlist = null;
					} else {

						for (Place p : lt) {
							visitedlist.add(p.getPlaceName());
						}
						break;
					}
					break;
				}
			}
			tx.commit();
		} catch (Throwable t) {
			if (tx != null)
				tx.rollback();
			throw t;
		} finally {
			session.close();
		}
		return visitedlist;
	}

	/**
	 * Given a place name it will return a list of visited people
	 * @param placeName the place name
	 * @return the people who has visited it
	 */
	public List<String> ListVisitedPeople(String placeName) {
		List<String> visitedPeople = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Place p = null;
			Query query = session
					.createQuery("from Place where placeName=:placeName");
			query.setParameter("placeName", placeName);
			@SuppressWarnings("unchecked")
			List<Place> list = query.list();
			for (Place p1 : list) {
				p = p1;
			}

			for (User u : p.getVisitedPlace()) {
				visitedPeople.add(u.getUserName());
			}
			tx.commit();
		} catch (Throwable t) {
			if (tx != null)
				tx.rollback();
			throw t;
		} finally {
			session.close();
		}

		return visitedPeople;

	}

}
