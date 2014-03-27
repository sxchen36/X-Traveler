package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * AddVisitedPlaceCommand corresponds to add visited place button in client side
 * 
 * @author iamrick86
 * @version java7
 */
public class AddWishPlaceCommand extends SessionCommand {

	public AddWishPlaceCommand(JSONObject j) {
		super(j);
	}

	/**
	 * @param context
	 *            provides the necessary execution environment. This applies
	 *            object pattern, which is great for codes extension.
	 */
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {

		JSONObject addWishPlaceResponse = new JSONObject();
		PlaceDAO placedao = context.getPlaceDAO();

		if (super.executeAuthenticated()) {
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			User user = usersf.getUserSession(sessionkey).getUser();
			// it should be in transaction
			String username = user.getUserName();
			String placename = j.getString(JsonKeys.PLACE_NAME);
			if (placedao.placeInWishList(username, placename)) {
				addWishPlaceResponse.put(JsonKeys.RES_ADD_WISH,
						JsonKeyValues.ADD_TO_WISH_FAIL);
			} else {
				placedao.addToWishList(username, placename);

				addWishPlaceResponse.put(JsonKeys.RES_ADD_WISH,
						JsonKeyValues.ADD_TO_WISH_SUC);
			}

		} else {
			addWishPlaceResponse.put(JsonKeys.RES_ADD_WISH,
					JsonKeyValues.ADD_TO_WISH_FAIL);
		}
		return addWishPlaceResponse;
	}

}
