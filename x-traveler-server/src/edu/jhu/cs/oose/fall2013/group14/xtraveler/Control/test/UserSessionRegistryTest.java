package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSession;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;

/**
 * UserSessionRegistryTest test UserSessionRegistry class
 * 
 * @author iamrick86
 * @version java7
 */
public class UserSessionRegistryTest {

	@Test
	public void test() {
		UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
		String sessionKey = "abcd";
		long lastTime = System.currentTimeMillis();
		User user = new User();
		UserSession us = new UserSession(user, lastTime);
		us.setUser(user);
		us.setCurrentTime(lastTime);
		usersf.addSession(sessionKey, us);
		assertEquals(us, usersf.getUserSession(sessionKey));
	}
}
