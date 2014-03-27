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
 * AddVisitedPlaceCommand corresponds to add visited place button in client side
 * 
 * @author iamrick86
 */
public class AddVisitedPlaceCommand extends SessionCommand {

	public AddVisitedPlaceCommand(JSONObject j) {
		super(j);

	}

	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {

		String placename = null;
		JSONObject addVisitedPlaceResponse = new JSONObject();

		PlaceDAO placedao = context.getPlaceDAO();
		UserDAO userdao = context.getUserDAO();

		if (super.executeAuthenticated()) {
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			User user = usersf.getUserSession(sessionkey).getUser();
			// it should be in transaction
			String username = userdao.getName(user);
			try {
				placename = j.getString(JsonKeys.PLACE_NAME);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			if (placedao.placeInVisitedList(username, placename)) {
				addVisitedPlaceResponse.put(JsonKeys.RES_ADD_VISIT,
						JsonKeyValues.ADD_TO_VISITEDLIST_FAIL);
			} else {
				placedao.addToVisitedList(username, placename);

				try {
					addVisitedPlaceResponse.put(JsonKeys.RES_ADD_VISIT,
							JsonKeyValues.ADD_TO_VISITEDLIST_SUC);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			addVisitedPlaceResponse.put(JsonKeys.RES_ADD_VISIT,
					JsonKeyValues.ADD_TO_VISITEDLIST_FAIL);
		}
		return addVisitedPlaceResponse;

	}
}
