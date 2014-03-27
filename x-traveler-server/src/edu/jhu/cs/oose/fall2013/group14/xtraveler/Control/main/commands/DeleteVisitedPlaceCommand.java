package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * Delete visited place by applying this command
 * 
 * @author iamrick86
 * 
 */
public class DeleteVisitedPlaceCommand extends SessionCommand {

	/**
	 * 
	 * @param j
	 *            JSONObject sent from client side
	 */
	public DeleteVisitedPlaceCommand(JSONObject j) {
		super(j);
	}

	/**
	 * @param context
	 *            ExecutionContext that the command need
	 * @return DeleteVisitedPlaceResponse JSONObject sent to the client side
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject deleteVisitedPlaceResponse = new JSONObject();
		// TODO Auto-generated method stub
		if (super.executeAuthenticated()) {
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			User user = usersf.getUserSession(sessionkey).getUser();
			UserDAO userdao = context.getUserDAO();
			String username = userdao.getName(user);
			PlaceDAO placedao = context.getPlaceDAO();
			// change the key to JSONKeys
			String placename = j.getString("");

			placedao.deleteFromMyVisitedList(username, placename);
			// change the keys
			deleteVisitedPlaceResponse.put(JsonKeys.RES_DELETE_VISITED,
					JsonKeyValues.DELETE_VISITED_SUC);
		} else {
			deleteVisitedPlaceResponse.put(JsonKeys.RES_DELETE_VISITED,
					JsonKeyValues.DELETE_VISITED_FAIL);
		}
		return deleteVisitedPlaceResponse;
	}

}
