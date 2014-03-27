package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * Lists the user's visited place
 * 
 * @author iamrick86
 * @version java7
 * 
 */
public class GetVisitedListCommand extends SessionCommand {
	/**
	 * 
	 * @param j
	 *            JSONObject sent from client side
	 */
	public GetVisitedListCommand(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 *            necessary execution environment for this command
	 * @return getVisitedListCommandResponse sent back to client side
	 */
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {

		PlaceDAO placedao = context.getPlaceDAO();
		JSONObject getVisitedListCommandResponse = new JSONObject();
		if (super.executeAuthenticated()) {
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			User user = usersf.getUserSession(sessionkey).getUser();
			// it should be in transaction
			// String username = userdao.getName(user);
			String username = user.getUserName();
			List<String> placenames = placedao.listVisitedList(username);

			try {

				getVisitedListCommandResponse.put(JsonKeys.RES_VISITED_LIST,
						placenames);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return getVisitedListCommandResponse;
		} else {

			try {
				getVisitedListCommandResponse.put(JsonKeys.RES_VISITED_LIST,
						JsonKeyValues.GET_WISH_FAIL);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return getVisitedListCommandResponse;
		}

	}
}