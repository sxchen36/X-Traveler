package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * LogOutCommand reply to the request to log out the user, and remove the
 * session key from the sessionRegistry map.
 * 
 * @author iamrick86
 * @version java7
 */
public class LogOutCommand extends SessionCommand {

	public LogOutCommand(JSONObject j) {
		super(j);

		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("null")
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject response = null;
		if (super.executeAuthenticated()) {
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			String sessionkey = j.getString("sessionkey");
			usersf.removeSession(sessionkey);
			response.put(JsonKeys.RES_LOG_OUT, JsonKeyValues.LOG_OUT_SUC);
		} else {
			response.put(JsonKeys.RES_LOG_OUT, JsonKeyValues.LOG_OUT_FAIL);
		}

		return response;
	}

}
