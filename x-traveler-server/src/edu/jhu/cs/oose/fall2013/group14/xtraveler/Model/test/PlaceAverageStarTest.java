package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceCommentDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * This class is to test if one place has average comment star 
 * @author kekekeng
 *
 */
public class PlaceAverageStarTest {
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	PlaceCommentDAO tester = new PlaceCommentDAO(sessionFactory);
	PlaceDAO tester1 = new PlaceDAO(sessionFactory);
	UserDAO tester2 =new UserDAO(sessionFactory);
	@Test	
	public void testCommonVisited() {
	tester2.insertUser("paul", "100000",5,"sss","ddd");
	tester2.insertUser("ray", "202020",30,"222","6666");
	tester2.insertUser("lulu", "100000",5,"sss","ddd");
	tester2.insertUser("zhuzhu", "202020",30,"222","6666");
	tester1.insertPlace("Inner Harbor", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
	tester.insertPlaceComment("paul", "Inner Harbor", 5, "really good");
	tester.insertPlaceComment("zhuzhu", "Inner Harbor", 4 ,"really nice");
	tester.insertPlaceComment("lulu","Inner Harbor", 3 ,"really beautiful");
	assertEquals("is Inner Harbor has right average star?",4,tester.getPlaceCommentStar("Inner Harbor"));
	}
}
