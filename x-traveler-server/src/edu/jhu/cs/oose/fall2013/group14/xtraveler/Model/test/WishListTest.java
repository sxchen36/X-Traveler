package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * To test if the use  can get add a place to his wishlist
 * @author kekekng
 *
 */
public class WishListTest {
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	PlaceDAO tester = new PlaceDAO(sessionFactory);
	UserDAO tester2 =new UserDAO(sessionFactory);

	/**
	 * To test if a person can list his wish list
	 */
	@Test	
	public void testAddToWishList(){
		tester2.insertUser("paul", "100000", 40, "20@slk.com","male");
		tester.insertPlace("Inner Harbor", "Baltimore", "Maryland","Beautiful place has good view and good restuarants", "Boating", "http://wikitravel.org/upload/en/thumb/c/ce/Baltimore24.jpg/400px-Baltimore24.jpg",5);
		tester.addToWishList("paul", "Inner Harbor");
		assertEquals("is inner harbor add to paul's wishList?","Inner Harbor", tester.listWishList("paul").get(0));
	}
}
