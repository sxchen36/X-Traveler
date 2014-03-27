package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main;

import java.util.HashMap;
import java.util.Map;

/**
 * UserSessionRegistry stores a SINGLETON which contains a map mapping from
 * session key to UserSession. It stores all the information of all session key.
 * 
 * @author iamrick86
 * @version java7
 * 
 */
public class UserSessionRegistry {
	public static final UserSessionRegistry SINGLETON = new UserSessionRegistry();

	private Map<String, UserSession> allUserSession;

	public UserSessionRegistry() {
		allUserSession = new HashMap<String, UserSession>();
	}

	public UserSession getUserSession(String sessionkey) {

		return allUserSession.get(sessionkey);
	}

	public void addSession(String sessionKey, UserSession usersession) {
		allUserSession.put(sessionKey, usersession);
	}

	public void removeSession(String sessionKey) {
		allUserSession.remove(sessionKey);
	}

}
