package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSession;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * SessionCommand is the super class of all commands who need session key. All
 * session command need to extend it.
 * 
 * @author iamrick86
 */
public abstract class SessionCommand implements Command {
	JSONObject j;

	public SessionCommand(JSONObject j) {
		this.j = j;
	}

	/**
	 * 
	 * @return feedback When feedback is false, it means authenticated false.
	 *         When feedback is true, it means this client's request is
	 *         trustworthy.
	 * @throws CommandException
	 * @throws JSONException
	 */
	public boolean executeAuthenticated() throws CommandException,
			JSONException { // template method pattern

		Boolean feedback = false;
		System.out.println("2");
		String sessionkey = j.getString(JsonKeys.SESSION_KEY);
		System.out.println(sessionkey);
		System.out.println("1");
		UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
		if (usersf.getUserSession(sessionkey) == null) {
			feedback = false;
			throw new NullPointerException(
					"Do me a favor, give me your right session key");
		} else {
			UserSession userSession = usersf.getUserSession(sessionkey);
			long time = System.currentTimeMillis();
			if (userSession.isSessionOut(time)) {
				usersf.removeSession(sessionkey);
				feedback = false;
				throw new CommandException("session out");
			} else {
				userSession.setCurrentTime(time);
				feedback = true;
			}
		}
		return feedback;
	}
}
