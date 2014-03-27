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
 * Deletes wish place from user's wish list
 * 
 * @author iamrick86
 * @version java7
 */
public class DeleteWishPlaceCommand extends SessionCommand {

	/**
	 * 
	 * @param j
	 *            JSONObject sent to server side
	 */
	public DeleteWishPlaceCommand(JSONObject j) {
		super(j);
	}

	/**
	 * @param context
	 *            ExecutionContext needed for execute this command
	 * @return deleteWishPlaceResponse JSONObject sent to client side
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject deleteWishPlaceResponse = new JSONObject();
		// TODO Auto-generated method stub
		if (super.executeAuthenticated()) {
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			String sessionkey = j.getString("sessionkey");
			User user = usersf.getUserSession(sessionkey).getUser();
			UserDAO userdao = context.getUserDAO();
			String username = userdao.getName(user);
			PlaceDAO placedao = context.getPlaceDAO();
			// change the place name key to shared
			String placename = j.getString("");
			placedao.deleteFromMywishList(username, placename);
			// change the keys and key values according to shared
			deleteWishPlaceResponse.put(JsonKeys.RES_DELETE_WISH,
					JsonKeyValues.DELETE_FRIEND_SUC);
		} else {
			deleteWishPlaceResponse.put(JsonKeys.RES_DELETE_WISH,
					JsonKeyValues.DELETE_FRIEND_FAIL);
		}
		return deleteWishPlaceResponse;
	}

}
