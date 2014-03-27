package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;


/**
 * This class is to test if gives a place feature can it list all the places has the same feature.
 * @author kekekeng
 *
 */
public class GetPlacesWithFeatureTest {
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	PlaceDAO tester = new PlaceDAO(sessionFactory);
	@Test	
	public void testCommonVisited() {
		tester.insertPlace("Inner Harbor", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		tester.insertPlace("National Aquarium", "Baltimore", "Maryland","The National Aquarium exhibits a variety of species in their naturalistic habitats", "Fishing", "http://www.baltimorenewsjournal.com/wp-content/uploads/2013/01/National-Aquarium.jpg",4);
		tester.insertPlace("Towson", "Baltimore", "Maryland","Big shopping mall", "shopping", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		assertEquals("is towson in the shopping feature list?","Towson",tester.placeWithOneFeature("shopping").get(0));
		assertEquals("is inner harbor in the shopping feature list?","Inner Harbor",tester.placeWithOneFeature("Boating").get(0));
	}
}
