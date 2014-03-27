package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * This class is to test if the place can get all the right information about it
 * @author kekekeng
 *
 */
public class PlaceDAOTest {
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	PlaceDAO tester = new PlaceDAO(sessionFactory);
	UserDAO tester2 =new UserDAO(sessionFactory);
	
	/**
	 * This method is to test if a place is in the database
	 */
	@Test	
	public void testPlaceInDatabase() {
		
		tester.insertPlace("Place7_bal", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);   
		assertEquals("is inner harbor in database?", "Place7_bal" , tester.getPlace("Place7_bal").getPlaceName());
	}

	/**
	 * This method is to test if the place can be get the right description
	 */
	@Test	
	public void testPlaceHasDescription() {
		
		tester.insertPlace("Place6_bal", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		assertEquals("is inner harbor has right place description?", "Beautiful place has good view and good restuarants" , tester.getPlaceDescription("Place6_bal"));
	}
	
	/**
	 * This method is to test if the place can be get the right star
	 */
	@Test	
	public void testPlaceHasStar() {
		
		tester.insertPlace("Place4_bal", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		assertEquals("is inner harbor has rigth star?", 5 , tester.getPlaceRate("Place4_bal"));
	}
	
	/**
	 * This method is to test if the place can be get the right image url
	 */
	@Test	
	public void testPlaceHasImageURL() {
		
		tester.insertPlace("Place5_bal", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		assertEquals("is inner harbor has rigth imageURL?","http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg", tester.getPlaceImage("Place5_bal"));
	}
	
	/**
	 * This method is to test if the place can be get the place feature
	 */
	@Test	
	public void testPlaceHasFeature() {
		
		tester.insertPlace("Place4_bal", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		assertEquals("is inner harbor has rigth feature?","Boating", tester.getPlaceFeature("Place4_bal"));
	}
	
	/**
	 * This method is to test if the place can be get the right city
	 */
	@Test	
	public void testPlaceHasCity() {
		
		tester.insertPlace("Place1_bal", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		assertEquals("is inner harbor has rigth city?","Baltimore", tester.getPlaceCity("Place1_bal"));
	}
	
	/**
	 * This method is to test if the place can be get the right state
	 */
	@Test	
	public void testPlaceState() {
		
		tester.insertPlace("Place2_bal", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		assertEquals("is inner harbor has rigth state?","Maryland", tester.getPlaceState("Place2_bal"));
	}
		

}
