package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * This class is to test dose paul has another interested places by the places feature of his visited list?
 * @author kekekeng
 *
 */
public class ListPersonInterestedByVisitedListTest {
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	PlaceDAO tester = new PlaceDAO(sessionFactory);
	UserDAO tester2 =new UserDAO(sessionFactory);
	@Test	
	public void testPersonInterestePlaces() {
		tester2.insertUser("paul", "100000",5,"sss","ddd");
		tester2.insertUser("ray", "202020",30,"222","6666");
		tester2.insertUser("lulu", "100000",5,"sss","ddd");
		tester2.insertUser("zhuzhu", "202020",30,"222","6666");
		tester.insertPlace("Inner Harbor", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		tester.insertPlace("National Aquarium", "Baltimore", "Maryland","The National Aquarium exhibits a variety of species in their naturalistic habitats", "Boating", "http://www.baltimorenewsjournal.com/wp-content/uploads/2013/01/National-Aquarium.jpg",4);
		tester.insertPlace("Towson", "Baltimore", "Maryland","Big shopping mall", "shopping", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		tester.addToVisitedList("paul","Inner Harbor");
		tester.addToVisitedList("lulu", "Aquarium");
		tester.addToVisitedList("zhuzhu","Inner Harbor");
		assertEquals("Is paul has another interested places by the place feature of his visited list?","National Aquarium",tester2.listInterestedByVisitedPlaces("paul").get(1));
		
	}
}
