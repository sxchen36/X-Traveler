package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSession;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;

/**
 * UserSessionTest test the UserSession class
 * 
 * @author iamrick86
 * @version java7
 * 
 */
public class UserSessionTest {

	@Test
	public void test() {
		User user = new User();
		long start = 10000;
		long end = 30000;
		UserSession userSession = new UserSession(user, start);
		assertEquals(false, userSession.isSessionOut(end));
	}

}
