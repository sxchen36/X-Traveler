package edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.test;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.SocketManager;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * To test the user dao can get the information of a user
 * @author kekekeng
 * 
 */
public class UserDAOTest {
	
	
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
	static SocketManager socketManager = new SocketManager(sessionFactory);
	UserDAO tester = new UserDAO(sessionFactory);
	
	@Test
	
	public void test() {
		
		tester.insertUser("paul","010101",20, "dd@22.com" , "male");   
		assertEquals("is paul in database?", true , tester.userInDatabase("paul"));
	}
	
	@Test
	public void test1() {
		tester.insertUser("paul","010101",20, "dd@22.com" , "male"); 
		tester.insertUser("ray","202020",30,"tt@ww.com","female");
		tester.addFriend("paul", "ray");
		assertEquals("is paul has friend ray?", "ray" , tester.getFriendList("paul").get(0));
	}
	
	@Test
	public void test2() {
		tester.insertUser("paul","010101",20, "dd@22.com" , "male");   
		assertEquals("is paul has right password?", "010101" , tester.getPassword("paul"));
	}
	
	@Test
	public void test3() {
		tester.insertUser("paul","010101",20, "dd@22.com" , "male");   
		assertEquals("is paul has right age", 20 , tester.getAge("paul"));
	}
	
	@Test
	public void test4() {
		tester.insertUser("paul","010101",20, "dd@22.com" , "male");   
		assertEquals("is paul has right email?", "dd@22.com" , tester.getEamil("paul"));
	}
	
	@Test
	public void test5() {
		tester.insertUser("paul","010101",20, "dd@22.com" , "male");   
		assertEquals("is paul has right sex?", "male" , tester.getSex("paul"));
	}
	
	
}
