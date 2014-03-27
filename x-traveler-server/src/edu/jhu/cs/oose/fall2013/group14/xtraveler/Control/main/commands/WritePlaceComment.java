package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceCommentDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

public class WritePlaceComment extends SessionCommand {

	public WritePlaceComment(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	/**
	 * insert a place comment into database. This command need to be
	 * authenticated.
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		// TODO Auto-generated method stub
		JSONObject placeComment = new JSONObject();
		if (super.executeAuthenticated()) {
			PlaceCommentDAO comment = context.getPlaceCommentDAO();
			String placename = j.getString(JsonKeys.PLACE_NAME);
			String userName = j.getString(JsonKeys.CURRENT_USER);
			String rate = j.getString(JsonKeys.PLACE_RATE);
			String comment1 = j.getString(JsonKeys.PLACE_COMMENT_CONTENT);
			int star = Integer.valueOf(rate);
			comment.insertPlaceComment(userName, placename, star, comment1);
			placeComment.put("response", "success");

		} else {
			placeComment.put("response", "fail");
		}
		return placeComment;
	}

}
