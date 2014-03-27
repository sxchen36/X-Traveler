package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceCommentDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * To test if a place can get its place comment list
 * @author kekekeng
 *
 */
public class PlaceCommetnDAOTest {
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	PlaceDAO tester = new PlaceDAO(sessionFactory);
	UserDAO tester2 =new UserDAO(sessionFactory);
	PlaceCommentDAO tester3 = new PlaceCommentDAO(sessionFactory);
	@Test	
	public void TestGetPlaceCommentContent(){
		tester2.insertUser("paul", "100000",5,"sss","ddd");
		tester2.insertUser("zhuzhu", "202020",30,"222","6666");
		tester.insertPlace("Inner Harbor", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		tester3.insertPlaceComment("paul", "Inner Harbor", 5, "really good");
		tester3.insertPlaceComment("zhuzhu", "Inner Harbor", 4 ,"really nice");
		assertEquals("is Inner Harbor has place comment?","really good",tester3.getPlaceCommentContent("Inner Harbor").get(0).get(0));
		assertEquals("is Inner Harbor has right average star?","5",tester3.getPlaceCommentContent("Inner Harbor").get(0).get(1));
		assertEquals("is Inner Harbor has place comment?","really nice",tester3.getPlaceCommentContent("Inner Harbor").get(1).get(0));
		assertEquals("is Inner Harbor has right average star?","4",tester3.getPlaceCommentContent("Inner Harbor").get(1).get(1));
		
		
	}
}
