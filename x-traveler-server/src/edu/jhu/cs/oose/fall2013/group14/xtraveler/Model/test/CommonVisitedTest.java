package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * This class is to test if the user have common interested traveler who have the same visited places
 * @author kekekeng
 *
 */
public class CommonVisitedTest {
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	PlaceDAO tester = new PlaceDAO(sessionFactory);
	UserDAO tester2 =new UserDAO(sessionFactory);
	@Test	
	public void testCommonVisited() {
		
		tester2.insertUser("paul", "100000",5,"sss","ddd");
		tester2.insertUser("ray", "202020",30,"222","6666");
		tester2.insertUser("lulu", "100000",5,"sss","ddd");
		tester2.insertUser("zhuzhu", "202020",30,"222","6666");
		tester.insertPlace("Inner Harbor", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		tester.insertPlace("National Aquarium", "Baltimore", "Maryland","The National Aquarium exhibits a variety of species in their naturalistic habitats", "Fishing", "http://www.baltimorenewsjournal.com/wp-content/uploads/2013/01/National-Aquarium.jpg",4);
		tester.addToVisitedList("paul", "Inner Harbor");
		tester.addToVisitedList("paul", "Aquarium");
		tester.addToVisitedList("ray","Inner Harbor");
		tester.addToWishList("paul","Inner Harbor");
		tester.addToWishList("lulu", "Aquarium");
		tester.addToWishList("zhuzhu","Inner Harbor");
		assertEquals("is user paul has common interested people?","ray",tester2.listCommonVisitetPeople("paul").get(1));
		
	}
}
