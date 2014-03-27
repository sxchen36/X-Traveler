package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * Delete a friend by apply this command
 * 
 * @author iamrick86
 * @version java7
 */

public class DeleteFriend extends SessionCommand {

	public DeleteFriend(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param context
	 *            contains DAO class and sessionFactory
	 * @return deleteF JSONObject returned to the client side
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject deleteF = new JSONObject();
		if (super.executeAuthenticated()) {
			UserDAO userdao = context.getUserDAO();
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			String friendName = j.getString(JsonKeys.FRIEND_NAME);
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			User user = usersf.getUserSession(sessionkey).getUser();
			String userName = userdao.getName(user);
			userdao.deleteFriend(userName, friendName);
			deleteF.put(JsonKeys.RES_DELETE_FRIEND,
					JsonKeyValues.DELETE_FRIEND_SUC);
		} else {
			deleteF.put(JsonKeys.RES_DELETE_FRIEND,
					JsonKeyValues.DELETE_FRIEND_FAIL);
		}
		return deleteF;
	}

}
