package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * To test if the user has other places he may wants to go, by the places feature of his wish list.
 * @author kekekeng
 */
public class ListPersonInterestedPlaceTest {
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
		tester.addToWishList("paul","Inner Harbor");
		tester.addToWishList("lulu", "Aquarium");
		tester.addToWishList("zhuzhu","Inner Harbor");
		assertEquals("Is national aquarim is in the interested places of paul with the same feaures of his wish list places ?","National Aquarium",tester2.listInterestedPlaces("paul").get(1));
		
	}
}
