package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * Lists the user's friend
 * 
 * @author iamrick86
 * @version java7
 */
public class ListFriend extends SessionCommand {
	/**
	 * 
	 * @param j
	 *            sent from client side
	 */
	public ListFriend(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param context
	 *            necessary execution environment for execution of this command
	 * @return listF sent back to client side
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject listF = new JSONObject();
		if (super.executeAuthenticated()) {
			UserDAO userdao = context.getUserDAO();
			ArrayList<String> friend = new ArrayList<String>();
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);

			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			User user = usersf.getUserSession(sessionkey).getUser();
			String userName = userdao.getName(user);
			friend = (ArrayList<String>) userdao.getFriendList(userName);
			listF.put(JsonKeys.RES_LIST_FRIEND, friend);
		} else {
			listF.put(JsonKeys.RES_LIST_FRIEND, JsonKeyValues.ADD_FRIEND_FAIL);
		}
		return listF;
	}

}
