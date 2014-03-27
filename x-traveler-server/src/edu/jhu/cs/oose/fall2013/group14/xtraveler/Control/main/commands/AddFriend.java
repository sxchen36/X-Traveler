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
 * Able to add friend with this command for later use, can set permission for
 * user's friend
 * 
 * @version java7
 * @author iamrick86
 * 
 */

public class AddFriend extends SessionCommand {

	public AddFriend(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 *            ExecutionContext provide the necessary execution environment
	 *            for the adding a friend.
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		// TODO Auto-generated method stub
		JSONObject addF = new JSONObject();
		if (super.executeAuthenticated()) {
			UserDAO userdao = context.getUserDAO();
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			String friendName = j.getString(JsonKeys.FRIEND_NAME);
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			User user = usersf.getUserSession(sessionkey).getUser();
			String userName = userdao.getName(user);
			userdao.addFriend(userName, friendName);
			addF.put(JsonKeys.RES_ADD_FRIEND, JsonKeyValues.ADD_FRIEND_SUC);
		} else {
			addF.put(JsonKeys.RES_ADD_FRIEND, JsonKeyValues.ADD_FRIEND_FAIL);
		}
		return addF;
	}

}
